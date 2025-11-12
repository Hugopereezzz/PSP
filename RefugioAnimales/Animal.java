package ola;

public class Animal {
	
	String nombre;
	int edad;
	
	
	public Animal(String nombre, int edad) {
		this.nombre=nombre;
		this.edad=edad;
	}
	
	public void mostarDatos() {
		System.out.println("Nombre: " + nombre + " Edad: " + edad);
	}

}


