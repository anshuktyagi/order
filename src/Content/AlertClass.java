/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Content;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author anshu
 */
public class AlertClass {

    protected static boolean checking = true;
    static ArrayList<Order> orderobj = new ArrayList<>();
    static ArrayList<Customer> customerobj = new ArrayList<>();
    static OrderFile object;
    static CustomerFile custObj;

    public static boolean deleteConfirmationDialogBox(ArrayList<Order> obj, int i) {
        orderobj = obj;
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you want to delete this order");
        alert.setContentText("Order Id: " + obj.get(i).getOrderId() + "\n"
                + "Customer Id: " + obj.get(i).getCustomerId() + "\n" + "Product: "
                + obj.get(i).getProduct() + "\n" + "Shipping: "
                + obj.get(i).getShipping());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean modifyConfirmationDialogBox() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Do you want to modify this order");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static void errorConfirmationDialogBox(String data) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText(data);
        alert.show();

    }

    public static boolean searchConfirmationDialogBox() {
        boolean check = true;
        Alert alertObj = new Alert(AlertType.CONFIRMATION);
        alertObj.setTitle("Search Options");
        alertObj.setHeaderText("How do you want to search?");
        alertObj.setContentText("1. Select Customer to search orders of a"
                + " perticular customer Id\n 2.Select product to search "
                + "orders according to a perticular product");
        ButtonType customerBtn = new ButtonType("Customer");
        ButtonType productBtn = new ButtonType("Product");
        ButtonType cancelBtn = ButtonType.CANCEL;
        alertObj.getButtonTypes().setAll(customerBtn, productBtn, cancelBtn);

        Optional<ButtonType> result = alertObj.showAndWait();

        if (result.get() == cancelBtn) {
            alertObj.close();
            checking = false;
        } else if (result.get() == customerBtn) {
            check = true;
        } else if (result.get() == productBtn) {
            check = false;
        } else {
            check = false;
        }
        return check;
    }

    public static void infoConfirmationDialogBox(String data, String alertString) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(data);
        alert.setHeaderText(alertString);

        alert.show();
    }
}
