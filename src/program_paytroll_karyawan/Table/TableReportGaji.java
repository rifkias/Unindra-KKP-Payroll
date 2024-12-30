/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Table;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.GajiModel;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class TableReportGaji extends AbstractTableModel{
    List<GajiModel> list ;
    public TableReportGaji(List<GajiModel> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getPeriode().getName();
            case 1 : return list.get(rowIndex).getKaryawan().getEmploye_name();
            case 2 : return list.get(rowIndex).getKaryawan().getDepartement().getName();
            case 3 : return list.get(rowIndex).getKaryawan().getDivision().getName();
            case 4 : return list.get(rowIndex).getKaryawan().getLocation().getName();
            case 5 : 
                 Double pendapatan = list.get(rowIndex).getTotal_pendapatan();
                return ("Rp."+formatter.format(pendapatan));
            case 6 : 
                 Double pengurangan = list.get(rowIndex).getTotal_pengurangan();
                return ("Rp."+formatter.format(pengurangan));
            case 7 : 
                 Double money = list.get(rowIndex).getGajiBersih();
                return ("Rp."+formatter.format(money));
                default:return null;
        }
    }
     @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "Nama Periode";
            case 1 : return "Nama Karyawan";
            case 2 : return "Departement";
            case 3 : return "Divisi";
            case 4 : return "Kantor";
            case 5 : return "Total Pendapatan";
            case 6 : return "Total Pengurangan";
            case 7 : return "Gaji Bersih";
                default:return null;
        }
    }
}
