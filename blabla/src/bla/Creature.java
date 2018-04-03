package bla;

public class Creature {
    private int velocity;
    private int energy;

    public Creature(int velocity, int energy) {
        this.velocity = velocity;
        this.energy = energy;
    }

    public Creature (){}
    void attack()
    {
        System.out.println("Sudden attack.with velocity " + velocity + "and energy" + energy);
        energy--;
    }
}
