
//Nofal Abdallah(991631883)
//Assignment1: Kiosk program to perform calculations and show how much to 
//collect from custumers.
//Date of submission: May 25, 2021
//this a BRANCH 1

package kiosk;

import java.util.Scanner;

public class Kiosk {

    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);
        
        //creating array to store items. Using method that takes sc as argument 
        //and return an array.
        Product[] itemsArray = creatItemArray(sc); 
        
        //program loop flag, and total daily sale 
        boolean shutdown = false; 
        double totalDaySales = 0.0;
        
        //loop to perform program objectives. ends upon user's "shutdown" entry.
        while (!shutdown){
            
            System.out.println("\nWelcome to the Kiosk!\nWe sell:");
            
            //printing list of available products for sale
            for (Product eachItem: itemsArray)
                System.out.println(eachItem);

            //declaring variables, the chosen item by user, flag if item is 
            //found in array, and while loop flag to add more items... 
            Product chosenItem;
            boolean itemFound; 
            boolean continueBuying;
            String nameOfProduct;  

            // initialing variables, sale for the tranaction, string response 
            // from user at end whether item name or shutdown
            double totalPrice = 0.0;
            String responseOtherProduct = "";
            
            
            //loop for the actual sale transaction session
            do {
                
                //initializing variable upon each iteration
                chosenItem = null;
                itemFound = false; 
                continueBuying = true; 
                double amount = 0;
                
                //if user input when asked what to buy is not "N" then it may 
                //be new item name that needs to be processed 
                if (!responseOtherProduct.toUpperCase().equals('N') && 
                        !responseOtherProduct.isEmpty())
                    nameOfProduct = responseOtherProduct;
                
                //this code will only be implemented once at beginning of loop, 
                //afterwords, name of of product will be taken from above 
                else {
                    System.out.println("What do you want to buy?");
                    nameOfProduct = sc.next();
                }
                
                //finding match to name of product in the array
                for (Product eachItem: itemsArray){
                    if (eachItem.getItemName().equalsIgnoreCase(nameOfProduct)){
                        chosenItem = eachItem;
                        itemFound = true;
                        break;
                    }
                }
                
                //we perform calculation once item is found
                if (itemFound){
                    System.out.println("How many?");
                    amount = chosenItem.calcTotal(sc.nextInt());
                    totalPrice += amount;
                    totalDaySales += amount;

                }
                
                //terminating if shutdown is entered 
                else if (nameOfProduct.equalsIgnoreCase("shutdown")){
                    shutdown = true;
                    System.out.printf("\nTotal sales for the day:\n$%.2f\n\n", 
                            totalDaySales);
                    break;
                }
                
                //to inform user item does not exist
                else
                    System.out.println("item not found, try again");
                
                // prompting user, either item name or N
                System.out.println("Any other product?");
                    responseOtherProduct = sc.next();
                if (responseOtherProduct.toUpperCase().charAt(0) == 'N')
                    continueBuying = false;

            } while (continueBuying);
            
            //exits the parent loop to exit 
            if (nameOfProduct.equalsIgnoreCase("shutdown"))
                break;

            System.out.printf("Your total price would be $%.2f\n", totalPrice);
            System.out.println("Thanks for shopping with us\n------------------"
                    + "------------------------------------------------------");
            System.out.println("Are there any changes in price due to COVID-19 "
                    + "and/or weather conditions?");
            
            //creating new array if changes are required by a dedicated method
            if (sc.next().toUpperCase().charAt(0) == 'Y')
                itemsArray = creatItemArray(sc);
      
        }
               
    }
   
    //method to prompt user and creat an array of items 
    public static Product[] creatItemArray (Scanner sc){
      
        //declaring and intializing variables for loop  
        String itemName; 
        double itemPrice;
        int itemQty;
        
        //declaring and initializing array, but only declaring Product objects 
        Product[] itemsArray = new Product[3];

        //getting user input for array
        for (int i = 0; i<3; i++){

            //prompting for items data 
            System.out.println("Enter the name of the item:");
            itemName = sc.next();
            
            //passing the entered price and Qty into validity methods
            itemPrice = validityFilterPrice(sc);
            itemQty = validityFilterQty (sc);

            //to initialize new Product objects in array 
            itemsArray[i] = new Product(itemName, itemQty, itemPrice);          
        }
        return itemsArray;
    }
    
    //Validity checker method for price
    public static double validityFilterPrice (Scanner sc){
        double itemCost = 0;
        String strItemCost;
        
        do {
            System.out.println("Enter the price:");
            strItemCost = sc.next();
            if (strItemCost.matches("-?\\d+(\\.\\d+)?")){
                itemCost = Double.parseDouble(strItemCost);
                if (itemCost > 0){
                    return itemCost;
                } else 
                    System.out.print("cost of items must be more than 0\n");
            } else
                System.out.print("cost of item can not be letters\n");
        }while (!strItemCost.matches("-?\\d+(\\.\\d+)?") || itemCost <= 0);

    }
    
    //Validity checker method for Qty
    public static int validityFilterQty (Scanner sc){
             int qty = 0;
             String strQty;
        do {
            System.out.println("Enter the QTY:");
            strQty = sc.next();
            if (strQty.matches("-?\\d+")) {
                qty = Integer.parseInt(strQty);
                if (qty > 0)
                    return qty;
                else 
                    System.out.println("Qty must be more than 0");
            } else 
                System.out.print("Qty can not be letters \n");
    }while (!strQty.matches("-?\\d+") || qty <=0 );

    }
}

        