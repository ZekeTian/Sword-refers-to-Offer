package linkedlist;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 *
 * 题目描述：定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 
 * 限制条件：0 <= 节点个数 <= 5000
 * 
 * 示例：
 *  输入: 1->2->3->4->5->NULL
 *  输出: 5->4->3->2->1->NULL
 * 
 */
public class Problem24 {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        
        ListNode head = ListUtil.createList(nums);
        
//        _24Solution1 solution = new _24Solution1();

        _24Solution2 solution = new _24Solution2();
        
        head = solution.reverseList(head);
        
        ListUtil.print(head);
                
    }
}

/**
 * 解法一：循环
 */
class _24Solution1 {
    
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        
        return pre;
    }
}

/**
 * 解法二：递归
 */
class _24Solution2 {
    ListNode newHead = null;

    private ListNode reverse(ListNode head) {
        if (null == head.next) {
            newHead = head; // 记录最后一个链表节点
            return head;
        }

        ListNode node = reverse(head.next);
        node.next = head;
        return head;
    }

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }

        reverse(head);
        head.next = null;

        return newHead;
    }
}
