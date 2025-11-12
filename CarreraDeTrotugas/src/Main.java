import java.util.concurrent.Semaphore;
public class Main {

    public static void main(String[] args) {
        Semaphore tunel = new Semaphore(1); // Solo una tortuga a la vez en el túnel

        Tortuga t1 = new Tortuga("Leonardo", tunel);
        Tortuga t2 = new Tortuga("Donatello", tunel);
        Tortuga t3 = new Tortuga("Michelangelo", tunel);

        t1.start();
        t2.start();
        t3.start();
    }
}
    
