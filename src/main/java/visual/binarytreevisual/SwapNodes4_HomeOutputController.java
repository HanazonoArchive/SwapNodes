package visual.binarytreevisual;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

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
        AppData appData = AppData.getInstance();
        GraphicsContext gc = ResultCanvas.getGraphicsContext2D();

        // Clear the canvas before drawing new content
        gc.clearRect(0, 0, ResultCanvas.getWidth(), ResultCanvas.getHeight());

        if (event.getSource() == LoadFileTreeButton) {
            System.out.println("[DEBUG]: LoadFileTreeButton clicked");

            if (appData.FilefinalIteration1DArray != null) {
                nodesOutputLB.setText(appData.FilefinalIteration1DArray.toString());
            } else {
                nodesOutputLB.setText("No data available for file tree");
            }

            timeTakenLB.setText(String.valueOf(appData.FiletimerNanoseconds));

            List<List<Integer>> iterations = appData.FileoutputForDisplay;  // or FileoutputForDisplay
            drawTree treeDrawer = new drawTree();
            treeDrawer.drawVisualTrees(gc, iterations);  // Pass cleared gc for drawing

        } else if (event.getSource() == LoadManualTreeButton) {
            System.out.println("[DEBUG]: LoadManualTreeButton clicked");

            // Check if ManualfinalIteration1DArray is null before setting the text
            if (appData.ManualfinalIteration1DArray != null) {
                nodesOutputLB.setText(appData.ManualfinalIteration1DArray.toString());
            } else {
                nodesOutputLB.setText("No data available for manual tree");
            }

            timeTakenLB.setText(String.valueOf(appData.ManualtimerNanoseconds));

            List<List<Integer>> iterations = appData.ManualoutputForDisplay;
            drawTree treeDrawer = new drawTree();
            treeDrawer.drawVisualTrees(gc, iterations);  // Pass cleared gc for drawing
        }
    }
}
