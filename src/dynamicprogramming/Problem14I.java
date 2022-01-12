package dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 * 
 * 题目描述：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
 *        请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *  
 * 限制条件：2 <= n <= 58
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
public class Problem14I {

    public static void main(String[] args) {
        int n = 2;
        
//        int n = 10;
        
        _14ISolution solution = new _14ISolution();
        
        System.out.println(solution.cuttingRope(n));
    }
}


class _14ISolution {
    
    public int cuttingRope(int n) {
        if (n < 1) {
            return 0;
        }
        
        int[] memo = new int[n + 1]; // memo[i] 表示长度为 i 的绳子的结果
        memo[0] = memo[1] = 1;
        
        for (int i = 2; i <= n; ++i) {
            int max = 0;
            for (int j = 1; j < i; ++j) { // 长度为 i 的绳子，切出长度为 j 的绳子，剩余长度 i - j
                
                // 剩余长度 i-j 的绳子，可以继续切，也可以不切
                max = Math.max(j * memo[i - j], max); // 继续切
                max = Math.max(j * (i - j), max); // 不继续切，则只有长度为 i 和长度为 i-j 的两条绳子，所以直接相乘
            }
            
            memo[i] = max;
        }
        
        return memo[n];
    }
}
