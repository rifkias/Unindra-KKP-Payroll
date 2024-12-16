/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.LocationModel;

/**
 *
 * @author lincbp
 */
public class TableLocation extends AbstractTableModel{

    List<LocationModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    public TableLocation(List<LocationModel> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 11;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {
            case 0 : return list.get(rowIndex).getLocation_id();
            case 1 : return list.get(rowIndex).getName();
            case 2 : return list.get(rowIndex).getProvince();
            case 3 : return list.get(rowIndex).getCity();
            case 4 : return list.get(rowIndex).getDistrict();
            case 5 : return list.get(rowIndex).getSub_district();
            case 6 : return list.get(rowIndex).getAddress_1();
            case 7 : return list.get(rowIndex).getAddress_2();
            case 8 : return list.get(rowIndex).getZip_code();
            case 9 : return this.iconEdit;
            case 10 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "NAMA";
            case 2 : return "PROVINSI";
            case 3 : return "KOTA";
            case 4 : return "KECAMATAN";
            case 5 : return "DESA";
            case 6 : return "ALAMAT 1";
            case 7 : return "ALAMAT 2";
            case 8 : return "KODE POS";
            case 9 : return "EDIT";
            case 10 : return "DELETE";
                default:return null;
        }
    }
    
}
