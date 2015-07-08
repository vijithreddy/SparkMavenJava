package org.spark.flatmaps;

import java.util.Arrays;

import org.apache.spark.api.java.function.*;

/**
 * @author vijith.reddy
 * Use this class to write custom Flat map functions
 */
public class FlatMapFunctions {

	// This function can be used to split the text per word basis
	public static final FlatMapFunction<String, String> wordMap = inputPara -> {
		return Arrays.asList(inputPara.toLowerCase().split("\\W+"));
	};
	
	
	
}
