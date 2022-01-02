package backtrack;

/**
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 * 
 * 题目描述：给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *        单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *        
 * 限制条件：
 *  （1）1 <= board.length <= 200
 *  （2）1 <= board[i].length <= 200
 *  （3）board 和 word 仅由大小写英文字母组成
 * 
 * 示例：
 *  例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 *      (A) (B) (C) E
 *       S   F  (C) S
 *       A  (D) (E) E
 *
 */
public class Problem12 {

    public static void main(String[] args) {
        // test case1, output: true
        char[][] board = {
                            {'A','B','C','E'},
                            {'S','F','C','S'},
                            {'A','D','E','E'}
                        };
        
        String word = "ABCCED";
        
        // test case2, output: false
//        char[][] board = {
//                            {'a', 'b'},
//                            {'c', 'd'}
//                        };
//        String word = "abcd";
        
        _12Solution solution = new _12Solution();
        
        
        System.out.println(solution.exist(board, word));
    }
}


class _12Solution {
    
    private int[][] moves = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    private char[][] board = null;
    private boolean[][] visited = null;
    private String word = "";
    
    
    private boolean inArea(int x, int y) {
        return (x >= 0 && x < board.length) && (y >= 0 && y < board[0].length);
    }
    
    // 在 board[x][y] 处匹配字符 word[index]
    private boolean tryFind(int index, int x, int y) {
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        
        // board[x][y] 能够匹配到 word[index]
        visited[x][y] = true;
        if (index == word.length() - 1) {
            return true; // 当前是最后一个字符，则已经匹配完
        }
        
        boolean flag = false;
        for (int i = 0; i < moves.length; ++i) {
            int newX = x + moves[i][0];
            int newY = y + moves[i][1];
            
            if (inArea(newX, newY) && !visited[newX][newY] && tryFind(index + 1, newX, newY)) {
                flag = true; // 寻找到一条合法的路径
                break;
            }
        }
        
        if (!flag) { // 从 board[x][y] 出发未找到合法路径，则恢复 visited 标记位，以便后面使用
            visited[x][y] = false;
            return false;
        }
        
        return true; // 从 board[x][y] 出发找到合法路径
    }
    
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }
        this.board = board;
        this.word = word;
        this.visited = new boolean[board.length][board[0].length];
        
        // 尝试从不同的位置出发，去匹配 word
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (tryFind(0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
}

