package recursion;

/**
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 * 
 * 题目描述：求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 
 * 限制条件：1 <= n <= 10000
 * 
 * 示例：
 *  示例 1
 *      输入: n = 3
 *      输出: 6
 *  
 *  示例 2
 *      输入: n = 9
 *      输出: 45
 *
 */
public class Problem64 {

    public static void main(String[] args) {
        // test case1, output: 6
        int n = 3;
        
        // test case2, output: 45
//        int n = 9;
        
//        _64Solution1 solution = new _64Solution1();

        _64Solution2 solution = new _64Solution2();
        
        
        System.out.println(solution.sumNums(n));
    }
}


/**
 * 解法一：递归 + if 判断终止
 *      题目中要求不能用 if，所以此种写法不满足条件
 */
class _64Solution1 {
    
    public int sumNums(int n) {
        if ( 1 == n) {
            return 1;
        }
        
        return sumNums(n - 1) + n;
    }
}

/**
 * 解法二：递归 + 断路与终止
 */
class _64Solution2 {
    
    public int sumNums(int n) {
        boolean flag = (n > 1) && (n += sumNums(n - 1)) > 0; // 使用断路与，可以使得当 n = 1 时，后面的 sumNums 不被调用，从而终止递归。
        // 此外，为了让最终的 return 语句能够在 n <= 1 和 n > 1 时保持一致，还需要将 sumNum 的结果加到 n 上
        
        return n;
    }
}
