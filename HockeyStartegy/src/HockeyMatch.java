public class HockeyMatch
{
    public static void main(String[] args) {
        Team nationalKoreaTeam =new Team();
        Strategy strategy = new MaxDefence();
        nationalKoreaTeam.changeStrategy(strategy);
        nationalKoreaTeam.playGame();
        System.out.println("----------");
        Strategy strategy2 = new PlayLikePutin();
        nationalKoreaTeam.changeStrategy(strategy2);
        nationalKoreaTeam.playGame();

    }
}
