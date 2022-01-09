package bit;

/**
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 * 
 * 题目描述：编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
 *        请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
 *        因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 *        
 * 限制条件：输入必须是长度为 32 的 二进制串 
 * 
 * 示例：
 *  示例 1
 *      输入：n = 11 (控制台输入 00000000000000000000000000001011)
 *      输出：3
 *      解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *      
 *  示例 2
 *      输入：n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
 *      输出：31
 *      解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *      
 */
public class Problem15 {

    public static void main(String[] args) {
        // test case1, output: 3
//        int n = 11;
        
        // test case1, output: 31
        int n = -3;
        
//        _15Solution1 solution = new _15Solution1();

//        _15Solution2 solution = new _15Solution2();

        _15Solution3 solution = new _15Solution3();
        
        System.out.println(solution.hammingWeight(n));
    }
}

/**
 * 解法一：利用库函数
 */
class _15Solution1 {
    
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}

/**
 * 解法二：位运算（逐个消除 n 二进制串的 1）
 *      假设 n 对应二进制串为 bit，bit 中最右边 1 的位置是 i
 *      n = n & (n - 1)，其作用有两个：
 *        （1） n - 1 使 n 中使 bit[i] 变成 0，但是在该过程中，bit[i] 后面的 0 可能会变成 1，为了消除这些 1，后面还需要使用 &
 *        （2） n & (n -1) ，& 可以使得 bit[i] 后面可能的 1 重新变成 0 
 *      
 *      以 n = 11 为例，过程如下：
 *       n    1011
 *       n-1  1010     使得 bit[0] 变成 0
 *       &    1010
 *          
 *       n    1010
 *       n-1  1001     使得 bit[1] 变成 0，但是导致 bit[1] 后面的 bit[0] 变成了 1，为了消除 bit[0] 处的 1，因此后面还需要使用 &
 *       &    1000     消除 n-1 中 bit[0] 处的 1
 *          
 *       n    1000
 *       n-1  0111
 *       &    0000
 *       结束
 */
class _15Solution2 {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1; // 消除 n 对应二进制串中的一个 1
            ++count;
        }
        
        return count;
    }
}

/**
 * 解法三：位运算（将 n 的二进制串中所有位相加）
 *      以 n = 11 为例，n 对应的二进制串为 1011，所有位相加： 1 + 0 + 1 + 1 = 3
 */
class _15Solution3 {
    
    public int hammingWeight(int n) {
        int num = n;
        int count = 0;
        for (int i = 0; i < 32; ++i) {
            // 取出 n 二进制串中的每一位，然后相加，最终的和即为 1 的个数
            int res = num & 1; // 取出 num 二进制串的最低位
            count = count + res; // 累加到 count 中
            
            num = num >>> 1; // 丢掉 num 二进制串的最低位，因为其已经统计过
            
        }
        
        return count;
    }
}
