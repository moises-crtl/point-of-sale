package viewModels;

import conexion.Consult;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import libreria.Encriptar;
import libreria.Hardware4Nix;
import libreria.Objeto;
import libreria.Ordenador;
import models.usuario.TUsuarios;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author ioriyagamy
 */
public class LoginViewModel extends Consult {

    private ArrayList<JTextField> texField;
    private ArrayList<JLabel> label;
    private List<TUsuarios> listUsuarios;

    public LoginViewModel(ArrayList<JLabel> label, ArrayList<JTextField> texField) {
        this.label = label;
        this.texField = texField;
    }

    public LoginViewModel() {

    }

    public Object[] Login() throws SQLException {
        if (texField.get(0).getText().equals("")) {
            label.get(0).setText("Ingrese el Usuario");
            label.get(0).setForeground(Color.red);
            texField.get(0).requestFocus();
        } else {
            if (!Objeto.events.isEmail(texField.get(0).getText())) {
                label.get(0).setText("Ingrese un Email Valido");
                label.get(0).setForeground(Color.red);
                texField.get(0).requestFocus();
            } else {
                if (texField.get(1).getText().equals("")) {
                    label.get(1).setText("Ingrese el Password");
                    label.get(1).setForeground(Color.red);
                    texField.get(1).requestFocus();
                } else {
                    listUsuarios = Usuarios().stream().filter(u -> u.getEmail()
                            .equals(texField.get(0).getText()))
                            .collect(Collectors.toList());
                    if (!listUsuarios.isEmpty()) {
                        try {
                            if (listUsuarios.get(0).isState()) {
                                var password = Encriptar.decrypt(listUsuarios.get(0).getPassword());
                                if (password.equals(texField.get(1).getText())) {
                                    Date date = new Date();
                                    //para sistemas windows
//                                String hdd = Ordenador.getSerilaNumber('c');
                                    //para sistemas linux
                                    String hdd = Hardware4Nix.getSerialNumber();
                                    final QueryRunner queryRunner = new QueryRunner(true);
                                    getConn().setAutoCommit(false);
                                    Object[] usuario = {true};
                                    String sqlUpdate1 = "UPDATE TUsuarios SET is_active = ? "
                                            + "WHERE idUsuario =" + listUsuarios.get(0).getIdUsuario();
                                    queryRunner.update(getConn(), sqlUpdate1, usuario);
                                    var dataOrdenador = Ordenadores().stream().filter(o -> o.getOrdenador()
                                            .equals(hdd)).collect(Collectors.toList());
                                    if (dataOrdenador.isEmpty()) {
                                        String sqlInsert2 = "INSERT INTO TOrdenadores "
                                                + "(ordenador,is_active,usuario,inFecha,idUsuario)"
                                                + " VALUES (?,?,?,?,?)";
                                        Object[] ordenador = {
                                            hdd,
                                            true,
                                            listUsuarios.get(0).getEmail(),
                                            date,
                                            listUsuarios.get(0).getIdUsuario()
                                        };
                                        queryRunner.insert(getConn(), sqlInsert2, new ColumnListHandler(), ordenador);
                                    } else {
                                        Object[] ordenador = {true, listUsuarios.get(0).getEmail(), date};
                                        String sqlUpdate3 = "UPDATE TOrdenadores SET is_active = ?, usuario = ?, "
                                                + "inFecha = ? WHERE idTOrdenadores = " + dataOrdenador.get(0).getIdTOrdenadores();
                                        queryRunner.update(getConn(), sqlUpdate3, ordenador);
                                    }
//                                getConn().commit();
                                    getConn().setAutoCommit(false);
                                } else {
                                    label.get(1).setText("Password incorrecto");
                                    label.get(1).setForeground(Color.red);
                                    texField.get(1).requestFocus();
                                    listUsuarios.clear();
                                }
                            } else {
                                listUsuarios.clear();
                                JOptionPane.showConfirmDialog(null, "El usuario no esta disponible", "Estado",
                                        JOptionPane.YES_OPTION, JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (Exception e) {
                            getConn().rollback();
                            JOptionPane.showMessageDialog(null, e);
                        }
                    } else {
                        label.get(0).setText("El email no esta registrado");
                        label.get(0).setForeground(Color.red);
                        label.get(0).requestFocus();
                        listUsuarios.clear();
                    }
                }
            }

        }

        Object[] objects = {listUsuarios};
        return objects;
    }

    public Object[] Verificar() {
        listUsuarios = new ArrayList<>();
        try {
            //para windows
//            var hdd = Ordenador.getSerilaNumber('c');
            //para Linux
            var hdd = Hardware4Nix.getSerialNumber();
            var dataOrdenador = Ordenadores().stream().filter(o -> o.getOrdenador()
                    .equals(hdd) && o.isIs_active() == true)
                    .collect(Collectors.toList());
            if (!dataOrdenador.isEmpty()) {
                listUsuarios = Usuarios().stream().filter(u -> u.getEmail()
                        .equals(dataOrdenador.get(0).getUsuario()))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
        }
        Object[] objects = {listUsuarios};
        return objects;
    }

    public void Close() throws SQLException {
        listUsuarios = new ArrayList<>();
        final QueryRunner queryRunner = new QueryRunner(true);
        getConn().setAutoCommit(false);
        try {
            Date date = new Date();
            //para windows
            //var hdd = Ordenador.getSerilaNumber('c');
            //para linux
            var hdd = Hardware4Nix.getSerialNumber();
            var dataOrdenador = Ordenadores().stream().filter(o -> o.getOrdenador()
                    .equals(hdd)).collect(Collectors.toList());
            listUsuarios = Usuarios().stream().filter(u -> u.getEmail()
                    .equals(dataOrdenador.get(0).getUsuario())).collect(Collectors.toList());
            Object[] usuario = {false};
            String sql1 = "UPDATE TUsuarios SET is_active = ? "
                    + "WHERE idUsuario =" + listUsuarios.get(0).getIdUsuario();
            queryRunner.update(getConn(), sql1, usuario);
            Object[] ordenador = {false, date};
            String sql2 = "UPDATE TOrdenadores SET is_active = ?,"
                    + "outFecha = ? WHERE idTOrdenadores =" + dataOrdenador.get(0).getIdTOrdenadores();
            queryRunner.update(getConn(), sql2, ordenador);
            getConn().setAutoCommit(false);
        } catch (Exception e) {
            getConn().rollback();
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
