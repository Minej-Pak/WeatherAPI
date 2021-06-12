package si.academia.unit29;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Singleton;
import javax.imageio.ImageIO;
import javax.ws.rs.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Path("/weather")
@Singleton
public class WeatherResource {
    private static final Logger log= LogManager.getLogger(WeatherResource.class);

    @Path("/{dtm}")
    @GET
    @Produces("application/json")
    public TemperatureGraph getTemperatureGraph(@PathParam("dtm") String dtmStr, @QueryParam("location") String location,
                                                @QueryParam("width") int width, @QueryParam("height") int height) {
        log.trace("getTemperatureGraph(dtmStr='" + dtmStr + "',location='" + location + "',width='" + width + "',height='" + height + "')");
        Date dtm = null;
        try {
            dtm = new SimpleDateFormat("dd.MM.yyyy").parse(dtmStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        log.debug("Retrieving temperature data");
        IWeatherApiProxy proxy = new WeatherApiProxy("http://api.weatherapi.com/v1/", "798bbc560c574b24bf984609211206");
        IWeatherProvider provider = new WeatherAdapter(proxy);
        WeatherService svc = new WeatherService(provider);
        TemperatureData[] temps = null;
        try {
            temps = svc.getTemperatures(location, dtm);
        } catch (IOException | InterruptedException e) {
            log.error("Failed in retrieving temperature data", e);
        }
        log.debug("Temperature retrieved");
        log.debug("Rendering temperature graph");
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width, height);

        int xres = width;
        int yres = height;
        g2.setColor(Color.BLUE);
        int xstart = 30;
        int ystart = yres - 30;
        int xend = xres - 20;
        int yend = 20;
        int xsize = xend - xstart;
        int ysize = ystart - yend;
        int dx100 = (100 * xsize) / 24;
        int dy100 = (100 * ysize) / 60;

        g2.drawLine(xstart, ystart, xstart, yend);
        g2.drawLine(xstart, ystart, xend, ystart);
        for (int i = 0; i < 24; i++) {
            int x = xstart + (dx100 * i) / 100 + dx100 / 200;
            g2.drawLine(x, ystart, x, ystart + 4);
            String hrStr = Integer.toString(i + 1);
            Rectangle2D rect = g2.getFontMetrics().getStringBounds(hrStr, g2);
            g2.drawString(hrStr, (float) (x - rect.getCenterX()), (float) (ystart + 4 + rect.getHeight()));
        }
        for (int i = 4; i <= 60; i += 4) {
            int y = ystart - (dy100 * i) / 100;
            g2.drawLine(xstart - 4, y, xstart, y);
            String tmpStr = Integer.toString(-20 + i);
            Rectangle2D rect = g2.getFontMetrics().getStringBounds(tmpStr, g2);
            g2.drawString(tmpStr, (float) (24 - rect.getWidth()), (float) (y - rect.getCenterY()));
        }
        if (temps != null) {
            int yzero = ystart - (dy100 * 20) / 100;
            g2.setColor(Color.RED);
            g2.drawLine(xstart + 1, yzero, xend, yzero);
            for (int i = 0; i < temps.length && i < 24; i++) {
                float temp = temps[i].getValue();
                if (temp > 40.0f) {
                    temp = 40.0f;
                }
                if (temp < -20.0f) {
                    temp = -20.0f;
                }
                int x = xstart + (dx100 * i) / 100;
                if (temp > 0) {
                    int cheight = (int) ((dy100 * temp) / 100 - 1);
                    g2.setColor(Color.YELLOW);
                    g2.fillRect(x + 5, yzero - cheight - 1, dx100 / 100 - 10, cheight);
                } else if (temp < 0) {
                    int cheight = (int) ((dy100 * (-temp)) / 100);
                    g2.setColor(Color.GREEN);
                    g2.fillRect(x + 5, yzero + 1, dx100 / 100 - 10, cheight);
                }
            }
        }
        log.debug("Temperature graph rendered");
        ByteArrayOutputStream nonStrm = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, "png", nonStrm);
        } catch (IOException e) {
            log.error("Failed to write image to memory buffer", e);
        }
        String imgBytes = Base64.getEncoder().encodeToString(nonStrm.toByteArray());
        TemperatureGraph tgraph = new TemperatureGraph(location, dtm, width, height, imgBytes);
        log.trace("Returning temperature graph" + tgraph);
        return tgraph;
    }
}