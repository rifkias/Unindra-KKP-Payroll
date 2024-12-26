/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.sql.Date;
import java.util.List;
import program_paytroll_karyawan.Model.AbsensiModel;

/**
 *
 * @author lincbp
 */
public interface ImplementAbsen {
    public AbsensiModel getCurrentAbsen(int EmployeId);
    
    public void checkIn(int employe_id);
    
    public void checkOut(int absensi_id);
    
    public List<AbsensiModel> getAbsensi();
    
    public List<AbsensiModel> getAbsensiSearch(String fromDate,String toDate, int employe_id);
    
    public AbsensiModel getAbsensiById(int id);
    
}
