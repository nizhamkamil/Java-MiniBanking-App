import java.sql.*;

public class Rekening {
    private Connection conn;

    public Rekening() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/m-banking",
                "root",
                ""
        );
    }

    public boolean isConnected() {
        return (this.conn != null);

    }

    public void closeConnection() throws SQLException {
        this.conn.close();

    }


    public String selectSaldo(String rek) throws Exception {

        String query = "select saldo from rekening where noRekening = '"+rek+"'";

        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        if (rs.next()){
            MainPage mainPage = new MainPage();
            mainPage.No_Rek2 = rs.getString(2);
        }

        return query;
    }
}

