package models.Cliente;

/**
 *
 * @author ioriyagamy
 */
public class clientReport extends client{
    
    private int idclientReport;
    private double deudaActual;
    private double deuda;
    private double mensual;
    private double cambio;
    private String fechaDeuda;
    private double ultimoPago;
    private String fechaPago;
    private String ticket;
    private String fechaLimite;
    private int idClient;

    public clientReport() {
    }

    
    public int getIdclientReport() {
        return idclientReport;
    }

    public void setIdClientReport(int idclientReport) {
        this.idclientReport = idclientReport;
    }

    public double getDeudaActual() {
        return deudaActual;
    }

    public void setDeudaActual(double deudaActual) {
        this.deudaActual = deudaActual;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

    public double getMensual() {
        return mensual;
    }

    public void setMensual(double mensual) {
        this.mensual = mensual;
    }

    
    @Override
    public double getCambio() {
        return cambio;
    }

    @Override
    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public String getFechaDeuda() {
        return fechaDeuda;
    }

    public void setFechaDeuda(String fechaDeuda) {
        this.fechaDeuda = fechaDeuda;
    }

    public double getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(double ultimoPago) {
        this.ultimoPago = ultimoPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Override
    public int getIdClient() {
        return idClient;
    }

    @Override
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }    
}
