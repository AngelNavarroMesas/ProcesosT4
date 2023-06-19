package ejercicio3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Descifrar {
    public static final String MENSAJE_CIFRADO = leerFichero() ;

    public static void main(String[] args) {

        try {
            PrivateKey clavePrivada = KeyManager.getClavePrivada();

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher.init(Cipher.DECRYPT_MODE, clavePrivada);

            byte[] mensajeCifrado = Base64.getDecoder().decode(MENSAJE_CIFRADO);

            byte[] mensaje = cipher.doFinal(mensajeCifrado);

            System.out.println(new String(mensaje));

        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el padding seleccionado");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave introducida no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque utilizado no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding utilizado es erróneo");
            e.printStackTrace();
        }
    }

    public static String leerFichero() {
        BufferedReader br = null;
        String contenido = "";

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio3\\MensajeCifrado.txt"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                contenido = sc.nextLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return contenido;
    }


}
