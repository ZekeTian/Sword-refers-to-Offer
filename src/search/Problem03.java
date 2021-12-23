package search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 * 
 * 题目描述：找出数组中重复的数字。
 *        在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 *        请找出数组中任意一个重复的数字。
 *  
 * 限制条件：2 <= n <= 100000
 * 
 * 示例：
 *  输入：[2, 3, 1, 0, 2, 5, 3]
 *  输出：2 或 3 
 *  
 */
public class Problem03 {

    public static void main(String[] args) {
       int[] nums = { 2, 3, 1, 0, 2, 5, 3 };
       
//       _03Solution1 solution = new _03Solution1();
       
//       _03Solution2 solution = new _03Solution2();

       _03Solution3 solution = new _03Solution3();
       
       System.out.println(solution.findRepeatNumber(nums));
    }
}

/**
 * 解法一：使用 Set
 */
class _03Solution1 {
    public int findRepeatNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return -1;
        }
        
        Set<Integer> set = new HashSet<>();
        
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            }
            set.add(i);
        }
        
        return -1;
    }
}

/**
 * 解法二：使用数组存储
 *      因为对于长度为 n 的数组 nums，里面每个数字都在 0~n-1 范围内，因此可以直接创建一个长度为 n 的数组，然后数字作为下标即可。
 */
class _03Solution2 {
    public int findRepeatNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return -1;
        }

        boolean[] flags = new boolean[nums.length];
        
        for (int i : nums) {
            if (flags[i]) {
                return i;
            }
            flags[i] = true;
        }
        
        return -1;
    }
}

/**
 * 解法三：排序
 */
class _03Solution3 {
    public int findRepeatNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return -1;
        }
        
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] == nums[i]) {
                return nums[i];
            }
        }
        
        return -1;
    }
}

