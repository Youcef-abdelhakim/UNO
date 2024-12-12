package defaultPackage;

import java.util.ArrayList;
import java.util.List;


public class Player {

	private String name;
	private List<Card> hand = new ArrayList<>();

	// *******************************
	// it's preferable if we use sters;

	// public Player(String name) {
	// 	this.name = name;
    //     this.hand = new ArrayList<>();
	// }


		


	public void setName(String name) {
		this.name = name;
	}
	
	public void AddCard(Card card) {
		hand.add(card);
	}

	public void removeFromHand(int index) {
		hand.remove(index);
	}

	public void drawCard(Card newCard) {
		hand.add(newCard);
	}

	public String GetName() {
		return name;
	}

	public List<Card> getHand() {
		return hand;
	}

	// *******************
	// this function sshould be in the human player class;
	
	// public Card putCard(int index) {
		
	// 	Scanner scan = new Scanner(System.in);
		
	// 	while(index <= 0 || index > hand.size()) {
	// 		System.out.println("enter a valid card position");
	// 		index = scan.nextInt();
	// 	}

	// 	scan.close();
	
    //     return hand.remove(index);
    // }
	
	// public void GetHand() {
	// 	System.out.println(hand);
	// }
	
}
