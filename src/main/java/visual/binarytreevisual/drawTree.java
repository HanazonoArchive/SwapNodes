package visual.binarytreevisual;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class drawTree {
    /*
    * // Sample binary tree structure
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.right = new Node(18);

        Pane rootPane = new Pane();
        Canvas canvas = new Canvas(800, 600);
        rootPane.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Drawing the binary tree starting from the root
        drawTree(gc, root, 400, 50, 200, 50);
        * */

    // Binary Tree Node class
    private void drawTreeAlgorithm(GraphicsContext gc, BinaryTree.Node node, double x, double y, double xOffset, double yOffset) {
        if (node == null) return;

        // Draw left child connection line first
        if (node.left != null) {
            gc.strokeLine(x, y, x - xOffset, y + yOffset);
            drawTreeAlgorithm(gc, node.left, x - xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        // Draw right child connection line first
        if (node.right != null) {
            gc.strokeLine(x, y, x + xOffset, y + yOffset);
            drawTreeAlgorithm(gc, node.right, x + xOffset, y + yOffset, xOffset / 2, yOffset);
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
