package queue;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 * 
 * 题目描述：给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 
 * 限制条件：假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * 
 * 示例：
 *    输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 *    输出: [3,3,5,5,6,7] 
 *    解释: 
 *      滑动窗口的位置                最大值
 *    ---------------               -----
 *    [1  3  -1] -3  5  3  6  7       3
 *     1 [3  -1  -3] 5  3  6  7       3
 *     1  3 [-1  -3  5] 3  6  7       5
 *     1  3  -1 [-3  5  3] 6  7       5
 *     1  3  -1  -3 [5  3  6] 7       6
 *     1  3  -1  -3  5 [3  6  7]      7
 *
 */
public class Problem59I {

    public static void main(String[] args) {
        // test case1, output: [3, 3, 5, 5, 6, 7]
        int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
        int k = 3;

        // test case2, output: [5, 4, 3]
//        int[] nums = { 5, 4, 3, 2, 1 };
//        int k = 3;
        
        // test case1, output: [7, 4]
//        int[] nums = { 7, 2, 4 };
//        int k = 2;
        
        _59ISolution solution = new _59ISolution();
        
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
    }
}


class _59ISolution {
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (null == nums || 0 == nums.length || 0 == k) {
            return new int[] {};
        }
        
        int[] res = new int[nums.length - k + 1];
        // 双端队列，其满足两个要求：（1）队列里面的元素都在窗口内（2）队列元素是非严格递减，即队头最大、队尾最小
        LinkedList<Integer> dequeue = new LinkedList<>(); 
        int left = 1 - k, right = 0; // [left, right] 是大小为 k 的窗口，该窗口右边界从 0 开始向右滑动，直到滑动完整个数组 nums
        
        // 大小为 k 的窗口 [left, right] 向右滑动，在滑动的过程中，用右边界 right 遍历数组 nums 
        while (right < nums.length) {
            if (left >= 1 && nums[left - 1] == dequeue.peekFirst()) {
                dequeue.pollFirst(); // 队列的最大值等于窗口外左边的第一个值，即队列的最大值不在窗口内，则应该将队列的最大值从队列中移除
            }
            
            // 为了保证队列的非严格递减，当 nums[right] > 队尾元素时，则不能直接添加 nums[right]，需要将队列中小于 nums[right] 的元素移除 
            while (!dequeue.isEmpty() && nums[right] > dequeue.peekLast()) {
                dequeue.pollLast(); 
            }
            
            // 经过上面的 while 循环，可以确保队列中所有元素大于等于 nums[right]，因此此时可以直接将 nums[right] 加入到队列中
            dequeue.addLast(nums[right]);
            
            if (left >= 0) { // 在数组 nums 中正式形成窗口
                res[left] = dequeue.peekFirst(); // 双端队列中队首元素即为窗口内最大值
            }
            
            // 向右滑动
            ++right;
            ++left;
        }
        
        return res;
    }
}
