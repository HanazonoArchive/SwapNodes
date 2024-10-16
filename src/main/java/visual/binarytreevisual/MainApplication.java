package visual.binarytreevisual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class    MainApplication extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage stage; // Declare stage as a class-level variable

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("SwapNodes1_StartUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 315, 538);
        stage.setTitle("Swap Nodes!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        // Load the icon
        URL resourceUrl = getClass().getResource("/visual/binarytreevisual/Image/icon.png");
        if (resourceUrl != null) {
            Image iconImage = new Image(resourceUrl.toExternalForm());
            stage.getIcons().add(iconImage); // Directly add the Image
        } else {
            System.out.println("[ERROR]: Icon image not found at the specified path.");
            System.out.println("Resource URL: " + resourceUrl);
        }

        stage.show(); // Show the stage after setting the icon

        SwapNodes1_StartController controller = fxmlLoader.getController();
        controller.setStage(stage);

        // Set up mouse dragging for the window
        scene.setOnMousePressed(this::handleMousePressed);
        scene.setOnMouseDragged(this::handleMouseDragged);
    }

    // Handle the mouse press event to initialize the dragging
    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getScreenX() - stage.getX();
        yOffset = event.getScreenY() - stage.getY();
    }

    // Handle the mouse drag event to move the stage
    private void handleMouseDragged(MouseEvent event) {
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public static void main(String[] args) {
        launch();
    }
}
