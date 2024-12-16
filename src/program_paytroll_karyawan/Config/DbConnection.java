/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package program_paytroll_karyawan.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lincbp
 */
public class DbConnection {
    private static Connection mysqlconfig;
    
    public static Connection getConnection(){
//        if(mysqlconfig == null){
            try{
    //            String URI = "jdbc:mysql://66.94.122.11/rifkialf_payroll_java";
    //            String user = "rifkialf_payroll_java";
    //            String password = "JMqx28EX";
                String URI = "jdbc:mysql://localhost/payroll_java";
                String user = "root";
                String password = "[password]";

                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                mysqlconfig = DriverManager.getConnection(URI,user,password);
            }catch(Exception e){
                System.err.println("Error Koneksi"+e.getMessage());
            }
//        }

        
        return mysqlconfig;
    }
    
    public static void main(String[] args) {
        
      DbConnection.getConnection();
    }
}
