package math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 * 
 * 题目描述：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *        你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *        
 * 限制条件：1 <= 数组长度 <= 50000
 * 
 * 示例：
 *  输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 *  输出: 2
 *
 */
public class Problem39 {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 2, 2, 2, 5, 4, 2 };
        
//        _39Solution1 solution = new _39Solution1();

        _39Solution2 solution = new _39Solution2();

//        _39Solution3 solution = new _39Solution3();
        
        System.out.println(solution.majorityElement(nums));
    }
}

/**
 * 解法一：使用 map 统计
 */
class _39Solution1 {
    
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int i : nums) {
            Integer count = countMap.getOrDefault(i, 0);
            countMap.put(i, count + 1);
        }
        
        for (int i : nums) {
            Integer count = countMap.get(i);
            if (count > (nums.length / 2)) {
                return i;
            }
        }
        
        return 0;
    }
}

/**
 * 解法二：排序
 *      数组中间的数字即为多数元素
 */
class _39Solution2 {
    
    public int majorityElement(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        int res = nums[nums.length / 2];

        // 检验 res 是否为多数元素
        int count = 0;
        for (int i : nums) {
            count = count + (i == res ? 1 : 0);
        }
        
        return (count > (nums.length / 2) ? res : 0);
    }
}


/**
 * 解法三：摩尔投票
 */
class _39Solution3 {
    
    public int majorityElement(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        
        int res = 0; // 最终结果，即数组中的多数元素
        int votes = 0; // 票数
        
        for (int i : nums) {
            if (votes == 0) {
                res = i; // 如果票数为 0，则假设当前数字 i 是多数元素
            }
            votes = votes + (i == res ? 1: -1); // 如果当前数字是 res，则票数加 1，否则票数减 1（相当于抵消一票）
        }
        
        // 检验 res 是否为多数元素（实际上，针对此题无需检验）
        votes = 0;
        for (int i : nums) {
            votes = votes + (i == res ? 1 : 0);
        }
        
        return (votes > nums.length / 2 ? res : 0);
    }
}

