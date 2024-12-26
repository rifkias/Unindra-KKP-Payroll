/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class GajiModel {
    private int gaji_id;
    private int employe_id;
    private int total_absen;
    private int total_lembur;
    private int periode_id;
    private double total_pendapatan;
    private double total_pengurangan;
    private Timestamp created_at;
    private Timestamp updated_at;
    
    private KaryawanModel karyawan;
    private PeriodeModel periode;
    
    private List<GajiDetailModel> detail;

    public int getGaji_id() {
        return gaji_id;
    }

    public void setGaji_id(int gaji_id) {
        this.gaji_id = gaji_id;
    }

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public int getTotal_absen() {
        return total_absen;
    }

    public void setTotal_absen(int total_absen) {
        this.total_absen = total_absen;
    }

    public int getTotal_lembur() {
        return total_lembur;
    }

    public void setTotal_lembur(int total_lembur) {
        this.total_lembur = total_lembur;
    }

    public int getPeriode_id() {
        return periode_id;
    }

    public void setPeriode_id(int periode_id) {
        this.periode_id = periode_id;
    }

    public double getTotal_pendapatan() {
        return total_pendapatan;
    }

    public void setTotal_pendapatan(double total_pendapatan) {
        this.total_pendapatan = total_pendapatan;
    }

    public double getTotal_pengurangan() {
        return total_pengurangan;
    }

    public void setTotal_pengurangan(double total_pengurangan) {
        this.total_pengurangan = total_pengurangan;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public KaryawanModel getKaryawan() {
        return karyawan;
    }

    public void setKaryawan(KaryawanModel karyawan) {
        this.karyawan = karyawan;
    }

    public PeriodeModel getPeriode() {
        return periode;
    }

    public void setPeriode(PeriodeModel periode) {
        this.periode = periode;
    }

    public List<GajiDetailModel> getDetail() {
        return detail;
    }

    public void setDetail(List<GajiDetailModel> detail) {
        this.detail = detail;
    }
    
    public Double getGajiBersih(){
        Double total;
        total = getTotal_pendapatan() - getTotal_pengurangan();
        return total;
    }
    
    
    
}
