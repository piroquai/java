public class ChocoFactory {
    public static Chocolate produceChocolate(int weight, String type) {
        if (type == "White") return new WhiteChocolate(weight);
        if (type == "Milk") return new MilkChocolate(weight);
        if (type == "Bitter") return new BitterChocolate(weight);
        else return null;
    }
}
