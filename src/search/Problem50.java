package search;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

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
        
//        _50Solution1 solution = new _50Solution1();

        _50Solution2 solution = new _50Solution2();
        
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
        if (null == s || 0 == s.length()) {
            return ' ';
        }
        
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


/**
 * 解法二：使用 LinkedHashMap。从而可以按照字符出现的顺序存储各字符的次数，方便寻找第一个只出现一次的字符。
 *      因此，针对 “找出第一个只出现xx次的” 问题，可以采取本题中的两种解题思路，第一种是两次循环 + HashMap，第二种是两次循环 + LinkedHashMap
 */
class _50Solution2 {
    
    public char firstUniqChar(String s) {
        if (null == s || 0 == s.length()) {
            return ' ';
        }
        
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            Integer count = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), count + 1);
        }
        
        Set<Map.Entry<Character, Integer>> set =  map.entrySet();
        
        for (Map.Entry<Character, Integer> e : set) {
            if (1 == e.getValue()) {
                return e.getKey();
            }
        }
        
        return ' ';
    }
}


