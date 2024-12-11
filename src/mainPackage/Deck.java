package mainPackage;
import java.util.Random;

import mainPackage.Card.Color;
import mainPackage.Card.Value;


public class Deck {
    //is multiple of distinct cards
    //we have to create cards that are diffirent from each others and put it in an array

    private static Card[] cards = new Card[108];
    
    
        public static void main(){
            for(int i=0; i<10; i++){
            System.out.println(cards[i]);
        }
    }

    public Deck() {

        for(int i=0; i<=3; i++) {

            int j = RandomIndex();

            cards[j] = new Card(Color.getColors(i), Value.getValue(0));
            // Here we did the case of 0 value manualy becasue eache colore have one card of 0 value; 
            // *******************************************
            // *******************************************

            for(int l=1; l <= 9; l++) {
                for(int f=0; f<2; f++){
                int k = RandomIndex();
                cards[k] = new Card(Color.getColors(i), Value.getValue(l));
                }
            } 
        }
    }


    int RandomIndex(){
        Random random = new Random();
        int i;
        do {
            i = random.nextInt(109);
        } while(cards[i] != null);
        return i;
    }

    
}


// recupirer les couleures;
//      eache colore take 1 zero and 2 of eache number either the special roles;
