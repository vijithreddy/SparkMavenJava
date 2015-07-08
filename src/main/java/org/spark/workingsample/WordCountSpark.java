package org.spark.workingsample;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;

import java.util.Arrays;

import scala.Tuple2;

public final class WordCountSpark {
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.err
					.println("Please use: WordCountSpark <master> <input file> <output file> <numOutputFiles>");
			System.exit(1);
		}

		@SuppressWarnings("resource")
		JavaSparkContext spark = new JavaSparkContext(args[0],
				"Java Wordcount", System.getenv("SPARK_HOME"),
				JavaSparkContext.jarOfClass(WordCountSpark.class));
		// input file
		JavaRDD<String> file = spark.textFile(args[1]);
		// number of output files
		Integer numOutputFiles = Integer.parseInt(args[3]);

		// Flat map function using functional programming paradigm in java 8
		FlatMapFunction<String, String> wordMap = inputPara -> {
			return Arrays.asList(inputPara.toLowerCase().split("\\W+"));
		};

		JavaRDD<String> words = file.flatMap(wordMap);

		// Map each word with Integer 1
		PairFunction<String, String, Integer> mapOnes = s -> {
			return new Tuple2<String, Integer>(s, 1);
		};

		JavaPairRDD<String, Integer> pairs = words.mapToPair(mapOnes);

		// Add the integers with the same key
		Function2<Integer, Integer, Integer> pairWords = (a, b) -> {
			return a + b;
		};
		// Reduce by each word and generate the given number of output files
		JavaPairRDD<String, Integer> counts = pairs.reduceByKey(pairWords,
				numOutputFiles);
		// Sort by key
		counts.sortByKey(true).saveAsTextFile(args[2]);
		System.exit(0);
	}
}
