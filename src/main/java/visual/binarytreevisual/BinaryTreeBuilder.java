package visual.binarytreevisual;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeBuilder {

    public static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
        }
    }

    // Rebuild a binary tree from multiple in-order traversal lists
    public List<Node> rebuildTreesFromIterations(List<List<Integer>> iterations) {
        List<Node> trees = new ArrayList<>();

        for (List<Integer> inOrderList : iterations) {
            Node tree = rebuildTree(inOrderList);
            trees.add(tree);
        }

        return trees;
    }

    // Rebuilds a single binary tree from an in-order traversal list
    private Node rebuildTree(List<Integer> inOrderList) {
        if (inOrderList == null || inOrderList.isEmpty()) {
            return null;
        }

        return buildTreeFromInOrder(inOrderList, 0, inOrderList.size() - 1);
    }

    // Helper method to build the tree from in-order
    private Node buildTreeFromInOrder(List<Integer> inOrderList, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;
        Node node = new Node(inOrderList.get(mid));

        node.left = buildTreeFromInOrder(inOrderList, start, mid - 1);
        node.right = buildTreeFromInOrder(inOrderList, mid + 1, end);

        return node;
    }
}
