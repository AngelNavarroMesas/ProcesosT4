package ejercicio1;

import java.io.*;
import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String user, pass, cuenta, resumen;
        byte[] passGuardada, passEncriptada;
        System.out.println("Introduzca su usuario");
        user = sc.next();
        sc.nextLine();
        System.out.println("Introduzca su contraseña");
        pass= sc.nextLine();

        cuenta = user+pass;
        passGuardada = cuenta.getBytes();
        passEncriptada = Hash.getDigest(passGuardada, "SHA-256");
        resumen = String.format("%064x", new BigInteger(1, passEncriptada));
        GuardarCuenta(resumen);
        sc.close();
    }
    public static void GuardarCuenta(String mensaje){
        System.out.println(mensaje);
        try {
            String filePath = "C:\\Users\\angel\\IdeaProjects\\ProcesosT4\\src\\ejercicio1\\credenciales.cre";
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(mensaje);
            bw.newLine();
            bw.close();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
