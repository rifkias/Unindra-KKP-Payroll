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
import program_paytroll_karyawan.Model.InsuranceModel;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class InsuranceDAO implements ImplementInsurance {
    private List<InsuranceModel> list;
    @Override
    public void input(InsuranceModel model) {
       try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO insurance (asuransi_id,name, asuransi_class, premi) VALUES (null, ?, ?, ?)");
            
            statement.setString(1, model.getName());
            statement.setString(2, model.getAsuransi_class());
            statement.setDouble(3, model.getPremi());
            
            statement.executeUpdate();
            
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(InsuranceModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE insurance SET name=?,asuransi_class=?,premi=? WHERE asuransi_id=?");
            
            statement.setString(1, model.getName());
            statement.setString(2, model.getAsuransi_class());
            statement.setDouble(3, model.getPremi());
            statement.setInt(4, model.getAsuransi_id());
            
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "DELETE FROM insurance WHERE asuransi_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @Override
    public List<InsuranceModel> getData(String search) {
        list = new ArrayList<InsuranceModel>();
        
        try {
            String like = "'%"+search+"%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM insurance WHERE name LIKE "+like+" OR asuransi_class LIKE "+like+" OR premi LIKE "+like);
            
            while (result.next()) { 
                InsuranceModel model = new InsuranceModel();
                model.setAsuransi_id(result.getInt("asuransi_id"));
                model.setName(result.getString("name"));
                model.setAsuransi_class(result.getString("asuransi_class"));
                model.setPremi(result.getDouble("premi"));
                list.add(model);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<InsuranceModel> getAllData() {
        list = new ArrayList<InsuranceModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM insurance");
            
            while (result.next()) { 
                InsuranceModel model = new InsuranceModel();
                model.setAsuransi_id(result.getInt("asuransi_id"));
                model.setName(result.getString("name"));
                model.setAsuransi_class(result.getString("asuransi_class"));
                model.setPremi(result.getDouble("premi"));
                list.add(model);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public InsuranceModel getDetail(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM insurance where asuransi_id = '"+id+"' limit 1");
            
            InsuranceModel model = new InsuranceModel();

            if(result.next()) { 
                model.setAsuransi_id(result.getInt("asuransi_id"));
                model.setName(result.getString("name"));
                model.setAsuransi_class(result.getString("asuransi_class"));
                model.setPremi(result.getDouble("premi"));
            }
            statement.close();
            result.close();
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }


}
