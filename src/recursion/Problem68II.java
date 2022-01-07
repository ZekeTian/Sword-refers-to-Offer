package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 
 * 题目描述：给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *        百度百科中最近公共祖先的定义为：
 *          “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 限制条件：
 *  （1）所有节点的值都是唯一的。
 *  （2）p、q 为不同节点且均存在于给定的二叉树中。
 * 
 * 示例：
 *                  3
 *                /   \
 *               5     1
 *              / \   / \
 *             6  2  0   8
 *               / \
 *              7  4
 *  节点 5 和节点 1 的最近公共祖先是节点 3。
 */
public class Problem68II {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        root.left = node5;
        root.right = node1;
        
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        node5.left = node6;
        node5.right = node2;
        
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        node1.left = node0;
        node1.right = node8;
        
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        node2.left = node7;
        node2.right = node4;
        
        _68IISolution solution = new _68IISolution();
        
        // output: 3
        TreeNode res = solution.lowestCommonAncestor(root, node5, node1);
        
        System.out.println(res.val);
    }
}

class _68IISolution {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        
        if (root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (null == left) {
            return right;
        }
        
        if (null == right) {
            return left;
        }
        
        return root;
    }
}

