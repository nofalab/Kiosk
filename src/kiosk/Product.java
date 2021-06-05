//Nofal Abdallah(991631883)
//Assignment1: Kiosk program to perform calculations and show how much to 
//collect from custumers.
//Date of submission: May 25, 2021
package kiosk;

public class Product{
        
    //declaring data 
    private String itemName;
    private int itemQty;
    private double itemPrice;

    //Constructor 
    public Product(String itemName, int itemQty, double itemPrice){
        setItemName(itemName); 
        setItemQty(itemQty);
        setItemPrice(itemPrice);
    }

    //creating accesors
    public String getItemName(){
            return itemName;
    }

    public int getItemQty(){
            return itemQty;
    }

    public double getItemPrice(){
            return itemPrice;
    }

    //creating mutators
    public void setItemName(String itemName){
            if (itemName==null || itemName.isEmpty())
                    throw new IllegalArgumentException("name can not be "
                            + "null or empty");
            else 
                    this.itemName = itemName;
    }

    public void setItemQty(int itemQty){
            if (itemQty >= 0)
                    this.itemQty = itemQty;
            else 
                    throw new IllegalArgumentException(" Error must be more"
                            + " than zero");
    }

    public void setItemPrice(double itemPrice){
            if (itemPrice >= 0)
                    this.itemPrice = itemPrice;
            else 
                    throw new IllegalArgumentException(" Error must be more"
                            + " than zero");
    }

    //calculating total method
    public double calcTotal(int exactQty){
                    return (itemPrice/itemQty)*exactQty; 
    }

    //displaying info as string
    public String toString(){
            return String.format("%s - %d for $%.2f", itemName, itemQty, 
                    itemPrice); 
    }	


		
	
}
