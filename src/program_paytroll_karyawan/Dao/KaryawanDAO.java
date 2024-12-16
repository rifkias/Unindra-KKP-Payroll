/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.LocationModel;

/**
 *
 * @author lincbp
 */
public class KaryawanDAO implements ImplementKaryawan{
    private List<KaryawanModel> list;
    private LocationDAO locationController = new LocationDAO();
    private DepartementDAO departementController = new DepartementDAO();
    private DivisionDAO divisionController = new DivisionDAO();
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    
    @Override
    public void input(KaryawanModel model) {
         try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO employe (employe_id, employe_name, date_of_birth, nik, username, password, location_id, departement_id, division_id,role,salary,is_active,created_by) VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            statement.setString(1, model.getEmploye_name());
            statement.setString(2, model.getDate_of_birth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            statement.setString(3, model.getNik());
            statement.setString(4, model.getUsername());
            statement.setString(5, model.getPassword());
            statement.setInt(6, model.getLocation_id());
            statement.setInt(7, model.getDepartement_id());
            statement.setInt(8, model.getDivision_id());
            statement.setString(9, model.getRole());
            statement.setDouble(10, model.getSalary());
            statement.setInt(11, model.getIs_active());
            statement.setString(12, model.getCreated_by());
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(KaryawanModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement("UPDATE employe SET employe_name=?, date_of_birth=?, nik=?, username=?, location_id=?, departement_id=?, division_id=?, role=?, salary=?, is_active=? WHERE employe_id=? ");
            
            statement.setString(1, model.getEmploye_name());
            statement.setString(2, model.getDate_of_birth().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            statement.setString(3, model.getNik());
            statement.setString(4, model.getUsername());
            statement.setInt(5, model.getLocation_id());
            statement.setInt(6, model.getDepartement_id());
            statement.setInt(7, model.getDivision_id());
            statement.setString(8, model.getRole());
            statement.setDouble(9, model.getSalary());
            statement.setInt(10, model.getIs_active());
            statement.setInt(11, model.getEmploye_id());
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
                    + "DELETE FROM employe WHERE employe_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<KaryawanModel> getData(String search) {
           list = new ArrayList<KaryawanModel>();
        
        try {
            String like = "'%"+search+"%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT " +
                "k.* \n" +
                "FROM employe k \n" +
                "LEFT JOIN location l ON k.location_id = l.location_id \n" +
                "LEFT JOIN departement d ON d.departement_id = k.departement_id \n" +
                "LEFT JOIN division d2 ON d2.division_id = k.division_id\n" +
                "WHERE "+
                "d.name LIKE "+like+" OR d2.name LIKE "+like+" OR l.name LIKE "+like+
                "OR k.employe_name LIKE"+like+" OR k.username LIKE "+like+" OR k.role LIKE "+like+" OR k.salary LIKE "+like);
            
            while (result.next()) { 
                KaryawanModel model = new KaryawanModel();
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                DepartementModel modelDepartement = departementController.getDetail(result.getInt("departement_id"));
                DivisionModel modelDivision = divisionController.getDetail(result.getInt("division_id"));
                model.setLocation(modelLocation);
                model.setDepartement(modelDepartement);
                model.setDivision(modelDivision);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setEmploye_name(result.getString("employe_name"));
                model.setDate_of_birth(result.getDate("date_of_birth").toLocalDate());
                model.setNik(result.getString("nik"));
                model.setUsername(result.getString("username"));
                model.setLocation_id(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setRole(result.getString("role"));
                model.setSalary(result.getDouble("salary"));
                model.setIs_active(result.getInt("is_active"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
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
    public List<KaryawanModel> getAllData() {
        list = new ArrayList<KaryawanModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employe");
            
            while (result.next()) { 
                KaryawanModel model = new KaryawanModel();
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                DepartementModel modelDepartement = departementController.getDetail(result.getInt("departement_id"));
                DivisionModel modelDivision = divisionController.getDetail(result.getInt("division_id"));
                model.setLocation(modelLocation);
                model.setDepartement(modelDepartement);
                model.setDivision(modelDivision);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setEmploye_name(result.getString("employe_name"));
                model.setDate_of_birth(result.getDate("date_of_birth").toLocalDate());
                model.setNik(result.getString("nik"));
                model.setUsername(result.getString("username"));
                model.setLocation_id(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setRole(result.getString("role"));
                model.setSalary(result.getDouble("salary"));
                model.setIs_active(result.getInt("is_active"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
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
    public KaryawanModel getDetail(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM employe where employe_id = '"+id+"' limit 1");
            
            KaryawanModel model = new KaryawanModel();

            if(result.next()) { 
                LocationModel modelLocation = locationController.getLocationDetail(result.getInt("location_id"));
                DepartementModel modelDepartement = departementController.getDetail(result.getInt("departement_id"));
                DivisionModel modelDivision = divisionController.getDetail(result.getInt("division_id"));
                model.setLocation(modelLocation);
                model.setDepartement(modelDepartement);
                model.setDivision(modelDivision);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setEmploye_name(result.getString("employe_name"));
                model.setDate_of_birth(result.getDate("date_of_birth").toLocalDate());
                model.setNik(result.getString("nik"));
                model.setUsername(result.getString("username"));
                model.setLocation_id(result.getInt("location_id"));
                model.setDepartement_id(result.getInt("departement_id"));
                model.setDivision_id(result.getInt("division_id"));
                model.setRole(result.getString("role"));
                model.setSalary(result.getDouble("salary"));
                model.setIs_active(result.getInt("is_active"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
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
    public Boolean checkUsername(String username) {
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            String like = "'%"+username+"%'";
            ResultSet result = statement.executeQuery("SELECT * FROM employe WHERE username LIKE "+like+" limit 1");
            if(result.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
             Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Boolean checkNik(String nik) {
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            String like = "'%"+nik+"%'";
            ResultSet result = statement.executeQuery("SELECT * FROM employe WHERE nik LIKE "+like+" limit 1");
            if(result.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
             Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public String encryptPassword(String password) {
        try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            //retrieve bytes to encrypt
            byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);
            //perform encryption
            for (int i = 0; i < encodedhash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodedhash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
            //return encrypted value
            return encryptionValue.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    @Override
    public void updatePassword(String password, int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement("UPDATE employe SET password=? WHERE employe_id=? ");
            
            statement.setString(1, password);
            statement.setInt(2, id);
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
