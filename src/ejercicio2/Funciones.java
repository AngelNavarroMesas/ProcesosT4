package ejercicio2;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
public class Funciones {

    public static Key obtenerClave(String pass){

        Key clave = new SecretKeySpec(pass.getBytes(), 0, pass.length(), "AES");


        return clave;
    }

    public static void cifrar(Key clave){

        Scanner sc = new Scanner(System.in);

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, clave);

            System.out.println("Introduzca un mensaje");
            String mensaje = sc.nextLine();

            byte[] cipherText = cipher.doFinal(mensaje.getBytes());

            escribirFichero(Base64.getEncoder().encodeToString(cipherText));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }


    }

    public static void descifrar(Key clave){

        String texto = leerFichero();
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, clave);

            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(texto));

            System.out.println(new String(plainText));

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirFichero(String mensaje){
        try {
            String filePath = "C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio2\\cifrados.txt";
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
        String contenido="";

        try {
            br = new BufferedReader(new FileReader("C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio2\\cifrados.txt"));
            Scanner sc = new Scanner(br);

            while (sc.hasNext()) {
                contenido = sc.nextLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
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
