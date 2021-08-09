package libreria;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ioriyagamy
 */
public class UploadImage extends javax.swing.JFrame {

    private File file;
    private JFileChooser openFile;
    private static String urlOrigen = null;
    private static byte[] imageByte = null;

    public static byte[] getImageByte() {
        return imageByte;
    }

    public void loadImage(JLabel label) {
        openFile = new JFileChooser();
        openFile.setFileFilter(new FileNameExtensionFilter("Archivo de Imagen", "jpg", "png", "gif"));
        int request = openFile.showOpenDialog(this);
        if (request == JFileChooser.APPROVE_OPTION) {
            file = openFile.getSelectedFile();
            urlOrigen = file.getAbsolutePath();
            Image image = getToolkit().getImage(urlOrigen);
            ImageIcon foto = new ImageIcon(image);
            Icon icono = new ImageIcon(foto.getImage().getScaledInstance(label.getWidth(),
                    label.getHeight(), Image.SCALE_DEFAULT));
            label.setIcon(icono);
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                imageByte = baos.toByteArray();
            } catch (IOException ex) {

            }
        }
    }

    public byte[] getTransPhoto(JLabel label) {
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            Icon iconUploadImaga = label.getIcon();
            BufferedImage bufferedImage = new BufferedImage(iconUploadImaga.getIconWidth(), iconUploadImaga.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
            byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
        }

        return byteArrayOutputStream.toByteArray();
    }

    public void byteImage(JLabel label, byte[] imagePhoto) {

        try {
            Image Photo;
            BufferedImage image;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imagePhoto);
            image = ImageIO.read(byteArrayInputStream);
            Photo = new ImageIcon(image).getImage();
            Photo = Photo.getScaledInstance(42, 45, 1);
            label.setIcon(new ImageIcon(Photo));

//            ImageIcon foto;
//            BufferedImage image;
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imagePhoto);
//            image = ImageIO.read(byteArrayInputStream);
//            foto = new ImageIcon(image);
//            Icon icono = new ImageIcon(foto.getImage().getScaledInstance(label.getWidth(),
//                    label.getHeight(), Image.SCALE_DEFAULT));
//            label.setIcon(icono);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
