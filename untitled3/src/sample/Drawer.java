package sample;

import javafx.scene.canvas.GraphicsContext;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

public class Drawer {

    double y;
    ResizableCanvas canvas;
    Argument x;
    GraphicsContext gc;
    Expression e;
    public void graphDrawer(){
        for (double z = 0; z < canvas.getWidth(); z++) {
            x.setArgumentValue(z);

//            System.out.println("z" + z*100);
//            System.out.println("x" + x);
////                        System.out.println();
            gc.lineTo(z*100, canvas.getHeight()-e.calculate()*100);
        }
        gc.stroke();

    }
}
