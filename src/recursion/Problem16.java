package recursion;

/**
 * https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 * 
 * 题目描述：实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * 
 * 限制条件：
 *  （1）-100.0 < x < 100.0
 *  （2）-2^31 <= n <= 2^31 - 1
 *  （3）-10^4 <= x^n <= 10^4
 * 
 * 示例：
 *  示例 1
 *      输入：x = 2.00000, n = 10
 *      输出：1024.00000
 *      
 *  示例 2
 *      输入：x = 2.00000, n = -2
 *      输出：0.25000
 *      解释：2-2 = 1/22 = 1/4 = 0.25
 *
 */
public class Problem16 {

    public static void main(String[] args) {
        
        // test case1, output: 1024.0
        double x = 2.0;
        int n = 10;
        
        // test case2, output: 0.25
//        double x = 2.0;
//        int n = -2;
        
//        _16Solution1 solution = new _16Solution1();

        _16Solution2 solution = new _16Solution2();
        
        System.out.println(solution.myPow(x, n));
    }
}

/**
 * 解法一：递归
 */
class _16Solution1 {
    
    private double pow(double x, long n) {
        if (0 == n) {
            return 1;
        }
        if (1 == n) {
            return x;
        }
        
        if (n % 2 == 0 ) { // x^n = (x^2)^(n/2)，如 x^8 = (x^2)^4
            return pow(x * x, n / 2);
        } else { // n 为奇数时，先提出一个 x ，从而使得 n-1 为偶数
            return x * pow(x * x, n / 2);
        }
    }
    
    public double myPow(double x, int n) {
        if (0 == n) {
            return 1;
        }
        
        // 因为当 n = Integer.MIN_VALUE 时，-n 依然是 Integer.MIN_VALUE，最终会堆栈溢出。为了能够得到 n 正确的相反值，所以将 n 转换成 long 类型
        double res = pow(x, Math.abs((long)n)); 
        
        return (n > 0 ? res : 1 / res);
    }
}

/**
 * 解法二：利用循环实现
 */
class _16Solution2 {
    public double myPow(double x, int n) {
        long absN = Math.abs((long)n);
        double res = 1.0;
        
        for (long i = absN; i > 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x; // 无论 n 是偶数还是奇数，此处代码一定会执行到
            }
            x *= x; // x 翻倍，然后次数 i 减半。如：所求为 2^8， x = 2^2 时， i = 4；当 x = 2^4 时， i = 2
        }
        
        return (n > 0 ? res : 1 / res);
    }
}
