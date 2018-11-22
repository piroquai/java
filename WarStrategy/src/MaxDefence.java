public class MaxDefence extends Strategy{
    @Override
    public void fight() {
        holdPositions();
        fireArrows();
    }

    private void fireArrows() {
        System.out.println("Fire Arrows!");
    }

    private void holdPositions() {
        System.out.println("Ни шагу назад!");
    }
}
