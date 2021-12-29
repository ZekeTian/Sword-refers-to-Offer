package dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * 
 * 题目描述：给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 *        一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法
 * 
 * 限制条件：0 <= num < 2^31
 * 
 * 示例：
 *      输入: 12258
 *      输出: 5
 *      解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 */
public class Problem46 {

    public static void main(String[] args) {
        int num = 12258;
        
        _46Solution solution = new _46Solution();
        
        System.out.println(solution.translateNum(num));
    }
}


class _46Solution {
    
    public int translateNum(int num) {
        String numStr = num + "";
        char[] numArr = numStr.toCharArray();
        int[] memo = new int[numArr.length + 1]; // memo[i] 表示 num[0...i-1] 可能形成的翻译个数
        
        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= numArr.length; ++i) {
            memo[i] += memo[i - 1]; // 截取一个数字进行翻译
            
            int curNum = numArr[i - 1] - '0'; // 当前数字
            int preNum = numArr[i - 2] - '0'; // 前一个数字
            int sum = curNum + preNum * 10;
            if (sum <= 25 && sum != curNum) { // 确保 sum 在范围之内，并且 preNum 不为 0
                memo[i] += memo[i - 2]; // 截取两个数字进行翻译
            }
        }
        
        return memo[numArr.length];
    }
}

