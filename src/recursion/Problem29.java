package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 * 
 * 题目描述：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 
 * 限制条件：
 *  （1）0 <= matrix.length <= 100
 *  （2）0 <= matrix[i].length <= 100
 *  
 * 示例：
 *         1 2 3
 *         4 5 6
 *         7 8 9
 *  输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 *  输出：[1,2,3,6,9,8,7,4,5]
 *
 */
public class Problem29 {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, 
                          {4, 5, 6}, 
                          {7, 8, 9}};
        
        _29Solution solution = new _29Solution();
        
        System.out.println(Arrays.toString(solution.spiralOrder(matrix)));
    }
}


class _29Solution {
    
    private int[][] moves = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // 顺时针，方向依次为：右、下、左、上
    private int moveIndex = 0; // moveIndex 在 0~3 之间循环，从而保证方向是顺时针旋转
    private List<Integer> list = new ArrayList<>();
    private boolean[][] visited = null;
    private int[][] matrix = null;
    private int m = 0, n = 0;
    
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    private void spiral(int x, int y) {
        visited[x][y] = true;
        list.add(matrix[x][y]);
        
        // 判断是否可以沿原来方向继续前进
        int newX = x + moves[moveIndex][0];
        int newY = y + moves[moveIndex][1];
        if (inArea(newX, newY) && !visited[newX][newY]) {
            spiral(newX, newY); // 可以沿原来方向继续前进
            return; 
        }
        
        // 不可以沿原来方向继续前进，则转换方向
        moveIndex = (moveIndex + 1) % moves.length; // 转换方向
        newX = x + moves[moveIndex][0];
        newY = y + moves[moveIndex][1];
        if (inArea(newX, newY) && !visited[newX][newY]) {
            spiral(newX, newY); // 沿新方向前进
        }
    }
    
    public int[] spiralOrder(int[][] matrix) {
        if (null == matrix || 0 == matrix.length) {
            return new int[] {};
        }
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.visited = new boolean[m][n];
        
        spiral(0, 0);
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        
        return res;
    }
}