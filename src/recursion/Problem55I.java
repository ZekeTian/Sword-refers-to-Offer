package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
 * 
 * 题目描述：输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * 
 * 限制条件：节点总数 <= 10000
 * 
 * 示例：
 *      3
 *     / \
 *    9  20
 *       / \
 *      15  7
 * 最大的深度为 3
 * 
 */
public class Problem55I {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        root.left = node9;
        root.right = node20;
        
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node20.left = node15;
        node20.right = node7;
        
        _55ISolution solution = new _55ISolution();
        
        System.out.println(solution.maxDepth(root));
    }
}

class _55ISolution {
    
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
