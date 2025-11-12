package ola;

import java.util.*;

public class Mainn {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);

	    ArrayList<Perro> perros = new ArrayList<>();
	    ArrayList<Gato> gatos = new ArrayList<>();

	    int eleccion = -1;

	    while (eleccion != 0) {
	        System.out.println("\nMenu:");
	        System.out.println("1. Añadir animal");
	        System.out.println("2. Mostrar datos de todos los animales del refugio");
	        System.out.println("0. Salir");
	        System.out.print("Elige: ");
	        eleccion = sc.nextInt();
	        sc.nextLine();

	        if (eleccion == 1) {
	            System.out.println("¿Qué animal quieres añadir?");
	            System.out.println("1. Perro");
	            System.out.println("2. Gato");
	            int tipo = sc.nextInt();
	            sc.nextLine();

	            if (tipo == 1) {
	                System.out.print("Nombre del perro: ");
	                String nombre = sc.nextLine();
	                System.out.print("Edad del perro: ");
	                int edad = sc.nextInt();
	                sc.nextLine();
	                System.out.print("Raza del perro: ");
	                String raza = sc.nextLine();

	                Perro nuevoPerro = new Perro(nombre, edad, raza);
	                perros.add(nuevoPerro);
	                System.out.println("Perro añadido.");
	            } else if (tipo == 2) {
	                System.out.print("Nombre del gato: ");
	                String nombre = sc.nextLine();
	                System.out.print("Edad del gato: ");
	                int edad = sc.nextInt();
	                System.out.print("Longitud de bigotes: ");
	                int longitud = sc.nextInt();

	                Gato nuevoGato = new Gato(nombre, edad, longitud);
	                gatos.add(nuevoGato);
	                System.out.println("Gato añadido.");
	            }
	        } else if (eleccion == 2) {
	            System.out.println("\n=== Lista de Perros ===");
	            for (Perro p : perros) {
	                System.out.println(p);
	            }

	            System.out.println("\n=== Lista de Gatos ===");
	            for (Gato g : gatos) {
	                System.out.println(g);
	            }
	        } else if (eleccion == 0) {
	            System.out.println("Saliendo...");
	        } else {
	            System.out.println("Opción no válida.");
	        }
	    }

	    sc.close();
	}
}
