/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.DivisionModel;
import program_paytroll_karyawan.Model.PeriodeModel;

/**
 *
 * @author lincbp
 */
public class TablePeriode extends AbstractTableModel{
    List<PeriodeModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    
    public TablePeriode(List<PeriodeModel> list) {
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
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getPeriode_id();
            case 1 : return list.get(rowIndex).getName();
            case 2 : return list.get(rowIndex).getStart_date();
            case 3 : return list.get(rowIndex).getEnd_date();
            case 4 : return list.get(rowIndex).getCreated_at();
            case 5 : return list.get(rowIndex).getUpdated_at();
            case 6 : return this.iconEdit;
            case 7 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA PERIODE";
            case 2 : return "TANGGAL MULAI";
            case 3 : return "TANGGAL SELESAI";
            case 4 : return "CREATED AT";
            case 5 : return "UPDATED AT";
            case 6 : return "EDIT";
            case 7 : return "DELETE";
                default:return null;
        }
    }
}
