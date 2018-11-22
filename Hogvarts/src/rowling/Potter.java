package rowling;

public class Potter {

    private Potter(){}
    private static Potter innerPotter = new Potter();
    public static Potter getPotter(){
        return innerPotter;
    }

    private int spellsCast;
    public void castSpell(){
        spellsCast++;
        System.out.println("Avadekedavra");
    }
    public int getSpellsCast(){
        return spellsCast;
    }
}
