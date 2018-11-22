public class Army {
    private Strategy currentStrategy= null;
    public void startBattle(){
        currentStrategy.fight();
    }
    public void changeStrategy(Strategy s){
        this.currentStrategy = s;
    }
}
