/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author lincbp
 */
public class ReimbursmentModel {
    private int reimbursment_id;
    private String reimbursment_no;
    private int employe_id;
    private KaryawanModel employeDetail;
    private int request_from;
    private KaryawanModel requestDetail;
    private Timestamp created_at;
    private String created_by;
    private List<ReimbursmentDetailModel> detail;

    public int getReimbursment_id() {
        return reimbursment_id;
    }

    public void setReimbursment_id(int reimbursment_id) {
        this.reimbursment_id = reimbursment_id;
    }

    public String getReimbursment_no() {
        return reimbursment_no;
    }

    public void setReimbursment_no(String reimbursment_no) {
        this.reimbursment_no = reimbursment_no;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public KaryawanModel getEmployeDetail() {
        return employeDetail;
    }

    public void setEmployeDetail(KaryawanModel employeDetail) {
        this.employeDetail = employeDetail;
    }

    public int getRequest_from() {
        return request_from;
    }

    public void setRequest_from(int request_from) {
        this.request_from = request_from;
    }

    public KaryawanModel getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(KaryawanModel requestDetail) {
        this.requestDetail = requestDetail;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public List<ReimbursmentDetailModel> getDetail() {
        return detail;
    }

    public void setDetail(List<ReimbursmentDetailModel> detail) {
        this.detail = detail;
    }
    
    public Double getTotalCost(){
        Double total = 0.0;
        for(ReimbursmentDetailModel list:detail){
            total += list.getCost();
        }
        
        return total;
    }
    
}
