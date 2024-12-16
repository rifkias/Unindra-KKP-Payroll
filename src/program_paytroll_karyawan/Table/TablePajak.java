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
import program_paytroll_karyawan.Model.PajakModel;

/**
 *
 * @author lincbp
 */
public class TablePajak extends AbstractTableModel{
    List<PajakModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    
    public TablePajak(List<PajakModel> list) {
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
            case 0 : return list.get(rowIndex).getPajak_id();
            case 1 : return list.get(rowIndex).getName();
            case 2 : return list.get(rowIndex).getDescription();
            case 3 : return list.get(rowIndex).getPercentage();
            case 4 : return this.iconEdit;
            case 5 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA";
            case 2 : return "DESKRIPSI";
            case 3 : return "PERCENTAGE";
            case 4 : return "EDIT";
            case 5 : return "DELETE";
                default:return null;
        }
    }
}
