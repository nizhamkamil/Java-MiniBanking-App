import java.sql.*;

public class Deposit {
    private Connection conn;


    public Deposit() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        this.conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/m-banking",
                "root",
                ""
        );
    }



    public void insertDeposit(String noRekening, String kategori, String idUser, String nominal, String date) throws SQLException {
        String query = "INSERT INTO transaksi(noRekening, idKategori, idUser, nominal, Date)VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = this.conn.prepareStatement(query);

        stmt.setString(1, noRekening);
        stmt.setString(2, kategori);
        stmt.setString(3, idUser);
        stmt.setString(4, nominal);
        stmt.setString(5, date);




        System.out.println("Deposit == " + stmt);

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


    public void updateSaldo(int sal, int norek) throws SQLException {

        String query = "UPDATE rekening set saldo = ? where noRekening = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setInt(1, sal);
        stmt.setInt(2, norek);

        System.out.println("Update == " + stmt);

        stmt.execute();
    }
}
