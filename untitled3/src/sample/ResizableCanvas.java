package sample;


    import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
    import org.mariuszgromada.math.mxparser.Argument;
    import org.mariuszgromada.math.mxparser.Expression;

/**
     * Tip 1: A canvas resizing itself to the size of
     *        the parent pane.
     */
//    public static class Graph extends Application {

     public class ResizableCanvas extends Canvas {

            public ResizableCanvas() {
                // Redraw canvas when size changes.
                widthProperty().addListener(evt -> draw());
                heightProperty().addListener(evt -> draw());

            }

            private void draw() {
                double width = getWidth();
                double height = getHeight();


                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, width, height);
                gc.strokeText("100",100+width/2,-10+height/2);
                gc.strokeText("-100",-100+width/2,-10+height/2);
                gc.strokeText("100",width/2,100+height/2);
                gc.strokeText("-100",width/2,-100+height/2);
                gc.setStroke(Color.RED);
                gc.strokeLine(0, height/2, width, height/2);
                gc.strokeLine(width/2, height, width/2, 0);

            }

            @Override
            public boolean isResizable() {
                return true;
            }

            @Override
            public double prefWidth(double width) {
                return getWidth();
            }

            @Override
            public double prefHeight(double height) {
                return getHeight();
            }
        }

//    }

