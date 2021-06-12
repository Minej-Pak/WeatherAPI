package si.academia.unit29;

import java.util.Date;

public class TemperatureData {
    private Date time;
    private float temp;

    public TemperatureData(Date time, float temp) {
        this.time = time;
        this.temp = temp;
    }

    public float getValue() {
        return this.temp;
    }
}
