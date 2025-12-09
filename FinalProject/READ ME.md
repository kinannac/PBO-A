# Memory Game

Kelompok: 4

Link Demo (Youtube): 

| Nama   | NRP   |
| ------ | ----- |
| Salwa Nadia Maharani | 5025241041 |
| Kinanti Ayu Caesandria | 5025241047 |
| Shafira Nauraishma Zahida | 5025241235 |

Program Memory Game ini merupakan aplikasi permainan sederhana berbasis GUI (Graphical User Interface) yang dibangun menggunakan bahasa pemrograman Java, library Swing, dan database MySQL sebagai media penyimpanan skor.

Pada permainan ini pemain diminta untuk menemukan pasangan angka pada papan berukuran 4x4 dalam waktu secepat mungkin. Setelah permainan selesai, skor pemain akan disimpan ke database dan dapat ditampilkan dalam leaderboard.

Tujuan utama pembuatan program ini adalah:
- Mengimplementasikan konsep OOP (Object-Oriented Programming).
- Menerapkan GUI interaktif menggunakan Swing.
- Mengintegrasikan aplikasi Java dengan database menggunakan JDBC.
- Menyediakan fitur CRUD sederhana untuk leaderboard.

## MemoryGameGUI.java
Class MemoryGameGUI merupakan kelas utama pada aplikasi Memory Game yang bertanggung jawab untuk mengelola antarmuka grafis (GUI) sekaligus alur permainan. Class ini berfungsi sebagai jendela aplikasi yang menampilkan papan permainan berbentuk grid 4×4, menangani interaksi pengguna melalui event klik kartu, serta mengatur logika pencocokan kartu, perhitungan skor dan waktu bermain, hingga mengirimkan data hasil permainan ke database. Pada class ini, terdapat 4 method yaitu:

1. generateCards()
   
  Method generateCards() berfungsi untuk menyiapkan kartu permainan dengan membuat delapan pasang yang diacak urutannya sebelum digunakan dalam permainan. Method ini menggunakan ArrayList untuk menampung sementara nilai kartu agar mudah diacak dengan Collections.shuffle(), lalu hasil acakan tersebut disalin ke dalam array values. Dengan cara ini, posisi kartu akan selalu berbeda setiap permainan dimulai

2. handleClick()
   
  Method handleClick(int index) bertugas menangani interaksi pemain ketika sebuah kartu diklik. Method ini memastikan bahwa kartu yang sudah terbuka tidak dapat diklik kembali, menampilkan nilai kartu yang dipilih, serta mencatat kartu pertama dan kedua yang dibuka oleh pemain. Setelah dua kartu dipilih, method ini menggunakan Timer selama 0,5 detik untuk memberi kesempatan pemain melihat kartu sebelum kartu kembali tertutup.

3. checkMatch()

  Method checkMatch() berperan untuk membandingkan dua kartu yang telah dipilih pemain. Jika kedua kartu memiliki nilai yang sama, skor pemain akan bertambah, sedangkan jika tidak cocok, kedua kartu tersebut akan ditutup kembali. Setelah proses pengecekan, indeks kartu di reset agar pemain dapat melanjutkan permainan berikutnya. Jika skor mencapai 8 alias semua pasangan ditemukan, permainan dianggap selesai dan method finishGame() dipanggil

4. finishGame()
   
  Method finishGame() dijalankan ketika permainan telah selesai dan seluruh pasangan kartu berhasil dicocokkan oleh pemain. Method ini menghitung total waktu bermain berdasarkan selisih waktu awal dan akhir permainan, kemudian meminta pemain memasukkan nama sebagai identitas skor. Data nama, skor, dan waktu selanjutnya dikirim ke database melalui class LeaderboardDAO. Terakhir, method ini menampilkan ringkasan hasil permainan kepada pemain dan menandai berakhirnya program

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
 - Setelah selesai, statement dan koneksi ditutup menggunakan `close()`.

2. `public static ArrayList<String> getScores()`

Method ini berfungsi untuk mengambil 5 pemain terbaik berdasarkan waktu tercepat dalam menyelesaikan permainan. Berikut penjelasan langkah kerjanya:

  - `Database.getConnection()` → Membuka koneksi ke database.
  - `Statement st` → Digunakan untuk menjalankan query SQL.
  - `executeQuery("SELECT * FROM leaderboard ORDER BY waktu ASC LIMIT 5")`
  → Mengambil maksimum 5 baris leaderboard yang sudah diurutkan berdasarkan waktu paling cepat (`ASC`).
  - `ArrayList<String> list` → Menyimpan hasil leaderboard dalam bentuk teks.
  - `int no = 1` → Digunakan sebagai nomor peringkat, karena id di database tidak mewakili ranking.
  - `while(rs.next())` → Membaca setiap baris hasil query dengan ketentuan berikut:
        - mengambil nama pemain, skor, dan waktu,
        - memformatnya menjadi string seperti `"1. Nama | Skor: ... | Waktu: ..."`
        - menyimpannya ke dalam `list`.
  - Setelah selesai, `ResultSet`, `Statement`, dan koneksi database ditutup.
  - Method mengembalikan `list` ke bagian program yang memanggilnya untuk ditampilkan kepada pemain.

3. `public static void updateName()`

Method ini berfungsi untuk mengubah nama pemain berdasarkan `id` yang terdapat pada tabel leaderboard. Berikut penjelasannya:

- Membuka koneksi database menggunakan `Database.getConnection()`.
- Membuat query SQL dengan placeholder:

```
UPDATE leaderboard SET nama=? WHERE id=?
```
- `PreparedStatement ps` → Mengisi nilai nama baru dan id pemain.
- `executeUpdate()` → Menjalankan perintah sehingga baris pada database diperbarui.
* Statement dan koneksi ditutup setelah operasi selesai.

4. `public static void deleteScore()`

Method ini berfungsi untuk menghapus satu baris data leaderboard berdasarkan `id` pemain. Cara kerjanya:

* Membuka koneksi database.
* Membuat query SQL:

```
DELETE FROM leaderboard WHERE id=...
```
* Menjalankannya menggunakan `Statement`.
* Setelah selesai, objek statement dan koneksi ditutup.
## Database.java

## Main.java
