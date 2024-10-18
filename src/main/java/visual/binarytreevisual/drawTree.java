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

        // Step 2: Set the canvas width dynamically (height depends on number of iterations)
        double canvasWidth = gc.getCanvas().getWidth();
        double baseYOffset = 100;  // Base vertical offset for drawing
        double iterationSpacing = 200; // Space between each tree iteration

        // Draw each tree iteration
        for (int i = 0; i < trees.size(); i++) {
            BinaryTreeBuilder.Node root = trees.get(i);
            gc.save(); // Save the current state of the graphics context

            // Calculate the vertical position for each tree
            double startX = canvasWidth / 2; // Start at the center horizontally
            double startY = baseYOffset + (i * iterationSpacing); // Vertical position for each tree

            // Calculate maximum depth of the tree to adjust spacing
            int maxDepth = calculateDepth(root);
            double initialXOffset = canvasWidth / (Math.pow(1.2, maxDepth)); // Wider horizontal spacing based on depth

            // Draw the tree for the current iteration
            drawTreeAlgorithm(gc, root, startX, startY, initialXOffset, 80); // Adjust Y offset for more vertical space

            gc.restore(); // Restore the graphics context to the previous state
        }
    }

    private void drawTreeAlgorithm(GraphicsContext gc, BinaryTreeBuilder.Node node, double x, double y, double xOffset, double yOffset) {
        if (node == null) return;

        // Draw left child connection line first
        if (node.left != null) {
            gc.setStroke(Color.WHITE); // Set line color to white
            gc.strokeLine(x, y, x - xOffset, y + yOffset);
            drawTreeAlgorithm(gc, node.left, x - xOffset, y + yOffset, xOffset / 1.5, yOffset); // Reduce xOffset slower
        }

        // Draw right child connection line first
        if (node.right != null) {
            gc.setStroke(Color.WHITE); // Set line color to white
            gc.strokeLine(x, y, x + xOffset, y + yOffset);
            drawTreeAlgorithm(gc, node.right, x + xOffset, y + yOffset, xOffset / 1.5, yOffset); // Reduce xOffset slower
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

    // Calculate the depth of the binary tree to adjust the horizontal spacing
    private int calculateDepth(BinaryTreeBuilder.Node node) {
        if (node == null) return 0;
        return 1 + Math.max(calculateDepth(node.left), calculateDepth(node.right));
    }
}
