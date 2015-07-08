package org.spark.reducers;

import org.apache.spark.api.java.function.Function2;

/**
 * @author vijith.reddy
 * Use this class to write Reducer functions
 */
public class ReducerFunctions {
	// Word count use case: Adding integers with same keys
	Function2<Integer, Integer, Integer> pairWords = (a, b) -> {
		return a + b;
	};
	
	
	

}
