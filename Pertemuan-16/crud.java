import java.sql.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Buku {
    private int id;
    private String judul;
    private String pengarang;

    public Buku(int id, String judul, String pengarang) {
        this.id = id;
        this.judul = judul;
        this.pengarang = pengarang;
    }

    public int getId() { return id; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
}

class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

//main program
public class CRUD {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        while (true) {
            showMenu();
        }
    }

    static void showMenu() {
        System.out.println("\n MENU UTAMA!!! ");
        System.out.println("1. Lihat Data");
        System.out.println("2. Tambah Data");
        System.out.println("3. Ubah Data");
        System.out.println("4. Hapus Data");
        System.out.println("0. Keluar");
        System.out.print("Pilihan: ");

        try {
            int pilih = Integer.parseInt(input.readLine());
            switch (pilih) {
                case 1: showData(); break;
                case 2: insertData(); break;
                case 3: updateData(); break;
                case 4: deleteData(); break;
                case 0: System.exit(0);
                default: System.out.println("Pilihan tidak valid");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void showData() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM buku");

            System.out.println("\n ~~~DATA BUKU~~~ ");

            while (rs.next()) {
                Buku b = new Buku(
                    rs.getInt("id_buku"),
                    rs.getString("judul"),
                    rs.getString("pengarang")
                );

                System.out.println(b.getId() + ". " + b.getJudul() + " - " + b.getPengarang());
            }

        } catch (Exception e) {
            System.out.println("Error membaca data: " + e.getMessage());
        }
    }

    static void insertData() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.print("Judul: ");
            String judul = input.readLine();
            System.out.print("Pengarang: ");
            String pengarang = input.readLine();

            String sql = String.format(
                "INSERT INTO buku(judul, pengarang) VALUES('%s', '%s')",
                judul, pengarang
            );

            stmt.execute(sql);

            System.out.println("Data berhasil ditambahkan!");

        } catch (Exception e) {
            System.out.println("Error insert: " + e.getMessage());
        }
    }

    static void updateData() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.print("ID Buku yang diubah: ");
            int id = Integer.parseInt(input.readLine());

            System.out.print("Judul baru: ");
            String judul = input.readLine();

            System.out.print("Pengarang baru: ");
            String pengarang = input.readLine();

            String sql = String.format(
                "UPDATE buku SET judul='%s', pengarang='%s' WHERE id_buku=%d",
                judul, pengarang, id
            );

            stmt.execute(sql);

            System.out.println("Data berhasil diupdate!");

        } catch (Exception e) {
            System.out.println("Error update: " + e.getMessage());
        }
    }

    static void deleteData() {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.print("ID Buku yang dihapus: ");
            int id = Integer.parseInt(input.readLine());

            String sql = "DELETE FROM buku WHERE id_buku=" + id;
            stmt.execute(sql);

            System.out.println("Data berhasil dihapus!");

        } catch (Exception e) {
            System.out.println("Error delete: " + e.getMessage());
        }
    }
}
