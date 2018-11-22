import java.util.ArrayList;
import java.util.List;

public class BigHead extends DeadCold {
    public List<Child> children = new  ArrayList<>();



    @Override
    public void present(Child ch) {
        System.out.println("I regret. No present for you.");
        children.add(ch);
    }
    public void presentTrue() {

        for (Child ch1:children) {
                     System.out.println("Here's your present" + ch1.getName());
        }
    }


}
