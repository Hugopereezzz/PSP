public class Pajaro implements Runnable {
    int posicion = 0;
    int velocidad = 0;

    @Override
    public void run() {
        while (posicion < Main.META) {

            boolean volando = Math.random() < 0.5;

            if (volando) {
                velocidad = Math.random() < 0.5 ? 10 : -10;

                if (Main.viento) {
                    velocidad += Math.random() < 0.5 ? 5 : -5;
                }

            } else {
                velocidad = 3;
            }

            posicion += velocidad;
            if (posicion < 0) posicion = 0;

            pasarTunel();
             System.out.println("Pájaro: " + posicion + " m" + (volando ? " (volando)" : " (tierra)"));
            dormir(1000);
        }
        System.out.println("El pajaro llego");
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

