package search;

/**
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 * 
 * 题目描述：在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 * 
 * 限制条件：0 <= s 的长度 <= 50000
 * 
 * 示例：
 *  示例 1
 *      输入：s = "abaccdeff"
 *      输出：'b'
 *      
 *  示例 2
 *      输入：s = "" 
 *      输出：' '
 *
 */
public class Problem50 {

    public static void main(String[] args) {
        String s = "abaccdeff";
        
        _50Solution1 solution = new _50Solution1();
        
        
        System.out.println(solution.firstUniqChar(s));
    }
}

/**
 * 解法一：两次循环 + 数组或 HashMap
 *      第一次循环时，使用数组或 HashMap 记录每个字符出现的次数；
 *      第二次循环时，找到第一个只出现一次的字符
 */
class _50Solution1 {
    
    public char firstUniqChar(String s) {
        int[] count = new int[26];
        // 第一次循环统计次数
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            count[ch - 'a']++;
        }
        
        // 第二次循环找出第一个只出现一次的字符
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (1 == count[ch - 'a']) {
                return ch;
            }
        }
        
        return ' ';
    }
}

