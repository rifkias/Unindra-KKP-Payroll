/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author lincbp
 */
public class LemburModel {
    private int lembur_id;
    private int absensi_id;
    private AbsensiModel absen;
    private Date startDate;
    private Date endDate;
    private int request_from;
    private KaryawanModel request_employe;
    private Timestamp created_at;
    private String created_by;

    public int getLembur_id() {
        return lembur_id;
    }

    public void setLembur_id(int lembur_id) {
        this.lembur_id = lembur_id;
    }

    public int getAbsensi_id() {
        return absensi_id;
    }

    public void setAbsensi_id(int absensi_id) {
        this.absensi_id = absensi_id;
    }

    public AbsensiModel getAbsen() {
        return absen;
    }

    public void setAbsen(AbsensiModel absen) {
        this.absen = absen;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getRequest_from() {
        return request_from;
    }

    public void setRequest_from(int request_from) {
        this.request_from = request_from;
    }

    public KaryawanModel getRequest_employe() {
        return request_employe;
    }

    public void setRequest_employe(KaryawanModel request_employe) {
        this.request_employe = request_employe;
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
    
    
}
