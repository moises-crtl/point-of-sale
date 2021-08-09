package conexion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Cliente.*;
import models.tConfiguration;
import models.ordenador.TOrdenadores;
import models.usuario.TRoles;
import models.usuario.TUsuarios;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author ioriyagamy
 */
public class Consult extends Conexion {

    private final QueryRunner queryRunner = new QueryRunner();

    public List<client> clients() {
        List<client> clientConsult = new ArrayList<>();
        try {
            clientConsult = (List<client>) queryRunner.query(getConn(), "SELECT * FROM client",
                    new BeanListHandler<>(client.class));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }

        return clientConsult;
    }

    public List<clientReport> reportClient(int idClient) {
        String where = "WHERE client.idClient =" + idClient;
        List<clientReport> reports = new ArrayList<>();
        String condicion1 = " client.idClient = clientReport.idClientReport ";
        String condicion2 = " client.idClient = TReportesInteresCliente.idClient ";

        String campos = " client.idClient,client.nid,client.name,client.lastName,"
                + "clientReport.idClientReport,clientReport.deudaActual,clientReport.fechaDeuda,"
                + "clientReport.ultimoPago,clientReport.fechaPago,clientReport.ticket,"
                + "clientReport.deuda,clientReport.mensual,clientReport.cambio,"
                + "clientReport.fechaLimite,TReportesInteresCliente.intereses,TReportesInteresCliente.pago,"
                + "TReportesInteresCliente.cambio,TReportesInteresCliente.cuotas,"
                + "TReportesInteresCliente.interesFecha,TReportesInteresCliente.ticketInteres,TReportesInteresCliente.idTReportesInteresCliente";
        try {
            reports = (List<clientReport>) queryRunner.query(getConn(), "SELECT" + campos + " FROM client Left Join clientReport ON " 
                    + condicion1 + "Left Join TReportesInteresCliente ON " + condicion2 + where, new BeanListHandler(clientReport.class));


        } catch (SQLException e) {
            System.out.println("Error : " + e);
        }
        return reports;
    }

    public List<tConfiguration> config() {
        List<tConfiguration> config = new ArrayList<>();
        try {
            config = (List<tConfiguration>) queryRunner.query(getConn(), "SELECT * FROM tConfiguration",
                    new BeanListHandler(tConfiguration.class));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }

        return config;
    }
    
    public List<TInteresesClientes> InteresesCliente(){
        
        List<TInteresesClientes> intereses = new ArrayList();
        try {
            intereses = (List<TInteresesClientes>) queryRunner.query(getConn(), "SELECT * FROM TInteresesClientes"
                    , new BeanListHandler(TInteresesClientes.class));
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        
        return intereses;
    }
    
    public List<clientReport> ReporteCliente(){
        List<clientReport> reporte = new ArrayList();
        try {
            reporte = (List<clientReport>) queryRunner.query(getConn(), "SELECT * FROM clientReport",
                    new BeanListHandler(clientReport.class));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        
        return reporte;
    }
    
    public List<clientReport> ReportesClientes(){
        String where = "";
        List<clientReport> reportes = new ArrayList<>();
        String condicion1 = " client.idClient = clientReport.idClient ";
        String campos = " client.idClient,client.nid,client.name,client.lastName,"
                + "client.phone,client.email,client.address,clientReport.idclientReport,"
                + "clientReport.deudaActual,clientReport.fechaDeuda,clientReport.ultimoPago,"
                + "clientReport.fechaPago,clientReport.ticket,clientReport.deuda,clientReport.mensual,"
                + "clientReport.cambio,clientReport.fechaLimite";
        try {
            reportes = (List<clientReport>) queryRunner.query(getConn(), "SELECT" + campos + " FROM client "
                    + "Inner Join clientReport ON" + condicion1+ where, new BeanListHandler<>(clientReport.class));
        } catch (Exception e) {
            System.out.println("Error : " + e); 
        }
        
        return reportes;
    }
    
    public List<TUsuarios> Usuarios(){
        List<TUsuarios> usuarios = new ArrayList<>();
        try {
            usuarios = (List<TUsuarios>) queryRunner.query(getConn(), "SELECT * FROM TUsuarios",
                    new BeanListHandler(TUsuarios.class));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        
        return usuarios;
    }
    
    public List<TOrdenadores> Ordenadores(){
        List<TOrdenadores> ordenadores = new ArrayList<>();
        try {
            ordenadores = (List<TOrdenadores>) queryRunner.query(getConn(), "SELECT * FROM TOrdenadores",
                    new BeanListHandler(TOrdenadores.class));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error : " + e);
        }
        return ordenadores;
    }
    
    public List<TRoles> roles() {
        List<TRoles> role = new ArrayList<>();
        try {
            role = (List<TRoles>) queryRunner.query(getConn(), "SELECT * FROM TRoles",
                    new BeanListHandler<>(TRoles.class));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }

        return role;
    }
    
    public List<TPagosClient> PagosClientes(){
        List<TPagosClient> pagos = new ArrayList<>();
        try {            
            pagos = (List<TPagosClient>) queryRunner.query(getConn(), "SELECT * FROM TPagosClient",
                    new BeanListHandler(TPagosClient.class));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error : " + ex);
        }
        return pagos;
    }
    
    
}