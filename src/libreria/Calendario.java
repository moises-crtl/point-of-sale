package libreria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author ioriyagamy
 */
public class Calendario {
    
    private DateFormat dateFormat;
    private Date date = new Date();
    private Calendar calendar = new GregorianCalendar();
    private final String fecha;
    private final String dia;
    private final String mes;
    private final String year;
    private final String hora;
    private String am_pm;

    public Calendario() {
        
        switch (calendar.get(Calendar.AM_PM)){
            case 0:
                am_pm = "am";
                break;
            case 1:
                am_pm ="pm";
                break;
        }
        
        dateFormat = new SimpleDateFormat("dd");
        dia = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("MM");
        mes = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("yy");
        year = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fecha = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("hh:mm:ss");
        hora = dateFormat.format(date) + " " + am_pm;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getYear() {
        return year;
    }

    public String getHora() {
        return hora;
    }
    
    public String addMes(int mes){
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, mes);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(calendar.getTime());
    }
    
    public String addDay(Date date, int day){
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK, day);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(calendar.getTime());
    }
    
    public String getFecha(Calendar date){
        var dia = date.get(Calendar.DAY_OF_MONTH);
        var mes = 1 + date.get(Calendar.MONTH);
        var year = date.get(Calendar.YEAR);
        var value = String.valueOf(year + "/" + mes + "/" + dia);
        return value;
    }
}
