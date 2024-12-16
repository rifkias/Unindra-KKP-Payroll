/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Dao.DepartementDAO;
import program_paytroll_karyawan.Dao.ImplementDepartement;
import program_paytroll_karyawan.Dao.LocationDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.LocationModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDepartement;
import program_paytroll_karyawan.View.DepartementForm;

import program_paytroll_karyawan.Model.ComboBoxModel;

/**
 *
 * @author lincbp
 */
public class DepartementController {
    private final DepartementForm panel;
    private List<DepartementModel> list;
    private final ImplementDepartement dao;
    private List<LocationModel> listLocation;
    
    public DepartementController(DepartementForm panel) {
        this.panel = panel;
        dao = new DepartementDAO();
        list = dao.getAllData();

    }
    
    public void isiTable(){
        list = dao.getAllData();
        
        panel.getTabel().setModel(new TableDepartement(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTabel(), null, 4);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTabel(), null, 5);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    public void initLocationValue(){
        LocationDAO locationController = new LocationDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listLocation = locationController.getAllLocation();
        
        for(LocationModel item : listLocation){
            comboModel.addElement(new ComboBoxModel(item.getName(),String.valueOf(item.getLocation_id())));
        }
        panel.getLocationComboBox().setModel(comboModel);
        
    }
    
    public void reset(){
        panel.getNameTxt().setText("");
        panel.getNotesTxt().setText("");
        panel.getHead().setText("MasterData > Departement > Save");
        panel.getButtonEdit().setVisible(false);
        panel.getButtonSave().setVisible(true);
        
    }
    
    public void insert(){
        Object selectedLocation = panel.getLocationComboBox().getSelectedItem();
        int location_id = Integer.valueOf(((ComboBoxModel)selectedLocation).getValue());
        DepartementModel model = new DepartementModel();
        model.setName(panel.getNameTxt().getText());
        model.setNotes(panel.getNotesTxt().getText());
        model.setLocation_id(location_id);
        String res = this.validate(model);
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
    
    public void edit(){
        Object selectedLocation = panel.getLocationComboBox().getSelectedItem();
        int location_id = Integer.valueOf(((ComboBoxModel)selectedLocation).getValue());
        DepartementModel model = new DepartementModel();
        model.setName(panel.getNameTxt().getText());
        model.setNotes(panel.getNotesTxt().getText());
        model.setLocation_id(location_id);
        model.setDepartement_id(Integer.valueOf(panel.getIdTxt().getText()));
        String res = this.validate(model);
        if(res.equals("Success")){
            dao.update(model);
            JOptionPane.showMessageDialog(null,"Data Berhasil DiUbah");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        }else{
            JOptionPane.showMessageDialog(null,res);
        }
    }
     
    public void getData(){
        if (panel.getSearchTxt().getText().trim().isEmpty()){
            this.isiTable();
            return;
        }
        String nama = panel.getSearchTxt().getText();
        
        list = dao.getData(nama);
        panel.getTabel().setModel(new TableDepartement(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTabel(), null, 4);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTabel(), null, 5);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public void selectedRow(){
        int row = panel.getTabel().getSelectedRow();
        
        if (row != -1){
            int id = Integer.valueOf(list.get(row).getDepartement_id());
            int col = panel.getTabel().getSelectedColumn();
            
            if(col == 4){
                this.detail(list.get(row));
            }
            
            if(col == 5){
                this.delete(id);
            }
            
            
            System.out.println("Table Selected Row :"+row+" Col :"+col+" Id :"+id);
            
        }  
    }
    
    public void detail(DepartementModel list){
        panel.getNameTxt().setText(list.getName());
        panel.getNotesTxt().setText(list.getNotes());
        panel.getIdTxt().setText(String.valueOf(list.getDepartement_id()));
        panel.getLocationComboBox().getModel().setSelectedItem(new ComboBoxModel(list.getLocation().getName(),String.valueOf(list.getLocation().getLocation_id())));
        
        panel.getHead().setText("MasterData > Departement > Update");
        panel.moveToForm();
        panel.getButtonEdit().setVisible(true);
        panel.getButtonSave().setVisible(false);
    }
    
    public void delete(int id){
        int res = JOptionPane.showConfirmDialog(null, "Yakin pengen di hapus ?","Warning",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.YES_OPTION){            
            dao.delete(id);
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            this.isiTable();
        }
    }
    
     public String validate(DepartementModel model){
        String res = "Success";
        if(this.isNullOrEmpty(model.getName())){
            return this.validateMessage(1, "Nama");
        }
        if(this.isNullOrEmpty(model.getNotes())){
            return this.validateMessage(1, "Notes");
        }
        return res;
    }
    public String validateMessage(int seq,String text){
        if(seq == 1){
            return "Field "+text+" Wajib Diisi";
        }else{
            return text;
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
