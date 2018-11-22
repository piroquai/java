public class MaxDefence extends Strategy {
    @Override
    public void play() {
        moveAllPlayersToGate();
        defend();
        fight();
    }
    private  void fight(){
        System.out.println("Start fight with enemy");
    }
    private void defend(){
        System.out.println("Every player defends");
    }
    private void moveAllPlayersToGate(){
        System.out.println("All to our gate");
    }
}
