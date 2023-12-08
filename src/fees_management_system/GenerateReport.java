/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fees_management_system;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author suraj
 */
public class GenerateReport extends javax.swing.JFrame {

    /**
     * Creates new form GenerateReport
     */
    public GenerateReport() {
        initComponents();
        fillCourseCombobox();
    }
    
    DefaultTableModel model;
    
    public void fillCourseCombobox(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management","root","pass123");
            PreparedStatement pst = con.prepareStatement("Select cname from course;");
           ResultSet rs =  pst.executeQuery();
            
           while(rs.next()){
               combo_courseDetails.addItem(rs.getString("cname"));
           }
                                            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void setDataToTable(){  
        try {
            
            String cname = combo_courseDetails.getSelectedItem().toString();
            SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
            String fromDate = format.format(datechooser_from.getDate());
            String toDate = format.format(datechooser_to.getDate());
            
            
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from fees_details where date between ? and ? and courses = ?");
            
            pst.setString(1,fromDate );
            pst.setString(2, toDate);
            pst.setString(3, cname);
            
            ResultSet rs = pst.executeQuery();
            
            Float totalAmount = 0.0f;
            
            while (rs.next()){
                
                String receiptNo = rs.getString("receipt_no");
                String rollNo = rs.getString("roll_no");
                String studentname = rs.getString("student_name");
                String course = rs.getString("courses");             
                int amount  =rs.getInt("total_amount");
                String remark = rs.getString("remark");
               
                lbl_courseSelected.setText(cname);
                
                totalAmount = totalAmount+ amount;
                lbl_totalamount.setText(totalAmount.toString());
                lbl_totalinwords.setText(NumberToWordsConverter.convert(totalAmount.intValue()));
                
                Object[] obj = {receiptNo,rollNo,studentname,course,amount,remark};
                 
                model = (DefaultTableModel)tbl_records.getModel();
                model.addRow(obj);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
     public void clearTable(){
        
        DefaultTableModel model = (DefaultTableModel)tbl_records.getModel();
        model.setRowCount(1);
    }
     
     
    public void exportToExcel(){
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet ws = wb.createSheet();
        DefaultTableModel model = (DefaultTableModel)tbl_records.getModel();
        
        TreeMap<String,Object[]> map = new TreeMap<>();
        
        map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),
            model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
        
        for (int i = 1; i < model.getRowCount(); i++) {
            
            map.put(Integer.toString(i), new Object[]{model.getValueAt(i, 0),model.getValueAt(i, 1),
            model.getValueAt(i, 2),model.getValueAt(i, 3),model.getValueAt(i, 4),model.getValueAt(i, 5)});
        }
        
        Set<String> id = map.keySet();
        
        XSSFRow frow;
        
        int rowId = 0;
        
        for (String key :id){
            
            frow = ws.createRow(rowId++);
            
            Object[] value = map.get(key);
            
            int cellId = 0;
            
            for (Object object : value){
                XSSFCell cell = frow.createCell(cellId++);
                cell.setCellValue(object.toString());
            }
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(new File(lbl_filepath.getText()));
            wb.write(fos);
            wb.close();
            JOptionPane.showMessageDialog(this, "File exported successfully :"+ lbl_filepath.getText());
     
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        datechooser_from = new com.toedter.calendar.JDateChooser();
        datechooser_to = new com.toedter.calendar.JDateChooser();
        btn_submit = new javax.swing.JButton();
        btn_print = new javax.swing.JButton();
        lbl_filepath = new javax.swing.JTextField();
        btn_browse = new javax.swing.JButton();
        btn_exportToExcel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_records = new javax.swing.JTable();
        combo_courseDetails = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_totalamount = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_totalinwords = new javax.swing.JLabel();
        lbl_courseSelected = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelsideBar.setBackground(new java.awt.Color(0, 102, 102));
        panelsideBar.setPreferredSize(new java.awt.Dimension(540, 1040));
        panelsideBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelHome.setBackground(new java.awt.Color(0, 102, 102));
        panelHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.white, null, null));
        panelHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelHomeMouseExited(evt);
            }
        });
        panelHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHome.setFont(new java.awt.Font("Mongolian Baiti", 0, 28)); // NOI18N
        btnHome.setForeground(new java.awt.Color(255, 255, 255));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/home.png"))); // NOI18N
        btnHome.setText("  Home");
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
        });
        panelHome.add(btnHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 330, 50));

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

        btnHome1.setFont(new java.awt.Font("Mongolian Baiti", 0, 28)); // NOI18N
        btnHome1.setForeground(new java.awt.Color(255, 255, 255));
        btnHome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/logout.png"))); // NOI18N
        btnHome1.setText("  Logout");
        btnHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome1MouseClicked(evt);
            }
        });
        panelLogout.add(btnHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 330, 50));

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

        btnHome14.setFont(new java.awt.Font("Mongolian Baiti", 0, 28)); // NOI18N
        btnHome14.setForeground(new java.awt.Color(255, 255, 255));
        btnHome14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/search2.png"))); // NOI18N
        btnHome14.setText("  Search Record");
        btnHome14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome14MouseClicked(evt);
            }
        });
        panelSearchrecord.add(btnHome14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        panelsideBar.add(panelSearchrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 330, 50));

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

        btnHome66.setFont(new java.awt.Font("Mongolian Baiti", 0, 28)); // NOI18N
        btnHome66.setForeground(new java.awt.Color(255, 255, 255));
        btnHome66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/edit2.png"))); // NOI18N
        btnHome66.setText("  Edit Courses");
        btnHome66.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome66MouseClicked(evt);
            }
        });
        panelEditcourse.add(btnHome66, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        panelsideBar.add(panelEditcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 330, 50));

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

        btnHome170.setFont(new java.awt.Font("Mongolian Baiti", 0, 28)); // NOI18N
        btnHome170.setForeground(new java.awt.Color(255, 255, 255));
        btnHome170.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/view all record.png"))); // NOI18N
        btnHome170.setText(" View All Records");
        btnHome170.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome170MouseClicked(evt);
            }
        });
        panelViewallRec.add(btnHome170, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 30));

        panelsideBar.add(panelViewallRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 330, 50));

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

        btnHome222.setFont(new java.awt.Font("Mongolian Baiti", 0, 28)); // NOI18N
        btnHome222.setForeground(new java.awt.Color(255, 255, 255));
        btnHome222.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/back1.png"))); // NOI18N
        btnHome222.setText("  Back");
        btnHome222.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHome222MouseClicked(evt);
            }
        });
        panelBack.add(btnHome222, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 30));

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 330, 50));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 810));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel1.setText("Select Date :  From Date: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 30));

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel3.setText("Select Course :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 150, 30));

        datechooser_from.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPanel1.add(datechooser_from, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 160, 30));

        datechooser_to.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jPanel1.add(datechooser_to, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 150, 30));

        btn_submit.setBackground(new java.awt.Color(0, 102, 102));
        btn_submit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_submit.setForeground(new java.awt.Color(255, 255, 255));
        btn_submit.setText("Submit");
        btn_submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_submitMouseClicked(evt);
            }
        });
        jPanel1.add(btn_submit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 100, 40));

        btn_print.setBackground(new java.awt.Color(0, 102, 102));
        btn_print.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_print.setForeground(new java.awt.Color(255, 255, 255));
        btn_print.setText("Print");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        jPanel1.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 90, 40));

        lbl_filepath.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lbl_filepath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_filepathActionPerformed(evt);
            }
        });
        jPanel1.add(lbl_filepath, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 270, 40));

        btn_browse.setBackground(new java.awt.Color(0, 102, 102));
        btn_browse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_browse.setForeground(new java.awt.Color(255, 255, 255));
        btn_browse.setText("Browse");
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });
        jPanel1.add(btn_browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 80, 40));

        btn_exportToExcel.setBackground(new java.awt.Color(0, 102, 102));
        btn_exportToExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_exportToExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_exportToExcel.setText("Export to Excel");
        btn_exportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportToExcelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_exportToExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, 40));

        tbl_records.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tbl_records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Receipt No", "Roll No", "Student Name", "Course", "Amount", "Remark"
            }
        ));
        jScrollPane1.setViewportView(tbl_records);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 1080, 450));

        combo_courseDetails.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jPanel1.add(combo_courseDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 390, 30));

        jPanel2.setBackground(new java.awt.Color(0, 180, 180));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 180, 180));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 290, 310));

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel5.setText("To Date :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 90, 30));

        lbl_totalamount.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        lbl_totalamount.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(lbl_totalamount, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 180, 30));

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course Selected :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 170, 30));

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Total Amount Collected :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 230, 30));

        jLabel9.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total Amount In Words : ");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 240, 30));

        lbl_totalinwords.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        lbl_totalinwords.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(lbl_totalinwords, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 440, 30));

        lbl_courseSelected.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        lbl_courseSelected.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.add(lbl_courseSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 240, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, 10));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 260, 10));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 240, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 460, 240));

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 16)); // NOI18N
        jLabel6.setText("To Date:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 70, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 1130, 810));

        setSize(new java.awt.Dimension(1549, 817));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        Home h = new Home();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomeMouseClicked

    private void panelHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseClicked

    }//GEN-LAST:event_panelHomeMouseClicked

    private void panelHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseEntered
        Color clr = new Color(0, 153, 153);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseEntered

    private void panelHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelHomeMouseExited
        Color clr = new Color(0, 102, 102);
        panelHome.setBackground(clr);
    }//GEN-LAST:event_panelHomeMouseExited

    private void panelLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseEntered
        Color clr = new Color(0, 153, 153);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseEntered

    private void panelLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLogoutMouseExited
        Color clr = new Color(0, 102, 102);
        panelLogout.setBackground(clr);
    }//GEN-LAST:event_panelLogoutMouseExited

    private void btnHome14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome14MouseClicked
        SearchRecord sr = new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome14MouseClicked

    private void panelSearchrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchrecordMouseEntered
        Color clr = new Color(0, 153, 153);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchrecordMouseEntered

    private void panelSearchrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchrecordMouseExited
        Color clr = new Color(0, 102, 102);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchrecordMouseExited

    private void btnHome66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome66MouseClicked
        EditCourse editCourse = new EditCourse();
        editCourse.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome66MouseClicked

    private void panelEditcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditcourseMouseEntered
        Color clr = new Color(0, 153, 153);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_panelEditcourseMouseEntered

    private void panelEditcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditcourseMouseExited
        Color clr = new Color(0, 102, 102);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_panelEditcourseMouseExited

    private void btnHome170MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome170MouseClicked
        viewAllRecords vAllRecords = new viewAllRecords();
        vAllRecords.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome170MouseClicked

    private void panelViewallRecMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewallRecMouseEntered
        Color clr = new Color(0, 153, 153);
        panelViewallRec.setBackground(clr);
    }//GEN-LAST:event_panelViewallRecMouseEntered

    private void panelViewallRecMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViewallRecMouseExited
        Color clr = new Color(0, 102, 102);
        panelViewallRec.setBackground(clr);
    }//GEN-LAST:event_panelViewallRecMouseExited

    private void panelBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseEntered
        Color clr = new Color(0, 153, 153);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseEntered

    private void panelBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBackMouseExited
        Color clr = new Color(0, 102, 102);
        panelBack.setBackground(clr);
    }//GEN-LAST:event_panelBackMouseExited

    private void lbl_filepathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_filepathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_filepathActionPerformed

    private void btn_exportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportToExcelActionPerformed
        // TODO add your handling code here:
        exportToExcel();
    }//GEN-LAST:event_btn_exportToExcelActionPerformed

    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = dateFormat.format(new Date());
        
        try {
            File f = fileChooser.getSelectedFile();
            String path = f.getAbsolutePath();
            path = path+"_"+date+".xlsx";
            
            lbl_filepath.setText(path);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }//GEN-LAST:event_btn_browseActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
       
        SimpleDateFormat Date_Format = new SimpleDateFormat("YYYY-MM-dd"); 
        String datefrom =  Date_Format.format(datechooser_from.getDate());
        String dateto =  Date_Format.format(datechooser_to.getDate());
       
        MessageFormat header=new MessageFormat("Report From "+datefrom+" To " +dateto);
        MessageFormat footer=new MessageFormat("page{0,number,integer}");
        try {
            tbl_records.print(JTable.PrintMode.FIT_WIDTH, header, footer);
            
        } catch (Exception e) {
            e.getMessage();
        } 
    }//GEN-LAST:event_btn_printActionPerformed

    private void btn_submitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_submitMouseClicked
        clearTable();
        setDataToTable();
    }//GEN-LAST:event_btn_submitMouseClicked

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
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateReport().setVisible(true);
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
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_exportToExcel;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_submit;
    private javax.swing.JComboBox<String> combo_courseDetails;
    private com.toedter.calendar.JDateChooser datechooser_from;
    private com.toedter.calendar.JDateChooser datechooser_to;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_courseSelected;
    private javax.swing.JTextField lbl_filepath;
    private javax.swing.JLabel lbl_totalamount;
    private javax.swing.JLabel lbl_totalinwords;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelEditcourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelSearchrecord;
    private javax.swing.JPanel panelViewallRec;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTable tbl_records;
    // End of variables declaration//GEN-END:variables
}
