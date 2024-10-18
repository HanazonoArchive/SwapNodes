package visual.binarytreevisual;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class drawTree {

    public void drawVisualTrees(GraphicsContext gc, List<List<Integer>> iterations) {
        BinaryTreeBuilder rebuilder = new BinaryTreeBuilder();

        // Step 1: Rebuild trees from each iteration
        List<BinaryTreeBuilder.Node> trees = rebuilder.rebuildTreesFromIterations(iterations);

        // Step 2: Set canvas dimensions and drawing offsets
        double canvasWidth = 594;
        double xOffset = 100; // Horizontal offset for drawing
        double yOffset = 100;  // Base vertical offset for drawing
        double iterationSpacing = 400; // Space between each tree iteration

        // Ensure the canvas is set to the correct width (no height limit applied)
        gc.getCanvas().setWidth(canvasWidth);

        // Draw each tree based on its iteration
        for (int i = 0; i < trees.size(); i++) {
            BinaryTreeBuilder.Node root = trees.get(i);
            gc.save(); // Save the current state of the graphics context

            // Calculate the vertical position for each tree
            double startX = canvasWidth / 2; // Center the tree horizontally
            double startY = 100 + (i * (iterationSpacing + yOffset)); // Adjusting Y position for each tree

            // Draw the tree
            drawTreeAlgorithm(gc, root, startX, startY, xOffset, yOffset);

            gc.restore(); // Restore the graphics context to the previous state
        }
    }

    // Your original drawing algorithm
    private void drawTreeAlgorithm(GraphicsContext gc, BinaryTreeBuilder.Node node, double x, double y, double xOffset, double yOffset) {
        if (node == null) return;

        // Draw left child connection line first
        if (node.left != null) {
            gc.setStroke(Color.WHITE); // Set line color to white
            gc.strokeLine(x, y, x - xOffset, y + yOffset);
            drawTreeAlgorithm(gc, node.left, x - xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        // Draw right child connection line first
        if (node.right != null) {
            gc.setStroke(Color.WHITE); // Set line color to white
            gc.strokeLine(x, y, x + xOffset, y + yOffset);
            drawTreeAlgorithm(gc, node.right, x + xOffset, y + yOffset, xOffset / 2, yOffset);
        }

        // Now draw the node (circle) on top of the lines
        gc.setFill(Color.LIGHTBLUE); // Fill color for the circle
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setStroke(Color.WHITE); // Circle outline color
        gc.strokeOval(x - 15, y - 15, 30, 30);

        // Draw the node value inside the circle
        gc.setFill(Color.BLACK); // Text color set to black
        gc.setFont(new Font(14));
        gc.fillText(String.valueOf(node.value), x - 6, y + 4);
    }
}
