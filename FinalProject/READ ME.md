# Memory Game

Kelompok: 4

Link Demo (Youtube): https://youtu.be/FgN8LRHtG6s

| Nama   | NRP   |
| ------ | ----- |
| Salwa Nadia Maharani | 5025241041 |
| Kinanti Ayu Caesandria | 5025241047 |
| Shafira Nauraishma Zahida | 5025241235 |

<br>

Program Memory Game ini merupakan aplikasi permainan sederhana berbasis GUI (Graphical User Interface) yang dibangun menggunakan bahasa pemrograman Java, library Swing, dan database MySQL sebagai media penyimpanan skor.

Pada permainan ini pemain diminta untuk menemukan pasangan angka pada papan berukuran 4x4 dalam waktu secepat mungkin. Setelah permainan selesai, skor pemain akan disimpan ke database dan dapat ditampilkan dalam leaderboard.

Tujuan utama pembuatan program ini adalah:
- Mengimplementasikan konsep OOP (Object-Oriented Programming).
- Menerapkan GUI interaktif menggunakan Swing.
- Mengintegrasikan aplikasi Java dengan database menggunakan JDBC.
- Menyediakan fitur CRUD sederhana untuk leaderboard.

<br>

## MemoryGameGUI.java
Class MemoryGameGUI merupakan kelas utama pada aplikasi Memory Game yang bertanggung jawab untuk mengelola antarmuka grafis (GUI) sekaligus alur permainan. Class ini berfungsi sebagai jendela aplikasi yang menampilkan papan permainan berbentuk grid 4×4, menangani interaksi pengguna melalui event klik kartu, serta mengatur logika pencocokan kartu, perhitungan skor dan waktu bermain, hingga mengirimkan data hasil permainan ke database. Pada class ini, terdapat 4 method yaitu:

1. `generateCards()`
   
      Method generateCards() berfungsi untuk menyiapkan kartu permainan dengan membuat delapan pasang yang diacak urutannya sebelum digunakan dalam permainan. Method ini menggunakan ArrayList untuk menampung sementara nilai kartu agar mudah diacak dengan Collections.shuffle(), lalu hasil acakan tersebut disalin ke dalam array values. Dengan cara ini, posisi kartu akan selalu berbeda setiap permainan dimulai

2. `handleClick()`
   
      Method handleClick(int index) bertugas menangani interaksi pemain ketika sebuah kartu diklik. Method ini memastikan bahwa kartu yang sudah terbuka tidak dapat diklik kembali, menampilkan nilai kartu yang dipilih, serta mencatat kartu pertama dan kedua yang dibuka oleh pemain. Setelah dua kartu dipilih, method ini menggunakan Timer selama 0,5 detik untuk memberi kesempatan pemain melihat kartu sebelum kartu kembali tertutup.

3. `checkMatch()`

      Method checkMatch() berperan untuk membandingkan dua kartu yang telah dipilih pemain. Jika kedua kartu memiliki nilai yang sama, skor pemain akan bertambah, sedangkan jika tidak cocok, kedua kartu tersebut akan ditutup kembali. Setelah proses pengecekan, indeks kartu di reset agar pemain dapat melanjutkan permainan berikutnya. Jika skor mencapai 8 alias semua pasangan ditemukan, permainan dianggap selesai dan method finishGame() dipanggil

4. `finishGame()`
   
      Method finishGame() dijalankan ketika permainan telah selesai dan seluruh pasangan kartu berhasil dicocokkan oleh pemain. Method ini menghitung total waktu bermain berdasarkan selisih waktu awal dan akhir permainan, kemudian meminta pemain memasukkan nama sebagai identitas skor. Data nama, skor, dan waktu selanjutnya dikirim ke database melalui class LeaderboardDAO. Terakhir, method ini menampilkan ringkasan hasil permainan kepada pemain dan menandai berakhirnya program

<br>

## LeaderboardDAO.java
Class `LeaderboardDAO` merupakan komponen `Data Access Object (DAO)` yang berfungsi sebagai penghubung antara program dengan database. Class ini menyediakan operasi untuk menyimpan, membaca, mengubah, dan menghapus data leaderboard dari tabel `leaderboard`.

Class ini menggunakan paket `java.sql` untuk koneksi `Database` dan `ArrayList` sebagai struktur penyimpanan sementara.

**Import Library**
```
import java.sql.*; 
import java.util.ArrayList;
```
Kode bagian ini mengimpor dua library penting sebagai berikut:

- `java.sql.*` → Menyediakan kelas untuk koneksi `Database` seperti `Connection`, `Statement`, `PreparedStatement`, dan `ResultSet`.
- `java.util.ArrayList` → Digunakan untuk menyimpan daftar hasil leaderboard yang sudah diproses sebelum dikembalikan ke program utama.

**Method**
1. `public static void insertScore()`

   Method ini berfungsi untuk menambahkan data skor baru ke dalam tabel leaderboard. Berikut penjelasan prosesnya:
    - `Database.getConnection()` → Membuka koneksi ke database.
    - `String sql` → Membuat query SQL menggunakan placeholder `(?)` agar lebih aman dari SQL Injection.
    - `PreparedStatement ps` → Mengisi nilai `nama`, `skor`, dan `waktu` ke dalam query.
    - `executeUpdate()` → Menjalankan perintah SQL sehingga database menambahkan baris baru ke tabel leaderboard.
    - Setelah selesai, hasil `statement` dan koneksi database ditutup menggunakan `close()`.

2. `public static ArrayList<String> getScores()`

   Method ini berfungsi untuk mengambil 5 pemain terbaik berdasarkan waktu tercepat dalam menyelesaikan permainan. Berikut penjelasan langkah kerjanya:
     - `Database.getConnection()` → Membuka koneksi ke database.
     - `Statement st` → Digunakan untuk menjalankan query SQL.
     - `executeQuery("SELECT * FROM leaderboard ORDER BY waktu ASC LIMIT 5")` → Mengambil maksimum 5 baris leaderboard yang sudah diurutkan berdasarkan waktu paling cepat (`ASC`).
     - `ArrayList<String> list` → Menyimpan hasil leaderboard dalam bentuk teks.
     - `int no = 1` → Digunakan sebagai nomor peringkat, karena id di database tidak mewakili ranking.
     - `while(rs.next())` → Membaca setiap baris hasil query dengan ketentuan berikut:
       
       - mengambil nama pemain, skor, dan waktu,
       - memformatnya menjadi string seperti `"1. Nama | Skor: ... | Waktu: ..."`
       - menyimpannya ke dalam `list`.
       
     - Setelah selesai `ResultSet`, `Statement`, dan koneksi database ditutup.
     - Method mengembalikan `list` ke bagian program yang memanggilnya untuk ditampilkan kepada pemain.

3. `public static void updateName()`
   
   Method ini berfungsi untuk mengubah nama pemain berdasarkan `id` yang terdapat pada tabel leaderboard. Berikut penjelasannya:
   - `Database.getConnection()` → Membuka koneksi ke database.
   - `String sql` → Membuat query SQL dengan placeholder:
      ```
      UPDATE leaderboard SET nama=? WHERE id=?
      ```
   - `PreparedStatement ps` → Mengisi nilai nama baru dan id pemain.
   - `executeUpdate()` → Menjalankan perintah sehingga baris pada database diperbarui.
   - `Statement` dan koneksi database ditutup setelah operasi selesai.

5. `public static void deleteScore()`

   Method ini berfungsi untuk menghapus satu baris data leaderboard berdasarkan `id` pemain. Cara kerjanya:
   - `Database.getConnection()` → Membuka koneksi ke database.
   - `String sql` → Membuat query SQL:
      ```
      DELETE FROM leaderboard WHERE id=...
      ```
   - `Statement` → Menjalankan query SQL.
   - Setelah selesai objek `Statement` dan koneksi ditutup.

<br>

## Database.java
``` java
import java.sql.*;
```

Mengimpor seluruh kelas dalam package `java.sql` yang diperlukan untuk
koneksi database melalui JDBC.

``` java
public class Database {
```

Mendefinisikan kelas utility bernama **Database** yang berfungsi sebagai
pusat koneksi database.

``` java
private static final String URL = "jdbc:mysql://localhost:3306/game_db";
private static final String USER = "root";
private static final String PASS = "";
```

Menentukan konfigurasi koneksi: - `URL` → alamat database MySQL. -
`USER` → username database. - `PASS` → password database.

``` java
public static Connection getConnection() throws Exception {
```

Method static untuk mengembalikan objek `Connection`. Dapat melempar
exception jika koneksi gagal.

``` java
Class.forName("com.mysql.cj.jdbc.Driver");
```

Mengaktifkan driver JDBC MySQL agar Java tahu cara berkomunikasi dengan
database.

``` java
return DriverManager.getConnection(URL, USER, PASS);
```

Membuka koneksi ke database dan mengembalikan objek `Connection`.

## Main.java

``` java
public class Main {
    public static void main(String[] args) {
        new MemoryGameGUI();
    }
}
```

-   `public class Main`\
    Mendefinisikan kelas utama program.

-   `public static void main(String[] args)`\
    Titik awal eksekusi program.

-   `new MemoryGameGUI();`\
    Membuat instance GUI game dan secara otomatis membuka tampilan
    permainan.

<br>

## Cara Kerja 
1. Program dimulai

2. Tampilkan grid kartu 4 x 4

3. Game dimulai

4. Player memilih dua kartu

5. Cek kartu
   - Jika cocok, simpan pasangan kartu sebagai matched
   - Jika tidak cocok, balikkan kembali kedua kartu ke posisi kosong

6. Cek apakah semua kartu telah ditemukan
   - Jika belum, kembali ke langkah memilih kartu
   - Jika sudah, lanjut ke langkah berikutnya

7. Game selesai

8. User memasukkan nama

9. Tampilkan skor dan waktu permainan

10. Tampilkan leaderboard

11. Game ditutup

<br>

## Dokumentasi

<p align="center">
<img width="434" height="400" alt="Screenshot 2025-12-09 135008" src="https://github.com/user-attachments/assets/d710c84a-1e6f-4ccc-a1c2-6cafa700b3e7" />
<br>
   <b>Memory Game Classes</b>
</p>
<br>
<p align="center">
<img width="481" height="552" alt="Screenshot 2025-12-09 135821" src="https://github.com/user-attachments/assets/dd2293ef-8ed1-4dfe-a9b9-32f833fb405f" />
<br>
   <b>Tampilan sebelum game dimulai</b>
</p>
<br>
<p align="center">
<img width="481" height="550" alt="Screenshot 2025-12-09 135908" src="https://github.com/user-attachments/assets/66454fc2-d3e9-4e9f-b157-843d721973d0" />
<br>
   <b>Jika memilih pasangan kartu yang benar</b>
</p>
<br>
<p align="center">
<img width="483" height="548" alt="Screenshot 2025-12-09 135953" src="https://github.com/user-attachments/assets/75e7e0e8-96c1-4860-a397-7a0ae03051d3" />
<img width="480" height="549" alt="Screenshot 2025-12-09 140003" src="https://github.com/user-attachments/assets/027ddc63-a104-4d2b-86c1-54fdec350e66" />
<br>
   <b>Jika memilih pasangan kartu yang salah (flipped ke kondisi kosong)</b>
</p>
<br>
<p align="center">
<img width="479" height="547" alt="Screenshot 2025-12-09 140051" src="https://github.com/user-attachments/assets/f71e317f-0caa-4eca-8de5-3897f5d19dae" />
<br>
   <b>Tampilan ketika game selesai (pemain menang)</b>
</p>
<br>
<p align="center">
<img width="479" height="548" alt="Screenshot 2025-12-09 140107" src="https://github.com/user-attachments/assets/112ddb98-83c2-4b23-9fdf-14e54826ab53" />
<br>
   <b>Pemain memasukkan namanya</b>
</p>
<br>
<p align="center">
<img width="478" height="554" alt="Screenshot 2025-12-09 140131" src="https://github.com/user-attachments/assets/5575a174-cbf8-4aa9-a7f3-c25327f50664" />
<br>
   <b>Menampilkan hasil skor dan waktu penyelesaian game</b>
</p>
<br>
<p align="center">
<img width="327" height="267" alt="Screenshot 2025-12-09 142708" src="https://github.com/user-attachments/assets/91da8a19-002a-4b03-b1be-eed5f13ec50b" />
<br>
   <b>Tampilan Leaderboard dengan 5 pemain teratas (berdasarkan waktu penyelesaian tercepat)</b>
</p>
<br>

## Kesimpulan
   Program Memory Game ini dirancang sebagai aplikasi berbasis GUI yang menyediakan permainan mencocokkan kartu secara interaktif, di mana pemain membuka dua kartu untuk menemukan nilai yang sama hingga seluruh kartu berhasil dicocokkan. Program ini mencatat skor berdasarkan jumlah pasangan kartu yang berhasil dicocokkan lalu menghitung waktu yang dibutuhkan pemain untuk menyelesaikan game. Setelah game selesai, pemain diminta untuk memasukkan nama, kemudian sistem akan menampilkan hasil permainan dan menyimpan data tersebut ke dalam database untuk bisa dibandingkan dan dimunculkan di leaderboard.

   Dari sisi desain dan implementasi, program Memory Game ini tersusun dari empat class, yaitu Main, MemoryGameGUI, LeaderboardDAO, dan Database. Class Main berfungsi sebagai tempat memulai proses kerja program, MemoryGameGUI menangani antarmuka pengguna serta logika permainan, LeaderboardDAO mengelola akses dan manipulasi data leaderboard, sedangkan Database menyediakan koneksi ke sistem basis data MySQL. Program ini menerapkan konsep Object Oriented Programming, seperti encapsulation, abstraction, dan separation of concerns, serta integrasi database.
