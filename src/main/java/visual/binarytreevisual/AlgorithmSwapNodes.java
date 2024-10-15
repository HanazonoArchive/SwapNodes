package visual.binarytreevisual;

import java.util.*;

public class AlgorithmSwapNodes {

    public static class TreeNode {
        int id;
        Algorithm.TreeNode left;
        Algorithm.TreeNode right;

        public TreeNode(int id) {
            this.id = id;
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        Algorithm.TreeNode root = buildTree(indexes);
        List<List<Integer>> result = new ArrayList<>();

        for (int k : queries) {
            swap(root, k);
            result.add(inorderTraversal(root));
        }

        return result;
    }

    private static Algorithm.TreeNode buildTree(List<List<Integer>> indexes) {
        int n = indexes.size();
        Algorithm.TreeNode[] nodeList = new Algorithm.TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodeList[i] = new Algorithm.TreeNode(i + 1);
        }

        for (int i = 0; i < n; i++) {
            int left = indexes.get(i).get(0);
            int right = indexes.get(i).get(1);

            if (left > 0) nodeList[i].left = nodeList[left - 1];
            if (right > 0) nodeList[i].right = nodeList[right - 1];
        }

        return nodeList[0];
    }

    private static void swap(Algorithm.TreeNode node, int target) {
        Stack<Algorithm.TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();

        stack.push(node);
        depths.push(1);

        while (!stack.isEmpty()) {
            Algorithm.TreeNode current = stack.pop();
            int depth = depths.pop();

            if (current == null) continue;

            if (depth % target == 0) {
                Algorithm.TreeNode temp = current.left;
                current.left = current.right;
                current.right = temp;
            }

            stack.push(current.right);
            depths.push(depth + 1);
            stack.push(current.left);
            depths.push(depth + 1);
        }
    }

    private static List<Integer> inorderTraversal(Algorithm.TreeNode node) {
        List<Integer> result = new ArrayList<>();
        Stack<Algorithm.TreeNode> stack = new Stack<>();
        Algorithm.TreeNode current = node;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.id);
            current = current.right;
        }

        return result;
    }
}
