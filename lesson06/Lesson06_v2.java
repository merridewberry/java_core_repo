import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Lesson06_v2 {



    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String LOCATIONS = "locations";
    private static final String API_VER = "v1";
    private static final String FREQUENCY = "daily";
    private static final String PERIOD = "5day";
    private static final String LOCATION_TYPE = "cities";
    private static final String SEARCH = "search";
    private static final String API_KEY = " IvJgV8jKhYz80dZv9DlO2A6WsDQS6GeG ";
    private static final String S = "/";

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название города: ");
        String search_text = scanner.nextLine();

       getForecast(getLocationKey(search_text).toString());
    }


    private static HttpUrl getLocationUrl(String search_text) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("HTTP")
                .host(BASE_HOST)
                .addPathSegments(LOCATIONS + S + API_VER + S + LOCATION_TYPE + S + SEARCH)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", search_text)
                .addQueryParameter("language", "ru-ru")
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

    private static Object getLocationKey(String search_text) throws IOException {
        String response = getLocationJson(search_text);
        Object responseObj = JSONValue.parse(response);
        JSONArray responseJson = (JSONArray) responseObj;
        Object key = new Object();
        for (int i = 0; i < responseJson.size(); i++) {
            JSONObject obj = (JSONObject) responseJson.get(i);
            if (((JSONObject) responseJson.get(i)).keySet().contains("Key")) {
                key = obj.get("Key");
                break;
            }
        }
        return key;
    }

    private static String getLocationJson(String search_text) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = getLocationRequest(getLocationUrl(search_text));
        String response = client.newCall(request).execute().body().string();
        return response;
    }

    private static HttpUrl getForecastUrl(String city_key) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("HTTP")
                .host(BASE_HOST)
                .addPathSegments(FORECASTS + S + API_VER + S + FREQUENCY + S + PERIOD + S + city_key)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
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

    private static void getForecast(String city_key) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = getForecastRequest(getForecastUrl(city_key));
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}