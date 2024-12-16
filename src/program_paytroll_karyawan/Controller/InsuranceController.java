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
import program_paytroll_karyawan.Dao.ImplementInsurance;
import program_paytroll_karyawan.Dao.PajakDAO;
import program_paytroll_karyawan.Dao.ImplementPajak;
import program_paytroll_karyawan.Dao.InsuranceDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.InsuranceModel;
import program_paytroll_karyawan.Model.PajakModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDivision;
import program_paytroll_karyawan.Table.TableInsurance;
import program_paytroll_karyawan.Table.TablePajak;
import program_paytroll_karyawan.View.InsuranceForm;
import program_paytroll_karyawan.View.PajakForm;

/**
 *
 * @author lincbp
 */
public class InsuranceController {
    private final InsuranceForm panel;
    private List<InsuranceModel> list;
    private final ImplementInsurance dao;

    public InsuranceController(InsuranceForm panel) {
        this.panel = panel;
        dao = new InsuranceDAO();
        list = dao.getAllData();
    }
    public void selectedRow(){
        int row = panel.getTable().getSelectedRow();
        
        if (row != -1){
            int id = list.get(row).getAsuransi_id();
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
    public void detail(InsuranceModel row){
        panel.getIdTxt().setText(String.valueOf(row.getAsuransi_id()));
        panel.getNameTxt().setText(row.getName());
        panel.getClassTxt().setText(row.getAsuransi_class());
        panel.getPercentageTxt().setText(String.valueOf(row.getPremi()));

        panel.moveToForm();
        panel.getHead().setText("Master Data > Asuransi > Update");
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
        panel.getClassTxt().setText("");
        panel.getPercentageTxt().setText("");
        panel.getHead().setText("Master Data > Asuransi > Save");
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
    
    public void applyTable(List<InsuranceModel> list){
        panel.getTable().setModel(new TableInsurance(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 4);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 5);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public void insert(){
        InsuranceModel model = new InsuranceModel();
        model.setName(panel.getNameTxt().getText());
        model.setAsuransi_class(panel.getClassTxt().getText());
        if(panel.getPercentageTxt().getText().equals("")){
            model.setPremi(-1.0);
        }else{
            model.setPremi(Double.valueOf(panel.getPercentageTxt().getText().replace(",","")));    
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
        InsuranceModel model = new InsuranceModel();
        model.setAsuransi_id(Integer.valueOf(panel.getIdTxt().getText()));
        model.setName(panel.getNameTxt().getText());
        model.setAsuransi_class(panel.getClassTxt().getText());
        if(panel.getPercentageTxt().getText().equals("")){
            model.setPremi(-1.0);
        }else{
            model.setPremi(Double.valueOf(panel.getPercentageTxt().getText().replace(",","")));    
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
    
    public String validate(InsuranceModel model){
        String res = "Success";
        if(this.isNullOrEmpty(model.getName())){
            return this.validateMessage(1, "Nama");
        }
        if(this.isNullOrEmpty(model.getAsuransi_class())){
            return this.validateMessage(1, "Kelas Asuransi");
        }
//        System.out.println(model.getPercentage());
        if(model.getPremi() < 0){
            return this.validateMessage(1, "Premi");
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
