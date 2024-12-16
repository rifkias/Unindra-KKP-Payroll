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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class reportAbsensi extends javax.swing.JPanel {
    
   // private Connection connection;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * Creates new form reportAbsensi
     */
    public reportAbsensi(LoginModel loginModel) {
        initComponents();
    //    connectToDatabase();
        initTable();
        showData();
    }

   /* private void connectToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://66.94.122.11/rifkialf_payroll_java", "rifkialf_payroll_java", "JMqx28EX");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/payroll_java", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }*/
    
    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID Karyawan");
        tableModel.addColumn("Nama Karyawan");
        tableModel.addColumn("Tanggal");
        tableModel.addColumn("Check IN");
        tableModel.addColumn("Check OUT");
        
        jTableReport.setModel(tableModel);
    }
    
    private void showData() {
        this.initTable();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startDateTemp = null;
            Date endDateTemp = null;
            if(!(jDateTglStart.getDate() == null)){
                startDateTemp = jDateTglStart.getDate();
            }
            if(!(jDateTglEnd.getDate() == null)){
                endDateTemp = jDateTglEnd.getDate();
            }

        try {
             String sql = "SELECT e.nik,\n" +
                            "e.employe_name,\n" +
                            "a.absensi_date,\n" +
                            "a.check_in,\n" +
                            "a.check_out\n" +
            "FROM employe e\n" +
            "INNER JOIN absensi a ON e.employe_id = a.employe_id\n";
             
             if(startDateTemp != null && endDateTemp != null){
                String startDate = dateFormat.format(startDateTemp);
                String endDate = dateFormat.format(endDateTemp);
                 sql += "WHERE a.absensi_date BETWEEN '"+ startDate +"' AND '"+endDate+"'";
             }
             System.out.println(sql);
            PreparedStatement statement = DbConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String idKaryawan = resultSet.getString("e.nik");
                String namaKaryawan = resultSet.getString("e.employe_name");
                String tanggalPeriode = resultSet.getString("a.absensi_date");
                String checkIn = resultSet.getString("a.check_in");
                String checkOut = resultSet.getString("a.check_out");

                Object[] data = new Object[]{idKaryawan, namaKaryawan, tanggalPeriode, checkIn, checkOut};
                tableModel.addRow(data);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      //  jTableReport.setModel(model);
    }
    
    private void clearData() {
        tableModel.setRowCount(0);
        jDateTglStart.setDate(null);
        jDateTglEnd.setDate(null);
    }
    
    private void printReport() {
        try {
            HashMap<String, Object> parameters = new HashMap<>();
            Date startDate = null;
            Date endDate = null;
            if(!(jDateTglStart.getDate() == null)){
                startDate = jDateTglStart.getDate();
            }
            if(!(jDateTglEnd.getDate() == null)){
                endDate = jDateTglEnd.getDate();
            }
            String whereCondition = "";
            
            if(startDate != null && endDate != null){
                whereCondition += "WHERE a.absensi_date BETWEEN '"+sdf.format(startDate)+"' AND '"+sdf.format(endDate)+"'";
            }
            parameters.put("searchCondition", whereCondition);
//            parameters.put("TglStart", startDate);
//            parameters.put("TglEnd", endDate);
            
            File file = new File("src/Report/laporanAbsensi.jasper");
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

        cardLayout = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateTglStart = new com.toedter.calendar.JDateChooser();
        jDateTglEnd = new com.toedter.calendar.JDateChooser();
        btnShow = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReport = new javax.swing.JTable();

        setLayout(new java.awt.CardLayout());

        cardLayout.setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/schedule.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Report > Absensi");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tanggal Awal");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Tanggal Akhir");

        jDateTglStart.setDateFormatString("yyyy-MM-dd");

        jDateTglEnd.setDateFormatString("yyyy-MM-dd");

        btnShow.setText("Show");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
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

        jTableReport.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableReportMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableReport);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jDateTglStart, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDateTglEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnShow)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPrint)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancel)))
                                .addGap(0, 78, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateTglStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateTglEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnShow)
                        .addComponent(btnCancel)
                        .addComponent(btnPrint)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );

        cardLayout.add(jPanel1, "card4");

        add(cardLayout, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        showData();
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
      clearData();
      showData();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void jTableReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableReportMouseClicked

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
            showData();
            printReport();
    }//GEN-LAST:event_btnPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnShow;
    private javax.swing.JPanel cardLayout;
    private com.toedter.calendar.JDateChooser jDateTglEnd;
    private com.toedter.calendar.JDateChooser jDateTglStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReport;
    // End of variables declaration//GEN-END:variables
}
