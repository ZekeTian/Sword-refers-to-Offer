package dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * 
 * 题目描述：一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *        答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 
 * 限制条件：0 <= n <= 100
 * 
 * 示例：
 *   示例 1
 *      输入：n = 2
 *      输出：2
 *      
 *   示例 2
 *      输入：n = 7
 *      输出：21
 *      
 *   示例 3
 *      输入：n = 0
 *      输出：1
 */
public class Problem10II {

    public static void main(String[] args) {
        // test case1, output: 2
//        int n = 2;
        
        // test case2, output: 21
        int n = 7;
        
        _10IISolution solution = new _10IISolution();
        
        
        System.out.println(solution.numWays(n));
    }
}


class _10IISolution {
    
    public int numWays(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] memo = new int[n + 1];
        memo[0] = memo[1] = 1;
        
        for (int i = 2; i <= n; ++i) {
            memo[i] = (memo[i - 1] + memo[i - 2]) % 1000000007;
        }
        
        return memo[n];
    }
}

