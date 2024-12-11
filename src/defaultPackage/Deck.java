package defaultPackage;
import java.util.Random;

import defaultPackage.Card.Color;
import defaultPackage.Card.Value;


public class Deck {
    //is multiple of distinct cards
    //we have to create cards that are diffirent from each others and put it in an array

    private Card[] cards = new Card[108];
    
    
    public static void main(String[] args){
    	
    	Deck deck = new Deck();
        for(int i=0; i<100; i++) {
    	System.out.println(deck.cards[i]);
        }
    }
    

    public Deck() {

        for(int i=0; i<=3; i++) {

            int j = RandomIndex();

            cards[j] = new Card(Color.getColors(i), Value.getValue(0));
            // Here we did the case of 0 value manualy becasue eache colore have one card of 0 value; 

            for(int l=1; l <= 12; l++) {
                for(int f=0; f<2; f++){
                int k = RandomIndex();
                cards[k] = new Card(Color.getColors(i), Value.getValue(l));
                }
            } 
        }
        
        int i = 4;
        
        for(int l=13; l <= 14; l++) {
            for(int f=0; f<4; f++){
            int k = RandomIndex();
            cards[k] = new Card(Color.getColors(i), Value.getValue(l));
            }
        }
    }


    int RandomIndex(){
        Random random = new Random();
        int i;
        do {
            i = random.nextInt(108);
        } while(cards[i] != null);
        return i;
    }

    public Card GetCard(int i) {
    	return cards[i];
    }
}


// recupirer les couleures;
//      eache colore take 1 zero and 2 of eache number either the special roles;
