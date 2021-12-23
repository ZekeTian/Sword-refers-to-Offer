package search;


/**
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * 
 * 题目描述：统计一个数字在排序数组中出现的次数。
 * 
 * 限制条件：
 *  （1）0 <= nums.length <= 10^5
 *  （2）-10^9 <= nums[i] <= 10^9
 *  （3）nums 是一个非递减数组
 *  （4）-10^9 <= target <= 10^9
 * 
 * 示例：
 *  示例 1
 *      输入: nums = [5,7,7,8,8,10], target = 8
 *      输出: 2
 *  
 *  示例 2
 *      输入: nums = [5,7,7,8,8,10], target = 6
 *      输出: 0
 */
public class Problem53I {

    public static void main(String[] args) {
        int[] nums = { 5,7,7,8,8,10 };
//        int target = 8;

        int target = 6;

//        int target = 5;
        
//        _53Solution1 solution = new _53Solution1();
        
//        _53Solution2 solution = new _53Solution2();

        _53Solution3 solution = new _53Solution3();
        
        
        System.out.println(solution.search(nums, target));
    }
}

/**
 * 解法一：使用 for 循环
 */
class _53Solution1 {
    
    public int search(int[] nums, int target) {
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (target == nums[i]) {
                ++count;
            }
        }
        
        return count;
    }
}


/**
 * 解法二：一次二分搜索 + 内部 for 循环
 */
class _53Solution2 {
    
    public int search(int[] nums, int target) {
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (target == nums[mid]) {
                // 找到后 target 后，向两边扩展，统计出所有等于 target 的数字的数量
                int count = 1;
                for (int i = mid - 1; i >= 0 && nums[i] == target; --i) {
                    ++count;
                }
                for (int i = mid + 1; i < nums.length && nums[i] == target; ++i) {
                    ++count;
                }
                
                return count;
            }
            
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        
        return 0;
    }
}


/**
 * 解法三：使用两次二分搜索，分别找到 target 第一次出现和最后一次出现的位置
 */
class _53Solution3 {
    
    // 在 nums 中寻找 target 第一次出现的位置，如果 nums 中没有 target，则返回 -1 
    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (target <= nums[mid]) { // 求左边界时，右边不断向左边界逼近
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return (left < nums.length && nums[left] == target) ? left : -1;
    }
    
    // 在 nums 中寻找 target 最后一次出现的位置，如果 nums 中没有 target，则返回 -1
    private int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (target >= nums[mid]) { // 求右边界时，左边不断向右边界逼近
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        
        return (right >= 0 && nums[right] == target) ? right : -1;
    }
    
    public int search(int[] nums, int target) {
        int upper = upperBound(nums, target);
        if (-1 == upper) {
            return 0; // 上边界为 -1 ，则说明 nums 中没有 target，无需寻找 lower，直接返回 0
        }
        int lower = lowerBound(nums, target);
        
        return upper - lower + 1;
    }
}



