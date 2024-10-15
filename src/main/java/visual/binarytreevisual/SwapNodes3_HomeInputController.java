package visual.binarytreevisual;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

import java.awt.*;

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
    private TextArea ManualInputTextArea;

    @FXML
    private Pane OpenFileButton;

    @FXML
    private Pane OpenFileGenerateTreeButton;

    @FXML
    private TextField SwapDepthsTextField;

    @FXML
    void HandlesButtonClicked(MouseEvent event) {
        if(event.getSource() == ManualGenerateTreeButton){
            //Methods When Manual Generate Tree Button is Clicked!
        } else if (event.getSource() == OpenFileGenerateTreeButton){
            //Methods When Open File Generate Tree Button is Clicked!
        } else if (event.getSource() == OpenFileButton) {
            //Methods When Open File is Clicked!
        }
    }

    @FXML
    void HandlesKeyboardTyped(KeyEvent event) {
        // Ensure the event is fired from the correct source (TextArea)
        if (event.getSource() == ManualInputTextArea) {
            System.out.println("[Debug]: Key Typed has been observed.");
            System.out.println("Typed character: " + event.getCharacter());
        }
    }
}
