package backtrack;

/**
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 * 
 * 题目描述：地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 *        也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 *        但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * 
 * 限制条件：
 *  （1）1 <= n,m <= 100
 *  （2）0 <= k <= 20
 * 
 * 示例：
 *  示例 1
 *      输入：m = 2, n = 3, k = 1
 *      输出：3
 *      
 *  示例 2
 *      输入：m = 3, n = 1, k = 0
 *      输出：1
 *
 */
public class Problem13 {

    public static void main(String[] args) {
        // test case1, output: 3
//        int m = 2, n = 3, k = 1;
        
        // test case2, output: 1
        int m = 3, n = 1, k = 0;
        
        _13Solution solution = new _13Solution();
        
        
        System.out.println(solution.movingCount(m, n, k));
    }
}


class _13Solution {
    
    private int[][] moves = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int num = 0;
    private boolean[][] visited = null;
    private int m = 0;
    private int n = 0;
    private int k = 0;
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < m) && (y >= 0 && y < n);
    }
    
    // 获取数字 n 各个位上的数字之和
    private int getSum(int n) {
        int sum = 0;
        
        while (n != 0) {
            sum = sum + n % 10;
            n /= 10;
        }
        
        return sum;
    }
    
    // 判断位置 (x,y) 是否合法，即数字的各位之和是否符合要求
    private boolean isValid(int x, int y) {
        return (getSum(x) + getSum(y)) <= k;
    }
    
    private void tryMove(int x, int y) {
        visited[x][y] = true;
        
        if (!isValid(x, y)) {
            return; // 当前位置不合法，则直接返回
        }
        
        // 当前位置合法，则计数加 1，并且继续尝试向其它方格移动
        ++num;
        for (int i = 0; i < moves.length; ++i) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            
            if (inArea(newX, newY) && !visited[newX][newY]) {
                tryMove(newX, newY);
            }
        }
    }
    
    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) {
            return 0;
        }
        
        this.m = m;
        this.n = n;
        this.k = k;
        this.visited = new boolean[m][n];
        
        tryMove(0, 0);
        
        return num;
    }
}
