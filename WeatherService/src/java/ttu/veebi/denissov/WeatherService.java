/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttu.veebi.denissov;

import javax.jws.WebService;

/**
 *
 * @author ilja
 */
@WebService(serviceName = "WeatherService", portName = "NewPort", endpointInterface = "namespace.webservice._new.WeatherServicePortType", targetNamespace = "http://new.webservice.namespace", wsdlLocation = "WEB-INF/wsdl/WeatherService/WeatherService.wsdl")
public class WeatherService {

    public namespace.webservice._new.SpecifiedWeatherElementResponse getSpecifiedWeather(namespace.webservice._new.WeatherTypes parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public namespace.webservice._new.WeatherElement getAllWeather() {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
