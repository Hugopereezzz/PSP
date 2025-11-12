import java.util.concurrent.Semaphore;

public class Tunel {

    Semaphore semaphore;
    int inicio = 50;
    int fin = 150;
    
    public Tunel(int inicio, int fin) {
        Semaphore semaphore = new Semaphore(1);
        this.inicio = inicio;
        this.fin = fin;
    }

    public void entar(String nombre){

    }

    public void salir(String nombre){

    }

    



    
}
