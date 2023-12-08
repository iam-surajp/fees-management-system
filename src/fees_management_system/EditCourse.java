/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fees_management_system;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author suraj
 */
public class EditCourse extends javax.swing.JFrame {

    /**
     * Creates new form EditCourse
     */
    
    DefaultTableModel model;
    
    public EditCourse() {
        initComponents();
        setDataToTable();
        
        err_courseid.setVisible(false);
        err_coursename.setVisible(false);
        err_courseprice.setVisible(false);
    }
    
    
    public boolean validation(){
 
        if(txt_courseId.getText().isEmpty() || txt_courseId.getText().matches("[0-9]+")==false){
            err_courseid.setVisible(true);
            err_coursename.setVisible(false);
            err_courseprice.setVisible(false);
            return false;
        }
        
        
        if(txt_courseName.getText().equals("")){
            err_courseid.setVisible(false);
            err_coursename.setVisible(true);
            err_courseprice.setVisible(false);
            return false;
        }
        
        if(txt_coursePrice.getText().equals("") || txt_coursePrice.getText().matches("[0-9.]+")==false){
            err_courseid.setVisible(false);
            err_coursename.setVisible(false);
            err_courseprice.setVisible(true);
            return false;
        }
        
        return true;
    }
    
    public void setDataToTable(){
        try {
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from course");
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()){
                
                String courseId = rs.getString("Id");
                String courseName = rs.getString("CNAME");
                String coursePrice = rs.getString("COST");
            
                
                Object[] obj = {courseId,courseName,coursePrice};
                 
                model = (DefaultTableModel)tbl_courseData.getModel();
                model.addRow(obj);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
       
       
    public void addCourse(int id,String cname,double cost){
        
        try {
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into course values(?,?,?)");
            
            pst.setInt(1, id);
            pst.setString(2, cname);
            pst.setDouble(3, cost);
            
            int rowCount = pst.executeUpdate();
            
            if (rowCount ==1){
                JOptionPane.showMessageDialog(this, "Course added successfully!!");
                
                txt_courseId.setText("");
                txt_courseName.setText("");
                txt_coursePrice.setText("");
                
                clearTable();
                setDataToTable();
            }
            
            else{
                JOptionPane.showMessageDialog(this, "Course adding failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Course adding failed");
            
        }
        
    }
    

    
    public void clearTable(){
        
        DefaultTableModel model = (DefaultTableModel)tbl_courseData.getModel();
        model.setRowCount(0);
    }
    
    
    public void update(int id,String cname,double cost){
        
        try {
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("update course set CNAME=?,COST=? where Id=?");
          
            pst.setString(1, cname);
            pst.setDouble(2, cost);
            pst.setInt(3, id);
            
            int rowCount = pst.executeUpdate();
            
            if (rowCount ==1){
                JOptionPane.showMessageDialog(this, "Course updated successfully!!");
                
                txt_courseId.setText("");
                txt_courseName.setText("");
                txt_coursePrice.setText("");
                
                clearTable();
                setDataToTable();
            }
            
            else{
                JOptionPane.showMessageDialog(this, "Course updation failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Course updation failed");
            
        }
    }
    
    public void delete(int id){
        
        try {
            Connection con = DB_Conn.getConnection();
            PreparedStatement pst = con.prepareStatement("delete from course where Id=?");
          
            pst.setInt(1, id);
            
            int rowCount = pst.executeUpdate();
            
            if (rowCount ==1){
                JOptionPane.showMessageDialog(this, "Course deleted successfully!!");
                
                
                txt_courseId.setText("");
                txt_courseName.setText("");
                txt_coursePrice.setText("");
                
                clearTable();
                setDataToTable();
            }
            
            else{
                JOptionPane.showMessageDialog(this, "Course deletion failed");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Course deletion failed");
            
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_courseData = new javax.swing.JTable();
        txt_coursePrice = new javax.swing.JTextField();
        txt_courseId = new javax.swing.JTextField();
        txt_courseName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_delete = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        err_courseprice = new javax.swing.JLabel();
        err_courseid = new javax.swing.JLabel();
        err_coursename = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();

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

        panelsideBar.add(panelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 330, 50));

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

        panelsideBar.add(panelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 520, 330, 50));

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

        panelsideBar.add(panelSearchrecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 330, 50));

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

        panelsideBar.add(panelEditcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 330, 50));

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

        panelsideBar.add(panelViewallRec, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 330, 50));

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

        panelsideBar.add(panelBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 330, 50));

        getContentPane().add(panelsideBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 450, 810));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_courseData.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tbl_courseData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Id", "Course Name", "Course Price"
            }
        ));
        tbl_courseData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_courseDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_courseData);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, 620, 580));

        txt_coursePrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txt_coursePrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 250, 40));

        txt_courseId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txt_courseId, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 250, 40));

        txt_courseName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(txt_courseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 250, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Course Price :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 120, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Edit Course Details");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 260, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Course Name :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 130, -1));

        btn_delete.setBackground(new java.awt.Color(0, 51, 51));
        btn_delete.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 255, 255));
        btn_delete.setText("DELETE");
        btn_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_deleteMouseClicked(evt);
            }
        });
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 520, 110, 40));

        btn_add.setBackground(new java.awt.Color(0, 51, 51));
        btn_add.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btn_add.setForeground(new java.awt.Color(255, 255, 255));
        btn_add.setText("ADD");
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_addMouseClicked(evt);
            }
        });
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });
        jPanel1.add(btn_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 100, 40));

        btn_update.setBackground(new java.awt.Color(0, 51, 51));
        btn_update.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 255, 255));
        btn_update.setText("UPDATE");
        btn_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_updateMouseClicked(evt);
            }
        });
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        jPanel1.add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 110, 40));

        err_courseprice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        err_courseprice.setForeground(new java.awt.Color(102, 0, 0));
        err_courseprice.setText("Price cannot be empty");
        jPanel1.add(err_courseprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 230, -1));

        err_courseid.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        err_courseid.setForeground(new java.awt.Color(102, 0, 0));
        err_courseid.setText("Id cannot be empty & must be integer");
        jPanel1.add(err_courseid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        err_coursename.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        err_coursename.setForeground(new java.awt.Color(102, 0, 0));
        err_coursename.setText("Name cannot be empty");
        jPanel1.add(err_coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fees_management_system/images/back-button.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 100, 80));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 420, 10));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Course Id :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 810));

        setSize(new java.awt.Dimension(1544, 815));
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

    private void panelSearchrecordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchrecordMouseEntered
        Color clr = new Color(0, 153, 153);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchrecordMouseEntered

    private void panelSearchrecordMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSearchrecordMouseExited
        Color clr = new Color(0, 102, 102);
        panelSearchrecord.setBackground(clr);
    }//GEN-LAST:event_panelSearchrecordMouseExited

    private void panelEditcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditcourseMouseEntered
        Color clr = new Color(0, 153, 153);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_panelEditcourseMouseEntered

    private void panelEditcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelEditcourseMouseExited
        Color clr = new Color(0, 102, 102);
        panelEditcourse.setBackground(clr);
    }//GEN-LAST:event_panelEditcourseMouseExited

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

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_updateActionPerformed

    private void tbl_courseDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_courseDataMouseClicked
        
        int rowNo = tbl_courseData.getSelectedRow();
        TableModel model = tbl_courseData.getModel();
        
        txt_courseId.setText(model.getValueAt(rowNo, 0).toString());
        txt_courseName.setText((String)model.getValueAt(rowNo, 1));
        txt_coursePrice.setText((String)model.getValueAt(rowNo, 2));
        
    }//GEN-LAST:event_tbl_courseDataMouseClicked

    private void btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseClicked
        if (validation()){
            
            err_courseid.setVisible(false);
            err_coursename.setVisible(false);
            err_courseprice.setVisible(false);
            
            int id = Integer.parseInt(txt_courseId.getText());
            String cname = txt_courseName.getText();
            double cost = Math.round(Double.parseDouble(txt_coursePrice.getText()));

            addCourse(id, cname, cost);
        }
    }//GEN-LAST:event_btn_addMouseClicked

    private void btn_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_updateMouseClicked
        if (validation()){
            
            err_courseid.setVisible(false);
            err_coursename.setVisible(false);
            err_courseprice.setVisible(false);
            
            int id = Integer.parseInt(txt_courseId.getText());
            String cname = txt_courseName.getText();
            double cost = Math.round(Double.parseDouble(txt_coursePrice.getText()));
            
            update(id, cname, cost);
        }
    }//GEN-LAST:event_btn_updateMouseClicked

    private void btn_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_deleteMouseClicked
        int id = Integer.parseInt(txt_courseId.getText());
        
        delete(id);
    }//GEN-LAST:event_btn_deleteMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void btnHome14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome14MouseClicked
        SearchRecord sr = new SearchRecord();
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome14MouseClicked

    private void btnHome170MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome170MouseClicked
        viewAllRecords vAllRecords = new viewAllRecords();
        vAllRecords.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome170MouseClicked

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

    private void btnHome66MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHome66MouseClicked
        // TODO add your handling code here:
         EditCourse ec = new EditCourse();
        ec.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHome66MouseClicked

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
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditCourse().setVisible(true);
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
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel err_courseid;
    private javax.swing.JLabel err_coursename;
    private javax.swing.JLabel err_courseprice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelBack;
    private javax.swing.JPanel panelEditcourse;
    private javax.swing.JPanel panelHome;
    private javax.swing.JPanel panelLogout;
    private javax.swing.JPanel panelSearchrecord;
    private javax.swing.JPanel panelViewallRec;
    private javax.swing.JPanel panelsideBar;
    private javax.swing.JTable tbl_courseData;
    private javax.swing.JTextField txt_courseId;
    private javax.swing.JTextField txt_courseName;
    private javax.swing.JTextField txt_coursePrice;
    // End of variables declaration//GEN-END:variables
}
