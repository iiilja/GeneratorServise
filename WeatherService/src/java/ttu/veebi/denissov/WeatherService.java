/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttu.veebi.denissov;

import com.sun.faces.util.CollectionsUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import namespace.webservice._new.SpecWeatherType;
import namespace.webservice._new.SpecifiedWeatherElementResponse;
import namespace.webservice._new.TempTypeEnumOut;
import namespace.webservice._new.WeatherElementList;
import namespace.webservice._new.WeatherElementList.*;
import namespace.webservice._new.WeatherTypes;

/**
 *
 * @author ilja
 */
@WebService(serviceName = "WeatherService", portName = "NewPort", endpointInterface = "namespace.webservice._new.WeatherServicePortType", targetNamespace = "http://new.webservice.namespace", wsdlLocation = "WEB-INF/wsdl/NewWebServiceFromWSDL/WeatherService.wsdl")
public class WeatherService {

    public SpecifiedWeatherElementResponse getSpecifiedWeather(WeatherTypes parameter) {
        SpecifiedWeatherElementResponse response = new SpecifiedWeatherElementResponse();
        List<SpecWeatherType> specWeatherTypes = response.getSpecifiedWeatherElement();
        
        Date from = parameter.getFrom().toGregorianCalendar().getTime();
        Date to = parameter.getTo().toGregorianCalendar().getTime();
        
        SpecWeatherType weatherType = new SpecWeatherType();
        for (WeatherElement weatherElement : getAllWeather().getWeatherElement()) {
            Date weatherElementDate = weatherElement.getDate().toGregorianCalendar().getTime();
            if (weatherElementDate.after(from) && weatherElementDate.before(to)) {
                weatherType = new SpecWeatherType();
                if (parameter.isClouds()) {
                    weatherType.setClouds(weatherElement.getClouds());
                }
                if (parameter.isWind()) {
                    weatherType.setWindSpeed(weatherElement.getWindSpeed());
                }
                if (parameter.isMoist()) {
                    weatherType.setMoist(weatherElement.getMoist());
                }
                specWeatherTypes.add(weatherType);
            }
        }
        return response;
    }

    public WeatherElementList getAllWeather() {
        
        Calendar cal  = Calendar.getInstance();
        cal.set(2014, 12, 12, 21, 37);
        WeatherElementList weather = new WeatherElementList();
        List <WeatherElementMine> elements = new ArrayList<>();
        
        
        WeatherElementMine element = new WeatherElementMine();
        element.setClouds("sunny");
        element.setDate(toGregorianDate(cal.getTime()));
        TempTypeEnumOut temp = new TempTypeEnumOut();
        temp.setCelsius(new BigDecimal(50));
        temp.setFahrenheit(new BigDecimal(120));
        element.setTemp(temp);
        element.setWindSpeed(new BigDecimal(7));
        elements.add(element);
        
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        element = new WeatherElementMine();
        element.setClouds("cloudy");
        element.setDate(toGregorianDate(cal.getTime()));
        temp = new TempTypeEnumOut();
        temp.setCelsius(new BigDecimal(45));
        temp.setFahrenheit(new BigDecimal(110));
        element.setTemp(temp);
        element.setWindSpeed(new BigDecimal(6.5));
        elements.add(element);
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        element = new WeatherElementMine();
        element.setClouds("rains");
        element.setDate(toGregorianDate(cal.getTime()));
        temp = new TempTypeEnumOut();
        temp.setCelsius(new BigDecimal(45));
        temp.setFahrenheit(new BigDecimal(110));
        element.setTemp(temp);
        element.setWindSpeed(new BigDecimal(6.5));
        elements.add(element);
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        element = new WeatherElementMine();
        element.setClouds("clouds from south");
        element.setDate(toGregorianDate(cal.getTime()));
        temp = new TempTypeEnumOut();
        temp.setCelsius(new BigDecimal(45));
        temp.setFahrenheit(new BigDecimal(110));
        element.setTemp(temp);
        element.setWindSpeed(new BigDecimal(6.5));
        elements.add(element);
        
        cal.add(Calendar.HOUR_OF_DAY, 1);
        element = new WeatherElementMine();
        element.setClouds("sun is shining bright");
        element.setDate(toGregorianDate(cal.getTime()));
        temp = new TempTypeEnumOut();
        temp.setCelsius(new BigDecimal(45));
        temp.setFahrenheit(new BigDecimal(110));
        element.setTemp(temp);
        element.setWindSpeed(new BigDecimal(6.5));
        elements.add(element);
        
        Collections.sort(elements);
        
        weather.getWeatherElement().addAll(elements);
        return weather;
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
