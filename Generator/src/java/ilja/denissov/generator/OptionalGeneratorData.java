/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ilja.denissov.generator;

/**
 *
 * @author ilja
 */
public class OptionalGeneratorData {
    private Boolean amps;
    private Boolean voltage;
    private Boolean rpm;

    public Boolean isAmps() {
        return amps;
    }

    public void setAmps(Boolean amps) {
        this.amps = amps;
    }

    public Boolean isRpm() {
        return rpm;
    }

    public void setRpm(Boolean rpm) {
        this.rpm = rpm;
    }

    public Boolean isVoltage() {
        return voltage;
    }

    public void setVoltage(Boolean voltage) {
        this.voltage = voltage;
    }
    
    
}
