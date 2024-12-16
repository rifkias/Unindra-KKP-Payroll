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
import program_paytroll_karyawan.Dao.PajakDAO;
import program_paytroll_karyawan.Dao.ImplementPajak;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.PajakModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDivision;
import program_paytroll_karyawan.Table.TablePajak;
import program_paytroll_karyawan.View.PajakForm;

/**
 *
 * @author lincbp
 */
public class PajakController {
    private final PajakForm panel;
    private List<PajakModel> list;
    private final ImplementPajak dao;

    public PajakController(PajakForm panel) {
        this.panel = panel;
        dao = new PajakDAO();
        list = dao.getAllData();
    }
    public void selectedRow(){
        int row = panel.getTable().getSelectedRow();
        
        if (row != -1){
            int id = Integer.valueOf(list.get(row).getPajak_id());
            int col = panel.getTable().getSelectedColumn();
            
            if(col == 4){
                this.detail(list.get(row));
            }
            
            if(col == 5){
                this.delete(id);
            }
            
            
            System.out.println("Table Selected Row :"+row+" Col :"+col+" Id :"+id);
            
        }  
    }
    public void detail(PajakModel row){
        panel.getIdTxt().setText(String.valueOf(row.getPajak_id()));
        panel.getNameTxt().setText(row.getName());
        panel.getNotesTxt().setText(row.getDescription());
        panel.getPercentageTxt().setText(String.valueOf(row.getPercentage()));

        panel.moveToForm();
        panel.getHead().setText("Master Data > Pajak > Update");
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
        panel.getPercentageTxt().setText("");
        panel.getHead().setText("Master Data > Pajak > Save");
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
    
    public void applyTable(List<PajakModel> list){
        panel.getTable().setModel(new TablePajak(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 4);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 5);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public void insert(){
        PajakModel model = new PajakModel();
        model.setName(panel.getNameTxt().getText());
        model.setDescription(panel.getNotesTxt().getText());
        if(panel.getPercentageTxt().getText().equals("")){
            model.setPercentage(-1);
        }else{
            model.setPercentage(Double.valueOf(panel.getPercentageTxt().getText()));    
        }
        
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
        PajakModel model = new PajakModel();
        model.setPajak_id(Integer.valueOf(panel.getIdTxt().getText()));
        model.setName(panel.getNameTxt().getText());
        model.setDescription(panel.getNotesTxt().getText());
        if(panel.getPercentageTxt().getText().equals("")){
            model.setPercentage(-1);
        }else{
            model.setPercentage(Double.valueOf(panel.getPercentageTxt().getText()));    
        }
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
    
    public String validate(PajakModel model){
        String res = "Success";
        if(this.isNullOrEmpty(model.getName())){
            return this.validateMessage(1, "Nama");
        }
        if(this.isNullOrEmpty(model.getDescription())){
            return this.validateMessage(1, "Description");
        }
        System.out.println(model.getPercentage());
        if(model.getPercentage() < 0){
            return this.validateMessage(1, "Percentage");
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
