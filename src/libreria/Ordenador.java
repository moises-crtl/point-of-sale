package libreria;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author ioriyagamy
 */
public class Ordenador {

    public static String getSerilaNumber(char driver) throws Exception {
        String linea = null;
        String serial = null;
        Process process = Runtime.getRuntime().exec("cmd /c vol " + driver + ":");        
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        while ((linea = bufferedReader.readLine()) != null) {
            String[] strings = linea.split(" ");
            serial = strings[strings.length - 1];
        }
        bufferedReader.close();
        return serial;
    }

}
