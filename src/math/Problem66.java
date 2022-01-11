package math;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/
 * 
 * 题目描述：给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 
 *        即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *        
 * 限制条件：
 *  （1）所有元素乘积之和不会溢出 32 位整数
 *  （2）a.length <= 100000
 *  
 *  示例：
 *   输入: [1,2,3,4,5]
 *   输出: [120,60,40,30,24]
 *   
 */
public class Problem66 {

    public static void main(String[] args) {
        int[] a = { 1, 2, 3, 4, 5 };
        
        _66Solution solution = new _66Solution();
        
        int[] res = solution.constructArr(a);
        
        System.out.println(Arrays.toString(res));
    }
}

/**
 * 将结果拆分成前后两部分的乘积
 *  (...,i-1,) i, (i+1,...)
 *  对于所求结果 res[i] ，其等于前面一部分乘积与后面一部分乘积相乘
 */
class _66Solution {
    
    public int[] constructArr(int[] a) {
        if (null == a || a.length <= 1) {
            return new int[] {};
        }
        
        int[] res = new int[a.length];
        int[] startArr = new int[a.length]; // startArr[i] 表示 a[i...len-1] 之间所有数字和乘积
        int[] endArr = new int[a.length]; // endArr[i] 表示 a[0...i] 之间所有数字和乘积
        
        startArr[a.length - 1] = a[a.length - 1];
        for (int i = a.length - 2; i >= 0; --i) {
            startArr[i] = a[i] * startArr[i + 1];
        }
        
        endArr[0] = a[0];
        for (int i = 1; i < a.length; ++i) {
            endArr[i] = a[i] * endArr[i - 1];
        }
        
        for (int i = 0; i < a.length; ++i) {
            if (0 == i) {
                res[i] = startArr[i + 1];
            } else if (i == a.length - 1) {
                res[i] = endArr[i - 1];
            } else {
                // (...,i-1,) i, (i+1,...)
                res[i] = endArr[i - 1] * startArr[i + 1]; // 前面一部分乘积与后面一部分乘积相乘，即得到当前结果
            }
        }
        
        return res;
    }
}