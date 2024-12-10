package mainPackage;
import java.util.Random;

import mainPackage.Card.Color;
import mainPackage.Card.Value;
public class Deck {
    //is multiple of distinct cards
    //we have to create cards that are diffirent from each others and put it in an array

    private Card[] cards = new Card[108];
    private int CardInDeck;

    

    public Deck() {
        Random random = new Random();
        Card card ;

        for(int i=0; i<=3; i++) {
            int randomIndex ;
            do {
                randomIndex = random.nextInt(109);
            } while(cards[randomIndex] != null);

            cards[randomIndex] = new Card(Color.getColors(i), Value.getValue(0));
            // Here we did the case of 0 value manualy becasue eache colore have one card of 0 value; 
            // *******************************************
            // *******************************************

            for(int j=1; j <= 9; j++) {

            } 
        }
    }

    
}


// recupirer les couleures;
//      eache colore take 1 zero and 2 of eache number either the special roles;
