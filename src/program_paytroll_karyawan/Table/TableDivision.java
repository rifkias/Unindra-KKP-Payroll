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

/**
 *
 * @author lincbp
 */
public class TableDivision extends AbstractTableModel{
    List<DivisionModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    
    public TableDivision(List<DivisionModel> list) {
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
            case 0 : return list.get(rowIndex).getDivision_id();
            case 1 : return list.get(rowIndex).getDepartement().getLocation().getName();
            case 2 : return list.get(rowIndex).getDepartement().getName();
            case 3 : return list.get(rowIndex).getName();
            case 4 : return list.get(rowIndex).getNotes();
            case 5 : return this.iconEdit;
            case 6 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA LOKASI";
            case 2 : return "NAMA DEPARTEMENT";
            case 3 : return "NAMA";
            case 4 : return "NOTES";
            case 5 : return "EDIT";
            case 6 : return "DELETE";
                default:return null;
        }
    }
}
