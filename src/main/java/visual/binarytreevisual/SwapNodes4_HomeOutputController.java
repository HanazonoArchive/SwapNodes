package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

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
    private GridPane GridPaneOutput;

    @FXML
    private Pane ViewResultButton; // Button to trigger the change

    @FXML
    private Label nodesOutputLB;

    @FXML
    private Label timeTakenLB;

    @FXML
    void HandlesButtonClicked(MouseEvent event) {
        if (event.getSource() == ViewResultButton) {
            loadResultPane(); // Call the method to load the new pane
        }
    }

    private void loadResultPane() {
        try {
            // Load the new FXML
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SwapNodes5_HomeResultUI.fxml"));
            Pane resultPane = fxmlLoader.load();

            // Optionally, set the stage if needed
            SwapNodes5_HomeResultController resultController = fxmlLoader.getController();
            resultController.setStage(stage); // Pass the stage if needed

            // Clear existing content and add the new pane
            OutputMainPane.getChildren().clear();
            OutputMainPane.getChildren().add(resultPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Failed to load the Result Pane.");
        }
    }
}
