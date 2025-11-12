import java.util.*;

public class Main {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);


        System.out.println("Primer nombre: ");
        String n1 = sc.nextLine();
        System.out.println("Segundo nombre: ");
        String n2 = sc.nextLine();
        System.out.println("Tercer nombre: ");
        String n3 = sc.nextLine();

        Alumno a1 = new Alumno(n1);
        Alumno a2 = new Alumno(n2);
        Alumno a3 = new Alumno(n3);

        Thread hilo1 = new Thread(a1);
        Thread hilo2 = new Thread(a2);
        Thread hilo3 = new Thread(a3);

        try {
            hilo1.start();
            hilo1.join();

            hilo2.start();
            hilo2.join();

            hilo3.start();
            hilo3.join();

        } catch (InterruptedException e){
            System.out.println("Se ha parado el hilo");
        }

    }
}
