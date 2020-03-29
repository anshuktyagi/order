package project1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anshu
 */
public class AddOrder extends Stage {
    TextField firstField = new TextField();
    TextField secondField = new TextField();
    TextField thirdField = new TextField();
    TextField fourthField = new TextField();
    Label oId = new Label("OrderId");
    Label cId = new Label("CustomerId");
    Label product = new Label("Product");
    Label method = new Label("Shipping");
    Button update = new Button("Update");
    HBox pane1 = new HBox(20, oId, firstField);
    HBox pane2 = new HBox(20, cId, secondField);
    HBox pane3 = new HBox(20, product, thirdField);
    HBox pane4 = new HBox(20, method, fourthField);
    VBox pane = new VBox(20, pane1, pane2, pane3, pane4, update);
    Scene scene = new Scene(pane, 500, 500);
    public AddOrder(){
        Stage addStage = new Stage();
        addStage.setScene(scene);
        addStage.show();
    }
    
    
}
