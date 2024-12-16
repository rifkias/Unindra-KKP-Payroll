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
import program_paytroll_karyawan.Model.LemburModel;

/**
 *
 * @author lincbp
 */
public class TableLembur extends AbstractTableModel{
    List<LemburModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public TableLembur(List<LemburModel> list) {
        this.list = list;
    }
    @Override
    public int getRowCount() {
         return list.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getLembur_id();
            case 1 : return list.get(rowIndex).getAbsen().getAbsensi_date();
            case 2 : return list.get(rowIndex).getAbsen().getEmploye().getEmploye_name();
            case 3 : return sdf.format(list.get(rowIndex).getStartDate());
            case 4 : return sdf.format(list.get(rowIndex).getEndDate());
            case 5 : return list.get(rowIndex).getRequest_employe().getEmploye_name();
            case 6 : return list.get(rowIndex).getCreated_at();
            case 7 : return list.get(rowIndex).getCreated_by();
            case 8 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "TANGGAL ABSEN";
            case 2 : return "NAMA KARYAWAN";
            case 3 : return "MULAI";
            case 4 : return "AKHIR";
            case 5 : return "MENGAJUKAN";
            case 6 : return "CREATED AT";
            case 7 : return "CREATED BY";
            case 8 : return "DELETE";
                default:return null;
        }
    }
    
}
