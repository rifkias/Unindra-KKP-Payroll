/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.AbsensiModel;

/**
 *
 * @author lincbp
 */
public class TableAbsensi extends AbstractTableModel{
    List<AbsensiModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");

    public TableAbsensi(List<AbsensiModel> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {
                 
             
            case 0 : return list.get(rowIndex).getAbsensi_id();
            case 1 : return list.get(rowIndex).getEmploye().getEmploye_name();
            case 2 : return list.get(rowIndex).getAbsensi_date();
//            case 3 : return sdf.format(list.get(rowIndex).getIn());
//            case 4 : return sdf.format(list.get(rowIndex).getOut());
            case 3 : return getDate(list.get(rowIndex).getIn());
            case 4 : return getDate(list.get(rowIndex).getOut());
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA KARYAWAN";
            case 2 : return "TANGGAL ABSEN";
            case 3 : return "CHECK IN";
            case 4 : return "CHECK OUT";
                default:return null;
        }
    }
    
    private String getDate(Date dateString){
        String res = "";
        if(dateString != null){
            res = sdf.format(dateString);
        }
        
        return res;
    }
}
