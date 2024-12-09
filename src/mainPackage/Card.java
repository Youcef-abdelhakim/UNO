package mainPackage;

public class Card {
    private enum  Colors{
        BLUE, YELLOW, RED, GREEN, WILD;
    }

    private Colors color;

    public Card(Colors color){
        this.color = color;
    }


}
