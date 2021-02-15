import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class Lesson06 {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String TYPE = "forecasts";
    private static final String API_VER = "v1";
    private static final String FREQUENCY = "daily";
    private static final String PERIOD = "5day";
    private static final String SPB_KEY = "295212";
    private static final String API_KEY = "Po7NdMTmTYOa65S04wSQeb0y6wCAtKug";
    private static final String S = "/";

    public static void main(String[] args) throws IOException {

        getForecast();

    }


    private static HttpUrl getUrl() {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("HTTP")
                .host(BASE_HOST)
                .addPathSegments(TYPE + S + API_VER + S + FREQUENCY + S + PERIOD + S + SPB_KEY)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();
        return url;
    }

    private static Request getRequest(HttpUrl url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    private static void getForecast() throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = getRequest(getUrl());
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
