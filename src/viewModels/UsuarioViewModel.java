package viewModels;

import conexion.Consult;
import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import libreria.Objeto;
import libreria.UploadImage;
import models.usuario.TUsuarios;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import libreria.Calendario;
import libreria.Encriptar;
import libreria.Pager;
import libreria.RenderCheckBox;
import models.usuario.TRoles;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author ioriyagamy
 */
public class UsuarioViewModel extends Consult {

    private static TUsuarios dataUsuario;
    private static JLabel picturePerfil;
    private static JLabel nombrePerfil;
    private JLabel _imagePicture;
    private List<JTextField> _txtField;
    private List<JLabel> _label;
    private JCheckBox _checkBoxState;
    private JComboBox _comboBoxRole;
    private JTable tableUser;
    private JSpinner spinnerPaginas;
    private String _action = "insert";
    private final UploadImage uploadImage = new UploadImage();
    private DefaultTableModel modelo1;
    private int _reg_For_Page = 10;
    private int _num_Page = 1;
    private int _seccion = 1;
    private Pager<TUsuarios> paginadorUsuarios;

    public UsuarioViewModel(TUsuarios dataUsuario, Object[] perfil) {
        this.dataUsuario = dataUsuario;
        picturePerfil = (JLabel) perfil[0];
        nombrePerfil = (JLabel) perfil[1];
        Perfil();
    }

    public UsuarioViewModel(Object[] objects, List<JTextField> txtField, List<JLabel> label) {
        this._txtField = txtField;
        this._label = label;
        _imagePicture = (JLabel) objects[0];
        _checkBoxState = (JCheckBox) objects[1];
        tableUser = (JTable) objects[2];
        spinnerPaginas = (JSpinner) objects[3];
        _comboBoxRole = (JComboBox) objects[4];
        Restablecer();
    }

    private void Perfil() {
        nombrePerfil.setText(dataUsuario.getName());
        if (null != dataUsuario.getImagen()) {
            uploadImage.byteImage(picturePerfil, dataUsuario.getImagen());
        }
    }
    //<editor-fold defaultstate="collapsed" desc="CODIGO DE REGISTRAR USUARIOS">

    public void RegistrarUsuario() {
        if (_txtField.get(0).getText().equals("")) {
            _label.get(0).setText("Ingrese el Nid");
            _label.get(0).setForeground(Color.red);
            _txtField.get(0).requestFocus();
        } else {
            if (_txtField.get(1).getText().equals("")) {
                _label.get(1).setText("Ingrese el Nombre");
                _label.get(1).setForeground(Color.red);
                _txtField.get(1).requestFocus();
            } else {
                if (_txtField.get(2).getText().equals("")) {
                    _label.get(2).setText("Ingrese el Apellido");
                    _label.get(2).setForeground(Color.red);
                    _txtField.get(2).requestFocus();
                } else {
                    if (_txtField.get(3).getText().equals("")) {
                        _label.get(3).setText("Ingrese la Direccion");
                        _label.get(3).setForeground(Color.red);
                        _txtField.get(3).requestFocus();
                    } else {
                        if (_txtField.get(4).getText().equals("")) {
                            _label.get(4).setText("Ingrese el Telefono");
                            _label.get(4).setForeground(Color.red);
                            _txtField.get(4).requestFocus();
                        } else {
                            if (_txtField.get(5).getText().equals("")) {
                                _label.get(5).setText("Ingrese el Email");
                                _label.get(5).setForeground(Color.red);
                                _txtField.get(5).requestFocus();
                            } else {
                                if (!Objeto.events.isEmail(_txtField.get(5).getText())) {
                                    _label.get(5).setText("Ingrese un Email Valido");
                                    _label.get(5).setForeground(Color.red);
                                    _txtField.get(5).requestFocus();
                                } else {
                                    if (_txtField.get(6).getText().equals("")) {
                                        _label.get(6).setText("Ingrese el Usuario");
                                        _label.get(6).setForeground(Color.red);
                                        _txtField.get(6).requestFocus();
                                    } else {
                                        if (_txtField.get(7).getText().equals("")) {
                                            _label.get(7).setText("Ingrese el Password");
                                            _label.get(7).setForeground(Color.red);
                                            _txtField.get(7).requestFocus();
                                        } else {
                                            int count;
                                            var listEmail = Usuarios().stream()
                                                    .filter(u -> u.getEmail()
                                                    .equals(_txtField.get(5).getText()))
                                                    .collect(Collectors.toList());
                                            count = listEmail.size();
                                            var listNid = Usuarios().stream().filter(u -> u.getNid()
                                                    .equals(_txtField.get(0).getText()))
                                                    .collect(Collectors.toList());
                                            count += listNid.size();
                                            try {
                                                switch (_action) {
                                                    case "insert":
                                                        if (count == 0) {
                                                            SaveData();
                                                        } else {
                                                            if (!listEmail.isEmpty()) {
                                                                _label.get(5).setText("El Email ya esta Registrado");
                                                                _label.get(5).setForeground(Color.red);
                                                                _txtField.get(5).requestFocus();
                                                            }
                                                            if (!listNid.isEmpty()) {
                                                                _label.get(0).setText("El Nid ya esta Registrado");
                                                                _label.get(0).setForeground(Color.red);
                                                                _txtField.get(0).requestFocus();
                                                            }
                                                        }
                                                        break;
                                                    case "update":
                                                        if (count == 2) {
                                                            if (listEmail.get(0).getIdUsuario() == _idUsuario
                                                                    && listNid.get(0).getIdUsuario()
                                                                    == _idUsuario) {
                                                                SaveData();
                                                            } else {
                                                                if (listNid.get(0).getIdUsuario() != _idUsuario) {
                                                                    _label.get(0).setText("El nid ya esta registrado");
                                                                    _label.get(0).setForeground(Color.red);
                                                                    _txtField.get(0).requestFocus();
                                                                }
                                                                if (listEmail.get(0).getIdUsuario() != _idUsuario) {
                                                                    _label.get(5).setText("El email. ya esta registrado");
                                                                    _label.get(5).setForeground(Color.red);
                                                                    _txtField.get(5).requestFocus();
                                                                }
                                                            }
                                                        } else {
                                                            if (count == 0) {
                                                                SaveData();
                                                            } else {
                                                                if (!listNid.isEmpty()) {
                                                                    if (listNid.get(0).getIdUsuario() == _idUsuario) {
                                                                        SaveData();
                                                                    } else {
                                                                        _label.get(0).setText("el nid ya esta registrado");
                                                                        _label.get(0).setForeground(Color.red);
                                                                        _txtField.get(0).requestFocus();
                                                                    }
                                                                }
                                                                if (!listEmail.isEmpty()) {
                                                                    if (listEmail.get(5).getIdUsuario() == _idUsuario) {
                                                                        SaveData();
                                                                    } else {
                                                                        _label.get(5).setText("el email ya esta registrado");
                                                                        _label.get(5).setForeground(Color.red);
                                                                        _txtField.get(5).requestFocus();
                                                                    }
                                                                }

                                                            }
                                                        }

                                                        break;
                                                }
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(null, e);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void SaveData() throws SQLException {
        try {
            final QueryRunner queryRunner = new QueryRunner(true);
            getConn().setAutoCommit(false);
            byte[] image = UploadImage.getImageByte();
            if (image == null) {
                image = Objeto.uploadImage.getTransPhoto(_imagePicture);
            }
            var role = (TRoles) _comboBoxRole.getSelectedItem();

            switch (_action) {
                case "insert":
                    String sqlUsuario1 = "INSERT INTO TUsuarios(nid,name,lastName,address,phone,email,usuario,"
                            + "password,role,imagen,is_active,state,fecha) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    Object[] dataUsuario1 = {
                        _txtField.get(0).getText(),
                        _txtField.get(1).getText(),
                        _txtField.get(2).getText(),
                        _txtField.get(3).getText(),
                        _txtField.get(4).getText(),
                        _txtField.get(5).getText(),
                        _txtField.get(6).getText(),
                        Encriptar.encript(_txtField.get(7).getText()),
                        role.getRole(),
                        image,
                        true,
                        _checkBoxState.isSelected(),
                        new Date()
                    };
                    queryRunner.insert(getConn(), sqlUsuario1, new ColumnListHandler(), dataUsuario1);
                    break;
                case "update":
                    Object[] dataUsuario2 = {
                        _txtField.get(0).getText(),
                        _txtField.get(1).getText(),
                        _txtField.get(2).getText(),
                        _txtField.get(3).getText(),
                        _txtField.get(4).getText(),
                        _txtField.get(5).getText(),
                        _txtField.get(6).getText(),
                        _checkBoxState.isSelected(),
                        role.getRole(),
                        image

                    };
                    String sqlUsuario2 = "UPDATE TUsuarios SET nid = ?,name = ?,lastName = ?,"
                            + "address = ?,phone = ?,email = ?,usuario = ?,state = ?,role = ?,imagen = ?"
                            + " WHERE idUsuario =" + _idUsuario;
                    queryRunner.update(getConn(), sqlUsuario2, dataUsuario2);

                    break;
            }
            getConn().commit();
            Restablecer();
        } catch (Exception e) {
            getConn().rollback();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void SearchUsuarios(String campo) {
        List<TUsuarios> usuariosFilter;
        String[] titulo = {"Id", "Nid", "Nombre", "Apellido", "Email", "Direccion", "Telefono", "Usuario",
            "Role", "Estado", "Imagen"};
        modelo1 = new DefaultTableModel(null, titulo);
        int inicio = (_num_Page - 1) * _reg_For_Page;
        if (campo.equals("")) {
            usuariosFilter = Usuarios().stream().skip(inicio)
                    .limit(_reg_For_Page).collect(Collectors.toList());
        } else {
            usuariosFilter = Usuarios().stream().filter(c -> c.getNid().startsWith(campo)
                    || c.getName().startsWith(campo) || c.getLastName().startsWith(campo))
                    .skip(inicio).limit(_reg_For_Page).collect(Collectors.toList());
        }
        if (!usuariosFilter.isEmpty()) {
            usuariosFilter.forEach(item -> {
                Object[] registros = {
                    item.getIdUsuario(),
                    item.getNid(),
                    item.getName(),
                    item.getLastName(),
                    item.getEmail(),
                    item.getAddress(),
                    item.getPhone(),
                    item.getUsuario(),
                    item.getRole(),
                    item.isState(),
                    item.getImagen()
                };
                modelo1.addRow(registros);
            });
        }

        tableUser.setModel(modelo1);
        tableUser.setRowHeight(30);
        tableUser.getColumnModel().getColumn(0).setMaxWidth(0);
        tableUser.getColumnModel().getColumn(0).setMinWidth(0);
        tableUser.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableUser.getColumnModel().getColumn(10).setMaxWidth(0);
        tableUser.getColumnModel().getColumn(10).setMinWidth(0);
        tableUser.getColumnModel().getColumn(10).setPreferredWidth(0);
        tableUser.getColumnModel().getColumn(9).setCellRenderer(new RenderCheckBox());
    }
    private int _idUsuario = 0;

    public void GetUsuario() {
        _action = "update";
        int filas = tableUser.getSelectedRow();
        _idUsuario = (Integer) modelo1.getValueAt(filas, 0);
        _txtField.get(0).setText((String) modelo1.getValueAt(filas, 1));
        _txtField.get(1).setText((String) modelo1.getValueAt(filas, 2));
        _txtField.get(2).setText((String) modelo1.getValueAt(filas, 3));
        _txtField.get(5).setText((String) modelo1.getValueAt(filas, 4));
        _txtField.get(3).setText((String) modelo1.getValueAt(filas, 5));
        _txtField.get(4).setText((String) modelo1.getValueAt(filas, 6));
        _txtField.get(6).setText((String) modelo1.getValueAt(filas, 7));
        _txtField.get(7).setText("********");
        _txtField.get(7).setEditable(false);
        var model = new DefaultComboBoxModel();
        var role = new TRoles();
        var rol = (String) modelo1.getValueAt(filas, 8);
        role.setRole(rol);
        model.addElement(role);
        roles().forEach(item -> {
            if (!rol.equals(item.getRole())) {
                model.addElement(item);
            }
        });
        _comboBoxRole.setModel(model);
        _checkBoxState.setSelected((Boolean) modelo1.getValueAt(filas, 9));
        byte[] image = (byte[]) modelo1.getValueAt(filas, 10);
        if (image != null) {
            Objeto.uploadImage.byteImage(_imagePicture, image);
        }
    }

    public final void Restablecer() {
        _seccion = 1;
        _action = "insert";
        for (int i = 0; i < _txtField.size(); i++) {
            _txtField.get(i).setText("");
        }
        _checkBoxState.setSelected(false);
        _checkBoxState.setForeground(new Color(102, 102, 102));
        _label.get(0).setText("NID");
        _label.get(0).setForeground(new Color(102, 102, 102));
        _label.get(1).setText("NOMBRE");
        _label.get(1).setForeground(new Color(102, 102, 102));
        _label.get(2).setText("APELLIDO");
        _label.get(2).setForeground(new Color(102, 102, 102));
        _label.get(3).setText("DIRECCION");
        _label.get(3).setForeground(new Color(102, 102, 102));
        _label.get(4).setText("TELEFONO");
        _label.get(4).setForeground(new Color(102, 102, 102));
        _label.get(5).setText("EMAIL");
        _label.get(5).setForeground(new Color(102, 102, 102));
        _label.get(6).setText("USUARIO");
        _label.get(6).setForeground(new Color(102, 102, 102));
        _label.get(7).setText("PASSWORD");
        _label.get(7).setForeground(new Color(102, 102, 102));
        _imagePicture.setIcon(new ImageIcon(getClass().getClassLoader()
                .getResource("Icon/client.png")));
        getRoles();
        listUsuarios = Usuarios();
        if (!listUsuarios.isEmpty()) {
            paginadorUsuarios = new Pager<>(listUsuarios, _label.get(9), _reg_For_Page);
        }
        SearchUsuarios("");
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(
                10.0, //Dato vizualizando al inicio en el spinner
                1.0, //Limite inferior
                100.0, //Limite superior
                1.0 //Incremento-decremento

        );
        spinnerPaginas.setModel(spinnerNumberModel);
    }

    public void getRoles() {
        var model = new DefaultComboBoxModel();
        roles().forEach(item -> {
            model.addElement(item);
        });
        _comboBoxRole.setModel(model);
    }
    private List<TUsuarios> listUsuarios;

    public void Paginador(String metodo) {
        switch (metodo) {
            case "first":
                switch (_seccion) {
                    case 1:
                        if (!listUsuarios.isEmpty()) {
                            _num_Page = paginadorUsuarios.firstPage();
                        }
                        break;
                }
                break;
            case "Previous":
                switch (_seccion) {
                    case 1:
                        if (!listUsuarios.isEmpty()) {
                            _num_Page = paginadorUsuarios.previousPage();
                        }
                        break;
                }
                break;
            case "Next":
                switch (_seccion) {
                    case 1:
                        if (!listUsuarios.isEmpty()) {
                            _num_Page = paginadorUsuarios.nextPage();
                        }
                        break;

                }
                break;
            case "Last":
                switch (_seccion) {
                    case 1:
                        if (!listUsuarios.isEmpty()) {
                            _num_Page = paginadorUsuarios.last();
                        }
                        break;
                }
                break;
        }
        switch (_seccion) {
            case 1:
                SearchUsuarios("");
                break;
        }
    }

    public void Registro_Paginas() {
        _num_Page = 1;
        Number value = (Number) spinnerPaginas.getValue();
        _reg_For_Page = value.intValue();
        switch (_seccion) {
            case 1:
                if (!listUsuarios.isEmpty()) {
                    paginadorUsuarios = new Pager<>(listUsuarios, _label.get(9), _reg_For_Page);
                }
                SearchUsuarios("");
                break;
        }
    }
    //</editor-fold>
}
