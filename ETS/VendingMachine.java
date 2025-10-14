
/**
 * Write a description of class VendingMachine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private List<Coffee> menu;
    private List<Transaction> log;

    public VendingMachine() {
        menu = new ArrayList<>();
        log = new ArrayList<>();

        menu.add(new Coffee("Espresso", 15000, 5));
        menu.add(new Coffee("Americano", 18000, 5));
        menu.add(new Coffee("Cappuccino", 20000, 5));
        menu.add(new Coffee("Mocha", 22000, 5));
        menu.add(new Coffee("Caramel Macchiato", 23000, 5));
        menu.add(new Coffee("Pandan Latte", 24000, 5));
        menu.add(new Coffee("Caramel Latte", 25000, 5));
        menu.add(new Coffee("Cold Brew", 26000, 5));
        menu.add(new Coffee("Affogato", 27000, 5));
        menu.add(new Coffee("Brown Sugar Latte", 30000, 5));
    }

    public void showMenu() {
        System.out.println("\n# MENU KOPI #");
        for (int i = 0; i < menu.size(); i++) {
            Coffee c = menu.get(i);
            System.out.printf("%d. %-15s Rp%.0f (stok: %d)\n", i + 1, c.getName(), c.getPrice(), c.getStock());
        }
    }

    public void order(Scanner sc) {
        showMenu();
        System.out.print("\nPilih nomor kopi: ");
        int choice = sc.nextInt();
        if (choice < 1 || choice > menu.size()) {
            System.out.println("Pilihan tidak valid!");
            return;
        }

        Coffee selected = menu.get(choice - 1);
        if (selected.isOutOfStock()) {
            System.out.println("!!! Stok kopi ini habis. Silakan pilih yang lain.");
            return;
        }

        System.out.print("Pilih ukuran (S/M/L): ");
        String size = sc.next().toUpperCase();
        double multiplier = switch (size) {
            case "S" -> 1.0;
            case "M" -> 1.2;
            case "L" -> 1.5;
            default -> { System.out.println("!!! Ukuran tidak valid, digunakan Small."); yield 1.0; }
        };

        System.out.print("Tambah gula? (y/n): ");
        boolean sugar = sc.next().equalsIgnoreCase("y");

        System.out.print("Tambah susu? (y/n): ");
        boolean milk = sc.next().equalsIgnoreCase("y");

        double total = selected.getPrice() * multiplier;
        if (sugar) total += 2000;
        if (milk) total += 3000;

        System.out.printf("Total harga: Rp%.0f\n", total);
        System.out.print("Masukkan uang: Rp");
        double payment = sc.nextDouble();

        if (payment >= total) {
            System.out.println("Pembayaran diterima. Kopi segera siap...");
            selected.reduceStock();
            double change = payment - total;
            if (change > 0) System.out.printf("Kembalian: Rp%.0f\n", change);
            System.out.println("Kopi siap disajikan!\n");

            log.add(new Transaction(selected.getName(), size, sugar, milk, total));
        } else {
            System.out.println("!!! Uang tidak cukup. Transaksi dibatalkan.");
        }
    }

    public void showLog() {
        System.out.println("\n# Log Transaksi #");
        if (log.isEmpty()) {
            System.out.println("!!! Belum ada transaksi.");
        } else {
            for (Transaction t : log) {
                System.out.println(t);
            }
        }
    }

    public void checkStock() {
        System.out.println("\n# Lihat Stok #");
        for (Coffee c : menu) {
            if (c.isOutOfStock()) {
                System.out.printf("!!! %s habis! Mohon refill.%n", c.getName());
            }
        }
    }
}
