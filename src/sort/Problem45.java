package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 * 
 * 题目描述：输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *        注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *            拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * 
 * 限制条件：0 < nums.length <= 100
 * 
 * 示例：
 *  示例 1
 *      输入: [10,2]
 *      输出: "102"
 *      
 *  示例 2
 *      输入: [3,30,34,5,9]
 *      输出: "3033459"
 *
 */
public class Problem45 {

    public static void main(String[] args) {
//        int[] nums = { 10, 2 };

        int[] nums = { 3, 30, 34, 5, 9 };
        
//        _45Solution1 solution = new _45Solution1();
        
        _45Solution2 solution = new _45Solution2();
        
        
        System.out.println(solution.minNumber(nums));
    }
}

/**
 * 解法一：使用库函数解决
 *      先对数字进行排序，将较小的数字放在前面，然后直接拼接成字符串即可。不过需要注意的是：排序不能简单的按照数字大小排序，而应该一定规则进行排序。
 *      如：3、30，如果按照数字排序，则 3 小于 30。但是在此题中，3、30 会进行字符串拼接，所以应该按照字符串拼接后的规则进行比较，具体如下：
 *         如果 x + y < y + x，则 x < y。如： x = 3、y = 30，330 > 303，则：x > y
 */
class _45Solution1 {
    
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        
        for (int i : nums) {
            list.add(i + "");
        }
        
        Collections.sort(list, (x, y) -> ((x + y).compareTo(y + x)));
        
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s);
        }
        
        return builder.toString();
    }
}


/**
 * 解法二：自己实现快速排序
 */
class _45Solution2 {
    
    // 比较字符串 x 和 y 的大小
    private int compare(String x, String y) {
        return (x + y).compareTo(y + x);
    }
    
    // 交换 arr[a] 和 arr[b]
    private void swap(String[] arr, int a, int b) {
        String tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
    
    private int partition(String[] arr, int left, int right) {
        int l = left;
        int r = right;
        String pivot = arr[left];
        
        while (l < r) {
            while (compare(arr[l], pivot) <= 0 && l < right) { // arr[l] <= pivot，则继续寻找，直到找到 > pivot
                ++l;
            }
            while (compare(arr[r], pivot) >= 0 && r > left) { // arr[r] >= pivot，则继续寻找，直到找到 < pivot
                --r;
            }
            
            if (l < r) {
                swap(arr, l, r);
            }
        }
        
        swap(arr, left, r);
        
        return r;
    }
    
    private void quickSort(String[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int pivot = partition(arr, left, right);
        
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }
    
    public String minNumber(int[] nums) {
        String[] numsStr = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            numsStr[i] = nums[i] + "";
        }
        
        quickSort(numsStr, 0, numsStr.length - 1);
        
        StringBuilder builder = new StringBuilder();
        for (String s : numsStr) {
            builder.append(s);
        }
        
        return builder.toString();
    }
}