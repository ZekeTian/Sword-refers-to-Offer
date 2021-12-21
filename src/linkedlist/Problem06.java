package linkedlist;

import java.util.Arrays;
import java.util.Stack;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 * 
 * 题目描述：输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 
 * 限制条件：0 <= 链表长度 <= 10000
 * 
 * 示例：
 *  输入：head = [1,3,2]
 *  输出：[2,3,1]
 *
 */
public class Problem06 {

    public static void main(String[] args) {
        int[] nums = { 1, 3, 2 };
        
//        _06Solution1 solution = new _06Solution1();
        _06Solution2 solution = new _06Solution2();
        
        ListNode head = ListUtil.createList(nums);

        int[] arr = solution.reversePrint(head);
        
        System.out.println(Arrays.toString(arr));
        
    }
}

/**
 * 解法一：利用栈（或者用递归）
 */
class _06Solution1 {
    
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<Integer>();
        ListNode cur = head;
        while (null != cur) {
            stack.push(cur.val);
            cur = cur.next;
        }
        
        int[] resultArr = new int[stack.size()];
        for (int i = 0; i < resultArr.length; ++i) {
            resultArr[i] = stack.pop();
        }
        
        return resultArr;
    }
}


/**
 * 解法二：两次遍历，第一次遍历获取长度，第二次遍历反着放数字
 */
class _06Solution2 {
    
    public int[] reversePrint(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (null != cur) {
            ++len;
            cur = cur.next;
        }

        int[] resultArr = new int[len];
        cur = head;
        int count = 0;
        while (null != cur) {
            resultArr[len - 1 - count] = cur.val; // 在数组中反方向开始放元素
            cur = cur.next;
            ++count;
        }
        
        return resultArr;
    }
}


