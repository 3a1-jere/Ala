/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crudfx;

import Entity.Voucher;
import Gestion.GestionVoucher;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class FXMLVoucherController implements Initializable {
    @FXML
    private TableView<Voucher> VoucherTableView;
    @FXML
    private TableColumn<Voucher,String> tableCode;
    @FXML
    private TableColumn<Voucher, Integer> tableNbUtil;
    @FXML
    private TableColumn<Voucher, Integer> tableUtilMax;
    @FXML
    private TableColumn<Voucher, Integer> tableRate;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonAjouter;
    @FXML
    private TextField codeText;
    @FXML
    private TextField nbUtilText;
    @FXML
    private TextField maxUtilText;
    @FXML
    private TextField rateText;

    private GestionVoucher GestionVo;
    @FXML
    private Button buttonModifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         initColums();
         GestionVo = new GestionVoucher();
        
         VoucherTableView.setItems(GestionVo.afficherVouchersList());
    }    
 public void initColums(){
        tableCode.setCellValueFactory(new PropertyValueFactory<Voucher,String>("voucherCode"));
        tableNbUtil.setCellValueFactory(new PropertyValueFactory<Voucher,Integer>("nbUse"));
        tableUtilMax.setCellValueFactory(new PropertyValueFactory<Voucher,Integer>("maxUse"));
        tableRate.setCellValueFactory(new PropertyValueFactory<Voucher,Integer>("rate"));
        
     
        //editableColumns();
        
    }
    

 
 
    
    
    @FXML
    private void SupprimerVoucherButton(ActionEvent event) {
        
          GestionVo.supprimerVoucher(VoucherTableView.getSelectionModel().getSelectedItem().getVoucherCode());
        VoucherTableView.getItems().clear();
       VoucherTableView.setItems(GestionVo.afficherVouchersList());
        
    }

    @FXML
    private void AjouterVoucherButton(ActionEvent event) {
        Voucher V = new Voucher(codeText.getText(),Integer.parseInt(nbUtilText.getText()),Integer.parseInt(maxUtilText.getText()),Integer.parseInt(rateText.getText()));
       GestionVo.ajouterVoucher(V);
       VoucherTableView.getItems().clear();
       VoucherTableView.setItems(GestionVo.afficherVouchersList());
              clearText();

        System.out.println("Voucher ajouté");
        
    }
    
    
     private Voucher vo;
    @FXML
    private void selectItem(){
        vo = VoucherTableView.getSelectionModel().getSelectedItem();
        if(vo!=null){
            codeText.setText(vo.getVoucherCode());
            nbUtilText.setText(String.valueOf(vo.getNbUse()));
            maxUtilText.setText(String.valueOf(vo.getMaxUse()));
            rateText.setText(String.valueOf(vo.getRate()));
 
        }
    }
    
    
    

    @FXML
    private void SwitchOffres(ActionEvent event) throws IOException {
        
         Parent offre_page_parent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene offre_page_scene =new Scene(offre_page_parent);
        offre_page_scene.getStylesheets().add(getClass().getResource("/css_produit/css_gestionProduit.css").toExternalForm());
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(offre_page_scene);
        app_stage.show();
    }

    @FXML
    private void ModifierVoucherButton(ActionEvent event) {
         Voucher V = new Voucher(codeText.getText(),Integer.parseInt(nbUtilText.getText()),Integer.parseInt(maxUtilText.getText()),Integer.parseInt(rateText.getText()));
       GestionVo.modifierVoucher(V,codeText.getText());
       VoucherTableView.getItems().clear();
       VoucherTableView.setItems(GestionVo.afficherVouchersList());
       clearText();
        System.out.println("Voucher Modifié");
        
    }
    
    
    @FXML
    private void clearText(){
        codeText.setText("");
        nbUtilText.setText("");
        maxUtilText.setText("");
        rateText.setText("");
    }
    
}
