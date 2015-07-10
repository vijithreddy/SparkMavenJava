package org.spark.reducers;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

import scala.Tuple2;

/**
 * @author vijith.reddy Use this class to write Reducer functions
 */
public class ReducerFunctions {
	// Word count use case: Adding integers with same keys
	Function2<Integer, Integer, Integer> pairWords = (a, b) -> {
		return a + b;
	};

	// Filter functions can be used to filter your result set
	Function<Tuple2<String, String>, Boolean> func = (map) -> {
		return (map._2.equals("key"));
	};

}
