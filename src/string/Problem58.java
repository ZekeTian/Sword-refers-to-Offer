package string;

/**
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 *
 * 题目描述：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 *        比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 * 
 * 限制条件：1 <= k < s.length <= 10000
 * 
 * 示例：
 *  示例 1
 *      输入: s = "abcdefg", k = 2
 *      输出: "cdefgab"
 *      
 *  示例 2
 *      输入: s = "lrloseumgh", k = 6
 *      输出: "umghlrlose"
 * 
 */
public class Problem58 {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;
                
//        _58Solution1 solution = new _58Solution1();

        _58Solution2 solution = new _58Solution2();
        
        
        System.out.println(solution.reverseLeftWords(s, k));
    }
}

class _58Solution1 {
    
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }
}

class _58Solution2 {
    public String reverseLeftWords(String s, int n) {
        StringBuilder builder = new StringBuilder();
        
        for (int i = n; i < s.length(); ++i) {
            builder.append(s.charAt(i));
        }
        for (int i = 0; i < n; ++i) {
            builder.append(s.charAt(i));
        }
        
        return builder.toString();
    }
}
