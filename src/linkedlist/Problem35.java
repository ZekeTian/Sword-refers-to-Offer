package linkedlist;

import java.util.HashMap;
import java.util.Map;

import linkedlist._35Solution1.Node;

/**
 * https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 * 
 * 题目描述：请实现 copyRandomList 函数，复制一个复杂链表。
 *        在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 限制条件：
 *  （1）-10000 <= Node.val <= 10000
 *  （2）Node.random 为空（null）或指向链表中的节点。
 *  （3）节点数目不超过 1000 。
 *  
 * 示例：可以参考题目中的图片
 * 
 */
public class Problem35 {
    
    private static void testSolution1() {
        _35Solution1 solution = new _35Solution1();
        
        Node node1 = solution.new Node(3);
        Node node2 = solution.new Node(3);
        Node node3 = solution.new Node(3);
        
        node1.next = node2;
        node1.random = null;
        
        node2.next = node3;
        node2.random = node1;
        
        node3.next = node3.random = null;
        
        Node head = solution.copyRandomList(node1);
        
        Node cur = head;
        while (null != cur) {
            System.out.println(cur + " ");
            cur = cur.next;
        }
    }

    private static void testSolution2() {
        _35Solution2 solution = new _35Solution2();
        
        _35Solution2.Node node1 = solution.new Node(3);
        _35Solution2.Node node2 = solution.new Node(3);
        _35Solution2.Node node3 = solution.new Node(3);
        
        node1.next = node2;
        node1.random = null;
        
        node2.next = node3;
        node2.random = node1;
        
        node3.next = node3.random = null;
        
        _35Solution2.Node head = solution.copyRandomList(node1);
        
        _35Solution2.Node cur = head;
        while (null != cur) {
            System.out.println(cur + " ");
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        
//        testSolution1();

        testSolution2();

    }
}

/**
 * 解法一：借助 Map 实现
 */
class _35Solution1 {
     class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
        
        @Override
        public String toString() {
            return  val + "";
        }
    }
    
    private Map<Node, Node> map = new HashMap<>(); // key: 旧节点，value：复制后的新节点
    
    public Node copyRandomList(Node head) {
        Node dummyHead1 = new Node(0); 
        dummyHead1.next = head;
        Node dummyHead2 = new Node(0);
        
        // cur1、cur2 分别对应旧链表、新链表中的节点，但是 cur1 比 cur2 快一步
        Node cur1 = head;
        Node cur2 = dummyHead2;
        
        // 复制节点，并用 map 保存对应关系
        while (null != cur1) {
            cur2.next = new Node(cur1.val);
            map.put(cur1, cur2.next);
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        
        // 复制 random 的指向关系
        cur1 = dummyHead1;
        cur2 = dummyHead2;
        while (null != cur1) {
            if (null != cur1.random) {
                cur2.random = map.get(cur1.random); // cur1 的 random 不为空，则 cur2 也不能为空，将 cur2 指向对应的复制节点
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        
        return dummyHead2.next;
    }
}

/**
 * 解法二：将复制的节点插入到原来链表节点的后面，这样便满足下面的关系：
 *      复制节点的 random = 原来节点 random 的下一个节点。
 *      如下图所示：a' 是 a 的复制节点。a 的 random 节点是 b，a' 的 random 节点是 b'，b' 是 b 的下一个节点。
 *       ---------
 *      /         ↘
 *      a -> a' -> b -> b' -> c -> c' -> null
 *           \         ↗
 *            ---------
 * 具体思路如下：
 *     第一遍循环，复制节点，并将复制节点添加到原节点后面
 *     第二遍循环，复制 random 的引用关系（即利用上面的关系）
 *     第三遍循环，将复制节点和原节点断开
 */
class _35Solution2 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
        
        @Override
        public String toString() {
            return  val + "";
        }
    }
    
    public Node copyRandomList(Node head) {
        if (null == head) {
            return null;
        }
     
        // 第一遍循环，复制节点，并插入到原节点后面
        Node cur = head;
        while (null != cur) {
            Node copyNode = new Node(cur.val); // 复制节点，然后插入到原节点后面
            Node next = cur.next;
            copyNode.next = next;
            cur.next = copyNode;
            
            cur = next; // 向后移动，继续复制
        }
        
        // 第二遍循环，复制 random 引用关系
        cur = head;
        while (null != cur) {
            Node copyNode = cur.next; // 复制节点在原节点后面
            Node next = copyNode.next;
            
            if (null != cur.random) {
                copyNode.random = cur.random.next; // 复制节点的 random 是在原节点的 random 后面
            }
            
            cur = next; // 向后移动，继续复制
        }
        
        // 第三遍循环，将原节点和复制节点断开
        Node newHead = head.next;
        cur = head;
        while (null != cur) {
            Node copNode = cur.next;
            Node next = copNode.next;
            
            cur.next = next; // 原节点指向原来的 next
            if (null != next) { // 如果 cur 是最后一个节点，next 可能为空，所以此处要判空处理
               copNode.next = next.next; // 复制节点指向复制的 next
            } else {
                copNode.next = null;
            }
            
            cur = next; // 向后移动，继续断开操作
        }
        
        return newHead;
    }
}
