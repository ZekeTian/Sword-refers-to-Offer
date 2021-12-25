package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 * 
 * 题目描述：从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 
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
          [9, 20],
          [15, 7]
        ]
 *
 */
public class Problem32II {

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
        
        _32IISolution solution = new _32IISolution();
        
        
        System.out.println(solution.levelOrder(node3));
    }
}


class _32IISolution {
    
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
                queue.addLast(new Pair(node.left, level + 1));
            }
            if (null != node.right) {
                queue.addLast(new Pair(node.right, level + 1));
            }
        }
        
        return resultList;
    }
}

