/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ilja.denissov.generator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author ilja.denissov
 */
// , wsdlLocation = "WEB-INF/wsdl/GeneratorValuesRegister/GeneratorValuesRegisterService.wsdl"
@WebService(targetNamespace = "http://my.org/ns/"  , wsdlLocation = "WEB-INF/wsdl/GeneratorValuesRegister/GeneratorValuesRegisterService.wsdl")
public class GeneratorValuesRegister {
    private final String DAY = "DAY";
    private final String HOUR = "HOUR";
    
    @WebMethod(operationName = "GetAvgPowerAccordingToWeatherAndType")
    public @WebResult(name = "PowerAccordingToWeather") List<PowerAccordingToWeather> getPowerAccordingToWeather(
            @WebParam(name = "TimeType") String type,
            @WebParam(name = "OptionalGeneratorData") OptionalGeneratorData optionalGeneratorData){
        Calendar cal = Calendar.getInstance();
        List<PowerAccordingToWeather> outList = new ArrayList<>();
        if (type.equals(DAY)) {

        }
        else if (type.equals(HOUR)) {
            
        }

        return null;
    }
    
    private List<PowerAccordingToWeather> sortValuesForType(int calendarType){
        Calendar cal = Calendar.getInstance();
        List<PowerAccordingToWeather> outList = new ArrayList<>();
        for (GeneratorValues value : getAllValues()) {
                PowerAccordingToWeather patw;
                
                cal.setTime(value.getDate());
                cal.set(calendarType == Calendar.DAY_OF_YEAR ? Calendar.HOUR_OF_DAY : Calendar.MINUTE , 0);
                Date begin = cal.getTime();
                cal.add(calendarType, 1);
                Date end = cal.getTime();
                patw = findPowerAccordingToWeather(outList, begin, end);
                if (patw == null) {
                    patw = new PowerAccordingToWeather();
                    end = value.getDate();
                    if  (Calendar.DAY_OF_YEAR == calendarType) {
                        cal.setTime(end);
                        cal.set(Calendar.HOUR_OF_DAY, 1);
                        end = cal.getTime();
                    }
                    else if  (Calendar.HOUR_OF_DAY == calendarType) {
                        cal.setTime(end);
                        cal.set(Calendar.MINUTE, 1);
                        end = cal.getTime();
                    }
                }
                patw.addValues(value.getAmps()*value.getVoltage(),
                        celsius, wind, value.getAmps(), value.getVoltage(), value.getRpm(), end);
            }
        return outList;
    }
    
    private PowerAccordingToWeather findPowerAccordingToWeather(List<PowerAccordingToWeather> list, Date begin, Date end){
        for (PowerAccordingToWeather powerAccordingToWeather : list) {
            Date date = powerAccordingToWeather.getDate();
            if (date.before(end) && date.after(begin)) {
                return powerAccordingToWeather;
            }
        }
        return null;
    }
    
    @WebMethod(operationName = "GetAllGeneratorValues")
    public @WebResult(name = "GeneratorValue") List<GeneratorValues> getAllValues(){
        
        Calendar cal = Calendar.getInstance();
        cal.set(2014, 12, 12, 20, 37);
        List<GeneratorValues> list = new ArrayList<>();
        list.add(new GeneratorValues(0.5F, 0.6F, 200, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add(new GeneratorValues(5.4F, 22.0F, 1200, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add(new GeneratorValues(10.6F, 20.4F, 750, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add(new GeneratorValues(15.5F, 26.1F, 1475, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add( new GeneratorValues(1.5F, 5.3F, 469, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add( new GeneratorValues(7.9F, 11.6F, 681, null));
        
        cal.add(Calendar.DAY_OF_YEAR, 1);
        list.add(new GeneratorValues(0.5F, 0.6F, 200, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add(new GeneratorValues(5.4F, 22.0F, 1200, cal.getTime()));
        
        cal.add(Calendar.DAY_OF_YEAR, 1);
        list.add(new GeneratorValues(10.6F, 20.4F, 750, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add(new GeneratorValues(15.5F, 26.1F, 1475, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add( new GeneratorValues(1.5F, 5.3F, 469, cal.getTime()));
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        list.add( new GeneratorValues(7.9F, 11.6F, 681, cal.getTime()));

        return list;
    }
}
