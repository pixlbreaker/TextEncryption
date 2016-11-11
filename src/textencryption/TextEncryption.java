package textencryption;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class TextEncryption {

    public static void main(String[] args) {
        
        // Scanner and gets text from the user
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a phrase to be encrypted: ");
        String textString = in.nextLine();
        
        // Try-Catch
        try {

            // Generates key
            KeyGenerator keygenerator = KeyGenerator.getInstance("Blowfish");
            SecretKey myDesKey = keygenerator.generateKey();

            // Generates cipher
            Cipher desCipher;
            desCipher = Cipher.getInstance("Blowfish");

            // Byte Array from the text
            byte[] text = textString.getBytes("UTF8");
            
            // Enctypts Text
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] textEncrypted = desCipher.doFinal(text);
            String s = new String(textEncrypted);
            System.out.println(s);

            // Decrypts text
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);
            s = new String(textDecrypted);
            System.out.println(s);
            
            // Copies to clipboard
            copy(s);
        } catch (Exception e) {
            System.out.println("Exception");
            System.err.println(e.toString());
        }
    }
    
    // Takes text and adds it to the clipboard
    public static void copy(String text){
        Clipboard clipboard = getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
        
    }
    
    // Generates clipboard
    private static Clipboard getSystemClipboard(){
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();
        return systemClipboard;
    }

}
