import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Cafeteria {

    public static void main(String[] args) {

        Semaphore maquina1 = new Semaphore(1);
        Semaphore maquina2 = new Semaphore(1);
        Semaphore maquina3 = new Semaphore(1);

        Maquina m1 = new Maquina(1, maquina1);
        Maquina m2 = new Maquina(2, maquina2);
        Maquina m3 = new Maquina(3, maquina3);

        List<Maquina> maquinas = new ArrayList<Maquina>();
        maquinas.add(m1);
        maquinas.add(m2);
        maquinas.add(m3);
        
        Cliente c1 = new Cliente("Hugo", 3, maquinas);
        Cliente c2 = new Cliente("Gabi", 5, maquinas);
        Cliente c3 = new Cliente("SamuBarber", 8, maquinas);
        Cliente c4 = new Cliente("Manel", 3, maquinas);
        Cliente c5 = new Cliente("Guille", 5, maquinas);
        Cliente c6 = new Cliente("Pijon", 8, maquinas);
        Cliente c7 = new Cliente("Fonsi", 3, maquinas);
        Cliente c8 = new Cliente("Ian", 5, maquinas);
        Cliente c9 = new Cliente("Maria", 8, maquinas);
        Cliente c10 = new Cliente("Dario", 3, maquinas);

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
            System.out.println("error");
        }
    }

    
}

class Maquina {
    int id;
    Semaphore semaforin;

    boolean estaOcupada;

    public Maquina(int id, Semaphore semaforin) {
        this.semaforin = semaforin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Semaphore getSemaforin() {
        return semaforin;
    }


    public boolean isEstaOcupada() {
        return estaOcupada;
    }

    public void setEstaOcupada(boolean estaOcupada) {
        this.estaOcupada = estaOcupada;
    }

    
}

class Cliente extends Thread{
    String nombre;
    int velocidad;
    boolean cafeTerminao;
    List<Maquina> maquinas;
    boolean enMaquina;
    Maquina maquinaActual;
    
    
    public Cliente(String nombre, int velocidad, List<Maquina> maquinas) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.cafeTerminao = false;
        this.maquinas = maquinas;
        this.enMaquina = false;
        this.maquinaActual = null;
    }

    public synchronized void usaMaquina(){

        if(!enMaquina){
            System.out.println(nombre+ " esta esperando pa hacerse un cafelito");
            System.out.println("-------------------------------------------------------------");
            for(int i=0; i<maquinas.size();i++){
                if(maquinas.get(i).isEstaOcupada()==false && enMaquina==false){
                    try {
                        maquinas.get(i).getSemaforin().acquire();
                        maquinas.get(i).setEstaOcupada(true);
                        maquinaActual=maquinas.get(i);

                    } catch (Exception e) {
                        System.out.println("error");
                    }
                    enMaquina=true;
                    System.out.println(nombre+ "se va a hacer un cafe en la maquina " + (i+1));
                    System.out.println("-------------------------------------------------------------");
                }
            }
        }
    }

    public synchronized void dejaMaquina(){

        if(enMaquina){
            for(int i=0;i<maquinas.size();i++){
                if(maquinaActual.getId()==maquinas.get(i).getId()){
                    maquinas.get(i).getSemaforin().release();
                    System.out.println("Maquina " + (i+1) + " esta libre");
                    System.out.println("-------------------------------------------------------------");
                    enMaquina=false;
                    cafeTerminao=true;
                    System.out.println(nombre + " ha tardado en hacerse el cafe " + velocidad + " segundos");
                }
            }
        }

    }

    @Override
    public void run(){
        try {
            while(!cafeTerminao){
                usaMaquina();
                Thread.sleep(velocidad * 1000);
                dejaMaquina();
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    
}
