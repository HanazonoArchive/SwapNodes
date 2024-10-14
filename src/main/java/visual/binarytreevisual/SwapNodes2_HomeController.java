package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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
    private Pane OutputButton;

    @FXML
    private Pane ResultButton;

    @FXML
    void HandlesClickedButton(MouseEvent event) {
        if (event.getSource() == CloseButton) {
            System.out.println("[DEBUG]: Closing the Frame");
            System.exit(0);
        } else if (event.getSource() == InputButton) {
            System.out.println("[DEBUG]: Input Button Clicked!");
            loadInputPane();
        } else if (event.getSource() == OutputButton) {
            System.out.println("[DEBUG]: Output Button Clicked!");
            loadOutputPane();
        } else if (event.getSource() == ResultButton) {
            System.out.println("[DEBUG]: Result Button Clicked!");
            loadResultPane();
        }
    }

    @FXML
    public void initialize() {
        loadInputPane();
    }

    private void loadResultPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SwapNodes5_HomeResultUI.fxml"));
            Pane resultPane = fxmlLoader.load();
            SwapNodes5_HomeResultController resultController = fxmlLoader.getController();
            resultController.setStage(stage);
            ChangePane.getChildren().clear();
            ChangePane.getChildren().add(resultPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Failed to load the Result Pane.");
        }
    }

    private void loadOutputPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SwapNodes4_HomeOutputUI.fxml"));
            Pane outputPane = fxmlLoader.load();
            SwapNodes4_HomeOutputController outputController = fxmlLoader.getController();
            outputController.setStage(stage);
            ChangePane.getChildren().clear();
            ChangePane.getChildren().add(outputPane);
            outputController.setOutputMainPane(ChangePane);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Failed to load the Output Pane.");
        }
    }

    private void loadInputPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SwapNodes3_HomeInputUI.fxml"));
            Pane inputPane = fxmlLoader.load();
            SwapNodes3_HomeInputController inputController = fxmlLoader.getController();
            inputController.setStage(stage);
            ChangePane.getChildren().clear();
            ChangePane.getChildren().add(inputPane);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Failed to load the Input Pane.");
        }
    }
}
