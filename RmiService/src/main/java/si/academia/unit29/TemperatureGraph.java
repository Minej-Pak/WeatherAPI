package si.academia.unit29;

import java.util.Date;

public class TemperatureGraph {

    private String location;
    private Date date;
    private int width;
    private int height;
    private String image;

    public TemperatureGraph() {

    }

    public TemperatureGraph(String location, Date date, int width, int height, String image) {
        this.date = date;
        this.height = height;
        this.width = width;
        this.image = image;
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "TemperatureGraph{" +
                "location='" + location + '\'' +
                ", date" + date +
                ", width" + width +
                ", height" + height +
                ", image.length='" + image.length() + '\'' +
                "}";
    }
}
