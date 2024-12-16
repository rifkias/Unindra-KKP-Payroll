/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Dao.DepartementDAO;
import program_paytroll_karyawan.Dao.DivisionDAO;
import program_paytroll_karyawan.Dao.ImplementKaryawan;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Dao.LocationDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.LocationModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableKaryawan;
import program_paytroll_karyawan.View.KaryawanForm;

/**
 *
 * @author lincbp
 */
public class KaryawanController {
    private final KaryawanForm panel;
    private List<KaryawanModel> list;
    private final ImplementKaryawan dao;
    private List<LocationModel> listLocation;
    private List<DepartementModel> listDepartement;
    private List<DivisionModel> listDivision;
    
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    
    private String loginBy;
    
    public KaryawanController(KaryawanForm panel) {
        this.panel = panel;
        this.dao = new KaryawanDAO();
        list = dao.getAllData();
        loginBy = panel.accessLoginData().getEmploye_name();
    }
    
    public void isiTable(){
        list = dao.getAllData();
        this.applyTable(list);
    }
    
    public void applyTable(List<KaryawanModel> list){
        panel.getTable().setModel(new TableKaryawan(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 13);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 14);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
        
    public void reset(){
        panel.getEmployeName().setText("");
        panel.getNik().setText("");
//        panel.getDob().setDateFormatString("");
        panel.getDob().setDate(null);
        panel.getUsername().setText("");
        panel.getPassword().setText("");
        panel.getSalary().setText("");        
//        panel.getDob().setDate(new Date());
        panel.getLocationCombo().getModel().setSelectedItem(new ComboBoxModel("",""));
        panel.getDepartementCombo().getModel().setSelectedItem(new ComboBoxModel("",""));
        panel.getDepartementCombo().getModel().setSelectedItem(new ComboBoxModel("", ""));
        panel.getHead().setText("Master Data > Karyawan > Save");
        panel.getButtonEdit().setVisible(false);
        panel.getButtonSave().setVisible(true);
        this.getDepartement();
        this.getDivision();
    }   
    
    public void initRoleValue(){
        
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        comboModel.addElement(new ComboBoxModel("Admin","admin"));
        comboModel.addElement(new ComboBoxModel("User","user"));
        
        panel.getRole().setModel(comboModel);
    }
    
    public void initActive(){
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        comboModel.addElement(new ComboBoxModel("Active","1"));
        comboModel.addElement(new ComboBoxModel("Not Active","0"));
        
        panel.getIsActive().setModel(comboModel);
    }
    
    public void initLocation(){
        LocationDAO locationController = new LocationDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listLocation = locationController.getAllLocation();
        comboModel.addElement(new ComboBoxModel("", ""));
        for(LocationModel item : listLocation){
            comboModel.addElement(new ComboBoxModel(item.getName(),String.valueOf(item.getLocation_id())));
        }
        panel.getLocationCombo().setModel(comboModel);
    }
    
    public void getDepartement(){
        Object selectedLocation = panel.getLocationCombo().getSelectedItem();
        String stringSelected = ((ComboBoxModel)selectedLocation).getValue();
        
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        
        if(!stringSelected.equals("") && stringSelected != null){
            comboModel.addElement(new ComboBoxModel("", ""));
            int location_id = Integer.valueOf(stringSelected);
            DepartementDAO departementController = new DepartementDAO();
            listDepartement = departementController.getAllByLocationId(location_id);
            for(DepartementModel item : listDepartement){
                comboModel.addElement(new ComboBoxModel(item.getName(),String.valueOf(item.getDepartement_id())));
            }
        }
        
        panel.getDepartementCombo().setModel(comboModel);
    }
    
    public void getDivision(){
        
        Object selectedDepartement = panel.getDepartementCombo().getSelectedItem();
        
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        if(panel.getDepartementCombo().getSelectedIndex() > 0){
            String stringSelected = ((ComboBoxModel)selectedDepartement).getValue();

            if(!stringSelected.equals("") && stringSelected != null){
                comboModel.addElement(new ComboBoxModel("", ""));
                int departement_id = Integer.valueOf(stringSelected);
                DivisionDAO divisionController = new DivisionDAO();
                listDivision = divisionController.getDataByDepartementId(departement_id);

                for(DivisionModel item : listDivision){
                    comboModel.addElement(new ComboBoxModel(item.getName(),String.valueOf(item.getDivision_id())));
                }
            }
        }
        panel.getDivisionCombo().setModel(comboModel);

    }
    
    public void insert(){
        int location_id = 0;
        int departement_id = 0;
        int division_id = 0;
        Double salary = 0.0;
        if(panel.getLocationCombo().getSelectedIndex() > 0){
            Object selectedLocation = panel.getLocationCombo().getSelectedItem();
            location_id = Integer.valueOf(((ComboBoxModel)selectedLocation).getValue());
        }
        
        if(panel.getDepartementCombo().getSelectedIndex() > 0){
            Object selectedDepartement = panel.getDepartementCombo().getSelectedItem();
            departement_id = Integer.valueOf(((ComboBoxModel)selectedDepartement).getValue());
        }
        
        if(panel.getDivisionCombo().getSelectedIndex() > 0){
            Object selectedDivision = panel.getDivisionCombo().getSelectedItem();
            division_id = Integer.valueOf(((ComboBoxModel)selectedDivision).getValue());
        }
        
        if(!isNullOrEmpty(panel.getSalary().getText())){
            salary = Double.valueOf(panel.getSalary().getText());
        }
        
        
        
        Object selectedRole = panel.getRole().getSelectedItem();
        String role = ((ComboBoxModel)selectedRole).getValue();
        
        Object selectedActive = panel.getIsActive().getSelectedItem();
        int isActive = Integer.valueOf(((ComboBoxModel)selectedActive).getValue());
        
        LocalDate dob = panel.getDob().getDate();
        KaryawanModel model = new KaryawanModel();
        model.setEmploye_name(panel.getEmployeName().getText());
        model.setNik(panel.getNik().getText());
        model.setDate_of_birth(dob);
        model.setUsername(panel.getUsername().getText());
        
        String password = dao.encryptPassword(panel.getPassword().getText());
        model.setPassword(password);
        
        model.setSalary(salary);
        model.setRole(role);
        model.setLocation_id(location_id);
        model.setDepartement_id(departement_id);
        model.setDivision_id(division_id);
        model.setCreated_by(loginBy);
        model.setIs_active(isActive);
        
        String res = this.validate(model,"insert",dao);
        
        if(res.equals("Success")){
            dao.input(model);
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        }else{
            JOptionPane.showMessageDialog(null,res);
        }
    }
    public String validate(KaryawanModel model,String type,ImplementKaryawan dao){
        String res = "Success";
        String validateText = "";
        
        if(this.isNullOrEmpty(model.getEmploye_name())){
            validateText +=  this.validateMessage(1, "Nama Karyawan");
        }
        
        if(this.isNullOrEmpty(model.getNik())){
            validateText += this.validateMessage(1, "Nik");
        }
        
        if(this.isNullOrEmpty(model.getUsername())){
            validateText += this.validateMessage(1, "Username");
        }
        
        if(type.equals("insert")){
            if(this.isNullOrEmpty(model.getPassword())){
                validateText += this.validateMessage(1, "Password");
            }
            
            if(dao.checkUsername(model.getUsername())){
                validateText += this.validateMessage(0, "Username Telah Digunakan");
            }
            
            if(dao.checkNik(model.getNik())){
                validateText += this.validateMessage(0, "NIK Tidak boleh duplikat");
            }
        }
        
        if(model.getDate_of_birth() == null){
            validateText += this.validateMessage(1, "Tanggal Lahir");
        }
        
        if(model.getLocation_id() == 0){
            validateText += this.validateMessage(1, "Lokasi");
        }
        
        if(model.getDepartement_id() == 0){
            validateText += this.validateMessage(1, "Departement");
        }
        
        if(model.getDivision_id() == 0){
            validateText += this.validateMessage(1, "Division");
        }
        
        
        if(!validateText.equals("")){
            return validateText;
        }else{
            return res;
        }
    }
    
    public void selectedRow(){
        int row = panel.getTable().getSelectedRow();
        
        if (row != -1){
            int id = Integer.valueOf(list.get(row).getEmploye_id());
            int col = panel.getTable().getSelectedColumn();
            
            if(col == 13){
                this.detail(list.get(row));
            }
            
            if(col == 14){
                this.delete(id);
            }
            
            
            System.out.println("Table Selected Row :"+row+" Col :"+col+" Id :"+id);
            
        }  
        
    }
    public void detail(KaryawanModel list){
        panel.getIdTxt().setText(String.valueOf(list.getEmploye_id()));
        panel.getEmployeName().setText(list.getEmploye_name());
        panel.getNik().setText(list.getNik());
        panel.getUsername().setText(list.getUsername());
        panel.getUsername().setEditable(false);
        panel.getSalary().setText(String.valueOf(list.getSalary()));
        panel.getDob().setDate(list.getDate_of_birth());
        panel.getRole().getModel().setSelectedItem(new ComboBoxModel(CapitalizeFirstLetter(list.getRole()),list.getRole()));
        if(list.getIs_active() == 0){
            panel.getIsActive().getModel().setSelectedItem(new ComboBoxModel("Not Active","0"));
        }else{
            panel.getIsActive().getModel().setSelectedItem(new ComboBoxModel("Active","1"));
        }
        
        panel.getLocationCombo().getModel().setSelectedItem(new ComboBoxModel(list.getLocation().getName(),String.valueOf(list.getLocation().getLocation_id())));
        this.getDepartement();
        
        panel.getDepartementCombo().getModel().setSelectedItem(new ComboBoxModel(list.getDepartement().getName(), String.valueOf(list.getDepartement().getDepartement_id())));
        this.getDivision();
        
        panel.getDivisionCombo().getModel().setSelectedItem(new ComboBoxModel(list.getDivision().getName(), String.valueOf(list.getDivision().getDivision_id())));
        panel.moveToForm();
        panel.getHead().setText("Master Data > Karyawan > Update");
        panel.getButtonEdit().setVisible(true);
        panel.getButtonSave().setVisible(false);
        
    }
    
    public void getData(){
        if (panel.getSearch().getText().trim().isEmpty()){
            this.isiTable();
            return;
        }
        String nama = panel.getSearch().getText();
        
        list = dao.getData(nama);
        this.applyTable(list);
//        panel.getTable().setModel(new TableKaryawan(list));
//        
//        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 13);
//        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 14);
//        buttonColumn1.setMnemonic(KeyEvent.VK_D);
//        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public static String CapitalizeFirstLetter(String str){
        if(str == null || str.length()<=1) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public void delete(int id){
        int res = JOptionPane.showConfirmDialog(null, "Yakin pengen di hapus ?","Warning",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.YES_OPTION){            
            dao.delete(id);
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            this.isiTable();
        }
    }
    
    public void edit(){
        int location_id = 0;
        int departement_id = 0;
        int division_id = 0;
        Double salary = 0.0;
        if(panel.getLocationCombo().getSelectedIndex() > 0 || !panel.getLocationCombo().getSelectedItem().equals("")){
            Object selectedLocation = panel.getLocationCombo().getSelectedItem();
            location_id = Integer.valueOf(((ComboBoxModel)selectedLocation).getValue());
        }
        if(panel.getDepartementCombo().getSelectedIndex() > 0 || !panel.getDepartementCombo().getSelectedItem().equals("")){ 
            Object selectedDepartement = panel.getDepartementCombo().getSelectedItem();
            departement_id = Integer.valueOf(((ComboBoxModel)selectedDepartement).getValue());
        }
        
        if(panel.getDivisionCombo().getSelectedIndex() > 0 || !panel.getDivisionCombo().getSelectedItem().equals("")){
            Object selectedDivision = panel.getDivisionCombo().getSelectedItem();
            division_id = Integer.valueOf(((ComboBoxModel)selectedDivision).getValue());
            System.out.println(((ComboBoxModel)selectedDivision).getKey()+"  "+((ComboBoxModel)selectedDivision).getValue());
        }
        
        System.out.println("Location :"+location_id);
        System.out.println("Departement :"+departement_id);
        System.out.println("Divisi :"+division_id);
        
        if(!isNullOrEmpty(panel.getSalary().getText())){
            salary = Double.valueOf(panel.getSalary().getText());
        }
        
        
        
        Object selectedRole = panel.getRole().getSelectedItem();
        String role = ((ComboBoxModel)selectedRole).getValue();
        
        Object selectedActive = panel.getIsActive().getSelectedItem();
        int isActive = Integer.valueOf(((ComboBoxModel)selectedActive).getValue());
        
        LocalDate dob = panel.getDob().getDate();
        KaryawanModel model = new KaryawanModel();
        model.setEmploye_id(Integer.valueOf(panel.getIdTxt().getText()));
        model.setEmploye_name(panel.getEmployeName().getText());
        model.setNik(panel.getNik().getText());
        model.setDate_of_birth(dob);
        model.setUsername(panel.getUsername().getText());
        
        String password = "";
        if(!panel.getPassword().getText().equals("")){
            password = dao.encryptPassword(panel.getPassword().getText());
        }
        model.setPassword(password);
        model.setSalary(salary);
        model.setRole(role);
        model.setLocation_id(location_id);
        model.setDepartement_id(departement_id);
        model.setDivision_id(division_id);
        model.setCreated_by(loginBy);
        model.setIs_active(isActive);
        
        String res = this.validate(model,"update",dao);
        
        if(res.equals("Success")){
            dao.update(model);
            
            if(!model.getPassword().equals("")){
                System.out.println("update password");
                dao.updatePassword(model.getPassword(), model.getEmploye_id());
            }
            JOptionPane.showMessageDialog(null,"Data Berhasil DiUbah");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        }else{
            JOptionPane.showMessageDialog(null,res);
        }
    }
    
    public String validateMessage(int seq,String text){
        if(seq == 1){
            return "Field "+text+" Wajib Diisi \n";
        }else{
            return text+" \n";
        }
    }
    public boolean isNullOrEmpty(String text){
        if(text.equals("") || text == null){
            return true;
        }else{
            return false;
        }
    }
}
