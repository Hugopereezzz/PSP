import java.util.concurrent.Semaphore;

public class Main{

    static final int META = 300;

    public static void main(String[] args) {

        Semaphore tunel = new Semaphore(1);
        Semaphore liana1 = new Semaphore(1);
        Semaphore liana2 = new Semaphore(1);
        Semaphore liana3 = new Semaphore(1);
        Semaphore liana4 = new Semaphore(1);
        Semaphore liana5 = new Semaphore(1);


        Tortuga t = new Tortuga(tunel);
        Liebre l = new Liebre(tunel);
        Pajaro p = new Pajaro(tunel);
        Mono m = new Mono("Wukong", liana1, liana2, liana3, liana4, liana5);
        Mono m2 = new Mono("Caramelo", liana1, liana2, liana3, liana4, liana5);
        t.start();
        l.start();
        p.start();
        m.start();
        m2.start();
}
}

class Mono extends Thread {
    int posicion=0;
    int velocidad=4;
    String nombre;
    Semaphore liana1;
    Semaphore liana2;
    Semaphore liana3;
    Semaphore liana4;
    Semaphore liana5;

    public Mono (String nombre, Semaphore liana1, Semaphore liana2, Semaphore liana3, Semaphore liana4, Semaphore liana5){
        this.nombre=nombre;
        this.liana1=liana1;
        this.liana2=liana2;
        this.liana3=liana3;
        this.liana4=liana4;
        this.liana5=liana5;
    }

    @Override
    public void run(){
        try {
            if (posicion < 60) {
            liana1.acquire();
            System.out.println(nombre +" sube a la liana 1");
            }
            while(posicion<Main.META){
                posicion+=velocidad;
                System.out.println(nombre +" Posicion: " + posicion + " m");

                if (posicion >= 60 && posicion - velocidad < 60) {
                liana1.release();
                System.out.println(nombre +" baja de la liana 1");
                liana2.acquire();
                System.out.println(nombre +" sube a la liana 2");
                }

                if (posicion >= 120&& posicion - velocidad < 120){
                    liana2.release();
                    System.out.println(nombre +" baja de la liana 2");
                    liana3.acquire();
                    System.out.println(nombre +" sube a la liana 3");
                }

                if (posicion >= 180 && posicion - velocidad < 180){
                    liana3.release();
                    System.out.println(nombre +" baja de la liana 3");
                    liana4.acquire();
                    System.out.println(nombre +" sube a la liana4");
                }

                if (posicion >= 240 && posicion - velocidad < 240) {
                    liana4.release();
                    System.out.println(nombre +" baja de la liana 4");
                    liana5.release();
                    System.out.println(nombre +" coge la liana 5");
                }

                Thread.sleep(1000);

            }
            System.out.println(nombre +" ha llegado a la fokin META");
        } catch (Exception e) {
            System.out.println("Lo siento Fonsi, apruebame porfavor");
        }
    }

}

class Tortuga extends Thread {
    
    int posicion=0;
    int velocidad = 2;

    boolean estaEnTunel = false;

    Semaphore tunel;

    public Tortuga (Semaphore tunel){
        this.tunel=tunel;
    }

    @Override
    public void run() {
        try {
            while (posicion < Main.META){
            posicion+=velocidad;
            pasarTunel();
            if (estaEnTunel){
                System.out.println("Mbappe posicion (dentro del tunel): " + posicion + " m");
            } else {
                System.out.println("Mbappe posicion: " + posicion + " m");
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
                System.out.println("Mbappe ha entrado en el tunel");
            }

            if (posicion >= 150 && estaEnTunel){
                tunel.release();
                estaEnTunel = false;
                System.out.println("Mbappe ha salido del tunel");
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

class Pajaro extends Thread{
    int posicion=0;
    int velocidad;
    Semaphore tunel;
    boolean estaEnTunel;
    boolean volando;
    boolean direccion;

    public Pajaro (Semaphore tunel){
        this.tunel=tunel;
    }

    @Override
    public void run(){
        try {
            while(posicion < Main.META){

                velocidad = 3;
                
                volando = Math.random() < 0.3;

                if (volando){
                    direccion = Math.random() < 0.1;
                }

                if (volando){
                    if (posicion != 0 && direccion) {
                    velocidad = -10;
                    } else {
                    velocidad = 10;
                    }
                }
                
                posicion+=velocidad;
                pasarTunel();
                if (estaEnTunel){
                    System.out.println("Pajaro posicion (dentro del tunel): " + posicion + " m");
                } else {
                    System.out.println("Pajaro posicion: " + posicion + " m");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    void pasarTunel() throws InterruptedException{
        if (posicion >= 50 && posicion < 150 && !estaEnTunel){
                tunel.acquire();
                estaEnTunel = true;
                System.out.println("Pajaro ha entrado en el tunel");
            }

            if (posicion >= 150 && estaEnTunel){
                tunel.release();
                estaEnTunel = false;
                System.out.println("Pajaro ha salido del tunel");
            }
    }
}