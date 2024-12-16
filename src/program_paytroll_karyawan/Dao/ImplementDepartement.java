/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.DepartementModel;

/**
 *
 * @author lincbp
 */
public interface ImplementDepartement {
    public void input(DepartementModel model);
    
    public void update(DepartementModel model);
    
    public void delete(int id);
    
    public List<DepartementModel> getData(String search);
    
    public List<DepartementModel> getAllData();
    
    public DepartementModel getDetail(int id);
    
    public List<DepartementModel> getAllByLocationId(int id);
    
}
