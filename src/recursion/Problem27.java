package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 * 
 * 题目描述：请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * 
 * 限制条件：0 <= 节点个数 <= 1000
 * 
 * 示例：
 *  输入
 *             4
 *           /   \
 *          2     7
 *         / \   / \
 *        1  3  6  9
 * 
 * 输出
 *            4
 *          /   \
 *         7     2
 *        / \   / \
 *       9  6  3  1
 */
public class Problem27 {

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        node4.left = node2;
        node4.right = node7;
        
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        node2.left = node1;
        node2.right = node3;
        
        TreeNode node9 = new TreeNode(9);
        TreeNode node6 = new TreeNode(6);
        node7.left = node6;
        node7.right = node9;
        
        
        System.out.println("翻转之前：");
        TreeUtil.levelOrder(node4);
        
        _27Solution solution = new _27Solution();
        
        TreeNode root = solution.mirrorTree(node4);
        System.out.println("翻转之后：");
        TreeUtil.levelOrder(root);
        
    }
}


class _27Solution {
    
    public TreeNode mirrorTree(TreeNode root) {
        if (null == root) {
            return null;
        }
        
        TreeNode leftTree = root.left;
        TreeNode rightTree = root.right;
        
        root.left = mirrorTree(rightTree);
        root.right = mirrorTree(leftTree);
        
        return root;
    }   
}
