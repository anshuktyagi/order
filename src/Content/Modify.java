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

    public Modify() {

        setScene(scene);

    }

    public void setEvents(ArrayList<Order> orderList, int i, Stage primaryStage) {
        modify.setOnMouseClicked((e) -> {
            changeOrderList(orderList, i);
            this.close();
        });
        close.setOnMouseClicked((e) -> {
            this.close();
        });
    }

    public void setField(ArrayList<Order> orderList, int i) {
        Order orderObj = orderList.get(i);
        firstField.setText(orderObj.getProduct());
        secondField.setText(orderObj.getShipping());

    }

    public void changeOrderList(ArrayList<Order> orderList, int i) {
        if (AlertClass.modifyConfirmationDialogBox()) {
            if (!(firstField.getText().isEmpty() || secondField.getText().isEmpty())) {
                Order orderObj = orderList.get(i);
                orderObj.setProduct(firstField.getText());
                orderObj.setShipping(secondField.getText());
                orderList.set(i, orderObj);
                AlertClass.infoConfirmationDialogBox("Done", "Order Modified");

            } else {
                AlertClass.errorConfirmationDialogBox("Fields can't be empty");
            }
        } else {
            AlertClass.infoConfirmationDialogBox("Okay", "Order not modified");
        }
    }
}
