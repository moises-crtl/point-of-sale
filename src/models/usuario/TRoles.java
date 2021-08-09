package models.usuario;

/**
 *
 * @author ioriyagamy
 */
public class TRoles {

    private int idTRole;
    private String role;

    public TRoles() {
    }

    public int getIdTRole() {
        return idTRole;
    }

    public void setIdTRole(int idTRole) {
        this.idTRole = idTRole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
