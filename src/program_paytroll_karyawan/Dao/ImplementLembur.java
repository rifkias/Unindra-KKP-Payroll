/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.CustomLemburModel;
import program_paytroll_karyawan.Model.LemburModel;

/**
 *
 * @author lincbp
 */
public interface ImplementLembur {
  public List<LemburModel> getData();
  public List<LemburModel> getDataById(int id);
  public List<LemburModel> getDataSearch(int employeId,String fromDate, String endDate);
  
  public List<LemburModel> getDataByMonth(String monthName,int employeId,String fromDate, String endDate);
  public void insert(LemburModel model);
  
  public void delete(int id);
  
  public List<CustomLemburModel> getCustomLembur();
  public List<CustomLemburModel> getCustomLemburSearch(int employeId,String fromDate, String endDate);
  
}
