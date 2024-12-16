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
import program_paytroll_karyawan.Dao.ImplementInsuranceEmploye;
import program_paytroll_karyawan.Dao.InsuranceDAO;
import program_paytroll_karyawan.Dao.InsuranceEmployeDAO;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.InsuranceEmployeModel;
import program_paytroll_karyawan.Model.InsuranceModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDivision;
import program_paytroll_karyawan.Table.TableInsuranceEmploye;
import program_paytroll_karyawan.View.DivisionForm;
import program_paytroll_karyawan.View.EmployeInsuranceForm;

/**
 *
 * @author lincbp
 */
public class EmployeInsuranceController {

    private final EmployeInsuranceForm panel;
    private List<InsuranceEmployeModel> list;
    private final ImplementInsuranceEmploye dao;
    private List<KaryawanModel> listKaryawan;
    private List<InsuranceModel> listInsurance;

    public EmployeInsuranceController(EmployeInsuranceForm panel) {
        this.panel = panel;
        dao = new InsuranceEmployeDAO();
        list = dao.getAllData();
    }

    public void initEmployeValue() {
        KaryawanDAO controller = new KaryawanDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listKaryawan = controller.getAllData();

        for (KaryawanModel item : listKaryawan) {
            comboModel.addElement(new ComboBoxModel(item.getNik() + " : " + item.getEmploye_name(), String.valueOf(item.getEmploye_id())));
        }
        panel.getComboBoxEmploye().setModel(comboModel);

    }
    
    public void initInsuranceValue() {
        InsuranceDAO controller = new InsuranceDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listInsurance = controller.getAllData();

        for (InsuranceModel item : listInsurance) {
            comboModel.addElement(new ComboBoxModel(item.getName() + " : " + item.getAsuransi_class(), String.valueOf(item.getAsuransi_id())));
        }
        panel.getComboBoxInsurance().setModel(comboModel);

    }

    public void selectedRow() {
        int row = panel.getTable().getSelectedRow();

        if (row != -1) {
            int id = list.get(row).getEmploye_id();
            int col = panel.getTable().getSelectedColumn();

            if (col == 10) {
                this.detail(list.get(row));
            }

            if (col == 11) {
                this.delete(id);
            }

            System.out.println("Table Selected Row :" + row + " Col :" + col + " Id :" + id);

        }
    }

    public void detail(InsuranceEmployeModel row) {
        panel.getComboBoxEmploye().getModel().setSelectedItem(new ComboBoxModel(row.getEmploye().getNik()+" : "+row.getEmploye().getEmploye_name(),String.valueOf(row.getEmploye_id())));
        panel.getComboBoxInsurance().getModel().setSelectedItem(new ComboBoxModel(row.getInsurance().getName()+" : "+row.getInsurance().getAsuransi_class(),String.valueOf(row.getInsurance_id())));
        panel.getIdTxt().setText(String.valueOf(row.getEmploye_insurance_id()));

        panel.moveToForm();
        panel.getHead().setText("Master Data > Asuransi Karyawan > Update");
        panel.getButtonEdit().setVisible(true);
        panel.getButtonSave().setVisible(false);
    }

    public void delete(int id) {
        int res = JOptionPane.showConfirmDialog(null, "Yakin pengen di hapus ?", "Warning", JOptionPane.YES_NO_OPTION);

        if (res == JOptionPane.YES_OPTION) {
            dao.delete(id);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            this.isiTable();
        }
    }

    public void isiTable() {
        list = dao.getAllData();
        this.applyTable(list);
    }

    public void reset() {
//        panel.getNameTxt().setText("");
//        panel.getNotesTxt().setText("");
        panel.getHead().setText("Master Data > Asuransi Karyawan > Save");
        panel.getButtonEdit().setVisible(false);
        panel.getButtonSave().setVisible(true);
    }

    public void getData() {
        if (panel.getSearch().getText().trim().isEmpty()) {
            this.isiTable();
            return;
        }
        String nama = panel.getSearch().getText();

        list = dao.getData(nama);

        this.applyTable(list);
    }

    public void applyTable(List<InsuranceEmployeModel> list) {
        panel.getTable().setModel(new TableInsuranceEmploye(list));

        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 10);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 11);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }

    public void insert() {
        InsuranceEmployeModel model = new InsuranceEmployeModel();
        model.setEmploye_id(getSelectedId(panel.getComboBoxEmploye()));
        model.setInsurance_id(getSelectedId(panel.getComboBoxInsurance()));
        String res = this.validate(model);
        if (res.equals("Success")) {
            dao.input(model);
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        } else {
            JOptionPane.showMessageDialog(null, res);
        }
    }

    public void edit() {
        InsuranceEmployeModel model = new InsuranceEmployeModel();
        model.setEmploye_id(getSelectedId(panel.getComboBoxEmploye()));
        model.setInsurance_id(getSelectedId(panel.getComboBoxInsurance()));
        model.setEmploye_insurance_id(Integer.parseInt(panel.getIdTxt().getText()));
        String res = this.validate(model);
        if (res.equals("Success")) {
            dao.update(model);
            JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        } else {
            JOptionPane.showMessageDialog(null, res);
        }
    }

    public int getSelectedId(JComboBox comboBox) {
        Object selectedLocation = comboBox.getSelectedItem();
        int id = Integer.valueOf(((ComboBoxModel) selectedLocation).getValue());
        return id;
    }

    public String validate(InsuranceEmployeModel model) {
        String res = "Success";
        if(dao.checkDuplicate(model)){
               res = "Duplicate Value !!!";
        }
        return res;
    }

    public String validateMessage(int seq, String text) {
        if (seq == 1) {
            return "Field " + text + " Wajib Diisi";
        } else {
            return text;
        }
    }

    public boolean isNullOrEmpty(String text) {
        if (text.equals("") || text == null) {
            return true;
        } else {
            return false;
        }
    }
}
