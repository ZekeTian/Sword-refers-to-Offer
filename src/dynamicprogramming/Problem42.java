package dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * 
 * 题目描述：输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
 * 
 * 
 * 限制条件：
 *  （1）1 <= arr.length <= 10^5
 *  （2）-100 <= arr[i] <= 100
 * 
 * 示例：
 *  输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *  输出: 6
 *  解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 
 */
public class Problem42 {

    public static void main(String[] args) {
        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        
        _42Solution solution = new _42Solution();
        
        System.out.println(solution.maxSubArray(nums));
    }
}


class _42Solution {
    
    public int maxSubArray(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        
        int[] memo = new int[nums.length]; // memo[i] 是以 nums[i] 结尾的子数组的和
        for (int i = 0; i < nums.length; ++i) {
            memo[i] = nums[i];
        }
        
        for (int i = 1; i < nums.length; ++i) {
            memo[i] = Math.max(memo[i - 1] + nums[i], memo[i]); // 两种情况：nums[i] 接在前面子数组后面 或 nums[i] 不接
        }
        
        int maxSum = Integer.MIN_VALUE;
        for (int i : memo) {
            maxSum = Math.max(maxSum, i);
        }
        
        return maxSum;
    }
}
