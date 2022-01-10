package bit;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 * 
 * 题目描述：一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 
 * 限制条件：2 <= nums.length <= 10000
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [4,1,4,6]
 *      输出：[1,6] 或 [6,1]
 *      
 *  示例 2
 *      输入：nums = [1,2,10,4,1,4,3,3]
 *      输出：[2,10] 或 [10,2]
 * 
 */
public class Problem56I {

    public static void main(String[] args) {
        // test case1, output: [6, 1]
        int[] nums = { 4, 1, 4, 6 };
        
        // test case2, output: [2, 10]
//        int[] nums = { 1, 2, 10, 4, 1, 4, 3, 3 };
        
        _56ISolution solution = new _56ISolution();
        
        System.out.println(Arrays.toString(solution.singleNumbers(nums)));
    }
}


/**
 * 思路：
 *  假设数组中 nums 待求的两个数分别是 x，y 
 *  
 *  （1）第一遍遍历，对 nums 中的数字进行异或(^)，得到 a = x^y
 *  （2）初始化变量 b = 1，在 while 循环中用 b 找到 a 中最低位的 1
 *  （3）第二遍遍历，用 (b & n) == 0 对 x、y 进行分组 
 */
class _56ISolution {
    
    public int[] singleNumbers(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        
        // 利用 b 找到 a 二进制串中最低位的 1。当 b&a = 1 时结束循环，则 b 二进制串中的 1 即是 a 二进制串中最低位的 1
        int b = 1;
        while ((b & a) == 0) { // (b & a) 只可能是 0 或 1，因为 b 二进制串中只有 1 位是 1
            b = b << 1;
        }
        
        // 分组，将 x、y 分到不同组中
        int x = 0, y = 0; // 存储两个不同的数
        // 循环中，(b & n) == 0 能够区分 x、y 的原因： 
        //   a = x ^ y，因此如果 a[i] 是 a 二进制串中最低位的 1，则说明 x[i]、y[i] 不相同，一个为 1、另一个为 0，从而 b&x、b&y 一个一定是 0，另一个一定是 1。
        //   这是因为 b 二进制中只有 b[i] 是 1，其它全部是 0，又加上是 & 操作，所以只看 b[i]&x[i]、b[i]&y[i] 的结果即可
        //   同时又因为 x[i]、y[i] 不相同，一个为 1、另一个为 0，所以 b[i]&x[i]、b[i]&y[i] 一个为 1、另一个为 0，即：b&x、b&y 不相同，一个一定是 0、另一个一定是 1
        for (int n : nums) {
            if ((b & n) == 0) {
                x ^= n;
            } else {
                y ^= n;
            }
        }
        
        return new int[] {x, y};
    }
}
