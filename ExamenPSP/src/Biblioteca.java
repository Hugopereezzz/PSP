import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Biblioteca {

    public static void main(String[] args) {
        
        Semaphore cabina1 = new Semaphore(1);
        Semaphore cabina2 = new Semaphore(1);
        Semaphore cabina3 = new Semaphore(1);


        Cabina c1 = new Cabina(cabina1,1);
        Cabina c2 = new Cabina(cabina2,2);
        Cabina c3 = new Cabina(cabina3,3);

        List<Cabina> listaCabinas = new ArrayList<Cabina>();

        listaCabinas.add(c1);
        listaCabinas.add(c2);
        listaCabinas.add(c3);

        Alumno a1 = new Alumno("Hugo", 3, listaCabinas);
        Alumno a2 = new Alumno("Gabi", 5, listaCabinas);
        Alumno a3 = new Alumno("Manel", 8, listaCabinas);
        Alumno a4 = new Alumno("SamuBarber", 5, listaCabinas);
        Alumno a5 = new Alumno("Dario", 8, listaCabinas);
        Alumno a6 = new Alumno("Ian", 3, listaCabinas);
        Alumno a7 = new Alumno("Satelitower", 3, listaCabinas);
        Alumno a8 = new Alumno("Guille", 8, listaCabinas);
        Alumno a9 = new Alumno("Pijon", 5, listaCabinas);
        Alumno a10 = new Alumno("Jefe", 3, listaCabinas);

        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
        a6.start();
        a7.start();
        a8.start();
        a9.start();
        a10.start();

        try {
            a1.join();
            a2.join();
            a3.join();
            a4.join();
            a5.join();
            a6.join();
            a7.join();
            a8.join();
            a9.join();
            a10.join();
        } catch (Exception e) {
            
        }
    }

}


class Cabina {
    
    boolean estaOcupada;
    Semaphore semaforin;
    int id;
    
    
    public Cabina(Semaphore semaforin, int id) {
        this.semaforin = semaforin;
    }

    


    public int getId() {
        return id;
    }




    public void setId(int id) {
        this.id = id;
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

class Alumno extends Thread{
    String nombre;
    int velocidad;
    boolean estudioTerminao;
    List<Cabina> cabinas;
    boolean enCabina;
    Cabina cabinaActual;

    


    public Alumno(String nombre, int velocidad, List<Cabina> cabinas) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.estudioTerminao = false;
        this.cabinas = cabinas;
        this.enCabina = false;
        this.cabinaActual = null;
    }

    public synchronized void entrarEnCabina() {

        if (!enCabina){
            for(int i=0; i<cabinas.size(); i++){
                if (cabinas.get(i).isEstaOcupada()==false && enCabina==false){
                    try {
                        cabinas.get(i).getSemaforin().acquire();
                        cabinas.get(i).setEstaOcupada(true);
                        this.cabinaActual=cabinas.get(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    enCabina=true;
                    System.out.println(nombre + " esta estudiando en la cabina " + (i+1));
                    System.out.println("----------------------------------------");
                }
            }
        }
    }

    public synchronized void salirCabina() {
        if (enCabina){
            for(int i=0; i<cabinas.size();i++){
                if(cabinaActual.getId()==cabinas.get(i).getId()){
                        cabinas.get(i).getSemaforin().release();
                        System.out.println("La cabina "+ (i+1) + " ha quedado libre");
                        System.out.println("----------------------------------------");
                        cabinas.get(i).setEstaOcupada(false);
                    estudioTerminao=true;
                    enCabina=false;
                    System.out.println(nombre + " ha terminado de estudiar. Ha tardado " + velocidad + " segundos");
                    System.out.println("----------------------------------------------------------------------");
                }
            }
        }

    }

    @Override
    public void run(){
        while(!estudioTerminao){
            entrarEnCabina();
            try {
                Thread.sleep(velocidad*1000);
            } catch (Exception e) {
                // TODO: handle exception
            }
            salirCabina();
        }
    }
}