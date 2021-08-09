package libreria;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 *
 * @author ioriyagamy
 */
public class Encriptar {
    
     private final static String alg = "AES";
     private final static String cI = "AES/CBC/PKCS5padding";
     private static String key = "92AE31A79FEEB2A3";
     private static String iV = "0123456789ABCDEF";
     
     public static String encript(String clearText) throws Exception{
         Cipher cipher = Cipher.getInstance(cI);
         SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), alg);
         IvParameterSpec ivParameterSpec = new IvParameterSpec(iV.getBytes());
         cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
         byte[] encrypted = cipher.doFinal(clearText.getBytes());
         return new String(encodeBase64(encrypted));
     }
     
     public static String decrypt(String encrypted) throws Exception{
         Cipher cipher = Cipher.getInstance(cI);
         SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), alg);
         IvParameterSpec ivParameterSpec = new IvParameterSpec(iV.getBytes());
         byte[] enc = decodeBase64(encrypted);
         cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
         byte[] decrypted = cipher.doFinal(enc);
         return  new String(decrypted);
     }
    
}
