package divideconquer;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 * 
 * 题目描述：输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 *        假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 
 * 限制条件：0 <= 节点个数 <= 5000
 * 
 * 示例：
 *               3
 *              / \
 *             9  20
 *                / \
 *               15  7
 *  输入：preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *  输出：[3,9,20,null,null,15,7]
 *  
 */
public class Problem07 {

    public static void main(String[] args) {
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };
        
        _07Solution solution = new _07Solution();
        
        TreeNode root = solution.buildTree(preorder, inorder);
        
        TreeUtil.levelOrder(root);
    }
}

class _07Solution {
    
    private TreeNode buildTree(int[] preorder, int preL, int preR,
                               int[] inorder, int inL, int inR) {
        if (preL > preR) {
            return null;
        }
        
        int root = preorder[preL];
        
        // 在中序遍历结果中确定根顶点的位置
        int inRoot = inL; // 根顶点在中序遍历结果中的位置
        for (inRoot = inL; inRoot <= inR; ++inRoot) {
            if (inorder[inRoot] == root) {
                break;
            }
        }
        
        // 计算左子树的长度，从而便于确定左右子树的区间
        int leftTreeLen = inRoot - inL;
        
        TreeNode node = new TreeNode(root);
        node.left = buildTree(preorder, preL + 1, preL + leftTreeLen, 
                              inorder, inL, inRoot - 1);
        node.right = buildTree(preorder, preL + leftTreeLen + 1, preR, 
                               inorder, inRoot + 1, inR);
        
        return node;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length - 1, 
                         inorder, 0, inorder.length - 1);
    }
} 
