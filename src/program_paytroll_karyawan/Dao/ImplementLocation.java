/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program_paytroll_karyawan.Dao;

import java.util.List;
import program_paytroll_karyawan.Model.LocationModel;

/**
 *
 * @author lincbp
 */
public interface ImplementLocation {
    public void input(LocationModel location);
    
    public void update(LocationModel location);
    
    public void delete(int id);
    
    public List<LocationModel> getLocation(String nama);
    
    public List<LocationModel> getAllLocation();
    
    public LocationModel getLocationDetail(int id);
}
