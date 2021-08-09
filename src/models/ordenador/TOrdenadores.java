package models.ordenador;

import java.util.Date;

/**
 *
 * @author ioriyagamy
 */
public class TOrdenadores {
    
    public int idTOrdenadores;
    public String ordenador;
    public boolean is_active;
    public String usuario;
    public Date inFecha;
    public Date outFecha;
    public int idUsuario;

    public TOrdenadores() {
    }

    public int getIdTOrdenadores() {
        return idTOrdenadores;
    }

    public void setIdTOrdenadores(int idTOrdenadores) {
        this.idTOrdenadores = idTOrdenadores;
    }

    public String getOrdenador() {
        return ordenador;
    }

    public void setOrdenador(String ordenador) {
        this.ordenador = ordenador;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getInFecha() {
        return inFecha;
    }

    public void setInFecha(Date inFecha) {
        this.inFecha = inFecha;
    }

    public Date getOutFecha() {
        return outFecha;
    }

    public void setOutFecha(Date outFecha) {
        this.outFecha = outFecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
