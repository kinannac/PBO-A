
/**
 * Write a description of class Penyewa here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Penyewa {
    private String nama;
    private Kendaraan kendaraan;

    public Penyewa(String nama, Kendaraan kendaraan) {
        this.nama = nama;
        this.kendaraan = kendaraan;
    }

    public void tampilkanPenyewa() {
        System.out.println("\nNama Penyewa: " + nama);
        System.out.println("Detail Kendaraan yang Disewa:");
        kendaraan.tampilkanInfo();
    }
}