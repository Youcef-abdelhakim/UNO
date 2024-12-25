package defaultPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    private List<Player> competitors;
    private Deck gameDeck;
    private Card lastCard;
    private Player winner;

    public Game() {
        competitors = new ArrayList<>();
        setupGame();
    }

    public void setupGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the total number of competitors (2-4): ");
        int numberOfCompetitors = scanner.nextInt();
        System.out.println("Enter the number of human players (1-" + numberOfCompetitors + "): ");
        int numberOfHumans = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfHumans; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            HumanPlayer human = new HumanPlayer();
            human.setName(name);
            competitors.add(human);
        }

        for (int i = 0; i < numberOfCompetitors - numberOfHumans; i++) {
            BootPlayer bot = new BootPlayer();
            bot.setName("Bot" + (i + 1));
            competitors.add(bot);
        }

        gameDeck = new Deck();
        for (Player player : competitors) {
            for (int j = 0; j < 7; j++) {
                player.addCard(gameDeck.popCard());
            }
        }

        do {
            lastCard = gameDeck.popCard();
        } while (lastCard.getValue() == Card.Value.DrawTwo);

        Collections.shuffle(competitors);
        System.out.println("Game setup complete. Starting the game!");
    }

    public void playGame() {
        while (winner == null) {
            for (int i = 0; i < competitors.size(); i++) {
                Player currentPlayer = competitors.get(i);
                System.out.println("\n" + currentPlayer.getName() + "'s turn.");
                Card playedCard = currentPlayer.putCard(lastCard, gameDeck);

                if (playedCard != null) {
                    lastCard = playedCard;

                    if (playedCard.getValue() == Card.Value.DrawTwo) {
                        int nextPlayerIndex = (i + 1) % competitors.size();
                        Player nextPlayer = competitors.get(nextPlayerIndex);
                        for (int j = 0; j < 2; j++) {
                            nextPlayer.addCard(gameDeck.popCard());
                        }
                        System.out.println(nextPlayer.getName() + " drew 2 cards.");
                    }

                    if (currentPlayer.getHand().isEmpty()) {
                        winner = currentPlayer;
                        System.out.println("\ud83c\udf89 " + currentPlayer.getName() + " wins the game! \ud83c\udf89");
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
