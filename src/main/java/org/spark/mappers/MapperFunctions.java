package org.spark.mappers;

import org.apache.spark.api.java.function.PairFunction;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import scala.Tuple2;

/**
 * @author vijith.reddy Use this class to write Mapper functions
 */
public class MapperFunctions {

	// This function is used for assigning Integer 1 to every key
	public static final PairFunction<String, String, Integer> mapOnes = s -> {
		return new Tuple2<String, Integer>(s, 1);
	};

	// This map can parse JSON and create a tuple from that JSON. Customize your
	// code with your own schema
	public static final PairFunction<String, String, String> mapJson = eachItem -> {
		JSONParser parser = new JSONParser();
		String country = "";
		String lines = "";
		try {
			Object obj = parser.parse(eachItem);
			JSONObject jsonObj = (JSONObject) obj;
			country = (String) jsonObj.get("country");
			String name = (String) jsonObj.get("name");

			lines = name + "\t" + country;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Tuple2<String, String>(lines, country);
	};
}
