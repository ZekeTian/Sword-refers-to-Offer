package backtrack;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * 
 * 题目描述：给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。叶子节点 是指没有子节点的节点。
 * 
 * 限制条件：
 *  （1）树中节点总数在范围 [0, 5000] 内
 *  （2）-1000 <= Node.val <= 1000
 *  （3）-1000 <= targetSum <= 1000
 *  
 * 示例：
 *                  5
 *                /   \
 *               4     8
 *              /     / \
 *             11    13  4
 *            /  \      /  \
 *           7   2     5    1
 *  输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 *  输出：[[5,4,11,2],[5,8,4,5]]
 *   
 */
public class Problem34 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node41 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        root.left = node41;
        root.right = node8;
        
        TreeNode node11 = new TreeNode(11);
        node41.left = node11;
        
        TreeNode node13 = new TreeNode(13);
        TreeNode node42 = new TreeNode(4);
        node8.left = node13;
        node8.right = node42;
        
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        node11.left = node7;
        node11.right = node2;
        
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        node42.left = node5;
        node42.right = node1;
        
        int targetSum = 22;
        
        
        _34Solution solution = new _34Solution();
        
        
        System.out.println(solution.pathSum(root, targetSum));
        
    }
}

class _34Solution {
    
    private List<List<Integer>> resultList = new ArrayList<>();
    
    private void findPath(TreeNode root, int target, List<Integer> path) {
        if (root.left == null && root.right == null /* 叶子节点 */ 
                && root.val == target /* 匹配到值 */ ) {
            path.add(root.val);
            resultList.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        
        if (root.left != null) {
            path.add(root.val);
            findPath(root.left, target - root.val, path);
            path.remove(path.size() - 1);
        }
        
        if (root.right != null) {
            path.add(root.val);
            findPath(root.right, target - root.val, path);
            path.remove(path.size() - 1);
        }
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (null != root) {
            findPath(root, target, new ArrayList<>());
        }
        
        return resultList;
    }
}
