package fees_management_system;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author suraj
 */
public class AddFees extends javax.swing.JFrame {

    /**
     * Creates new form AddFees
     */
    public AddFees() {
        initComponents();
        addcashfirst();
        fillCourseCombobox();
        int receipt_no = getReceiptNo();
        txt_receiptno.setText(Integer.toString(receipt_no));
    }
    
    public void addcashfirst(){
        
        lbl_dd.setVisible(false);
        lbl_bankname.setVisible(false);
        lbl_chequeno.setVisible(false);
        
        txt_ddno.setVisible(false);
        txt_chequeno.setVisible(false);
        txt_bankname.setVisible(false);
    }
    
    public boolean validation(){
        
        if(txt_receivedfrom.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter user name");
            return false;
        }
        
        if(txt_date.getDate() == null){
            JOptionPane.showMessageDialog(this, "Please select a date");
            return false;
        }
        
        if (txt_amount.getText().equals("") || txt_amount.getText().matches("[0-9]+")==false){
            JOptionPane.showMessageDialog(this, "Please enter the amount(in digits)");
            return false;
        }
        
        if(combobox_paymentmode.getSelectedItem().toString().equalsIgnoreCase("dd")){
            
            if(txt_ddno.getText().equals("") || txt_ddno.getText().matches("^[0-9]{6}$")==false){
                JOptionPane.showMessageDialog(this, "Please enter valid DD no");
                return false;
            }
            
             if(txt_bankname.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter bank name");
                return false;
            }
        }
        
        if(combobox_paymentmode.getSelectedItem().toString().equalsIgnoreCase("cheque")){
            
            if(txt_chequeno.getText().equals("") || txt_chequeno.getText().matches("^[0-9]{6}$")==false){
                JOptionPane.showMessageDialog(this, "Please enter valid Cheque no");
                return false;
            }
            
             if(txt_bankname.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter bank name");
                return false;
            }
        }
                
        if(combobox_paymentmode.getSelectedItem().toString().equalsIgnoreCase("credit/debit card")){
          
            
             if(txt_bankname.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please enter bank name");
                return false;
            }
        }
        
        if ((txt_year1.getText().matches("[0-9]+")==false) || (txt_year2.getText().matches("[0-9]+")==false) || txt_year1.getText().length()>4 || txt_year2.getText().length()>4){
            JOptionPane.showMessageDialog(this, "Please enter valid year");
                return false;
    }
        
        return true;
       
    }

    public void fillCourseCombobox(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management","root","pass123");
            PreparedStatement pst = con.prepareStatement("Select cname from course;");
           ResultSet rs =  pst.executeQuery();
            
           while(rs.next()){
               combo_course.addItem(rs.getString("cname"));
           }
                                            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int getReceiptNo(){
        int receipt_no = 0;
        
        try {
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("Select MAX(receipt_no) from fees_details;");
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()==true){
                receipt_no = rs.getInt(1);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receipt_no +1;
    }
    
    
    public String insertData(){
        
        
        String status = "";
        
        int receiptno = Integer.parseInt(txt_receiptno.getText());
        String studentname = txt_receivedfrom.getText();
        int rollno = Integer.parseInt(txt_rollno.getText());
        String paymentmode = combobox_paymentmode.getSelectedItem().toString();
        String chequeno = txt_chequeno.getText();
        String bankname = txt_bankname.getText();
        String ddno = txt_ddno.getText();
        String coursename = combo_course.getSelectedItem().toString();
        String gstin = lbl_gstin.getText();
        Float totalamount = Float.parseFloat(txt_total.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY:MM:dd");
        String date = dateFormat.format(txt_date.getDate());
        float initialamount = Float.parseFloat(txt_amount.getText());
        float cgst = Float.parseFloat(txt_cgst.getText());
        float sgst = Float.parseFloat(txt_sgst.getText());
        String totalinwords = txt_totalinwords.getText();
        String remark = txt_remarks.getText();
        int year1 = Integer.parseInt(txt_year1.getText());
        int year2 = Integer.parseInt(txt_year2.getText());
        
        
        try {
            
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("Insert into fees_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setInt(1, receiptno);
            pst.setString(2, studentname);
            pst.setString(3, paymentmode);
            pst.setString(4, chequeno);
            pst.setString(5, bankname);
            pst.setString(6, ddno);
            pst.setString(7, coursename);
            pst.setString(8,gstin);
            pst.setFloat(9, totalamount);
            pst.setString(10, date);
            pst.setFloat(11,initialamount);
            pst.setFloat(12, cgst);
            pst.setFloat(13, sgst);
            pst.setString(14, totalinwords);
            pst.setString(15, remark);
            pst.setInt(16, year1);
            pst.setInt(17, year2);
            pst.setInt(18, rollno);
            
            int rowcount = pst.executeUpdate();
            
            if (rowcount==1){
                status = "Success";
            }else{
                status="Failed";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }    
                
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        panelsideBar = new javax.swing.JPanel();
        panelHome = new javax.swing.JPanel();
        btnHome = new javax.swing.JLabel();
        panelLogout = new javax.swing.JPanel();
        btnHome1 = new javax.swing.JLabel();
        panelSearchrecord = new javax.swing.JPanel();
        btnHome14 = new javax.swing.JLabel();
        panelEditcourse = new javax.swing.JPanel();
        btnHome66 = new javax.swing.JLabel();
        panelViewallRec = new javax.swing.JPanel();
        btnHome170 = new javax.swing.JLabel();
        panelBack = new javax.swing.JPanel();
        btnHome222 = new javax.swing.JLabel();
        panelParent = new javax.swing.JPanel();
        lbl_date = new javax.swing.JLabel();
        lbl_receiptno = new javax.swing.JLabel();
        lbl_paymentmode = new javax.swing.JLabel();
        lbl_dd = new javax.swing.JLabel();
        lbl_chequeno = new javax.swing.JLabel();
        lbl_gstin = new javax.swing.JLabel();
        txt_receiptno = new javax.swing.JTextField();
        txt_ddno = new javax.swing.JTextField();
        txt_chequeno = new javax.swing.JTextField();
        lbl_gstno = new javax.swing.JLabel();
        combobox_paymentmode = new javax.swing.JComboBox<>();
        txt_date = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbl_receivedfrom = new javax.swing.JLabel();
        txt_year1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_year2 = new javax.swing.JTextField();
        txt_totalinwords = new javax.swing.JTextField();
        lbl_rollno = new javax.swing.JLabel();
        combo_course = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lbl_course = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_receivedfrom = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        txt_amount = new javax.swing.JTextField();
        txt_cgst = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        txt_sgst = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_coursename = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_remarks = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        btn_print = new javax.swing.JButton();
        txt_rollno = new javax.swing.JTextField();
        lbl_bankname = new javax.swing.JLabel();
        txt_bankname = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsideBar.setBackground(new java.awt.Color(0, 102, 102));
        panelsideBar.setPreferredSize(new java.awt.Dimension(540, 1040));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(0, 102, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHomeMouseExited(evt);
            }
        });
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Mongolian Baiti", 0, 30)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/home.png"))); // NOI18N
        btnHome.setText("  Home");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });
        panelHome.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 50));

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 330, 70));

        panelLogout.setBackground(new java.awt.Color(0, 102, 102));
        panelLogout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLogoutMouseExited(evt);
            }
        });
        panelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome1.setFont(new java.awt.Font("Mongolian Baiti", 0, 30)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(255, 255, 255));
        btnHome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/logout.png"))); // NOI18N
        btnHome1.setText("  Logout");
        btnHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome1MouseClicked(evt);
            }
        });
        panelLogout.add(btnHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 50));

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 570, 330, 70));

        panelSearchrecord.setBackground(new java.awt.Color(0, 102, 102));
        panelSearchrecord.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelSearchrecord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSearchrecordMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSearchrecordMouseExited(evt);
            }
        });
        panelSearchrecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome14.setFont(new java.awt.Font("Mongolian Baiti", 0, 30)); // NOI18N
        btnHome14.setForeground(new java.awt.Color(255, 255, 255));
        btnHome14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/search2.png"))); // NOI18N
        btnHome14.setText("  Search Record");
        btnHome14.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btnHome14MouseDragged(evt);
            }
        });
        btnHome14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome14MouseClicked(evt);
            }
        });
        panelSearchrecord.add(btnHome14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 50));

        panelsideBar.add(panelSearchrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 330, 70));

        panelEditcourse.setBackground(new java.awt.Color(0, 102, 102));
        panelEditcourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelEditcourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelEditcourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelEditcourseMouseExited(evt);
            }
        });
        panelEditcourse.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome66.setFont(new java.awt.Font("Mongolian Baiti", 0, 30)); // NOI18N
        btnHome66.setForeground(new java.awt.Color(255, 255, 255));
        btnHome66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/edit2.png"))); // NOI18N
        btnHome66.setText("  Edit Courses");
        btnHome66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome66MouseClicked(evt);
            }
        });
        panelEditcourse.add(btnHome66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 50));

        panelsideBar.add(panelEditcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 330, 70));

        panelViewallRec.setBackground(new java.awt.Color(0, 102, 102));
        panelViewallRec.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelViewallRec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViewallRecMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViewallRecMouseExited(evt);
            }
        });
        panelViewallRec.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome170.setFont(new java.awt.Font("Mongolian Baiti", 0, 30)); // NOI18N
        btnHome170.setForeground(new java.awt.Color(255, 255, 255));
        btnHome170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/view all record.png"))); // NOI18N
        btnHome170.setText(" View All Records");
        btnHome170.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome170MouseClicked(evt);
            }
        });
        panelViewallRec.add(btnHome170, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 50));

        panelsideBar.add(panelViewallRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 330, 70));

        panelBack.setBackground(new java.awt.Color(0, 102, 102));
        panelBack.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBackMouseExited(evt);
            }
        });
        panelBack.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome222.setFont(new java.awt.Font("Mongolian Baiti", 0, 30)); // NOI18N
        btnHome222.setForeground(new java.awt.Color(255, 255, 255));
        btnHome222.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/back1.png"))); // NOI18N
        btnHome222.setText("  Back");
        btnHome222.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome222MouseClicked(evt);
            }
        });
        panelBack.add(btnHome222, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 50));

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 330, 70));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelParent.setBackground(new java.awt.Color(204, 204, 204));
        panelParent.setPreferredSize(new java.awt.Dimension(1310, 1040));
        panelParent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_date.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_date.setText("Date : ");
        panelParent.add(lbl_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 70, -1));

        lbl_receiptno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_receiptno.setText("Receipt No. :  GMVIT - ");
        panelParent.add(lbl_receiptno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 180, -1));

        lbl_paymentmode.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_paymentmode.setText("Mode of payment. : ");
        panelParent.add(lbl_paymentmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, -1));

        lbl_dd.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_dd.setText("DD : ");
        panelParent.add(lbl_dd, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, 20));

        lbl_chequeno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_chequeno.setText("Cheque No. : ");
        panelParent.add(lbl_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, -1));

        lbl_gstin.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_gstin.setText("64326GDYR4185");
        panelParent.add(lbl_gstin, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 150, -1));

        txt_receiptno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_receiptno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receiptnoActionPerformed(evt);
            }
        });
        panelParent.add(txt_receiptno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 150, 30));

        txt_ddno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        panelParent.add(txt_ddno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 200, 30));

        txt_chequeno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_chequeno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_chequenoActionPerformed(evt);
            }
        });
        panelParent.add(txt_chequeno, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 180, 30));

        lbl_gstno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_gstno.setText("GSTIN No. : ");
        panelParent.add(lbl_gstno, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, 100, -1));

        combobox_paymentmode.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        combobox_paymentmode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cash", "Cheque", "Credit/Debit card" }));
        combobox_paymentmode.setSelectedIndex(1);
        combobox_paymentmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_paymentmodeActionPerformed(evt);
            }
        });
        panelParent.add(combobox_paymentmode, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 170, 30));

        txt_date.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        panelParent.add(txt_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 170, 30));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel6.setText("The following payment in the college office for the year ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 410, 20));

        lbl_receivedfrom.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_receivedfrom.setText("Received from :");
        jPanel1.add(lbl_receivedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 150, -1));

        txt_year1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year1ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(544, 50, 70, 30));

        jLabel11.setText("     __");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 30, -1));

        txt_year2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_year2ActionPerformed(evt);
            }
        });
        jPanel1.add(txt_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 70, 30));

        txt_totalinwords.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPanel1.add(txt_totalinwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 394, 270, 30));

        lbl_rollno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_rollno.setText("Roll No : ");
        jPanel1.add(lbl_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        combo_course.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        combo_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_courseActionPerformed(evt);
            }
        });
        jPanel1.add(combo_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 240, 30));

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel13.setText("Amount (Rs.)");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, -1, -1));

        jSeparator2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(255, 204, 204), null, null));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 740, 40));

        lbl_course.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_course.setText("Course :");
        jPanel1.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel15.setText("Receiver's Signature");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 470, -1, -1));

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel16.setText("Heads");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, -1, -1));

        txt_receivedfrom.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_receivedfrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_receivedfromActionPerformed(evt);
            }
        });
        jPanel1.add(txt_receivedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 4, 230, 30));

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel17.setText("Sr. No.");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, -1, -1));

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel18.setText("CGST  9% ");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        txt_total.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPanel1.add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 110, -1));

        txt_amount.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_amountActionPerformed(evt);
            }
        });
        jPanel1.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 110, -1));

        txt_cgst.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPanel1.add(txt_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 110, -1));

        jSeparator5.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 200, 20));

        txt_sgst.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPanel1.add(txt_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, 110, -1));

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel19.setText("SGST  9%");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 320, -1, -1));

        txt_coursename.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_coursename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coursenameActionPerformed(evt);
            }
        });
        jPanel1.add(txt_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 250, 30));

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel20.setText("Total in words");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, -1, -1));

        txt_remarks.setColumns(20);
        txt_remarks.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_remarks.setRows(5);
        jScrollPane1.setViewportView(txt_remarks);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 460, 240, 60));

        jSeparator6.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 190, 10));

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel21.setText("Remarks : ");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, 20));

        btn_print.setBackground(new java.awt.Color(102, 102, 102));
        btn_print.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("Save");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel1.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 470, 90, 40));

        txt_rollno.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txt_rollno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rollnoActionPerformed(evt);
            }
        });
        jPanel1.add(txt_rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 100, 30));

        panelParent.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 1480, 790));

        lbl_bankname.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_bankname.setText("Bank name : ");
        panelParent.add(lbl_bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 210, 150, 20));

        txt_bankname.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        panelParent.add(txt_bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 240, 30));

        getContentPane().add(panelParent, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 1480, -1));

        setSize(new java.awt.Dimension(1614, 907));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseEntered
        Color clr = new Color(0,153,153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseEntered

    private void panelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseExited
        Color clr = new Color(0,102,102);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseExited

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered
        Color clr = new Color(0,153,153);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited
        Color clr = new Color(0,102,102);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseExited

    private void panelSearchrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchrecordMouseEntered
        Color clr = new Color(0,153,153);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchrecordMouseEntered

    private void panelSearchrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchrecordMouseExited
        Color clr = new Color(0,102,102);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchrecordMouseExited

    private void panelEditcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditcourseMouseEntered
        Color clr = new Color(0,153,153);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_panelEditcourseMouseEntered

    private void panelEditcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditcourseMouseExited
        Color clr = new Color(0,102,102);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_panelEditcourseMouseExited

    private void panelViewallRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewallRecMouseEntered
        Color clr = new Color(0,153,153);
        panelViewallRec.setBackground(clr);
    }//GEN-LAST:event_panelViewallRecMouseEntered

    private void panelViewallRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewallRecMouseExited
        Color clr = new Color(0,102,102);
        panelViewallRec.setBackground(clr);
    }//GEN-LAST:event_panelViewallRecMouseExited

    private void panelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseEntered
        Color clr = new Color(0,153,153);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseEntered

    private void panelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseExited
        Color clr = new Color(0,102,102);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseExited

    private void txt_receiptnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receiptnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receiptnoActionPerformed

    private void txt_chequenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_chequenoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_chequenoActionPerformed

    private void txt_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year1ActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        if(validation()==true){
           String result = insertData();
           
            if (result.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(this, "Registered successfully!!");
                
                PrintReceipt pr = new PrintReceipt();
                pr.setVisible(true);
                this.dispose();
                
            }else{
                JOptionPane.showMessageDialog(this, "Registration failed");
            }
           
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void txt_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_year2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_year2ActionPerformed

    private void txt_receivedfromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_receivedfromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_receivedfromActionPerformed

    private void combobox_paymentmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_paymentmodeActionPerformed
        
        if (combobox_paymentmode.getSelectedIndex()==0){
            lbl_dd.setVisible(true);
            txt_ddno.setVisible(true);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            lbl_chequeno.setVisible(false);
            txt_chequeno.setVisible(false);
        }
        
        if (combobox_paymentmode.getSelectedIndex()==1){
            lbl_chequeno.setVisible(false);
            txt_chequeno.setVisible(false);
            lbl_dd.setVisible(false);
            txt_ddno.setVisible(false);
            lbl_bankname.setVisible(false);
            txt_bankname.setVisible(false);
            
        }
        
        if (combobox_paymentmode.getSelectedIndex()==2){
            lbl_chequeno.setVisible(true);
            txt_chequeno.setVisible(true);
            lbl_dd.setVisible(false);
            txt_ddno.setVisible(false);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            
        }
        
        
        
        if (combobox_paymentmode.getSelectedIndex()==3){
            lbl_chequeno.setVisible(false);
            txt_chequeno.setVisible(false);
            lbl_dd.setVisible(false);
            txt_ddno.setVisible(false);
            lbl_bankname.setVisible(true);
            txt_bankname.setVisible(true);
            
        }
        
        
    }//GEN-LAST:event_combobox_paymentmodeActionPerformed

    private void txt_amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_amountActionPerformed
        Float amnt = Float.parseFloat(txt_amount.getText());
        
        float cgst = (float)(amnt *0.09);
        float sgst = (float)(amnt *0.09);
        
        txt_cgst.setText(Float.toString(cgst));
        txt_sgst.setText(Float.toString(sgst));
        
        float total = amnt + cgst+ sgst;
        
        txt_total.setText(Float.toString(total));
        
        txt_totalinwords.setText(NumberToWordsConverter.convert((int)total)+ " Only");
        
    }//GEN-LAST:event_txt_amountActionPerformed

    private void txt_rollnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rollnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rollnoActionPerformed

    private void txt_coursenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coursenameActionPerformed
        
    }//GEN-LAST:event_txt_coursenameActionPerformed

    private void combo_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_courseActionPerformed
        txt_coursename.setText(combo_course.getSelectedItem().toString());
    }//GEN-LAST:event_combo_courseActionPerformed

    private void btnHome14MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome14MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHome14MouseDragged

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
         Home h = new Home();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome1MouseClicked
        // TODO add your handling code here:
        Login_page login = new Login_page();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome1MouseClicked

    private void btnHome222MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome222MouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome222MouseClicked

    private void btnHome14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome14MouseClicked
        // TODO add your handling code here:
        SearchRecord sr = new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome14MouseClicked

    private void btnHome66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome66MouseClicked
        // TODO add your handling code here:
        EditCourse ec = new EditCourse();
        ec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome66MouseClicked

    private void btnHome170MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome170MouseClicked
        // TODO add your handling code here:
        viewAllRecords var = new viewAllRecords();
        var.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome170MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddFees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddFees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnHome;
    private javax.swing.JLabel btnHome1;
    private javax.swing.JLabel btnHome14;
    private javax.swing.JLabel btnHome170;
    private javax.swing.JLabel btnHome222;
    private javax.swing.JLabel btnHome66;
    private javax.swing.JButton btn_print;
    private javax.swing.JComboBox<String> combo_course;
    private javax.swing.JComboBox<String> combobox_paymentmode;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lbl_bankname;
    private javax.swing.JLabel lbl_chequeno;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_dd;
    private javax.swing.JLabel lbl_gstin;
    private javax.swing.JLabel lbl_gstno;
    private javax.swing.JLabel lbl_paymentmode;
    private javax.swing.JLabel lbl_receiptno;
    private javax.swing.JLabel lbl_receivedfrom;
    private javax.swing.JLabel lbl_rollno;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelEditcourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelParent;
    private javax.swing.JPanel panelSearchrecord;
    private javax.swing.JPanel panelViewallRec;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_bankname;
    private javax.swing.JTextField txt_cgst;
    private javax.swing.JTextField txt_chequeno;
    private javax.swing.JTextField txt_coursename;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_ddno;
    private javax.swing.JTextField txt_receiptno;
    private javax.swing.JTextField txt_receivedfrom;
    private javax.swing.JTextArea txt_remarks;
    private javax.swing.JTextField txt_rollno;
    private javax.swing.JTextField txt_sgst;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_totalinwords;
    private javax.swing.JTextField txt_year1;
    private javax.swing.JTextField txt_year2;
    // End of variables declaration//GEN-END:variables
}
