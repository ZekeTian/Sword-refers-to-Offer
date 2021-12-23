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
 * 解法一：循环
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


