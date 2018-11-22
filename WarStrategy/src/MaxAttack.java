public class MaxAttack extends Strategy{
    @Override
    public void fight() {
        pushForward();
        surroundEnemy();

    }

    private void surroundEnemy() {
        System.out.println("Окружай!");
    }

    private void pushForward() {

        System.out.println("В атаку!");}
}
