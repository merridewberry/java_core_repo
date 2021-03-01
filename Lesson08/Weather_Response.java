import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather_Response {

    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<Double> temperature = new ArrayList<Double>();
    private ArrayList<String> weatherText = new ArrayList<String>();

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<Double> getTemperature() {
        return temperature;
    }

    public void setTemperature(ArrayList<Double> temperature) {
        this.temperature = temperature;
    }

    public ArrayList<String> getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(ArrayList<String> weatherText) {
        this.weatherText = weatherText;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("DailyForecasts")
    private void unpack(Map<String, Object>[] forecasts) {
        for (int i = 0; i < forecasts.length; i++) {
            Map<String, Object> forecast = forecasts[i];
            this.date.add((String)forecast.get("Date"));
            Map<String, Object> temperature = (Map<String, Object>)forecast.get("Temperature");
            Map<String, Object> minimum = (Map<String, Object>)temperature.get("Minimum");
            this.temperature.add((Double)minimum.get("Value"));
            Map<String, Object> day = (Map<String, Object>)forecast.get("Day");
            this.weatherText.add((String)day.get("IconPhrase"));
        }
    }
}