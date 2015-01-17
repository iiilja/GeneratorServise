/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ilja.denissov.generator;

import java.util.Date;
/**
 *
 * @author ilja
 */
public class PowerAccordingToWeather {
    private float power;
    private float celsius;
    private float wind;
    private Date date;
    private Float amps;
    private Float voltage;
    private Integer rpm;

    public void addValues(float power, float celsius, float wind, float amps, float voltage, int rpm, Date date) {
        this.power = (this.power + power)/2;
        this.celsius = (this.celsius + celsius)/2;
        this.wind = (this.wind + wind)/2;
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

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public Float getCelsius() {
        return celsius;
    }

    public void setCelsius(float celsius) {
        this.celsius = celsius;
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
