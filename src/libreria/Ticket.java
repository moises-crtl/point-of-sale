package libreria;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import javax.swing.JOptionPane;

/**
 *
 * @author ioriyagamy
 */
public class Ticket {

    private final StringBuilder lineas = new StringBuilder();
    private final int maxCaracter = 40;
    private int stop;
    private final FormatDecimal format = new FormatDecimal();

    public String LineasGuion() {
        String linea = "";
        for (int i = 0; i < maxCaracter; i++) {
            linea += "-";
        }

        return lineas.append(linea).append("\n").toString();
    }

    public String LineaAsteriscos() {
        String asteriscos = "";
        for (int i = 0; i < maxCaracter; i++) {
            asteriscos += "*";
        }
        
        return lineas.append(asteriscos).append("\n").toString();
    }

    public String LineaIgual() {
        String igual = "";
        for (int i = 0; i < maxCaracter; i++) {
            igual += "=";
        }

        return lineas.append(igual).append("\n").toString();
    }

    public void EncabezadoVenta(String columnas) {
        lineas.append(columnas).append("\n");
    }

    public void TextoIzquierda(String texto) {
        if (texto.length() > maxCaracter) {
            int caracterActual = 0;
            for (int i = texto.length(); i > maxCaracter; i -= maxCaracter) {
                lineas.append(texto.substring(caracterActual, maxCaracter)).append("\n");
                caracterActual += maxCaracter;
            }

            lineas.append(texto.substring(caracterActual, texto.length() - caracterActual)).append("\n");
        } else {
            lineas.append(texto).append("\n");
        }
    }

    public void textoDerecha(String texto) {
        if (texto.length() > maxCaracter) {
            int caracterActual = 0;
            for (int i = texto.length(); i > maxCaracter; i -= maxCaracter) {
                lineas.append(texto.substring(caracterActual, maxCaracter)).append("\n");
                caracterActual += maxCaracter;
            }

            String espacios = "";
            for (int i = 0; i < (maxCaracter - texto.substring(caracterActual,
                    texto.length() - caracterActual).length()); i++) {
                espacios += " ";
            }

            lineas.append(espacios).append(texto.substring(caracterActual, texto.length() - caracterActual)).append("\n");
        } else {
            String espacios = "";
            for (int i = 0; i < (maxCaracter - texto.length()); i++) {
                espacios += " ";
            }

            lineas.append(espacios).append(texto).append("\n");
        }
    }

    public void textoCentro(String texto) {
        if (texto.length() > maxCaracter) {
            int caracterActual = 0;
            for (int i = texto.length(); i > maxCaracter; i -= maxCaracter) {
                lineas.append(texto.substring(caracterActual, maxCaracter)).append("\n");
                caracterActual += maxCaracter;
            }

            String espacios = "";
            int centrar = (maxCaracter - texto.substring(caracterActual, texto.length() - caracterActual).length()) / 2;
            for (int i = 0; i < centrar; i++) {
                espacios += " ";
            }

            lineas.append(espacios).append(texto.substring(caracterActual, texto.length() - caracterActual)).append("\n");
        } else {
            String espacios = "";
            int central = (maxCaracter - texto.length()) / 2;
            for (int i = 0; i < central; i++) {
                espacios += " ";
            }

            lineas.append(espacios).append(texto).append("\n");
        }
    }

    public void textoExtremo(String izquierdo, String derecho) {
        String derecho1, izquierdo1, completo = "", espacio = "";
        if (izquierdo.length() > 18) {
            stop = izquierdo.length() - 18;
            izquierdo1 = izquierdo.substring(stop, 19);
        } else {
            izquierdo1 = izquierdo;
        }

        completo = izquierdo1;
        if (derecho.length() > 20) {
            stop = derecho.length() - 20;
            derecho1 = derecho.substring(stop, 20);
        } else {
            derecho1 = derecho;
        }

        int numEspacios = maxCaracter - (izquierdo1.length() + derecho1.length());
        for (int i = 0; i < numEspacios; i++) {
            espacio += " ";
        }

        completo += espacio + derecho;
        lineas.append(completo).append("\n");
    }

    public void agregarTotales(String texto, double total) {
        String resumen, valor, completo = "", espacio = "";
        if (texto.length() > 25) {
            stop = texto.length() - 25;
            resumen = texto.substring(stop, 25);
        } else {
            resumen = texto;
        }
        completo = resumen;
        valor = format.decimal(total);
        int numEspacios = maxCaracter - (resumen.length() + valor.length());
        for (int i = 0; i < numEspacios; i++) {
            espacio += " ";
        }
        completo += espacio + valor;
        lineas.append(completo).append("\n");
    }

    public void AgregarArticulo(String articulo, Integer cantidad, Double precio, Double importe) {
        if (cantidad.toString().length() <= 5 && precio.toString().length() <= 7
                && importe.toString().length() <= 8) {
            String elemento = "", espacios = "";
            boolean bandera = false;
            int numEspacios = 0;
            if (articulo.length() > 20) {
                //Colocar la cantidad a la Derecha
                numEspacios = (5 - cantidad.toString().length());
                espacios = "";
                for (int i = 0; i < numEspacios; i++) {
                    espacios = " ";
                }

                elemento += espacios + cantidad.toString();
                numEspacios = (7 - precio.toString().length());
                espacios = "";
                for (int i = 0; i < numEspacios; i++) {
                    espacios += " ";
                }
                elemento += espacios + precio.toString();
                //Colocamos el Importe a la Derecha
                numEspacios = (8 - importe.toString().length());
                espacios = "";
                for (int i = 0; i < numEspacios; i++) {
                    espacios += " ";
                }
                elemento += espacios + importe.toString();
                int caracterActual = 0;
                for (int i = articulo.length(); i > 20; i -= 20) {
                    if (bandera) {
                        lineas.append(articulo.substring(caracterActual, 20)).append("\n");
                    } else {
                        lineas.append(articulo.substring(caracterActual, 20)).append(elemento).append("\n");
                        bandera = true;
                    }
                }
                lineas.append(articulo.substring(caracterActual, articulo.length() - caracterActual)).append("\n");
            } else {
                for (int i = 0; i < (20 - articulo.length()); i++) {
                    espacios += " ";//Agregar espacios para poner el valor de Articulo
                }
                elemento = articulo + espacios;
                //Colocar Cantidad a la Derecha
                numEspacios = (5 - cantidad.toString().length());
                espacios = "";
                for (int i = 0; i < numEspacios; i++) {
                    espacios += " ";//Agregar espacios para poner el valor cantidad
                }
                elemento += espacios + cantidad.toString();
                //Colocamos el espacio a la derecha
                numEspacios = (7 - precio.toString().length());
                espacios = "";
                for (int i = 0; i < numEspacios; i++) {
                    espacios += " ";//Agregar espacios para poner el valor de Precio
                }
                elemento += espacios + precio.toString();
                //Colocamos el Importe a la Derecha
                numEspacios = (8 - importe.toString().length());
                espacios = "";
                for (int i = 0; i < numEspacios; i++) {
                    espacios += " ";//Agregar espacios para poner el valor Importe
                }
                elemento += espacios + importe.toString();
                lineas.append(elemento).append("\n");
            }
        } else {
            lineas.append("Los valores ingresados para esta fila").append("\n");
            lineas.append("Superan las columnas soportadas por esta ").append("\n");
        }
    }
    
    public void print(){
        //Especificamos el tipo de dato a imprimir
        //Tipo: byte; Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        /*
            Aca obtenemos el servicio de impresion por default
            Sino quieres ver el dialogo de seleccionar impresora  usa esto
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
         */

        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, printRequestAttributeSet);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, printRequestAttributeSet);
        
        //Crearmos un arreglo de tipo byte
        byte[] bytes;
        /*
        Aca convertimos el String(cuerpo del Ticket) a byte tal como
        lo maneja la impresora (mas bien ticketera :p)
        */
        bytes =  this.lineas.toString().getBytes();
        Doc doc = new SimpleDoc(bytes, flavor, null);
        DocPrintJob job = service.createPrintJob();
        
        try {
            //El metodo print imprime
            job.print(doc, null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Imprimir: " + e.getMessage());
        }
    }
}
