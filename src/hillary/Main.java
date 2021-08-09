/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import menu.MenuItem;
import models.usuario.TUsuarios;
import viewModels.ClientViewModel;
import viewModels.LoginViewModel;
import viewModels.UsuarioViewModel;
import views.Cliente;
import views.Moneda;
import views.OtraPlantilla;
import views.Usuario;

/**
 *
 * @author ioriyagamy
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    boolean a = true;
    private final LoginViewModel loginViewModel;
    private final UsuarioViewModel usuario;
    private static TUsuarios dataUsuario;
    private final ClientViewModel cliente;

    public Main(TUsuarios dataUsuario) {
        initComponents();
        this.dataUsuario = dataUsuario;
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        execute();
        loginViewModel = new LoginViewModel();
        Object[] perfil = {
            lbUsuarioPerfil,
            lbNombrePerfil,};
        usuario = new UsuarioViewModel(dataUsuario, perfil);
        cliente = new ClientViewModel(dataUsuario);
        timer1.start();

    }
    Timer timer1 = new Timer(10000000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cliente.GetReportesDeudas("");
        }
    });

    private void execute() {

        ImageIcon iconIngreso = new ImageIcon(getClass().getResource("/Icon/ingreso.png"));
        ImageIcon iconGastos = new ImageIcon(getClass().getResource("/Icon/gastos.png"));
        ImageIcon iconContacto = new ImageIcon(getClass().getResource("/Icon/contactos.png"));
        ImageIcon iconInventario = new ImageIcon(getClass().getResource("/Icon/inventario.png"));
        ImageIcon iconBancos = new ImageIcon(getClass().getResource("/Icon/banco.png"));
        ImageIcon iconContabilidad = new ImageIcon(getClass().getResource("/Icon/contabilidad.png"));
        ImageIcon iconReporte = new ImageIcon(getClass().getResource("/Icon/reporte.png"));
        ImageIcon iconConfiguracion = new ImageIcon(getClass().getResource("/Icon/settings_32px.png"));
        ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/Icon/aceptar.png"));

        //Create SubMenu Ingreso
        MenuItem menuClient = new MenuItem(iconSubMenu, "Cliente", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.add(new Cliente());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem menuCotizaciones = new MenuItem(iconSubMenu, "Cotizaciones", null);
        MenuItem menuFactura = new MenuItem(iconSubMenu, "Factura", null);
        MenuItem menuFacturasRecurrentes = new MenuItem(iconSubMenu, "Facturas Recurrentes", null);
        MenuItem menuNotaCredito = new MenuItem(iconSubMenu, "Nota de Credito", null);
        MenuItem menuPagosRecibidos = new MenuItem(iconSubMenu, "Pagos Recibidos", null);

        //Create SubMenu Gastos
        MenuItem menuFacturasProveedores = new MenuItem(iconSubMenu, "Facturas Proveedores", null);
        MenuItem menuOrdenesCompra = new MenuItem(iconSubMenu, "Ordenes de Compra", null);
        MenuItem menuPagos = new MenuItem(iconSubMenu, "Pagos", null);
        MenuItem menuPagosRecurrentes = new MenuItem(iconSubMenu, "Pagos Recurrentes", null);

        //Create SubMenu Contactos
        MenuItem menuClientes = new MenuItem(iconSubMenu, "Clientes", null);
        MenuItem menuUsuario = new MenuItem(iconSubMenu, "Usuario", new ActionListener() {
            Usuario usuario;

            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.add(new Usuario());
                panelBody.repaint();
                panelBody.revalidate();
            }
        });

        MenuItem menuProveedores = new MenuItem(iconSubMenu, "Proveedores", null);
        MenuItem menuSolicitudesActualizaciones = new MenuItem(iconSubMenu, "Solicitudes de Actualizaciones", null);
        MenuItem menuTodos = new MenuItem(iconSubMenu, "Todos", null);

        //Create SubMenu Inventario
        MenuItem menuAjustesInventario = new MenuItem(iconSubMenu, "Ajustes del Inventario", null);
        MenuItem menuAlmacenes = new MenuItem(iconSubMenu, "Almacenes", null);
        MenuItem menuCategorias = new MenuItem(iconSubMenu, "Categorias", null);
        MenuItem menuItemVenta = new MenuItem(iconSubMenu, "Item de Venta", null);
        MenuItem menuListasPrecios = new MenuItem(iconSubMenu, "Listas de Precios", null);
        MenuItem menuValorInventario = new MenuItem(iconSubMenu, "Valor de Inventario", null);

        //Create SubMenu Bancos
//        MenuItem menuAjustesInventario = new MenuItem(iconSubMenu, "Ajustes del Inventario");
        //Create SubMenu Reporte
        MenuItem menuReportesPagosCliente = new MenuItem(iconSubMenu, "Reporte Cliente", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.add(new OtraPlantilla());
                panelBody.revalidate();
            }
        });

        //Create SubMenu Configuration        
        MenuItem menuMoneda = new MenuItem(iconSubMenu, "Moneda", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBody.add(new Moneda());
                panelBody.revalidate();
            }
        });

        MenuItem menuIngreso = new MenuItem(iconIngreso, "INGRESOS", null, menuClient, menuCotizaciones, menuFactura, menuFacturasRecurrentes, menuNotaCredito, menuPagosRecibidos);
        MenuItem menuGastos = new MenuItem(iconGastos, "GASTOS", null, menuFacturasProveedores, menuOrdenesCompra, menuPagos, menuPagosRecurrentes);
        MenuItem menuContacto = new MenuItem(iconContacto, "CONTACTOS", null, menuClientes, menuUsuario, menuProveedores, menuSolicitudesActualizaciones, menuTodos);
        MenuItem menuInventario = new MenuItem(iconInventario, "INVENTARIO", null, menuAjustesInventario, menuAlmacenes, menuCategorias, menuItemVenta, menuListasPrecios, menuValorInventario);
        MenuItem menuBancons = new MenuItem(iconBancos, "BANCOS", null);
        MenuItem menuContabilidad = new MenuItem(iconContabilidad, "CONTABILIDAD", null);
        MenuItem menuRepote = new MenuItem(iconReporte, "REPORTES", null, menuReportesPagosCliente);
        MenuItem menuConfiguracion = new MenuItem(iconConfiguracion, "CONFIGURACION", null, menuMoneda);
        addMenu(menuIngreso, menuGastos, menuContacto, menuInventario, menuBancons, menuContabilidad, menuRepote, menuConfiguracion);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem menuItem : subMenu) {
                addMenu(menuItem);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        iconminmaxclose = new javax.swing.JPanel();
        buttonClose = new javax.swing.JPanel();
        close = new javax.swing.JLabel();
        buttonMaximized = new javax.swing.JPanel();
        min = new javax.swing.JLabel();
        buttoMinimized = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbUsuarioPerfil = new javax.swing.JLabel();
        lbNombrePerfil = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        menuIcon = new javax.swing.JPanel();
        lineaHideMenu = new javax.swing.JPanel();
        ocultarMenu = new javax.swing.JPanel();
        buttonMenu = new javax.swing.JLabel();
        menuHide = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelHeader.setBackground(new java.awt.Color(39, 36, 42));
        panelHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(39, 36, 42)));
        panelHeader.setPreferredSize(new java.awt.Dimension(959, 50));
        panelHeader.setLayout(new java.awt.BorderLayout());

        iconminmaxclose.setBackground(new java.awt.Color(39, 36, 42));
        iconminmaxclose.setPreferredSize(new java.awt.Dimension(150, 50));
        iconminmaxclose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonClose.setBackground(new java.awt.Color(39, 36, 42));
        buttonClose.setLayout(new java.awt.BorderLayout());

        close.setBackground(new java.awt.Color(0, 51, 255));
        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cerrar.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeMouseExited(evt);
            }
        });
        buttonClose.add(close, java.awt.BorderLayout.CENTER);

        iconminmaxclose.add(buttonClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 40, 50));

        buttonMaximized.setBackground(new java.awt.Color(39, 36, 42));
        buttonMaximized.setLayout(new java.awt.BorderLayout());

        min.setBackground(new java.awt.Color(0, 66, 123));
        min.setForeground(new java.awt.Color(0, 51, 255));
        min.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/minimizar.png"))); // NOI18N
        min.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minMouseExited(evt);
            }
        });
        buttonMaximized.add(min, java.awt.BorderLayout.CENTER);

        iconminmaxclose.add(buttonMaximized, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 40, 50));

        buttoMinimized.setBackground(new java.awt.Color(39, 36, 42));
        buttoMinimized.setLayout(new java.awt.BorderLayout());
        iconminmaxclose.add(buttoMinimized, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 40, 40));

        panelHeader.add(iconminmaxclose, java.awt.BorderLayout.LINE_END);

        jPanel1.setBackground(new java.awt.Color(39, 36, 42));

        lbUsuarioPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/defaultImg.png"))); // NOI18N

        lbNombrePerfil.setForeground(new java.awt.Color(255, 255, 255));
        lbNombrePerfil.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNombrePerfil.setText("Usuario");

        jLabel1.setFont(new java.awt.Font("FreeSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("HERP MicroSystem");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lbUsuarioPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNombrePerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(381, 381, 381))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUsuarioPerfil)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbNombrePerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        panelHeader.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setPreferredSize(new java.awt.Dimension(270, 440));
        menu.setLayout(new java.awt.BorderLayout());

        menuIcon.setBackground(new java.awt.Color(39, 36, 42));
        menuIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menuIcon.setPreferredSize(new java.awt.Dimension(50, 440));
        menuIcon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lineaHideMenu.setBackground(new java.awt.Color(39, 36, 42));

        javax.swing.GroupLayout lineaHideMenuLayout = new javax.swing.GroupLayout(lineaHideMenu);
        lineaHideMenu.setLayout(lineaHideMenuLayout);
        lineaHideMenuLayout.setHorizontalGroup(
            lineaHideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        lineaHideMenuLayout.setVerticalGroup(
            lineaHideMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        menuIcon.add(lineaHideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 5));

        ocultarMenu.setBackground(new java.awt.Color(39, 36, 42));
        ocultarMenu.setLayout(new java.awt.BorderLayout());

        buttonMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonMenu.setIcon(new javax.swing.ImageIcon("/home/ioriyagamy/Documents/software/Hillary/src/Icon/back_32px.png")); // NOI18N
        buttonMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonMenuMouseExited(evt);
            }
        });
        ocultarMenu.add(buttonMenu, java.awt.BorderLayout.CENTER);

        menuIcon.add(ocultarMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 50, 50));

        menu.add(menuIcon, java.awt.BorderLayout.LINE_START);

        menuHide.setBackground(new java.awt.Color(102, 102, 102));
        menuHide.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(255, 255, 255));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        menuHide.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        menu.add(menuHide, java.awt.BorderLayout.CENTER);

        getContentPane().add(menu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1247, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void changeColor(JPanel hover, Color rand) {
        hover.setBackground(rand);
    }

    public void clickMenu(JPanel h1, JPanel h2, int numberbool) {
        if (numberbool == 1) {
            h1.setBackground(new Color(65, 64, 69));
            h2.setBackground(new Color(44, 56, 76));
        } else {
            h1.setBackground(new Color(44, 56, 76));
            h2.setBackground(new Color(65, 64, 69));
        }
    }

    public void changeImage(JLabel button, String resourcheima) {
        ImageIcon aimg = new ImageIcon(getClass().getResource(resourcheima));
        button.setIcon(aimg);
    }

    public void hideshow(JPanel menushowhide, boolean panelBody, JLabel button) {
        if (panelBody == true) {
            menushowhide.setPreferredSize(new Dimension(50, menushowhide.getHeight()));
            changeImage(button, "/Icon/menu.png");
        } else {
            menushowhide.setPreferredSize(new Dimension(270, menushowhide.getHeight()));
            changeImage(button, "/Icon/back_32px.png");
        }
    }

    private void closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseEntered
        changeColor(buttonClose, new Color(65, 64, 69));
    }//GEN-LAST:event_closeMouseEntered

    private void closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseExited
        changeColor(buttonClose, new Color(44, 56, 76));
    }//GEN-LAST:event_closeMouseExited

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(null,
                "Estas Seguro de Salir del Sistema? ", "Cerrar Sesion",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

            try {
                loginViewModel.Close();

            } catch (SQLException e) {
            }
        }
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void minMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseEntered
        changeColor(buttonMaximized, new Color(65, 64, 69));
    }//GEN-LAST:event_minMouseEntered

    private void minMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseExited
        changeColor(buttonMaximized, new Color(44, 56, 76));
    }//GEN-LAST:event_minMouseExited

    private void minMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minMouseClicked
        if (this.getExtendedState() != Main.MAXIMIZED_BOTH) {
            this.setExtendedState(Main.MAXIMIZED_BOTH);
        } else {
            this.setExtendedState(Main.NORMAL);
        }
    }//GEN-LAST:event_minMouseClicked

    private void buttonMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMenuMouseEntered
        changeColor(lineaHideMenu, new Color(247, 78, 105));
    }//GEN-LAST:event_buttonMenuMouseEntered

    private void buttonMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMenuMouseExited
        changeColor(lineaHideMenu, new Color(5, 10, 46));
    }//GEN-LAST:event_buttonMenuMouseExited

    private void buttonMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMenuMouseClicked
//        clickMenu(ocultarMenu, setting, 1);
        if (a == true) {
            hideshow(menu, a, buttonMenu);
            SwingUtilities.updateComponentTreeUI(this);
            a = false;
        } else {
            hideshow(menu, a, buttonMenu);
            SwingUtilities.updateComponentTreeUI(this);
            a = true;

        }

    }//GEN-LAST:event_buttonMenuMouseClicked


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.YES_NO_OPTION == JOptionPane.showConfirmDialog(null,
                "Estas Seguro de Salir del Sistema? ", "Cerrar Sesion",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {

        }
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttoMinimized;
    private javax.swing.JPanel buttonClose;
    private javax.swing.JPanel buttonMaximized;
    private javax.swing.JLabel buttonMenu;
    private javax.swing.JLabel close;
    private javax.swing.JPanel iconminmaxclose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNombrePerfil;
    private javax.swing.JLabel lbUsuarioPerfil;
    private javax.swing.JPanel lineaHideMenu;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel menuHide;
    private javax.swing.JPanel menuIcon;
    private javax.swing.JPanel menus;
    private javax.swing.JLabel min;
    private javax.swing.JPanel ocultarMenu;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    // End of variables declaration//GEN-END:variables
}
