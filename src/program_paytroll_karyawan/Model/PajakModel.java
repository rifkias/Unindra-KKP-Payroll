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
public class PajakModel {

    private int pajak_id;
    private String name;
    private String description;
    private double percentage;

    public int getPajak_id() {
        return pajak_id;
    }

    public void setPajak_id(int pajak_id) {
        this.pajak_id = pajak_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
   
}
