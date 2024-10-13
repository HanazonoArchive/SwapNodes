package visual.binarytreevisual;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BinaryTree extends Application {

    // Binary Tree Node class
    class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            left = right = null;
        }
    }

    // Drawing the binary tree on a Canvas
    @Override
    public void start(Stage primaryStage) {
        // Sample binary tree structure
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

        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Binary Tree Visualizer");
        primaryStage.show();
    }

    // Recursive function to draw the binary tree
    private void drawTree(GraphicsContext gc, Node node, double x, double y, double xOffset, double yOffset) {
        if (node == null) return;

        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - 15, y - 15, 30, 30);

        // Draw node value inside the circle
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText(String.valueOf(node.value), x - 6, y + 4);

        // Draw left child
        if (node.left != null) {
            gc.strokeLine(x, y, x - xOffset, y + yOffset);
            drawTree(gc, node.left, x - xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        // Draw right child
        if (node.right != null) {
            gc.strokeLine(x, y, x + xOffset, y + yOffset);
            drawTree(gc, node.right, x + xOffset, y + yOffset, xOffset / 2, yOffset);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

