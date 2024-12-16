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
import program_paytroll_karyawan.Dao.DivisionDAO;
import program_paytroll_karyawan.Dao.ImplementDivision;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDivision;
import program_paytroll_karyawan.View.DivisionForm;

/**
 *
 * @author lincbp
 */
public class DivisionController {
    private final DivisionForm panel;
    private List<DivisionModel> list;
    private final ImplementDivision dao;
    private List<DepartementModel> listExt;

    public DivisionController(DivisionForm panel) {
        this.panel = panel;
        dao = new DivisionDAO();
        list = dao.getAllData();
    }
     public void initDepartementValue(){
        DepartementDAO departementController = new DepartementDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listExt = departementController.getAllData();
        
        for(DepartementModel item : listExt){
            comboModel.addElement(new ComboBoxModel(item.getLocation().getName()+" : "+item.getName(),String.valueOf(item.getDepartement_id())));
        }
        panel.getComboBox().setModel(comboModel);
        
    }
    public void selectedRow(){
        int row = panel.getTable().getSelectedRow();
        
        if (row != -1){
            int id = Integer.valueOf(list.get(row).getDivision_id());
            int col = panel.getTable().getSelectedColumn();
            
            if(col == 5){
                this.detail(list.get(row));
            }
            
            if(col == 6){
                this.delete(id);
            }
            
            
            System.out.println("Table Selected Row :"+row+" Col :"+col+" Id :"+id);
            
        }  
    }
    public void detail(DivisionModel row){
        panel.getNameTxt().setText(row.getName());
        panel.getNotesTxt().setText(row.getNotes());
        panel.getIdTxt().setText(String.valueOf(row.getDivision_id()));
        panel.getComboBox().getModel().setSelectedItem(new ComboBoxModel(row.getDepartement().getLocation().getName()+" : "+row.getName(),String.valueOf(row.getDepartement_id())));
        
        panel.moveToForm();
        panel.getHead().setText("Master Data > Division > Update");
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
    public void isiTable(){
        list = dao.getAllData();
        this.applyTable(list);
    }
    
    public void reset(){
        panel.getNameTxt().setText("");
        panel.getNotesTxt().setText("");
        panel.getHead().setText("Master Data > Division > Save");
        panel.getButtonEdit().setVisible(false);
        panel.getButtonSave().setVisible(true);
    }
    
    public void getData(){
        if(panel.getSearch().getText().trim().isEmpty()){
            this.isiTable();
            return;
        }
        String nama = panel.getSearch().getText();
        
        list = dao.getData(nama);
        
        this.applyTable(list);
    }
    
    public void applyTable(List<DivisionModel> list){
        panel.getTable().setModel(new TableDivision(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 5);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 6);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public void insert(){
        DivisionModel model = new DivisionModel();
        model.setName(panel.getNameTxt().getText());
        model.setNotes(panel.getNotesTxt().getText());
        model.setDepartement_id(this.getSelectedId(panel.getComboBox()));
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
        DivisionModel model = new DivisionModel();
        model.setName(panel.getNameTxt().getText());
        model.setNotes(panel.getNotesTxt().getText());
        model.setDepartement_id(this.getSelectedId(panel.getComboBox()));
        model.setDivision_id(Integer.valueOf(panel.getIdTxt().getText()));
        String res = this.validate(model);
        if(res.equals("Success")){
            dao.update(model);
            JOptionPane.showMessageDialog(null,"Data Berhasil DiUpdate");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        }else{
            JOptionPane.showMessageDialog(null,res);
        }
    }
    
    public int getSelectedId(JComboBox comboBox){
        Object selectedLocation = comboBox.getSelectedItem();
        int id = Integer.valueOf(((ComboBoxModel)selectedLocation).getValue());
        return id;
    }
    
    public String validate(DivisionModel model){
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
