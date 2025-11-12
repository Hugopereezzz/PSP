public class Alumno implements Runnable{

    String nombre;

    public Alumno (String nombre){
        this.nombre=nombre;
    }


        @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            System.out.println("Hola soy " + nombre + " y este es mi mensaje numero " + i);

        try {
            Thread.sleep(500);
            }   catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido");
                }
        }
    }

}