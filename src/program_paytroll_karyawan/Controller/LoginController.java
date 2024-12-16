/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.LoginModel;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Dao.ImplementLogin;
import program_paytroll_karyawan.Dao.LoginDAO;
import program_paytroll_karyawan.View.Form_Login;
import program_paytroll_karyawan.View.MainMenu;
import program_paytroll_karyawan.View.MainMenu2;
import program_paytroll_karyawan.View.MainMenu_Utama;

/**
 *
 * @author lincbp
 */
public class LoginController {
    private final Form_Login panel;
    private final ImplementLogin implementLogin;
    
    public LoginController(Form_Login panel) {
        this.panel = panel;
        implementLogin = new LoginDAO();
    }
    
    public void authUser(){
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername(panel.getUsername().getText());
        String password = implementLogin.encryptPassword(panel.getPassword().getText());
        
        loginModel.setPassword(password);
        String Response = implementLogin.authUser(loginModel);
        if(Response.equals("Success")){
//            new MainMenu_Utama(loginModel).setVisible(true);
            new MainMenu2(loginModel).setVisible(true);
            panel.setVisible(false);
            JOptionPane.showMessageDialog(null,"Login Success");
        }else{
            JOptionPane.showMessageDialog(null,"Login Gagal");
        }
    }
}
