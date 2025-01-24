package defaultPackage;

import defaultPackage.Card.Value;
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
        System.out.println(competitors);
        System.out.println("Game setup complete. Starting the game!");
    }

    public void playGame() {
        boolean plusTwo = false;
        int Acc = 0;
        boolean skipped = false;
        while (winner == null) {
            for (int i = 0; i < competitors.size(); i++) {
                Player currentPlayer = competitors.get(i);
                System.out.println("\n" + currentPlayer.getName() + "'s turn.");

                if (!skipped) {
                    if (plusTwo) {
                        Acc += 2;
                        if (currentPlayer instanceof HumanPlayer) {
                            if (currentPlayer.counter(Value.DrawTwo).size() != 0) {
                                System.out.println("Would you put your counter or draw two cards:\n[0]: choose from " + currentPlayer.counter(Value.DrawTwo) + "\n[other]: Draw two");
                                Scanner s = new Scanner(System.in);
                                char choice = s.nextLine().charAt(0);
                                int choicee;
                                if (choice == '0') {
                                    while (true) {
                                        System.out.println("Enter the index of the chosen card (0," + (currentPlayer.counter(Value.DrawTwo).size() - 1) + "):");
                                        choicee = s.nextInt();
                                        s.nextLine();
                                        if (choicee >= 0 && choicee < currentPlayer.counter(Value.DrawTwo).size()) {
                                            break;
                                        } else {
                                            System.out.println("Invalid index");
                                        }
                                    }
                                    lastCard = currentPlayer.counter(Value.DrawTwo).get(choicee);
                                    currentPlayer.removeFromHand(lastCard);
                                } else {
                                    for (int index = 0; index < Acc; index++) {
                                        currentPlayer.addCard(gameDeck.popCard());
                                    }
                                    plusTwo = false;
                                    System.out.println(currentPlayer.getName() + " drew"+Acc+" cards.");
                                }
                                s.close();
                            } else {
                                for (int index = 0; index < Acc; index++) {
                                    currentPlayer.addCard(gameDeck.popCard());
                                }
                                Acc = 0;
                                plusTwo = false;
                                System.out.println(currentPlayer.getName() + " drew "+Acc+" cards.");
                            }
                        } else if (currentPlayer instanceof BootPlayer) {
                            System.out.println(currentPlayer.getName() + " has drew " + Acc + " cards.");
                            for (int index = 0; index < Acc; index++) {
                                currentPlayer.addCard(gameDeck.popCard());
                            }
                            Acc = 0;
                            plusTwo = false;
                        }
                    } else {
                        Card playedCard = currentPlayer.putCard(lastCard, gameDeck);
                        if (playedCard != null) {
                            lastCard = playedCard;
                            if (playedCard.getValue() == Card.Value.DrawTwo) {
                                plusTwo = true;
                            } else if (playedCard.getValue() == Card.Value.Skip) {
                                skipped = true;
                            } else if (playedCard.getValue() == Card.Value.Reverse) {
                                reverseOrder(i);
                                break;
                            }
                            if (currentPlayer.getHand().isEmpty()) {
                                winner = currentPlayer;
                                System.out.println("\ud83c\udf89 " + currentPlayer.getName() + " wins the game! \ud83c\udf89");
                                return;
                            }
                        }
                    }
                } else {
                    System.out.println("Skipped");
                    skipped = false;
                }
            }
        }
    }

    public void reverseOrder(int i) {
        Player reverser = competitors.get(i);
        while (competitors.get(competitors.size() - 1).getName() != reverser.getName()) {
            competitors.add(0, competitors.remove(competitors.size() - 1));
        }
        Collections.reverse(competitors);
        competitors.add(0, competitors.remove(competitors.size() - 1));
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
