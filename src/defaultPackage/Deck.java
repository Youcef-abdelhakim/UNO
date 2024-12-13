package defaultPackage;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import defaultPackage.Card.Color;
import defaultPackage.Card.Value;


public class Deck {
    //is multiple of distinct cards
    //we have to create cards that are diffirent from each others and put it in an array

    //Ayoub:Charnge the type (Array=>list)
    private List<Card> cards = new ArrayList<>();
    
    public Deck() {
        //Ayoub: Adjusting the function
        for(int i=0; i<=3; i++) {

            int j = RandomIndex();

            Card card = new Card(Color.getColors(i), Value.getValue(0));
            cards.add(j,card);
            // Here we did the case of 0 value manualy becasue eache colore have one card of 0 value; 

            for(int l=1; l <= 12; l++) {
                for(int f=0; f<2; f++){
                int k = RandomIndex();
                card = new Card(Color.getColors(i), Value.getValue(0));
                cards.add(k,card);                
                }
            } 
        }
        
        int i = 4;
        
        for(int l=13; l <= 14; l++) {
            for(int f=0; f<4; f++){
            int k = RandomIndex();
            Card card = new Card(Color.getColors(i), Value.getValue(0));
            cards.add(k,card);            
            }
        }
    }

    //Ayoub: Set This function to private to prevent it being used outside
    private int RandomIndex(){
        Random random = new Random();
        int i;
        do {
            i = random.nextInt(108);
        } while(cards.get(i) != null);
        return i;
    }

    public Card GetCard(int i) {
    	return cards.get(i);
    }
    

}


// recupirer les couleures;
//      eache colore take 1 zero and 2 of eache number either the special roles;
