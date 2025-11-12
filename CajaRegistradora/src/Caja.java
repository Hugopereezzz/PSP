public class Caja {
    
    private double total = 0;

    public double cobrar(double cantidad){
        return total+=cantidad;
    }

    public double getTotal(){
        return this.total;
    }
}
