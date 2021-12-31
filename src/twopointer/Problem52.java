package twopointer;

import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 * 
 * 题目描述：输入两个链表，找出它们的第一个公共节点。
 * 
 * 
 * 限制条件：
 *  （1）如果两个链表没有交点，返回 null
 *  （2）在返回结果后，两个链表仍须保持原有的结构
 *  （3）可假定整个链表结构中没有循环
 *  （4）程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存
 * 
 * 示例：
 *   A：       a1 -> a2  
 *                     \
 *                      -> c1 -> c2 -> c3
 *                     /
 *   B：b1 -> b2 -> b3
 *
 *  如上面两个链表 A、B ，它们在节点 c1 处开始相交。
 */
public class Problem52 {

    public static void main(String[] args) {
        int[] nums= { 8, 4, 5 };
        
        ListNode commHead = ListUtil.createList(nums);
        
        // list a: 4, 1, 8, 4, 5
        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = commHead;
        
        // list b: 5, 0, 1, 8, 4, 5
        ListNode headB = new ListNode(5);
        headB.next = new ListNode(0);
        headB.next.next = new ListNode(1);
        headB.next.next.next = commHead;
        
//        _52Solution1 solution = new _52Solution1();
        
        _52Solution2 solution = new _52Solution2();
        
        // output: 8
        System.out.println(solution.getIntersectionNode(headA, headB));
    }
}

/**
 * 解法一：使用 set
 */
class _52Solution1 {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        
        ListNode cur = headA;
        while (null != cur) {
            set.add(cur);
            cur = cur.next;
        }
        
        cur = headB;
        while (null != cur) {
            if (set.contains(cur)) {
                return cur;
            }
            
            cur = cur.next;
        }
        
        return null;
    }
}

/**
 * 解法二：使用双指针
 */
class _52Solution2 {
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aCur = headA;
        ListNode bCur = headB;
        
        while (null != aCur || null != bCur) {
            if (aCur == bCur) { // 有可能 aCur 和 bCur 一开始就相等，所以先判断两个是否相等
                return aCur;
            }
            
            if (aCur != null) {
                aCur = aCur.next;
            } else {
                aCur = headB;
            }
            
            if (bCur != null) {
                bCur = bCur.next;
            } else {
                bCur = headA;
            }
        }
        
        return null;
    }
}


