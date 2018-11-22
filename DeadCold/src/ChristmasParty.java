public class ChristmasParty {
    public static void main(String[] args) {
        DeadCold deadCold = new BigHead();

        Child vasya = new Child("Vasya");
        Child donald = new Child("Donald");
        Child kondalissa = new Child("Kondalissa");
        deadCold.present(vasya);
        deadCold.present(donald);
        deadCold.present(kondalissa);
        ((BigHead)deadCold).presentTrue();

    }
}
