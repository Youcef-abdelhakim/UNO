package defaultPackage;
import defaultPackage.Card.Color;
import defaultPackage.Card.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Deck {
    //is multiple of distinct cards
    //we have to create cards that are diffirent from each others and put it in an array

    private List<Card> cards = new ArrayList<>();
    

    public Deck() {
        Card[] cards = new Card[108];
        for(int i=0; i<=3; i++) {

            int j = RandomIndex(cards);

            cards[j] = new Card(Color.getColors(i), Value.getValue(0));
            // Here we did the case of 0 value manualy becasue eache colore have one card of 0 value; 

            for(int l=1; l <= 12; l++) {
                for(int f=0; f<2; f++){
                int k = RandomIndex(cards);
                cards[k] = new Card(Color.getColors(i), Value.getValue(l));
                }
            } 
        }
        
        int i = 4;
        
        for(int l=13; l <= 14; l++) {
            for(int f=0; f<4; f++){
            int k = RandomIndex(cards);
            cards[k] = new Card(Color.getColors(i), Value.getValue(l));
            }
        }
        for (Card card : cards) {
            this.cards.add(card);
        }
    }


    int RandomIndex(Card[] cards){
        Random random = new Random();
        int i;
        do {
            i = random.nextInt(108);
        } while(cards[i] != null);
        return i;
    }

    public Card GetCard(int i) {
    	return cards.get(i);
    }

    public List<Card> getCards() {

        return cards;

    }
    
    int numofCards(){
        return cards.size();
    }

    public void addCard(Card card) {

        cards.add(card);

    }

    Card popCard(){
        Card removedCard = cards.remove((cards.size())-1);
        return removedCard;
    }
}


// recupirer les couleures;
//      eache colore take 1 zero and 2 of eache number either the special roles;

// recupirer les couleures;
//      eache colore take 1 zero and 2 of eache number either the special roles;
