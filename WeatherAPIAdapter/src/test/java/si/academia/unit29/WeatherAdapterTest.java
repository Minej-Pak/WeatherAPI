package si.academia.unit29;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WeatherAdapterTest {

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{

    }

    @Test
    public void testGetTemperatures() throws IOException, InterruptedException{
        Date xDaysAgo = Date.from( Instant.now().minus(Duration.ofDays(5)));
        WeatherApiProxy proxy = new WeatherApiProxy("http://api.weatherapi.com/v1/", "798bbc560c574b24bf984609211206");;
        WeatherAdapter adapter = new WeatherAdapter(proxy);
        TemperatureData[] temps=adapter.getTemperatures("Maribor", xDaysAgo);
        System.out.println("Temps=" + Arrays.toString(temps));
        assertEquals(24,temps.length);
    }
}
