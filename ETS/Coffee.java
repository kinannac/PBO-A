
/**
 * Write a description of class Coffee here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Coffee {
    private String name;
    private double price;
    private int stock;

    public Coffee(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock() {
        if (stock > 0) stock--;
    }

    public boolean isOutOfStock() {
        return stock <= 0;
    }
}