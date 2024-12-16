/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Model;

import java.util.Date;

/**
 *
 * @author lincbp
 */
public class AbsensiModel {
    private int absensi_id;
    private int employe_id;
    private KaryawanModel employe;
    private Date absensi_date;
    private Date in;
    private Date out; 

    public int getAbsensi_id() {
        return absensi_id;
    }

    public void setAbsensi_id(int absensi_id) {
        this.absensi_id = absensi_id;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public KaryawanModel getEmploye() {
        return employe;
    }

    public void setEmploye(KaryawanModel employe) {
        this.employe = employe;
    }

    public Date getAbsensi_date() {
        return absensi_date;
    }

    public void setAbsensi_date(Date absensi_date) {
        this.absensi_date = absensi_date;
    }

    public Date getIn() {
        return in;
    }

    public void setIn(Date in) {
        this.in = in;
    }

    public Date getOut() {
        return out;
    }

    public void setOut(Date out) {
        this.out = out;
    }
    
    
}
