public class Team {
    private Strategy currentStrategy = null;
    public void playGame(){
        currentStrategy.play();
    }
    public void changeStrategy(Strategy s){
        this.currentStrategy = s;
    }
}
