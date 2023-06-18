package ejercicio1;

import java.security.*;

public class Hash {
    public static byte[] getDigest(byte[] contraseña,String algoritmo){
        MessageDigest encriptado;
        byte[] mensajeEncriptado = new byte[0];
        try {
            encriptado = MessageDigest.getInstance(algoritmo);
            encriptado.reset();
            encriptado.update(contraseña);
            mensajeEncriptado = encriptado.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }finally {
            return  mensajeEncriptado;
        }
    }
    public static boolean compararResumenes(byte[] mensaje1, byte[] mensaje2){
        boolean iguales = false;
        if (MessageDigest.isEqual(mensaje1, mensaje2)){
            iguales = true;
        }
        return iguales;
    }
}
