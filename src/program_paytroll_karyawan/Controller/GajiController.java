/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Controller;

import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Dao.AbsensiDAO;
import program_paytroll_karyawan.Dao.GajiDAO;
import program_paytroll_karyawan.Dao.ImplementAbsen;
import program_paytroll_karyawan.Dao.ImplementGaji;
import program_paytroll_karyawan.Dao.ImplementInsuranceEmploye;
import program_paytroll_karyawan.Dao.ImplementKaryawan;
import program_paytroll_karyawan.Dao.ImplementLembur;
import program_paytroll_karyawan.Dao.ImplementPajak;
import program_paytroll_karyawan.Dao.ImplementPeriode;
import program_paytroll_karyawan.Dao.ImplementReimburse;
import program_paytroll_karyawan.Dao.InsuranceEmployeDAO;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Dao.LemburDAO;
import program_paytroll_karyawan.Dao.PajakDAO;
import program_paytroll_karyawan.Dao.PeriodeDAO;
import program_paytroll_karyawan.Dao.ReimbursmentDAO;
import program_paytroll_karyawan.Model.AbsensiModel;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.GajiDetailModel;
import program_paytroll_karyawan.Model.GajiModel;
import program_paytroll_karyawan.Model.InsuranceEmployeModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.LemburModel;
import program_paytroll_karyawan.Model.PajakModel;
import program_paytroll_karyawan.Model.PeriodeModel;
import program_paytroll_karyawan.Model.ReimbursmentDetailModel;
import program_paytroll_karyawan.Model.ReimbursmentModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableGaji;
import program_paytroll_karyawan.Table.TableGajiDetail;
import program_paytroll_karyawan.View.GajiForm;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class GajiController {

    private final GajiForm panel;
    private List<GajiModel> list;
    private List<GajiDetailModel> listDetail = new ArrayList<GajiDetailModel>();
    private final ImplementGaji dao;
    private List<KaryawanModel> listKaryawan;
    private List<PeriodeModel> listPeriode;
    private final ImplementPeriode daoPeriode = new PeriodeDAO();
    private final ImplementAbsen daoAbsensi = new AbsensiDAO();
    private final ImplementKaryawan daoKaryawan = new KaryawanDAO();
    private final ImplementLembur daoLembur = new LemburDAO();
    private final ImplementPajak daoPajak = new PajakDAO();
    private final ImplementInsuranceEmploye daoEmployeInsurance = new InsuranceEmployeDAO();
    private final ImplementReimburse daoReimburse = new ReimbursmentDAO();
    private GajiModel currentGaji;

    public GajiController(GajiForm panel) {
        this.panel = panel;
        this.dao = new GajiDAO();
    }

    public void resetForm() {
        panel.getPeriodeFormCombo().setSelectedIndex(0);
        panel.getDetailNamaTxt().setText("");
        panel.getDetailLokasiTxt().setText("");
        panel.getDetailDepartmentTxt().setText("");
        panel.getDetailDivisiTxt().setText("");
        panel.getGajiTxt().setText("");
    }

    public void reset() {
        panel.getPeriodeCombo().setSelectedIndex(0);
        panel.getEmployeListCombo().setSelectedIndex(0);
    }

    public void generateGaji() {
        int selectedPeriode = this.getSelectedId(panel.getPeriodeFormCombo());

        if (selectedPeriode <= 0) {

            JOptionPane.showMessageDialog(null, "Select Periode !!!");
        } else {
            System.out.println(selectedPeriode);
            panel.getBackButton().setEnabled(false);
            panel.getSaveButton().setEnabled(false);
            PeriodeModel modelPeriode = daoPeriode.getDetail(selectedPeriode);
            List<KaryawanModel> karyawanList = daoKaryawan.getActiveEmploye();
            dao.deleteGajiByPeriode(modelPeriode.getPeriode_id());
            for (KaryawanModel model : karyawanList) {
                int totalPendapatan = 0;
                int totalPengurangan = 0;

                // Add Gaji
                totalPendapatan += model.getSalary();

                //            Get Total Absen
                List<AbsensiModel> absensi = daoAbsensi.getAbsensiSearch(String.valueOf(modelPeriode.getStart_date()), String.valueOf(modelPeriode.getEnd_date()), model.getEmploye_id());
                int totalAbsen = absensi.size();
                // 50k/absen
                int payAbsen = totalAbsen * 50000;
                totalPendapatan += payAbsen;
                //  Get Total Lembur
                List<LemburModel> lembur = daoLembur.getDataSearch(model.getEmploye_id(), String.valueOf(modelPeriode.getStart_date()), String.valueOf(modelPeriode.getEnd_date()));
                int totalLembur = lembur.size();
                // 200k/lembur
                int payLembur = totalLembur * 200000;
                totalPendapatan += payLembur;

                List<GajiDetailModel> detailGajis = new ArrayList<GajiDetailModel>();
                GajiModel modelGaji = new GajiModel();
                modelGaji.setEmploye_id(model.getEmploye_id());
                modelGaji.setPeriode_id(modelPeriode.getPeriode_id());
                modelGaji.setTotal_absen(totalAbsen);
                modelGaji.setTotal_lembur(totalLembur);

                // Add Lembur
                GajiDetailModel modelDetail = new GajiDetailModel();
                
                modelDetail.setType("Gaji Pokok");
                modelDetail.setRemarks("");
                modelDetail.setTotal(model.getSalary());
                detailGajis.add(modelDetail);
                
                modelDetail = new GajiDetailModel();
                modelDetail.setType("Lembur");
                modelDetail.setRemarks("Total Lembur = " + totalLembur);
                modelDetail.setTotal(Double.valueOf(payLembur));
                detailGajis.add(modelDetail);

                // Add Absensi
                modelDetail = new GajiDetailModel();
                modelDetail.setType("Absen");
                modelDetail.setRemarks("Total Absen = " + totalAbsen);
                modelDetail.setTotal(Double.valueOf(payAbsen));
                detailGajis.add(modelDetail);

                // BPJS
                modelDetail = new GajiDetailModel();
                modelDetail.setType("BPJS");
                modelDetail.setRemarks("Pembayaran BPJS Kesehatan");
                double payBpjs = model.getSalary() * 0.01;
                totalPengurangan += payBpjs;
                modelDetail.setTotal(payBpjs * -1.0);
                detailGajis.add(modelDetail);
                
                // Add Pajak
                List<PajakModel> pajaks = daoPajak.getAllData();
                for (PajakModel pajak : pajaks) {
                    modelDetail = new GajiDetailModel();
                    modelDetail.setType("Pajak");
                    modelDetail.setRemarks(pajak.getName());
                    double total = model.getSalary() * pajak.getPercentage();
                    totalPengurangan += total;
                    modelDetail.setTotal(total * -1.0);
                    detailGajis.add(modelDetail);
                }

                List<ReimbursmentModel> reimbursments = daoReimburse.getReimbursment(String.valueOf(modelPeriode.getStart_date()), String.valueOf(modelPeriode.getEnd_date()), model.getEmploye_id());
                for (ReimbursmentModel reimbursment : reimbursments) {
                    for (ReimbursmentDetailModel detail : reimbursment.getDetail()) {
                        modelDetail = new GajiDetailModel();
                        modelDetail.setType("Reimbursment");
                        modelDetail.setRemarks("No " + reimbursment.getReimbursment_no() + " : " + detail.getDescription());
                        double total = detail.getCost();
                        totalPendapatan += total;
                        modelDetail.setTotal(total);
                        detailGajis.add(modelDetail);
                    }
                }

                // Add Asuransi
                List<InsuranceEmployeModel> asuransis = daoEmployeInsurance.getEmployeInsurance(model.getEmploye_id());
                for (InsuranceEmployeModel asuransi : asuransis) {
                    modelDetail = new GajiDetailModel();
                    modelDetail.setType("Asuransi");
                    modelDetail.setRemarks(asuransi.getInsurance().getName()+" : "+asuransi.getInsurance().getAsuransi_class());
                    double total = asuransi.getInsurance().getPremi();
                    totalPengurangan += total;
                    modelDetail.setTotal(total * -1.0);
                    detailGajis.add(modelDetail);
                }

                modelGaji.setDetail(detailGajis);
                modelGaji.setTotal_pendapatan(totalPendapatan);
                modelGaji.setTotal_pengurangan(totalPengurangan);
                dao.input(modelGaji);

                System.out.println(model.getNik());
            }

            this.resetForm();

            panel.getBackButton().setEnabled(true);
            panel.getSaveButton().setEnabled(true);
            JOptionPane.showMessageDialog(null, "Success");
            this.isiTable();
        }
    }

    public void isiTable() {
        list = dao.getAllData();
        this.applyTable(list);
    }

    public int getSelectedId(JComboBox comboBox) {
        Object selected = comboBox.getSelectedItem();
        if (((ComboBoxModel) selected).getValue().equals("")) {
            return 0;
        } else {
            int id = Integer.valueOf(((ComboBoxModel) selected).getValue());
            return id;
        }

    }

    public void applyTable(List<GajiModel> list) {
        panel.getTable().setModel(new TableGaji(list));

        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 6);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }

    public void initPeriodeValue() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listPeriode = daoPeriode.getAllData();

        comboModel.addElement(new ComboBoxModel("", ""));
        for (PeriodeModel item : listPeriode) {
            comboModel.addElement(new ComboBoxModel(item.getName() + " : " + item.getStart_date() + " - " + item.getEnd_date(), String.valueOf(item.getPeriode_id())));
        }
        panel.getPeriodeCombo().setModel(comboModel);
        panel.getPeriodeFormCombo().setModel(comboModel);

    }

    public void initEmployeValue() {
        KaryawanDAO karyawanController = new KaryawanDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listKaryawan = karyawanController.getAllData();

        comboModel.addElement(new ComboBoxModel("", ""));
        for (KaryawanModel item : listKaryawan) {
            comboModel.addElement(new ComboBoxModel(item.getEmploye_name() + " : " + item.getNik(), String.valueOf(item.getEmploye_id())));
        }
        panel.getEmployeListCombo().setModel(comboModel);

    }

    public void search() {
        int employe_id = 0;
        if (panel.getEmployeListCombo().getSelectedIndex() > 0) {
            Object selectedEmploye = panel.getEmployeListCombo().getSelectedItem();
            employe_id = Integer.valueOf(((ComboBoxModel) selectedEmploye).getValue());
        }

        int periode_id = 0;
        if (panel.getPeriodeCombo().getSelectedIndex() > 0) {
            Object selectedPeriode = panel.getPeriodeCombo().getSelectedItem();
            periode_id = Integer.valueOf(((ComboBoxModel) selectedPeriode).getValue());
        }

        list = dao.getGajiSearch(employe_id, periode_id);
        this.applyTable(list);
    }

    public void detail(GajiModel gaji) {

        this.resetForm();
        gaji.setDetail(dao.getGajiDetail(gaji.getGaji_id()));
        panel.getDetailNamaTxt().setText(gaji.getKaryawan().getEmploye_name());
        panel.getDetailLokasiTxt().setText(gaji.getKaryawan().getLocation().getName());
        panel.getDetailDepartmentTxt().setText(gaji.getKaryawan().getDepartement().getName());
        panel.getDetailDivisiTxt().setText(gaji.getKaryawan().getDivision().getName());
        Double money = gaji.getGajiBersih();
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));

        panel.getGajiBersihTxt().setText("Rp." + formatter.format(money));
        panel.getGajiTxt().setText("Rp." + formatter.format(gaji.getKaryawan().getSalary()));
        listDetail = gaji.getDetail();
        panel.getDetailTable().setModel(new TableGajiDetail(listDetail));
        panel.moveToDetail();

    }

    public void selectedRow() {
        int row = panel.getTable().getSelectedRow();
        if (row != -1) {
            int col = panel.getTable().getSelectedColumn();

            if (col == 6) {
                this.detail(list.get(row));
                currentGaji = list.get(row);
            }
        }
    }
}
