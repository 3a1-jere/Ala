/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestion;

import Connect.dataSource;
import Entity.Offre;
import Entity.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ala
 */
public class GestionVoucher {
    
     Connection c = dataSource.getInstance().getConnection();
    public void ajouterVoucher(Voucher V){
        try {
            
             Statement st = c.createStatement();
             
       String req = "INSERT INTO Voucher (voucherCode, nbUse, maxUse, rate) VALUES('"+V.getVoucherCode()+"', "+V.getNbUse()+", "+V.getMaxUse()+", "+V.getRate()+")";
       
     
            // String req_check = "SELECT * FROM voucher where voucherCode = '"+V.getVoucherCode()+"'";
       
      /* String req1 = "IF EXISTS (SELECT * FROM voucher WHERE voucherCode= '"+V.getVoucherCode()+"' ) "
               + "UPDATE voucher SET (voucherCode,nbUse,maxUse,rate) WHERE voucherCode= '"+V.getVoucherCode()+"' "
               + "ELSE "
               + "INSERT INTO voucher (voucherCode, nbUse, maxUse, rate) VALUES ('"+V.getVoucherCode()+"',"+V.getNbUse()+","+V.getMaxUse()+","+V.getRate()+")";*/
    //Statement st = c.createStatement();
     //String req = "INSERT INTO Voucher (voucherCode, nbUse, maxUse, rate) VALUES('"+V.getVoucherCode()+"', "+V.getNbUse()+", "+V.getMaxUse()+", "+V.getRate()+")";
        st.executeUpdate(req);
            
        } catch (SQLException e) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE,null,e);
        }
       
    }
    
    public void afficherVouchers(){
        try {
            Statement st = c.createStatement();
            
            String req1 = "Select * from voucher";
            
            ResultSet rs = st.executeQuery(req1);
            
            while(rs.next()){
                System.out.println("Vouchers: "+rs.getString(1)+"NbUse :"+rs.getString(2)+"MaxUse : "+rs.getString(3)+"Rate : "+rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionVoucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modifierVoucher(Voucher V, String code){
        
      
        int nbUse = V.getNbUse();
        int maxUse = V.getMaxUse();
        int rate = V.getRate();
        
        
        
        
        try {
            PreparedStatement pt = c.prepareStatement("Update Voucher SET nbUse = ?, maxUse = ?, rate= ? where voucherCode = ?");
             
           
            pt.setInt(1, nbUse);
            pt.setInt(2,maxUse);
            pt.setInt(3,rate);
             pt.setString(4,code);
          
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void supprimerVoucher(String code){
        try {
            PreparedStatement pt = c.prepareStatement("DELETE FROM Voucher WHERE voucherCode = ?");
             
            pt.setString(1, code);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     
     public ObservableList<Voucher> afficherVouchersList(){
                ObservableList<Voucher> list = FXCollections.observableArrayList();
        try {
            Statement st = c.createStatement();
            
            String req1 = "Select * from voucher";
            
            ResultSet rs = st.executeQuery(req1);
            
            while(rs.next()){
           
            Voucher v = new Voucher();
            v.setVoucherCode(rs.getString(1));
            v.setNbUse(rs.getInt(2));
            v.setMaxUse(rs.getInt(3));
            v.setRate(rs.getInt(4));
             list.add(v);
            
            
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(GestionVoucher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
