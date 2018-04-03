package sample;

import java.util.function.DoubleUnaryOperator;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;


public class Main extends Application {


//    int startX = 0;
//    int startY;
//    int height = 400;
//    int wigth = 400;
    Argument x = new Argument("x=0");

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Canvas canvas = new Canvas(600,600);
        pane.getChildren().add(canvas);
        primaryStage.setScene(new Scene(pane));
        pane.setPadding(new Insets(10));
        TextField functionInput = new TextField();
//        functionInput.setAlignment();
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        gc.strokeLine(0, canvas.getHeight()/2, canvas.getWidth(), canvas.getHeight()/2);
//        gc.strokeLine(canvas.getWidth()/2, canvas.getHeight(), canvas.getWidth()/2, 0);

//
//        pane.widthProperty().addListener((observable, oldValue, newValue) -> canvas.setWidth((double)newValue) );
//
//        pane.widthProperty().addListener((observable, oldValue, newValue) ->
//                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));
//        pane.heightProperty().addListener((observable, oldValue, newValue) -> canvas.setHeight((double)newValue) );
//
//        pane.heightProperty().addListener((observable, oldValue, newValue) ->
//                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));

////        plot.setLayoutX(c);
////        plot.setLayoutY(150);
        functionInput.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)) {
//                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    Expression e = new Expression(functionInput.getText(), x);
                    ResizableCanvas canvas = new ResizableCanvas();
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(canvas);

                    // Bind canvas size to stack pane size.
                    canvas.widthProperty().bind(
                            stackPane.widthProperty());
                    canvas.heightProperty().bind(
                            stackPane.heightProperty());
                    Stage newWindow = new Stage();
                    newWindow.setScene(new Scene(stackPane));
                    newWindow.setTitle("Graph");
                    newWindow.show();
                }
            }
        });

        BorderPane.setMargin(pane, new Insets(20));
        BorderPane root = new BorderPane(pane, functionInput, null, null, null);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}