import java.util.concurrent.Semaphore;

public class Main{

    static final int META = 300;

    public static void main(String[] args) {

        Semaphore tunel = new Semaphore(1);

        Tortuga t = new Tortuga("Mbappe", tunel);
        Liebre l = new Liebre(tunel);

        t.start();
        l.start();
}
}

class Tortuga extends Thread {
    
    int posicion=0;
    int velocidad = 2;

    boolean estaEnTunel = false;

    String nombre;
    Semaphore tunel;

    public Tortuga (String nombre, Semaphore tunel){
        this.nombre=nombre;
        this.tunel=tunel;
    }

    @Override
    public void run() {
        try {
            while (posicion < Main.META){
            posicion+=velocidad;
            pasarTunel();
            if (estaEnTunel){
                System.out.println(nombre + " posicion (dentro del tunel): " + posicion + " m");
            } else {
                System.out.println(nombre + " posicion: " + posicion + " m");
            }
            Thread.sleep(1000);
            

        }
            
        } catch (Exception e) {
        }
    }

    void pasarTunel() throws InterruptedException{
        if (posicion >= 50 && posicion < 150 && !estaEnTunel){
                tunel.acquire();
                estaEnTunel = true;
                System.out.println(nombre + " ha entrado en el tunel");
            }

            if (posicion >= 150 && estaEnTunel){
                tunel.release();
                estaEnTunel = false;
                System.out.println(nombre + " ha salido del tunel");
            }
    }
}

class Liebre extends Thread{
    int posicion=0;
    int velocidad=5;

    Semaphore tunel;
    boolean estaEnTunel;
    boolean durmiendo;

    public Liebre (Semaphore tunel){
        this.tunel=tunel;
    }

    @Override
    public void run() {
        try {
            while(posicion < Main.META){
                 for (int i = 0; i < 4; i++) {
                posicion += velocidad;
                pasarTunel();
                System.out.println("Liebre: " + posicion + " m");
                Thread.sleep(1000);
                }

            durmiendo = true;
            System.out.println("La liebre esta durmiendo...");
            Thread.sleep(10000);
            durmiendo = false;
                
            if (estaEnTunel){
                System.out.println("Liebre posicion (dentro del tunel): " + posicion + " m");
            }
        }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }

    
    }
void pasarTunel() throws InterruptedException{
        if (posicion >= 50 && posicion < 150 && !estaEnTunel){
                tunel.acquire();
                estaEnTunel = true;
                System.out.println("Liebre ha entrado en el tunel");
            }

            if (posicion >= 150 && estaEnTunel){
                tunel.release();
                estaEnTunel = false;
                System.out.println("Liebre ha salido del tunel");
            }
}
}
