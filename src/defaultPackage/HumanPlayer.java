package defaultPackage;

import java.util.Scanner;

public class HumanPlayer extends Player {

    @Override
    public Card putCard(Card lastCard, Deck gameDeck) {
        Scanner scanner = new Scanner(System.in);
        Card selectedCard = null;
        while (true) {
            System.out.println("Last card on the pile: " + lastCard);
            System.out.println("Your hand: " + getHand());
            System.out.print("Enter the card index to play (0-" + (getHand().size() - 1) + "), or -1 to draw: ");
            int choice = scanner.nextInt();

            if (choice == -1) {
                Card newCard = gameDeck.popCard();
                addCard(newCard);
                System.out.println("You drew: " + newCard);
                return null; // skip turn after drawing
            } else if (choice >= 0 && choice < getHand().size()) {
                Card card = getHand().get(choice);
                if (isValidCard(card, lastCard)) {
                    selectedCard = removeFromHand(choice);
                    System.out.println("You played: " + selectedCard);
                    break;
                } else {
                    System.out.println("Invalid card! Choose a card that matches the color or value.");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
        return selectedCard;
    }
}
