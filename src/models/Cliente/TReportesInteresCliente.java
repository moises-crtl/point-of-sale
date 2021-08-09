package models.Cliente;

/**
 *
 * @author ioriyagamy
 */
public class TReportesInteresCliente {

    private int idTReportesInteresCliente;
    private double intereses;
    private double pago;
    private double cambio;
    private int cuotas;
    private String interesFecha;
    private String ticketInteres;
    private int idClient;

    public TReportesInteresCliente() {
    }

    public int getIdTReportesInteresCliente() {
        return idTReportesInteresCliente;
    }

    public void setIdTReportesInteresCliente(int idTReportesInteresCliente) {
        this.idTReportesInteresCliente = idTReportesInteresCliente;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public double getCambio() {
        return cambio;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getInteresFecha() {
        return interesFecha;
    }

    public void setInteresFecha(String interesFecha) {
        this.interesFecha = interesFecha;
    }

    public String getTicketInteres() {
        return ticketInteres;
    }

    public void setTicketInteres(String ticketInteres) {
        this.ticketInteres = ticketInteres;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
