/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ttu.denissov;

import java.util.Date;
import namespace.webservice._new.WeatherElementResponce.WeatherElement;

/**
 *
 * @author ilja
 */
public class WeatherElementMine extends WeatherElement implements Comparable<WeatherElement>{

    @Override
    public int compareTo(WeatherElement o) {
        Date thisDate = this.getDate().toGregorianCalendar().getTime();
        Date otherDate = o.getDate().toGregorianCalendar().getTime();
        if (thisDate.after(otherDate)) {
            return 1;
        }
        return -1;
    }
    
    
    
}
