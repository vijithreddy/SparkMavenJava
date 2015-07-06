package org.spark.workingsample;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;

import java.util.Arrays;

import scala.Tuple2;

@SuppressWarnings("serial")
public final class WordCountSpark {
	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			System.err
					.println("Usage: WordCountSpark <master> <input> <output> <numOutputFiles>");
			System.exit(1);
		}

		@SuppressWarnings("resource")
		//Create Spark context with the desired master
		JavaSparkContext spark = new JavaSparkContext(args[0],
				"Java Wordcount", System.getenv("SPARK_HOME"),
				JavaSparkContext.jarOfClass(WordCountSpark.class));
		//Input file RDD is created from the command line
		JavaRDD<String> file = spark.textFile(args[1]);
		
		Integer numOutputFiles = Integer.parseInt(args[3]);
		//Separate to each word
		JavaRDD<String> words = file
				.flatMap(new FlatMapFunction<String, String>() {
					public Iterable<String> call(String s) {
						return Arrays.asList(s.toLowerCase().split("\\W+"));
					}
				});
		// Map each word to integer 1
		JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call(String s) {
						return new Tuple2<String, Integer>(s, 1);
					}
				});
		// Add the mapped integers for same words.
		JavaPairRDD<String, Integer> counts = pairs.reduceByKey(
				new Function2<Integer, Integer, Integer>() {
					public Integer call(Integer a, Integer b) {
						return a + b;
					}
				}, numOutputFiles);
		//sort by words and save to the output file mentioned in the command line
		counts.sortByKey(true).saveAsTextFile(args[2]);
		System.exit(0);
	}
}
