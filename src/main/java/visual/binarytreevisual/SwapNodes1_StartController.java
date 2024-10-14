package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SwapNodes1_StartController {

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Pane StartButtonPane;

    @FXML
    private AnchorPane StartMainAnchorPane;

    @FXML
    private Pane StartPane;

    @FXML
    void HandlesButtonClicked(MouseEvent event) throws IOException {
        if (event.getSource() == StartButtonPane) {
            System.out.println("[DEBUG]: Start Button Clicked!");

            // Load the next FXML file for the new stage
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SwapNodes2_HomeUI.fxml")); // Change to the actual FXML file
            Scene scene = new Scene(fxmlLoader.load(), 715, 538);

            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.setTitle("Swap Nodes!");
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.initStyle(StageStyle.UNDECORATED); // If needed

            // Retrieve the controller for the new stage
            SwapNodes2_HomeController InputController = fxmlLoader.getController();
            InputController.setStage(newStage);

            // Show the new stage before closing the old one
            newStage.show();

            // Close the current stage
            this.stage.close();
        }
    }
}
