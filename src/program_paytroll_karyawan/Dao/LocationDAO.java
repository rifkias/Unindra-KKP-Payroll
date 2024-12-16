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
import program_paytroll_karyawan.Model.LocationModel;

/**
 *
 * @author lincbp
 */
public class LocationDAO implements ImplementLocation{
    private List<LocationModel> list;

    @Override
    public void input(LocationModel location) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO location (location_id, address_1, address_2, province, city, district, sub_district, zip_code, name) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            statement.setString(1, location.getAddress_1());
            statement.setString(2, location.getAddress_2());
            statement.setString(3, location.getProvince());
            statement.setString(4, location.getCity());
            statement.setString(5, location.getDistrict());
            statement.setString(6, location.getSub_district());
            statement.setString(7, location.getZip_code());
            statement.setString(8, location.getName());
            
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(LocationModel location) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE location SET address_1=?, address_2=?, province=?, city=?, district=?, sub_district=?, zip_code=?, name=? WHERE location_id=?");
            
            statement.setString(1, location.getAddress_1());
            statement.setString(2, location.getAddress_2());
            statement.setString(3, location.getProvince());
            statement.setString(4, location.getCity());
            statement.setString(5, location.getDistrict());
            statement.setString(6, location.getSub_district());
            statement.setString(7, location.getZip_code());
            statement.setString(8, location.getName());
            statement.setInt(9, location.getLocation_id());
            
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "DELETE FROM location WHERE location_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<LocationModel> getLocation(String text) {
        list = new ArrayList<LocationModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            String like = "'%"+text+"%'";
            ResultSet result = statement.executeQuery("SELECT * FROM location WHERE name LIKE "+like+" OR province LIKE "+like+" OR city LIKE "+like+" OR district LIKE "+like+" OR sub_district LIKE "+like+" OR zip_code LIKE "+like);
            
            while (result.next()) { 
                LocationModel location = new LocationModel();
                location.setLocation_id(result.getInt("location_id"));
                location.setName(result.getString("name"));
                location.setAddress_1(result.getString("address_1"));
                location.setAddress_2(result.getString("address_2"));
                location.setCity(result.getString("city"));
                location.setDistrict(result.getString("district"));
                location.setSub_district(result.getString("sub_district"));
                location.setZip_code(result.getString("zip_code"));
                location.setProvince(result.getString("province"));
                list.add(location);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    @Override
    public List<LocationModel> getAllLocation() {
        list = new ArrayList<LocationModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM location");
            
            while (result.next()) { 
                LocationModel location = new LocationModel();
                location.setLocation_id(result.getInt("location_id"));
                location.setName(result.getString("name"));
                location.setAddress_1(result.getString("address_1"));
                location.setAddress_2(result.getString("address_2"));
                location.setCity(result.getString("city"));
                location.setDistrict(result.getString("district"));
                location.setSub_district(result.getString("sub_district"));
                location.setZip_code(result.getString("zip_code"));
                location.setProvince(result.getString("province"));
                list.add(location);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public LocationModel getLocationDetail(int id) {
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM location WHERE location_id='"+id+"' limit 1");
            LocationModel location = new LocationModel();
            while (result.next()) { 
                location.setLocation_id(result.getInt("location_id"));
                location.setName(result.getString("name"));
                location.setAddress_1(result.getString("address_1"));
                location.setAddress_2(result.getString("address_2"));
                location.setCity(result.getString("city"));
                location.setDistrict(result.getString("district"));
                location.setSub_district(result.getString("sub_district"));
                location.setZip_code(result.getString("zip_code"));
                location.setProvince(result.getString("province"));
            }
            
            statement.close();
            result.close();
            return location;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
