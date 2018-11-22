public class RoboFactory {
    public static Robots produceRobots(int quantity, String type) {
        if (type == "Big") return new BigRobot(quantity);
        if (type == "Medium") return new MediumRobot(quantity);
        if (type == "Little") return new LittleRobot(quantity);
        else return null;
    }
}