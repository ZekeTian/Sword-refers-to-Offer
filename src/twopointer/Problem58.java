package twopointer;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 * 
 * 题目描述：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 *        例如输入字符串"I am a student. "，则输出"student. a am I"。
 * 
 * 限制条件：
 *  （1）无空格字符构成一个单词。
 *  （2）输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *  （3）如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * 示例：
 *  示例 1：
 *      输入: "the sky is blue"
 *      输出: "blue is sky the"
 *  
 *  示例 2
 *      输入: " hello world! "
 *      输出: "world! hello"
 *           解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 */
public class Problem58 {

    public static void main(String[] args) {
//        String s = " hello world! ";

//        String s = "a good     example";

        String s = "the sky is blue";
        
//        _58Solution1 solution = new _58Solution1();

        _58Solution2 solution = new _58Solution2();
        
        
        System.out.println(solution.reverseWords(s));
    }
}


/**
 * 解法一：使用 split 
 */
class _58Solution1 {
    
    public String reverseWords(String s) {
        String[] splits = s.trim().split("\\s+");
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = splits.length - 1; i >= 0; --i) {
            if (!"".equals(splits[i].trim())) { // 当前字符串不是空串，则添加进去
                joiner.add(splits[i]);
            }
        }
        
        return joiner.toString();
    }
}

/**
 * 解法二：循环实现，将每个单词截取出来，然后拼接成一个新字符串
 */
class _58Solution2 {
    
    public String reverseWords(String s) {
        int left = s.length() - 1; // 单词左边界
        int right = left; // 单词右边界
        
        // 从后向前截取单词
        StringBuilder builder = new StringBuilder();
        while (left >= 0 && right >= 0) {
            // 寻找单词的右边界
            while (right >= 0 && s.charAt(right) == ' ') {
                --right; 
            }
            // 寻找单词的左边界
            left = right;
            while (left >= 0 && s.charAt(left) != ' ') {
                --left;
            }
            
            // 截取出单词
            String word = s.substring(left + 1, right + 1);
            builder.append(word).append(" ");
            // 继续下一轮单词截取
            right = left;
        }
        
        return builder.toString().trim();
    }
}

