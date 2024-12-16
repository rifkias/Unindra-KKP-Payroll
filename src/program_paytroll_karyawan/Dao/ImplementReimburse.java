/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.ReimbursmentDetailModel;
import program_paytroll_karyawan.Model.ReimbursmentModel;

/**
 *
 * @author lincbp
 */
public interface ImplementReimburse {
    public List<ReimbursmentModel> getReimbursment();
    public List<ReimbursmentModel> getReimbursmentSearch(String nama,int employeId);
    public List<ReimbursmentDetailModel> getReimbursmentDetail(int id);
    
    public int insertReimbursment(ReimbursmentModel model);
    
    public void insertReimbursmentDetail(ReimbursmentDetailModel model);

    public void deleteReimbursmentHeader(int id);
    public void deleteReimbursmentDetail(int id);
    
    public ReimbursmentModel getDetailReimbusment(int id);
}
