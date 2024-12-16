/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import program_paytroll_karyawan.Dao.ImplementLocation;
import program_paytroll_karyawan.Dao.LocationDAO;
import program_paytroll_karyawan.Model.LocationModel;
import program_paytroll_karyawan.Table.TableLocation;
import program_paytroll_karyawan.View.Form_Login;
import program_paytroll_karyawan.View.LocationForm;

import program_paytroll_karyawan.Table.ButtonColumn;

/**
 *
 * @author lincbp
 */
public class LocationController {

    private final LocationForm panel;
    private List<LocationModel> list;
    private final ImplementLocation implementLocation;

    public LocationController(LocationForm panel) {
        this.panel = panel;
        implementLocation = new LocationDAO();
        list = implementLocation.getAllLocation();

    }

    public void isiTable() {
        list = implementLocation.getAllLocation();

        panel.getTabelLocation().setModel(new TableLocation(list));

        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTabelLocation(), null, 10);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTabelLocation(), null, 9);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }

    public void reset() {
        panel.getNameTxt().setText("");
        panel.getDistrictTxt().setText("");
        panel.getProvinceTxt().setText("");
        panel.getCityTxt().setText("");
        panel.getSubDistrictTxt().setText("");
        panel.getZipCodeTxt().setText("");
        panel.getAddress1Txt().setText("");
        panel.getAddress2Txt().setText("");
        panel.getLocationIdTxt().setText("");
        panel.getHeadLabel().setText("Master Data > Location > Save");
        panel.getButtonSave().setVisible(true);
        panel.getButtonEdit().setVisible(false);
    }

    public void insert() {
        LocationModel model = new LocationModel();

        model.setProvince(panel.getProvinceTxt().getText());
        model.setName(panel.getNameTxt().getText());
        model.setAddress_1(panel.getAddress1Txt().getText());
        model.setAddress_2(panel.getAddress2Txt().getText());
        model.setCity(panel.getCityTxt().getText());
        model.setDistrict(panel.getDistrictTxt().getText());
        model.setSub_district(panel.getSubDistrictTxt().getText());
        model.setZip_code(panel.getZipCodeTxt().getText());
        String res = this.validate(model);
        if (res.equals("Success")) {
            implementLocation.input(model);
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        } else {
            JOptionPane.showMessageDialog(null, res);
        }

    }

    public void selectedRow() {
        int row = panel.getTabelLocation().getSelectedRow();

        if (row != -1) {
            int id = Integer.valueOf(list.get(row).getLocation_id());
            int col = panel.getTabelLocation().getSelectedColumn();

            if (col == 9) {
                this.detail(list.get(row));
            }

            if (col == 10) {
                this.delete(id);
            }

            System.out.println("Table Selected Row :" + row + " Col :" + col + " Id :" + id);

        }
    }

    public void detail(LocationModel list) {
        panel.getNameTxt().setText(list.getName());
        panel.getDistrictTxt().setText(list.getDistrict());
        panel.getProvinceTxt().setText(list.getProvince());
        panel.getCityTxt().setText(list.getCity());
        panel.getSubDistrictTxt().setText(list.getSub_district());
        panel.getZipCodeTxt().setText(list.getZip_code());
        panel.getAddress1Txt().setText(list.getAddress_1());
        panel.getAddress2Txt().setText(list.getAddress_2());
        panel.getLocationIdTxt().setText(String.valueOf(list.getLocation_id()));
        panel.moveToForm();
        panel.getButtonEdit().setVisible(true);
        panel.getButtonSave().setVisible(false);
        panel.getHeadLabel().setText("Master Data > Location > Update");
        
    }

    public void delete(Integer id) {
        int res = JOptionPane.showConfirmDialog(null, "Yakin pengen di hapus ?", "Warning", JOptionPane.YES_NO_OPTION);

        if (res == JOptionPane.YES_OPTION) {
            implementLocation.delete(id);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            this.isiTable();
        }
    }

    public void edit() {
        LocationModel model = new LocationModel();

        model.setProvince(panel.getProvinceTxt().getText());
        model.setName(panel.getNameTxt().getText());
        model.setAddress_1(panel.getAddress1Txt().getText());
        model.setAddress_2(panel.getAddress2Txt().getText());
        model.setCity(panel.getCityTxt().getText());
        model.setDistrict(panel.getDistrictTxt().getText());
        model.setSub_district(panel.getSubDistrictTxt().getText());
        model.setZip_code(panel.getZipCodeTxt().getText());
        model.setLocation_id(Integer.valueOf(panel.getLocationIdTxt().getText()));

        String res = this.validate(model);
        if (res.equals("Success")) {
            implementLocation.update(model);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            this.reset();
            this.isiTable();
            panel.moveToTable();
        } else {
            JOptionPane.showMessageDialog(null, res);
        }
    }

    public void getData() {
        if (panel.getSearchTxt().getText().trim().isEmpty()) {
            this.isiTable();
            return;
        }
        String nama = panel.getSearchTxt().getText();

        list = implementLocation.getLocation(nama);
        panel.getTabelLocation().setModel(new TableLocation(list));

        ButtonColumn buttonColumn1 = new ButtonColumn(panel.getTabelLocation(), null, 10);
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTabelLocation(), null, 9);
        buttonColumn1.setMnemonic(KeyEvent.VK_D);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }

    public String validate(LocationModel model) {
        String res = "Success";
        if (this.isNullOrEmpty(model.getName())) {
            return this.validateMessage(1, "Nama");
        }

        if (this.isNullOrEmpty(model.getProvince())) {
            return this.validateMessage(1, "Province");
        }

        if (this.isNullOrEmpty(model.getCity())) {
            return this.validateMessage(1, "City");
        }

        if (this.isNullOrEmpty(model.getDistrict())) {
            return this.validateMessage(1, "District");
        }

        if (this.isNullOrEmpty(model.getSub_district())) {
            return this.validateMessage(1, "Sub District");
        }

        if (this.isNullOrEmpty(model.getZip_code())) {
            return this.validateMessage(1, "Zip Code");
        }

        if (this.isNullOrEmpty(model.getAddress_1())) {
            return this.validateMessage(1, "Address 1");
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
