package defaultPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Player> competitors;
    private int numberOfCompetitors;
    private Deck gameDeck;
    private Player winner;
    private Card lastCard;
    private boolean reverse;
    private Scanner scanner;
    private int accumulatedCards; // Track accumulated cards from Wild_Four
    private boolean isWildFourActive; // Track if there's an active Wild_Four chain

    public Game() {
        scanner = new Scanner(System.in);
        competitors = new ArrayList<>();
        accumulatedCards = 0;
        isWildFourActive = false;
        initializeGame();
        playGame();
        scanner.close();
    }

    private void initializeGame() {
        int number, numberHumans;

        // Determine number of players
        do {
            System.out.println("Enter the number of total competitors (2 to 4):");
            number = scanner.nextInt();
            scanner.nextLine();
        } while (number < 2 || number > 4);
        this.numberOfCompetitors = number;

        // Determine number of human players
        do {
            System.out.println("Enter the number of human competitors (remaining will be bots):");
            numberHumans = scanner.nextInt();
            scanner.nextLine();
        } while (numberHumans < 1 || numberHumans > number);

        // Add human players
        for (int i = 0; i < numberHumans; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ":");
            String name = scanner.nextLine();
            Player human = new Player();
            human.setName(name);
            competitors.add(human);
        }

        // Add bot players
        for (int i = 0; i < numberOfCompetitors - numberHumans; i++) {
            String[] botNames = {"Mike", "Jake", "Leo"};
            BootPlayer bot = new BootPlayer();
            bot.setName(botNames[i] + "_Bot");
            competitors.add(bot);
        }

        // Initialize deck and distribute cards
        gameDeck = new Deck();
        for (Player player : competitors) {
            for (int i = 0; i < 7; i++) {
                player.addCard(gameDeck.popCard());
            }
        }

        // Shuffle players
        Collections.shuffle(competitors);
        
        // Get first card (ensuring it's not a wild card)
        do {
            lastCard = gameDeck.popCard();
            if (lastCard.getColor() == Card.Color.Wild) {
                gameDeck.addCard(lastCard);
                Collections.shuffle(gameDeck.getCards());
            }
        } while (lastCard.getColor() == Card.Color.Wild);
        
        reverse = false;
        System.out.println("Starting card: " + lastCard);
    }

    private void playGame() {
        int currentIndex = 0;

        while (winner == null) {
            Player currentPlayer = competitors.get(currentIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn. Last card: " + lastCard);
            
            if (isWildFourActive) {
                System.out.println("Active Wild Draw Four chain! Accumulated cards: " + accumulatedCards);
            }

            if (currentPlayer instanceof BootPlayer) {
                playBotTurn((BootPlayer) currentPlayer);
            } else {
                playHumanTurn(currentPlayer);
            }

            // Check for winner
            if (currentPlayer.getHand().isEmpty()) {
                winner = currentPlayer;
                System.out.println("\n" + winner.getName() + " wins the game!");
                break;
            }

            // Determine next player
            currentIndex = getNextPlayerIndex(currentIndex);
        }
    }

    private void playHumanTurn(Player player) {
        boolean played = false;
        boolean mustRespondToWildFour = isWildFourActive;

        while (!played) {
            System.out.println("Your hand: " + player.getHand());
            
            if (mustRespondToWildFour) {
                if (hasWildFour(player)) {
                    System.out.println("You must play a Wild Draw Four or draw " + accumulatedCards + " cards!");
                    System.out.println("Choose a card to play (1-" + player.getHand().size() + ") or enter 0 to draw:");
                } else {
                    System.out.println("No Wild Draw Four available. Drawing " + accumulatedCards + " cards.");
                    drawAccumulatedCards(player);
                    break;
                }
            } else {
                System.out.println("Choose a card to play (1-" + player.getHand().size() + ") or enter 0 to draw:");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                if (mustRespondToWildFour) {
                    drawAccumulatedCards(player);
                } else {
                    player.addCard(gameDeck.popCard());
                    System.out.println("You drew a card.");
                }
                break;
            } else if (choice > 0 && choice <= player.getHand().size()) {
				Card selectedCard = player.getHand().get(choice - 1);
				if (mustRespondToWildFour) {
					if (selectedCard.getValue() == Card.Value.Wild_Four) {
						playCard(player, selectedCard);
						played = true;
					} else {
						System.out.println("You must play a Wild Draw Four or draw cards!");
						continue;
					}
				} else if (isPlayable(selectedCard)) {
					playCard(player, selectedCard);
					played = true;
				} else {
					System.out.println("You cannot play this card. Choose another.");
				}
			} else {
				System.out.println("Invalid choice.");
			}
        }
    }

	private void playBotTurn(BootPlayer bot) {
		if (isWildFourActive) {
			Card wildFourCard = findWildFourCard(bot);
			if (wildFourCard != null) {
				System.out.println(bot.getName() + " played " + wildFourCard);
				playCard(bot, wildFourCard);
			} else {
				System.out.println(bot.getName() + " cannot respond to Wild Four!");
				drawAccumulatedCards(bot);
			}
			return;
		}
	
		for (Card card : bot.getHand()) {
			if (isPlayable(card)) {
				System.out.println(bot.getName() + " played " + card);
				playCard(bot, card);
				return;
			}
		}
		bot.addCard(gameDeck.popCard());
		System.out.println(bot.getName() + " drew a card.");
	}

    private void playCard(Player player, Card card) {
		player.getHand().remove(card);
		lastCard = card;
	
		switch (card.getValue()) {
			case Wild_Four:
				handleWildFour(player);
				break;
			case Wild:
				chooseWildColor(player);
				isWildFourActive = false;  // Reset chain only for regular Wild cards
				accumulatedCards = 0;
				break;
			case Reverse:
				reverse = !reverse;
				isWildFourActive = false;  // Reset chain when non-Wild Four card is played
				accumulatedCards = 0;
				break;
			case Skip:
				skipNextPlayer();
				isWildFourActive = false;  // Reset chain when non-Wild Four card is played
				accumulatedCards = 0;
				break;
			case DrawTwo:
				handleDrawTwo(getNextPlayerIndex(competitors.indexOf(player)));
				isWildFourActive = false;  // Reset chain when non-Wild Four card is played
				accumulatedCards = 0;
				break;
			default:
				isWildFourActive = false;  // Reset chain when non-Wild Four card is played
				accumulatedCards = 0;
				break;
		}
	}

    private void handleDrawTwo(int playerIndex) {
        Player nextPlayer = competitors.get(playerIndex);
        boolean hasDrawTwo = false;
        
        // Check if next player has a Draw Two card
        for (Card card : nextPlayer.getHand()) {
            if (card.getValue() == Card.Value.DrawTwo) {
                hasDrawTwo = true;
                break;
            }
        }
        
        if (!hasDrawTwo) {
            drawCards(playerIndex, 2);
        }
    }

    private void handleWildFour(Player player) {
        chooseWildColor(player);
        isWildFourActive = true;
        accumulatedCards += 4;
        System.out.println("Wild Draw Four played! Accumulated cards: " + accumulatedCards);
    }
    private boolean isPlayable(Card card) {
        return card.getColor() == lastCard.getColor() || 
               card.getValue() == lastCard.getValue() || 
               card.getColor() == Card.Color.Wild;
    }

	private void drawAccumulatedCards(Player player) {
        System.out.println(player.getName() + " draws " + accumulatedCards + " cards!");
        for (int i = 0; i < accumulatedCards; i++) {
            player.addCard(gameDeck.popCard());
        }
        accumulatedCards = 0;
        isWildFourActive = false;
    }

	private boolean hasWildFour(Player player) {
        return findWildFourCard(player) != null;
    }

    private void drawCards(int playerIndex, int count) {
        Player player = competitors.get(playerIndex);
        for (int i = 0; i < count; i++) {
            player.addCard(gameDeck.popCard());
        }
        System.out.println(player.getName() + " drew " + count + " cards.");
    }

    private void skipNextPlayer() {
        System.out.println("Next player is skipped!");
    }

	private Card findWildFourCard(Player player) {
        for (Card card : player.getHand()) {
            if (card.getValue() == Card.Value.Wild_Four) {
                return card;
            }
        }
        return null;
    }

    private void chooseWildColor(Player player) {
        System.out.println(player.getName() + ", choose a color (Red, Blue, Green, Yellow):");
        String colorChoice = scanner.nextLine();
        lastCard = new Card(Card.Color.valueOf(colorChoice), lastCard.getValue());
    }

    private int getNextPlayerIndex(int currentIndex) {
        int step = reverse ? -1 : 1;
        return (currentIndex + step + competitors.size()) % competitors.size();
    }

    public static void main(String[] args) {
        new Game();
    }
}