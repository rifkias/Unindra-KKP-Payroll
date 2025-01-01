 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.View;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Dao.GajiDAO;
import program_paytroll_karyawan.Dao.ImplementGaji;
import program_paytroll_karyawan.Dao.ImplementKaryawan;
import program_paytroll_karyawan.Dao.ImplementPeriode;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Dao.PeriodeDAO;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.GajiModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.LoginModel;
import program_paytroll_karyawan.Model.PeriodeModel;
import program_paytroll_karyawan.Table.TableReportGaji;

/**
 *
 * @author mymau
 */
public class detailGaji extends javax.swing.JPanel {

    private DefaultTableModel tableModelSlip; 
    private List<GajiModel> list;
    private final ImplementGaji daoGaji = new GajiDAO();
    private List<PeriodeModel> listPeriode;
    private List<KaryawanModel> listKaryawan;
    private final ImplementPeriode daoPeriode = new PeriodeDAO();
    private final ImplementKaryawan daoKaryawan = new KaryawanDAO();
    
    /**
     * Creates new form slipGaji
     */
    public detailGaji(LoginModel loginModel) {
        initComponents();
        this.initPeriodeValue();
        this.initKaryawanValue();
//        showData("");
    }

     
    public void initPeriodeValue() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listPeriode = daoPeriode.getAllData();

        comboModel.addElement(new ComboBoxModel("", ""));
        for (PeriodeModel item : listPeriode) {
            comboModel.addElement(new ComboBoxModel(item.getName() + " : " + item.getStart_date() + " - " + item.getEnd_date(), String.valueOf(item.getPeriode_id())));
        }
        
        cbPeriodeList.setModel(comboModel);

    }
    
     public void initKaryawanValue() {
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listKaryawan = daoKaryawan.getAllData();

        comboModel.addElement(new ComboBoxModel("", ""));
        for (KaryawanModel item : listKaryawan) {
            comboModel.addElement(new ComboBoxModel(item.getNik() + " : " + item.getEmploye_name(), String.valueOf(item.getEmploye_id())));
        }
        
        cbKaryawan.setModel(comboModel);

    }
    
    public int getSelectedId(JComboBox comboBox) {
        Object selectedLocation = comboBox.getSelectedItem();
        int id = 0;
        if(!((ComboBoxModel) selectedLocation).getValue().equals("")){
            id = Integer.valueOf(((ComboBoxModel) selectedLocation).getValue());
        }
        return id;
    }
    
    private void clearData() {
        // Clear the table data
//        showData(null);
    }
    
    private void printReport() {
                                  
        // TODO add your handling code here:
        int idKaryawan = this.getSelectedId(cbKaryawan);
        int idPeriode = this.getSelectedId(cbPeriodeList);
        
        if(idKaryawan > 0 && idPeriode > 0){
            List<GajiModel> checkGaji = daoGaji.getGajiSearch(idKaryawan,idPeriode);
            if(!checkGaji.isEmpty()){
                try {
                   File file = new File("src/Report/laporanDetailGaji.jasper");
                   HashMap<String, Object> parameters = new HashMap<>();
                   parameters.put("periode_id", idPeriode);
                   parameters.put("employe_id", idKaryawan);

                   JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                   JasperPrint jp = JasperFillManager.fillReport(jr, parameters, DbConnection.getConnection());
                   JasperViewer.viewReport(jp, false);
                   JasperViewer.setDefaultLookAndFeelDecorated(true);        
               } catch (Exception e) {
                   JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
               }   
            }else{
                JOptionPane.showMessageDialog(null, "Empty Data !!!");
            }
             
        }else{
            if(idKaryawan == 0){
                JOptionPane.showMessageDialog(null, "Select Karyawan For View !!!");
            }
            if(idPeriode == 0){
                JOptionPane.showMessageDialog(null, "Select Periode For View !!!");
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardSlipGaji = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbKaryawan = new javax.swing.JComboBox<>();
        cbPeriodeList = new javax.swing.JComboBox<>();
        btnCari = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        reportView = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        cardSlipGaji.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Report > Laporan Detail Gaji Karyawan");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/schedule.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Periode");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Karyawan");

        cbKaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbPeriodeList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCari.setText("Show");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnCancel.setText("Reset");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbPeriodeList, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPeriodeList, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(362, Short.MAX_VALUE))
        );

        reportView.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout reportViewLayout = new javax.swing.GroupLayout(reportView);
        reportView.setLayout(reportViewLayout);
        reportViewLayout.setHorizontalGroup(
            reportViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        reportViewLayout.setVerticalGroup(
            reportViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(reportView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reportView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(516, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(20, 20, 20))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        cardSlipGaji.add(jPanel1, "card2");

        add(cardSlipGaji, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        int idKaryawan = this.getSelectedId(cbKaryawan);
        int idPeriode = this.getSelectedId(cbPeriodeList);
        
        if(idKaryawan > 0 && idPeriode > 0){
            List<GajiModel> checkGaji = daoGaji.getGajiSearch(idKaryawan,idPeriode);
            if(!checkGaji.isEmpty()){
                try {
                    File file = new File("src/Report/laporanDetailGaji.jasper");
                    HashMap<String, Object> parameters = new HashMap<>();
                    parameters.put("periode_id", idPeriode);
                    parameters.put("employe_id", idKaryawan);

                    JasperReport jr = (JasperReport) JRLoader.loadObject(file);
                    JasperPrint print = JasperFillManager.fillReport(jr, parameters, DbConnection.getConnection());
                    //tampil panel
                    reportView.setLayout(new BorderLayout());
                    reportView.repaint();
                    reportView.add(new JRViewer(print));
                    reportView.revalidate();
                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e.getMessage());
                }
            }else{
                JOptionPane.showMessageDialog(null, "Empty Data !!!");
            }
        }else{
            if(idKaryawan == 0){
                JOptionPane.showMessageDialog(null, "Select Karyawan For View !!!");
            }
            if(idPeriode == 0){
                JOptionPane.showMessageDialog(null, "Select Periode For View !!!");
            }
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        cbPeriodeList.setSelectedIndex(0);
        cbKaryawan.setSelectedIndex(0);
        
        reportView.removeAll();
        reportView.repaint();
        reportView.revalidate();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
        printReport();
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnPrint;
    private javax.swing.JPanel cardSlipGaji;
    private javax.swing.JComboBox<String> cbKaryawan;
    private javax.swing.JComboBox<String> cbPeriodeList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel reportView;
    // End of variables declaration//GEN-END:variables
}
