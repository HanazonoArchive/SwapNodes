package visual.binarytreevisual;

import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SwapNodes3_HomeInputController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Pane InputMainPane;

    @FXML
    private Pane ManualGenerateTreeButton;

    @FXML
    private Pane OpenFileButton;

    @FXML
    private Pane OpenFileGenerateTreeButton;

    @FXML
    void HandlesButtonClicked(MouseEvent event) {
        if(event.getSource() == ManualGenerateTreeButton){
            //Methods When Manual Generate Tree Button is Clicked!
        } else if (event.getSource() == OpenFileGenerateTreeButton){
            //Methods When Open File Generate Tree Button is Clicked!
        } else if (event.getSource() == OpenFileButton){
            //Methods When Open File is Clicked!
        }
    }
}
