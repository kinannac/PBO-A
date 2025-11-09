
/**
 * Write a description of class RentalApp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class RentalApp {
    public static void main(String[] args) {
        ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
        daftarKendaraan.add(new Mobil("Toyota", "Avanza", 2020, 4));
        daftarKendaraan.add(new Motor("Honda", "Vario", 2022, 2));
        daftarKendaraan.add(new Sepeda("Polygon", "Xtrada", 2021, "Gunung"));

        System.out.println("# Daftar Kendaraan #");
        for (Kendaraan k : daftarKendaraan) {
            k.tampilkanInfo();
            System.out.println();
        }

        Penyewa p1 = new Penyewa("Sein", daftarKendaraan.get(0));
        Penyewa p2 = new Penyewa("Tesa", daftarKendaraan.get(2));

        System.out.println("\n# Daftar Penyewa #");
        p1.tampilkanPenyewa();
        p2.tampilkanPenyewa();
    }
}