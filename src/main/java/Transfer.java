import java.sql.*;
import java.text.DateFormat;

public class Transfer{
    private Connection conn;


    public Transfer() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/m-banking",
                "root",
                ""
        );
    }



    public void insertTransfer(String noRekening, String kategori, String idUser, String noRekTuj, String nominal, String date) throws SQLException {
        String query = "INSERT INTO transaksi(noRekening, idKategori, idUser, noRekTujuan, nominal, Date)VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = this.conn.prepareStatement(query);

        stmt.setString(1, noRekening);
        stmt.setString(2, kategori);
        stmt.setString(3, idUser);
        stmt.setString(4, noRekTuj);
        stmt.setString(5, nominal);
        stmt.setString(6, date);




        System.out.println("Transfer== " + stmt);

        stmt.execute();
    }


    public int selectSaldo(int rek) throws SQLException {
        int sld = 0;

        try {
            String slct = "SELECT * from rekening where noRekening = ?";
            PreparedStatement stmt2 = this.conn.prepareStatement(slct);
            stmt2.setInt(1,rek);
            ResultSet rs = stmt2.executeQuery();

            while (rs.next()){
                sld = rs.getInt(2);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return sld;
    }


    public void transfer(int norek1, int norek2, int amount) throws SQLException {
        String query = "UPDATE rekening SET saldo = saldo + ? WHERE noRekening = ?";
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {this.conn.setAutoCommit(false);
            stmt1 = this.conn.prepareStatement(query);
            stmt2 = this.conn.prepareStatement(query);


            stmt1.setInt(1, -amount);
            stmt1.setInt(2, norek1);
            stmt1.executeUpdate();
            stmt2.setInt(1, amount);
            stmt2.setInt(2, norek2);
            stmt2.executeUpdate();
            this.conn.commit();

        } catch (SQLException e) {
            try {
                this.conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            this.conn.setAutoCommit(true);
        }
    }

}
