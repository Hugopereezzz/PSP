package ola;

public class Perro extends Animal {

	String raza;
	
	public Perro(String nombre, int edad, String raza) {
		super(nombre, edad);
		this.raza=raza;
		
	}
	
	public void ladrar() {
		System.out.println("guau guau");
	}
	
	@Override
	public String toString() {
	    return "Perro [nombre=" + nombre + ", edad=" + edad + ", raza=" + raza + "]";
	}


}
