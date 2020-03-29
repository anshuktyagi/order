/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Content;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
public class CustomerFile extends Stage {
     TextField firstField = new TextField();
    TextField secondField = new TextField();
    TextField thirdField = new TextField();
    TextField fourthField = new TextField();
    Label name = new Label("Name");
    Label cId = new Label("CustomerId");
    Label street = new Label("Street Address");
    Label city = new Label("City");
    Button update = new Button("Update");
    HBox pane1 = new HBox(20, name, firstField);
    HBox pane2 = new HBox(20, cId, secondField);
    HBox pane3 = new HBox(20, street, thirdField);
    HBox pane4 = new HBox(20, city, fourthField);
    VBox pane = new VBox(20, pane1, pane2, pane3, pane4, update);
    Scene scene = new Scene(pane, 500, 500);
    
    public CustomerFile(){
        Stage customerStage = new Stage();
        customerStage.setScene(scene);
        customerStage.show();
    }

    // Fill data for new User into array list 
    public ArrayList<Customer> makeArray(Customer obj){
        ArrayList<Customer> objarr = new ArrayList<>();
        obj.setName(firstField.getText());
        obj.setStreetAddress(thirdField.getText());
        obj.setCity(fourthField.getText());
        objarr.add(obj);
        
        return objarr;
    }

    
// Transfer data from array list to customer.dat
    public void setData(ArrayList<Customer> custom) throws IOException{
        FileWriter customerFile = new FileWriter("Customer.dat"); 
        BufferedWriter writer = new BufferedWriter(customerFile);
        writer.write(custom.toString()+"hello");
        writer.flush();
        writer.close();
        
    }
    public void printing() throws IOException{
        update.setOnMouseClicked((e) ->{
            Customer obj = new Customer(Integer.parseInt(secondField.getText()));
            try {
                setData(makeArray(obj));
            } catch (IOException ex) {
                System.out.println("Exception");
            }
        });
        
    }
}
