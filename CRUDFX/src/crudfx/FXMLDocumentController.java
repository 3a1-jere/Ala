/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crudfx;

import Entity.Offre;
import Gestion.GestionOffre;
import java.io.IOException;
import java.net.URL;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label21;
    @FXML
    private Label label22;
    @FXML
    private TextField cibleText;
    @FXML
    private TextField CategorieText;
    @FXML
    private TextField tauxText;
    @FXML
    private DatePicker debutDate;
    @FXML
    private DatePicker finDate;
    @FXML
    private TextField idText;
    @FXML
    private Label label3;
    @FXML
    private TableView<Offre> tableView;
    @FXML
    private TableColumn<Offre,String> tableId;
    @FXML
    private TableColumn<Offre,String> tableCible;
    @FXML
    private TableColumn<Offre,String> tableCategorie;
    @FXML
    private TableColumn<Offre,Integer> tableTaux;
    @FXML
    private TableColumn<Offre,Date> tableDebut;
    @FXML
    private TableColumn<Offre,Date> tableFin;
    @FXML
    private Button button1;
    
    private static final String DATE_PATTERN = "YYYY-dd-mm";

    
    private GestionOffre GestionOff;
    @FXML
    private Button buttonDELETE;
    @FXML
    private Button ButtonVoucher;
    @FXML
    private Button buttonModify;
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initColums();
         GestionOff = new GestionOffre();
        
         tableView.setItems(GestionOff.afficherOffresList());
        
       
        
        
    }    

 

    @FXML
    private void handleButtonAction(ActionEvent event) {
       
          
             Date date_debut = java.sql.Date.valueOf(debutDate.getValue());
         Date date_fin = java.sql.Date.valueOf(finDate.getValue());
        
        
        System.out.println("Id :"+idText.getText());
          System.out.println("Categorie :"+CategorieText.getText());
            System.out.println("Cible :"+cibleText.getText());
              System.out.println("Taux :"+tauxText.getText());
                System.out.println("Date debut :"+debutDate.getValue());
                System.out.println("Date fin :"+finDate.getValue());
               
        
        
     
        //DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
        //Date currentDate = new Date();
        //System.out.println(date_ajout);
        //String dateF = dateFormat.format(date_ajout); 
	//System.out.println(dateF); //2016/11/16 12:08:43
        
    
   //Date DA = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);

             
        Offre O = new Offre(idText.getText(),CategorieText.getText(),cibleText.getText(),Integer.parseInt(tauxText.getText()),date_debut,date_fin);
       GestionOff.ajouterOffre(O);
       tableView.getItems().clear();
       tableView.setItems(GestionOff.afficherOffresList());
        System.out.println("Offre ajoutée");
       
    }
    
    public void initColums(){
        tableId.setCellValueFactory(new PropertyValueFactory<Offre,String>("id"));
        tableCible.setCellValueFactory(new PropertyValueFactory<Offre,String>("categorie"));
        tableCategorie.setCellValueFactory(new PropertyValueFactory<Offre,String>("cible"));
        tableTaux.setCellValueFactory(new PropertyValueFactory<Offre,Integer>("taux"));
        tableDebut.setCellValueFactory(new PropertyValueFactory<Offre,Date>("date_debut"));
        tableFin.setCellValueFactory(new PropertyValueFactory<Offre,Date>("date_fin"));
        //editableColumns();
        
    }
    
    
    /*private void editableColumns(){
        tableView.setEditable(true);
        
        tableCible.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCible.setOnEditCommit(e->{e.getTableView().getItems().get(e.getTablePosition().getRow()).setCible(e.getNewValue());});
        
          tableCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCategorie.setOnEditCommit(e->{e.getTableView().getItems().get(e.getTablePosition().getRow()).setCategorie(e.getNewValue());});
        
       
       
    }*/

    @FXML
    private void DeleteButton(ActionEvent event) {
        
        GestionOff.supprimerOffre(tableView.getSelectionModel().getSelectedItem().getId());
        tableView.getItems().clear();
       tableView.setItems(GestionOff.afficherOffresList());
        
    }
    
    private Offre of;
    @FXML
    private void selectItem(){
        of=tableView.getSelectionModel().getSelectedItem();
        if(of!=null){
            idText.setText(of.getId());
            cibleText.setText(of.getCible());
            CategorieText.setText(of.getCategorie());
            tauxText.setText(String.valueOf(of.getTaux()));
           // Date date_d=of.getDate_debut();
            //Date date_f=of.getDate_fin();
            debutDate.setValue(of.getDate_debut().toLocalDate());
            finDate.setValue(of.getDate_fin().toLocalDate());
            
            
            
            
           //debutDate.setValue(LOCAL_DATE(date_d.toString(DATE_PATTERN)));
            
            
            
           // debutDate.setValue();
            //System.out.println(date_d);
            //System.out.println(date_d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            //debutDate.setValue(date_d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            
            //finDate.setValue(date_f.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

       
           
          
        }
    }

    @FXML
    private void SwitchVoucher(ActionEvent event) throws IOException {
        
        Parent voucher_page_parent = FXMLLoader.load(getClass().getResource("FXMLVoucher.fxml"));
        Scene voucher_page_scene =new Scene(voucher_page_parent);
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(voucher_page_scene);
        app_stage.show();
        
    }

    @FXML
    private void ModifyButton(ActionEvent event) {
        
         Date date_debut = java.sql.Date.valueOf(debutDate.getValue());
         Date date_fin = java.sql.Date.valueOf(finDate.getValue());
         Offre of = new Offre(idText.getText(),cibleText.getText(),CategorieText.getText(),Integer.parseInt(tauxText.getText()),date_debut,date_fin);
       GestionOff.modifierOffre(of,idText.getText());
       tableView.getItems().clear();
       tableView.setItems(GestionOff.afficherOffresList());
       //clearText();
        System.out.println("Voucher Modifié");
        
    }
  @FXML
    private void clearText(){
        idText.setText("");
        cibleText.setText("");
        CategorieText.setText("");
        tauxText.setText("");
        debutDate.setValue(LocalDate.MIN);
        finDate.setValue(LocalDate.MIN);
    }

  


    
}
