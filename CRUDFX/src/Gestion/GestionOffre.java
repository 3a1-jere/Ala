/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestion;

import Connect.dataSource;
import Entity.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author Ala
 */
public class GestionOffre {
    Connection c = dataSource.getInstance().getConnection();
    
    
    
     public void ajouterOffre(Offre F){
        try {
            
             Statement st = c.createStatement();
             
       String req = "INSERT INTO offres (id, categorie, cible, taux, date_debut, date_fin) VALUES('"+F.getId()+"', '"+F.getCategorie()+"', '"+F.getCible()+"', "+F.getTaux()+", '"+F.getDate_debut()+"', '"+F.getDate_fin()+"')";

       
        st.executeUpdate(req);
            
        } catch (SQLException e) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE,null,e);
        }
       
    }
    
    public void afficherOffres(){
        try {
            Statement st = c.createStatement();
            
            String req1 = "Select * from offres";
            
            ResultSet rs = st.executeQuery(req1);
            
            while(rs.next()){
                System.out.println("Offre: "+rs.getString(1)+"Categorie :"+rs.getString(2)+"Cible : "+rs.getString(3)+"Taux : "+rs.getInt(4)+"Date debut : "+rs.getString(5)+"Date fin : "+rs.getString(6)/*+"Date ajout : "+rs.getString(7)*/);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
            public ObservableList<Offre> afficherOffresList(){
                ObservableList<Offre> list = FXCollections.observableArrayList();
        try {
            Statement st = c.createStatement();
            
            String req1 = "Select id,categorie,cible,taux,date_debut,date_fin from offres";
            
            ResultSet rs = st.executeQuery(req1);
            
            while(rs.next()){
           
            Offre o =new Offre();
            o.setId(rs.getString(1));
            o.setCategorie(rs.getString(2));
            o.setCible(rs.getString(3));
            o.setTaux(rs.getInt(4));
            o.setDate_debut(rs.getDate(5));
            o.setDate_fin(rs.getDate(6));
            list.add(o);
            
            
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void modifierOffre(Offre of, String code){
          String cible = of.getCible();
        String categorie = of.getCategorie();
        int taux = of.getTaux();
        Date dd = of.getDate_debut();
        Date df = of.getDate_fin();
        
        
        
        
        try {
            PreparedStatement pt = c.prepareStatement("Update Offres SET cible = ?, categorie = ?, taux= ?,date_debut = ?,date_fin = ? where id = ?");
             
           
            pt.setString(1, cible);
            pt.setString(2,categorie);
            pt.setInt(3,taux);
             pt.setString(4,code);
             pt.setDate(5, dd);
             pt.setDate(6, df);

          
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
     public void supprimerOffre(String id){
        try {
            PreparedStatement pt = c.prepareStatement("DELETE FROM Offres WHERE id = ?");
             
            pt.setString(1, id);
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
