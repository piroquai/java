package lord;

public class Gorlum {
    private Gorlum(){}

    private static Gorlum innerGorlum = new Gorlum();
    public static Gorlum getGorlum(){
        return innerGorlum;
    }

    private int spidersEaten;
    public void eatSpider(){
        spidersEaten++;
        System.out.println("ksdjfdslkfh");
    }
    public int getSpiders(){
        return spidersEaten;
    }
}
