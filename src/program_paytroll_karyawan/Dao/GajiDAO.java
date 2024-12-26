/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import program_paytroll_karyawan.Model.GajiDetailModel;
import program_paytroll_karyawan.Model.GajiModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.PeriodeModel;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class GajiDAO implements ImplementGaji {

    private List<GajiModel> list;
    private List<GajiDetailModel> listDetail;
    private final KaryawanDAO karyawanController = new KaryawanDAO();
    private final PeriodeDAO periodeController = new PeriodeDAO();
    private final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

    @Override
    public GajiModel input(GajiModel model) {
        try {   
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO gaji (gaji_id, employe_id, total_absen, total_lembur, periode_id, total_pendapatan,total_pengurangan,created_at,updated_at) VALUES (null, ?, ?, ?, ?, ?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, model.getEmploye_id());
            statement.setInt(2, model.getTotal_absen());
            statement.setInt(3, model.getTotal_lembur());
            statement.setInt(4, model.getPeriode_id());
            statement.setDouble(5, model.getTotal_pendapatan());
            statement.setDouble(6, model.getTotal_pengurangan());
            statement.setString(7, timeStamp);
            statement.setString(8, timeStamp);
            
            int affectedRow = statement.executeUpdate();
            
            if(affectedRow == 0){
                throw new SQLException("Insert data failed, no rows affected.");
            }
            
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    model.setGaji_id(rs.getInt(1));
                }
                rs.close();
                
                for(GajiDetailModel detail : model.getDetail()){
                    statement = DbConnection.getConnection().prepareStatement(""
                        + "INSERT INTO gaji_detail (gaji_detail_id, gaji_id, type, remarks, total) VALUES (null, ?, ?, ?, ?)");
                    statement.setInt(1, model.getGaji_id());
                    statement.setString(2, detail.getType());
                    statement.setString(3, detail.getRemarks());
                    statement.setDouble(4, detail.getTotal());

                    statement.executeUpdate();
                }
            }
            
            statement.close();
            
            return model;    
        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
    }

    @Override
    public List<GajiModel> getAllData() {
        list = new ArrayList<GajiModel>();

        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM gaji");

            while (result.next()) {
                GajiModel model = new GajiModel();
                model.setGaji_id(result.getInt("gaji_id"));
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                PeriodeModel modelPeriode = periodeController.getDetail(result.getInt("periode_id"));
                model.setKaryawan(modelKaryawan);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setPeriode(modelPeriode);

                model.setPeriode_id(result.getInt("periode_id"));
                model.setTotal_absen(result.getInt("total_absen"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setTotal_pendapatan(result.getDouble("total_pendapatan"));
                model.setTotal_pengurangan(result.getDouble("total_pengurangan"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setUpdated_at(result.getTimestamp("updated_at"));
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
    public GajiModel getDetail(int id) {
        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM gaji WHERE gaji_id="+id+" LIMIT 1");

            GajiModel model = new GajiModel();
            while (result.next()) {
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                PeriodeModel modelPeriode = periodeController.getDetail(result.getInt("periode_id"));
                model.setKaryawan(modelKaryawan);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setPeriode(modelPeriode);

                model.setPeriode_id(result.getInt("periode_id"));
                model.setTotal_absen(result.getInt("total_absen"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setTotal_pendapatan(result.getDouble("total_pendapatan"));
                model.setTotal_pengurangan(result.getDouble("total_pengurangan"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setUpdated_at(result.getTimestamp("updated_at"));
                
                model.setDetail(getGajiDetail(result.getInt("gaji_id")));
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
    public List<GajiModel> getDataByEmployeId(int id) {
        list = new ArrayList<GajiModel>();

        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM gaji where employe_id="+id);

            while (result.next()) {
                GajiModel model = new GajiModel();
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                PeriodeModel modelPeriode = periodeController.getDetail(result.getInt("periode_id"));
                model.setKaryawan(modelKaryawan);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setPeriode(modelPeriode);

                model.setPeriode_id(result.getInt("periode_id"));
                model.setTotal_absen(result.getInt("total_absen"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setTotal_pendapatan(result.getDouble("total_pendapatan"));
                model.setTotal_pengurangan(result.getDouble("total_pengurangan"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setUpdated_at(result.getTimestamp("updated_at"));
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
    public List<GajiModel> getDataByPeriodeId(int id) {
         list = new ArrayList<GajiModel>();

        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM gaji where periode_id="+id);

            while (result.next()) {
                GajiModel model = new GajiModel();
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                PeriodeModel modelPeriode = periodeController.getDetail(result.getInt("periode_id"));
                model.setKaryawan(modelKaryawan);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setPeriode(modelPeriode);

                model.setPeriode_id(result.getInt("periode_id"));
                model.setTotal_absen(result.getInt("total_absen"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setTotal_pendapatan(result.getDouble("total_pendapatan"));
                model.setTotal_pengurangan(result.getDouble("total_pengurangan"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setUpdated_at(result.getTimestamp("updated_at"));
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
    public List<GajiDetailModel> getGajiDetail(int id) {
        listDetail = new ArrayList<GajiDetailModel>();

        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM gaji_detail WHERE gaji_id=" + id);

            while (result.next()) {
                GajiDetailModel model = new GajiDetailModel();
                model.setGaji_detail_id(result.getInt("gaji_detail_id"));
                model.setGaji_id(result.getInt("gaji_id"));
                model.setType(result.getString("type"));
                model.setRemarks(result.getString("remarks"));
                model.setTotal(result.getDouble("total"));
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
    public void deleteGajiByPeriode(int id) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "DELETE FROM gaji WHERE periode_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<GajiModel> getGajiSearch(int employe_id, int periode_id) {
        list = new ArrayList<GajiModel>();
        String sqlWhere1 = "";
        String sqlWhere2 = "";
        if(employe_id > 0){
            sqlWhere1 = " AND employe_id = "+employe_id;
        }
        if(periode_id > 0){
            sqlWhere2 = " AND periode_id = '"+periode_id+"'";
        }
        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM gaji WHERE 1=1 "+sqlWhere1 + sqlWhere2);
            
            while (result.next()) {
                GajiModel model = new GajiModel();
                model.setGaji_id(result.getInt("gaji_id"));
                KaryawanModel modelKaryawan = karyawanController.getDetail(result.getInt("employe_id"));
                PeriodeModel modelPeriode = periodeController.getDetail(result.getInt("periode_id"));
                model.setKaryawan(modelKaryawan);
                model.setEmploye_id(result.getInt("employe_id"));
                model.setPeriode(modelPeriode);

                model.setPeriode_id(result.getInt("periode_id"));
                model.setTotal_absen(result.getInt("total_absen"));
                model.setTotal_lembur(result.getInt("total_lembur"));
                model.setTotal_pendapatan(result.getDouble("total_pendapatan"));
                model.setTotal_pengurangan(result.getDouble("total_pengurangan"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setUpdated_at(result.getTimestamp("updated_at"));
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
