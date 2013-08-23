package hu.palkonyves.business.util;


import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@CdiTransactionInterceptor
@Interceptor
public class TransactionInterceptorBinding {

	ThreadLocal<int[]> interceptorCallStack = new ThreadLocal<int[]>(){

		@Override
		protected int[] initialValue(){
			return new int[]{0};
		}
	};

	private void push(){
		int[] i = interceptorCallStack.get();
		i[0]++;
		interceptorCallStack.set(i);
	}

	private int pop(){
		int[] i = interceptorCallStack.get();
		int prev = i[0];
		i[0]--;
		interceptorCallStack.set(i);
		return prev;
	}

	private void reset() {
		int[] i = interceptorCallStack.get();
		i[0] = 0;
		interceptorCallStack.set(i);
	}

	private int get(){
		return interceptorCallStack.get()[0];
	}

	@Inject
	EntityManager em;

	@AroundInvoke
	public Object manageTransaction(InvocationContext context) throws Exception{
		Exception e = null;
		Object result = null;
		try{
			push();
			em.getTransaction().begin();
			result = context.proceed();
		} catch(Exception ex){
			e = ex;
		} finally{

			rollbackOnException(e);

			if(em.getTransaction().getRollbackOnly()){
				// transaction was marked as rollback only

				em.getTransaction().rollback();
				reset();
			}
			else{
				// otherwise commit
				int prevState = pop();
				if(prevState == 1){
					em.getTransaction().commit();
				}
			}
		}

		return result;
	}

	private void rollbackOnException(Exception e) throws Exception{
		if(e != null){
			// if there was an exception

			if(get() != 0){
				// roll back only if it wasn't already
				em.getTransaction().rollback();
				reset();
			}

			// throw it
			throw e;
		}
	}
}
