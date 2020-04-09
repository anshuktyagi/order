/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CustomerFile extends Stage {

    TextField firstField = new TextField();
    TextField secondField = new TextField();
    TextField thirdField = new TextField();
    TextField fourthField = new TextField();
    Label name = new Label("Name");
    Label cId = new Label("CustomerId");
    Label street = new Label("Street Address");
    Label city = new Label("City");
    Button close = new Button("Close");
    Button update = new Button("Update");
    HBox pane1 = new HBox(20, name, firstField);
    HBox pane2 = new HBox(20, cId, secondField);
    HBox pane3 = new HBox(20, street, thirdField);
    HBox pane4 = new HBox(20, city, fourthField);
    HBox pane5 = new HBox(40, update, close);
    VBox pane = new VBox(20, pane1, pane2, pane3, pane4, pane5);
    Scene scene = new Scene(pane, 500, 500);
    static int i = 0;
    static ArrayList<Customer> cusomterList = new ArrayList<>();
    static ArrayList<Customer> getcusomterList = new ArrayList<>();

    public CustomerFile() throws IOException {
        Stage customerStage = new Stage();
        customerStage.setScene(scene);
        printing();
        customerStage.show();
        close.setOnMouseClicked((e) -> {
            customerStage.close();

        });

    }

    // Fill data for new User into array list 
    public void addCustomerToList(Customer obj) throws FileNotFoundException, IOException {
        FileReader filecust = new FileReader("Customer.dat");
        BufferedReader readercust = new BufferedReader(filecust);
        ArrayList<String> arrCust = new ArrayList<>();
        boolean checkCid = false;
        String dataCustomer;
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
            if (!checkCid) {

                obj.setName(firstField.getText());
                obj.setStreetAddress(thirdField.getText());
                obj.setCity(fourthField.getText());
                cusomterList.add(obj);
                MainClass.addToCustList(obj);
                String alertString="Customer Id: "+obj.getCustomerId()+"\n"+"Name: "+
                        obj.getName()+"\n"+"Street address: "
                    +obj.getStreetAddress()+"\n"+"City: "+
                        obj.getCity()+"\n";
                 AlertClass.infoAlert("New customer Id", alertString);
                 
            } else {
                AlertClass.ialert("Customer Id is not unique");
            }
        } else {
            AlertClass.ialert("Fields can't be empty");

        }

    }

// Transfer data from array list to customer.dat
    public void setData(ArrayList<Customer> custom) throws IOException {

        File file = new File("Customer.dat");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        if (!(cusomterList.size() <= i)) {
            Customer input = cusomterList.get(i);
            writer.write(input.getCustomerId() + "," + input.getName() + "," + input.getStreetAddress() + "," + input.getCity() + "\n");
            writer.flush();
            i++;
        }
        writer.close();
        fileWriter.close();

        

    }

//read data ffrom file
    public static ArrayList<Customer> getData() throws FileNotFoundException, IOException {
        FileReader customerFile = new FileReader("Customer.dat");
        BufferedReader reader = new BufferedReader(customerFile);
        Customer obj = null;
        String data;
        while ((data = reader.readLine()) != null) {

            StringTokenizer one = new StringTokenizer(data, ",");
            obj = new Customer(one.nextToken());
            obj.setName(one.nextToken());
            obj.setStreetAddress(one.nextToken());
            obj.setCity(one.nextToken());
            getcusomterList.add(obj);
            
        }
        
        reader.close();
        return getcusomterList;
    }

    public void printing() throws IOException {

        update.setOnMouseClicked((e) -> {

            Customer obj = new Customer(secondField.getText());

            try {
                addCustomerToList(obj);
                setData(cusomterList);
                //  getData();
            } catch (IOException ex) {
                System.out.println("Exception");
            }
           
            firstField.clear();
            secondField.clear();
            thirdField.clear();
            fourthField.clear();

        });

    }
}
