package libreria;

/**
 *
 * @author ioriyagamy
 */
public class Codigos {
    
    private String tickets = null;
    
    public String codesTickets(String ticket){
        if(ticket.equals("9999999999")){
            tickets = "0000000001";
        }else{
            int numero = Integer.valueOf(ticket);
            numero ++;
            tickets = String.format("%010d", numero);
        }
        
        return tickets;
    }
}
