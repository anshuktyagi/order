/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import Content.AlertClass;
import Content.Customer;
import Content.CustomerFile;
import Content.Modify;
import Content.Order;
import Content.OrderFile;
import Content.Searching;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author anshu
 */
public class MainClass extends Application {

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
    Button displayOrder = new Button("Orders");
    Button displayCustomers = new Button("Customers");
    Button delete = new Button("Delete");
    Button search = new Button("Search");
    Label fLabel = new Label("id");
    HBox pane1 = new HBox(20, firstField, secondField, thirdField, fourthField);
    HBox pane2 = new HBox(20, next, previous, last, first, modify, add, delete);
    HBox pane3 = new HBox(20, displayCustomers, displayOrder, search);
    VBox pane = new VBox(20, pane1, pane2, pane3);
    Scene scene = new Scene(pane, 600, 500);
    int i = 0;
    ArrayList<Order> orderList = new ArrayList<>();
   static  ArrayList<Customer> customerList = new ArrayList<>();

    public boolean alert() {
        Alert check = new Alert(Alert.AlertType.CONFIRMATION);
        check.setContentText("Are you a new customer");
        ButtonType btn1 = new ButtonType("Yes");
        ButtonType btn2 = new ButtonType("No");
        check.getButtonTypes().setAll(btn1, btn2);
        Optional<ButtonType> result = check.showAndWait();
        if (result.get() == btn1) {
            return true;
        } else {
            return false;
        }

    }
    public static void addToCustList(Customer obj){
        customerList.add(obj);
    }

    public void display() {
        if (!orderList.isEmpty()) {
            firstField.setText(orderList.get(i).getOrderId());
            secondField.setText(orderList.get(i).getCustomerId());
            thirdField.setText(orderList.get(i).getProduct());
            fourthField.setText(orderList.get(i).getShipping());
        } else {
            firstField.clear();
            secondField.clear();
            thirdField.clear();
            fourthField.clear();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        orderList = OrderFile.getOrders();
        customerList = CustomerFile.getData();
        display();
        first.setOnMouseClicked((e) -> {
            i = 0;
            display();
        });
        next.setOnMouseClicked((e) -> {
            i++;
            if (i > orderList.size() - 1) {
                i--;
            }
            display();
        });
        previous.setOnMouseClicked((e) -> {
            i--;
            if (i < 0) {
                i = 0;
            }
            display();
        });
        last.setOnMouseClicked((e) -> {
            i = orderList.size() - 1;
            display();
        });
        modify.setOnMouseClicked((e) -> {
            Modify mod = new Modify();
            orderList = mod.modify(orderList, i);
            
            display();

        });
        displayOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Stage allOrders = new Stage();

                Button close = new Button("Close");
                VBox paneBox = new VBox(20);
                String text = "";
                TextArea one = new TextArea();
                for (int j = 0; j < orderList.size(); j++) {

                    text += (orderList.get(j).getOrderId() + ","
                            + orderList.get(j).getCustomerId() + ","
                            + orderList.get(j).getProduct() + ","
                            + orderList.get(j).getShipping() + "\n");

                }
                one.setText(text);

                paneBox.getChildren().addAll(one, close);
                Scene scene = new Scene(paneBox, 500, 500);
                allOrders.setScene(scene);
                allOrders.show();
                close.setOnMouseClicked((a) -> {
                    allOrders.close();
                });
            }
        });
        displayCustomers.setOnMouseClicked((e) -> {
            Stage allCustomers = new Stage();

            Button close = new Button("Close");
            VBox paneBox = new VBox(20);
            TextArea one = new TextArea();
            String text = "";
            for (int j = 0; j < customerList.size(); j++) {

                text += (customerList.get(j).getCustomerId() + ","
                        + customerList.get(j).getName() + ","
                        + customerList.get(j).getStreetAddress() + ","
                        + customerList.get(j).getCity() + "\n");

            }
            one.setText(text);
            paneBox.getChildren().addAll(one, close);
            Scene scene = new Scene(paneBox, 500, 500);
            allCustomers.setScene(scene);
            allCustomers.show();
            close.setOnMouseClicked((a) -> {
                allCustomers.close();
            });
        });

        delete.setOnMouseClicked((e) -> {
            if (!orderList.isEmpty()) {
                if (AlertClass.dAlert(orderList, i)) {
                    orderList.remove(i);
                    if (i >= (orderList.size())) {
                        i--;
                    }

                    display();

                } else {
                    display();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nothing to delete");
                alert.show();
            }
            try {
                OrderFile.onExit(orderList);
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        add.setOnMouseClicked((e) -> {

            if (alert()) {
                try {
                    CustomerFile two = new CustomerFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                OrderFile one = new OrderFile();
                i = orderList.size() - 1;
                display();
            }
            

        });
        search.setOnMouseClicked((e) -> {
            try {
                Searching obj = new Searching();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
