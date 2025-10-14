
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        VendingMachine vm = new VendingMachine();

        int choice;
        do {
            System.out.println("\n# Vending Coffee Machine #");
            System.out.println("1. Pesan Kopi");
            System.out.println("2. Lihat Log Transaksi");
            System.out.println("3. Lihat Stok");
            System.out.println("0. Keluar Program");
            System.out.print("Pilihan menu: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> vm.order(sc);
                case 2 -> vm.showLog();
                case 3 -> vm.checkStock();
                case 0 -> System.out.println("Terima kasih telah berkunjung. Enjoy!");
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 0);
    }
}