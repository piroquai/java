public class Chocolate {
    public int weight;

    public Chocolate(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Chocolate{" +
                "class="+ this.getClass().getSimpleName()+
                " weight=" + weight +
                '}';
    }
}
