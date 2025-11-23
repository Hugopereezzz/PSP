public class Tortuga implements Runnable {
        int posicion = 0;
        int velocidad = 2;

 @Override
    public void run() {
        while (posicion < Main.META) {
            posicion += velocidad;
            pasarTunel();
            System.out.println("Tortuga: " + posicion + " m");
            dormir(1000);
        }
        System.out.println(" La tortuga llego");
    }

    void pasarTunel() {
        if (posicion >= 50 && posicion <= 150) {
            synchronized (Main.TUNEL) {
            }
        }
    }

    void dormir(int ms) {
        try { Thread.sleep(ms); 

        } catch (Exception e) {}
    }
}