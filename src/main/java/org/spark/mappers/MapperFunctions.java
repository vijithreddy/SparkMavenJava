package org.spark.mappers;

import org.apache.spark.api.java.function.*;

import scala.Tuple2;

/**
 * @author vijith.reddy
 * Use this class to write Mapper functions
 */
public class MapperFunctions {
	
	//This function is used for assigning Integer 1 to every key
	public static final PairFunction<String, String, Integer> mapOnes = s -> {
		return new Tuple2<String, Integer>(s, 1);
	};
}
