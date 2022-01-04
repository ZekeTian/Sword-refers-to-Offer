package sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 * 
 * 题目描述：从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 *        2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0，可以看成任意数字。A 不能视为 14。
 * 
 * 限制条件：
 *  （1）数组长度为 5 
 *  （2）数组的数取值为 [0, 13] 
 * 
 * 示例：
 *  输入: [1,2,3,4,5]
 *  输出: True
 *  
 */
public class Problem61 {

    public static void main(String[] args) {
        // test case1, output: true
//        int[] nums = { 1, 2, 3, 4, 5 };
        
        // test case2, output: true
//        int[] nums = { 0, 0, 1, 2, 5 };
        
        // test case3, output: false
        int[] nums = { 0, 1, 2, 5, 6 };
        
//        _61Solution1 solution = new _61Solution1();
        
//        _61Solution2 solution = new _61Solution2();

        _61Solution3 solution = new _61Solution3();
        
        
        System.out.println(solution.isStraight(nums));
    }
}

/**
 * 解法一：不排序，使用 boolean 数组并统计 0 的个数和 nums 中缺失个数
 */
class _61Solution1 {
    
    public boolean isStraight(int[] nums) {
        if (nums.length < 5) {
            return false;
        }
        
        boolean[] flags = new boolean[14]; // flags[i] 为 true，则表示 nums 中有数字 i；反之，则表示没有数字 i
        
        int min = Integer.MAX_VALUE; // nums 中最小的非 0 数
        int max = Integer.MIN_VALUE; // nums 中最大的非 0 数 
        int zeroNum = 0; // nums 中 0 的个数
        int missNum = 0; // nums 中，min~max 之间缺失数字的个数
        // 统计 0 的个数，并标记各个数是否出现
        for (int i : nums) {
            if (0 == i) {
                ++zeroNum;
                continue;
            }
            if (flags[i]) { // 有重复的非零数字，则不能形成顺子
                return false;
            }
            
            flags[i] = true;
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        
        // 统计缺失值个数，如果本身是连续的（无需 0 进行填充），则从 flags[min] 到 flags[max] 全部为 true，无缺失
        for (int i = min; i <= max; ++i) {
            if (!flags[i]) {
                ++missNum;
            }
        }
        
        return zeroNum >= missNum; // 如果 0 的个数大于等于缺失个数，则可以用 0 进行填充，从而保证连续
    }
}

/**
 * 解法二：使用 Set，核心思想：除大小王之外没有重复，并且最大值和最小值（非零）之差小于 5
 *      最大值和最小值（非零）之差小于 5 的解释：
 *      因为总共只有 5 张牌，并且要求连续，则最大相差只可能是 4，如：1、2、3、4、5
 *      实际上，最小之差只能是 2，即： 2 <= 最大值和最小值（非零）之差 <= 4，各种情况分别如下：
 *      最大值和最小值（非零）之差 = 2，含有 2 个 0，如 0，0，1，2，3
 *      最大值和最小值（非零）之差 = 3，含有 1 个 0，如 0，1，2，3，4
 *      最大值和最小值（非零）之差 = 4，不含有 0，   如 1，2，3，4，5
 */
class _61Solution2 {
    
    public boolean isStraight(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                continue; // 大小王不进行处理
            }
            
            if (set.contains(nums[i])) {
                return false; // 含有重复数字，则不能形成顺子
            }
            set.add(nums[i]);
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        
        return max - min < 5;
    }
}


/**
 * 解法三：排序，核心思想和解法二的一样，即：除大小王之外没有重复，最大值和最小值（非零）之差小于 5
 */
class _61Solution3 {
    
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        
        int min = Integer.MAX_VALUE; // 最小的非零数
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == 0) {
                continue;
            }
            if (nums[i] == nums[i + 1]) {
                return false; // 非零数有重复，则不能形成顺子
            }
            min = Math.min(min, nums[i]);
        }
        
        return nums[nums.length - 1] - min < 5;
    }
}
