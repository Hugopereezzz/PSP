package ola;

public class Gato extends Animal {

	int longitudBigotes;
	
	public Gato(String nombre, int edad, int longitudBigotes) {

		super(nombre, edad);
		this.longitudBigotes=longitudBigotes;
		
	}
	
	public void maullar() {
		System.out.println("miau miau");
	}
	
	@Override
	public String toString() {
	    return "Gato [nombre=" + nombre + ", edad=" + edad + ", peso=" + longitudBigotes + "]";
	}

	
}
