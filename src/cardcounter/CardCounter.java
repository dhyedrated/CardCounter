/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardcounter;

import hye.unicycles.game.*;
import hye.unicycles.interfaces.*;
import java.util.*;
import java.io.*;


/**
 *
 * @author David
 */
public class CardCounter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck myDeck =null;//= new PokerDeck();
        Console console = System.console();
        String action ;
        

        do {
            
            System.out.println("Select an operation: ");
            if (myDeck != null) {
                System.out.println("\t (S)huffle "
                    + "\n\r\t (D)eal another card");
            }
            System.out.println("\t (N)ew deck"        
                + "\n\r\t (Q)uit");
            action  = console.readLine().toUpperCase();
           
            
            switch(action) {
                case "D":
                {
                    if (myDeck != null) {
                        Card current = myDeck.dealOneCard();
                        if (current != null) {
                            System.out.println(current.flipOver());
                        } else {
                            System.out.println("Deck is empty.");
                        }
                    }
                    break;
                }
                case "S":
                {                    
                    if (myDeck != null) {
                        myDeck.shuffle();
                        System.out.println("Deck is shuffled");
                    }
                    break;
                }
                case "N":
                {                    
                    myDeck = requestNewDeck(console);
                    if (myDeck != null ) {
                        System.out.println("Brand new poker deck");
                    }
                    break;
                }
                case "Q":
                {                    
                    System.out.println("GoodBye");     
                    break;
                }
                default:
                {                    
                    System.out.println("Invalid selection."); 
                }
            }
          
        }while(!action.equalsIgnoreCase("Q"));
    }
    
    static Deck requestNewDeck(Console console) 
    {    
        String action = null;
        do {            
            System.out.println("Select Deck type: ");
            ArrayList<String> menu = DeckFactory.getInstance().getMenu();
            for(String choice : menu) {
                System.out.println("\t" + choice);
            }
            System.out.println("\t or (Q)uit");
            action  = console.readLine().toUpperCase();
            switch(action) { 
                case "Q":
                    return null;
                default: 
                {
                    if (!menu.contains(action)) {                        
                        System.out.println("Invalid selection."); 
                    } else {
                        return DeckFactory.getInstance().create(action);
                    }
                            
                }
            } 
        }while(action.equalsIgnoreCase("Q"));
        return null;
    }
}


