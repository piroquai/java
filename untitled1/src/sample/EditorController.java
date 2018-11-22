package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class EditorController {

    @FXML
    private TextArea areaText;

    private TextFile currentTextFile;

    private EditorModel model;

    public EditorController(EditorModel model) {
        this.model = model;
    }

    @FXML
    private void onSaveas() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File fileSave = fileChooser.showSaveDialog(null);
//        fileChooser.setSelectedExtensionFilter();

        try (FileWriter writer = new FileWriter(new File(String.valueOf(fileSave)), false)){
            writer.write(areaText.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    @FXML
    private void onClose() {
        model.close();
    }

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("This is NotepadKiller v 1.0");
        alert.show();
    }
}
