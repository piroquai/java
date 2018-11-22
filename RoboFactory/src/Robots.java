public class Robots {
    private int quantity;

    public Robots(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Robots{" +
                "class="+ this.getClass().getSimpleName()+
                " quantity=" + quantity +
                '}';
    }
}
