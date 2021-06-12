package si.academia.unit29;

import java.io.IOException;
import java.rmi.Remote;
import java.util.Date;

public interface IWeatherService extends Remote {
    public TemperatureData[] getTemperatures(String Location, Date dtm) throws IOException, InterruptedException;
}
