
public class Battle {
    public static void main(String[] args) {
        Army russianArmy = new Army();
        Strategy strategy = new MaxAttack();
        russianArmy.changeStrategy(strategy);
        russianArmy.startBattle();
        Strategy strategy1 = new MaxDefence();
        russianArmy.changeStrategy(strategy1);
        russianArmy.startBattle();

    }
}
