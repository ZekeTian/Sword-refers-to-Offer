package bit;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 * 
 * 题目描述：在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 10000
 *  （2）1 <= nums[i] < 2^31
 *  
 * 示例：
 *  示例 1
 *      输入：nums = [3,4,3,3]
 *      输出：4
 *      
 *  示例 2
 *      输入：nums = [9,1,7,9,7,9,7]
 *      输出：1
 *
 */
public class Problem56II {

    public static void main(String[] args) {
        // test case1, output: 4
//        int[] nums = { 3, 4, 3, 3 };
        
        // test case2, output: 1
        int[] nums = { 9, 1, 7, 9, 7, 9, 7 };
        
        _56IISolution1 solution = new _56IISolution1();
        
        System.out.println(solution.singleNumber(nums));
    }
}

/**
 * 解法一（将二进制串还原成十进制数时使用累加）：
 *  （1）累加数组中每个数的二进制位，将和记录到数组 count 中
 *  （2）count 中每一位对应的数字模 3，最终得到的 count 数组即为所求数的二进制串
 *  （3）将 count 对应的二进制串还原成十进制数
 */
class _56IISolution1 {
    
    public int singleNumber(int[] nums) {
        int[] count = new int[32];
        
        for (int n : nums) {
            // 将每个数的二进制位进行累加
            int a = n;
            for (int i = 0; i < 32; ++i) {
                count[i] = count[i] + (a & 1); // 逐个取出 n 对应二进制串的位，然后累加到 count 中
                a = a >>> 1;
            }
        }
        
        // 将 count 中计数模上 3，从而只保留出现一次的数
        for (int i = 0; i < 32; ++i) {
            count[i] %= 3;
        }
        
        // count 中的二进制位实际上就是 nums 数组中只出现一次的数的二进制位，所以根据 count 二进制位复原成十进制数
        int res = 0;
        
        // 利用累加实现还原
        int tmp = 1;
        for (int i = 0; i < 32; ++i ) {
            res = res + tmp * count[i];
            tmp *= 2;
        }
        
        return res;
    }
}

/**
 * 解法二（将二进制串还原成十进制数时使用位运算）
 */
class _56IISolution2 {
    
    public int singleNumber(int[] nums) {
        // 记录 nums 中每个数的二进制串位
        int[] count = new int[32];
        for (int i : nums) {
            int n = i;
            for (int j = 0; j < 32; ++j) {
                count[j] = count[j] + (n & 1);
                n >>>= 1;
            }
        }

        // 将二进制串还原成十进制数
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            res <<= 1;
            res = res | (count[31 - i] % 3);
        }
        
        return res;
    }
}
