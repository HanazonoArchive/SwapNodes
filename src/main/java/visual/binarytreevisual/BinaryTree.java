package visual.binarytreevisual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    TreeNode root;

    public BinaryTree(int start, int end) {
        buildTree(start, end);
    }

    // Build the tree from start to end values
    private void buildTree(int start, int end) {
        Queue<TreeNode> queue = new LinkedList<>();
        root = new TreeNode(start); // The first value starts at '2'
        queue.add(root);
        int value = start + 1;

        while (value <= end) {
            TreeNode current = queue.poll();

            // Assign left child
            if (value <= end) {
                current.left = new TreeNode(value);
                queue.add(current.left);
                value++;
            }

            // Assign right child
            if (value <= end) {
                current.right = new TreeNode(value);
                queue.add(current.right);
                value++;
            }
        }

        // Fill remaining spots with -1 if necessary
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left == null) {
                current.left = new TreeNode(-1);
            }
            if (current.right == null) {
                current.right = new TreeNode(-1);
            }
        }
    }

    // Level-order traversal to display the tree structure
    public void writeTreeToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode current = queue.poll();

                // Write the current node's children (left and right) to the file
                if (current.left != null && current.right != null) {
                    writer.write(current.left.value + " " + current.right.value);
                    writer.newLine(); // Move to the next line
                }

                // Add children to the queue
                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            System.out.println("Tree structure successfully written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create a binary tree with values from 2 to 2000
        BinaryTree tree = new BinaryTree(1, 202400);

        // Output the tree structure to a file
        tree.writeTreeToFile("binary_tree_output.txt");
    }
}
