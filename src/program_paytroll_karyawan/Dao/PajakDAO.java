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
import program_paytroll_karyawan.Model.PajakModel;

/**
 *
 * @author lincbp
 */
public class PajakDAO implements ImplementPajak {
    private List<PajakModel> list;
    

    @Override
    public List<PajakModel> getData(String search) {
         list = new ArrayList<PajakModel>();
        
        try {
            String like = "'%"+search+"%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM pajak WHERE name LIKE "+like+" OR description LIKE "+like+" OR percentage LIKE "+like);
            
            while (result.next()) { 
                PajakModel model = new PajakModel();
                model.setPajak_id(result.getInt("pajak_id"));
                model.setName(result.getString("name"));
                model.setDescription(result.getString("description"));
                model.setPercentage(result.getDouble("percentage"));
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
    public List<PajakModel> getAllData() {
        list = new ArrayList<PajakModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM pajak");
            
            while (result.next()) { 
                PajakModel model = new PajakModel();
                model.setPajak_id(result.getInt("pajak_id"));
                model.setName(result.getString("name"));
                model.setDescription(result.getString("description"));
                model.setPercentage(result.getDouble("percentage"));
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
    public PajakModel getDetail(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM pajak where pajak_id= '"+id+"' limit 1");
            
            PajakModel model = new PajakModel();

            if(result.next()) { 
                model.setPajak_id(result.getInt("pajak_id"));
                model.setName(result.getString("name"));
                model.setDescription(result.getString("description"));
                model.setPercentage(result.getDouble("percentage"));
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
    public void input(PajakModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO pajak (pajak_id,name, description, percentage) VALUES (null, ?, ?, ?)");
            
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getPercentage());
            
            statement.executeUpdate();
            
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void update(PajakModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE pajak SET name=?,description=?,percentage=? WHERE pajak_id=?");
            
            statement.setString(1, model.getName());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getPercentage());
            statement.setInt(4, model.getPajak_id());
            
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
                    + "DELETE FROM pajak WHERE pajak_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

}
