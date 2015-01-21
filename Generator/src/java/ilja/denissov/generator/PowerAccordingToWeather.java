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
    private float power;
    private BigDecimal celsius;
    private BigDecimal wind;
    private Date dateBegin;
    private Date dateEnd;
    private BigDecimal amps;
    private BigDecimal voltage;
    private Integer rpm;

    public PowerAccordingToWeather(Date begin, Date end) {
        this.dateBegin = begin;
        this.dateEnd = end;
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

    public BigDecimal getAmps() {
        return amps;
    }

    public void setAmps(BigDecimal amps) {
        this.amps = amps;
    }

    public void addAmps(float amps) {
        if (this.amps == null) {
            this.amps = new BigDecimal(0);
        }
        this.amps = (this.amps.add(BigDecimal.valueOf(amps))).divide(new BigDecimal(2));
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

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    

    public void addRpm(int rpm) {
        if (this.rpm == null) {
            this.rpm = new Integer(0);
        }
        this.rpm = (int)(this.rpm+ rpm)/2;
    }

    public void addVoltage(float voltage) {
        if (this.voltage == null) {
            this.voltage = new BigDecimal(0);
        }
        this.voltage = (this.voltage.add(BigDecimal.valueOf(voltage))).divide(new BigDecimal(2));
    }
    
    public void addPower(int power){
        this.power = (this.power + power)/2;
    }

    public BigDecimal getVoltage() {
        return voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }
    
    

}
