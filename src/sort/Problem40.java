package sort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * 
 * 题目描述：输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 
 * 
 * 限制条件：
 *  （1）0 <= k <= arr.length <= 10000
 *  （2）0 <= arr[i] <= 10000
 *  
 * 示例：
 *  示例 1
 *      输入：arr = [3,2,1], k = 2
 *      输出：[1,2] 或者 [2,1]
 *  
 *  示例 2
 *      输入：arr = [0,1,2,1], k = 1
 *      输出：[0]
 *
 */
public class Problem40 {

    public static void main(String[] args) {
        int[] arr = { 3, 2, 1 };
        int k = 2;
        
//        _40Solution1 solution = new _40Solution1();

        _40Solution2 solution = new _40Solution2();
        
        int[] res = solution.getLeastNumbers(arr, k);
        
        System.out.println(Arrays.toString(res));
    }
}

/**
 * 解法一：排序
 */
class _40Solution1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (null == arr || 0 == arr.length) {
            return new int[] {};
        }
        
        Arrays.sort(arr);
        
        return  Arrays.copyOfRange(arr, 0, k);
    }
}

/**
 * 解法二：使用最大堆
 */
class _40Solution2 {
    
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>( (o1, o2) -> (o2 - o1));
        
        if (0 == k) {
            return new int[] {};
        }
        
        for (int i : arr) {
            if (heap.size() < k) {
                heap.add(i); // 堆中不满 k 个元素，则直接放进去
            } else {
                // 堆中已经有 k 个元素，则取出最大值，然后与 i 进行比较
                Integer peek = heap.peek();
                if (i < peek) {
                    heap.poll(); // 因为有比 peek 更小的数 i，所以 peek 没有资格成为最小的 k 个数
                    heap.add(i);
                }
            }
        }
        
        int[] res = new int[k];
        int index = k - 1;
        while (!heap.isEmpty()) {
            res[index--] = heap.poll();
        }
        
        return res;
    }
}

