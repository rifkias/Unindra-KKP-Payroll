/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Dao.AbsensiDAO;
import program_paytroll_karyawan.Model.DepartementModel;
import program_paytroll_karyawan.View.lemburForm;
import program_paytroll_karyawan.Dao.ImplementLembur;
import program_paytroll_karyawan.Dao.KaryawanDAO;
import program_paytroll_karyawan.Dao.LemburDAO;
import program_paytroll_karyawan.Model.AbsensiModel;
import program_paytroll_karyawan.Model.ComboBoxModel;
import program_paytroll_karyawan.Model.CustomLemburModel;
import program_paytroll_karyawan.Model.KaryawanModel;
import program_paytroll_karyawan.Model.LemburModel;
import program_paytroll_karyawan.Table.ButtonColumn;
import program_paytroll_karyawan.Table.TableKaryawan;
import program_paytroll_karyawan.Table.TableLembur;
import program_paytroll_karyawan.Table.TableLemburCustom;

/**
 *
 * @author lincbp
 */
public class LemburController {
    private final lemburForm panel;
    private List<LemburModel> list;
    private List<CustomLemburModel> listCustom;
    private final ImplementLembur dao;
    private List<KaryawanModel> listKaryawan;
    private List<AbsensiModel> listAbsensi = null;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private CustomLemburModel currentLembur;
    
    private AbsensiModel selectedAbsensi = null;
    
    public LemburController(lemburForm panel) {
        this.panel = panel;
        this.dao = new LemburDAO();
    }
    
    public void initEmploye(){
        KaryawanDAO karyawanController = new KaryawanDAO();
        DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
        comboModel.removeAllElements();
        listKaryawan = karyawanController.getAllData();
        comboModel.addElement(new ComboBoxModel("", ""));
        for(KaryawanModel item : listKaryawan){
            comboModel.addElement(new ComboBoxModel(item.getEmploye_name(),String.valueOf(item.getEmploye_id())));
        }
        panel.getEmployeListCombo().setModel(comboModel);
        panel.getEmployeCombo().setModel(comboModel);
    }
    public void initLembur(){
//        list = dao.getData();
//        this.applyTable(list);
        if(panel.getLoginUser().getRole().equals("admin")){
            listCustom = dao.getCustomLembur();
        }else{
            listCustom = dao.getCustomLemburSearch(panel.getLoginUser().getEmploye_id(),null,null);
        }
        this.applyTableCustom(listCustom);
    }
    public void employeChanged(int id){
        if(id == 0){
            Object selectedLocation = panel.getEmployeCombo().getSelectedItem();
            String stringSelected = ((ComboBoxModel)selectedLocation).getValue();
            Date selectedDateMin = null;
            Date selectedDateMax = null;
            if(!stringSelected.equals("") && stringSelected != null){
                panel.getLemburDate().setDate(null);
                int employeId = Integer.valueOf(stringSelected);
                AbsensiDAO absensiDAO = new AbsensiDAO();
                listAbsensi = absensiDAO.getAbsensiSearch("", "", employeId);
                for(AbsensiModel model:listAbsensi){
                    System.out.println(model.getAbsensi_date());
                    if(selectedDateMin == null || selectedDateMax == null){
                        selectedDateMin = model.getAbsensi_date();
                        selectedDateMax = model.getAbsensi_date();
                    }else{
                        if(selectedDateMin.after(model.getAbsensi_date())){
                            selectedDateMin = model.getAbsensi_date();
                        }

                        if(selectedDateMax.before(model.getAbsensi_date())){
                            selectedDateMax = model.getAbsensi_date();
                        }

                    }
                }
                if(selectedDateMin == null && selectedDateMax == null){
                    panel.getLemburDate().setMinSelectableDate(new Date());
                    panel.getLemburDate().setMaxSelectableDate(new Date(System.currentTimeMillis()-24*60*60*1000));
                }else{
                    panel.getLemburDate().setMinSelectableDate(selectedDateMin);
                    panel.getLemburDate().setMaxSelectableDate(selectedDateMax);
                }

            }else{
                listAbsensi = null;
            }
        }else{
            panel.getLemburDate().setDate(null);
            int employeId = id;
            Date selectedDateMin = null;
            Date selectedDateMax = null;
            AbsensiDAO absensiDAO = new AbsensiDAO();
            listAbsensi = absensiDAO.getAbsensiSearch("", "", employeId);
            for(AbsensiModel model:listAbsensi){
                System.out.println(model.getAbsensi_date());
                if(selectedDateMin == null || selectedDateMax == null){
                    selectedDateMin = model.getAbsensi_date();
                    selectedDateMax = model.getAbsensi_date();
                }else{
                    if(selectedDateMin.after(model.getAbsensi_date())){
                        selectedDateMin = model.getAbsensi_date();
                    }

                    if(selectedDateMax.before(model.getAbsensi_date())){
                        selectedDateMax = model.getAbsensi_date();
                    }

                }
            }
            if(selectedDateMin == null && selectedDateMax == null){
                panel.getLemburDate().setMinSelectableDate(new Date());
                panel.getLemburDate().setMaxSelectableDate(new Date(System.currentTimeMillis()-24*60*60*1000));
            }else{
                panel.getLemburDate().setMinSelectableDate(selectedDateMin);
                panel.getLemburDate().setMaxSelectableDate(selectedDateMax);
            }
        }
        
    }
    
    public void lemburChanged(){
        AbsensiModel checkDate = getCurrentAbsen(panel.getLemburDate().getDate());
        if(checkDate != null){
            selectedAbsensi = checkDate;
            LocalDateTime startLembur = Instant.ofEpochMilli(checkDate.getIn().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime endLembur = Instant.ofEpochMilli(checkDate.getOut().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
            panel.getStartLembur().setDateTimeStrict(startLembur);
            panel.getEndLembur().setDateTimeStrict(endLembur);
            System.out.println(checkDate.getIn());
            System.out.println(checkDate.getOut());
        }else{
            JOptionPane.showMessageDialog(null,"Data Tidak Ditemukan");
            panel.getLemburDate().setDate(null);
        }
        
    }
    
    public AbsensiModel getCurrentAbsen(Date date){
        String formatDate = sdf.format(date);
        if(listAbsensi != null){
            for(AbsensiModel model:listAbsensi){
               System.out.println("MODEL DATE : "+model.getAbsensi_date()+"  Date Get :"+date);
               if(model.getAbsensi_date().toString().equals(formatDate)){
                   return model;
               }
            }   
        }
        
        return null;
    }
    
    public void applyTable(List<LemburModel> list){
        panel.getDetailTable().setModel(new TableLembur(list));
        
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getDetailTable(), null, 8);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public void applyTableCustom(List<CustomLemburModel> list){
        panel.getTable().setModel(new TableLemburCustom(list));
        
        ButtonColumn buttonColumn2 = new ButtonColumn(panel.getTable(), null, 5);
        buttonColumn2.setMnemonic(KeyEvent.VK_E);
    }
    
    public void insert(){
        int absensiId = 0;
        int requestFrom = 0;
        
        if(selectedAbsensi != null){
            absensiId = selectedAbsensi.getAbsensi_id();
        }
        
        if(panel.getLoginUser() != null){
            requestFrom = panel.getLoginUser().getEmploye_id();
        }
        System.out.println(panel.getEndLembur().getDateTimeStrict());
        Date startLembur = null;
        Date endLembur = null;
        if(panel.getStartLembur().getDateTimeStrict() != null){
            startLembur = Date.from(panel.getStartLembur().getDateTimeStrict().atZone(ZoneId.systemDefault()).toInstant());
        }
        
        if(panel.getEndLembur().getDateTimeStrict() != null){
            endLembur = Date.from(panel.getEndLembur().getDateTimeStrict().atZone(ZoneId.systemDefault()).toInstant());
        }
        
        LemburModel model = new LemburModel();
        model.setAbsensi_id(absensiId);
        model.setRequest_from(requestFrom);
        model.setCreated_by(panel.getLoginUser().getEmploye_name());
        model.setStartDate(startLembur);
        model.setEndDate(endLembur);
        
        String res = this.validate(model);
        if(res.equals("Success")){
            dao.insert(model);
            JOptionPane.showMessageDialog(null,"Data Berhasil Ditambah");
            this.reset();
            this.initLembur();
            panel.moveToTable();
        }else{
            JOptionPane.showMessageDialog(null,res);
        }
        System.out.println(res);
    }
    
    public void reset(){
        panel.getEmployeCombo().setSelectedIndex(0);
        panel.getLemburDate().setDate(null);
        panel.getStartLembur().setDateTimePermissive(null);
        panel.getEndLembur().setDateTimePermissive(null);
        panel.getEmployeListCombo().setSelectedIndex(0);
        panel.getFilterStart().setDate(null);
        panel.getFilterEnd().setDate(null);
    }
    
    public String validate(LemburModel model){
        String res = "Success";
        String validateText = "";
        String infoString = "";
        if(model.getAbsensi_id() == 0){
            validateText += validateMessage(0, "Tanggal Lembur Tidak Valid");
        }
        
        if(model.getStartDate() == null){
            validateText += validateMessage(1, "Mulai Lembur");
        }else{
            if(model.getStartDate().before(selectedAbsensi.getIn())){
                if(infoString.equals("")){
                    infoString += "Check In : "+ selectedAbsensi.getIn()+" \nCheckOut : "+selectedAbsensi.getOut();
                }
                validateText += validateMessage(0, "Mulai Lembur tidak boleh kurang dari waktu check in");
            }
            
            if(model.getStartDate().after(selectedAbsensi.getOut())){
                if(infoString.equals("")){
                    infoString += "Check In : "+ selectedAbsensi.getIn()+" \nCheckOut : "+selectedAbsensi.getOut();
                }
                validateText += validateMessage(0, "Mulai Lembur tidak boleh lebih dari waktu check out");
            }
        }
        if(model.getEndDate() == null){
            validateText += validateMessage(1, "Akhir Lembur");
        }else{
            if(model.getEndDate().before(selectedAbsensi.getIn())){
                if(infoString.equals("")){
                    infoString += "Check In : "+ selectedAbsensi.getIn()+" \nCheckOut : "+selectedAbsensi.getOut();
                }
                validateText += validateMessage(0, "Akhir Lembur tidak boleh kurang dari waktu check in");
            }
            
            if(model.getEndDate().after(selectedAbsensi.getOut())){
                if(infoString.equals("")){
                    infoString += "Check In : "+ selectedAbsensi.getIn()+" \nCheckOut : "+selectedAbsensi.getOut();
                }
                validateText += validateMessage(0, "Akhir Lembur tidak boleh lebih dari waktu check out");
            }
        }
        if(!validateText.equals("")){
            if(!infoString.equals("")){
                validateText = infoString+" \n\n"+validateText;
            }
            return validateText;
        }else{
            return res;
        }
    }
    
    public String validateMessage(int seq,String text){
        if(seq == 1){
            return "Field "+text+" Wajib Diisi \n";
        }else{
            return text+" \n";
        }
    }
    
    public void selectedRow(){
        int row = panel.getTable().getSelectedRow();
        System.out.println(row);
        if (row != -1){
            int col = panel.getTable().getSelectedColumn();
            
            if(col == 5){
                this.detail(listCustom.get(row));
                currentLembur = listCustom.get(row);

            }            
        }   
    }
    public void selectedRowDetail(){
        int row = panel.getDetailTable().getSelectedRow();
        if (row != -1){
            int id = Integer.valueOf(list.get(row).getLembur_id());
            int col = panel.getDetailTable().getSelectedColumn();
            
            if(col == 8){
                this.delete(id);
            } 
            
            System.out.println("Table Selected Row :"+row+" Col :"+col+" Id :"+id);
        } 
    }
    
 
    
    public void delete(int id){
        int res = JOptionPane.showConfirmDialog(null, "Yakin pengen di hapus ?","Warning",JOptionPane.YES_NO_OPTION);
        
        if(res == JOptionPane.YES_OPTION){            
            dao.delete(id);
            JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
            this.initLembur();
            this.detail(currentLembur);
        }
    }
    
    public void search(){
         int employe_id = 0;
         if(panel.getLoginUser().getRole().equals("admin")){
             if(panel.getEmployeListCombo().getSelectedIndex() > 0){
                Object selectedEmploye = panel.getEmployeListCombo().getSelectedItem();
                employe_id = Integer.valueOf(((ComboBoxModel)selectedEmploye).getValue());
            }
         }else{
             employe_id = panel.getLoginUser().getEmploye_id();
         }
         
        String fromDate = "";
        String toDate = "";
        if(panel.getFilterStart().getDate() != null){
            fromDate = sdf.format(panel.getFilterStart().getDate());
        }
        
        if(panel.getFilterEnd().getDate() != null){
            toDate = sdf.format(panel.getFilterEnd().getDate());
        }
        
        listCustom = dao.getCustomLemburSearch(employe_id,fromDate, toDate);
        this.applyTableCustom(listCustom);
    }
   
    public void detail(CustomLemburModel model){
        
        String fromDate = "";
        String toDate = "";
        if(panel.getFilterStart().getDate() != null){
            fromDate = sdf.format(panel.getFilterStart().getDate());
        }
        
        if(panel.getFilterEnd().getDate() != null){
            toDate = sdf.format(panel.getFilterEnd().getDate());
        }
        
        list = dao.getDataByMonth(model.getNameMonth(),model.getEmploye_id(),fromDate,toDate);
        this.applyTable(list);
        panel.moveToDetail();
    }
}
