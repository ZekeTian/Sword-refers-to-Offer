package divideconquer;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * 
 * 题目描述：在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * 
 * 限制条件：0 <= 数组长度 <= 50000
 * 
 * 示例：
 *  输入: [7,5,6,4]
 *  输出: 5
 *
 */
public class Problem51 {

    public static void main(String[] args) {
//        int[] nums = { 7, 5, 6, 4 };
        
        int[] nums = { 1, 3, 2, 3, 1 };
        
        _51Solution solution = new _51Solution();
        
        System.out.println(solution.reversePairs(nums));
    }
}

/**
 * 利用归并排序（升序）的思想解决，在归并的时候加一个计数即可。
 */
class _51Solution {
    
    private int count = 0;
    
    // nums[left...right] 中左右两个子数组 nums[left...mid]、nums[mid+1...right] 都已经排序好，现在需要进行归并
    private void merge(int[] nums, int left, int right) {
        int[] tmp = Arrays.copyOfRange(nums, left, right + 1);
        int mid = (tmp.length - 1) / 2; // tmp[left...mid]、tmp[mid + 1...right] 两个子数组都是有序的，对这两个数组进行归并，然后放到 nums[left...right] 中
        int i = 0, j = mid + 1, k = left; // i、j 是数组 tmp 中左右两个子数组的起始指针，k 是数组 nums 中的起始指针
        
        for (k = left; k <= right; ++k) {
            if (i <= mid && j < tmp.length) {
                if (tmp[i] > tmp[j]) {
                    // 因为 tmp[i...mid] 是升序， 即 tmp[j] 比最小的 tmp[i] 还小时，意味着 tmp[j] 比 tmp[i...mid] 中所有的元素都要小。
                    // 所以 tmp[i]、tmp[i+1]...tmp[mid] 所有的元素都可以与 tmp[j] 形成逆序对。即含有 (mid - i + 1) 个逆序对
                    nums[k] = tmp[j++];
                    count = count + (mid - i + 1); // 在归并排序（升序）的基础上，添加一个计数即可完成本题
                } else {
                    nums[k] = tmp[i++];
                }
            } else if (i <= mid) {
                nums[k] = tmp[i++];
            } else if (j < tmp.length) {
                nums[k] = tmp[j++];
            }
        }
    }
    
    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        // 对 nums[left...mid]、nums[mid+1...right] 分别进行排序
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        
        // 对有序的两个子数组 nums[left...mid]、nums[mid+1...right] 进行归并
        merge(nums, left, right);
    }
    
    public int reversePairs(int[] nums) {
        if (null == nums || nums.length < 2) {
            return 0;
        }
        
        mergeSort(nums, 0, nums.length - 1);
        
        return count;
    }
}