package gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class BasicGson {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {

		parseJsonToObj();
		parseObjToJson();
		readJsonFile("car.json");
		readJsonListFile("cars.json");

	}

	static void parseJsonToObj() {
		String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
		Gson gson = new Gson();
		Car car = gson.fromJson(json, Car.class);
		System.out.println(car.brand);
	}

	static void parseObjToJson() {
		Car car = new Car();
		car.brand = "Rover";
		car.doors = 5;

		Gson gson = new Gson();
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String json = gson.toJson(car);
		System.out.println(json);

	}

	static void readJsonFile(String filename) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		System.out.println("===== JSON FILE READ ======");
		Gson gson = new Gson();
		File file = new File("./TEXT/", filename);
		Car car = gson.fromJson(new FileReader(file), Car.class);
		System.out.println(car.brand);

		JsonElement json = gson.fromJson(new FileReader(file), JsonElement.class);
		String result = gson.toJson(json);
		System.out.println(result);

	}

	static void readJsonListFile(String filename) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		System.out.println("===== JSON LIST FILE READ ======");
		Gson gson = new Gson();
		File file = new File("./TEXT/", filename);
		Car[] cars = gson.fromJson(new FileReader(file), Car[].class);

		for (int i = 0; i < cars.length; i++) {
			System.out.println(cars[i].brand);
		}
	}

}

class Car {
	public String brand = null;
	public int doors = 0;
}
