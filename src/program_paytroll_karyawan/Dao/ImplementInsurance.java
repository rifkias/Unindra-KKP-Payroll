/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.InsuranceModel;

/**
 *
 * @author lincbp
 */
public interface ImplementInsurance {
    public void input(InsuranceModel model);
    
    public void update(InsuranceModel model);
    
    public void delete(int id);
    
    public List<InsuranceModel> getData(String search);
    
    public List<InsuranceModel> getAllData();
    
    public InsuranceModel getDetail(int id);
    
}
