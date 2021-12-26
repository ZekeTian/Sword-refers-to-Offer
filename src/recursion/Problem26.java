package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 * 
 * 题目描述：输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *        B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * 
 * 限制条件：0 <= 节点个数 <= 10000
 * 
 * 示例：
 *  给定的树 A          给定的树 B
 *           3             4
 *         /  \           /
 *        4   5          1
 *       / \
 *      1  2
 */
public class Problem26 {

    public static void main(String[] args) {
        // test case1, ouptut: true
//        TreeNode nodeA3 = new TreeNode(3);
//        TreeNode nodeA4 = new TreeNode(4);
//        TreeNode nodeA5 = new TreeNode(5);
//        nodeA3.left = nodeA4;
//        nodeA3.right = nodeA5;
//        
//        TreeNode nodeA1 = new TreeNode(1);
//        TreeNode nodeA2 = new TreeNode(2);
//        nodeA4.left = nodeA1;
//        nodeA4.right = nodeA2;
//        
//        TreeNode nodeB4 = new TreeNode(4);
//        TreeNode nodeB1 = new TreeNode(1);
//        nodeB4.left = nodeB1;
        
//        _26Solution solution = new _26Solution();
//        System.out.println(solution.isSubStructure(nodeA3, nodeB4));
        
        // test case1, ouptut: true
        TreeNode node41 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node41.left = node2;
        node41.right = node3;
        
        TreeNode node42 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.left = node42;
        node2.right = node5;

        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node3.left = node6;
        node3.right = node7;
        
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        node42.left = node8;
        node42.right = node9;
        
        
        TreeNode node4B = new TreeNode(4);
        TreeNode node8B = new TreeNode(8);
        TreeNode node9B = new TreeNode(9);
        node4B.left = node8B;
        node4B.right = node9B;
        
        _26Solution solution = new _26Solution();
        System.out.println(solution.isSubStructure(node41, node4B));
    }
}


class _26Solution {
    
    // 判断 A 树是否含有 B 树
    private boolean isContains(TreeNode A, TreeNode B) {
        if (null == B) {
            return true;  // 如果 B 树为空，则认为 A 树中含有 B 树
        }
        if (null == A || A.val != B.val) {
            return false; // A 树为空或 A、B 两个节点的值不一样，则认为不包含
        }
        
        return isContains(A.left, B.left) && isContains(A.right, B.right);
    }
    
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (null == A || null == B) {
            return false; // 只要有一个节点是空，则认为不是子结构
        }
        
        // 总共有三种可能方式，只要有一种成立即认为最终结果成立
        boolean res = isContains(A, B) // 用以 A 为根顶点的树去匹配子结构 B
                        || isSubStructure(A.left, B) // 用 A 的左子树去匹配子结构 B
                        || isSubStructure(A.right, B); // 用 A 的右子树去匹配子结构 B
        
        return res;
    }
}
