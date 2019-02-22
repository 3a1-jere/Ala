/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ala
 */
public class dataSource {
    private  final String URL="jdbc:mysql://localhost/crudJAVA";
    private  final String username="root";
    private  final String password="1234";
    
    
            private Connection conn;
            
    static dataSource connexion;//la variable statique de la connection
    
    private dataSource(){
         try{
             conn = DriverManager.getConnection(URL,username,password);
            System.out.println("Connexion établie ");
        }
        catch(SQLException ex){
            System.out.println("erreur"+ex.getMessage());
        }
        
    }

    public Connection getConnection() {
        return conn;
    }
    
    public static dataSource getInstance(){
        if(connexion == null){
            connexion = new dataSource();
        }
        return connexion;
    }
}
