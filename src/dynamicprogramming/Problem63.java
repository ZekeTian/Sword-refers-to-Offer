package dynamicprogramming;


/**
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 *
 * 题目描述：假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * 
 * 限制条件：0 <= 数组长度 <= 10^5
 * 
 * 示例：
 *  示例 1
 *      输入: [7,1,5,3,6,4]
 *      输出: 5
 *      解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *           注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *  
 *  示例 2
 *      输入: [7,6,4,3,1]
 *      输出: 0
 *      解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *      
 */
public class Problem63 {

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        
//        _63Solution1 solution = new _63Solution1();
        
        _63Solution2 solution = new _63Solution2();
        
        System.out.println(solution.maxProfit(prices));
    }
}

/**
 * 解法一：动态规划
 */
class _63Solution1 {
    
    public int maxProfit(int[] prices) {
        if (null == prices | 0 == prices.length) {
            return 0;
        }
        
        int[] memo = new int[prices.length]; // memo[i] 记录 0~i 天内能够获取的最大利润
        int minPrice = prices[0];
        memo[0] = 0;
        
        for (int i = 1; i < prices.length; ++i) {
            minPrice = Math.min(minPrice, prices[i]);
            memo[i] = Math.max(memo[i - 1], prices[i] - minPrice);
        }
        
        return memo[prices.length - 1];
    }
}

/**
 * 解法二：贪心算法
 */
class _63Solution2 {
    
    public int maxProfit(int[] prices) {
        if (null == prices || 0 == prices.length) {
            return 0;
        }
        
        int minPrice = prices[0];
        int maxProfit = 0;
        
        for (int i = 1; i < prices.length; ++i) {
            minPrice = Math.min(prices[i], minPrice);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        
        return maxProfit;
    }
}
