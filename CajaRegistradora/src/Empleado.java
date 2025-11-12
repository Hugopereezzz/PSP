import java.util.*;


public class Empleado implements Runnable {

    private Caja caja;
    private String nombre;

    public Empleado(Caja caja, String nombre){
        this.caja=caja;
        this.nombre=nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++){
            Random random = new Random();
             double importe = random.nextDouble() * (100 - 5) + 5;
             caja.cobrar(importe);
             System.out.println("Empleado " + nombre + " cobro " + String.format("%.2f", importe) + " euros. Total en caja: " + String.format("%.2f", caja.getTotal()));

             try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        
    }

}
