package defaultPackage;


public class Card {

    enum Color {
        Red, Blue, Green, Yellow, Wild;

        private static Color[] colors = Color.values();

        public static Color getColors(int i) {
            return Color.colors[i];
        }
    }    


    enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, DrawTwo, Skip, Reverse, Wild, Wild_Four;

        public static Value[] values = Value.values();

        public static Value getValue(int i) {
            return Value.values[i];
        }
    }

    // *********Atributes of Card **************************


    private final  Color color;
    private final Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value;
    }

    public String toString() {
        return color + " " + value;
    }
}


