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
import program_paytroll_karyawan.Dao.ImplementPeriode;
import program_paytroll_karyawan.Dao.PeriodeDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.PeriodeModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableDivision;
import program_paytroll_karyawan.Table.TablePeriode;
import program_paytroll_karyawan.View.DivisionForm;
import program_paytroll_karyawan.View.PeriodeForm;

/**
 *
 * @author lincbp
 */
public class PeriodeController {

    private final PeriodeForm panel;
    private List<PeriodeModel> list;
    private final ImplementPeriode dao;

    public PeriodeController(PeriodeForm panel) {
        this.panel = panel;
        dao = new PeriodeDAO();
        list = dao.getAllData();
    }

    public void selectedRow() {
        int row = panel.getTable().getSelectedRow();

        if (row != -1) {
            int id = list.get(row).getPeriode_id();
            int col = panel.getTable().getSelectedColumn();

            if (col == 6) {
                this.detail(list.get(row));
            }

            if (col == 7) {
                this.delete(id);
            }

            System.out.println("Table Selected Row :" + row + " Col :" + col + " Id :" + id);

        }
    }

    public void detail(PeriodeModel row) {
        panel.getNameTxt().setText(row.getName());
        panel.getIdTxt().setText(String.valueOf(row.getPeriode_id()));
        panel.getStartdate().setDate(row.getStart_date());
        panel.getEnddate().setDate(row.getEnd_date());
        panel.getHead().setText("Master Data > Periode > Update");
        panel.moveToForm();
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
        panel.getNameTxt().setText("");
        panel.getStartdate().setCalendar(null);
        panel.getEnddate().setCalendar(null);
        panel.getHead().setText("Master Data > Periode > Save");
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

    public void applyTable(List<PeriodeModel> list) {
        panel.getTable().setModel(new TablePeriode(list));

        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTable(), null, 6);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 7);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }

    public void insert() {
        PeriodeModel model = new PeriodeModel();
        model.setName(panel.getNameTxt().getText());
        model.setStart_date(panel.getStartdate().getDate());
        model.setEnd_date(panel.getEnddate().getDate());
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
        PeriodeModel model = new PeriodeModel();
        model.setPeriode_id(Integer.valueOf(panel.getIdTxt().getText()));
        model.setName(panel.getNameTxt().getText());
        model.setStart_date(panel.getStartdate().getDate());
        model.setEnd_date(panel.getEnddate().getDate());
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

    public String validate(PeriodeModel model) {
        String res = "Success";
        if (this.isNullOrEmpty(model.getName())) {
            return this.validateMessage(1, "Nama");
        }
        if (model.getStart_date() == null) {
            return this.validateMessage(1, "Start Date");
        }
        if (model.getEnd_date() == null) {
            return this.validateMessage(1, "End Date");
        }

        if (model.getStart_date().after(model.getEnd_date())) {
            return this.validateMessage(0, "Start Date Greater Than End Date");
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
