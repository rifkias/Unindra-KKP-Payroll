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
public class DepartementModel {
    private int departement_id;
    private int location_id;
    private LocationModel location;
    private String name;
    private String notes;
    
    public int getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
}
