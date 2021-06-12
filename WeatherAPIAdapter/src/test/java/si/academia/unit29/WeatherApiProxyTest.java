package si.academia.unit29;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.*;

public class WeatherApiProxyTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getHistoricalData() throws IOException, InterruptedException{
        Date xDaysAgo = Date.from(Instant.now().minus(Duration.ofDays(5)));
        WeatherApiProxy proxy=new WeatherApiProxy("http://api.weatherapi.com/v1/", "798bbc560c574b24bf984609211206");
        WeatherData wdata = proxy.getHistoricalData("Maribor", xDaysAgo);
        assertNotNull(wdata);
    }

}