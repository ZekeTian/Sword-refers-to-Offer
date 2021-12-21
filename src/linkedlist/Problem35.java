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

    public static void main(String[] args) {
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
}

/**
 * 借助 Map 实现
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