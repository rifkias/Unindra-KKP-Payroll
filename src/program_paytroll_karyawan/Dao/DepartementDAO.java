/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.LocationModel;
import program_paytroll_karyawan.Dao.LocationDAO;

/**
 *
 * @author lincbp
 */
public class DepartementDAO implements ImplementDepartement{
    private List<DepartementModel> list;
    private LocationDAO locationController = new LocationDAO();
    @Override
    public void input(DepartementModel model) {
         try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO departement (departement_id,location_id, name, notes) VALUES (null, ?, ?, ?)");
            
            statement.setInt(1, model.getLocation_id());
            statement.setString(2, model.getName());
            statement.setString(3, model.getNotes());
            
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(DepartementModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE departement SET name=?,notes=?,location_id=? WHERE departement_id=?");
            
            statement.setString(1, model.getName());
            statement.setString(2, model.getNotes());
            statement.setInt(3, model.getLocation_id());
            statement.setInt(4, model.getDepartement_id());
            
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
                    + "DELETE FROM departement WHERE departement_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<DepartementModel> getData(String search) {
       list = new ArrayList<DepartementModel>();
        
        try {
            String like = "'%"+search+"%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT d.* FROM departement d LEFT JOIN location l ON d.location_id = l.location_id WHERE d.name LIKE "+like+" OR d.notes LIKE "+like+" OR l.name LIKE "+like+" ");
            
            while (result.next()) { 
                DepartementModel model = new DepartementModel();
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setLocation_id(result.getInt("location_id"));
                model.setLocation(modelLocation);
                model.setName(result.getString("name"));
                model.setNotes(result.getString("notes"));
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
    public List<DepartementModel> getAllData() {
        list = new ArrayList<DepartementModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departement");
            
            while (result.next()) { 
                DepartementModel model = new DepartementModel();
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setLocation_id(result.getInt("location_id"));
                model.setLocation(modelLocation);
                model.setName(result.getString("name"));
                model.setNotes(result.getString("notes"));
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
    public DepartementModel getDetail(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departement where departement_id = '"+id+"' limit 1");
            
            DepartementModel model = new DepartementModel();

            if(result.next()) { 
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setLocation_id(result.getInt("location_id"));
                model.setLocation(modelLocation);
                model.setName(result.getString("name"));
                model.setNotes(result.getString("notes"));
            }
            statement.close();
            result.close();
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    
    }

    @Override
    public List<DepartementModel> getAllByLocationId(int id) {
        list = new ArrayList<DepartementModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM departement WHERE location_id='"+id+"'");
            
            while (result.next()) { 
                DepartementModel model = new DepartementModel();
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setLocation_id(result.getInt("location_id"));
                model.setLocation(modelLocation);
                model.setName(result.getString("name"));
                model.setNotes(result.getString("notes"));
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
   
}
