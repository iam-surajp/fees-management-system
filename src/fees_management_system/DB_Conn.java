/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fees_management_system;
import java.sql.*;
/**
 *
 * @author suraj
 */
public class DB_Conn {
    
    
    public static Connection getConnection(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/fees_management","root","pass123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
}
