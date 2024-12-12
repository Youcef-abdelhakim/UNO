package defaultPackage;
import java.util.Scanner;

public class HumanPlayer extends Player {
    


    public Card putCard(int index) {
        Scanner scan = new Scanner(System.in);
        Card choosenCard ;
        while (index < 0 || index > getHand().size()) {
            System.out.println("Enter a valide card position !");
            index = scan.nextInt();
        }
        scan.close();
        choosenCard = getHand().get(index);
        removeFromHand(index);
        return choosenCard;
        
        // it shoud be a check if the card is valid with the game's stack or no !
    }
}