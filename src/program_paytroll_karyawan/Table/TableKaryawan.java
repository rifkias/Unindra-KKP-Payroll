/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.KaryawanModel;

/**
 *
 * @author lincbp
 */
public class TableKaryawan extends AbstractTableModel{

    List<KaryawanModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));

    public TableKaryawan(List<KaryawanModel> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 15;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getEmploye_id();
            case 1 : return list.get(rowIndex).getEmploye_name();
            case 2 : return list.get(rowIndex).getDate_of_birth();
            case 3 : return list.get(rowIndex).getNik();
            case 4 : return list.get(rowIndex).getUsername();
            case 5 : return list.get(rowIndex).getLocation().getName();
            case 6 : return list.get(rowIndex).getDepartement().getName();
            case 7 : return list.get(rowIndex).getDivision().getName();
            case 8 : return list.get(rowIndex).getRole();
            case 9 : return list.get(rowIndex).getSalaryString();
            case 10 : return list.get(rowIndex).toStringIs_Active();
            case 11 : return list.get(rowIndex).getCreated_at();
            case 12 : return list.get(rowIndex).getCreated_by();
            case 13 : return this.iconEdit;
            case 14 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA";
            case 2 : return "TANGGAL LAHIR";
            case 3 : return "NIK";
            case 4 : return "USERNAME";
            case 5 : return "LOKASI";
            case 6 : return "DEPARTEMENT";
            case 7 : return "DIVISI";
            case 8 : return "ROLE";
            case 9 : return "GAJI";
            case 10 : return "ACTIVE";
            case 11 : return "CREATED AT";
            case 12 : return "CREATED BY";
            case 13 : return "EDIT";
            case 14 : return "DELETE";
                default:return null;
        }
    }
    
}
