package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

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

    private Stack<Pane> paneHistory = new Stack<>();
    private Pane currentPane;

    @FXML
    void HandlesClickedButton(MouseEvent event) {
        if (event.getSource() == CloseButton) {
            System.out.println("[DEBUG]: Closing the Frame");
            System.exit(0);
        } else if (event.getSource() == InputButton) {
            System.out.println("[DEBUG]: Input Button Clicked!");
            goBack();
            loadInputPane();
        } else if (event.getSource() == OutputButton) {
            System.out.println("[DEBUG]: Output Button Clicked!");
            goBack();
            loadOutputPane();
        } else if (event.getSource() == ResultButton) {
            System.out.println("[DEBUG]: Result Button Clicked!");
            goBack();
            loadResultPane();
        }
    }


    @FXML
    public void initialize() {
        loadInputPane();
    }

    private void loadResultPane() {
        loadPane("SwapNodes5_HomeResultUI.fxml");
    }

    private void loadOutputPane() {
        loadPane("SwapNodes4_HomeOutputUI.fxml");
    }

    private void loadInputPane() {
        loadPane("SwapNodes3_HomeInputUI.fxml");
    }

    private void loadPane(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
            Pane newPane = fxmlLoader.load();

            if (currentPane != null && currentPane.getId().equals(newPane.getId())) {
                System.out.println("[DEBUG]: The same pane is already loaded.");
                return;
            }

            if (currentPane != null) {
                paneHistory.push(currentPane);
                currentPane.setVisible(false);
            }

            ChangePane.getChildren().add(newPane);
            currentPane = newPane;
            currentPane.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ERROR]: Failed to load the pane.");
        }
    }

    private void goBack() {
        if (!paneHistory.isEmpty()) {
            if (currentPane != null) {
                currentPane.setVisible(false);
            }
            currentPane = paneHistory.pop();
            currentPane.setVisible(true);
        }
    }
}
