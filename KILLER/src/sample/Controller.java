package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

public class Controller {


    private EditorModel model;
    public Controller (EditorModel model) {
        this.model = model;
    }
    private TextFile currentTextFile;

    @FXML
    private TextArea areaText;


    @FXML
    private void closeProgram() {
        model.close();
    }
    @FXML
    private void onSave() {
        TextFile textFile = new TextFile(currentTextFile.getFile(), Arrays.asList(areaText.getText().split("\n")));
        model.save(textFile);
    }

    @FXML
    private void onLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            IOResult<TextFile> io = model.load(file.toPath());

            if (io.isOk() && io.hasData()) {
                currentTextFile = io.getData();

                areaText.clear();
                currentTextFile.getContent().forEach(line -> areaText.appendText(line + "\n"));
            } else {
                System.out.println("Failed");
            }
        }
    }

}
