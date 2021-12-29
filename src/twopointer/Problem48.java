package twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 * 
 * 题目描述：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 
 * 限制条件：s.length <= 40000
 * 
 * 示例：
 *  示例 1
 *      输入: "abcabcbb"
 *      输出: 3 
 *      解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *      
 *  示例 2
 *      输入: "bbbbb"
 *      输出: 1
 *      解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 */
public class Problem48 {

    public static void main(String[] args) {
//        String s = "abcabcbb";
        
        String s = "bbbbb";
                
        _48Solution solution = new _48Solution();
        
        
        System.out.println(solution.lengthOfLongestSubstring(s));
    }
}


class _48Solution {
    
    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            Integer index = map.get(s.charAt(i));
            if (null != index) {
                // 如果 s[i] 已经存在，则更新 left，确保 [left, i] 之间没有重复字符。需要特别注意的是，left 只能向右走，不能向左走
                left = Math.max(left, index + 1); 
            }
            maxLen = Math.max(maxLen, i - left + 1);
            map.put(s.charAt(i), i);
        }
        
        return maxLen;
    }
}
