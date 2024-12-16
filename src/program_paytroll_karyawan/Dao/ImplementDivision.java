/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.DivisionModel;

/**
 *
 * @author lincbp
 */
public interface ImplementDivision {
    public void input(DivisionModel model);
    
    public void update(DivisionModel model);
    
    public void delete(int id);
    
    public List<DivisionModel> getData(String search);
    
    public List<DivisionModel> getAllData();
    
    public DivisionModel getDetail(int id);
    
    public List<DivisionModel> getDataByDepartementId(int id);
}
