package twopointer;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 *
 * 题目描述：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * 
 * 限制条件：
 *  （1）0 <= nums.length <= 50000
 *  （2）0 <= nums[i] <= 10000
 * 
 * 示例：
 *  输入：nums = [1,2,3,4]
 *  输出：[1,3,2,4] 
 *  注：[3,1,2,4] 也是正确的答案之一。
 * 
 */
public class Problem21 {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4 };
        
//        _21Solution1 solution = new _21Solution1();
        
//        _21Solution2 solution = new _21Solution2();
        
        _21Solution3 solution = new _21Solution3();
        
        System.out.println(Arrays.toString(solution.exchange(nums)));
    }
}

/**
 * 解法一：暴力解法
 */
class _21Solution1 {
    
    public int[] exchange(int[] nums) {
        int[] newNums = new int[nums.length];
        
        int j = 0;
        for (int i : nums) {
            if (i % 2 != 0) {
                newNums[j++] = i;
            }
        }
        for (int i : nums) {
            if (i % 2 == 0) {
                newNums[j++] = i;
            }
        }
        
        return newNums;
    }
}

/**
 * 解法二：双指针实现方式一
 */
class _21Solution2 {
    
    public int[] exchange(int[] nums) {
        int left = 0; // nums[0...left-1] 是奇数区域，nums[left] 是偶数
        int right = nums.length - 1; // nums[right+1...len-1] 是偶数区域，nums[right] 是奇数
        
        while (left < right) {
            if (0 == nums[left] % 2 && 0 != nums[right] % 2) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                // 交换 nums[left] 和 nums[right] 之后，left、right 指针继续移动，继续寻找
                ++left;
                --right;
            } else if (0 == nums[left] % 2 && 0 == nums[right] % 2) {
                --right; // nums[right] 是偶数，继续向左找
            } else if (0 != nums[left] % 2 && 0 != nums[right] % 2) {
                ++left; // nums[left] 是奇数，继续向右找
            } else { // nums[left] 是奇数、nums[right] 是偶数，则需要继续寻找
                ++left;
                --right; 
            }
        }
        
        return nums;
    }
}


/**
 * 解法二：双指针实现方式二
 */
class _21Solution3 {
    
    public int[] exchange(int[] nums) {
        int left = 0; // nums[0...left-1] 是奇数区域，nums[left] 是偶数
        int right = nums.length - 1; // nums[right+1...len-1] 是偶数区域，nums[right] 是奇数
        
        while (left < right) {
            // left 向右寻找偶数，直到找到偶数为止
            if (0 != nums[left] % 2) {
                ++left;
                continue;
            }
            
            // nums[left] 是偶数，则指针 left 停止寻找，让 right 向左寻找奇数
            if (0 == nums[right] % 2) {
                --right;
                continue;
            }
            
            // 此时，nums[left] 是偶数，nums[right] 是奇数，left 和 right 都找到符合条件数，需要交换 nums[left] 和 nums[right]
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
        }
        
        return nums;
    }
}

