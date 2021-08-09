package models.Cliente;

/**
 *
 * @author ioriyagamy
 */
public class TPagosReporteInteresesCliente {

    private int idTPagosReporteInteresesCliente;
    private Double intereses;
    private Double pago;
    private Double cambio;
    private int cuotas;
    private String fecha;
    private String ticket;
    private int idUsuario;
    private String usuario;
    private int idClient;

    public TPagosReporteInteresesCliente() {
    }

    public int getIdTPagosReporteInteresesCliente() {
        return idTPagosReporteInteresesCliente;
    }

    public void setIdTPagosReporteInteresesCliente(int idTPagosReporteInteresesCliente) {
        this.idTPagosReporteInteresesCliente = idTPagosReporteInteresesCliente;
    }

    public Double getIntereses() {
        return intereses;
    }

    public void setIntereses(Double intereses) {
        this.intereses = intereses;
    }

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    
    
}
