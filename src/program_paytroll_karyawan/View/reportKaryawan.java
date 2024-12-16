/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.View;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import program_paytroll_karyawan.Config.DbConnection;
import program_paytroll_karyawan.Model.LoginModel;

/**
 *
 * @author mymau
 */
public class reportKaryawan extends javax.swing.JPanel {

    private DefaultTableModel tableModelKarya; 
    /**
     * Creates new form reportKaryawan
     */
    public reportKaryawan(LoginModel loginModel) {
        initComponents();
        initTable();
        showData("");
    }
    
    private void initTable() {
        tableModelKarya = new DefaultTableModel();
        tableModelKarya.addColumn("ID Karyawan");
        tableModelKarya.addColumn("NIK Karyawan");
        tableModelKarya.addColumn("Nama Karyawan");
        tableModelKarya.addColumn("Gaji");
        tableModelKarya.addColumn("Departement");
        tableModelKarya.addColumn("Divisi");
        tableModelKarya.addColumn("Kantor");
        tableModelKarya.addColumn("Alamat Kantor");
        
        jTableKaryawan.setModel(tableModelKarya);
    }
    
    private void showData(String searchText) {
    //    String searchText = txtSearch.getText();
        try {
             String sql = "SELECT e.employe_id AS employe_id, \n" +
                            "e.nik,\n" +
                            "e.employe_name AS employe_name, \n" +
                            "e.salary AS salary, \n" +
                            "d.name AS department, \n" +
                            "di.name AS division, \n" +
                            "l.city AS city, \n" +
                            "l.name AS location \n" +
                            "FROM employe e\n" +
                            "INNER JOIN division di ON e.division_id = di.division_id \n" +
                            "INNER JOIN location l ON e.location_id = l.location_id \n" +
                            "INNER JOIN departement d ON e.departement_id = d.departement_id ";
            if (searchText != null && !searchText.isEmpty()) {
                sql += "WHERE e.nik LIKE '%"+searchText+"%' OR e.employe_name LIKE '%"+searchText+"%' OR e.salary LIKE '%"+searchText+"%' "
                        + "OR d.name LIKE '%"+searchText+"%' OR di.name LIKE '%"+searchText+"%' OR l.city LIKE '%"+searchText+"%' OR l.name LIKE '%"+searchText+"%'";
            }
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            tableModelKarya.setRowCount(0);
            while (resultSet.next()) {
                String idKaryawan = resultSet.getString("employe_id");
                String nikKaryawan = resultSet.getString("e.nik");
                String namaKaryawan = resultSet.getString("employe_name");
                String gaji = resultSet.getString("salary");
                String department = resultSet.getString("department");
                String division = resultSet.getString("division");
                String office = resultSet.getString("location");
                String addresOffice = resultSet.getString("city");

                Object[] data = new Object[]{idKaryawan, nikKaryawan, namaKaryawan, gaji, department, division, office, addresOffice};
                tableModelKarya.addRow(data);
                
            } 
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Clear the table data after each search
           // tableModelKarya.getDataVector().removeAllElements();
        }
    }
    
    public void searchKaryawan() {
        String searchText = txtSearch.getText();
        showData(searchText);
    }
    
     public void clearData() {
        // Clear the table data
        txtSearch.setText(null);
        showData(null);
    //    tableModelKarya.getDataVector().removeAllElements();
     }
     
     private void printReport() {
        try {
            HashMap<String, Object> parameters = new HashMap<>();
            String whereCondition = "";
            
            if(!txtSearch.getText().equals("")){
                String like = "'%"+txtSearch.getText()+"%'";
                whereCondition = "WHERE e.nik LIKE "+like+" OR e.employe_name LIKE "+like+" OR e.salary LIKE "+like+" OR d.name LIKE "+like+" OR di.name LIKE "+like+" OR l.city LIKE "+like+" OR l.name LIKE "+like;
            }
            parameters.put("searchCondition",whereCondition );
            
            File file = new File("src/Report/laporanKaryawan.jasper");
            JasperReport jr = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, DbConnection.getConnection());
            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

        CardLayoutKarya = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jButtonCari = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonPrint1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableKaryawan = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        CardLayoutKarya.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Report > Karyawan");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/schedule.png"))); // NOI18N

        jButtonCari.setText("Cari");
        jButtonCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCariActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        jButtonPrint1.setText("Print");
        jButtonPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrint1ActionPerformed(evt);
            }
        });

        jTableKaryawan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableKaryawan);

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Search");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        CardLayoutKarya.add(jPanel1, "card2");

        add(CardLayoutKarya, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        // TODO add your handling code here:
        clearData();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jButtonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCariActionPerformed
        // TODO add your handling code here:
        searchKaryawan();
    }//GEN-LAST:event_jButtonCariActionPerformed

    private void jButtonPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrint1ActionPerformed
        // TODO add your handling code here:
        printReport();
    }//GEN-LAST:event_jButtonPrint1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardLayoutKarya;
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonCari;
    private javax.swing.JButton jButtonPrint1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableKaryawan;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
