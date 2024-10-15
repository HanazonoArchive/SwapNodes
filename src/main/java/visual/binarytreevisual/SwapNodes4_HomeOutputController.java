package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SwapNodes4_HomeOutputController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Pane OutputMainPane;

    public void setOutputMainPane(Pane outputMainPane) {
        this.OutputMainPane = outputMainPane; // Assign the passed reference
    }

    @FXML
    private Pane LoadFileTreeButton;

    @FXML
    private Pane LoadManualTreeButton;

    @FXML
    private Pane OuputMainPane;

    @FXML
    private Canvas ResultCanvas;

    @FXML
    private Label nodesOutputLB;

    @FXML
    private Label timeTakenLB;

    @FXML
    void HandlesClicked(MouseEvent event) {

    }
}
