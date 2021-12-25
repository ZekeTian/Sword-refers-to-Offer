package queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 * 
 * 题目描述：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
 *  输出：[
          [3],
          [20, 9],
          [15, 7]
        ]
 *
 */
public class Problem32III {

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
        
        _32IIISolution solution = new _32IIISolution();
        
        System.out.println(solution.levelOrder(node3));
    }
}


class _32IIISolution {
    
    private class Pair {
        TreeNode node;
        int level;
        
        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
        
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> resultList = new ArrayList<>();
        LinkedList<Pair> queue = new LinkedList<>();
        if (null == root) {
            return resultList;
        }
        
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            TreeNode node = poll.node;
            int level = poll.level;
            
            if (level == resultList.size()) {
                resultList.add(new ArrayList<>());
            }
            resultList.get(level).add(node.val);
            
            if (null != node.left) {
                queue.add(new Pair(node.left, level + 1));
            }
            if (null != node.right) {
                queue.add(new Pair(node.right, level + 1));
            }
        }
        
        // 翻转奇数层的顺序
        for (int i = 0; i < resultList.size(); ++i) {
            if (i % 2 != 0) {
                Collections.reverse(resultList.get(i));
            }
        }
        
        return resultList;
    }
}
