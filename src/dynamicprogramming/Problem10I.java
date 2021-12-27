package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 * 
 * 题目描述：写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *          F(0) = 0, F(1) = 1
 *          F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *       斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *       答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 限制条件：0 <= n <= 100
 * 
 * 
 * 示例：
 *  示例 1
 *      输入：n = 2
 *      输出：1
 *      
 *  示例 2
 *      输入：n = 5
 *      输出：5
 */
public class Problem10I {

    public static void main(String[] args) {
        // test case1, output: 1
//        int n = 2;
        
        // test case2, output: 5
        int n = 5;
        
//        _10Solution1 solution = new _10Solution1();
        
//        _10Solution2 solution = new _10Solution2();

        _10Solution3 solution = new _10Solution3();
        
        System.out.println(solution.fib(n));
    }
}

/**
 * 解法一：递归
 */
class _10Solution1 {
    
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        
        return (fib(n - 1) + fib(n - 2)) % 1000000007;
    }
}

/**
 * 解法二：递归 + 记忆化
 */
class _10Solution2 {
    
    private int[] memo = null;
    
    private int getFib(int n) {
        if (-1 != memo[n]) {
            return (int) memo[n];
        }
        
        memo[n] = (getFib(n - 1) + getFib(n - 2)) % 1000000007;
        
        return (int) memo[n];
    }
    
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        memo[1] = 1;
        
        
        return getFib(n);
    }
}

/**
 * 解法三：动态规划
 */
class _10Solution3 {
    
    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        
        for (int i = 2; i <= n; ++i) {
            memo[i] = (memo[i - 1] + memo[i - 2]) % 1000000007;
        }
        
        return memo[n];
    }
}

