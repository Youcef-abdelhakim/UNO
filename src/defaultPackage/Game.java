package defaultPackage;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {

	//first we have to know how many players there are

	private List<Player> Competetors;
	private int numberOfCompetetors;
	private Deck GameDeck;
	private Player winner;
	private Card lastCard;

	//setters and getters
	public List<Player> getCompetetors() {
		return this.Competetors;
	  }
	public void setCompetetors(List<Player> value) {
		this.Competetors = value;
	  }
  
	public int getNumberOfCompetetors() {
		return this.numberOfCompetetors;
	  }
	public void setNumberOfCompetetors(int value) {
		this.numberOfCompetetors = value;
	  }
  
	public Deck getGameDeck() {
		return this.GameDeck;
	  }
	public void setGameDeck(Deck value) {
		this.GameDeck = value;
	  }
  
	public Player getWinner() {
		return this.winner;
	  }
	public void setWinner(Player value) {
		this.winner = value;
	  }
	
	public Card getLastCard() {
		return this.lastCard;
	  }
	public void setLastCard(Card value) {
		this.lastCard = value;
	  }
	  //then constructing the players by the input giving us their names 
	public Game() {
		//Ayoub
		int number,numberHumans;
		Competetors = new ArrayList<>();
		//Enter the total number of players

		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Enter the number of the total competetors");
			number = scan.nextInt();
			scan.nextLine();
		} while (number<2 || number>4);
		this.numberOfCompetetors=number;

		//know how many human will play

		do {
			System.out.println("Enter the number of the human competetors(any diffefence between total and human number will be considered as bots)");
			numberHumans = scan.nextInt();
			scan.nextLine();
		} while (numberHumans<1 || numberHumans>number);

		//inserting the human players

		for (int index = 0; index < numberHumans; index++) {
			System.out.println("Enter the name of player n'"+(index+1));
			String name = scan.nextLine();
			Player human = new Player();
			human.setName(name);
			Competetors.add(human);
		}
		scan.close();

		//inseting the bots

		for (int index = 0; index < numberOfCompetetors-numberHumans; index++) {
			String[] botNames = {"Mike","Jake","Leo"};
			BootPlayer bot = new BootPlayer();
			bot.setName(botNames[index]+"_Bot");
			Competetors.add(bot);
		}

		//create the deck "not yet"
		GameDeck = new Deck();

		//distributions of cards
		//give every one of them a card seven times 

		for (Player player : Competetors) {
			for (int i = 0; i < 7; i++) {
				player.AddCard(GameDeck.PopCard());
			}
		}
		
		//shuffle the players to determine how is going to start
		Collections.shuffle(Competetors);
		//initialazing the play
		lastCard = GameDeck.PopCard();
	}
	public void Round(Card lastCard){
	



	}
	
	public static void main(String[] args) {
		
	}

}
