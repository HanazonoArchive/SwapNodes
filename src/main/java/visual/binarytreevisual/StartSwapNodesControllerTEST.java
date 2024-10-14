package visual.binarytreevisual;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class StartSwapNodesControllerTEST {
    // Don't mind this line ===
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    // ===

    @FXML
    private Pane CloseButtonPane;

    @FXML
    private AnchorPane MainAnchorPane;

    @FXML
    private Pane MainPane;

    @FXML
    private Canvas OutputCanvas;

    @FXML
    private Pane TestRunButton;


    class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }


    @FXML
    public void HandlesClicked(MouseEvent event) {
        if (event.getSource() == CloseButtonPane){
            stage.close();
            System.exit(0);
        } else if (event.getSource() == TestRunButton){
            Node root = new Node(10);
            root.left = new Node(5);
            root.right = new Node(15);
            root.left.left = new Node(3);
            root.left.right = new Node(7);
            root.right.right = new Node(18);

            GraphicsContext gc = OutputCanvas.getGraphicsContext2D();

            drawTree(gc, root, 400, 50, 200, 50);
        }
    }


    private void drawTree(GraphicsContext gc, Node node, double x, double y, double xOffset, double yOffset) {
        if (node == null) return;

        // Draw left child connection line first
        if (node.left != null) {
            gc.strokeLine(x, y, x - xOffset, y + yOffset);
            drawTree(gc, node.left, x - xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        // Draw right child connection line first
        if (node.right != null) {
            gc.strokeLine(x, y, x + xOffset, y + yOffset);
            drawTree(gc, node.right, x + xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        // Now draw the node (circle) on top of the lines
        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        // Draw the node value inside the circle
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText(String.valueOf(node.value), x - 6, y + 4);
    }

}