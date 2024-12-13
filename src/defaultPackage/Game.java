package defaultPackage;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Game {
	//first we have to know how many players there are


	private List<Player> Competetors;
	private int numberOfCompetetors;
	

	//then constructing the players by the input giving us their names 
	public Game(Deck deck) {
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




	}
	public List<Player> getList(){
		return Competetors;
	}

	
	//give every one of them a card seven times 
	//the game start with the first player who put his names + to add the times of people that are playing 
	//inside a while with condition "no winner" keep the game playing with the for loop of how many players
	//the players should be in a list so it would be easy to remove players from it "if we continue for the second winner"
	//
	public static void main(String[] args) {
		//for test
		Deck deck = new Deck();
		Game game = new Game(deck);
		System.out.println(game.getList());
	}

}
