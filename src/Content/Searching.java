/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Content;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author anshu
 */
public class Searching extends Stage {

    TextField firstFeild = new TextField();
    TextArea firstArea = new TextArea();
    Label firstLabel = new Label();
//    Label secondLabel = new Label();
    Button search = new Button("Search");
    Button close = new Button("Close");
    Button close2 = new Button("Close");
    HBox pane1 = new HBox(20, firstLabel, firstFeild);
    VBox pane2 = new VBox(20, firstArea, close2);
    HBox pane3 = new HBox(20, search, close);
    VBox pane = new VBox(20, pane1, pane3);
    Scene scene = new Scene(pane, 500, 500);
    Stage result = new Stage();
    Scene secondScene = new Scene(pane2, 500, 500);

    public Searching() throws FileNotFoundException {

        boolean check = AlertClass.salert();
        if (AlertClass.checking) {
            Stage searchStage = new Stage();
            if (check) {
                firstLabel.setText("Customer ID: ");
            } else {
                firstLabel.setText("Product: ");
            }

            searchStage.setScene(scene);

            searchStage.show();
            FileReader file = new FileReader("Order.dat");
            BufferedReader reader = new BufferedReader(file);

            search.setOnMouseClicked((e) -> {
                String getData;
                String token;
                String records = "";
                boolean checkRecords = false;
                if (check) {
                    try {

                        String data = firstFeild.getText();
                        while ((getData = reader.readLine()) != null) {
                            StringTokenizer one = new StringTokenizer(getData, ",");
                            one.nextToken();
                            token = one.nextToken();
                            if (data.equalsIgnoreCase(token)) {
                                records += getData + "\n";
                                checkRecords = true;
                            }
                        }
                        if (checkRecords) {
                            firstArea.setText(records);
                            System.out.println(AlertClass.checking);
                            result.setScene(secondScene);
                            result.show();
                            data = "";
                        } else {
                            firstArea.setText("No records found for this customer Id.");
                            result.setScene(secondScene);
                            result.show();
                            data = "";
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (!check) {
                    try {

                        String data = firstFeild.getText();
                        while ((getData = reader.readLine()) != null) {
                            StringTokenizer one = new StringTokenizer(getData, ",");
                            one.nextToken();
                            one.nextToken();
                            if (data.equalsIgnoreCase(one.nextToken())) {
                                records += getData + "\n";
                                checkRecords = true;
                            }
                        }
                        if (checkRecords) {
                            firstArea.setText(records);
                            result.setScene(secondScene);
                            result.show();
                            data = "";
                        } else {
                            firstArea.setText("No records found for this product.");
                            result.setScene(secondScene);
                            result.show();
                            data = "";
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Searching.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

            close.setOnMouseClicked((e) -> {
                searchStage.close();
            });
        }
        else{
            AlertClass.checking=true;
        }
        close2.setOnMouseClicked((e) -> {
            result.close();
        });

    }
}
