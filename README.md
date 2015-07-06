# SparkMavenJava
Spark Maven java project

To execute the spark job, run the following command replacing the properties in {}.


./bin/spark-submit --class org.spark.workingsample.WordCountSpark --properties-file {config file .conf} {jar file} {spark master URL ex: spark://xxxxx:7077} {input_file} {output_file} {number of output files}


Use sparkjob.conf found in this repository or build your custom configuration file.
