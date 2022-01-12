package math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 * 
 * 题目描述：0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 *        例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * 
 * 限制条件：
 *  （1）1 <= n <= 10^5
 *  （2）1 <= m <= 10^6
 * 
 * 示例：
 *  示例 1
 *      输入: n = 5, m = 3
 *      输出: 3
 *  
 *  示例 2
 *      输入: n = 10, m = 17
 *      输出: 2
 *      
 */
public class Problem62 {

    public static void main(String[] args) {
        // test case1, output: 3
//        int n = 5, m = 3;

        // test case2, output: 2
//        int n = 10, m = 17;
        
        // test case3, output: 4
//        int n = 5, m = 1;
        
        // test case4, output: 64165 （solution1 会超时）
        int n = 70866, m = 116922;
        
//        _62Solution1 solution = new _62Solution1();

        _62Solution2 solution = new _62Solution2();
        
        System.out.println(solution.lastRemaining(n, m));
    }
}

/**
 * 解法一：使用链表模拟
 */
class _62Solution1 {
    
    public int lastRemaining(int n, int m) {
        LinkedList<Integer> list = new LinkedList<>();
        
        for (int i = 0; i < n; ++i) {
            list.add(i);
        }
        
        int count = 0;
        while (list.size() > 1) {
            List<Integer> delList = new ArrayList<>();
            // 记录待删除数字的下标
            for (int i = 0; i < list.size() && delList.size() < list.size() - 1 /* 不能将 list 中所有元素删除完 */; ++i) {
                ++count;
                if (count % m == 0) { // 计数已经满 m 个，则当前数字需要删除
                    delList.add(i);
                    count = 0;
                }
            }
            
            // 删除对应的数字（注意：是从后向前删除）
            for (int i = delList.size() - 1; i >= 0; --i) {
                list.remove((int)(delList.get(i)));
            }
        }
        
        return list.get(0);
    }
}


/**
 * 解法二：数学解法
 */
class _62Solution2 {
    
    public int lastRemaining(int n, int m) {
        int[] memo = new int[n + 1]; // memo[i] 表示长度为 i 时，剩下数字的下标
        memo[1] = 0; // 长度为 1 时，则剩下数字的下标为 0
        
        for (int i = 2; i <= n; ++i) {
            memo[i] = (memo[i - 1] + m) % i; // 在上一次的结果之上，右移 m 位。因为可能会越界，所以模上当前长度
        }
        
        return memo[n];
    }
}
