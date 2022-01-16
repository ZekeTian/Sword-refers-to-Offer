package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/
 * 
 * 题目描述：请实现两个函数，分别用来序列化和反序列化二叉树。你需要设计一个算法来实现二叉树的序列化与反序列化。
 *        这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * 
 * 示例：
 *           1
 *          / \
 *         2   3
 *            / \
 *           4   5
 *  输入：root = [1,2,3,null,null,4,5]
 *  输出：[1,2,3,null,null,4,5]
 */
public class Problem37 {

    public static void main(String[] args) {
        String tree = "[1,2,3,null,null,4,5]";
        
        Codec codec = new Codec();
        
        TreeNode root = codec.deserialize(tree);
        
        System.out.println("反序列化，树的层序遍历：");
        TreeUtil.levelOrder(root);
        
        System.out.println();
        System.out.println("树序列化后的字符串：");
        System.out.println(codec.serialize(root));
        
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * 解题思路本质上是一个广度遍历
 */
class Codec {
    
    private class Pair {
        TreeNode node;
        int level;
        
        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    // BFS 遍历
    private List<List<TreeNode>> bfs(TreeNode root) {
        List<List<TreeNode>> resultList = new ArrayList<>();
        LinkedList<Pair> queue = new LinkedList<>();
        queue.addLast(new Pair(root, 0));
        
        while (!queue.isEmpty()) {
            Pair poll = queue.pollFirst();
            TreeNode node = poll.node;
            int level = poll.level;
            
            if (level >= resultList.size()) {
                resultList.add(new ArrayList<>());
            }
            
            resultList.get(level).add(node);
            
            if (null != node.left) {
                queue.addLast(new Pair(node.left, level + 1));
            }
            if (null != node.right) {
                queue.addLast(new Pair(node.right, level + 1));
            }
        }
        
        return resultList;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return "[]";
        }
        
        List<List<TreeNode>> levelList = bfs(root);
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(root.val + "");
        
        // 将层序结果转换成字符串
        for (int i = 0; i < levelList.size() - 1; ++i) {
            List<TreeNode> list = levelList.get(i);
            for (TreeNode node : list) {
                String left = (node.left == null ? "null" : node.left.val + "");
                String right = (node.right == null ? "null" : node.right.val + "");
                joiner.add(left).add(right);
            }
        }
        
        return "[" + joiner.toString() + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || "[]".equals(data)) {
            return null;
        }
        
        // 分割得到单个节点的值
        String[] values = data.substring(1, data.length() - 1) /* 去除前后的 [] */
                              .split(",");
        
        if ("null".equals(values[0])) {
            return null; // 根顶点为 null
        }
        
        // 将根顶点添加到 levelList 中
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        List<List<TreeNode>> levelList = new ArrayList<>(); // 存放树中每层节点
        levelList.add(new ArrayList<>());
        levelList.get(0).add(root);
        int proccesedNodeNum = 1; // 已经处理节点个数
        
        while (proccesedNodeNum < values.length) {
            // 创建一个 list 存放当前层节点
            List<TreeNode> curLevel = new ArrayList<>();
            // 获取上层节点（因为当前层节点还没有加进 levelList 中，所以 levelList 中的最后一层即为上一层）
            List<TreeNode> upperLevel=  levelList.get(levelList.size() - 1);
            
            for (TreeNode node : upperLevel) {
                // 取出 node 左右子树的值，并创建相应子节点
                if (proccesedNodeNum < values.length) {
                    String left = values[proccesedNodeNum++];
                    node.left = ("null".equals(left) ? null : new TreeNode(Integer.parseInt(left)));
                    if (null != node.left) {
                        curLevel.add(node.left); // 将子节点添加到当前层中
                    }
                }
                
                if (proccesedNodeNum < values.length) {
                    String right = values[proccesedNodeNum++];
                    node.right = ("null".equals(right) ? null : new TreeNode(Integer.parseInt(right)));
                    if (null != node.right) {
                        curLevel.add(node.right);
                    }
                }
            }
            
            levelList.add(curLevel); // 将当前层节点添加到 levelList 中
        }
        
        return root;
    }
}
