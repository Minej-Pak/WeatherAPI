package si.academia.unit29;

import java.io.IOException;
import java.util.Date;

public interface IWeatherProvider {
    public TemperatureData[] getTemperatures(String Location, Date dtm) throws IOException, InterruptedException;
}
