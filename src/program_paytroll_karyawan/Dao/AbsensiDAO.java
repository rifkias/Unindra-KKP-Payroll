/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.AbsensiModel;

/**
 *
 * @author lincbp
 */
public class AbsensiDAO implements ImplementAbsen {
    private List<AbsensiModel> list;
    private final KaryawanDAO daoKaryawan = new KaryawanDAO();
    
    private final String DATE_FORMAT = "yyyy-MM-dd H:m:s";
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public AbsensiModel getCurrentAbsen(int EmployeId) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM absensi WHERE employe_id='"+EmployeId+"' AND (check_out IS NULL OR DATE(absensi_date) = DATE(NOW()))  LIMIT 1");
            
            if(result.next()){
                AbsensiModel model = new AbsensiModel();
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setEmploye_id(result.getInt("employe_id"));
                model.setAbsensi_date(result.getDate("absensi_date"));
                
                java.util.Date dateIn = null;
                String inTxt = result.getString("check_in");
                
                if(inTxt != null){
                    try{
                        dateIn = sdf.parse(inTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                java.util.Date dateOut = null;
                String outTxt = result.getString("check_out");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setIn(dateIn);
                model.setOut(dateOut);
                
                
                model.setEmploye(daoKaryawan.getDetail(result.getInt("employe_id")));
                return model;
            }
            
            statement.close();
            result.close();
            
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void checkIn(int employe_id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement("INSERT INTO absensi (absensi_id, employe_id, absensi_date, check_in) VALUES (null, ?, ?, ?)");
            java.util.Date currentDate = new java.util.Date();
            statement.setInt(1, employe_id);
            statement.setString(2, sdf2.format(currentDate));
            statement.setString(3, sdf.format(currentDate));
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void checkOut(int absensi_id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement("UPDATE absensi SET check_out=? WHERE absensi_id=?");
            java.util.Date currentDate = new java.util.Date();
            statement.setString(1, sdf.format(currentDate));
            statement.setInt(2, absensi_id);
            statement.executeUpdate();
          
          
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<AbsensiModel> getAbsensi() {
        list = new ArrayList<AbsensiModel>();
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM absensi");
            
            while (result.next()) { 
                AbsensiModel model = new AbsensiModel();
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setEmploye(daoKaryawan.getDetail(result.getInt("employe_id")));
                model.setAbsensi_date(result.getDate("absensi_date"));                
                java.util.Date dateIn = null;
                String inTxt = result.getString("check_in");
                
                if(inTxt != null){
                    try{
                        dateIn = sdf.parse(inTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                java.util.Date dateOut = null;
                String outTxt = result.getString("check_out");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setIn(dateIn);
                model.setOut(dateOut);
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
    public List<AbsensiModel> getAbsensiSearch(String fromDate, String toDate, int employe_id) {
        list = new ArrayList<AbsensiModel>();
        String sqlWhere1 = "";
        String sqlWhere2 = "";
        if(fromDate != null && toDate != null && !fromDate.equals("") && !toDate.equals("")){
            sqlWhere1 = " AND absensi_date BETWEEN '"+fromDate+"' AND '"+toDate+"'";
        }
        if(employe_id != 0){
            sqlWhere2 = " AND employe_id = '"+employe_id+"'";
        }
        try {
            System.out.println("SELECT * FROM absensi WHERE 1=1 "+sqlWhere1+sqlWhere2);
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM absensi WHERE 1=1 "+sqlWhere1+sqlWhere2);
            
            while (result.next()) { 
                AbsensiModel model = new AbsensiModel();
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setEmploye(daoKaryawan.getDetail(result.getInt("employe_id")));
                model.setAbsensi_date(result.getDate("absensi_date"));                
                java.util.Date dateIn = null;
                String inTxt = result.getString("check_in");
                
                if(inTxt != null){
                    try{
                        dateIn = sdf.parse(inTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                java.util.Date dateOut = null;
                String outTxt = result.getString("check_out");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(Exception e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setIn(dateIn);
                model.setOut(dateOut);
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

    public AbsensiModel getAbsensiById(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM absensi WHERE absensi_id='"+id+"' and check_out IS NOT NULL limit 1");
            AbsensiModel model = new AbsensiModel();
            while (result.next()) { 
                model.setAbsensi_id(result.getInt("absensi_id"));
                model.setEmploye(daoKaryawan.getDetail(result.getInt("employe_id")));
                model.setAbsensi_date(result.getDate("absensi_date"));
                
                java.util.Date dateIn = null;
                String inTxt = result.getString("check_in");
                
                if(inTxt != null){
                    try{
                        dateIn = sdf.parse(inTxt);
                    }catch(ParseException e){
                        System.err.println(e.getMessage());
                    }
                }
                
                java.util.Date dateOut = null;
                String outTxt = result.getString("check_out");
                if(outTxt != null){
                    try{
                        dateOut = sdf.parse(outTxt);
                    }catch(ParseException e){
                        System.err.println(e.getMessage());
                    }
                }
                
                model.setIn(dateIn);
                model.setOut(dateOut);
            }
            
            statement.close();
            result.close();
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int getTotalAbsenByMonth(int id, int employe_id) {
         try {
            int total = 0;
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT COUNT(*) FROM absensi WHERE employe_id="+employe_id+" AND MONTH(absensi_date)="+id);
            if(result.next()){
                total = result.getInt(1);
            }
          
            statement.close();
            result.close();
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    
}
