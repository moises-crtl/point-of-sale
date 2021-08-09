package viewModels;

import conexion.Consult;
import datechooser.beans.DateChooserCombo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import models.Cliente.*;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;
import libreria.*;
import models.tConfiguration;
import models.usuario.TUsuarios;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import static viewModels.ConfiguratioViewModel.intereses;

/**
 *
 * @author ioriyagamy
 */
public class ClientViewModel extends Consult {

    private String accion = "insert", mony;
    private ArrayList<JLabel> labelVM;
    private ArrayList<JTextField> txtFieldVM;
    private JCheckBox checkBoxCredito;
    private JTable tableClients, tableReports, tableReportDeuda;
    private JTable _tablePagosCuotas;
    private DefaultTableModel model1, model2, model3, model4;
    private JSpinner spinnerPage;
    private JRadioButton radioCuotas, radioInteres;
    private int idClient = 0;
    private int numPage = 1;
    private int regForPage = 10;
    public int section;
    private FormatDecimal format;
    private Pager<client> pagerClients;
    private Pager<client> pagerReports;
    private Codigos codigos;
    private SimpleDateFormat formateador;    
    private DateChooserCombo _dateChooser, _dateChooser1, _dateChooser2;
    private JCheckBox _checkBoxDia;
    private static TUsuarios dataUsuario;
    private ConfiguratioViewModel configuratioViewModel;

    public ClientViewModel() {

    }

    public ClientViewModel(TUsuarios dataUsuario) {
        this.dataUsuario = dataUsuario;
        formateador = new SimpleDateFormat();        
        configuratioViewModel = new ConfiguratioViewModel();

    }

    public ClientViewModel(Object[] objects, ArrayList<JLabel> label, ArrayList<JTextField> textField) {
        labelVM = label;
        txtFieldVM = textField;
        checkBoxCredito = (JCheckBox) objects[0];
        tableClients = (JTable) objects[1];
        spinnerPage = (JSpinner) objects[2];
        tableReports = (JTable) objects[3];
        radioCuotas = (JRadioButton) objects[4];
        radioInteres = (JRadioButton) objects[5];
        tableReportDeuda = (JTable) objects[6];
        _dateChooser = (DateChooserCombo) objects[7];
        _checkBoxDia = (JCheckBox) objects[8];
        _dateChooser1 = (DateChooserCombo) objects[9];
        _dateChooser2 = (DateChooserCombo) objects[10];
        _tablePagosCuotas = (JTable) objects[11];
        format = new FormatDecimal();
        mony = ConfiguratioViewModel.money;
        formateador = new SimpleDateFormat("dd/MM/yyyy");
        codigos = new Codigos();
        restablecer();
        restablecerReporte();
        RestReportDeudas();
    }

    public void addClient() {
        if (this.txtFieldVM.get(0).getText().equals("")) {
            this.labelVM.get(0).setText("Ingrese el Nid");
            this.labelVM.get(0).setForeground(Color.red);
            this.txtFieldVM.get(0).requestFocus();
        } else {
            if (this.txtFieldVM.get(1).getText().equals("")) {
                this.labelVM.get(1).setText("Ingrese el Nombre");
                this.labelVM.get(1).setForeground(Color.red);
                this.txtFieldVM.get(1).requestFocus();
            } else {
                if (this.txtFieldVM.get(2).getText().equals("")) {
                    this.labelVM.get(2).setText("Ingrese el Apellido");
                    this.labelVM.get(2).setForeground(Color.red);
                    this.txtFieldVM.get(2).requestFocus();
                } else {
                    if (this.txtFieldVM.get(3).getText().equals("")) {
                        this.labelVM.get(3).setText("Ingrese el Direccion");
                        this.labelVM.get(3).setForeground(Color.red);
                        this.txtFieldVM.get(3).requestFocus();
                    } else {
                        if (this.txtFieldVM.get(4).getText().equals("")) {
                            this.labelVM.get(4).setText("Ingrese el Telefono");
                            this.labelVM.get(4).setForeground(Color.red);
                            this.txtFieldVM.get(4).requestFocus();
                        } else {
                            if (!Objeto.events.isEmail(txtFieldVM.get(5).getText())) {
                                this.labelVM.get(5).setText("Ingrese un Email Valido");
                                this.labelVM.get(5).setForeground(Color.red);
                                this.txtFieldVM.get(5).requestFocus();
                            } else {
                                if (this.txtFieldVM.get(6).getText().equals("")) {
                                    this.labelVM.get(6).setText("Ingrese la Ciudad");
                                    this.labelVM.get(6).setForeground(Color.red);
                                    this.txtFieldVM.get(6).requestFocus();
                                } else {
                                    if (this.txtFieldVM.get(7).getText().equals("")) {
                                        this.labelVM.get(7).setText("Ingrese el Rnc");
                                        this.labelVM.get(7).setForeground(Color.red);
                                        this.txtFieldVM.get(7).requestFocus();
                                    } else {
                                        int count;
                                        List<client> listEmail = clients().stream().filter(u -> u.getEmail().equals(txtFieldVM.get(5).getText()))
                                                .collect(Collectors.toList());
                                        count = listEmail.size();
                                        List<client> listNid = clients().stream().filter(u -> u.getNid().equals(txtFieldVM.get(0).getText()))
                                                .collect(Collectors.toList());
                                        count += listNid.size();
                                        try {
                                            switch (accion) {
                                                case "insert":
                                                    if (count == 0) {
                                                        SaveData();
                                                    } else {
                                                        if (!listEmail.isEmpty()) {
                                                            labelVM.get(5).setText("El Email ya esta Registrado");
                                                            labelVM.get(5).setForeground(Color.red);
                                                            txtFieldVM.get(5).requestFocus();
                                                        }
                                                        if (!listNid.isEmpty()) {
                                                            labelVM.get(0).setText("El Nid ya esta Registrado");
                                                            labelVM.get(0).setForeground(Color.red);
                                                            txtFieldVM.get(0).requestFocus();
                                                        }
                                                    }

                                                    break;
                                                case "update":
                                                    if (count == 2) {
                                                        if (listEmail.get(0).getIdClient() == idClient && listNid.get(0).getIdClient() == idClient) {
                                                            SaveData();
                                                        } else {
                                                            if (listNid.get(0).getIdClient() != idClient) {
                                                                labelVM.get(0).setText("El Nid ya esta Registrado");
                                                                labelVM.get(0).setForeground(Color.red);
                                                                txtFieldVM.get(0).requestFocus();
                                                            }
                                                            if (listEmail.get(0).getIdClient() != idClient) {
                                                                labelVM.get(5).setText("El Email ya esta Registrado");
                                                                labelVM.get(5).setForeground(Color.red);
                                                                txtFieldVM.get(5).requestFocus();

                                                            }
                                                        }
                                                    } else {
                                                        if (count == 0) {
                                                            SaveData();
                                                        } else {
                                                            if (!listNid.isEmpty()) {
                                                                if (listNid.get(0).getIdClient() == idClient) {
                                                                    SaveData();
                                                                } else {
                                                                    labelVM.get(0).setText("El Nid ya esta Registrado");
                                                                    labelVM.get(0).setForeground(Color.red);
                                                                    txtFieldVM.get(0).requestFocus();
                                                                }
                                                            }

                                                            if (!listEmail.isEmpty()) {
                                                                if (listEmail.get(0).getIdClient() == idClient) {
                                                                    SaveData();
                                                                } else {
                                                                    labelVM.get(5).setText("El Email ya esta Registrado");
                                                                    labelVM.get(5).setForeground(Color.red);
                                                                    txtFieldVM.get(5).requestFocus();
                                                                }

                                                            }
                                                        }
                                                    }
                                                    break;
                                            }
                                        } catch (SQLException ex) {
                                            JOptionPane.showMessageDialog(null, ex);
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
                image = Objeto.uploadImage.getTransPhoto(labelVM.get(8));
            }
            switch (accion) {
                case "insert":
                    String sqlClient1 = "INSERT INTO client (nid, name, lastName, address, phone, email, city, rnc, credito, fecha, image) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    Object[] dataClient1 = {
                        txtFieldVM.get(0).getText(),
                        txtFieldVM.get(1).getText(),
                        txtFieldVM.get(2).getText(),
                        txtFieldVM.get(3).getText(),
                        txtFieldVM.get(4).getText(),
                        txtFieldVM.get(5).getText(),
                        txtFieldVM.get(6).getText(),
                        txtFieldVM.get(7).getText(),
                        checkBoxCredito.isSelected(),
                        new Calendario().getFecha(),
                        image
                    };
                    queryRunner.insert(getConn(), sqlClient1, new ColumnListHandler(), dataClient1);
                    String sqlReport = "INSERT INTO clientReport (deuda, mensual, cambio, deudaActual, fechaDeuda,"
                            + " ultimoPago, fechaPago, ticket, fechaLimite, idClient)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    List<client> clientVM = clients();
                    Object[] dataReport = {
                        0,
                        0,
                        0,
                        0,
                        "--/--/--",
                        0,
                        "--/--/--",
                        "0000000000",
                        "--/--/--",
                        clientVM.get(clientVM.size() - 1).getIdClient(),};
                    queryRunner.insert(getConn(), sqlReport, new ColumnListHandler(), dataReport);

                    String sqlReportInteres = "INSERT INTO TReportesInteresCliente (intereses, pago, cambio, cuotas, interesFecha, ticketInteres, idClient)"
                            + " VALUES (?, ?, ?, ?, ?, ?, ?)";
                    Object[] dataReportInteres = {
                        0,
                        0,
                        0,
                        0,
                        "--/--/--",
                        "0000000000",
                        clientVM.get(clientVM.size() - 1).getIdClient()
                    };
                    queryRunner.insert(getConn(), sqlReportInteres, new ColumnListHandler(), dataReportInteres);
                    break;

                case "update":
                    Object[] dataClient2 = {
                        txtFieldVM.get(0).getText(),
                        txtFieldVM.get(1).getText(),
                        txtFieldVM.get(2).getText(),
                        txtFieldVM.get(3).getText(),
                        txtFieldVM.get(4).getText(),
                        txtFieldVM.get(5).getText(),
                        txtFieldVM.get(6).getText(),
                        txtFieldVM.get(7).getText(),
                        checkBoxCredito.isSelected(),
                        image
                    };
                    String sqlClient2 = "UPDATE client SET nid = ?, name = ?, lastName = ?, address = ?,"
                            + " phone = ?, email = ?, city = ?, rnc = ?, credito = ?, image = ? WHERE idClient = " + idClient;
                    queryRunner.update(getConn(), sqlClient2, dataClient2);
                    break;
            }

            getConn().commit();
            restablecer();
        } catch (SQLException ex) {
            getConn().rollback();
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void searchClients(String campo) {
        List<client> clientFilter;
        String[] title = {"IdClient", "Nid", "Nombres", "Apellidos", "Direccion", "Telefono", "Email", "Ciudad", "Rnc", "Credito", "Imagen"};
        model1 = new DefaultTableModel(null, title);
        int inicio = (numPage - 1) * regForPage;
        if (campo.equals("")) {
            clientFilter = clients().stream().skip(inicio).limit(regForPage).collect(Collectors.toList());
        } else {
            clientFilter = clients().stream().filter(clFilter -> clFilter.getNid().startsWith(campo) || clFilter.getName().startsWith(campo)
                    || clFilter.getLastName().startsWith(campo)).skip(inicio).limit(regForPage).collect(Collectors.toList());
        }

        if (!clientFilter.isEmpty()) {
            clientFilter.forEach(item -> {
                Object[] records = {
                    item.getIdClient(),
                    item.getNid(),
                    item.getName(),
                    item.getLastName(),
                    item.getAddress(),
                    item.getPhone(),
                    item.getEmail(),
                    item.getCity(),
                    item.getRnc(),
                    item.isCredito(),
                    item.getImage(),};

                model1.addRow(records);
            });
        }
        //Create Table
        tableClients.setModel(model1);
        tableClients.setRowHeight(30);
        tableClients.getColumnModel().getColumn(0).setMaxWidth(0);
        tableClients.getColumnModel().getColumn(0).setMinWidth(0);
        tableClients.getColumnModel().getColumn(0).setPreferredWidth(0);
        tableClients.getColumnModel().getColumn(10).setMaxWidth(0);
        tableClients.getColumnModel().getColumn(10).setMinWidth(0);
        tableClients.getColumnModel().getColumn(10).setPreferredWidth(0);
        tableClients.getColumnModel().getColumn(9).setCellRenderer(new RenderCheckBox());
    }

    public void GetCliente() {
        accion = "update";
        int filas = tableClients.getSelectedRow();
        idClient = (Integer) model1.getValueAt(filas, 0);
        txtFieldVM.get(0).setText((String) model1.getValueAt(filas, 1));//Nid
        txtFieldVM.get(1).setText((String) model1.getValueAt(filas, 2));//Name
        txtFieldVM.get(2).setText((String) model1.getValueAt(filas, 3));//LastName
        txtFieldVM.get(3).setText((String) model1.getValueAt(filas, 4));//Address
        txtFieldVM.get(4).setText((String) model1.getValueAt(filas, 5));//Phone
        txtFieldVM.get(5).setText((String) model1.getValueAt(filas, 6));//Email
        txtFieldVM.get(6).setText((String) model1.getValueAt(filas, 7));//City
        txtFieldVM.get(7).setText((String) model1.getValueAt(filas, 8));//Rnc
        checkBoxCredito.setSelected((Boolean) model1.getValueAt(filas, 9));
        Objeto.uploadImage.byteImage(labelVM.get(8), (byte[]) model1.getValueAt(filas, 10));

    }

    public final void restablecer() {
        section = 1;
        accion = "insert";
        txtFieldVM.get(0).setText("");
        txtFieldVM.get(1).setText("");
        txtFieldVM.get(2).setText("");
        txtFieldVM.get(3).setText("");
        txtFieldVM.get(4).setText("");
        txtFieldVM.get(5).setText("");
        txtFieldVM.get(6).setText("");
        txtFieldVM.get(7).setText("");
        checkBoxCredito.setSelected(false);
        checkBoxCredito.setForeground(new Color(102, 102, 102));

        labelVM.get(0).setText("NID");
        labelVM.get(0).setForeground(new Color(102, 102, 102));
        labelVM.get(1).setText("NOMBRES");
        labelVM.get(1).setForeground(new Color(102, 102, 102));
        labelVM.get(2).setText("APELLIDOS");
        labelVM.get(2).setForeground(new Color(102, 102, 102));
        labelVM.get(3).setText("DIRECCION");
        labelVM.get(3).setForeground(new Color(102, 102, 102));
        labelVM.get(4).setText("TELEFONO");
        labelVM.get(4).setForeground(new Color(102, 102, 102));
        labelVM.get(5).setText("EMAIL");
        labelVM.get(5).setForeground(new Color(102, 102, 102));
        labelVM.get(6).setText("CIUDAD");
        labelVM.get(6).setForeground(new Color(102, 102, 102));
        labelVM.get(7).setText("RNC");
        labelVM.get(7).setForeground(new Color(102, 102, 102));
        labelVM.get(8).setIcon(new ImageIcon(getClass()
                .getResource("/image/client.png")));
        listClients = clients();
        if (!listClients.isEmpty()) {
            pagerClients = new Pager<>(listClients, labelVM.get(9), regForPage);
        }
        SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(
                10.0,//data displayed at the start of the spinner
                1.0,//lower limit
                100.0,//upper limit
                1.0//increase-decrease

        );
        spinnerPage.setModel(spinnerNumberModel);
        searchClients("");
    }

    private int interesCuotas = 0;
    private int idReport;
    private int idclientReport, idReportInteres;
    private Double intereses = 0.0;
    private Double interesesPagos = 0.0;
    private Double interesPago = 0.0;
    private Double deudaActual = 0.0;
    private Double cambio = 0.0;
    private Double deuda;
    private Double deudaActualCliente = 0.0;
    private Double mensual = 0.0;
    private Double pago = 0.0;
    private Double interesesCliente = 0.0;
    private String ticketCuota, nameCliente, ticketIntereses;
    private List<TInteresesClientes> listIntereses;

    public void searchReport(String valor) {
        List<client> clientFilter;
        String[] title = {"IdClient", "Nid", "Nombres", "Apellidos", "Direccion", "Telefono", "Email", "Ciudad"};
        model2 = new DefaultTableModel(null, title);
        int inicio = (numPage - 1) * regForPage;
        if (valor.equals("")) {
            clientFilter = clients().stream().skip(inicio).limit(regForPage).collect(Collectors.toList());
        } else {
            clientFilter = clients().stream().filter(clFilter -> clFilter.getNid().startsWith(valor) || clFilter.getName().startsWith(valor)
                    || clFilter.getLastName().startsWith(valor)).skip(inicio).limit(regForPage).collect(Collectors.toList());
        }

        if (!clientFilter.isEmpty()) {
            clientFilter.forEach(item -> {
                Object[] records = {
                    item.getIdClient(),
                    item.getNid(),
                    item.getName(),
                    item.getLastName(),
                    item.getAddress(),
                    item.getPhone(),
                    item.getEmail(),
                    item.getCity(),};

                model2.addRow(records);
            });
        }
        tableReports.setModel(model2);
        tableReports.setRowHeight(30);
        tableReports.getColumnModel().getColumn(0).setMaxWidth(0);
        tableReports.getColumnModel().getColumn(0).setMinWidth(0);
        tableReports.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    //Este metodo muestra los datos del cuadro de texto a pagos y reportes
    public void GetReportClient() {
        int filas = tableReports.getSelectedRow();
        idclientReport = (Integer) model2.getValueAt(filas, 0);
        List<clientReport> clienteFilter = reportClient(idclientReport);
        if (!clienteFilter.isEmpty()) {
            clientReport cliente = clienteFilter.get(0);
            idReport = cliente.getIdclientReport();
            idReportInteres = cliente.getIdTReportesInteresCliente();
            nameCliente = cliente.getName() + " " + cliente.getLastName();
            labelVM.get(10).setText(nameCliente);
            deudaActual = (Double) cliente.getDeudaActual();
            deuda = (Double) cliente.getDeuda();
            labelVM.get(11).setText(mony + format.decimal(deudaActual));
            labelVM.get(12).setText(mony + format.decimal((Double) cliente.getUltimoPago()));//verificar el ultimo pago que no me esta llegando
            ticketCuota = cliente.getTicket();
            labelVM.get(13).setText(ticketCuota);
            labelVM.get(14).setText(cliente.getFechaPago());
            labelVM.get(15).setText(mony + format.decimal((Double) cliente.getMensual()));
            listIntereses = InteresesCliente().stream().filter(u -> u.getIdClient() == idclientReport
                    && u.getCancelado() == false).collect(Collectors.toList());
            if (listIntereses.isEmpty()) {
                labelVM.get(16).setText(mony + "0.00");
                labelVM.get(17).setText("0");
                labelVM.get(18).setText("0000000000");
                labelVM.get(20).setText("--/--/--");
            } else {
                interesCuotas = 0;
                intereses = 0.0;
                listIntereses.forEach(item -> {
                    intereses += item.getIntereses();
                    interesCuotas++;
                });
                labelVM.get(16).setText(mony + format.decimal(intereses));
                labelVM.get(17).setText(String.valueOf(interesCuotas));
                ticketIntereses = cliente.getTicketInteres();
                labelVM.get(18).setText(ticketIntereses);
                labelVM.get(20).setText(cliente.getInteresFecha());

            }
            HistorialPago(false);
        }
    }

    public void Pagos() {
        if (!txtFieldVM.get(8).getText().isEmpty()) {
            labelVM.get(21).setText("Ingrese el Pago");
            if (idReport == 0) {
                labelVM.get(21).setText("Seleccione un Cliente");
            } else {
                if (radioInteres.isSelected()) {
                    if (!txtFieldVM.get(9).getText().isEmpty()) {
                        int cantCuotas = Integer.valueOf(txtFieldVM.get(9).getText());
                        if (cantCuotas <= interesCuotas) {
                            if (!txtFieldVM.get(8).getText().isEmpty()) {
                                interesPago = format.rebuild(txtFieldVM.get(8).getText());
                                interesesPagos = Double.valueOf(format.decimal(interesesPagos));
                                if (interesPago >= interesesPagos) {
                                    cambio = interesPago - interesesPagos;
                                    labelVM.get(21).setText("Cambio para el Cliente "
                                            + mony + format.decimal(cambio));
                                    interesesCliente = intereses - interesesPagos;
                                    labelVM.get(16).setText(mony + format.decimal(interesesCliente));
                                } else {
                                    labelVM.get(21).setText("El Pago Debe de Ser "
                                            + mony + format.decimal(interesesPagos));
                                    interesesCliente = intereses - interesesPagos;
                                    labelVM.get(16).setText(mony + format.decimal(interesesCliente));
                                }
                            }
                        } else {
                            labelVM.get(21).setText("Cuotas Invalidas");
                        }
                    } else {
                        labelVM.get(21).setText("Ingrese el Numero de Cuotas");
                    }

                } else if (radioCuotas.isSelected()) {
                    if (!txtFieldVM.get(8).getText().isEmpty()) {
                        pago = format.rebuild(txtFieldVM.get(8).getText());
                        clientReport dataReport = ReporteCliente().stream()
                                .filter(u -> u.getIdclientReport() == idReport)
                                .collect(Collectors.toList()).get(0);
                        mensual = dataReport.getMensual();
                        if (pago > mensual) {
                            if (Objects.equals(pago, deudaActual) || pago > deudaActual) {
                                cambio = pago - deudaActual;
                                labelVM.get(21).setText("Cambio para el Cliente " + mony + format.decimal(cambio));
                                labelVM.get(11).setText(mony + "0.00");
                                deudaActual = 0.0;
                            } else {
                                cambio = pago - mensual;
                                labelVM.get(21).setText("Cambio para el Cliente " + mony + format.decimal(cambio));
                                deudaActualCliente = deudaActual - mensual;
                                labelVM.get(11).setText(mony + format.decimal(deudaActualCliente));
                            }
                        } else if (Objects.equals(pago, mensual)) {
                            deudaActualCliente = deudaActual - mensual;
                            labelVM.get(11).setText(mony + format.decimal(deudaActualCliente));
                        }
                    }
                }
            }
        } else {
            labelVM.get(21).setText("Ingrese el Pago");
            labelVM.get(11).setText(mony + format.decimal(deudaActual));
            labelVM.get(16).setText(mony + format.decimal(intereses));
        }
    }

    public void CuotasIntereses() {

        if (Objects.equals(idReport, 0)) {
            labelVM.get(21).setText("Seleccione un Cliente");
        } else {
            if (deudaActual > 0 || intereses > 0) {
                labelVM.get(21).setText("Ingrese el Pago");
                if (null != txtFieldVM.get(9)) {
                    if (txtFieldVM.get(9).getText().isEmpty()) {//9
                        labelVM.get(16).setText(mony + format.decimal(intereses));
                        labelVM.get(17).setText(String.valueOf(interesCuotas));
                        labelVM.get(19).setText(mony + "0.0");
                        labelVM.get(21).setText("Ingrese el Pago");
                    } else {
                        labelVM.get(19).setText(mony + "0.0");
                        int cantCuotas = Integer.valueOf(txtFieldVM.get(9).getText());//9
                        if (cantCuotas <= interesCuotas) {
                            labelVM.get(21).setText("Ingrese el Pago");
                            if (!listIntereses.isEmpty()) {
                                interesesPagos = 0.0;
                                for (int i = 0; i < cantCuotas; i++) {
                                    interesesPagos += listIntereses.get(i).getIntereses();
                                }
                                int cuotas = interesCuotas - cantCuotas;
                                double _intereses = intereses - interesesPagos;
                                labelVM.get(16).setText(mony + format.decimal(_intereses));
                                labelVM.get(17).setText(String.valueOf(cuotas));
                                labelVM.get(19).setText(mony + format.decimal(interesesPagos));
                            }
                        } else {
                            labelVM.get(21).setText("Cuotas Invalida");
                        }
                    }
                }

                Pagos();
            } else {
                labelVM.get(21).setText("El Cliente no Contiene Deuda");
            }

        }
    }

    public void EjecutarPago() throws SQLException, ParseException {
        final QueryRunner queryRunner = new QueryRunner(true);
        if (Objects.equals(idReport, 0)) {
            labelVM.get(21).setText("Seleccione un Cliente");
        } else {
            if (txtFieldVM.get(8).getText().isEmpty()) {
                labelVM.get(21).setText("Ingrese el Pago");
                txtFieldVM.get(8).requestFocus();
            } else {
                String fecha = new Calendario().getFecha();
                //Realizar consulta al usuario que inicie session
                var usuario = dataUsuario.getName() + " " + dataUsuario.getLastName();
                if (radioCuotas.isSelected()) {
                    if (!deuda.equals(0) || !deuda.equals(0.0)) {
                        if (pago >= mensual) {
                            try {
                                getConn().setAutoCommit(false);
                                String dateNow = new Calendario().addMes(1);
                                String fechaLimite = Objects.equals(deudaActual, 0) ? "--/--/--" : dateNow;
                                String ticket = codigos.codesTickets(ticketCuota);
                                String query1 = "INSERT INTO TPagosClient(deuda,saldo,pago,cambio,fecha,fechaLimite,ticket,idUsuario,usuario,idClient,fechaDeuda,mensual) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                                var dataReport = ReporteCliente().stream().filter(u -> u.getIdclientReport() == idclientReport)
                                        .collect(Collectors.toList()).get(0);
                                Object[] dataInsertTPagoClient = {
                                    deuda, deudaActualCliente, pago, cambio, formateador.parse(fecha),
                                    formateador.parse(fechaLimite), ticket, dataUsuario.getIdUsuario(), usuario, idclientReport,
                                    formateador.parse(dataReport.getFechaDeuda()), dataReport.getMensual()
                                };

                                queryRunner.insert(getConn(), query1, new ColumnListHandler(), dataInsertTPagoClient);
                                if (deudaActual.equals(pago)) {
                                    String query2 = "UPDATE clientReport SET deuda = ?,mensual = ?,"
                                            + "fechaDeuda = ?, deudaActual = ?, ultimoPago = ?,"
                                            + "cambio = ?, fechaPago = ?,ticket = ?,fechaLimite = ?"
                                            + " WHERE idClienReport = " + idReport;
                                    Object[] data2 = {
                                        0.0,
                                        0.0,
                                        "--/--/--",
                                        0.0,
                                        0.0,
                                        0.0,
                                        "--/--/--",
                                        "0000000000",
                                        "--/--/--",};
                                    queryRunner.update(getConn(), query2, data2);
                                } else {
                                    String query2 = "UPDATE clientReport SET deudaActual = ?,"
                                            + "ultimoPago = ?,Cambio = ?,fechaPago = ?,ticket = ?,"
                                            + "fechaLimite = ? WHERE idclientReport = " + idReport;
                                    Object[] actualizaColumnas = {
                                        deudaActualCliente, pago, cambio, fecha, ticket, fechaLimite,};
                                    queryRunner.update(getConn(), query2, actualizaColumnas);
                                }

                                Ticket Ticket1 = new Ticket();
                                Ticket1.textoCentro("Sistema de Ventas HERP");
                                Ticket1.TextoIzquierda("Direccion");
                                Ticket1.TextoIzquierda("Azua, Rep. Dom.");
                                Ticket1.TextoIzquierda("Tel: (829)750-4664");
                                Ticket1.LineasGuion();
                                Ticket1.textoCentro("FACTURA");
                                Ticket1.TextoIzquierda("Factura: " + ticket);
                                Ticket1.TextoIzquierda("Cliente: " + nameCliente);
                                Ticket1.TextoIzquierda("Fecha: " + fecha);
                                Ticket1.TextoIzquierda("Usuario: " + usuario);
                                Ticket1.LineasGuion();
                                Ticket1.textoCentro("Su Credito: " + mony + format.decimal(deuda));
                                Ticket1.textoExtremo("Cuotas por 12 Meses:", mony + format.decimal(mensual));
                                Ticket1.textoExtremo("Deuda Anterior: ", mony + format.decimal(deudaActual));
                                Ticket1.textoExtremo("Pago: ", mony + format.decimal(pago));
                                Ticket1.textoExtremo("Cambio: ", mony + format.decimal(cambio));
                                Ticket1.textoExtremo("Deuda Actual: ", mony + format.decimal(deudaActualCliente));
                                Ticket1.textoExtremo("Proximo Pago: ", fechaLimite);
                                Ticket1.textoCentro("HERP");
                                Ticket1.print();
                                getConn().commit();
                                restablecerReporte();

                            } catch (SQLException e) {
                                getConn().rollback();
                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    } else {
                        labelVM.get(21).setText("El Cliente No Contiene Deuda");
                    }
                } else if (radioInteres.isSelected()) {
                    if (!intereses.equals(0)) {
                        if (!txtFieldVM.get(9).getText().equals("")) {
                            try {
                                Integer cantCuotas = Integer.valueOf(txtFieldVM.get(9).getText());
                                if (cantCuotas <= interesCuotas) {
                                    if (interesPago >= interesesPagos) {
                                        getConn().setAutoCommit(false);
                                        if (!listIntereses.isEmpty()) {
                                            Object[] data1 = {true};
                                            for (int i = 0; i < cantCuotas; i++) {
                                                String query1 = "UPDATE TInteresesClientes SET cancelado = ? "
                                                        + "WHERE idTInteresesClientes= " + listIntereses.get(i).getIdTInteresesClientes() + " AND "
                                                        + "idClient= " + idclientReport;
                                                queryRunner.update(getConn(), query1, data1);
                                            }
                                            String ticket = codigos.codesTickets(ticketIntereses);
                                            String query2 = "INSERT INTO TPagosReporteInteresesCliente(intereses,"
                                                    + "pago,cambio,cuotas,fecha,ticket,idUsuario,usuario,idClient)"
                                                    + " VALUES(?,?,?,?,?,?,?,?,?)";
                                            Object[] data2 = {
                                                intereses, interesPago, cambio, cantCuotas, fecha,
                                                ticket, dataUsuario.getIdUsuario(), usuario, idclientReport
                                            };
                                            queryRunner.insert(getConn(), query2, new ColumnListHandler(), data2);
                                            String query3 = "UPDATE TReportesInteresCliente SET intereses = ?,"
                                                    + "pago = ?,cambio = ?,cuotas = ?,interesFecha = ?,"
                                                    + "ticketInteres = ? WHERE idTReportesInteresCliente= " + idReportInteres + " AND "
                                                    + "idClient= " + idclientReport;
                                            if (intereses.equals(interesPago)) {
                                                Object[] data3 = {
                                                    0.0,
                                                    0.0,
                                                    0.0,
                                                    0,
                                                    fecha,
                                                    "0000000000"
                                                };
                                                queryRunner.update(getConn(), query3, data3);
                                            } else {
                                                Object[] data3 = {
                                                    interesesCliente,
                                                    interesPago,
                                                    cambio,
                                                    cantCuotas,
                                                    fecha,
                                                    ticket
                                                };
                                                queryRunner.update(getConn(), query3, data3);
                                            }

                                            Ticket ticket1 = new Ticket();
                                            ticket1.textoCentro("Sistema de Facturacion HERP");
                                            ticket1.TextoIzquierda("Direccion");
                                            ticket1.TextoIzquierda("Azua, Rep. Dom.");
                                            ticket1.TextoIzquierda("Tel 8297504664");
                                            ticket1.LineasGuion();
                                            ticket1.textoCentro("FACTURA DE PAGOS DE INTERESES");
                                            ticket1.LineasGuion();
                                            ticket1.TextoIzquierda("Factura: " + ticket);
                                            ticket1.TextoIzquierda("Cliente: " + nameCliente);
                                            ticket1.TextoIzquierda("Fecha: " + fecha);
                                            ticket1.TextoIzquierda("Usuario: " + usuario);
                                            ticket1.LineasGuion();
                                            ticket1.textoCentro("Intereses " + mony + format.decimal(intereses));
                                            ticket1.textoExtremo("Cuotas", cantCuotas.toString());
                                            ticket1.textoExtremo("Pago:", mony + format.decimal(interesPago));
                                            ticket1.textoExtremo("Cambio", mony + format.decimal(cambio));
                                            ticket1.textoCentro("HERP");
                                            ticket1.print();
                                            getConn().commit();
                                            restablecerReporte();
                                        }
                                    } else {
                                        labelVM.get(21).setText("Su Pago Debe de Ser " + mony + format.decimal(interesesPagos));
                                    }
                                } else {
                                    labelVM.get(21).setText("Cuota Invalida");
                                }
                            } catch (Exception e) {
                                getConn().rollback();
                                JOptionPane.showMessageDialog(null, e);
                            }
                        } else {
                            labelVM.get(21).setText("Ingrese el Numero de Cuotas");
                        }
                    } else {
                        labelVM.get(21).setText("El Cliente no Contiene Deuda");
                    }
                }
            }
        }
    }

    public void HistorialPago(boolean filtrar) {
        try {
            _dateChooser1.setFormat(3);
            _dateChooser2.setFormat(3);
            String[] titulos = {
                "Id", "Deuda", "Saldo", "Pago",
                "Cambio", "Fecha", "Ticket"
            };
            model4 = new DefaultTableModel(null, titulos);
            var date1 = formateador.parse(_dateChooser1.getSelectedPeriodSet().toString());            
            var date2 = formateador.parse(_dateChooser2.getSelectedPeriodSet().toString());
            if (filtrar) {
                if (date2.after(date1) || date1.equals(date2)) {
                    List<TPagosClient> listPagos1 = new ArrayList<TPagosClient>();
                    var pagos = PagosClientes().stream().filter(u -> u.getIdClient() == idclientReport)
                            .collect(Collectors.toList());
                    for (TPagosClient pago : pagos) {
                        var date3 = formateador.parse(_dateChooser1.getSelectedPeriodSet().toString());
                        var date4 = pago.getFecha();
                        if (date3.equals(date4) || date4.after(date3)) {
                            listPagos1.add(pago);
                        }
                    }
                    for (TPagosClient pago : listPagos1) {
                        var date3 = formateador.parse(_dateChooser2.getSelectedPeriodSet().toString());
                        var date4 = pago.getFecha();
                        if (date3.equals(date4) || date3.after(date4)) {
                            Object[] registros = {
                                pago.getIdClient(),
                                pago.getDeuda(),
                                pago.getSaldo(),
                                pago.getPago(),
                                pago.getCambio(),
                                pago.getFecha(),
                                pago.getTicket()
                            };
                            model4.addRow(registros);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha final debe de ser mayor a la"
                            + "fecha inicial");
                }
            } else {
                var pagos = PagosClientes().stream().filter(u -> u.getIdClient() == idclientReport)
                        .collect(Collectors.toList());
                for (TPagosClient pago : pagos) {
                    Object[] registros = {
                        pago.getIdClient(),
                        pago.getDeuda(),
                        pago.getSaldo(),
                        pago.getPago(),
                        pago.getCambio(),
                        pago.getFecha(),
                        pago.getTicket()
                    };
                    model4.addRow(registros);
                }
            }

        } catch (ParseException ex) {
            var data = ex.getMessage();
        }
        _tablePagosCuotas.setModel(model4);
    }

    public final void restablecerReporte() {
        idReport = 0;
        interesCuotas = 0;
        intereses = 0.0;
        interesPago = 0.0;
        deudaActual = 0.0;
        interesesPagos = 0.0;
        cambio = 0.0;
        interesesCliente = 0.0;
        pago = 0.0;
        mensual = 0.0;
        deudaActualCliente = 0.0;
        ticketCuota = "0000000000";
        idclientReport = 0;
        ticketIntereses = "0000000000";
        labelVM.get(10).setText("Nombre del Cliente");
        labelVM.get(11).setText(mony + "0.00");
        labelVM.get(12).setText(mony + "0.00");
        labelVM.get(13).setText("00000000000");
        labelVM.get(14).setText("--/--/--");
        labelVM.get(15).setText(mony + "0.00");
        labelVM.get(16).setText(mony + "0.00");
        labelVM.get(17).setText("0");
        labelVM.get(18).setText("0000000000");
        labelVM.get(20).setText("--/--/--");
        labelVM.get(21).setText("Ingrese el Pago");
        txtFieldVM.get(8).setText("");
        txtFieldVM.get(9).setText("");
        listClienteReportes = clients();
        if (!listClienteReportes.isEmpty()) {
            pagerReports = new Pager<>(listClienteReportes, labelVM.get(9), regForPage);
        } else {
        }
        searchReport("");
    }

    // <editor-fold defaultstate="collapsed" desc="CODIGO DE DEUDAS Y REPORTES">
    public void GetReportesDeudas(String valor) {
        String[] titulo = {"Id", "Nid", "Nombre", "Apellido", "Email",
            "Telefono", "IdReport", "Fecha Limite"};
        model3 = new DefaultTableModel(null, titulo);
        int inicio = (numPage - 1) * regForPage;
        List<clientReport> list = new ArrayList<>();
        if (valor.equals("")) {
            list = ReportesClientes().stream().skip(inicio).limit(regForPage)
                    .collect(Collectors.toList());
            if (!list.isEmpty()) {
                for (clientReport item : list) {
                    if (!item.getFechaLimite().equals("--/--/--")) {
                        try {
                            Date date1 = formateador.parse(item.getFechaLimite());
                            Date date2 = formateador.parse(new Calendario().getFecha());
                            long time = date1.getTime() - date2.getTime();
                            long days = time / (1000 * 60 * 60 * 24);
                            //indica la cantidad de dias que les hacen falta a nuestro cliente para que se le venca la fecha limite
                            if (3 >= days) {
                                Object[] registros = {
                                    item.getIdClient(),
                                    item.getNid(),
                                    item.getName(),
                                    item.getLastName(),
                                    item.getEmail(),
                                    item.getPhone(),
                                    item.getIdclientReport(),
                                    item.getFechaLimite(),
                                    item.getFechaPago(),
                                    item.getMensual(),
                                    item.getDeuda()
                                };
                                model3.addRow(registros);
                                if (0 > days) {
                                    //cuando comento el metodo interesesMora se pone este en rojo
                                    InteresesMora(registros, days);
                                }
                            }
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }
            }
        } else {
            list = ReportesClientes().stream().filter(u -> u.getNid().startsWith(valor) || u.getName().startsWith(valor)
                    || u.getLastName().startsWith(valor)).skip(inicio).limit(regForPage).collect(Collectors.toList());
            if (!list.isEmpty()) {
                for (clientReport item : list) {
                    if (!item.getFechaLimite().equals("--/--/--")) {
                        try {
                            Date date1 = formateador.parse(item.getFechaLimite());
                            Date date2 = formateador.parse(new Calendario().getFecha());
                            long time = date1.getTime() - date2.getTime();
                            long days = time / (1000 * 60 * 60 * 24);
                            //indica la cantidad de dias que les hacen falta a nuestro cliente para que se le venca la fecha limite
                            if (3 >= days) {
                                Object[] registros = {
                                    item.getIdClient(),
                                    item.getNid(),
                                    item.getName(),
                                    item.getLastName(),
                                    item.getEmail(),
                                    item.getPhone(),
                                    item.getIdclientReport(),
                                    item.getFechaLimite(),
                                    item.getFechaPago(),
                                    item.getMensual(),
                                    item.getDeuda()
                                };
                                model3.addRow(registros);
                            }
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                }
            }
        }
        if (tableReportDeuda != null) {
            tableReportDeuda.setModel(model3);
            tableReportDeuda.setRowHeight(30);
            tableReportDeuda.getColumnModel().getColumn(0).setMaxWidth(0);
            tableReportDeuda.getColumnModel().getColumn(0).setMinWidth(0);
            tableReportDeuda.getColumnModel().getColumn(0).setPreferredWidth(0);
            tableReportDeuda.getColumnModel().getColumn(6).setMaxWidth(0);
            tableReportDeuda.getColumnModel().getColumn(6).setMinWidth(0);
            tableReportDeuda.getColumnModel().getColumn(6).setPreferredWidth(0);
        }

    }
    private long diasMoras;
    private DefaultTableModel _selectedCliente;
    private Date _fechaLimite;
    private Integer _idReporte;

    public void GetReporteDeuda(DefaultTableModel selected, int fila) {
        if (selected != null) {
            Calendar calendar = Calendar.getInstance();
            try {
                String nombre = (String) selected.getValueAt(fila, 2);
                String apellido = (String) selected.getValueAt(fila, 3);
                labelVM.get(22).setText(nombre + " " + apellido);
                _idReporte = (Integer) selected.getValueAt(fila, 6);
                _fechaLimite = formateador.parse((String) selected.getValueAt(fila, 7));
                calendar.setTime(_fechaLimite);
                _dateChooser.setSelectedDate(calendar);
                Date date = formateador.parse(new Calendario().getFecha());
                long time = _fechaLimite.getTime() - date.getTime();
                diasMoras = time / (1000 * 60 * 60 * 24);
                if (0 < diasMoras) {
                    labelVM.get(23).setText(diasMoras + " Dias Restantes");
                } else {
                    labelVM.get(23).setText("Se ha Sobrepasado " + Math.abs(diasMoras) + " Dias");
                }
                _selectedCliente = selected;
            } catch (Exception e) {
            }
        }
    }

    public void ExtenderDias() throws SQLException {
        if (_selectedCliente != null) {
            if (0 <= diasMoras) {
                if (_checkBoxDia.isSelected()) {
                    try {
                        _dateChooser.setFormat(3);
                        Date date1 = formateador.parse(new Calendario().getFecha());
                        Date date2 = _dateChooser.getSelectedDate().getTime();
                        if (date1.before(date2) && _fechaLimite.before(date2)) {
                            final QueryRunner queryRunner = new QueryRunner(true);
                            String query = "UPDATE clientReport SET fechaLimite = ?"
                                    + " WHERE idclientReport =" + _idReporte;
                            Object[] data = {
                                formateador.format(date2)
                            };
                            queryRunner.update(getConn(), query, data);
                            RestReportDeudas();
                        } else {
                            String fechaLimiteCliente = !date1.before(date2)
                                    ? new Calendario().getFecha() : formateador.format(_fechaLimite);
                            JOptionPane.showConfirmDialog(null,
                                    "Seleccione una fecha mayor a la fecha " + fechaLimiteCliente
                                    + " \npara extender los dias de pago al cliente", "Fecha para extender dias",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        }
                        _dateChooser.setFormat(0);
                    } catch (ParseException ex) {

                    }
                } else {
                    JOptionPane.showConfirmDialog(null,
                            "Seleccione la casilla para verificar que va extender \n"
                            + "los dias de pago al Cliente", "Extender Dias",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                }
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Al Cliente no se le puede extender los dias", "Extender Dias",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            }
        } else {
            JOptionPane.showConfirmDialog(null,
                    "Seleccione un Cliente de la Lista", "Extender Dias",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
    }

    private void InteresesMora(Object[] cliente, long days) {
        var interests = config().get(0).getIntereses();
        var idCliente = (Integer) cliente[0];
        var fechaPagoMora = (String) cliente[8];
        var mensual = (Double) cliente[9];
        if (!interests.equals(0.0)) {
            var clientesIntereses = InteresesCliente().stream().filter(i
                    -> i.getIdClient() == idCliente && i.getFechaInicial()
                    .equals(fechaPagoMora)).collect(Collectors.toList());

            var clientesIntereses2 = InteresesCliente().stream().filter(i
                    -> i.getIdClient() == idCliente && i.getFechaInicial()
                    .equals(fechaPagoMora) && i.getCancelado() == false)
                    .collect(Collectors.toList());
            long dias = Math.abs(days);
            Double porcentaje = interests / 100;
            Double moratorio = mensual * porcentaje;
            int countCliente = clientesIntereses.size();
            int countCliente2 = clientesIntereses2.size();
            int posCliente = countCliente2;
            posCliente--;
            if (countCliente2 == 0) {
                for (int i = 1; i <= dias; i++) {
                    insert(cliente, new TInteresesClientes(), i, false, moratorio);
                }
            } else {
                if (countCliente < dias) {
                    if (countCliente2 <= dias) {
                        long interesesDias = dias - countCliente;
                        for (int i = 1; i <= interesesDias; i++) {
                            insert(cliente, clientesIntereses2.get(posCliente), i, true, moratorio);
                        }
                    }
                }
            }
        }
    }

    private void insert(Object[] cliente, TInteresesClientes clientesInteres,
            int day, boolean value, Double interes) {
        Date fechaInsert = null;
        final QueryRunner queryRunner = new QueryRunner(true);
        var idCliente = (Integer) cliente[0];
        var fechaLimiteInsert = (String) cliente[7];
        var fechaPagoInsert = (String) cliente[8];
        var mensualInsert = (Double) cliente[9];
        var deudaInsert = (Double) cliente[10];
        try {
            getConn().setAutoCommit(false);
            if (value) {
                fechaInsert = formateador.parse(clientesInteres.getFecha());
            } else {
                fechaInsert = formateador.parse(fechaLimiteInsert);
            }
            var nowDate = new Calendario().addDay(fechaInsert, day);
            String query = "INSERT INTO TInteresesClientes(idClient,fechaInicial,deuda,mensual,"
                    + "intereses,cancelado,fecha) VALUES(?,?,?,?,?,?,?)";
            Object[] data = {
                idCliente,
                fechaPagoInsert,
                mensualInsert,
                deudaInsert,
                interes,
                false,
                nowDate,};
            queryRunner.insert(getConn(), query, new ColumnListHandler(), data);
            getConn().commit();
        } catch (Exception e) {
            try {
                getConn().rollback();
            } catch (SQLException ex) {

            }
            JOptionPane.showMessageDialog(null, e);

        }
    }

    private void RestReportDeudas() {
        diasMoras = 0;
        labelVM.get(22).setText("Cliente");
        labelVM.get(23).setText("Dias");
        Calendar calendarNew = new GregorianCalendar();
        _dateChooser.setSelectedDate(calendarNew);
        GetReportesDeudas("");
    }
    // </editor-fold>

    private List<client> listClients;
    private List<client> listClienteReportes;
    private List<clientReport> listReports;

    //hice un cambio en el nombre de la variable method por metodo
    public void pager(String metodo) {
        switch (metodo) {
            case "first":
                switch (section) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            numPage = pagerClients.firstPage();
                        }
                        break;
                    case 2:
                        if (!listClienteReportes.isEmpty()) {
                            numPage = pagerReports.firstPage();
                        }
                        break;
                }

                break;
            case "Previous":
                switch (section) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            numPage = pagerClients.previousPage();

                        }
                        break;
                    case 2:
                        if (!listClienteReportes.isEmpty()) {
                            numPage = pagerReports.previousPage();
                        }
                        break;
                }

                break;
            case "Next":
                switch (section) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            numPage = pagerClients.nextPage();
                        }
                        break;
                    case 2:
                        if (!listClienteReportes.isEmpty()) {
                            numPage = pagerReports.nextPage();
                        }
                        break;
                }
                break;
            case "Last":
                switch (section) {
                    case 1:
                        if (!listClients.isEmpty()) {
                            numPage = pagerClients.last();
                        }
                        break;
                    case 2:
                        if (!listClienteReportes.isEmpty()) {
                            numPage = pagerReports.last();
                        }
                        break;
                }
                break;
        }

        switch (section) {
            case 1:
                searchClients("");
                break;
            case 2:
                searchReport("");
                break;
        }
    }

    public void RegistrationPages() {
        Number boxNumber = (Number) spinnerPage.getValue();
        regForPage = boxNumber.intValue();
        switch (section) {
            case 1:
                if (!listClients.isEmpty()) {
                    pagerClients = new Pager<>(listClients, labelVM.get(9), regForPage);
                }
                searchClients("");
                break;
            case 2:
                if (!listClienteReportes.isEmpty()) {
                    pagerReports = new Pager<>(listClienteReportes, labelVM.get(9), regForPage);
                }
                searchReport("");
                break;
        }

    }

}
