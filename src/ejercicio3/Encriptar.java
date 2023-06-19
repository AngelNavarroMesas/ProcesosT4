package ejercicio3;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Scanner;

public class Encriptar {
    public static final String MENSAJE =leerFichero();

    public static void main(String[] args) {

        try {
            PublicKey clavePublica = KeyManager.getClavePublica();
            PrivateKey clavePrivada = KeyManager.getClavePrivada();
            byte[] mensaje = MENSAJE.getBytes(StandardCharsets.UTF_8);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clavePrivada);

            byte[] mensajeCifrado1 = cipher.doFinal(mensaje);
            byte[] mensajeCifrado2 = cifrarContenido(mensajeCifrado1, clavePublica);

            insertarEnFichero(Base64.getEncoder().encodeToString(mensajeCifrado2));
            System.out.println(Base64.getEncoder().encodeToString(mensajeCifrado2));

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] cifrarContenido(byte[] contenido, Key clave) throws Exception {
        Cipher cifrador = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        cifrador.init(Cipher.ENCRYPT_MODE, clave);

        int tamanoBloque = (((RSAPublicKey)clave).getModulus().bitLength() + 7) / 8 - 11;
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();

        int offset = 0;
        while (offset < contenido.length) {
            int tamanoBloqueActual = Math.min(tamanoBloque, contenido.length - offset);
            byte[] bloqueCifrado = cifrador.doFinal(contenido, offset, tamanoBloqueActual);
            bufferSalida.write(bloqueCifrado);
            offset += tamanoBloqueActual;
        }

        return bufferSalida.toByteArray();
    }

    public static void insertarEnFichero(String mensaje){
        try {
            String filePath = "C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio3\\MensajeCifrado.txt";
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mensaje);
            bw.newLine();
            bw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static String leerFichero() {
        BufferedReader br = null;
        String contenido = "";

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio3\\Mensaje"));
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
