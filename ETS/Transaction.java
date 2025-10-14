
/**
 * Write a description of class Transaction here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Date;

public class Transaction {
    private String coffeeName;
    private String size;
    private boolean sugar;
    private boolean milk;
    private double totalPrice;
    private Date date;

    public Transaction(String coffeeName, String size, boolean sugar, boolean milk, double totalPrice) {
        this.coffeeName = coffeeName;
        this.size = size;
        this.sugar = sugar;
        this.milk = milk;
        this.totalPrice = totalPrice;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) | Gula: %s | Susu: %s | Total: Rp%.2f",
                date, coffeeName, size, sugar ? "Ya" : "Tidak", milk ? "Ya" : "Tidak", totalPrice);
    }
}
