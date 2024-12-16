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
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.PeriodeModel;

/**
 *
 * @author lincbp
 */
public class PeriodeDAO implements ImplementPeriode {

    private List<PeriodeModel> list;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
    @Override
    public void input(PeriodeModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "INSERT INTO periode (periode_id, name, start_date, end_date, created_at, updated_at) VALUES (null, ?, ?, ?, ?, ?)");

            statement.setString(1, model.getName());
            statement.setString(2, sdf.format(model.getStart_date()));
            statement.setString(3, sdf.format(model.getEnd_date()));
            statement.setString(4, timeStamp);
            statement.setString(5, timeStamp);

            statement.executeUpdate();

            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(DepartementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(PeriodeModel model) {
        try {
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(""
                    + "UPDATE periode SET name=?,start_date=?,end_date=?,updated_at=? WHERE periode_id=?");
            
            statement.setString(1, model.getName());
            statement.setString(2, sdf.format(model.getStart_date()));
            statement.setString(3, sdf.format(model.getEnd_date()));
            statement.setString(4, timeStamp);
            statement.setInt(5,model.getPeriode_id());
            
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
                    + "DELETE FROM periode WHERE periode_id=?");
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    @Override
    public List<PeriodeModel> getData(String search) {
        list = new ArrayList<PeriodeModel>();

        try {
            String like = "'%" + search + "%'";
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM periode p WHERE p.name LIKE " + like + " OR p.start_date = '" + search + "' OR p.end_date = '" + like + "'");

            while (result.next()) {
                PeriodeModel model = new PeriodeModel();
                model.setPeriode_id(result.getInt("periode_id"));
                model.setName(result.getString("name"));
                model.setStart_date(result.getDate("start_date"));
                model.setEnd_date(result.getDate("end_date"));
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
    public List<PeriodeModel> getAllData() {
        list = new ArrayList<PeriodeModel>();

        try {
            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM periode p");

            while (result.next()) {
                PeriodeModel model = new PeriodeModel();
                model.setPeriode_id(result.getInt("periode_id"));
                model.setName(result.getString("name"));
                model.setStart_date(result.getDate("start_date"));
                model.setEnd_date(result.getDate("end_date"));
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
    public PeriodeModel getDetail(int id) {
        try {

            Statement statement = DbConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM periode where periode_id = '" + id + "' limit 1");

            PeriodeModel model = new PeriodeModel();

            if (result.next()) {
                model.setPeriode_id(result.getInt("periode_id"));
                model.setName(result.getString("name"));
                model.setStart_date(result.getDate("start_date"));
                model.setEnd_date(result.getDate("end_date"));
                model.setCreated_at(result.getTimestamp("created_at"));
                model.setUpdated_at(result.getTimestamp("updated_at"));
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
