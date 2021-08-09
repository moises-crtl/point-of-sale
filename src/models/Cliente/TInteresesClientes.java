package models.Cliente;

/**
 *
 * @author ioriyagamy
 */
public class TInteresesClientes {

    private int idTInteresesClientes;
    private String fechaInicial;
    private Double deuda;
    private Double mensual;
    private Double intereses;
    private String fecha;
    private Boolean cancelado;
    private int idClient;

    public TInteresesClientes() {
    }

    public int getIdTInteresesClientes() {
        return idTInteresesClientes;
    }

    public void setIdTInteresesClientes(int idTInteresesClientes) {
        this.idTInteresesClientes = idTInteresesClientes;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Double getDeuda() {
        return deuda;
    }

    public void setDeuda(Double deuda) {
        this.deuda = deuda;
    }

    public Double getMensual() {
        return mensual;
    }

    public void setMensual(Double mensual) {
        this.mensual = mensual;
    }

    public Double getIntereses() {
        return intereses;
    }

    public void setIntereses(Double intereses) {
        this.intereses = intereses;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
