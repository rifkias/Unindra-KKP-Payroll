/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import program_paytroll_karyawan.Model.LoginModel;

/**
 *
 * @author lincbp
 */
public interface ImplementLogin {
    public String authUser(LoginModel loginModel);
    
    public String encryptPassword(String password);
}
