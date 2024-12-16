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
import program_paytroll_karyawan.Model.InsuranceEmployeModel;
import program_paytroll_karyawan.Model.InsuranceModel;
import program_paytroll_karyawan.Model.PajakModel;

/**
 *
 * @author lincbp
 */
public class TableInsuranceEmploye extends AbstractTableModel{
    List<InsuranceEmployeModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    
    public TableInsuranceEmploye(List<InsuranceEmployeModel> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getEmploye_insurance_id();
            case 1 : return list.get(rowIndex).getEmploye().getEmploye_name();
            case 2 : return list.get(rowIndex).getEmploye().getNik();
            case 3 : return list.get(rowIndex).getEmploye().getRole();
            case 4 : return list.get(rowIndex).getEmploye().getDivision().getName();
            case 5 : return list.get(rowIndex).getEmploye().getDepartement().getName();
            case 6 : return list.get(rowIndex).getEmploye().getLocation().getName();
            case 7 : return list.get(rowIndex).getInsurance().getName();
            case 8 : return list.get(rowIndex).getInsurance().getAsuransi_class();
            case 9 : return list.get(rowIndex).getInsurance().getPremi();
            case 10 : return this.iconEdit;
            case 11 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA KARYAWAN";
            case 2 : return "NIK";
            case 3 : return "ROLE";
            case 4 : return "DIVISI";
            case 5 : return "DEPARTEMENT";
            case 6 : return "LOKASI";
            case 7 : return "NAMA ASURANSI";
            case 8 : return "KELAS ASURANSI";
            case 9 : return "PREMI";
            case 10 : return "EDIT";
            case 11 : return "DELETE";
                default:return null;
        }
    }
}
