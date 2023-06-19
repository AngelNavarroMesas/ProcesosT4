package ejercicio2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opc;

        System.out.println("Elija una opcion");
        System.out.println("1. Cifrar ");
        System.out.println("2. Descifrar");
        opc = sc.nextInt();
        sc.nextLine();

        switch (opc){
            case 1:
                System.out.println("Introduzca una clave de 16 caracteres");
                Funciones.cifrar(Funciones.obtenerClave(sc.nextLine()));
                break;
            case 2:
                System.out.println("Introduzca su clave");
                Funciones.descifrar(Funciones.obtenerClave(sc.nextLine()));
                break;
        }

    }

}
