import java.util.concurrent.Semaphore;

class Tortuga extends Thread {
    private String nombre;
    private Semaphore tunel;

    public Tortuga(String nombre, Semaphore tunel) {
        this.nombre = nombre;
        this.tunel = tunel;
    }

    @Override
    public void run() {
        try {

            for (int i = 1; i <= 20; i++) {
                System.out.println(nombre + " avanza " + i + "m antes del tunel");
                Thread.sleep(1000); // 1 m/s
            }

            System.out.println(nombre + " quiere entrar al tunel");
            tunel.acquire();
            System.out.println(nombre + " ENTRA al tunel");


            for (int i = 1; i <= 10; i++) {
                System.out.println(nombre + " dentro del tunel: " + i + "m");
                Thread.sleep(1000);
            }

            System.out.println(nombre + " SALE del tunel");
            tunel.release();


            for (int i = 1; i <= 20; i++) {
                System.out.println(nombre + " avanza " + (20 + 10 + i) + "m después del tunel");
                Thread.sleep(1000);
            }

            System.out.println(nombre + " ha llegado a la meta");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}