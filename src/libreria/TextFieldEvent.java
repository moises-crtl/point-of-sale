package libreria;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author ioriyagamy
 */
public class TextFieldEvent {

    public void textKeyPress(KeyEvent evt) {
        char char1 = evt.getKeyChar();
        if ((char1 < 'a' || char1 > 'z') && (char1 < 'A' || char1 > 'Z')&& (char1 != (char) KeyEvent.VK_BACK_SPACE) && (char1 != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    public void numbreKeyPress(KeyEvent evt) {
        char char1 = evt.getKeyChar();
        if((char1 < '0' || char1 > '9') && (char1 != (char) KeyEvent.VK_BACK_SPACE)){
            evt.consume();
        }
    }
    
    public void numberDecimalKeyPress(KeyEvent evt, JTextField textField){
        char caracter = evt.getKeyChar();
        if((caracter < '0' || caracter > '9') && textField.getText().contains(".")
                && (caracter != (char) KeyEvent.VK_BACK_SPACE)){
            evt.consume();
        }else if((caracter < '0' || caracter > '9') && (caracter != '.')
                && (caracter != (char) KeyEvent.VK_BACK_SPACE)){
            evt.consume();
        }
    }
    
    public boolean isEmail(String correo){
        Pattern pattern = Pattern.compile("^[\\w\\-\\_\\+]+(\\.\\w\\-\\_\\+])*@([A-Za-z0-9]+\\.)+[A-Za-z]{2,4}$");
        Matcher matcher = pattern.matcher(correo);
        return matcher.find();
    }
}
