package rowling;

public class Test {
    public static void main(String[] args) {
        Potter potter = Potter.getPotter();
        potter.castSpell();
        System.out.println(potter.getSpellsCast());
    }
}
