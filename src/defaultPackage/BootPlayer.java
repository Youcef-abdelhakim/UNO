package defaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BootPlayer extends Player{

    public Card putCard(Card curentCard) {
        Card choosenCard;

        //removed the unecessary redeclarring hand 
        List<Card> hand = getHand();
        List<Card> rightCards = new ArrayList<>();

        //***************************************************/
        //********remove this variable*********
        // rightCardsCounter = 0 ;
        //***************************************************/

        //***************************************************/
        //********useless variables declaration needs to be removed***********
        //Card.Color curentColor = curentCard.getColor();
        //Card.Value curValue    = curentCard.getValue();
        //***************************************************/

        for(int i = 0; i < hand.size(); i++ ) {
            Card card = hand.get(i);
            if( card.getColor() == curentCard.getColor() || 
                card.getValue() == curentCard.getValue() ||
                card.getValue() == Card.Value.Wild ||
                card.getValue() == Card.Value.Wild_Four ) {
                
                rightCards.add(card);
                
                //rightCardsCounter isnt needed we can use rightCards.size();
                //rightCardsCounter ++;

            }
        }

        //****we have to do an if statment for it to in case of a weird behaviour from the program*****
        if(rightCards.isEmpty()){
            return null;
        }

        Random random = new Random();
        int choosenCardIndex = random.nextInt(rightCards.size());
        choosenCard = rightCards.get(choosenCardIndex);

        return choosenCard;
        // if boot dont have a valid card that is mean that the function will return null and that s mean that 
        // we should have a check if it is null the boot add card to his hand pass;
        
    }

    
}