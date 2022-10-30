import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.sql.*;



public class MainPage  extends  JFrame{
    static String id_user2;
    static String full_name2;
    static String rek2;
    static String No_Rek2;
    static String rekTuj;
    static String Nominal;

    ////////////////////////////////////////////////////
    static String no_transaksi;
    static String no_rekening;
    static String nama_kategori;
    static String id_user;
    static String no_rekTuju;
    static String nominal;
    static String date2;
    ///////////////////////////////////////////////////

    private Connection conn;

    LocalDate obj = LocalDate.now(); // Date or waktu
    String date = obj.toString(); // Waktu


    public MainPage() throws Exception {

        JPanel mainPage = new JPanel();
        setTitle("Main Application");
        setSize(800,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Panel Initialization
        JPanel mainPanel = new JPanel();
        JPanel sidePanel = new JPanel();
        JPanel accountDetailsPanel = new JPanel();
        JPanel withdrawPanel = new JPanel();
        JPanel depositPanel = new JPanel();
        JPanel transferMoneyPanel = new JPanel();
        JPanel transactionHistoryPanel = new JPanel();

        //SIDE MENU NAVIGATION
        JButton switchButton = new JButton("Log Out");
        JButton accountDetails = new JButton("Account Details");
        JButton depositCash  = new JButton("Deposit");
        JButton withdrawCash = new JButton("Withdraw");
        JButton transferMoney = new JButton("Transfer Money");
        JButton transactionHistory = new JButton("Transaction History");



        //Account Details Panel
        JLabel accountDetailsTitle = new JLabel("Account Details");
        JLabel accountSummaryLabel = new JLabel("Current Account Summary ");
        JLabel accountFullName = new JLabel();
        JLabel accountIDLabel = new JLabel();
        JLabel amountLabel= new JLabel();
        JLabel typeAccountLabel = new JLabel();




        //Withdraw panel
        JLabel withdrawTitle = new JLabel("Withdraw");
        JLabel currentBalancewithdrawLabel = new JLabel("");
        JLabel enterAmountwithdrawLabel = new JLabel("Enter Amount");
        JTextField enterAmountWithdrawField = new JTextField(){
            {
                setSize(200,30);
                setMaximumSize(getSize());
            }
        };
        JButton withdrawButton = new JButton("Withdraw") {
            {
                setSize(150,40);
                setMaximumSize(getSize());
            }
        };



        //Deposit panel
        JLabel depositTitleLabel = new JLabel("Deposit");
        JLabel currentBalanceDepositLabel = new JLabel();
        JLabel enterAmountDepositLabel = new JLabel("Enter Amount ");
        JTextField enterAmountDepositField = new  JTextField(){
            {
                setSize(200,30);
                setMaximumSize(getSize());
            }
        };
        JButton confirmDepositButton = new JButton("Confirm Deposit"){
            {
                setSize(150,40);
                setMaximumSize(getSize());
            }
        };
        confirmDepositButton.setSize(150,75);

        //Transfer Panel
        JLabel transferMoneyTitle = new JLabel("Transfer Money");
        JLabel accountIDTransferLabel = new JLabel("Enter Account ID");
        JTextField toAccountIDField = new JTextField(){
            {
                setSize(200,30);
                setMaximumSize(getSize());
            }
        };
        JLabel enterAmountTransferLabel = new JLabel("Enter Amount");
        JTextField enterAmountTransferField = new JTextField(){
            {
                setSize(200,30);
                setMaximumSize(getSize());
            }
        };
        JButton confirmTransferButton = new JButton("Transfer"){
            {
                setSize(150,40);
                setMaximumSize(getSize());
            }
        };


        String[] column2 = {"No. Transaction", "Account", "Category", "User ID", "To Account", "Amount", "DATE"};
        JTableHeader jtHeader = new JTableHeader();

        DefaultTableModel tblModel = new DefaultTableModel(0,6);
        tblModel.setColumnIdentifiers(column2);
        JTable jt = new JTable(tblModel);

        //JTable jt = new JTable(data,column);


        mainPanel.setLayout(new GridLayout(1,2));

        //SIDE PANEL
        sidePanel.setLayout(new GridLayout(6,1));
        sidePanel.setPreferredSize(new Dimension(200,500));
        sidePanel.add(accountDetails);
        sidePanel.add(withdrawCash);
        sidePanel.add(depositCash);
        sidePanel.add(transferMoney);
        sidePanel.add(transactionHistory);
        sidePanel.add(switchButton);
        mainPanel.add(sidePanel);


        //RIGHT PANEL
        JPanel rightPanel = new JPanel();
        CardLayout cards = new CardLayout();
        rightPanel.setLayout(cards);

        //Account Details Panel
        accountDetailsPanel.setLayout(new BoxLayout(accountDetailsPanel, BoxLayout.Y_AXIS));

        accountDetailsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        accountDetailsPanel.add(Box.createRigidArea(new Dimension(0,20)));
        accountDetailsTitle.setFont(new Font("Serif",Font.PLAIN, 40));
        accountDetailsPanel.add(accountDetailsTitle);

        accountDetailsPanel.add(Box.createRigidArea(new Dimension(0,50)));

        accountSummaryLabel.setFont(new Font("Serif",Font.PLAIN,20));
        accountSummaryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountDetailsPanel.add(accountSummaryLabel);

        accountDetailsPanel.add(Box.createRigidArea(new Dimension(0,10)));

        accountFullName.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountDetailsPanel.add(accountFullName);

        accountDetailsPanel.add(Box.createRigidArea(new Dimension(0,5)));

        accountIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountDetailsPanel.add(accountIDLabel);

        accountDetailsPanel.add(Box.createRigidArea(new Dimension(0,5)));

        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        accountDetailsPanel.add(amountLabel);

        accountDetailsPanel.add(Box.createRigidArea(new Dimension(0,5)));


        //withdraw Panel
        withdrawPanel.setLayout(new BoxLayout(withdrawPanel, BoxLayout.Y_AXIS));

        withdrawPanel.add(Box.createRigidArea(new Dimension(0,20)));
        withdrawTitle.setFont(new Font("Serif",Font.PLAIN,40));
        withdrawTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawPanel.add(withdrawTitle);

        withdrawPanel.add(Box.createRigidArea(new Dimension(0,50)));

        currentBalancewithdrawLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawPanel.add(currentBalancewithdrawLabel);

        withdrawPanel.add(Box.createRigidArea(new Dimension(0,20)));

        enterAmountwithdrawLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawPanel.add(enterAmountwithdrawLabel);

        withdrawPanel.add(Box.createRigidArea(new Dimension(0,10)));

        enterAmountWithdrawField.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawPanel.add(enterAmountWithdrawField);

        withdrawPanel.add(Box.createRigidArea(new Dimension(0,20)));

        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawPanel.add(withdrawButton);


        //Deposit Panel
        depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.Y_AXIS));

        depositPanel.add(Box.createRigidArea(new Dimension(0,20)));
        depositTitleLabel.setFont(new Font("Serif", Font.PLAIN, 40));
        depositTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositPanel.add(depositTitleLabel);

        depositPanel.add(Box.createRigidArea(new Dimension(0,50)));

        currentBalanceDepositLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositPanel.add(currentBalanceDepositLabel);

        depositPanel.add(Box.createRigidArea(new Dimension(0,20)));

        enterAmountDepositLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositPanel.add(enterAmountDepositLabel);

        depositPanel.add(Box.createRigidArea(new Dimension(0,10)));

        enterAmountDepositField.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositPanel.add(enterAmountDepositField);


        depositPanel.add(Box.createRigidArea(new Dimension(0,20)));

        confirmDepositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositPanel.add(confirmDepositButton);

        //Transfer Panel
        transferMoneyPanel.setLayout(new BoxLayout(transferMoneyPanel, BoxLayout.Y_AXIS));
        
        transferMoneyPanel.add(Box.createRigidArea(new Dimension(0,20)));
        transferMoneyTitle.setFont(new Font("Serif", Font.PLAIN, 40));
        transferMoneyTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferMoneyPanel.add(transferMoneyTitle);

        transferMoneyPanel.add(Box.createRigidArea(new Dimension(0,50)));

        accountIDTransferLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferMoneyPanel.add(accountIDTransferLabel);

        transferMoneyPanel.add(Box.createRigidArea(new Dimension(0,10)));

        toAccountIDField.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferMoneyPanel.add(toAccountIDField);

        transferMoneyPanel.add(Box.createRigidArea(new Dimension(0,20)));

        enterAmountTransferLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferMoneyPanel.add(enterAmountTransferLabel);

        transferMoneyPanel.add(Box.createRigidArea(new Dimension(0,10)));

        enterAmountTransferField.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferMoneyPanel.add(enterAmountTransferField);

        transferMoneyPanel.add(Box.createRigidArea(new Dimension(0,30)));

        confirmTransferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        transferMoneyPanel.add(confirmTransferButton);



        //Account Details
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                dispose();
            }
        });

        accountDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(rightPanel,"AccountDetails");
                accountFullName.setText("fullName: "+full_name2);
            }
        });

        withdrawCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cards.show(rightPanel,"Withdraw");
//                JOptionPane.showMessageDialog(null, "fullname = '"+full_name2+"'");
            }
        });

        // WITHDRAW
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Withdraw w = new Withdraw();
                    Nominal = enterAmountWithdrawField.getText();
                    Integer cek = Integer.parseInt(Nominal);

                    if (cek >= 10000) {
                        w.insertWithdraw(No_Rek2, "K01", id_user2, Nominal, date);
                        JOptionPane.showMessageDialog(null, "Transaction Successful");
                        int nmnl = Integer.parseInt(Nominal);
                        System.out.println(nmnl);
                        int norek = Integer.parseInt(No_Rek2);
                        System.out.println(norek);
                        int sldAwal = w.selectSaldo(norek);
                        System.out.println(sldAwal);
                        int akhir = sldAwal-nmnl;
                        System.out.println(akhir);

                        w.updateSaldo(akhir,norek);
                        enterAmountWithdrawField.setText("");
                    } else{
                        JOptionPane.showMessageDialog(null,"Please fill The Amount... ");
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/m-banking",
                            "root",
                            "");

                    Statement stmt = con.createStatement();

                    String sql2 = "select * from rekening where noRekening = '"+No_Rek2+"'";
                    ResultSet rs2 = stmt.executeQuery(sql2);


                    if(rs2.next()){
                        rek2 = rs2.getString(2);

                    }
                    currentBalancewithdrawLabel.setText("Current Balance: "+rek2);
                    currentBalanceDepositLabel.setText("Current Balance: "+rek2);
                    amountLabel.setText("Current Balance: "+rek2);

                    con.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });


        // DEPOSIT
        confirmDepositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Deposit d = new Deposit();
                    Nominal = enterAmountDepositField.getText();
                    Integer cek = Integer.parseInt(Nominal);

                    if (cek >= 10000) {
                        d.insertDeposit(No_Rek2, "K02", id_user2, Nominal, date);
                        JOptionPane.showMessageDialog(null, "Transaction Successful");
                        int nmnl = Integer.parseInt(Nominal);
                        System.out.println(nmnl);
                        int norek = Integer.parseInt(No_Rek2);
                        System.out.println(norek);
                        int sldAwal = d.selectSaldo(norek);
                        System.out.println(sldAwal);
                        int akhir = sldAwal+nmnl;
                        System.out.println(akhir);

                        d.updateSaldo(akhir,norek);
                        enterAmountDepositField.setText("");
                    } else{
                        JOptionPane.showMessageDialog(null,"Amount must be more than Rp 10.000... ");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/m-banking",
                            "root",
                            "");

                    Statement stmt = con.createStatement();

                    String sql2 = "select * from rekening where noRekening = '"+No_Rek2+"'";
                    ResultSet rs2 = stmt.executeQuery(sql2);


                    if(rs2.next()){
                        rek2 = rs2.getString(2);

                    }
                    currentBalancewithdrawLabel.setText("Current Balance: "+rek2);
                    currentBalanceDepositLabel.setText("Current Balance: "+rek2);
                    amountLabel.setText("Current Balance: "+rek2);

                    con.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        //Transfer
        confirmTransferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Transfer t = new Transfer();
                    Nominal = enterAmountTransferField.getText();
                    rekTuj = toAccountIDField.getText();
                    Integer cek = Integer.parseInt(Nominal);

                    if (cek >= 10000) {

                        t.insertTransfer(No_Rek2, "K03", id_user2, rekTuj, Nominal, date);
                        JOptionPane.showMessageDialog(null, "Transaction Successful");
                        int nmnl = Integer.parseInt(Nominal);
                        System.out.println(nmnl);
                        int norek = Integer.parseInt(No_Rek2);
                        System.out.println(norek);

                        int tujuan = Integer.parseInt(rekTuj);

                        t.transfer(norek,tujuan,nmnl);
                        toAccountIDField.setText("");
                        enterAmountTransferField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null,"Amount must be more than Rp 10.000... ");
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/m-banking",
                            "root",
                            "");

                    Statement stmt = con.createStatement();

                    String sql2 = "select * from rekening where noRekening = '"+No_Rek2+"'";
                    ResultSet rs2 = stmt.executeQuery(sql2);


                    if(rs2.next()){
                        rek2 = rs2.getString(2);

                    }
                    currentBalancewithdrawLabel.setText("Current Balance: "+rek2);
                    currentBalanceDepositLabel.setText("Current Balance: "+rek2);
                    amountLabel.setText("Current Balance: "+rek2);

                    con.close();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });



        depositCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(rightPanel,"deposit");
            }
        });

        transferMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(rightPanel,"transfer");
            }
        });

        transactionHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.show(rightPanel,"transactionHistory");

                jt.revalidate();
                jt.setTableHeader(jtHeader);
            }
        });


        //Add Icon to Jbutton
        try{
            Image imgAccount = ImageIO.read(getClass().getResource("account_48px.png"));
            Image imgWithdraw = ImageIO.read(getClass().getResource("money_48px.png"));
            Image imgDeposit = ImageIO.read(getClass().getResource("coin_in_hand_48px.png"));
            Image imgTransfer = ImageIO.read(getClass().getResource("initiate_money_transfer_48px.png"));
            Image imgHistory = ImageIO.read(getClass().getResource("payment_history_48px.png"));
            Image imgLogout = ImageIO.read(getClass().getResource("Logout_48px.png"));

            accountDetails.setIcon(new ImageIcon(imgAccount));
            accountDetails.setHorizontalAlignment(SwingConstants.LEFT);

            withdrawCash.setIcon(new ImageIcon(imgWithdraw));
            withdrawCash.setHorizontalAlignment(SwingConstants.LEFT);

            depositCash.setIcon(new ImageIcon(imgDeposit));
            depositCash.setHorizontalAlignment(SwingConstants.LEFT);

            transferMoney.setIcon(new ImageIcon(imgTransfer));
            transferMoney.setHorizontalAlignment(SwingConstants.LEFT);

            transactionHistory.setIcon(new ImageIcon(imgHistory));
            transactionHistory.setHorizontalAlignment(SwingConstants.LEFT);

            switchButton.setIcon(new ImageIcon(imgLogout));
            switchButton.setHorizontalAlignment(SwingConstants.LEFT);


        } catch (Exception ex){
            System.out.println("Image Not Found");
        }


        //Transaction History
        transactionHistoryPanel.add(new JScrollPane(jt));


        rightPanel.add(accountDetailsPanel,"AccountDetails");
        rightPanel.add(withdrawPanel,"Withdraw");
        rightPanel.add(depositPanel,"deposit");
        rightPanel.add(transferMoneyPanel,"transfer");
        rightPanel.add(transactionHistoryPanel,"transactionHistory");



        //Color Control
        accountDetailsPanel.setBackground(new Color(48, 54, 76));
        withdrawPanel.setBackground(new Color(48, 54, 76));
        depositPanel.setBackground(new Color(48, 54, 76));
        transferMoneyPanel.setBackground(new Color(48, 54, 76));
        transactionHistoryPanel.setBackground(new Color(48, 54, 76));

        accountDetailsTitle.setForeground(Color.white);
        accountSummaryLabel.setForeground(Color.white);
        accountFullName.setForeground(Color.white);
        accountIDLabel.setForeground(Color.white);
        amountLabel.setForeground(Color.white);
        typeAccountLabel.setForeground(Color.white);


        accountDetailsTitle.setForeground(Color.white);
        accountSummaryLabel.setForeground(Color.white);
        accountFullName.setForeground(Color.white);
        accountIDLabel.setForeground(Color.white);
        amountLabel.setForeground(Color.white);
        typeAccountLabel.setForeground(Color.white);

        withdrawTitle.setForeground(Color.white);
        currentBalancewithdrawLabel.setForeground(Color.white);
        enterAmountwithdrawLabel.setForeground(Color.white);

        depositTitleLabel.setForeground(Color.white);
        currentBalanceDepositLabel.setForeground(Color.white);
        enterAmountDepositLabel.setForeground(Color.white);

        transferMoneyTitle.setForeground(Color.white);
        accountIDTransferLabel.setForeground(Color.white);
        enterAmountTransferLabel.setForeground(Color.white);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);

                LocalDate obj = LocalDate.now(); // Date or waktu
                String date = obj.toString(); // Waktu


                accountFullName.setText("fullName: "+full_name2);
                accountIDLabel.setText("ID User: "+id_user2);
                amountLabel.setText("ID User: "+id_user2);
                currentBalancewithdrawLabel.setText("Current Balance: "+rek2);
                currentBalanceDepositLabel.setText("Current Balance: "+rek2);
                amountLabel.setText("Current Balance: "+rek2);


                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/m-banking",
                            "root",
                            ""
                    );

                    Statement stmt = conn.createStatement();
                    String sql = "select transaksi.*, kategori.idKategori, kategori.namaKategori from transaksi, kategori where transaksi.idKategori = kategori.idKategori and transaksi.noRekening = '"+No_Rek2+"' ";
                    ResultSet rs = stmt.executeQuery(sql);

                    while (rs.next()){
                        no_transaksi= String.valueOf(rs.getInt("noTransaksi"));
                        no_rekening= rs.getString("noRekening");
                        nama_kategori = rs.getString("namaKategori");
                        id_user = rs.getString("idUser");
                        no_rekTuju = rs.getString("noRekTujuan");
                        nominal = rs.getString("nominal");
                        date2 = rs.getString("Date");

                        String[] tbData = {no_transaksi, no_rekening, nama_kategori, id_user, no_rekTuju, nominal, date2};
                        tblModel.addRow(tbData);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });


        //Viewing
        add(mainPage);
        mainPanel.add(rightPanel);
        add(mainPanel);
        add(sidePanel, BorderLayout.WEST);
        setVisible(true);




    }




}
