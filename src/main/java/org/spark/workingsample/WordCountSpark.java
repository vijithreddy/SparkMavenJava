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
		JavaSparkContext spark = new JavaSparkContext(args[0],
				"Java Wordcount", System.getenv("SPARK_HOME"),
				JavaSparkContext.jarOfClass(WordCountSpark.class));

		JavaRDD<String> file = spark.textFile(args[1]);

		Integer numOutputFiles = Integer.parseInt(args[3]);

	
		FlatMapFunction<String, String> wordMap=inputLine->{
			return Arrays.asList(inputLine.toLowerCase().split("\\W+"));
		};
		
		JavaRDD<String> words = file
				.flatMap(wordMap);
		
		PairFunction<String, String, Integer> mapOnes=(String s)->{
			return new Tuple2<String, Integer>(s, 1);
		};

		JavaPairRDD<String, Integer> pairs = words.mapToPair(mapOnes);
		
		Function2<Integer, Integer, Integer>pairWords=(Integer a, Integer b)->{
			return a + b;
		};

		JavaPairRDD<String, Integer> counts = pairs.reduceByKey(
				pairWords, numOutputFiles);

		counts.sortByKey(true).saveAsTextFile(args[2]);
		System.exit(0);
	}
}
