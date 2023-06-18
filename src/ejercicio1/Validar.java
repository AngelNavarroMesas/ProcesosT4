package ejercicio1;

import java.io.*;
import java.math.*;
import java.util.*;

public class Validar {
    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        String usu, contraseña, resumen;
        String cuenta;
        byte[] cuentaRecogida, cuentaEncriptada;
        System.out.println("Introduzca su usuario");
        usu = sc.next();
        sc.nextLine();
        System.out.println("Introduzca su contraseña");
        contraseña = sc.nextLine();
        cuenta = usu + contraseña;
        cuentaRecogida = cuenta.getBytes();
        cuentaEncriptada = Hash.getDigest(cuentaRecogida, "SHA-256");
        resumen = String.format("%064x", new BigInteger(1, cuentaEncriptada));
        leerFichero(resumen);
        sc.close();
    }
    public static String leerFichero(String mensajeEncriptado) {
        BufferedReader br;
        String mensaje = "";
        byte[] mensaje1 = mensajeEncriptado.getBytes();
        byte[] mensaje2;
        boolean existe = false;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio1\\credenciales.cre"));
            sc = new Scanner(br);
            while (sc.hasNext()) {
                mensaje = sc.nextLine();
                mensaje2 = mensaje.getBytes();
                if (Hash.compararResumenes(mensaje1, mensaje2)){
                    existe = true;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (existe){
            System.out.println("Acceso permitido");
        }else {
            System.out.println("Acceso denegado");
        }
        return mensaje;
    }
}
