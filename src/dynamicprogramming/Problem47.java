package dynamicprogramming;

import java.util.Iterator;

/**
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 *
 * 题目描述：在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 *        你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 *        给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 * 
 * 限制条件：
 *  （1）0 < grid.length <= 200
 *  （2）0 < grid[0].length <= 200
 * 
 * 示例：
 *      输入: 
 *          [
 *            [1,3,1],
 *            [1,5,1],
 *            [4,2,1]
 *          ]
 *      输出: 12
 *      解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 */
public class Problem47 {

    public static void main(String[] args) {
        int[][] grid = {
                        {1, 3, 1}, 
                        {1, 5, 1}, 
                        {4, 2, 1}
                       };
        
        _47Solution solution = new _47Solution();
        
        
        System.out.println(solution.maxValue(grid));
    }
}


class _47Solution {
    
    public int maxValue(int[][] grid) {
        if (null == grid || 0 == grid.length) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] memo = new int[m][n]; // memo[i][j] 表示从 grid[i][j] 出发能够获取到的礼物的最大值
        
        // 初始化最后一行
        memo[m - 1][n - 1] = grid[m - 1][n - 1];
        for (int i = n - 2; i >= 0; --i) {
            memo[m - 1][i] = memo[m - 1][i + 1] + grid[m - 1][i]; // 向右走
        }
        
        for (int i = m - 2; i >= 0; --i) {
            memo[i][n - 1] = memo[i + 1][n - 1] + grid[i][n - 1]; // 向下走
            for (int j = n - 2; j >= 0; --j) {
                memo[i][j] = Math.max(memo[i][j + 1], memo[i + 1][j]) + grid[i][j]; // 从向右、向下中取较大值
            }
        }
        
        return memo[0][0];
    }
}


