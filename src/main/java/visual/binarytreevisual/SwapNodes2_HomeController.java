package visual.binarytreevisual;

import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class SwapNodes2_HomeController {
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Pane ChangePane;

    @FXML
    private Pane CloseButton;

    @FXML
    private Pane InputButton;

    @FXML
    private Pane OuputButton;

    @FXML
    private Pane ResultButton;

    @FXML
    void HandlesClickedButton(MouseEvent event) {
        if(event.getSource() == CloseButton){
            System.out.println("[DEBUG]: Closing the Frame");
            System.exit(0);
        }
    }
}
