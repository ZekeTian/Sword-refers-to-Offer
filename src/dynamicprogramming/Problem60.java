package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/
 * 
 * 题目描述：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 *        你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * 
 * 限制条件：1 <= n <= 11
 * 
 * 示例：
 *  示例 1
 *      输入: 1
 *      输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 *  
 *  示例 2
 *      输入: 2
 *      输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 */
public class Problem60 {

    public static void main(String[] args) {
//        int n = 1;

        int n = 2;
        
        _60Solution solution = new _60Solution();
        
        
        double[] probability = solution.dicesProbability(n);
        
        System.out.println(Arrays.toString(probability));
    }
}


class _60Solution {
    
    public double[] dicesProbability(int n) {
        if (n < 1) {
            return new double[] {};
        }
        
        double[][] memo = new double[n + 1][6 * n + 1]; // memo[i][j] 表示 i 个骰子，朝上一面点数之和为 j 时的概率
        
        // 初始状态
        for (int i = 1; i <= 6; ++i) {
            memo[1][i] = 1.0 / 6;
        }
        
        for (int i = 2; i <= n; ++i) { // 骰子个数
            for (int j = i; j <= 6 * i; ++j) { // 骰子朝上一面之和
                // 第 i 个骰子能够抛出的最大面。如果 j 比 6 大，则只能取 6，因为不可能抛出比 6 更大的面。如果 j 比 6 小，则取 j，因为抛出比 j 更大的面是非法的。如 j = 2，抛出的面是 4 ，肯定是非法的。
                int maxValue = Math.min(6, j);
                for (int k = 1; k <= maxValue; ++k) { // 当前骰子朝上的一面
                    // 当前骰子朝上一面是 k，则前 i-1 个骰子朝上一面之和应该为 j-k，所以转移到状态 memo[i - 1][j - k]
                    memo[i][j] = memo[i][j] + memo[i - 1][j - k] * 1.0 / 6;
                }
            }
        }
        
        double[] res = new double[6 * n - n + 1];
        for (int i = n; i <= 6 * n; ++i) {
            res[i - n] = memo[n][i];
        }
        
        return res;
    }
}