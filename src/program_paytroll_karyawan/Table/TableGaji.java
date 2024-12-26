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
public class TableGaji extends AbstractTableModel{
List<GajiModel> list ;
    private final ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private final ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    private final ImageIcon iconDetail = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/Eye_1.png"));
    
    public TableGaji(List<GajiModel> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getGaji_id();
            case 1 : return list.get(rowIndex).getKaryawan().getEmploye_name();
            case 2 : return list.get(rowIndex).getPeriode().getName();
            case 3 : return list.get(rowIndex).getTotal_absen();
            case 4 : return list.get(rowIndex).getTotal_lembur();
            
            case 5 : 
                 Double money = list.get(rowIndex).getGajiBersih();
        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
                return ("Rp."+formatter.format(money));
//                return (list.get(rowIndex).getTotal_pendapatan() - list.get(rowIndex).getTotal_pengurangan());
            case 6 : return this.iconDetail;
                default:return null;
        }
    }
     @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA KARYAWAN";
            case 2 : return "NAMA PERIODE";
            case 3 : return "TOTAL ABSEN";
            case 4 : return "TOTAL LEMBUR";
            case 5 : return "TOTAL PENDAPATAN";
            case 6 : return "DETAIL";
                default:return null;
        }
    }
}
