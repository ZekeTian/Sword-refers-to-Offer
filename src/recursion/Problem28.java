package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 *
 * 题目描述：请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * 
 * 限制条件：0 <= 节点个数 <= 1000
 * 
 * 示例：
 *  示例 1
 *           1
 *         /   \
 *        2     2
 *       / \   / \
 *      3  4  4  3
 *  示例 1 中的二叉树是对称的
 *  
 *  示例 2
 *           1
 *         /   \
 *        2     2
 *         \     \
 *          3     3
 *         
 */
public class Problem28 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(2);
        root.left = node21;
        root.right = node22;
        
        TreeNode node31 = new TreeNode(3);
        TreeNode node41 = new TreeNode(4);
        node21.left = node31;
        node21.right = node41;
        
        TreeNode node32 = new TreeNode(3);
        TreeNode node42 = new TreeNode(4);
        node22.left = node42;
        node22.right = node32;
         
        _28Solution solution = new _28Solution();
        
        System.out.println(solution.isSymmetric(root));
    }
}


class _28Solution {
    
    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (null == root1 && null == root2) {
            return true; // root1、root2 同时为空，依然保持对称
        }
        
        if (null == root1 || null == root2) { // root1、root2 中一个为空，一个不为空，则不对称，返回 false
            return false;
        }
        
        if (root1.val != root2.val) {
            return false; // root1、root2 不为空，但是值不相等，则不对称
        }
        
        // root1、root2 的值相等，则继续判断左右子树是否对称
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
}
