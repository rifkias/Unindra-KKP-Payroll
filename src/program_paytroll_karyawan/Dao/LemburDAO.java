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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.CustomLemburModel;
import program_paytroll_karyawan.Model.LemburModel;
import program_paytroll_karyawan.Model.LocationModel;

/**
 *
 * @author lincbp
 */
public class LemburDAO implements ImplementLembur{
    private List<LemburModel> list;
    private List<CustomLemburModel> list2;
    private final KaryawanDAO daoKaryawan = new KaryawanDAO();
    private final AbsensiDAO daoAbsensi = new AbsensiDAO();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
    @Override
    public List<LemburModel> getData() {
        list = new ArrayList<LemburModel>();
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM lembur");
            
            while (result.next()) { 
                LemburModel model = new LemburModel();
                model.setLembur_id(result.getInt("lembur_id"));
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                
                model.setAbsen(daoAbsensi.getAbsensiById(result.getInt("absensi_id")));
                model.setRequest_employe(daoKaryawan.getDetail(result.getInt("request_from")));
                java.util.Date startDate = null;
                
                String inTxt = result.getString("start_date");
                if(inTxt != null){
                    try{
                        startDate = sdf.parse(inTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                java.util.Date dateOut = null;
                String outTxt = result.getString("end_date");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setStartDate(startDate);
                model.setEndDate(dateOut);
                list.add(model);
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
    public List<LemburModel> getDataById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(LemburModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO lembur (lembur_id, absensi_id, start_date, end_date, request_from, created_by) VALUES (null, ?, ?, ?, ?, ?)");
            
            statement.setInt(1, model.getAbsensi_id());
            statement.setString(2, sdf.format(model.getStartDate()));
            statement.setString(3, sdf.format(model.getEndDate()));
            statement.setInt(4, model.getRequest_from());
            statement.setString(5, model.getCreated_by());
            
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
                    + "DELETE FROM lembur WHERE lembur_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<LemburModel> getDataSearch(int employeId, String fromDate, String endDate) {
        list = new ArrayList<LemburModel>();
        String sqlWhere1 = "";
        String sqlWhere2 = "";
        if(fromDate != null && endDate != null && !fromDate.equals("") && !endDate.equals("")){
            sqlWhere1 = " AND a.absensi_date BETWEEN '"+fromDate+"' AND '"+endDate+"'";
        }
        if(employeId != 0){
            sqlWhere2 = " AND a.employe_id = '"+employeId+"'";
        }
        try {
//            System.out.println("SELECT l.* ,a.employe_id,a.absensi_date FROM lembur l LEFT JOIN absensi a on l.absensi_id = a.absensi_id WHERE 1=1 "+sqlWhere1+sqlWhere2);
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT l.* ,a.employe_id,a.absensi_date FROM lembur l LEFT JOIN absensi a on l.absensi_id = a.absensi_id WHERE 1=1 "+sqlWhere1+sqlWhere2);
            
            while (result.next()) { 
                 LemburModel model = new LemburModel();
                model.setLembur_id(result.getInt("lembur_id"));
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                
                model.setAbsen(daoAbsensi.getAbsensiById(result.getInt("absensi_id")));
                model.setRequest_employe(daoKaryawan.getDetail(result.getInt("request_from")));
                java.util.Date startDate = null;
                
                String inTxt = result.getString("start_date");
                if(inTxt != null){
                    try{
                        startDate = sdf.parse(inTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                java.util.Date dateOut = null;
                String outTxt = result.getString("end_date");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setStartDate(startDate);
                model.setEndDate(dateOut);
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
    public List<CustomLemburModel> getCustomLembur() {
        list2 = new ArrayList<CustomLemburModel>();
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT MONTHNAME(a.absensi_date) AS nameMonth, a.employe_id AS employe_id, COUNT(l.lembur_id) AS total_lembur FROM lembur l LEFT JOIN absensi a ON l.absensi_id = a.absensi_id GROUP BY nameMonth,employe_id");
            
            while (result.next()) { 
                CustomLemburModel model = new CustomLemburModel();
                model.setNameMonth(result.getString("nameMonth"));
                model.setEmploye_id(result.getInt("employe_id"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setEmployeDetail(daoKaryawan.getDetail(result.getInt("employe_id")));
                list2.add(model);
            }
            
            statement.close();
            result.close();
            return list2;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<CustomLemburModel> getCustomLemburSearch(int employeId, String fromDate, String endDate) {
        list2 = new ArrayList<CustomLemburModel>();
        String sqlWhere1 = "";
        String sqlWhere2 = "";
        if(fromDate != null && endDate != null && !fromDate.equals("") && !endDate.equals("")){
            sqlWhere1 = " AND a.absensi_date BETWEEN '"+fromDate+"' AND '"+endDate+"'";
        }
        if(employeId != 0){
            sqlWhere2 = " AND a.employe_id = '"+employeId+"'";
        }
        try {
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT MONTHNAME(a.absensi_date) AS nameMonth, a.employe_id AS employe_id, COUNT(l.lembur_id) AS total_lembur FROM lembur l LEFT JOIN absensi a ON l.absensi_id = a.absensi_id WHERE 1=1 "+sqlWhere1+sqlWhere2+" GROUP BY nameMonth,employe_id");
            
            while (result.next()) { 
                CustomLemburModel model = new CustomLemburModel();
                model.setNameMonth(result.getString("nameMonth"));
                model.setEmploye_id(result.getInt("employe_id"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setEmployeDetail(daoKaryawan.getDetail(result.getInt("employe_id")));
                list2.add(model);
            }
            
            statement.close();
            result.close();
            return list2;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<LemburModel> getDataByMonth(String monthName,int employeId, String fromDate, String endDate) {
        list = new ArrayList<LemburModel>();
        String sqlWhere1 = "";
        if(fromDate != null && endDate != null && !fromDate.equals("") && !endDate.equals("")){
            sqlWhere1 = " AND a.absensi_date BETWEEN '"+fromDate+"' AND '"+endDate+"'";
        }
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM lembur l LEFT JOIN absensi a ON l.absensi_id = a.absensi_id WHERE MONTHNAME(a.absensi_date) = '"+monthName+"' AND a.employe_id = '"+employeId+"'"+sqlWhere1);
            
            while (result.next()) { 
                LemburModel model = new LemburModel();
                model.setLembur_id(result.getInt("lembur_id"));
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                
                model.setAbsen(daoAbsensi.getAbsensiById(result.getInt("absensi_id")));
                model.setRequest_employe(daoKaryawan.getDetail(result.getInt("request_from")));
                java.util.Date startDate = null;
                
                String inTxt = result.getString("start_date");
                if(inTxt != null){
                    try{
                        startDate = sdf.parse(inTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                java.util.Date dateOut = null;
                String outTxt = result.getString("end_date");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setStartDate(startDate);
                model.setEndDate(dateOut);
                list.add(model);
            }
            
            statement.close();
            result.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
