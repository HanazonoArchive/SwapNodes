package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SwapNodes5_HomeResultController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private TextArea FileIterationTextArea;

    @FXML
    private Label FileSampleSizeLB;

    @FXML
    private Label FileTimeTakenLB;

    @FXML
    private TextArea ManualIterationTextArea;

    @FXML
    private Label ManualSampleSizeLB;

    @FXML
    private Label ManualTimeTakenLB;

    @FXML
    private Pane OuputMainPane;

    @FXML
    private Pane ResultLoadButton;

    @FXML
    void HandlesClicked(MouseEvent event) {
        if (event.getSource() == ResultLoadButton) {
            AppData appData = AppData.getInstance();
            ManualIterationTextArea.setText(appData.ManualIterrationTextAreaResult);
            ManualTimeTakenLB.setText(String.valueOf(appData.ManualtimerNanoseconds) + " Nanoseconds");
            ManualSampleSizeLB.setText("|" + appData.ManualpairCount + "|");

            FileIterationTextArea.setText(appData.FileIterrationTextAreaResult);
            FileTimeTakenLB.setText(String.valueOf(appData.FiletimerNanoseconds) + " Nanoseconds");
            FileSampleSizeLB.setText("|" + appData.FilepairCount + "|");
        }
    }
}
