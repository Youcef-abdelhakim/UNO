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
        // if humanplayer dont have a valid card in his hand he should add a card to his hand and pass
        // also we use the function addTohand if there is a special card or draw 2 or wild-four

    }
}