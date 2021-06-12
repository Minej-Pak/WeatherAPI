package si.academia.unit29;

import java.io.IOException;
import java.util.Date;

public interface IWeatherApiProxy {
    public WeatherData getHistoricalData(String location, Date dtm) throws IOException, InterruptedException;
}
