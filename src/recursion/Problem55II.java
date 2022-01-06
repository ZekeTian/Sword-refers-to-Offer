package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 * 
 * 题目描述：输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 
 * 限制条件：0 <= 树的结点个数 <= 10000
 * 
 * 示例：
 *      3
 *     / \
 *    9  20
 *       / \
 *      15  7
 * 返回 true
 */
public class Problem55II {

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
        
        _55IISolution solution = new _55IISolution();
        
        System.out.println(solution.isBalanced(root));
    }
}


class _55IISolution {
    
    private int getHeight(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        
        if (!isBalanced(root.left)) {
            return false;
        }
        
        if (!isBalanced(root.right)) {
            return false;
        }
        
        // 左右子树均平衡，则需要判断当前节点的左右子树高度差是否符合条件
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1;
    }
}