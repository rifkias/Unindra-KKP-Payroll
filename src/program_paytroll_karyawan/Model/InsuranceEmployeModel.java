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
public class InsuranceEmployeModel {
    private int employe_insurance_id;
    private int employe_id;
    private int insurance_id;
    private KaryawanModel employe;
    private InsuranceModel insurance;

    public int getEmploye_insurance_id() {
        return employe_insurance_id;
    }

    public void setEmploye_insurance_id(int employe_insurance_id) {
        this.employe_insurance_id = employe_insurance_id;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public int getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(int insurance_id) {
        this.insurance_id = insurance_id;
    }

    public KaryawanModel getEmploye() {
        return employe;
    }

    public void setEmploye(KaryawanModel employe) {
        this.employe = employe;
    }

    public InsuranceModel getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceModel insurance) {
        this.insurance = insurance;
    }
    
}
