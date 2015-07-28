# SparkMavenJava
Spark Maven java project

To execute the spark job, run the following command replacing the properties in {}.


./bin/spark-submit --class org.spark.workingsample.WordCountSpark --properties-file {config file .conf} {jar file} {spark master URL ex: spark://xxxxx:7077} {input_file} {output_file} {number of output files}


Use sparkjob.conf found in this repository or build your custom configuration file.

Following are the instructions for people who haven't installed Spark standalone

Note: Spark can be installed in any unix like systems (Mac, any flavor of Linux etc..)

Skip the steps if not necessary 

Step 1: Install Oracle JDK 8 (Functional programming paradigm is supported using Java 8, well not truly a 100% functional paradigm, but java has brought the best of two worlds that is: OO and Functional). 

Note: To download java8 please use the link below. Please do not use Open JDK

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html 

Step 2: Set JAVA_HOME 

export JAVA_HOME=$(/usr/libexec/java_home) 

Recommendation: Create a .bash_profile shell file, add your environment variables, and source that file for permanent use. 

Step 3: Install Scala

Mac users install homebrew first running the following command 

 ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)" 
 
 Step 4: Set SCALA_HOME 

export SCALA_HOME=/usr/local/bin/scala

export PATH=$PATH:$SCALA_HOME/bin 

Step 5 : Download Spark from https://spark.apache.org/downloads.html  and extract

Step 6: Build and Install Apache Spark 

sbt/sbt clean assembly (sbt directory is under the extracted spark directory)

Step 7: Fire up Spark shell (pyspark or spark-shell)

--For Spark Cluster:

Step 8: Start Spark server:

Go to spark installation directory and run:

./sbin/start-master.sh

Go to the logs and check the Spark master URL (Or open up localhost:8080 and check for the spark master URL)


Step 9: Start Spark Workder Daemons:

./sbin/start-slave.sh {master-spark-URL} -c {add cores} -m {Add memory}

If you have not specified -c and -m values then Spark slave is assigned the entire system capacity.

Check localhost:8080 (spark UI) for worker daemon, if not check the logs under logs directory for troubleshooting.

Happy Sparking!!!!
