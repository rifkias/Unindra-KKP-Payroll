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
public class InsuranceModel {
    private int asuransi_id;
    private String name;
    private String asuransi_class;
    private Double premi;

    public int getAsuransi_id() {
        return asuransi_id;
    }

    public void setAsuransi_id(int asuransi_id) {
        this.asuransi_id = asuransi_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsuransi_class() {
        return asuransi_class;
    }

    public void setAsuransi_class(String asuransi_class) {
        this.asuransi_class = asuransi_class;
    }

    public Double getPremi() {
        return premi;
    }

    public void setPremi(Double premi) {
        this.premi = premi;
    }
    
    
}
