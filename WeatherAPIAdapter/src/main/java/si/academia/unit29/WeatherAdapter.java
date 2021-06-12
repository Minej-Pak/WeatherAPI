package si.academia.unit29;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class WeatherAdapter implements IWeatherProvider{
    private static final Logger log= LogManager.getLogger(WeatherAdapter.class);
    private IWeatherApiProxy proxy;

    public WeatherAdapter(IWeatherApiProxy proxy){this.proxy = proxy;}

    @Override
    public TemperatureData[] getTemperatures(String location, Date dtm) throws IOException, InterruptedException {
        log.trace("getTemperatures(Location='" + location + "',dtm=" + dtm + ")");
        WeatherData wdata = proxy.getHistoricalData(location, dtm);
        log.debug("Retrived wdata=" + wdata);
        int numDataPoints = 0;
        for (WeatherData.ForecastDay dd:wdata.getForecast().getForecastday()) {
            numDataPoints += dd.getHour().length;
        }
        TemperatureData[] tempData = new TemperatureData[numDataPoints];
        int idx = 0;
        for (WeatherData.ForecastDay dd:wdata.getForecast().getForecastday()) {
            for (WeatherData.HourData hd:dd.getHour()) {
                tempData[idx++] = new TemperatureData(hd.getTime(), hd.getTemp_c());
            }
            numDataPoints += dd.getHour().length;
        }
        log.trace("Returning tempData" + Arrays.toString(tempData));
        return tempData;
    }
}
