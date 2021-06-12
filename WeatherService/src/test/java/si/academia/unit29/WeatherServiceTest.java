package si.academia.unit29;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.Assert.*;

public class WeatherServiceTest {

    @org.junit.Before
    public void setup() throws Exception{

    }

    @org.junit.After
    public void tearDown() throws Exception{

    }

    @org.junit.Test
    public void testGetTemperatures() throws IOException, InterruptedException{
        IWeatherProvider provider=new IWeatherProvider(){
            @Override
            public TemperatureData[] getTemperatures(String location, Date dtm) throws IOException, InterruptedException{
                return new TemperatureData[]{new TemperatureData(dtm, 1.5f)};
            }
        };

        WeatherService svc=new WeatherService(provider);
        Date xDaysAgo = Date.from(Instant.now().minus(Duration.ofDays(5)));
        TemperatureData[] temps=svc.getTemperatures("Maribor",xDaysAgo);
        assertEquals(1,temps.length);
    }
}