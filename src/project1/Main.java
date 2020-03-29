/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import Content.CustomerFile;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author anshu
 */
public class Main extends Application {
    TextField firstField = new TextField();
    TextField secondField = new TextField();
    TextField thirdField = new TextField();
    TextField fourthField = new TextField();
    Button next = new Button("Next");
    Button previous = new Button("Previous");
    Button last = new Button("Last");
    Button first = new Button("First");
    Button modify = new Button("Modify");
    Button add = new Button("add");
    Label fLabel = new Label("id");
    HBox pane1 = new HBox(20, firstField, secondField, thirdField, fourthField);
    HBox pane2 = new HBox(20, next, previous, last, first, modify, add);
    VBox pane = new VBox(20, pane1, pane2);
    Scene scene = new Scene(pane, 500, 500);
    
    public boolean alert(){
        Alert check = new Alert(Alert.AlertType.CONFIRMATION);
        check.setContentText("Are you a new customer");
        ButtonType btn1 = new ButtonType("Yes");
        ButtonType btn2 = new ButtonType("No");
        check.getButtonTypes().setAll(btn1, btn2);
        Optional<ButtonType> result = check.showAndWait();
        if (result.get()==btn1) {
            return true;
        }
        else{
            return false;
        }
        
    }
    @Override
    public void start(Stage primaryStage) {
        add.setOnMouseClicked((e) -> {
             boolean check=alert();
             if (check) {
                 CustomerFile one = new CustomerFile();
            } else {
                 AddOrder one = new AddOrder();
            }
            
           
            primaryStage.hide();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
