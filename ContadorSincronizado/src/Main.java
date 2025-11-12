public class Main {

    public static void main(String[] args) {
        Contador contador = new Contador();

        Thread t1 = new Thread(new Trabajador(contador, "Hugo"));
        Thread t2 = new Thread(new Trabajador(contador, "Maria"));
        Thread t3 = new Thread(new Trabajador(contador, "Guille"));
        Thread t4 = new Thread(new Trabajador(contador, "Michu"));
        Thread t5 = new Thread(new Trabajador(contador, "Gabi"));


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

         try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();

        } catch (Exception e) {
            // TODO: handle exception
        }

        System.out.println("Valor final del contador: " + contador.getValor());
    }
    
}
