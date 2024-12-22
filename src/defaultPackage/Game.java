package defaultPackage;

import java.util.*;

public class Game {

    private List<Player> competitors;
    private int numberOfCompetitors;
    private Deck gameDeck;
    private Player winner;
    private Card lastCard;
    private int drawCardsCount = 0; // track the number of cards to be drawn

    // constructor
    public Game() {
        competitors = new ArrayList<>();
        setupGame();
    }

    public void setupGame() {
        Scanner scanner = new Scanner(System.in);

        // input number of players
        do {
            System.out.println("Enter the total number of competitors (2-4): ");
            numberOfCompetitors = scanner.nextInt();
            scanner.nextLine();
        } while (numberOfCompetitors < 2 || numberOfCompetitors > 4);

        // input number of human players
        int numberHumans;
        do {
            System.out.println("Enter the number of human competitors (1-" + numberOfCompetitors + "): ");
            numberHumans = scanner.nextInt();
            scanner.nextLine();
        } while (numberHumans < 1 || numberHumans > numberOfCompetitors);

        // add human players
        for (int i = 0; i < numberHumans; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            Player human = new Player();
            human.setName(name);
            competitors.add(human);
        }

        // add bot players if needed
        for (int i = 0; i < numberOfCompetitors - numberHumans; i++) {
            String[] botNames = {"Bot_Mike", "Bot_Jake", "Bot_Leo", "Bot_Alex"};
            Player bot = new BootPlayer();
            bot.setName(botNames[i]);
            competitors.add(bot);
        }

        // initialize the deck and shuffle
        gameDeck = new Deck();

        // Distribute cards
        for (Player player : competitors) {
            for (int i = 0; i < 7; i++) {
                player.addCard(gameDeck.popCard());
            }
        }

        // set the starting card
        do {
            lastCard = gameDeck.popCard();
        } while (lastCard.getColor() == Card.Color.Wild); // ensure first card is not a wild card

        // shuffle player order
        Collections.shuffle(competitors);

        System.out.println("Game setup complete. Starting the game!");
    }

    public void playGame() {
        while (winner == null) {
            for (Player player : competitors) {
                System.out.println("\n" + player.getName() + "'s turn.");
                System.out.println("Last card on the pile: " + lastCard);
                System.out.println("Your hand: " + player.getHand());

                // handle the effect of drawing cards, if applicable
                if (drawCardsCount > 0) {
                    for (int i = 0; i < drawCardsCount; i++) {
                        player.addCard(gameDeck.popCard());
                    }
                    System.out.println(player.getName() + " drew " + drawCardsCount + " cards.");
                    drawCardsCount = 0;
                    continue; // skip this player's turn
                }

                if (player instanceof BootPlayer) {
                    botPlay((BootPlayer) player);
                } else {
                    humanPlay(player);
                }

                // check if the player has won
                if (player.getHand().isEmpty()) {
                    winner = player;
                    System.out.println("\ud83c\udf89 " + player.getName() + " wins the game! \ud83c\udf89");
                    return;
                }
            }
        }
    }

    private void humanPlay(Player player) {
        Scanner scanner = new Scanner(System.in);
        List<Card> hand = player.getHand();

        while (true) {
            System.out.println("Enter the card index to play (0-" + (hand.size() - 1) + "), or -1 to draw: ");
            int choice = scanner.nextInt();

            if (choice == -1) {
                player.addCard(gameDeck.popCard());
                System.out.println("You drew a card.");
                break;
            } else if (choice >= 0 && choice < hand.size()) {
                Card selectedCard = hand.get(choice);
                if (isValidCard(selectedCard)) {
                    lastCard = player.removeFromHand(choice);
                    System.out.println("You played: " + selectedCard);

                    // handle wild card effects
                    if (selectedCard.getColor() == Card.Color.Wild && selectedCard.isDrawFour()) {
                        drawCardsCount = 4;
                        System.out.println("Next player must draw 4 cards!");
                    }

                    break;
                } else {
                    System.out.println("Invalid card! Choose a card that matches the color or value.");
                }
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void botPlay(BootPlayer bot) {
        List<Card> hand = bot.getHand();
        for (int i = 0; i < hand.size(); i++) {
            if (isValidCard(hand.get(i))) {
                lastCard = bot.removeFromHand(i);
                System.out.println(bot.getName() + " played: " + lastCard);

                // handle wild card effects
                if (lastCard.getColor() == Card.Color.Wild && lastCard.isDrawFour()) {
                    drawCardsCount = 4;
                    System.out.println("Next player must draw 4 cards!");
                }

                return;
            }
        }

        // if no valid card, draw
        bot.addCard(gameDeck.popCard());
        System.out.println(bot.getName() + " drew a card.");
    }

    private boolean isValidCard(Card card) {
        return card.getColor() == lastCard.getColor() || card.getValue() == lastCard.getValue() ||
               card.getColor() == Card.Color.Wild;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
