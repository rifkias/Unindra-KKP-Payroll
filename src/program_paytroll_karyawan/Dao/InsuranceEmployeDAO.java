/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.InsuranceEmployeModel;
import program_paytroll_karyawan.Model.InsuranceModel;
import program_paytroll_karyawan.Model.KaryawanModel;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class InsuranceEmployeDAO implements ImplementInsuranceEmploye{
    private List<InsuranceEmployeModel> list;
    private final KaryawanDAO karyawanController = new KaryawanDAO();
    private final InsuranceDAO insuranceController = new InsuranceDAO();
    
    @Override
    public void input(InsuranceEmployeModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO employe_insurance (employe_insurance_id,employe_id, insurance_id) VALUES (null, ?, ?)");
            
            statement.setInt(1, model.getEmploye_id());
            statement.setInt(2, model.getInsurance_id());
            
            statement.executeUpdate();
            
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(InsuranceEmployeModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE employe_insurance SET employe_id=?,insurance_id=? WHERE employe_insurance_id=?");
            
            statement.setInt(1, model.getEmploye_id());
            statement.setInt(2, model.getInsurance_id());
            statement.setInt(3, model.getEmploye_insurance_id());
            statement.executeUpdate();
            
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement("DELETE FROM employe_insurance WHERE employe_insurance_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public List<InsuranceEmployeModel> getData(String search) {
        list = new ArrayList<InsuranceEmployeModel>();
        
        try {
            String like = "'%"+search+"%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT a.* FROM employe_insurance a LEFT JOIN insurance i on a.insurance_id = i.asuransi_id LEFT JOIN employe e on a.employe_id = e.employe_id WHERE k.employe_name LIKE"+like+" OR k.username LIKE "+like+" OR k.role LIKE "+like+" OR k.salary LIKE "+like+" OR i.name LIKE "+like+" OR i.asuransi_class LIKE "+like);
            
            while (result.next()) { 
                InsuranceModel modelInsurance = insuranceController.getDetail(result.getInt("insurance_id"));
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                InsuranceEmployeModel model = new InsuranceEmployeModel();
                model.setEmploye_id(result.getInt("employe_id"));
                model.setInsurance_id(result.getInt("insurance_id"));
                model.setEmploye(modelKaryawan);
                model.setInsurance(modelInsurance);
                list.add(model);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<InsuranceEmployeModel> getAllData() {
        list = new ArrayList<InsuranceEmployeModel>();
        
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employe_insurance");
            
            while (result.next()) { 
                InsuranceModel modelInsurance = insuranceController.getDetail(result.getInt("insurance_id"));
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                InsuranceEmployeModel model = new InsuranceEmployeModel();
                model.setEmploye_insurance_id(result.getInt("employe_insurance_id"));
                model.setEmploye_id(result.getInt("employe_id"));
                model.setInsurance_id(result.getInt("insurance_id"));
                model.setEmploye(modelKaryawan);
                model.setInsurance(modelInsurance);
                list.add(model);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public InsuranceEmployeModel getDetail(int id) {
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employe_insurance WHERE employe_insurance_id = '"+id+"' limit 1");
            InsuranceEmployeModel model = new InsuranceEmployeModel();
            while (result.next()) { 
                InsuranceModel modelInsurance = insuranceController.getDetail(result.getInt("insurance_id"));
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                
                model.setEmploye_id(result.getInt("employe_id"));
                model.setInsurance_id(result.getInt("insurance_id"));
                model.setEmploye(modelKaryawan);
                model.setInsurance(modelInsurance);
            }
            
            statement.close();
            result.close();
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean checkDuplicate(InsuranceEmployeModel model) {
        try {
            Boolean res = false; 
            Statement statement = DbConnection.getConnection().createStatement();
            String query = "SELECT * FROM employe_insurance WHERE employe_id = '"+model.getEmploye_id()+"' AND insurance_id = '"+model.getInsurance_id()+"'";
            if(model.getEmploye_insurance_id() > 0){
                System.out.println(model.getEmploye_insurance_id());
                query = query + " AND employe_insurance_id <>'"+model.getEmploye_insurance_id()+"'";
            }
            query = query + " limit 1";
            ResultSet result = statement.executeQuery(query);
            int x = 0;
            while (result.next()) { 
                x++;
            }
            
            if(x > 0){
                res = true;
            }
            return res;
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<InsuranceEmployeModel> getEmployeInsurance(int id) {
        list = new ArrayList<InsuranceEmployeModel>();
        
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employe_insurance WHERE employe_id = "+id);
            
            while (result.next()) { 
                InsuranceModel modelInsurance = insuranceController.getDetail(result.getInt("insurance_id"));
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                InsuranceEmployeModel model = new InsuranceEmployeModel();
                model.setEmploye_insurance_id(result.getInt("employe_insurance_id"));
                model.setEmploye_id(result.getInt("employe_id"));
                model.setInsurance_id(result.getInt("insurance_id"));
                model.setEmploye(modelKaryawan);
                model.setInsurance(modelInsurance);
                list.add(model);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(InsuranceEmployeDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
