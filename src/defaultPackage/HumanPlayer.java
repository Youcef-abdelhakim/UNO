package defaultPackage;
import java.util.Scanner;

public class HumanPlayer extends Player {
    
    @Override
    public void setName(String name) {
        
        super.setName(name);
    }

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
        // It should be a check of number or colore in the main game !

    }
}