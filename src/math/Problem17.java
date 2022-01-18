package math;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 * 
 * 题目描述：输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 
 * 限制条件：
 *  （1）用返回一个整数列表来代替打印
 *  （2）n 为正整数
 * 
 * 示例：
 *  输入: n = 1
 *  输出: [1,2,3,4,5,6,7,8,9]
 *  
 */
public class Problem17 {

    public static void main(String[] args) {
        int n = 1;
        
        _17Solution1 solution = new _17Solution1();
        
        System.out.println(Arrays.toString(solution.printNumbers(n)));
    }
}

/**
 * 解法一：使用数学公式计算，确定出范围，然后遍历生成结果数组
 */
class _17Solution1 {
    
    public int[] printNumbers(int n) {
        int upper = 1;
        for (int i = 0; i < n; ++i) {
            upper *= 10;
        }
        
        int[] res = new int[upper - 1];
        for (int i = 1; i < upper; ++i) {
            res[i - 1] = i;
        }
        
        return res;
    }
}