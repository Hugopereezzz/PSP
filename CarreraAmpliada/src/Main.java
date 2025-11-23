public class Main {

    public static final int META = 300;
    public static final Object TUNEL = new Object();
    public static volatile boolean viento = false;

    public static void main(String[] args) {
        new Thread(new Tortuga()).start();
        new Thread(new Liebre()).start();
        new Thread(new Pajaro()).start();
        new Thread(new Viento()).start();
    }
    
}
