package string;

/**
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * 
 * 题目描述：请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 
 * 限制条件：0 <= s 的长度 <= 10000
 * 
 * 示例：
 *  输入：s = "We are happy."
 *  输出："We%20are%20happy."
 *
 */
public class Problem05 {

    public static void main(String[] args) {
        String s = "We are happy.";
        
        _05Solution1 solution = new _05Solution1();
        
        System.out.println(solution.replaceSpace(s));
    }
}

class _05Solution1 {
    
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
