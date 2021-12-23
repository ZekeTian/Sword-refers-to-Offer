package search;

/**
 * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 * 
 * 题目描述：一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 *        在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *        
 * 限制条件：1 <= 数组长度 <= 10000
 * 
 * 示例：
 *  示例 1
 *      输入: [0,1,3]
 *      输出: 2
 *      
 *  示例 2
 *      输入: [0,1,2,3,4,5,6,7,9]
 *      输出: 8
 * 
 */
public class Problem53II {

    public static void main(String[] args) {
//        int[] nums = { 0, 1, 3 };

        int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 9 };
        
        _53IISolution1 solution = new _53IISolution1();
        
        System.out.println(solution.missingNumber(nums));
    }
}

/**
 * 解法一：循环求解
 */
class _53IISolution1 {
    
    public int missingNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return -1;
        }
        
        for (int i = 0; i < nums.length; ++i) {
            if (i != nums[i]) {
                return i;
            }
        }
        
        return nums.length;
    }
}

/**
 * 解法二：二分搜索求解
 *      数组中若缺失一个值，则在缺失值左边一定是相等的（因为前面是连续的），而在缺失值右边一定是不相等的。
 *      在二分搜索时，如果 mid == nums[mid]，则说明在左边，需要去右边寻找第一个缺失的位置，即 left = mid + 1
 *                如果 mid != nums[mid]，则说明在右边，需要去左边寻找第一个缺失的位置，即 right = mid - 1
 */
class _53IISolution2 {
    
    public int missingNumber(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // 若缺失一个值，则在缺失值左边一定是相等的，而在缺失值右边一定是不相等的
            if (nums[mid] == mid) { // 相等，则此时 mid 在左边，需要去右边去寻找
                left = mid + 1;
            } else { // 不相等，则此时 mid 在右边，需要向左边逼近
                right = mid - 1;
            }
        }
        
        return left; // 因为相等时，left + 1，所以 left 是保存着第一个不相等位置的下标
    }
}
