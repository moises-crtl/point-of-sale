package models.Cliente;

import java.util.Date;

/**
 *
 * @author ioriyagamy
 */
public class TPagosClient {

    private int idTPagosClient;
    private Double deuda;
    private Double saldo;
    private Double pago;
    private Double cambio;
    private Date fecha;
    private Date fechaLimite;
    private String ticket;
    private int idUsuario;
    private String usuario;
    private int idClient;
    private Date fechaDeuda;
    private Double mensual;

    public TPagosClient() {
        
    }

    public int getIdTPagosClient() {
        return idTPagosClient;
    }

    public void setIdTPagosClient(int idTPagosClient) {
        this.idTPagosClient = idTPagosClient;
    }

    public Double getDeuda() {
        return deuda;
    }

    public void setDeuda(Double deuda) {
        this.deuda = deuda;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
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

    public Date getFechaDeuda() {
        return fechaDeuda;
    }

    public void setFechaDeuda(Date fechaDeuda) {
        this.fechaDeuda = fechaDeuda;
    }

    public Double getMensual() {
        return mensual;
    }

    public void setMensual(Double mensual) {
        this.mensual = mensual;
    }
}
