package si.academia.unit29;

import org.apache.logging.log4j.LogManager;
import  org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class WeatherService implements IWeatherService{

    private static final Logger log= LogManager.getLogger(WeatherService.class);
    private IWeatherProvider provider;

    public WeatherService(IWeatherProvider provider) {this.provider = provider;}

    @Override
    public TemperatureData[] getTemperatures(String Location, Date dtm) throws IOException, InterruptedException{
        log.trace("getTemperatures(Location='"+Location+"',dtm="+dtm+")");
        TemperatureData[] temps=provider.getTemperatures(Location,dtm);
        log.trace("Returning temps="+ Arrays.toString(temps));
        return temps;
    }
}
