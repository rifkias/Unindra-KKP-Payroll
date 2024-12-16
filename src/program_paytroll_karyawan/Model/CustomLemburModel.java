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
public class CustomLemburModel {
    private String nameMonth;
    private int employe_id;
    private int total_lembur;
    private KaryawanModel employeDetail;
    
    
    public String getNameMonth() {
        return nameMonth;
    }

    public void setNameMonth(String nameMonth) {
        this.nameMonth = nameMonth;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public int getTotal_lembur() {
        return total_lembur;
    }

    public void setTotal_lembur(int total_lembur) {
        this.total_lembur = total_lembur;
    }

    public KaryawanModel getEmployeDetail() {
        return employeDetail;
    }

    public void setEmployeDetail(KaryawanModel employeDetail) {
        this.employeDetail = employeDetail;
    }
    
    public int getBiayaLembur(){
        return total_lembur * 200000;
    }
    
    
}
