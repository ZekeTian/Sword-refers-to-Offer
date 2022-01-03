package recursion;

import java.util.ArrayList;
import java.util.List;

import recursion._36Solution1.Node;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 * 
 * 题目描述：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 
 * 示例：
 *          4
 *         / \
 *        2   5
 *       / \
 *      1  3
 *  我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *  下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *      -------------------------
 *      |/---↘ /---↘ /---↘ /---↘↓
 *      1     2     3     4     5
 *      ↑↖---/ ↖---/ ↖---/ ↖---/|
 *     -------------------------
 *  特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *  
 */
public class Problem36 {
    

    public static void main(String[] args) {
        _36Solution1 node = new _36Solution1();
        
        _36Solution1.Node root = node.new Node(4);
        _36Solution1.Node node2 = node.new Node(2);
        _36Solution1.Node node5 = node.new Node(5);
        root.left = node2;
        root.right = node5;
        
        _36Solution1.Node node1 = node.new Node(1);
        _36Solution1.Node node3 = node.new Node(3);
        node2.left = node1;
        node2.right = node3;
        
//        _36Solution1 soluton = new _36Solution1();
        
        _36Solution2 soluton = new _36Solution2();
        
        Node head = soluton.treeToDoublyList(root);
        node.print(head);
    }
}


/**
 * 解法一：使用 List 存储所有节点，然后再转换成链表
 */
class _36Solution1 {
    
    public class Node {
        public int val;
        public Node left;
        public Node right;
        
        public Node() {}
        
        public Node(int val) {
            this.val = val;
        }
        
        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    private List<Node> list = new ArrayList<>();
    
    private void traverse(Node root) {
        if (null == root) {
            return;
        }
        
        traverse(root.left);
        list.add(root);
        traverse(root.right);
    }
    
    public Node treeToDoublyList(Node root) {
        // 遍历二叉搜索树，将其转换成线性的链表结构
        traverse(root);
        Node head = null;
        if (0 == list.size()) {
            return head;
        }
        
        // 正向（后继）
        for (int i = 0; i < list.size() - 1; ++i) {
            list.get(i).right = list.get(i + 1);
        }
        list.get(list.size() - 1).right = list.get(0);
        
        // 反向（前驱）
        for (int i = 1; i < list.size(); ++i) {
            list.get(i).left = list.get(i - 1);
        }
        list.get(0).left = list.get(list.size() - 1);
        
        head = list.get(0);
        
        return head;
    }
    
    public void print(Node head) {
        if (null == head) {
            return;
        }
        
        System.out.print(head.val + " ");

        Node cur = head.right;
        while (cur != head) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        
        System.out.println();
    }
}

/**
 * 解法二：直接在中序遍历时转换成链表
 */
class _36Solution2 {
    
    private Node head = null; // 记录链表的头节点
    private Node pre = null; // 记录遍历时的前一个节点
    
    private void convert2List(Node cur) {
        if (null != cur.left) {
            convert2List(cur.left);
        }
        
        if (null == pre) { // pre 为 null，则 cur 是第一个节点，即头节点
            head = cur; 
        } else { // 连接 pre、cur 两个节点
            pre.right = cur;
            cur.left = pre;
        }
        pre = cur;
        
        if (null != cur.right) {
            convert2List(cur.right);
        }
    }
    
    public Node treeToDoublyList(Node root) {
        if (null == root) {
            return null;
        }
        
        convert2List(root);
        // 首尾两个节点相连
        head.left = pre;
        pre.right = head;
        
        return head;
    }
}

