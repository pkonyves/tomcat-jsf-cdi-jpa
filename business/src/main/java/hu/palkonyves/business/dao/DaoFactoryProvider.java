package hu.palkonyves.business.dao;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class DaoFactoryProvider {

    EntityManagerFactory emf = null;

    EntityManager em;

    private final JpaDaoFactory jpaDaoFactoryInstance = new JpaDaoFactory();

    @PostConstruct
    public void postConstruct(){
	emf = Persistence.createEntityManagerFactory("banana-pu");
	em = emf.createEntityManager();
	jpaDaoFactoryInstance.init(em);
    }

    public DaoFactory getJpaFactory(){
	return jpaDaoFactoryInstance;
    }

    @Produces
    @ApplicationScoped
    public EntityManager getEntityManager(){
	return em;
    }
}
