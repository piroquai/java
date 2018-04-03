import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("AAAAAAAA");
        Pane root = new Pane();
        btn.setLayoutX(250);
        btn.setLayoutY(220);
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();


        primaryStage.setTitle("Count me");
//        RadioButton radio =new RadioButton("On/Off");
        Button button = new Button("Click me");
        StackPane pane = new StackPane();
        pane.getChildren().add(button);
//        pane.getChildren().add(radio);

        primaryStage.setScene(new Scene(pane, 300, 400));

        button.setOnAction(new EventHandler<ActionEvent>() {
            int count = 0;
            @Override
            public void handle(ActionEvent event) {
                button.setText(""+ count++);
            }
        });
        primaryStage.show();

    }
}
