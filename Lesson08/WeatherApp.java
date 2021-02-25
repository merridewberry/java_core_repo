import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.Scanner;

public class WeatherApp {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String LOCATIONS = "locations";
    private static final String API_VER = "v1";
    private static final String FREQUENCY = "daily";
    private static final String PERIOD = "5day";
    private static final String LOCATION_TYPE = "cities";
    private static final String SEARCH = "search";
    private static final String API_KEY = "izi0gudpr3zqSO82is08eVJ3J1KxCA4p";
    public static Location location = new Location();
    public static Weather_Response weather_response = new Weather_Response();

    public static void getTheWeatherAlready() throws IOException {

        menu();
        getLocationInfo();
        printForecast();
        repeat();

    }

    private static void repeat() throws IOException{
        System.out.println("Would you like to get another forecast? y/n");
        Scanner scanner = new Scanner(System.in);
        String response = (String)scanner.nextLine();
        if (response.equals("y")) {
            getTheWeatherAlready();
        } else if (response.equals("n")) {
            System.out.println("It was nice knowing you, soft human person.");
            WeatherDB.close();
            System.exit(0);
        } else {
            System.out.println("You are doing something wrong.");
            repeat();
        }
    }

    private static void menu(){
        System.out.println("Would you like to get weather forecast for the next 5 days? y/n");
        Scanner scanner = new Scanner(System.in);
        String response = (String)scanner.nextLine();
        if (response.equals("y")) {
            System.out.println("Okay, here you go...");
        } else if (response.equals("n")) {
            System.out.println("lol ok bye");
            WeatherDB.close();
            System.exit(0);
        } else {
            System.out.println("You are doing something wrong.");
            menu();
        }
    }

    private static void printForecast()throws IOException{
        String json = getForecast(location.getKey());
        ObjectMapper objectMapper = new ObjectMapper();
        weather_response = objectMapper
                .readerFor(Weather_Response.class)
                .readValue(json);
        System.out.printf("Weather forecast for the next 5 days in %s, %s is following:%n%n", location.getCity(),
                location.getCountry());
        for (int i = 0; i < weather_response.getDate().size(); i++) {
            System.out.printf("%s: %nExpected weather: %s, expected temperature: %.1f %n%n",
                    StringUtils.left(weather_response.getDate().get(i), 10),
                    weather_response.getWeatherText().get(i), weather_response.getTemperature().get(i));
        }
        WeatherDB.write(location, weather_response);
        printDB();
    }

    private static void printDB(){
        System.out.println("Would you like to print the weather data from the database? y/n");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if (response.equals("y")) {
            WeatherDB.read();
        }
        if (!response.equals("y") && !response.equals("n")){
            System.out.println("You are doing something wrong.");
            printDB();
        }
    }


    private static HttpUrl getLocationUrl(String query) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("HTTP")
                .host(BASE_HOST)
                .addPathSegments(LOCATIONS)
                .addPathSegments(API_VER)
                .addPathSegments(LOCATION_TYPE)
                .addPathSegments(SEARCH)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", query)
                .addQueryParameter("language", "en-us")
                .build();
        return url;
    }

    private static Request getLocationRequest(HttpUrl url) {
        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();
        return request;
    }

    private static String askLocation() {
        System.out.println("Please enter city name:");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();
        return query;
    }

    private static void getLocationInfo() throws IOException {
        String response = getLocationJson(askLocation());
        ObjectMapper objectMapper = new ObjectMapper();
        while (objectMapper.readTree(response).size() == 0) {
            System.out.println("Nothing found. Try different search query.");
            response = getLocationJson(askLocation());
        }
        JsonNode node = objectMapper
                .readTree(response)
                .get(0);
        location.setKey(node.at("/Key").asText());
        location.setCity(node.at("/EnglishName").asText());
        location.setCountry(node.at("/Country/EnglishName").asText());
    }

    private static String getLocationJson(String query) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = getLocationRequest(getLocationUrl(query));
        String  response = client.newCall(request).execute().body().string();
        return response;
    }

    private static HttpUrl getForecastUrl(String city_key) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("HTTP")
                .host(BASE_HOST)
                .addPathSegments(FORECASTS)
                .addPathSegments(API_VER)
                .addPathSegments(FREQUENCY)
                .addPathSegments(PERIOD)
                .addPathSegments(city_key)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "en-us")
                .addQueryParameter("metric", "true")
                .build();
        return url;
    }

    private static Request getForecastRequest(HttpUrl url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    private static String getForecast(String city_key) throws IOException{
        OkHttpClient client = new OkHttpClient();
        HttpUrl url = getForecastUrl(city_key);
        Request request = getForecastRequest(url);
        String response = client.newCall(request).execute().body().string();
        return response;
    }

}
