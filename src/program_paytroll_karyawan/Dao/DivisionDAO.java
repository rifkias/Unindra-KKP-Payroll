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
import program_paytroll_karyawan.Model.DivisionModel;

/**
 *
 * @author lincbp
 */
public class DivisionDAO implements ImplementDivision {
    private List<DivisionModel> list;
    private DepartementDAO departementDao = new DepartementDAO();
    
    @Override
    public void input(DivisionModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO division (division_id,departement_id, name, notes) VALUES (null, ?, ?, ?)");
            
            statement.setInt(1, model.getDepartement_id());
            statement.setString(2, model.getName());
            statement.setString(3, model.getNotes());
            
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(DivisionModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE division SET name=?,notes=?,departement_id=? WHERE division_id=?");
            
            statement.setString(1, model.getName());
            statement.setString(2, model.getNotes());
            statement.setInt(3, model.getDepartement_id());
            statement.setInt(4, model.getDivision_id());
            
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
                    + "DELETE FROM division WHERE division_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<DivisionModel> getData(String search) {
         list = new ArrayList<DivisionModel>();
        
        try {
            String like = "'%"+search+"%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT dv.* FROM division dv LEFT JOIN departement dp ON dv.departement_id = dp.departement_id LEFT JOIN location l ON dp.location_id = l.location_id WHERE dv.name LIKE "+like+" OR dv.notes LIKE "+like+" OR dp.name LIKE "+like+" OR l.name LIKE"+like);
            
            while (result.next()) { 
                DivisionModel model = new DivisionModel();
                DepartementModel modelDepartement = departementDao.getDetail(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDepartement(modelDepartement);
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
    public List<DivisionModel> getAllData() {
        list = new ArrayList<DivisionModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM division");
            
            while (result.next()) { 
                DivisionModel model = new DivisionModel();
                DepartementModel modelDepartement = departementDao.getDetail(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDepartement(modelDepartement);
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
    public DivisionModel getDetail(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM division where division_id = '"+id+"' limit 1");
            
            DivisionModel model = new DivisionModel();

            if(result.next()) { 
                DepartementModel modelDepartement = departementDao.getDetail(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDepartement(modelDepartement);
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
    public List<DivisionModel> getDataByDepartementId(int id) {
        list = new ArrayList<DivisionModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM division WHERE departement_id='"+id+"'");
            
            while (result.next()) { 
                DivisionModel model = new DivisionModel();
                DepartementModel modelDepartement = departementDao.getDetail(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDepartement(modelDepartement);
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
