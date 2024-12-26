    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program_paytroll_karyawan.Table;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.GajiDetailModel;
import program_paytroll_karyawan.Model.GajiModel;

/**
 *
 * @author rifki-alfariz-shidiq
 */
public class TableGajiDetail extends AbstractTableModel{
List<GajiDetailModel> list ;
    private final ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private final ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));
    
    public TableGajiDetail(List<GajiDetailModel> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getGaji_detail_id();
            case 1 : return list.get(rowIndex).getType();
            case 2 : return list.get(rowIndex).getRemarks();
            case 3 :
                        Double money = list.get(rowIndex).getTotal();
                        NumberFormat formatter = NumberFormat.getInstance(new Locale("en_US"));
                        return ("Rp."+formatter.format(money));
//                return list.get(rowIndex).getTotal();
//            case 4 : return this.iconEdit;
//            case 5 : return this.iconDelete;
                default:return null;
        }
    }
     @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "ID";
            case 1 : return "TYPE";
            case 2 : return "REMARKS";
            case 3 : return "TOTAL";
//            case 4 : return "EDIT";
//            case 5 : return "DELETE";
                default:return null;
        }
    }
}
