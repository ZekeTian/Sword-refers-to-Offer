package twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 * 
 * 题目描述：输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *        序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *        
 * 限制条件：1 <= target <= 10^5
 * 
 * 示例：
 *  示例 1
 *      输入：target = 9
 *      输出：[[2,3,4],[4,5]]
 *  
 *  示例 2
 *      输入：target = 15
 *      输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 */
public class Problem57II {

    public static void main(String[] args) {
        int target = 9;
        
        _57IISolution solution = new _57IISolution();
        
        int[][] res = solution.findContinuousSequence(target);
        
        System.out.println(Arrays.deepToString(res));
    }
}

class _57IISolution {
    
    public int[][] findContinuousSequence(int target) {
        int left = 1;
        int right = 2;
        int sum = 3;
        List<int[]> list = new ArrayList<>();
        
        while (left < right) {
            if (sum < target) {
                // sum 较小，则序列需要添加元素，右边界向右移
                ++right;
                sum += right;
            } else if (sum > target) {
                // sum 较大，则序列需要减少元素，左边界向右移（但是在移之前，需要在 sum 中将左边界对应的值删除）
                sum -= left;
                ++left;
            } else {
                // 找到符合条件的序列
                int[] res = new int[right - left + 1];
                for (int i = left; i <= right; ++i) {
                    res[i - left] = i;
                }
                list.add(res);
                
                // 将符合条件的序列添加到 list 之后，左边界向右移动，继续下轮查找
                sum -= left;
                ++left;
            }
        }
        
        return list.toArray(new int[list.size()][]);
    }
}