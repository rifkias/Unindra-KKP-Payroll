/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Model;

/**
 *
 * @author lincbp
 */
public class ReimbursmentDetailModel {
    private int reimbursment_detail_id;
    private int reimbursment_id;
    private ReimbursmentModel reimbursmentDetail;
    private String description;
    private Double cost;

    public int getReimbursment_detail_id() {
        return reimbursment_detail_id;
    }

    public void setReimbursment_detail_id(int reimbursment_detail_id) {
        this.reimbursment_detail_id = reimbursment_detail_id;
    }

    public int getReimbursment_id() {
        return reimbursment_id;
    }

    public void setReimbursment_id(int reimbursment_id) {
        this.reimbursment_id = reimbursment_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ReimbursmentModel getReimbursmentDetail() {
        return reimbursmentDetail;
    }

    public void setReimbursmentDetail(ReimbursmentModel reimbursmentDetail) {
        this.reimbursmentDetail = reimbursmentDetail;
    }
    
    
    
    
}
