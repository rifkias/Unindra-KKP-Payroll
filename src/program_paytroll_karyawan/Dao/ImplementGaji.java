/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.GajiDetailModel;
import program_paytroll_karyawan.Model.GajiModel;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public interface ImplementGaji {
    public GajiModel input(GajiModel model);
    public List<GajiModel> getAllData();
    public GajiModel getDetail(int id);
    public List<GajiDetailModel> getGajiDetail(int id);
    public List<GajiModel> getDataByEmployeId(int id);
    public List<GajiModel> getDataByPeriodeId(int id);
    public void deleteGajiByPeriode(int id);
    public List<GajiModel> getGajiSearch(int employe_id, int periode_id);
    
}
