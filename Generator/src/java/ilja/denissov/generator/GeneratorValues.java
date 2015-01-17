package ilja.denissov.generator;


import java.util.Date;
import javax.jws.WebParam;

/**
 *
 * @author ilja.denissov
 */
public class GeneratorValues {
    private float amps;
    private float voltage;
    private int rpm;
    private Date date;

    public GeneratorValues(float amps, float voltage, int rpm, Date date) {
        this.amps = amps;
        this.voltage = voltage;
        this.rpm = rpm;
        this.date = date;
    }

    public GeneratorValues() {
    }

    public float getAmps() {
        return amps;
    }

    public void setAmps(float amps) {
        this.amps = amps;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }
}
