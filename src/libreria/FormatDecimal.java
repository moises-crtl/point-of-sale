package libreria;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author ioriyagamy
 */
public class FormatDecimal {
    
    Number number;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");   
    
    public String decimal(double formatDecimal){
        return  decimalFormat.format(formatDecimal);
    }
    
    public double rebuild(String formatDecimal){
        try {
            number = decimalFormat.parse(formatDecimal.replace(" ", ""));
        } catch (ParseException ex) {
            System.err.println("Error " + ex);
        }
        return number.doubleValue();
    }
}
