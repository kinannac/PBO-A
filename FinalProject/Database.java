
/**
 * Write a description of class Database here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/game_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
