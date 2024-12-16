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
public class PeriodeModel {

    private int periode_id;
    private String name;
    private Date start_date;
    private Date end_date;
    private Timestamp created_at;
    private Timestamp updated_at;

    public int getPeriode_id() {
        return periode_id;
    }

    public void setPeriode_id(int periode_id) {
        this.periode_id = periode_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
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

   
}
