package search;

/**
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * 
 * 题目描述：把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *        给你一个可能存在重复元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
 *        请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。  
 * 
 * 示例：
 *  示例 1
 *      输入：[3,4,5,1,2]
 *      输出：1
 *  
 *  示例 2
 *      输入：[2,2,2,0,1]
 *      输出：0
 */
public class Problem11 {

    public static void main(String[] args) {
        int[] nums = { 3, 4, 5, 1, 2 };

//        int[] nums = { 2, 2, 2, 0, 1 };
        
//        _11Solution1 solution = new _11Solution1();

        _11Solution2 solution = new _11Solution2();
        
        
        System.out.println(solution.minArray(nums));
    }
}

/**
 * 解法一：循环暴力搜索
 */
class _11Solution1 {
    public int minArray(int[] numbers) {
        if (null == numbers || 0 == numbers.length) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < numbers.length; ++i) {
            min = Math.min(min, numbers[i]);
        }
        
        return min;
    }
}

/**
 * 解法二：使用二分搜索
 *    7
 *   6 
 *  5  
 * 4   
 *       3
 *      2
 *     1
 */
class _11Solution2 {
    
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (numbers[mid] > numbers[right]) {
                left = mid + 1; // mid 在左边，left 向右逼近
            } else if (numbers[mid] < numbers[right]){
                right = mid; // mid 在右边，right 向左逼近，mid 不减 1 是因为 numbers[mid] 可能是最小值
            } else {
                --right; // numbers[right] 可以丢弃
            }
        }
        
        return numbers[left];
    }
}
