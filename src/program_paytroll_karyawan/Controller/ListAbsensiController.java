/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import program_paytroll_karyawan.Dao.AbsensiDAO;
import program_paytroll_karyawan.Dao.ImplementAbsen;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Model.AbsensiModel;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.LoginModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableAbsensi;
import program_paytroll_karyawan.View.ListAbsensi;

/**
 *
 * @author lincbp
 */
public class ListAbsensiController {
    private final ListAbsensi panel;
    private List<AbsensiModel> list;
    private List<KaryawanModel> listKaryawan;
    private final ImplementAbsen dao;
    private AbsensiModel modelAbsen;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd H:m:s");
    private LoginModel loginData;
    
    public ListAbsensiController(ListAbsensi panel) {
        this.panel = panel;
        loginData = panel.getLoginData();
        dao = new AbsensiDAO();
    }
    
    public void initAbsensi(){
        
        if(loginData.getRole().equals("admin")){
            list = dao.getAbsensi();
        }else{
            list = dao.getAbsensiSearch(null, null, loginData.getEmploye_id());
        }
        
        panel.getTable().setModel(new TableAbsensi(list));

    }
    
    public void initEmploye(){
        KaryawanDAO karyawanController = new KaryawanDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listKaryawan = karyawanController.getAllData();
        comboModel.addElement(new ComboBoxModel("", ""));
        for(KaryawanModel item : listKaryawan){
            comboModel.addElement(new ComboBoxModel(item.getEmploye_name(),String.valueOf(item.getEmploye_id())));
        }
        panel.getKaryawanCombo().setModel(comboModel);
    }
    public void search(){
        int employe_id = 0;
        if(loginData.getRole().equals("admin")){
            if(panel.getKaryawanCombo().getSelectedIndex() > 0){
                Object selectedEmploye = panel.getKaryawanCombo().getSelectedItem();
                employe_id = Integer.valueOf(((ComboBoxModel)selectedEmploye).getValue());
            }
        }else{
            employe_id = loginData.getEmploye_id();
        }
        String fromDate = "";
        String toDate = "";
        if(panel.getFromDate().getDate() != null){
            fromDate = sdf.format(panel.getFromDate().getDate());
        }
        
        if(panel.getToDate().getDate() != null){
            toDate = sdf.format(panel.getToDate().getDate());
        }
        
        list = dao.getAbsensiSearch(fromDate, toDate, employe_id);
        panel.getTable().setModel(new TableAbsensi(list));
    }
    
    public void reset(){
        panel.getFromDate().setDate(null);
        panel.getToDate().setDate(null);
        panel.getKaryawanCombo().setSelectedIndex(0);
        
        this.initAbsensi();
    }
}
