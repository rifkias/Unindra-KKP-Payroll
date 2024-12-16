/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.KaryawanModel;

/**
 *
 * @author lincbp
 */
public interface ImplementKaryawan {
    public void input(KaryawanModel model);
    
    public void update(KaryawanModel model);
    
    public void updatePassword(String password,int id);
    
    public void delete(int id);
    
    public List<KaryawanModel> getData(String search);
    
    public List<KaryawanModel> getAllData();
    
    public KaryawanModel getDetail(int id);
    
    public Boolean checkUsername(String username);
    
    public Boolean checkNik(String nik);
    
    public String encryptPassword(String password);
    
    
}
