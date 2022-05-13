package dynamicprogramming;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

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
        // test case1, output: 12
//        int n = 10;
        
        // test case2, output: 536870912
        int n = 1407;
        
//        _49Solution1 solution = new _49Solution1();
        
//        _49Solution2 solution = new _49Solution2();
        
        _49Solution3 solution = new _49Solution3();
        
        
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
 *       后面的丑数实际上可以由前面的丑数生成，例如：
 *          初始时只有 1，然后乘以 2、3、5，得到 1、[2、3、5]。其中，[2、3、5] 表示未处理的数字；
 *          然后根据 2 再扩展，即 2 乘以 2、3、5，得到 1、2、[3、4、5、6、10]。其中，[3、4、5、6、10] 表示未处理的数字；
 *          然后根据 3 再扩展，即 3 乘以 2、3、5，得到 1、2、3、[4、5、6、9、10、15]。其中，[4、5、6、9、10、15] 表示未处理的数字；
 *          ...
 *       从上面的过程可知，每次都是从未处理的数字中取出第一个数字（即最小的数字），然后进行扩展。
 *       但是，需要额外注意的是，列表是有序、无重复的，因此可以考虑使用 TreeSet 存储。
 */
class _49Solution2 {
    
    public int nthUglyNumber(int n) {
        Set<Long> set = new TreeSet<>(); // 使用 TreeSet，一是为了避免重复，二是方便取最小的数字 
        set.add(1L);
        
        long num = 0;
        for (int i = 0; i < n; ++i) {
            // 取出 set 中最小的数字，然后再进行扩展
            num = set.iterator().next();
            
            // 根据 num 进行扩展。因为在进行乘法运算时，可能会溢出，所以在前面定义数据类型时，使用 long 
            set.add(2 * num);
            set.add(3 * num);
            set.add(5 * num);
            
            // num 已经处理过，则将其从 set 中删除
            set.remove(num);
        }
        
        return (int) num;
    }
    
}

/**
 * 解法三：动态规划（三指针）
 *        创建一个 memo 数组，其中 memo[i] 表示第 i 个丑数。 
 *        memo 数组中，每个数字都可以乘以 2、3、5。为了区分哪些数字已经乘以了 2、3、5，哪些数字没有乘以 2、3、5，
 *        我们使用三个指针 p2, p3, p5 分别表示。
 *          memo[1 ... p2-1] 表示已经乘以了 2
 *          memo[1 ... p3-1] 表示已经乘以了 3
 *          memo[1 ... p5-1] 表示已经乘以了 5
 *        每次生成一个新的丑数时，都用 memo[p2] * 2，memo[p3] * 3，memo[p5] * 5，然后取三个数字中的最小值作为新的丑数。
 *        之后，再根据新的丑数更新 p2、p3、p5 的位置。
 *        
 *        实际上，该过程也相当于把 memo 数组复制成三个，然后 p2、p3、p5 各自负责遍历一个数组，然后再将三个数组中当前值的最小值
 *        添加到 memo 中。整体的过程类似于归并排序。
 */
class _49Solution3 {
    
    public int nthUglyNumber(int n) {
        int[] memo = new int[n + 1]; // memo[i] 表示第 i 个丑数
        memo[1] = 1;
        int p2 = 1; // memo[1 ... p2-1] 表示已经乘以了 2
        int p3 = 1; // memo[1 ... p3-1] 表示已经乘以了 3
        int p5 = 1; // memo[1 ... p5-1] 表示已经乘以了 5
        
        for (int i = 2; i <= n; ++i) {
            // p2、p3、p5 对应的数字分别乘以 2、3、5
            int num2 = memo[p2] * 2;
            int num3 = memo[p3] * 3;
            int num5 = memo[p5] * 5;
            
            // 从 num2、num3、num5 中取出最小值，将其作为第 i 个丑数
            int num = Math.min(num2, Math.min(num3, num5));
            memo[i] = num;

            // 更新 p2、p3、p5 的位置
            if (num == num2) {
                ++p2; // p2 位置可以得到 num，则更新 p2
            }
            
            if (num == num3) {
                ++p3;
            }
            
            if (num == num5) {
                ++p5;
            }
        }
        
        return memo[n];
    }
    
}

