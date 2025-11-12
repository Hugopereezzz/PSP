public class Trabajador implements Runnable{
    private Contador contador;
    private String nombre;

    public Trabajador(Contador contador, String nombre){
        this.contador=contador;
        this.nombre=nombre;
    }

    @Override
    public void run() {
        for(int k = 0; k < 1000; k++){
            contador.incrementar();
            
        }
    }
        
}
