/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.PajakModel;

/**
 *
 * @author lincbp
 */
public interface ImplementPajak {
    public void input(PajakModel model);
    
    public void update(PajakModel model);
    
    public void delete(int id);
    
    public List<PajakModel> getData(String search);
    
    public List<PajakModel> getAllData();
    
    public PajakModel getDetail(int id);
    
}
