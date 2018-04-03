package sample;

import javafx.scene.chart.LineChart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    public int xMin, xMax;
    @FXML
    private NumberAxis XAxis;

    @FXML
    private NumberAxis YAxis;

    @FXML
    private LineChart<?, ?> Chart;


    @FXML
    private TextField xMinInput;

    @FXML
    private TextField xMaxInput;
    @FXML
    private TextField func_input;

    @FXML
    private Button ButtonShow;
    @FXML
    private Button buttonSetRange;

    @FXML
    public void setRange(ActionEvent event) {
//        xMin=
    }


    @FXML
    public void showFunc(ActionEvent event) {


    }
}
