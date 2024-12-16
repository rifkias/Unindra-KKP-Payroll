/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.Model.ReimbursmentModel;

/**
 *
 * @author lincbp
 */
public class TableReimbursment extends AbstractTableModel{
    List<ReimbursmentModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    private ImageIcon iconDetail = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/Eye_1.png"));
    
    public TableReimbursment(List<ReimbursmentModel> list) {
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
            case 0 : return list.get(rowIndex).getReimbursment_id();
            case 1 : return list.get(rowIndex).getReimbursment_no();
            case 2 : return list.get(rowIndex).getEmployeDetail().getEmploye_name();
            case 3 : return list.get(rowIndex).getRequestDetail().getEmploye_name();
            case 4 : return list.get(rowIndex).getTotalCost();
            case 5 : return this.iconDetail;
            case 6 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NO REIMBURSMENT";
            case 2 : return "NAMA KARYAWAN";
            case 3 : return "NAMA PENGAJU";
            case 4 : return "TOTAL";
            case 5 : return "DETAIL";
            case 6 : return "DELETE";
                default:return null;
        }
    }
    
}
