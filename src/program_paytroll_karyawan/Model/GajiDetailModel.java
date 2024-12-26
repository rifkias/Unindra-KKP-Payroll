/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Model;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class GajiDetailModel {
    private int gaji_detail_id;
    private int gaji_id;
    private String type;
    private String remarks;
    private Double total;

    public int getGaji_detail_id() {
        return gaji_detail_id;
    }

    public void setGaji_detail_id(int gaji_detail_id) {
        this.gaji_detail_id = gaji_detail_id;
    }

    public int getGaji_id() {
        return gaji_id;
    }

    public void setGaji_id(int gaji_id) {
        this.gaji_id = gaji_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
}
