package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 * 
 * 题目描述：给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * 
 * 限制条件：1 ≤ k ≤ 二叉搜索树元素个数
 * 
 * 示例：
 *          3
 *         / \
 *        1   4
 *         \
 *         2
 *  输入：root = [3,1,4,null,2], k = 1
 *  输出：4
 *  
 */
public class Problem54 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        
        root.left = node1;
        root.right = node4;
        
        TreeNode node2 = new TreeNode(2);
        node1.right = node2;
        
        int k = 1;
        
        _54Solution solution = new _54Solution();
        
        
        System.out.println(solution.kthLargest(root, k));
    }
}

class _54Solution {
    
    private int count = 0; // 记录已经访问过的元素
    private int kth = 0;
    
    private void getKthLargest(TreeNode root, int k) {
        // 因为是寻找第 k 大，所以使用降序，即遍历顺序更改为：右、根、左
        if (null != root.right && count < k) { // 右节点不为空，并且还未访问到第 k 大的元素 
            getKthLargest(root.right, k);
        }
        
        ++count;
        if (count == k) { // 已经访问到第 k 大的元素，则返回
            kth = root.val;
            return;
        }
        
        if (null != root.left && count < k) { // 左节点不为空，并且还未访问到第 k 大的元素 
            getKthLargest(root.left, k);
        }
    }
    
    public int kthLargest(TreeNode root, int k) {
        if (null != root) {
            getKthLargest(root, k);
        }
        
        return kth;
    }
}

