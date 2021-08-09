package models.Cliente;

/**
 *
 * @author ioriyagamy
 */
public class ClienteModel {
    
    private int idClienteModel;
    private int nid;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private int idclientReport;
    private String fechaLimite;

    public ClienteModel(int idClienteModel, int nid, String name, String lastName, String email, String phone, int idclientReport, String fechaLimite) {
        this.idClienteModel = idClienteModel;
        this.nid = nid;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.idclientReport = idclientReport;
        this.fechaLimite = fechaLimite;
    }
    

    public int getIdClienteModel() {
        return idClienteModel;
    }

    public void setIdClienteModel(int idClienteModel) {
        this.idClienteModel = idClienteModel;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdclientReport() {
        return idclientReport;
    }

    public void setIdclientReport(int idclientReport) {
        this.idclientReport = idclientReport;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }    
}
