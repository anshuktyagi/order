package Content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OrderFile extends Stage {
    
    TextField firstField = new TextField();
    TextField secondField = new TextField();
    TextField thirdField = new TextField();
    TextField fourthField = new TextField();
    Label oId = new Label("OrderId");
    Label cId = new Label("CustomerId");
    Label product = new Label("Product");
    Label method = new Label("Shipping");
    Button update = new Button("Update");
    Button close = new Button("Close");
    HBox pane1 = new HBox(20, oId, firstField);
    HBox pane2 = new HBox(20, cId, secondField);
    HBox pane3 = new HBox(20, product, thirdField);
    HBox pane4 = new HBox(20, method, fourthField);
    HBox pane5 = new HBox(40, update, close);
    VBox pane = new VBox(20, pane1, pane2, pane3, pane4, pane5);
    Scene scene = new Scene(pane, 500, 500);
    static ArrayList<Order> orderList = new ArrayList<Order>();
    static ArrayList<Order> getOrderList = new ArrayList<Order>();
    static boolean check = true;
    static int i = 0;
    
    public OrderFile() {
        Stage addStage = new Stage();
        addStage.setScene(scene);
        addStage.show();
        printing();
        close.setOnMouseClicked((e) -> {
            addStage.close();
        });
        
    }
    
    public void orderDetails(Order obj) throws FileNotFoundException, IOException {
        FileReader file = new FileReader("Order.dat");
        BufferedReader reader = new BufferedReader(file);
        FileReader filecust = new FileReader("Customer.dat");
        BufferedReader readercust = new BufferedReader(filecust);
        ArrayList<String> arrOrder = new ArrayList<>();
        ArrayList<String> arrCust = new ArrayList<>();
        boolean checkOid = false;
        boolean checkCid = false;
        String dataOrder;
        String dataCustomer;
        int subOrder = 0;
        int subCust = 0;
        if (!(firstField.getText().isEmpty() || firstField.getText().isEmpty()
                || secondField.getText().isEmpty()
                || thirdField.getText().isEmpty()
                || fourthField.getText().isEmpty())) {
            while ((dataCustomer = readercust.readLine()) != null) {
                StringTokenizer one = new StringTokenizer(dataCustomer, ",");
                arrCust.add(subCust, one.nextToken());
                subCust++;
                
            }
            for (int a = 0; a < arrCust.size(); a++) {
                if (secondField.getText().equalsIgnoreCase(arrCust.get(a))) {
                    checkCid = true;
                }
            }
            while ((dataOrder = reader.readLine()) != null) {
                StringTokenizer one = new StringTokenizer(dataOrder, ",");
                arrOrder.add(subOrder, one.nextToken());
                subOrder++;
                
            }
            
            for (int j = 0; j < arrOrder.size(); j++) {
                if (firstField.getText().equalsIgnoreCase(arrOrder.get(j))) {
                    checkOid = true;
                }
            }
            if (checkCid) {
                if (!checkOid) {
                    
                    obj.setCustomerId(secondField.getText());
                    obj.setProduct(thirdField.getText());
                    obj.setShipping(fourthField.getText());
                    orderList.add(obj);
                    String alertString="Order Id: "+obj.getOrderId()+"\n"+
                        "Customer Id: "+obj.getCustomerId()+"\n"+"Product: "+
                        obj.getProduct()+"\n"+"Shipping method: "+
                        obj.getShipping();
                    AlertClass.infoAlert("New Order added", alertString);
                    
                } else {
                    AlertClass.ialert("Order Id is not unique");
                    checkOid = false;
                }
            } else {
                AlertClass.ialert("Please enter valid customer Id");
                checkCid = false;
            }
        } else {
            AlertClass.ialert("Fields can't be empty");
            firstField.clear();
            secondField.clear();
            thirdField.clear();
            fourthField.clear();
        }
        
    }
    
    public static void setOrder(boolean vrr) throws IOException {
        check = vrr;
        File file = new File("Order.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, check);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        if (!(orderList.size() <= i)) {
            Order input = orderList.get(i);
            writer.write(input.getOrderId() + "," + input.getCustomerId() + ","
                    + input.getProduct() + "," + input.getShipping() + "\n");
            writer.flush();
            i++;
        }
        writer.close();
        fileWriter.close();
        
    }
    
    public static ArrayList<Order> getOrders() throws FileNotFoundException, IOException {
        FileReader file = new FileReader("Order.dat");
        BufferedReader reader = new BufferedReader(file);
        
        String data;
        Order obj = null;
        while ((data = reader.readLine()) != null) {
            
            StringTokenizer one = new StringTokenizer(data, ",");
            obj = new Order(one.nextToken());
            obj.setCustomerId(one.nextToken());
            obj.setProduct(one.nextToken());
            obj.setShipping(one.nextToken());
            getOrderList.add(obj);
        }
        reader.close();
        return getOrderList;
    }
    
    public void printing() {
        update.setOnMouseClicked((e) -> {
            Order one = new Order(firstField.getText());
            try {
                orderDetails(one);
            } catch (IOException ex) {
                Logger.getLogger(OrderFile.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                
                setOrder(true);
                getOrders();
            } catch (IOException ex) {
                Logger.getLogger(OrderFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
    public static void saveInFile(ArrayList<Order> arr) throws IOException {
        File file = new File("Order.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        
        for (int i = 0; i < arr.size(); i++) {
            Order input = arr.get(i);
            writer.write(input.getOrderId() + "," + input.getCustomerId()
                    + "," + input.getProduct() + "," + input.getShipping() + "\n");
        }
        writer.flush();
        writer.close();
        fileWriter.close();
        
    }
}
