package visual.binarytreevisual;

import java.util.*;

public class Algorithm {

    public static class TreeNode {  
        int id;              
        TreeNode left; 
        TreeNode right; 

        public TreeNode(int id) {
            this.id = id; 
        }
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        TreeNode root = buildTree(indexes);
        List<List<Integer>> result = new ArrayList<>();

        for (int k : queries) {
            swap(root, k);  
            result.add(inorderTraversal(root)); 
        }

        return result;
    }

    private static TreeNode buildTree(List<List<Integer>> indexes) {
        int n = indexes.size();
        TreeNode[] nodeList = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodeList[i] = new TreeNode(i + 1);
        }

        for (int i = 0; i < n; i++) {
            int left = indexes.get(i).get(0);
            int right = indexes.get(i).get(1);

            if (left > 0) nodeList[i].left = nodeList[left - 1];
            if (right > 0) nodeList[i].right = nodeList[right - 1];
        }

        return nodeList[0];
    }

    private static void swap(TreeNode node, int target) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        
        stack.push(node);
        depths.push(1);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            int depth = depths.pop();
            
            if (current == null) continue;
            
            if (depth % target == 0) {
                TreeNode temp = current.left;
                current.left = current.right;
                current.right = temp;
            }
            
            stack.push(current.right);
            depths.push(depth + 1);
            stack.push(current.left);
            depths.push(depth + 1);
        }
    }

    private static List<Integer> inorderTraversal(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = node;

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); 
        List<List<Integer>> indexes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            indexes.add(Arrays.asList(a, b));
        }

        System.out.println(indexes);

        int t = sc.nextInt();
        List<Integer> queries = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            queries.add(sc.nextInt());
        }

        long startTime = System.nanoTime();
        
        List<List<Integer>> result = swapNodes(indexes, queries);
        
        long endTime = System.nanoTime();
        
        for (List<Integer> r : result) {
            for (int num : r) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        System.out.println("Time taken for algorithm (nanoseconds): " + (endTime - startTime));
    }
}
