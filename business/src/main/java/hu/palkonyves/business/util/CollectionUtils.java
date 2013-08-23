package hu.palkonyves.business.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class CollectionUtils {

	public static <F, T> Collection<F> transform(Collection<T> collection, Function<T, F> function){
		List<F> result = new ArrayList<F>();
		for(T originalElement : collection){
			F resultedElement = function.call(originalElement);
			result.add(resultedElement);
		}

		return result;
	}
}
