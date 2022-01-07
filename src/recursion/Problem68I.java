package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 
 * 题目描述：给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *        百度百科中最近公共祖先的定义为：
 *          “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 限制条件：
 *  （1）所有节点的值都是唯一的。
 *  （2）p、q 为不同节点且均存在于给定的二叉搜索树中。
 *  
 * 示例：
 *                  6
 *                /   \
 *               2     8
 *              / \   / \
 *             0  4  7   9
 *               / \
 *              3   5
 *  节点 2 和节点 8 的最近公共祖先是 6
 */
public class Problem68I {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node8 = new TreeNode(8);
        root.left = node2;
        root.right = node8;
        
        TreeNode node0 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        node2.left = node0;
        node2.right = node4;
        
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        node8.left = node7;
        node8.right = node9;
        
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        node4.left = node3;
        node4.right = node5;
        
        _68ISolution1 solution = new _68ISolution1();

//        _68ISolution2 solution = new _68ISolution2();
        
        TreeNode res = solution.lowestCommonAncestor(root, node2, node8);
        
        // output: 6
        System.out.println(res.val);
    }
}

class _68ISolution1 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q); // p、q 都比 root 的值小，则去 root 的左子树中寻找
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q); // p、q 都比 root 的值大，则去 root 的右子树中寻找
        }
        
        // p、q 一个在 root 的左子树中，另一个在右子树中，则当前节点即为 p、q 的父节点
        return root;
    }
}

/**
 * 解法二：通用解法，可以适用于一般的二叉树
 */
class _68ISolution2 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root) {
            return null;
        }
        
        if (root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (null == right) {
            return left;
        }
        
        if (null == left) {
            return right;
        }
        
        return root; // 左右各有一个祖先，则当前节点是 p、q 的最近公共祖先
    }
}

