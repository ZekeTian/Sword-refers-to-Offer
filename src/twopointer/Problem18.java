package twopointer;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 * 
 * 题目描述：给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
 * 
 * 限制条件：题目保证链表中节点的值互不相同
 * 
 * 示例：
 *  示例 1
 *      输入: head = [4,5,1,9], val = 5
 *      输出: [4,1,9]
 *      解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 *      
 *  示例 2
 *      输入: head = [4,5,1,9], val = 1
 *      输出: [4,5,9]
 *      解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *
 */
public class Problem18 {

    public static void main(String[] args) {
        int[] nums = { 4, 5, 1, 9 };
        
        ListNode head = ListUtil.createList(nums);
        
        _18Solution solution = new _18Solution();
        
        System.out.println("删除之前的链表：");
        ListUtil.print(head);
        
        head = solution.deleteNode(head, 5);
                
        System.out.println("删除之后的链表：");
        ListUtil.print(head);
    }
}


class _18Solution {
    
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        
        ListNode pre = dummyHead;
        ListNode cur = head;
        
        while (null != cur && val != cur.val) {
            pre = cur;
            cur = cur.next;
        }
        
        if (null != cur) {
            pre.next = cur.next;
            cur.next = null;
        }
        
        return dummyHead.next;
    }
}
