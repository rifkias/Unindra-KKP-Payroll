 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.View;

import java.io.File;
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
public class slipGaji extends javax.swing.JPanel {

    private DefaultTableModel tableModelSlip; 
    /**
     * Creates new form slipGaji
     */
    public slipGaji(LoginModel loginModel) {
        initComponents();
        initTable();
        showData("");
    }

    public String getMonthName(int id){
        String month = "";
        if(id==0){
            month = "January";
        }else if(id==1){
            month = "February";
        }else if(id==2){
            month = "March";
        }else if(id==3){
            month = "April";
        }else if(id==4){
            month = "May";
        }else if(id==5){
            month = "June";
        }else if(id==6){
            month = "July";
        }else if(id==7){
            month = "August";
        }else if(id==8){
            month = "September";
        }else if(id==9){
            month = "October";
        }else if(id==10){
            month = "November";
        }else{
            month = "December";
        }
        return month;
    }
    
    private void initTable() {
        tableModelSlip = new DefaultTableModel();
        tableModelSlip.addColumn("Periode");
        tableModelSlip.addColumn("ID Karyawan");
        tableModelSlip.addColumn("Nama Karyawan");
        tableModelSlip.addColumn("Departement");
        tableModelSlip.addColumn("Divisi");
        tableModelSlip.addColumn("Kantor");
        tableModelSlip.addColumn("Gaji");
        tableModelSlip.addColumn("Cost Lembur");
        tableModelSlip.addColumn("Cost Reimbursment");
        tableModelSlip.addColumn("Total Pendapatan");
        
        jTableSlip.setModel(tableModelSlip);
    }
    
    private void showData(String searchText) {
        System.out.println(getMonthName(jMonthGaji.getMonth()));
        try {
//             String sql = "SELECT\n" +
//                            "  e.nik AS idKaryawan,\n" +
//                            "  e.employe_name AS namaKaryawan,\n" +
//                            "  d.name AS Departement,\n" +
//                            "  di.name AS Divisi,\n" +
//                            "  lo.name AS kantor,\n" +
//                            "  lo.city AS kota,\n" +
//                            "  e.salary AS gaji,\n" +
//                            "  lembur_custom.biaya_lembur AS CostLembur,\n" +
//                            "  lembur_custom.nameMonth AS Periode,\n" +
//                            "  rd.totalCost AS CostReimburst,\n" +
//                            "  (e.salary + IFNULL(lembur_custom.biaya_lembur,0) + IFNULL(rd.totalCost,0)) AS TotalPendapatan\n" +
//                            "FROM employe e\n" +
//                            "LEFT JOIN location lo ON lo.location_id = e.location_id\n" +
//                            "LEFT JOIN departement d ON d.departement_id = e.departement_id\n" +
//                            "LEFT JOIN division di ON di.division_id = e.division_id\n" +
//                            "RIGHT JOIN (\n" +
//                            "  SELECT\n" +
//                            "    MONTHNAME(a.absensi_date) AS nameMonth,\n" +
//                            "    a.employe_id AS employe_id,\n" +
//                            "    COUNT(l.lembur_id) AS total_lembur,\n" +
//                            "    (COUNT(l.lembur_id) * 200000) AS biaya_lembur\n" +
//                            "  FROM lembur l\n" +
//                            "  LEFT JOIN absensi a ON l.absensi_id = a.absensi_id\n" +
//                            "  GROUP BY nameMonth, employe_id\n" +
//                            ") lembur_custom ON e.employe_id = lembur_custom.employe_id\n" +
//                            "RIGHT JOIN (\n" +
//                            "  SELECT\n" +
//                            "    MONTHNAME(h.created_at) AS nameMonth,\n" +
//                            "    h.employe_id AS employeId,\n" +
//                            "    SUM(d.cost) AS totalCost\n" +
//                            "  FROM reimbursment h\n" +
//                            "  LEFT JOIN reimbursment_detail d ON h.reimbursment_id = d.reimbursment_id\n" +
//                            "  GROUP BY nameMonth, employeId\n" +
//                            ") rd ON rd.employeId = e.employe_id AND rd.nameMonth = lembur_custom.nameMonth \n"+
//                            "WHERE  (lembur_custom.nameMonth LIKE '%"+ getMonthName(jMonthGaji.getMonth())+"%')"
//                     ;
            String monthName = getMonthName(jMonthGaji.getMonth());
            String sql = "SELECT \n" +
                "  e.nik AS idKaryawan,\n" +
                "  e.employe_name AS namaKaryawan,\n" +
                "  d.name AS Departement,\n" +
                "  di.name AS Divisi,\n" +
                "  lo.name AS kantor,\n" +
                "  lo.city AS kota,\n" +
                "  e.salary AS gaji,\n" +
                "  CONCAT('"+monthName+"') as Periode,\n" +
                "  lembur_custom.biaya_lembur as CostLembur,\n" +
                "  rd.totalCost AS CostReimburst,\n" +
                "  (e.salary + IFNULL(lembur_custom.biaya_lembur,0) + IFNULL(rd.totalCost,0)) AS TotalPendapatan\n" +
                "FROM employe e\n" +
                "LEFT JOIN location lo ON lo.location_id = e.location_id\n" +
                "LEFT JOIN departement d ON d.departement_id = e.departement_id\n" +
                "LEFT JOIN division di ON di.division_id = e.division_id\n" +
                "LEFT JOIN (\n" +
                "  SELECT\n" +
                "    MONTHNAME(a.absensi_date) AS nameMonth,\n" +
                "    a.employe_id AS employe_id,\n" +
                "    COUNT(l.lembur_id) AS total_lembur,\n" +
                "    (COUNT(l.lembur_id) * 200000) AS biaya_lembur\n" +
                "  FROM lembur l\n" +
                "  LEFT JOIN absensi a ON l.absensi_id = a.absensi_id\n" +
                "  GROUP BY nameMonth, employe_id\n" +
                ") lembur_custom ON e.employe_id = lembur_custom.employe_id AND lembur_custom.nameMonth = '"+ monthName +"' \n" +
                "LEFT JOIN (\n" +
                "  SELECT\n" +
                "    MONTHNAME(h.created_at) AS nameMonth,\n" +
                "    h.employe_id AS employeId,\n" +
                "    SUM(d.cost) AS totalCost\n" +
                "  FROM reimbursment h\n" +
                "  INNER JOIN reimbursment_detail d ON h.reimbursment_id = d.reimbursment_id\n" +
                "  GROUP BY nameMonth, employeId\n" +
                ") rd ON rd.employeId = e.employe_id AND rd.nameMonth = '"+ monthName +"'";
            if (searchText != null && !searchText.isEmpty()) {
                sql += "WHERE (e.employe_name LIKE '%"+searchText+"%')";
            }
            System.out.println(sql);
            System.out.println(searchText);
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            tableModelSlip.setRowCount(0);
            while (resultSet.next()) {
                String idKaryawan = resultSet.getString("idKaryawan");
                String periode = resultSet.getString("Periode");
                String namaKaryawan = resultSet.getString("namaKaryawan");
                String department = resultSet.getString("Departement");
                String division = resultSet.getString("Divisi");
                String office = resultSet.getString("kantor");
                int costLembur = resultSet.getInt("CostLembur");
                int costReimburst = resultSet.getInt("CostReimburst");
                int Gaji = resultSet.getInt("gaji");
                int totalPendapatan = resultSet.getInt("TotalPendapatan");

                Object[] data = new Object[]{periode, idKaryawan, namaKaryawan, department, division, office, Gaji, costLembur, costReimburst, totalPendapatan};
                tableModelSlip.addRow(data);
                
            } 
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            // Clear the table data after each search
           // tableModelKarya.getDataVector().removeAllElements();
        }
    }
    
    private void searchKaryawan() {
        showData(null);
    }
    
    private void clearData() {
        // Clear the table data
        showData(null);
    }
    
    private void printReport() {
        try {
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("cariPeriode", getMonthName(jMonthGaji.getMonth()));
            File file = new File("src/Report/laporanSlipGaji.jasper");
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

        cardSlipGaji = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCari = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSlip = new javax.swing.JTable();
        jMonthGaji = new com.toedter.calendar.JMonthChooser();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        cardSlipGaji.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Report > Laporan Gaji Karyawan");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/schedule.png"))); // NOI18N

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        jTableSlip.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableSlip);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Periode");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jMonthGaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(445, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jMonthGaji, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        cardSlipGaji.add(jPanel1, "card2");

        add(cardSlipGaji, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // TODO add your handling code here:
        searchKaryawan();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        clearData();
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JMonthChooser jMonthGaji;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSlip;
    // End of variables declaration//GEN-END:variables
}
