package models;


/**
 *
 * @author ioriyagamy
 */
public class tConfiguration {

    private int idtConfiguration;
    private String typeMoney;
    private Double intereses;

    public tConfiguration() {

    }

    public int getIdtConfiguration() {
        return idtConfiguration;
    }

    public void setIdtConfiguration(int idtConfiguration) {
        this.idtConfiguration = idtConfiguration;
    }

    public String getTypeMoney() {
        return typeMoney;
    }

    public void setTypeMoney(String typeMoney) {
        this.typeMoney = typeMoney;
    }

    public Double getIntereses() {
        return intereses;
    }

    public void setIntereses(Double intereses) {
        this.intereses = intereses;
    }
}
