/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.CustomLemburModel;
import program_paytroll_karyawan.Model.LemburModel;

/**
 *
 * @author lincbp
 */
public class TableLemburCustom extends AbstractTableModel{
    List<CustomLemburModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    private ImageIcon iconDetail = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/Eye_1.png"));
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public TableLemburCustom(List<CustomLemburModel> list) {
        this.list = list;
    }
    @Override
    public int getRowCount() {
         return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return (rowIndex+1);
            case 1 : return list.get(rowIndex).getNameMonth();
            case 2 : return list.get(rowIndex).getEmployeDetail().getEmploye_name();
            case 3 : return list.get(rowIndex).getTotal_lembur();
            case 4 : return list.get(rowIndex).getBiayaLembur();
            case 5 : return this.iconDetail;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "NO";
            case 1 : return "NAMA BULAN";
            case 2 : return "NAMA KARYAWAN";
            case 3 : return "JUMLAH LEMBUR";
            case 4 : return "TOTAL LEMBUR";
            case 5 : return "DETAIL";
                default:return null;
        }
    }
    
}
