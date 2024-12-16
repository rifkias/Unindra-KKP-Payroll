/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import program_paytroll_karyawan.Dao.AbsensiDAO;
import program_paytroll_karyawan.Dao.ImplementAbsen;
import program_paytroll_karyawan.Model.AbsensiModel;
import program_paytroll_karyawan.View.AbsenPanel;

/**
 *
 * @author lincbp
 */
public class AbsensiPanelController {
    private final AbsenPanel panel;
    private List<AbsensiModel> list;
    private final ImplementAbsen dao;
    private AbsensiModel modelAbsen;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd H:m:s");
    
    public AbsensiPanelController(AbsenPanel panel) {
        this.panel = panel;
        dao = new AbsensiDAO();
    }
    
    
    public void initAbsensi(){
        int id = panel.getCurrentLogin().getEmploye_id();
        modelAbsen = dao.getCurrentAbsen(id);
        
        if(modelAbsen == null){
            System.out.println("Masook");
            
            panel.getCheckInButton().setEnabled(true);
            panel.getTxtCheckIn().setText("");
            panel.getTxtCheckOut().setText("");
            panel.getCheckOutButton().setEnabled(false);
        }else{
            if(modelAbsen.getIn() != null){
                panel.getTxtCheckIn().setText(sdf2.format(modelAbsen.getIn()));
                panel.getCheckInButton().setEnabled(false);
            }else{
                panel.getCheckInButton().setEnabled(true);
                panel.getTxtCheckIn().setText("");
            }
            if(modelAbsen.getOut() != null){
                panel.getTxtCheckOut().setText(sdf2.format(modelAbsen.getOut()));
                panel.getCheckOutButton().setEnabled(true);
            }else{
                panel.getTxtCheckOut().setText("");
                panel.getCheckOutButton().setEnabled(true);
            }
        }
    }
    
    public void checkIn(){
        dao.checkIn(panel.getCurrentLogin().getEmploye_id());
        this.initAbsensi();
        JOptionPane.showMessageDialog(null,"Berhasil CheckIn");
    }
    
    public void checkOut(){
        dao.checkOut(modelAbsen.getAbsensi_id());
        this.initAbsensi();
        JOptionPane.showMessageDialog(null,"Berhasil Checkout");
    }
}
