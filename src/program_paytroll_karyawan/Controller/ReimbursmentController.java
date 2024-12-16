/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import program_paytroll_karyawan.Dao.ImplementReimburse;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Dao.ReimbursmentDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.ReimbursmentDetailModel;
import program_paytroll_karyawan.Model.ReimbursmentModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDetailReimbursement;
import program_paytroll_karyawan.Table.TableReimbursment;
import program_paytroll_karyawan.View.ReimburseForm;

/**
 *
 * @author lincbp
 */
public class ReimbursmentController {
    private final ReimburseForm panel;
    private List<ReimbursmentModel> list;
    private List<ReimbursmentDetailModel> listDetail = new ArrayList<ReimbursmentDetailModel>();
    private DefaultTableModel modelDetail ;
    private List<KaryawanModel> listKaryawan;
    private final ImplementReimburse dao;
//    private final ImplementLocation implementLocation;
    
    
    public ReimbursmentController(ReimburseForm panel) {
        this.panel = panel;
        dao = new ReimbursmentDAO();
    }
    
    
    public void initTable(){
        
        if(panel.getLoginUser().getRole().equals("admin")){
            list = dao.getReimbursment();
        }else{
            list = dao.getReimbursmentSearch("", panel.getLoginUser().getEmploye_id());
        }
        
        this.applyTable(list);
    }
    
    public void applyTable(List<ReimbursmentModel> list){
        panel.getTable().setModel(new TableReimbursment(list));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 5);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 6);
        buttonColumn2.setMnemonic(KeyEvent.VK_D);
    }
    public void initDetail(){
        panel.getReimburseDetail().setModel(new TableDetailReimbursement(listDetail));
        
        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getReimburseDetail(), null, 2);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
    }
    
    public void addDetail(){
        ReimbursmentDetailModel model = new ReimbursmentDetailModel();
        model.setDescription("");
        model.setCost(0.0);
        
        listDetail.add(model);
        
        this.initDetail();
    }
    public void reset(){
        panel.getNoReimburse().setText("");
        panel.getKaryawanCombo().setSelectedIndex(0);
        listDetail = new ArrayList<ReimbursmentDetailModel>();
        initDetail();
        panel.getNoReimburse().setEnabled(true);
        panel.getKaryawanCombo().setEnabled(true);
        panel.getTable().setEnabled(true);
        panel.getSaveButton().setVisible(true);
    }
    
    public void selectedRowDetail(){
        int row = panel.getReimburseDetail().getSelectedRow();
        
        if (row != -1){
            int col = panel.getReimburseDetail().getSelectedColumn();
            
            if(col == 2){
                listDetail.remove(row);
                this.initDetail();
            }        
        }  
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
    
    public void insert(){
        int employe_id = 0;
        int requestFrom = 0;
        if(panel.getLoginUser() != null){
            requestFrom = panel.getLoginUser().getEmploye_id();
        }
        if(panel.getLoginUser().getRole().equals("admin")){
            if(panel.getKaryawanCombo().getSelectedIndex() > 0){
                Object selectedEmploye = panel.getKaryawanCombo().getSelectedItem();
                employe_id = Integer.valueOf(((ComboBoxModel)selectedEmploye).getValue());
            }
        }else{
            employe_id = panel.getLoginUser().getEmploye_id();
        }
        ReimbursmentModel model = new ReimbursmentModel();
        model.setReimbursment_no(panel.getNoReimburse().getText());
        model.setEmploye_id(employe_id);
        model.setRequest_from(requestFrom);
        model.setCreated_by(panel.getLoginUser().getUsername());
        
        String res = this.validate(model);
        
        if(res.equals("Success")){
            int idReimbursment = dao.insertReimbursment(model);
            if(idReimbursment > 0){
                for (ReimbursmentDetailModel item:listDetail) {
                    if(!item.getDescription().equals("") && item.getCost() > 0){
                        item.setReimbursment_id(idReimbursment);
                        dao.insertReimbursmentDetail(item);
                    }
                }
            }
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
            this.reset();
            this.initTable();
            panel.moveToTable();
        }else{
            JOptionPane.showMessageDialog(null,res);
        }
        
    }
    
    public String validate(ReimbursmentModel model){
        String res = "Success";
        String validateText = "";
        
        if(model.getRequest_from()== 0){
            validateText += this.validateMessage(1, "Request From");
        }
        
        if(isNullOrEmpty(model.getReimbursment_no())){
            validateText += this.validateMessage(1, "Reimbursment No");
        }
        
        if(model.getEmploye_id() == 0){
            validateText += this.validateMessage(1, "Karyawan");
        }
                
        if(!validateText.equals("")){
            return validateText;
        }else{
            return res;
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
            
    public void delete(int id){
        int res = JOptionPane.showConfirmDialog(null, "Yakin pengen di hapus ?","Warning",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.YES_OPTION){
            dao.deleteReimbursmentHeader(id);
            dao.deleteReimbursmentDetail(id);
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            this.initTable();
        }
    }
    
    public void search(){
        String search = panel.getSearch().getText();
        if(panel.getLoginUser().getRole().equals("admin")){
            list = dao.getReimbursmentSearch(search,0);
        }else{
            list = dao.getReimbursmentSearch(search, panel.getLoginUser().getEmploye_id());
        }
        
        this.applyTable(list);
    }
    
    public void detail(ReimbursmentModel model){
        panel.moveToForm();
        panel.getNoReimburse().setText(model.getReimbursment_no());
        panel.getNoReimburse().setEnabled(false);
        panel.getKaryawanCombo().getModel().setSelectedItem(new ComboBoxModel(model.getEmployeDetail().getEmploye_name(), String.valueOf(model.getEmployeDetail().getEmploye_id())));
        panel.getKaryawanCombo().setEnabled(false);
        listDetail = model.getDetail();
        panel.getTable().setEnabled(false);
        this.initDetail();
        panel.getSaveButton().setVisible(false);
        
    }
    
    public void selectedRow(){
        int row = panel.getTable().getSelectedRow();
        
        if (row != -1){
            int id = Integer.valueOf(list.get(row).getReimbursment_id());
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
}
