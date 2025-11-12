public class Main {

    public static void main(String[] args) {
        
        Caja caja = new Caja();

        Thread t1 = new Thread(new Empleado(caja, "Hugo"));    
        Thread t2 = new Thread(new Empleado(caja, "Maria"));    
        Thread t3 = new Thread(new Empleado(caja, "Guille"));
        
        
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
