package defaultPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {

	private String name;
	private List<Card> hand;
	
	public Player(String name) {
		this.name = name;
        this.hand = new ArrayList<>();
	}
	
	public void AddCard(Card card) {
		hand.add(card);
	}
	
	public Card putCard(int index) {
		
		Scanner scan = new Scanner(System.in);
		
		while(index <= 0 || index > hand.size()) {
			System.out.println("enter a valid card position");
			index = scan.nextInt();
		}

		scan.close();
	
        return hand.remove(index);
    }
	
	public void GetHand() {
		System.out.println(hand);
	}
	
	public String GetName() {
		return name;
	}
}
