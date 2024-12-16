/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Table;

import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import program_paytroll_karyawan.Model.ReimbursmentDetailModel;

/**
 *
 * @author lincbp
 */
public class TableDetailReimbursement extends AbstractTableModel{
    List<ReimbursmentDetailModel> list ;
    private ImageIcon iconEdit = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/pencil-custom.png"));
    private ImageIcon iconDelete = new ImageIcon(getClass().getResource("/program_paytroll_karyawan/Assets/Icons/delete-custom.png"));

    public TableDetailReimbursement(List<ReimbursmentDetailModel> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
      public Class getColumnClass(int col) {
        if (col == 1)       //second column accepts only Integer values
            return Double.class;
        else return String.class;  //other columns accept String values
    }
      
    @Override
    public boolean isCellEditable(int row, int col){
        if(col == 2){
            return false;
        }else{
            return true;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 : return list.get(rowIndex).getDescription();
            case 1 : return list.get(rowIndex).getCost();
            case 2 : return this.iconDelete;
                default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0 : return "DESKRIPSI";
            case 1 : return "BIAYA";
            case 2 : return "DELETE";
                default:return null;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int colIndex) {
      if (rowIndex < 0 || rowIndex >= getRowCount()) {
         return;
      }
      if (colIndex < 0 || colIndex >= getColumnCount()) {
         return;
      }
      ReimbursmentDetailModel model = list.get(rowIndex);
      
      switch(colIndex){
          case 0:
              model.setDescription(aValue.toString());
              break;
          case 1:
              model.setCost(Double.valueOf(aValue.toString()));
              
      }
      fireTableRowsUpdated(rowIndex,rowIndex);
    }
    
}
