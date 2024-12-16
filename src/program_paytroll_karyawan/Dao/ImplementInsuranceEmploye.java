/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.InsuranceEmployeModel;

/**
 *
 * @author lincbp
 */
public interface ImplementInsuranceEmploye {
    public void input(InsuranceEmployeModel model);
    
    public void update(InsuranceEmployeModel model);
    
    public void delete(int id);
    
    public List<InsuranceEmployeModel> getData(String search);
    
    public List<InsuranceEmployeModel> getAllData();
    
    public InsuranceEmployeModel getDetail(int id);
    
    public boolean checkDuplicate(InsuranceEmployeModel model);
}
