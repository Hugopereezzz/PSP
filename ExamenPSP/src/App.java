import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class App {


    public static void main(String[] args) throws Exception {
        
        Semaphore lavadora1 = new Semaphore(1);
        Semaphore lavadora2 = new Semaphore(1);
        Semaphore lavadora3 = new Semaphore(1);
        
        Lavadora s1 = new Lavadora(lavadora1);
        Lavadora s2 = new Lavadora(lavadora2);
        Lavadora s3 = new Lavadora(lavadora3);

        List<Lavadora> lista = new ArrayList<Lavadora>();

        lista.add(s1);
        lista.add(s2);
        lista.add(s3);

        Cliente c1 = new Cliente("Hugo", lista, 3);
        Cliente c2 = new Cliente("SamuBarber", lista, 5);
        Cliente c3 = new Cliente("Gabi", lista, 8);
        Cliente c4 = new Cliente("Dario", lista, 3);
        Cliente c5 = new Cliente("Ian", lista, 5);
        Cliente c6 = new Cliente("Pedro", lista, 8);
        Cliente c7 = new Cliente("Adriana", lista, 3);
        Cliente c8 = new Cliente("Maria", lista, 5);
        Cliente c9 = new Cliente("Guille", lista, 8);
        Cliente c10 = new Cliente("Samuel", lista, 8);

        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        c7.start();
        c8.start();
        c9.start();
        c10.start();

        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();
            c6.join();
            c7.join();
            c8.join();
            c9.join();
            c10.join();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Lo siento Fonsi apruebame");
        }
    }
}

class Lavadora{
    
    boolean estaOcupada;
    Semaphore semaforin;
    
    public Lavadora ( Semaphore semaforin) {
        this.estaOcupada = false;
        this.semaforin = semaforin;
    }

    public boolean isEstaOcupada() {
        return estaOcupada;
    }

    public void setEstaOcupada(boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }

    public Semaphore getSemaforin() {
        return semaforin;
    }
    
    
}


class Cliente extends Thread{
    
    boolean enLavadora;
    List<Lavadora> lavadoras;
    String nombre;
    int velocidad;
    Lavadora lavadoraActual;
    boolean lavado;

    




    public Cliente(String nombre, List<Lavadora> lavadoras, int velocidad) {
        this.enLavadora = false;
        this.lavadoras = lavadoras;
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.lavadoraActual = null;
        this.lavado = false;
    }

    public synchronized void ponerEnLavadora(){
        if(!enLavadora){
            System.out.println(nombre + " Esta esperando");

            for(int k=0;k<lavadoras.size();k++){
                if (lavadoras.get(k).isEstaOcupada()==false && enLavadora==false){
                    try {
                        lavadoras.get(k).getSemaforin().acquire();
                        lavadoras.get(k).setEstaOcupada(true);
                    } catch (Exception e) {
                        System.out.println("Lo siento fonsi, apruebame");
                    }
                    enLavadora=true;
                    System.out.println(nombre + " esta lavadondo ropa en la lavadora" + (k+1));

                }
            }
        }
    }


    public synchronized void sacarDeLavadora(){
        if(enLavadora){
            for(int k=0;k<lavadoras.size();k++){
                if(lavadoras.get(k).isEstaOcupada()==true && enLavadora==true){
                    lavadoras.get(k).getSemaforin().release();
                    System.out.println("Lavadora" + (k+1) + " esta libre");
                    lavadoras.get(k).setEstaOcupada(false);
                    lavado=true;
                    enLavadora=false;
                    System.out.println(nombre + " ha terminao de lavar. Ha tardao " + velocidad + " segundos");
                }
            }
        }
    }






    @Override
    public void run(){
        try {
            while(!lavado){
                ponerEnLavadora();
                Thread.sleep(velocidad*1000);
                sacarDeLavadora();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}

class Monitorizacion implements Runnable{

    List<Lavadora> lavadoera;

    public Monitorizacion(List<Lavadora>lavadoera){
        this.lavadoera=lavadoera;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
