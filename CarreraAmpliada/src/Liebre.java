public class Liebre implements Runnable {
    int posicion = 0;
    int velocidad = 5;
    boolean durmiendo = false;

    @Override
    public void run() {
        while (posicion < Main.META) {

            if (durmiendo && Main.viento) {
                durmiendo = false;
            }

            for (int i = 0; i < 4 && posicion < Main.META; i++) {
                posicion += velocidad;
                pasarTunel();
                System.out.println("Liebre: " + posicion + " m");
                dormir(1000);
            }

            durmiendo = true;
            System.out.println("La liebre esta durmiendo...");
            dormir(10000);
            durmiendo = false;
        }
        System.out.println(" La liebre llego");
    }

    void pasarTunel() {
        if (posicion >= 50 && posicion <= 150) {
            synchronized (Main.TUNEL) {
            }
        }
    }

    void dormir(int ms) {
        try {Thread.sleep(ms); 

        } catch (Exception e) {}
    }
}
