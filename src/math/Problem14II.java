package math;

/**
 * https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
 * 
 * 题目描述：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 *        请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *        答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *        
 * 限制条件：2 <= n <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入: 2
 *      输出: 1
 *      解释: 2 = 1 + 1, 1 × 1 = 1
 *      
 *  示例 2
 *      输入: 10
 *      输出: 36
 *      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 
 */
public class Problem14II {

    public static void main(String[] args) {
//        int n = 2;

        int n = 10;
        
        _14IISolution solution = new _14IISolution();
        
        System.out.println(solution.cuttingRope(n));
    }
}

/**
 * 贪心法。每次尽可能切长度为 3 的，可以保证最终得到的乘积是最大的。
 */
class _14IISolution {
    
    public int cuttingRope(int n) {
        if (n <= 3) {
            return n - 1; // n = 2 = 1 + 1 ，返回 1； n = 3 = 1 + 2，返回 2
        }
        
        int mod = (int) (1e9) + 7;
        long res = 1; // int 整数可能会溢出，所以使用 long 类型
        
        while (n > 4) {
            n -= 3; // 每次切出长度为 3 的绳子
            res *= 3; // 因为已经切出一段长度为 3 的绳子，所以乘积上再累乘 3 
            res = res % mod; // 为避免溢出，再模上 mod
        }
        
        return (int)((res * n) % mod); // 因为最后还剩下一段长度，所以还要乘上 n
    }
}

