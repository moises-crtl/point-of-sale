package viewModels;

import conexion.Consult;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import libreria.FormatDecimal;
import models.tConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author ioriyagamy
 */
public class ConfiguratioViewModel extends Consult {

    public static String money;
    public static Double intereses;
    public static List<JRadioButton> radio;
    private List<JTextField> textField;
    private List<JLabel> label;
    private FormatDecimal _format;
    
    public ConfiguratioViewModel() {
        TypeMoney();
        Restablecer();
    }

    public ConfiguratioViewModel(List<JRadioButton> radio) {
        this.radio = radio;
        RadioEvent();
        TypeMoney();
        Restablecer();
    }

    public ConfiguratioViewModel(List<JRadioButton> radio, List<JTextField> textField,
            List<JLabel> label) {
        this.radio = radio;
        this.textField = textField;
        this.label = label;
        _format = new FormatDecimal();
        RadioEvent();
        TypeMoney();
        Restablecer();
    }

    private void RadioEvent() {
        radio.get(0).addActionListener((ActionEvent e) -> {
            TypeMoney("RD$", radio.get(0).isSelected());

        });

        radio.get(1).addActionListener((ActionEvent e) -> {
            TypeMoney("Dolar", radio.get(1).isSelected());
        });
    }

    private String sqlConfig;

    private void TypeMoney() {
        sqlConfig = "INSERT INTO tConfiguration(typeMoney) VALUES(?)";
        List<tConfiguration> config = config();
        final QueryRunner queryRunner = new QueryRunner(true);
        if (config.isEmpty()) {
            money = "RD$";
            Object[] dataConfig = {money};
            try {
                queryRunner.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);
            } catch (SQLException e) {

            }
        } else {
            tConfiguration data = config.get(0);
            money = data.getTypeMoney();
            if (radio != null) {
                switch (money) {
                    case "RD$":
                        radio.get(0).setSelected(true);
                        break;
                    case "Dolar":
                        radio.get(1).setSelected(true);
                        break;
                }
            }

        }
    }

    private void TypeMoney(String typeMoney, boolean valor) {
        final QueryRunner queryRunner = new QueryRunner(true);
        if (valor) {
            try {
                List<tConfiguration> config = config();
                if (config.isEmpty()) {
                    sqlConfig = "INSERT INTO tConfiguration(typeMoney) VALUES(?)";
                    money = typeMoney;
                    Object[] dataConfig = {money};

                    queryRunner.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);

                } else {
                    tConfiguration data = config.get(0);
                    sqlConfig = "UPDATE tConfiguration SET typeMoney = ? WHERE idtConfiguration =" + data.getIdtConfiguration();
                    if (data.getTypeMoney().equals(typeMoney)) {
                        money = typeMoney;
                    } else {
                        money = typeMoney;
                        Object[] dataConfig = {money};
                        queryRunner.update(getConn(), sqlConfig, dataConfig);
                    }
                }
            } catch (SQLException e) {
            }
        }

    }

    public void RegistrarIntereses() {
        if (textField.get(0).getText().equals("")) {
            label.get(0).setText("Ingrese el Interes");
            label.get(0).setForeground(Color.red);
            textField.get(0).requestFocus();
        } else {
            if (radio.get(2).isSelected()) {
                try {
                    final QueryRunner queryRunner = new QueryRunner();
                    var tconfiguration = config();
                    if (tconfiguration.isEmpty()) {
                        var sqlConfig = "INSERT INTO tConfiguration(typeMoney,intereses) VALUES(?,?)";
                        Object[] dataConfig = {"RD$", _format.rebuild(textField.get(0).getText())};
                        queryRunner.insert(getConn(), sqlConfig, new ColumnListHandler(), dataConfig);
                    } else {
                        var data = tconfiguration.get(0);
                        var sqlConfig = "UPDATE tConfiguration SET typeMoney = ?,intereses = ?"
                                + " WHERE idtConfiguration = " + data.getIdtConfiguration();
                        Object[] dataConfig = {
                            data.getTypeMoney(),
                            _format.rebuild(textField.get(0).getText())
                        };
                        queryRunner.update(getConn(), sqlConfig, dataConfig);
                    }
                    Restablecer();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                label.get(0).setText("Seleccione la Opcion Intereses");
                label.get(0).setForeground(Color.red);
            }
        }
    }

    private void Restablecer() {
        var tConfiguration = config();
        if (!tConfiguration.isEmpty()) {
            var data = tConfiguration.get(0);
            intereses = data.getIntereses();
            if (label != null) {
                var interest = data.getIntereses() == 0.0 ? "0.0%" : data.getIntereses() + "%";
                label.get(1).setText(interest);
                textField.get(0).setText("");
                label.get(0).setText("");
                radio.get(0).setSelected(false);
            }
        }
    }
}
