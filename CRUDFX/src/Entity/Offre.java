/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.nio.charset.Charset;
//import java.util.Date;
import java.sql.Date;
import java.util.Random;

/**
 *
 * @author Ala
 */
public class Offre {
    private String id;
    private String categorie;
    private String cible;
    private int taux;
    private Date date_debut;
    private Date date_fin;
    //private Date date_ajout;

    public Offre(String id, String categorie, String cible, int taux, Date date_debut, Date date_fin) {
        this.id = id;
        this.categorie = categorie;
        this.cible = cible;
        this.taux = taux;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        
        
    }

    public static String generateRandomString(){
      /*  int length = 8;
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String charactersLength = strlen(characters);
        
        String randomString = "";
      
        for (int i = 0; i < length; i++) {
             randomString .= characters[rand(0, charactersLength - 1)];
        }
        return randomString;*/
          byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
 
        return generatedString;
    }

    public Offre() {
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCible() {
        return cible;
    }

    public void setCible(String cible) {
        this.cible = cible;
    }

    public int getTaux() {
        return taux;
    }

    public void setTaux(int taux) {
        this.taux = taux;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

   /* public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }*/
    
    
    
    
    
}
