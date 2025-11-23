public class Viento implements Runnable {
    @Override
    public void run() {
        while (true) {
            Main.viento = Math.random() < 0.3; // pa que solo haya un 30% que sino es una chapa :D
            if (Main.viento) {
                System.out.println("¡Hay viento!");
            }
            try {Thread.sleep(1000); 

            } catch (Exception e) {}
        }
    }
}
