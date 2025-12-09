
/**
 * Write a description of class LeaderboardDAO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.*;
import java.util.ArrayList;

public class LeaderboardDAO {

    public static void insertScore(String nama, int skor, int waktu) throws Exception {
        Connection conn = Database.getConnection();
        String sql = "INSERT INTO leaderboard(nama, skor, waktu) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nama);
        ps.setInt(2, skor);
        ps.setInt(3, waktu);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public static ArrayList<String> getScores() throws Exception {
        Connection conn = Database.getConnection();
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM leaderboard ORDER BY waktu ASC LIMIT 5");

        ArrayList<String> list = new ArrayList<>();
        int no = 1;

        while (rs.next()) {
            list.add(no + "." + rs.getString("nama") + " | Skor: " +
                     rs.getInt("skor") + " | Waktu: " +
                     rs.getInt("waktu"));
            no++;
        }

        rs.close();
        st.close();
        conn.close();
        return list;
    }

    public static void updateName(int id, String baru) throws Exception {
        Connection conn = Database.getConnection();
        String sql = "UPDATE leaderboard SET nama=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, baru);
        ps.setInt(2, id);
        ps.executeUpdate();

        ps.close();
        conn.close();
    }

    public static void deleteScore(int id) throws Exception {
        Connection conn = Database.getConnection();
        String sql = "DELETE FROM leaderboard WHERE id=" + id;

        Statement st = conn.createStatement();
        st.execute(sql);

        st.close();
        conn.close();
    }
}
