/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ilja.denissov.generator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import namespace.webservice._new.SpecWeatherType;
import namespace.webservice._new.SpecifiedWeatherElementResponse;
import namespace.webservice._new.TempTypeEnum;
import namespace.webservice._new.WeatherService;
import namespace.webservice._new.WeatherServicePortType;
import namespace.webservice._new.WeatherTypes;

/**
 *
 * @author ilja.denissov
 */
// , wsdlLocation = "WEB-INF/wsdl/GeneratorValuesRegister/GeneratorValuesRegisterService.wsdl"
@WebService(targetNamespace = "http://my.org/ns/"  , wsdlLocation = "WEB-INF/wsdl/GeneratorValuesRegister/GeneratorValuesRegisterService.wsdl")
public class GeneratorValuesRegister {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ilja-pc_8080/WeatherService/WeatherService.wsdl")
    private WeatherService service;
    
    @WebMethod(operationName = "GetAvgPowerAccordingToWeatherAndType")
    public @WebResult(name = "PowerAccordingToWeather") List<PowerAccordingToWeather> getPowerAccordingToWeather(
            @WebParam(name = "TimeType") TimeCalendarType type,
            @WebParam(name = "OptionalGeneratorData") OptionalGeneratorData optionalGeneratorData){
        if (type.equals(TimeCalendarType.DAY)) {
            return sortValuesForType(Calendar.DAY_OF_YEAR);
        }
        else if (type.equals(TimeCalendarType.HOUR)) {
            return sortValuesForType(Calendar.HOUR_OF_DAY);
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
                patw = new PowerAccordingToWeather(begin,end);

            }
            patw.addValues(value.getAmps()*value.getVoltage(),
                    value.getAmps(), value.getVoltage(), value.getRpm(),begin, end);
        }
        List<PowerAccordingToWeather> outListNew = new ArrayList<>();
        for (PowerAccordingToWeather pAtW : outList) {
            WeatherTypes types = new WeatherTypes();
            types.setFrom(toGregorianDate(pAtW.getDateBegin()));
            types.setTo(toGregorianDate(pAtW.getDateEnd()));
            types.setTemp(TempTypeEnum.CELSIUS);
            types.setWind(Boolean.TRUE);
            SpecifiedWeatherElementResponse response = getSpecifiedWeather(types);
            List<SpecWeatherType> weatherTypes = response.getSpecifiedWeatherElement();
            if (weatherTypes != null && !weatherTypes.isEmpty()) {
                SpecWeatherType specWeatherType = weatherTypes.get(0);
                pAtW.setCelsius(specWeatherType.getTemp().getCelsius());
                pAtW.setWind(specWeatherType.getWindSpeed());
                outListNew.add(pAtW);
            }
        }
        return outListNew;
    }
    
    private PowerAccordingToWeather findPowerAccordingToWeather(List<PowerAccordingToWeather> list, Date begin, Date end){
        for (PowerAccordingToWeather powerAccordingToWeather : list) {
            Date date = powerAccordingToWeather.getDateBegin();
            if (date.before(end) && date.after(begin) || date.equals(end) || date.equals(begin)) {
                return powerAccordingToWeather;
            }
        }
        return null;
    }
    
    @WebMethod(operationName = "GetAllGeneratorValues")
    public @WebResult(name = "GeneratorValues") List<GeneratorValues> getAllValues(){
        
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

    private SpecifiedWeatherElementResponse getSpecifiedWeather(WeatherTypes parameter) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        WeatherServicePortType port = service.getNewPort();
        return port.getSpecifiedWeather(parameter);
    }
    
        public static XMLGregorianCalendar toGregorianDate(Date date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar date2 = null;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(WeatherService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date2;
    }
}
