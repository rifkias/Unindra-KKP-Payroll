/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.LoginModel;

/**
 *
 * @author lincbp
 */
public class LoginDAO implements ImplementLogin{

    @Override
    public String authUser(LoginModel loginModel) {
        String username = loginModel.getUsername();
        String password = loginModel.getPassword();
        Connection conn = DbConnection.getConnection();;
        Statement st = null;
        ResultSet rs = null;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM employe WHERE username='"+username+"' AND password='"+password+"' limit 1");
            if(rs.next()){
                loginModel.setEmploye_id(rs.getInt("employe_id"));
                loginModel.setEmploye_name(rs.getString("employe_name"));
                loginModel.setDate_of_birth(rs.getDate("date_of_birth"));
                loginModel.setNik(rs.getString("nik"));
                loginModel.setRole(rs.getString("role"));
                loginModel.setSalary(rs.getDouble("salary"));
                loginModel.setIs_active(rs.getInt("is_active"));
                loginModel.setCreated_by(rs.getString("created_by"));
                loginModel.setCreated_at(rs.getDate("created_at"));
                
                return "Success";
                
            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return "Failed";
        
    }

    @Override
    public String encryptPassword(String password) {
        try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            //retrieve bytes to encrypt
            byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
            //perform encryption
            for (int i = 0; i < encodedhash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodedhash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
            //return encrypted value
            return encryptionValue.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
        
    }
    
}
