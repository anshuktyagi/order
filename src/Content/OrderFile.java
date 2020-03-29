/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Content;

import java.util.ArrayList;

/**
 *
 * @author anshu
 */
public class OrderFile {
    // Fill data into array list
    public ArrayList<Order> getCustomer(Order obj){
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(obj);
        return orders;
    }
    
}
