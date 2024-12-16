/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author lincbp
 */
public class KaryawanModel {
    private int employe_id;
    private String employe_name;
    private LocalDate date_of_birth;
    private String nik;
    private String username;
    private String password;
    private int location_id;
    private LocationModel location;
    private int departement_id;
    private DepartementModel departement;
    private int division_id;
    private DivisionModel division;
    private String role;
    private Double salary;
    private int is_active;
    private Timestamp created_at;
    private String created_by;

    public int getEmploye_id() {
        return employe_id;
    }

    public void setEmploye_id(int employe_id) {
        this.employe_id = employe_id;
    }

    public String getEmploye_name() {
        return employe_name;
    }

    public void setEmploye_name(String employe_name) {
        this.employe_name = employe_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getDepartement_id() {
        return departement_id;
    }

    public void setDepartement_id(int departement_id) {
        this.departement_id = departement_id;
    }

    public DepartementModel getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementModel departement) {
        this.departement = departement;
    }

    public int getDivision_id() {
        return division_id;
    }

    public void setDivision_id(int division_id) {
        this.division_id = division_id;
    }

    public DivisionModel getDivision() {
        return division;
    }

    public void setDivision(DivisionModel division) {
        this.division = division;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
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
    
    public String toStringIs_Active(){
        String text = "";
        if(is_active == 0){
            text = "Not Active";
        }else{
            text = "Active";
        }
        return text;
    }
    
    public String getSalaryString(){
        return "Rp. "+ new DecimalFormat("#,##0.00").format(salary);
    }
    
}
