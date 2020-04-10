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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
    Button next = new Button("Next Order");
    Button previous = new Button("Previous Order");
    Button last = new Button("Last Order");
    Button first = new Button("First Order");
    Button modify = new Button("Modify Order");
    Button add = new Button("Add Customer/Order");
    Button displayOrder = new Button("Show Orders");
    Button displayCustomers = new Button("Show Customers");
    Button delete = new Button("Delete Order");
    Button search = new Button("Search Order");
    Label fLabel = new Label("id");
    HBox pane1 = new HBox(20, firstField, secondField, thirdField, fourthField);
    HBox pane2 = new HBox(20, previous, next, first, last, modify, add, delete);
    HBox pane3 = new HBox(20, displayCustomers, displayOrder, search);
    VBox pane = new VBox(20, pane1, pane2, pane3);
    Scene scene = new Scene(pane, 800, 600);
    int orderListindex = 0;
    ArrayList<Order> orderList = new ArrayList<>();
    static ArrayList<Customer> customerList = new ArrayList<>();
    Modify mod;

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        orderList = OrderFile.getOrders();
        customerList = CustomerFile.getData();

        display();
        first.setOnMouseClicked((e) -> {
            orderListindex = 0;
            display();
        });
        previous.setOnMouseClicked((e) -> {
            orderListindex--;
            if (orderListindex < 0) {
                orderListindex = 0;
            }
            display();
        });
        next.setOnMouseClicked((e) -> {
            orderListindex++;
            if (orderListindex > orderList.size() - 1) {
                orderListindex--;
            }
            display();
        });
        last.setOnMouseClicked((e) -> {
            orderListindex = orderList.size() - 1;
            display();
        });
        modify.setOnMouseClicked((e) -> {
            mod = new Modify();
            mod.show();
            mod.setField(orderList, orderListindex);
            mod.setEvents(orderList, orderListindex, primaryStage);
            System.out.println("modify button trigger");
            mod.setOnHiding((ev) -> {
                try {

                    OrderFile.saveInFile(orderList);
                    display();
                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

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
                if (AlertClass.deleteConfirmationDialogBox(orderList, orderListindex)) {
                    orderList.remove(orderListindex);
                    AlertClass.infoConfirmationDialogBox("Done", "Order deleted");
                    if (orderListindex >= (orderList.size())) {
                        orderListindex--;
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
                OrderFile.saveInFile(orderList);
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        add.setOnMouseClicked((e) -> {

            if (alert()) {
                try {
                    CustomerFile newCustomer = new CustomerFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                OrderFile newOrderStage = new OrderFile();
                newOrderStage.show();
                newOrderStage.setEvents();
                System.out.println("modify button trigger ");
                newOrderStage.setOnHiding((ev) -> {

                    orderListindex = orderList.size() - 1;
                    display();
                });

            }

        });
        search.setOnMouseClicked((e) -> {
            Searching obj = new Searching();
        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public boolean alert() {
        Alert check = new Alert(Alert.AlertType.CONFIRMATION);
        check.setContentText("Are you a new customer");
        ButtonType yesBtn = ButtonType.YES;
        ButtonType noBtn = ButtonType.NO;
        check.getButtonTypes().setAll(yesBtn, noBtn);
        Optional<ButtonType> result = check.showAndWait();
        if (result.get() == yesBtn) {
            return true;
        } else {
            return false;
        }

    }

    public static void addToCustList(Customer obj) {
        customerList.add(obj);
    }

    public void display() {
        if (!orderList.isEmpty()) {
            firstField.setText(orderList.get(orderListindex).getOrderId());
            secondField.setText(orderList.get(orderListindex).getCustomerId());
            thirdField.setText(orderList.get(orderListindex).getProduct());
            fourthField.setText(orderList.get(orderListindex).getShipping());
        } else {
            firstField.clear();
            secondField.clear();
            thirdField.clear();
            fourthField.clear();
        }
    }

}
