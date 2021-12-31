package twopointer;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 * 
 * 题目描述：输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 
 * 限制条件：0 <= 链表长度 <= 1000
 * 
 * 示例：
 *  输入：1->2->4, 1->3->4
 *  输出：1->1->2->3->4->4
 *
 */
public class Problem25 {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 4 };
        int[] nums2 = { 1, 3, 4 };
        
//        _25Solution1 solution = new _25Solution1();

        _25Solution2 solution = new _25Solution2();
        
        
        ListNode l1 = ListUtil.createList(nums1);
        ListNode l2 = ListUtil.createList(nums2);
        
        ListNode head = solution.mergeTwoLists(l1, l2);
        
        ListUtil.print(head);
    }
}

/**
 * 解法一：递归合并
 */
class _25Solution1 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1 && null == l2) {
            return null;
        }

        if (null == l1) {
            return l2;
        }

        if (null == l2) {
            return l1;
        }

        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }
}

/**
 * 解法二：循环合并
 */
class _25Solution2 {
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode dummyHead = new ListNode(0);
        ListNode pre = dummyHead;
        ListNode cur = null;
        
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur = cur1;
                cur1 = cur1.next;
            } else {
                cur = cur2;
                cur2 = cur2.next;
            }
            
            pre.next = cur;
            pre = cur;
        }
        
        if (null != cur1) {
            pre.next = cur1;
        }
        if (null != cur2) {
            pre.next = cur2;
        }
        
        return dummyHead.next;
    }
}

