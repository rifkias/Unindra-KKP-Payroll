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
import program_paytroll_karyawan.Model.ReimbursmentDetailModel;
import program_paytroll_karyawan.Model.ReimbursmentModel;

/**
 *
 * @author lincbp
 */
public class ReimbursmentDAO implements ImplementReimburse{
    private List<ReimbursmentModel> list;
    private List<ReimbursmentDetailModel> listDetail;
    private KaryawanDAO karyawanDao = new KaryawanDAO();
    @Override
    public List<ReimbursmentModel> getReimbursment() {
        list = new ArrayList<ReimbursmentModel>(); 
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM reimbursment");
            
            while (result.next()) { 
                ReimbursmentModel model = new ReimbursmentModel();
                model.setEmploye_id(result.getInt("employe_id"));
                model.setReimbursment_no(result.getString("reimbursment_no"));
                model.setReimbursment_id(result.getInt("reimbursment_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                model.setEmployeDetail(karyawanDao.getDetail(result.getInt("employe_id")));
                model.setRequestDetail(karyawanDao.getDetail(result.getInt("request_from")));
                model.setDetail(getReimbursmentDetail(result.getInt("reimbursment_id")));
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
    public List<ReimbursmentModel> getReimbursmentSearch(String search,int employeId) {
        list = new ArrayList<ReimbursmentModel>(); 
        try {
            String sql = "SELECT * FROM reimbursment WHERE 1=1";
            
            if(!search.equals("")){
                String like = "'%"+search+"%'";
                sql += " AND reimbursment_no LIKE "+like;
            }
            
            if(employeId > 0){
                sql += " AND employe_id = '"+employeId+"'";
            }
            System.out.println(sql);
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(sql);
            
            while (result.next()) { 
                ReimbursmentModel model = new ReimbursmentModel();
                model.setEmploye_id(result.getInt("employe_id"));
                model.setReimbursment_no(result.getString("reimbursment_no"));
                model.setReimbursment_id(result.getInt("reimbursment_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                model.setEmployeDetail(karyawanDao.getDetail(result.getInt("employe_id")));
                model.setRequestDetail(karyawanDao.getDetail(result.getInt("request_from")));
                model.setDetail(getReimbursmentDetail(result.getInt("reimbursment_id")));
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
    public List<ReimbursmentDetailModel> getReimbursmentDetail(int id) {
        listDetail = new ArrayList<ReimbursmentDetailModel>();
        
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM reimbursment_detail WHERE reimbursment_id='"+id+"'");
            
            while (result.next()) { 
                ReimbursmentDetailModel model = new ReimbursmentDetailModel();
                model.setDescription(result.getString("description"));
                model.setCost(result.getDouble("cost"));
                listDetail.add(model);
            }
            
            statement.close();
            result.close();
            return listDetail;
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public int insertReimbursment(ReimbursmentModel model) {
        int id = 0;
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO reimbursment (reimbursment_id,reimbursment_no, employe_id, request_from, created_by) VALUES (null, ?, ?, ?,?)",Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1, model.getReimbursment_no());
            statement.setInt(2, model.getEmploye_id());
            statement.setInt(3, model.getRequest_from());
            statement.setString(4, model.getCreated_by());
            statement.executeUpdate();
          
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }

    @Override
    public void insertReimbursmentDetail(ReimbursmentDetailModel model) {
         try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO reimbursment_detail (reimbursment_detail_id,reimbursment_id, description, cost) VALUES (null, ?, ?, ?)");
            
            statement.setInt(1, model.getReimbursment_id());
            statement.setString(2, model.getDescription());
            statement.setDouble(3, model.getCost());
            statement.executeUpdate();
            
            statement.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteReimbursmentHeader(int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "DELETE FROM reimbursment WHERE reimbursment_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteReimbursmentDetail(int id) {
         try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "DELETE FROM reimbursment_detail WHERE reimbursment_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ReimbursmentModel getDetailReimbusment(int id) {
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM reimbursment WHERE reimbursment_id='"+id+"' limit 1");
            
            ReimbursmentModel model = new ReimbursmentModel();

            if(result.next()) { 
                model.setEmploye_id(result.getInt("employe_id"));
                model.setReimbursment_no(result.getString("reimbursment_no"));
                model.setReimbursment_id(result.getInt("reimbursment_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                model.setEmployeDetail(karyawanDao.getDetail(result.getInt("employe_id")));
                model.setRequestDetail(karyawanDao.getDetail(result.getInt("request_from")));
                model.setDetail(getReimbursmentDetail(result.getInt("reimbursment_id")));
            }
            
            statement.close();
            result.close();
            
            return model;
        } catch (SQLException ex) {
            Logger.getLogger(ReimbursmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<ReimbursmentModel> getReimbursment(String fromDate, String toDate, int employe_id) {
        list = new ArrayList<ReimbursmentModel>(); 
        String sqlWhere1 = "";
        String sqlWhere2 = "";
        if(fromDate != null && toDate != null && !fromDate.equals("") && !toDate.equals("")){
            sqlWhere1 = " AND created_at BETWEEN '"+fromDate+"' AND '"+toDate+"'";
        }
        if(employe_id != 0){
            sqlWhere2 = " AND employe_id = '"+employe_id+"'";
        }
        try {
            
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM reimbursment WHERE 1=1 "+sqlWhere1+sqlWhere2);
            
            while (result.next()) { 
                ReimbursmentModel model = new ReimbursmentModel();
                model.setEmploye_id(result.getInt("employe_id"));
                model.setReimbursment_no(result.getString("reimbursment_no"));
                model.setReimbursment_id(result.getInt("reimbursment_id"));
                model.setRequest_from(result.getInt("request_from"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setCreated_by(result.getString("created_by"));
                model.setEmployeDetail(karyawanDao.getDetail(result.getInt("employe_id")));
                model.setRequestDetail(karyawanDao.getDetail(result.getInt("request_from")));
                model.setDetail(getReimbursmentDetail(result.getInt("reimbursment_id")));
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
