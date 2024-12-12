package defaultPackage;

import java.util.ArrayList;
import java.util.List;

public class BootPlayer extends Player{

    public Card putCard(Card curentCard) {
        Card choosenCard;
        List<Card> hand = new ArrayList<>();
        List<Card> rightCards = new ArrayList<>();
        int rightCardsCounter = 0 ;
        hand = getHand();

        Card.Color curentColor = curentCard.getColor();
        Card.Value curValue    = curentCard.getValue();

        for(int i = 0; i < hand.size(); i++ ) {
            Card card = hand.get(i);
            if(card.getColor() == curentCard.getColor() || 
              card.getValue == curentCard.getValue() ||
              card.getValue == Card.Value.Wild ||
              card.getValue == Card.Value.Wild_Four ) {
                
                rightCardsCounter ++;

            }
        }
        



        return
    }
}