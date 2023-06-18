package gson;

import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OtherGson {
    public static void main(String[] args) {

        mapParser();
        System.out.println("==============================");
        jsonElementParser();
    }

    private static void mapParser() {
        String jsonString = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\", \"rating\": 4.5}";

        Gson gson = new Gson();

        // Deserialize the JSON string into a Map object
        Type type = Map.class;
        Map<String, Object> dataMap = gson.fromJson(jsonString, type);

        // Access the values from the Map
        String name = (String) dataMap.get("name");
        int age = ((Number) dataMap.get("age")).intValue();
        String city = (String) dataMap.get("city");
        double rating = ((Number) dataMap.get("rating")).doubleValue();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Rating: " + rating);
    }

    private static void jsonElementParser() {
        String jsonString = "{\"name\": \"John\", \"age\": 30, \"city\": \"New York\", \"pets\": [{\"name\": \"Fluffy\", \"type\": \"cat\"}, {\"name\": \"Buddy\", \"type\": \"dog\"}]}";

        // Create a Gson object
        Gson gson = new Gson();

        // Use JsonParser to parse the JSON string
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(jsonString);

        // Convert the parsed JSON element to a Gson object
        JsonObject gsonObject = jsonElement.getAsJsonObject();

        // Access the values using Gson methods
        String name = gsonObject.get("name").getAsString();
        int age = gsonObject.get("age").getAsInt();
        String city = gsonObject.get("city").getAsString();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);

        // Access the nested JSON array
        JsonArray petsArray = gsonObject.getAsJsonArray("pets");

        // Iterate over the array elements
        for (JsonElement petElement : petsArray) {
            JsonObject petObject = petElement.getAsJsonObject();
            String petName = petObject.get("name").getAsString();
            String petType = petObject.get("type").getAsString();

            System.out.println("Pet: " + petName + " (" + petType + ")");
        }
    }

}
