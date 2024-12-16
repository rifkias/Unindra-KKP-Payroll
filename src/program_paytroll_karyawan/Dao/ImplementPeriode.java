/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.PeriodeModel;

/**
 *
 * @author lincbp
 */
public interface ImplementPeriode {
    public void input(PeriodeModel model);
    
    public void update(PeriodeModel model);
    
    public void delete(int id);
    
    public List<PeriodeModel> getData(String search);
    
    public List<PeriodeModel> getAllData();
    
    public PeriodeModel getDetail(int id);
    
}
