package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 * 
 * 题目描述：从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 
 * 限制条件：节点总数 <= 1000
 * 
 * 示例：
 *           3
 *         /  \
 *        9   20
 *            / \
 *           15  7
 *           
 *  输出：[3,9,20,15,7]
 *  
 */
public class Problem32I {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        node3.left = node9;
        node3.right = node20;
        
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node20.left = node15;
        node20.right = node7;
        
        _32ISolution solution = new _32ISolution();
        
        System.out.println(Arrays.toString(solution.levelOrder(node3)));
    }
}

class _32ISolution {
    
    public int[] levelOrder(TreeNode root) {
        if (null == root) {
            return new int[] {};
        }
        
        List<Integer> resultList = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            resultList.add(node.val);
            
            if (null != node.left) {
                queue.add(node.left);
            }
            if (null != node.right) {
                queue.add(node.right);
            }
        }
        
        int[] resultArr = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); ++i) {
            resultArr[i] = resultList.get(i);
        }
        
        return resultArr;
    }
}


