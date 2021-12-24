package search;

/**
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 * 
 * 题目描述：在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *        请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *        
 * 限制条件：0 <= n <= 1000，0 <= m <= 1000
 * 
 * 示例：
 *      所给矩阵如下：
 *          [
 *            [1,   4,  7, 11, 15],
 *            [2,   5,  8, 12, 19],
 *            [3,   6,  9, 16, 22],
 *            [10, 13, 14, 17, 24],
 *            [18, 21, 23, 26, 30]
 *          ]
 *      
 *      给定 target = 5，返回 true。
 *      给定 target = 20，返回 false。
 *  
 */
public class Problem04 {

    public static void main(String[] args) {
        int[][] matrix = {
                    {1,   4,  7, 11, 15},
                    {2,   5,  8, 12, 19},
                    {3,   6,  9, 16, 22},
                    {10, 13, 14, 17, 24},
                    {18, 21, 23, 26, 30}
                };
        
//        int target = 5; // true
        
        int target = 20; // false
        
//        _04Solution1 solution = new _04Solution1();
        
//        _04Solution2 solution = new _04Solution2();
        
        _04Solution3 solution = new _04Solution3();
        
        
        System.out.println(solution.findNumberIn2DArray(matrix, target));
    }
}

/**
 * 解法一：暴力搜索
 */
class _04Solution1 {
    
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (null == matrix || 0 == matrix.length) {
            return false;
        }
        
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

/**
 * 解法二：对每行使用二分搜索
 */
class _04Solution2 {
    
    private boolean binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (null == matrix || 0 == matrix.length) {
            return false;
        }
        
        for (int i = 0; i < matrix.length; ++i) {
            if (binarySearch(matrix[i], target)) {
                return true;
            }
        }
        
        return false;
    }
}

/**
 * 解法三：对矩阵直接使用二分搜索
 *  
 * 思路：将矩阵逆时针旋转 45 度，然后利用二分搜索的思想进行二分搜索。以如下矩阵为例
 *      1 3 5 6                             6
 *      2 4 7 8    --逆时针旋转 45 度-->      5  8
 *      5 7 8 9                          3  7  9
 *                                      1  4  8
 *                                        2  7
 *                                          5 
 * 将矩阵逆时针旋转 45 度后，右上角的元素作为根顶点，此时旋转后的矩阵相当于是一棵二分搜索树，根顶点左边比根顶点小，根顶点的右边比根顶点大
 *  
 */
class _04Solution3 {
    
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (null == matrix || 0 == matrix.length) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int r = 0, c = n - 1; // 从右上角开始寻找
        
        while (r < m && c >= 0) {
            if (target == matrix[r][c]) { // 找到特定值
                return true;
            } else if (target < matrix[r][c]) { // 在左边寻找
                --c;
            } else { // 在右边寻找
                ++r;
            }
        }
        
        return false;
    }
}

