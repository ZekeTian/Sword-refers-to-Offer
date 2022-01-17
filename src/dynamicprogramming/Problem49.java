package dynamicprogramming;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/chou-shu-lcof/
 *
 * 题目描述：我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 
 * 限制条件：
 *  （1）1 是丑数。
 *  （2）n 不超过1690。
 * 
 * 示例：
 *  输入: n = 10
 *  输出: 12
 *  解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 
 */
public class Problem49 {

    public static void main(String[] args) {
//        int n = 10;
        
        int n = 1407;
        
        _49Solution1 solution = new _49Solution1();
        
        
        System.out.println(solution.nthUglyNumber(n));
    }
}


/**
 * 解法一：使用最小堆 + 集合
 *      后面的丑数实际上都可以通过前面的丑数乘以 2、3、5 得到。
 *      为了得到升序的丑数，所以需要使用最小堆记录目前最小的丑数，然后用该丑数分别乘以 2、3、5 得到新丑数，之后将新丑数添加到最小堆中。
 *      但是需要注意的是，这种解法会导致重复的数。如：6 = 2 * 3 = 3 * 2，其可以通过丑数 2 乘以 3 得到，也可以通过丑数 3 乘以 2 得到。
 *      如果不加以限制，6 会被重复添加到最小堆中，这样会造成出现重复的丑数。为了避免数字被重复加入到最小堆中，可以使用集合记录曾添加到最小堆中的丑数。
 */
class _49Solution1 {
    
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>(); // 记录曾经添加到最小堆中的丑数。使用 long 类型，是为了防止溢出
        PriorityQueue<Long> heap = new PriorityQueue<>();
        int[] factors = { 2, 3, 5 };
        
        set.add(1L);
        heap.add(1L);
        for (int i = 1; i < n; ++i) {
            long minUgly = heap.poll();
            
            for (int f : factors) {
                long newUgly = minUgly * f;
                if (!set.contains(newUgly)) {
                    set.add(newUgly);
                    heap.add(newUgly);
                }
            }
        }
        
        return heap.peek().intValue();
    }
}

/**
 * 解法二：使用 TreeSet 
 */
class _49Solution2 {
    
}

