package si.academia.unit29;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Condition{
        private String text;
        private String icon;
        private int code;

        public Condition() {
        }
        public Condition(String text, String icon, int code) {
            this.text = text;
            this.icon = icon;
            this.code = code;
        }

        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getCode() {
            return code;
        }
        public void setCode(int code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return "Condition{" +
                    "text='" + text + '\'' +
                    ", icon='" + icon + '\'' +
                    ", code=" + code +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class  DayData{
        @JsonProperty("maxtemp_c")
        private float maxTempC;
        @JsonProperty("mintemp_c")
        private float minTempC;
        Condition condition;

        public DayData() {
        }
        public DayData(float maxTempC, float minTempC, Condition condition) {
            this.maxTempC = maxTempC;
            this.minTempC = minTempC;
            this.condition = condition;
        }

        public float getMaxTempC() {
            return maxTempC;
        }
        public void setMaxTempC(float maxTempC) {
            this.maxTempC = maxTempC;
        }

        public float getMinTempC() {
            return minTempC;
        }
        public void setMinTempC(float minTempC) {
            this.minTempC = minTempC;
        }

        public Condition getCondition() {
            return condition;
        }
        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return "DayData{" +
                    "maxTempC=" + maxTempC +
                    ", minTempC=" + minTempC +
                    ", condition=" + condition +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class HourData{
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private Date time;
        private float temp_c;
        private Condition condition;
        private float wind_kmh;
        private String wind_dir;
        private float pressure_mb;
        private int humidity;

        public HourData() {
        }
        public HourData(Date time, float temp_c, Condition condition, float wind_kmh, String wind_dir, float pressure_mb, int humidity) {
            this.time = time;
            this.temp_c = temp_c;
            this.condition = condition;
            this.wind_kmh = wind_kmh;
            this.wind_dir = wind_dir;
            this.pressure_mb = pressure_mb;
            this.humidity = humidity;
        }

        public Date getTime() {
            return time;
        }
        public void setTime(Date time) {
            this.time = time;
        }

        public float getTemp_c() {
            return temp_c;
        }
        public void setTemp_c(float temp_c) {
            this.temp_c = temp_c;
        }

        public Condition getCondition() {
            return condition;
        }
        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public float getWind_kmh() {
            return wind_kmh;
        }
        public void setWind_kmh(float wind_kmh) {
            this.wind_kmh = wind_kmh;
        }

        public String getWind_dir() {
            return wind_dir;
        }
        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public float getPressure_mb() {
            return pressure_mb;
        }
        public void setPressure_mb(float pressure_mb) {
            this.pressure_mb = pressure_mb;
        }

        public int getHumidity() {
            return humidity;
        }
        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "HourData{" +
                    "time=" + time +
                    ", temp_c=" + temp_c +
                    ", condition=" + condition +
                    ", wind_kmh=" + wind_kmh +
                    ", wind_dir='" + wind_dir + '\'' +
                    ", pressure_mb=" + pressure_mb +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Astro{
        private String sunrise;
        private String sunset;

        public Astro() {
        }
        public Astro(String sunrise, String sunset) {
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public String getSunrise() {
            return sunrise;
        }
        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }
        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        @Override
        public String toString() {
            return "Astro{" +
                    "sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class ForecastDay{
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date date;
        private DayData day;
        private Astro astro;
        private HourData[] hour;

        public ForecastDay() {
        }
        public ForecastDay(Date date, DayData day, Astro astro, HourData[] hour) {
            this.date = date;
            this.day = day;
            this.astro = astro;
            this.hour = hour;
        }

        public Date getDate() {
            return date;
        }
        public void setDate(Date date) {
            this.date = date;
        }

        public DayData getDay() {
            return day;
        }
        public void setDay(DayData day) {
            this.day = day;
        }

        public Astro getAstro() {
            return astro;
        }
        public void setAstro(Astro astro) {
            this.astro = astro;
        }

        public HourData[] getHour() {
            return hour;
        }
        public void setHour(HourData[] hour) {
            this.hour = hour;
        }

        @Override
        public String toString() {
            return "ForecastDay{" +
                    "date=" + date +
                    ", day=" + day +
                    ", astro=" + astro +
                    ", hour=" + Arrays.toString(hour) +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Forecast{
        private ForecastDay[] forecastday;

        public Forecast() {
        }
        public Forecast(ForecastDay[] forecastday) {
            this.forecastday = forecastday;
        }

        public ForecastDay[] getForecastday() {
            return forecastday;
        }
        public void setForecastday(ForecastDay[] forecastday) {
            this.forecastday = forecastday;
        }

        @Override
        public String toString() {
            return "Forecast{" +
                    "forecastday=" + Arrays.toString(forecastday) +
                    '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Location{
        private String name;

        public Location() {
        }
        public Location(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private Location location;
    private Forecast forecast;

    public WeatherData(){}
    public WeatherData(Location location, Forecast forecast) {
        this.location = location;
        this.forecast = forecast;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "location=" + location +
                ", forecast=" + forecast +
                '}';
    }
}