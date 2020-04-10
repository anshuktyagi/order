/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Content;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project1.MainClass;

/**
 *
 * @author anshu
 */
public class Modify extends Stage {

    TextField firstField = new TextField();
    TextField secondField = new TextField();
    Label product = new Label("Product");
    Label shipping = new Label("shipping");
    Button modify = new Button("Modify");
    Button close = new Button("Close");
    HBox pane1 = new HBox(20, product, firstField);
    HBox pane2 = new HBox(20, shipping, secondField);
    HBox pane3 = new HBox(20, modify, close);
    VBox pane = new VBox(20, pane1, pane2, pane3);
    Scene scene = new Scene(pane);

    public ArrayList<Order> modify(ArrayList<Order> orderList, int i) {
        Stage modifyStage = new Stage();
        modifyStage.setScene(scene);
        modifyStage.show();
        modify.setOnMouseClicked((e) -> {
            changeList(orderList, i);
            modifyStage.close();
        });
        close.setOnMouseClicked((e) -> {
            modifyStage.close();
        });
        modifyStage.setOnHiding((e) -> {
            try {
                OrderFile.onExit(orderList);
            } catch (IOException ex) {
                Logger.getLogger(Modify.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return orderList;
    }

    public void changeList(ArrayList<Order> orderList, int i) {
        if(AlertClass.mAlert()){
        if (!(firstField.getText().isEmpty() || secondField.getText().isEmpty())) {
            Order one = orderList.get(i);
            one.setProduct(firstField.getText());
            one.setShipping(secondField.getText());
            orderList.set(i, one);
            AlertClass.infoAlert("Done", "Order Modified");
        } else {
            AlertClass.ialert("Fields can't be empty");
        }
        }
        else{
            AlertClass.infoAlert("Okay", "Order not modified");
        }
    }
}
