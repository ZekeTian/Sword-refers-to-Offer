package math;


/**
 * https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
 * 
 * 题目描述：数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *        请写一个函数，求任意第n位对应的数字。
 *        
 * 限制条件：0 <= n < 2^31
 *  
 * 示例：
 *  示例 1
 *      输入：n = 3
 *      输出：3
 *      
 *  示例 2
 *      输入：n = 11
 *      输出：0
 *      
 */
public class Problem44 {

    public static void main(String[] args) {
//        int n = 3;
        
//        int n = 11;
        
        int n = 0;
        
        _44Solution solution = new _44Solution();
        
        System.out.println(solution.findNthDigit(n));
    }
}

class _44Solution {
    
    public int findNthDigit(int n) {
        if (0 == n) {
            return 0;
        }
        
        int numLen = 1; // 数字的长度，即数字是 numLen 位数
        long start = 1; // numLen 位数的起始数字（从 1 开始，对 0 进行特殊判断）
        long count = 9; // 所有 numLen 位数中含有的数字总个数
        
        while (n > count) {
            n -= count; // 丢掉所有 numLen 位数的数字总个数，计算出新的下标值
            // 更新 numLen、start、count
            ++numLen; // 数字长度增加
            start *= 10; // 数字长度增加，则对应的起始数也将增加
            // 9 * start 表示 numLen 位数的数字个数，如：2 位数，99 - 10 + 1 = 90；3 位数，999 - 100 + 1 = 900；4 位数：9999 - 1000 + 1 = 9000 
            // 又因为每个 numLen 位数都含有 numLen 位，所以所有 numLen 位数的数字总个数 = 9 * start * numLen
            count = 9 * start * numLen;
        }
        
        // 101, 102, 103
        long num = start + (n - 1) / numLen; // 第 n 位所属的数字
        // 每 numLen 个数字表示一个完整数，所以 (n - 1) % numLen 表示第 n 位数在 num 中的位置。将 num 转换成字符串，也是为了方便取出相应位置的数字。
        return (num + "").charAt((n - 1) % numLen) - '0';
    }
}