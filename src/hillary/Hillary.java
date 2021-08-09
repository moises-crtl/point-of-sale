/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillary;

import java.awt.Frame;
import java.util.List;
import models.usuario.TUsuarios;
import viewModels.LoginViewModel;
import views.Login;
import hillary.Main;

/**
 *
 * @author ioriyagamy
 */
public class Hillary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        new Main().setVisible(true);
        LoginViewModel login = new LoginViewModel();
        Object[] objects = login.Verificar();
        var listUsuario = (List<TUsuarios>) objects[0];
        if (!listUsuario.isEmpty()) {
            Main sisten = new Main(listUsuario.get(0));
            sisten.setVisible(true);
            sisten.setExtendedState(Frame.MAXIMIZED_BOTH);
        } else {
            Login sistema = new Login();
//        sistema.setExtendedState(Frame.MAXIMIZED_BOTH);
            sistema.setVisible(true);
        }

    }

}
