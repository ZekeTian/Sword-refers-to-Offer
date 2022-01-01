package twopointer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 * 
 * 题目描述：输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 10^5
 *  （2）1 <= nums[i] <= 10^6
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [2,7,11,15], target = 9
 *      输出：[2,7] 或者 [7,2]
 *      
 *  示例 2
 *      输入：nums = [10,26,30,31,47,60], target = 40
 *      输出：[10,30] 或者 [30,10]
 * 
 */
public class Problem57 {

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;
        
//        _57Solution1 solution = new _57Solution1();

        _57Solution2 solution = new _57Solution2();
        
        
        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }
}

/**
 * 解法一：使用 Set
 */
class _57Solution1 {
    
    public int[] twoSum(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        
        for (int i : nums) {
            int remain = target - i;
            if (set.contains(remain)) {
                return new int[] {remain, i};
            }
            set.add(i);
        }
        
        return new int[] {};
    }
}

/**
 * 解法二：双指针（碰撞）
 */
class _57Solution2 {
    
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[] {nums[left], nums[right]};
            }
            if (sum > target) {
                --right;
            } else {
                ++left;
            }
        }
        
        return new int[] {};
    }
}
