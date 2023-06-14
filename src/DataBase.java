import java.io.IOException;
import java.sql.*;

public class DataBase {
    public static final String USER = "user";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Connection conection;
    public static Statement statement;
    static {
        try {
            conection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    static {
        try {
            statement = conection.createStatement();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        ResultSet rs;
        Class.forName("com.mysql.cj.jdbc.Driver");
       /* statement.executeUpdate("CREATE DATABASE Furniture_store;");
        statement.executeUpdate("USE library");*/
        rs = statement.executeQuery("SELECT * FROM library.book;");
        printQuery(rs);
    }
    public static void printQuery(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int countColumns = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= countColumns; i++) {
                System.out.printf(rs.getString(i) + " ");
            }
            System.out.print("\n");
        }
    }
}