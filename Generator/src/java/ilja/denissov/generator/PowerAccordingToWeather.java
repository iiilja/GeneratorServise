/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ilja.denissov.generator;

import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
/**
 *
 * @author ilja
 */
public class PowerAccordingToWeather {
    @XmlElement(required = true)
    @XmlSchemaType(name = "powerPo")
    private float power;
    private BigDecimal celsius;
    private BigDecimal wind;
    private Date dateBegin;
    private Date dateEnd;
    private Float amps;
    private Float voltage;
    private Integer rpm;

    public PowerAccordingToWeather(Date begin, Date end) {
        this.dateBegin = begin;
        this.dateEnd = end;
    }
    
    

    public void addValues(float power, float amps, float voltage, int rpm, Date dateBegin, Date dateEnd) {
        this.power = (this.power + power)/2;
        this.amps = (this.amps + amps)/2;
        this.voltage = (this.voltage + voltage)/2;
        this.rpm = (this.rpm + rpm)/2;
    }

    
    
    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public BigDecimal getWind() {
        return wind;
    }

    public void setWind(BigDecimal wind) {
        this.wind = wind;
    }

    public BigDecimal getCelsius() {
        return celsius;
    }

    public void setCelsius(BigDecimal celsius) {
        this.celsius = celsius;
    }
    

    public float getAmps() {
        return amps;
    }

    public void setAmps(float amps) {
        this.amps = amps;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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
