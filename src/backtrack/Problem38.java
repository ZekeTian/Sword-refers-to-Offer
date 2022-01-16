package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 * 
 * 题目描述：输入一个字符串，打印出该字符串中字符的所有排列。
 *        你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 
 * 限制条件：1 <= s 的长度 <= 8
 * 
 * 示例：
 *  输入：s = "abc"
 *  输出：["abc","acb","bac","bca","cab","cba"]
 *  
 */
public class Problem38 {

    public static void main(String[] args) {
        String s = "abc";
        
//        _38Solution1 solution = new _38Solution1();

        _38Solution2 solution = new _38Solution2();
        
        System.out.println(Arrays.toString(solution.permutation(s)));
    }
}

/**
 * 解法一：暴力法
 */
class _38Solution1 {
    
    private Set<String> set = new HashSet<>();
    private boolean[] flags = null;
    private String str = "";
    
    private void getPermutation(String s) {
        if (s.length() == str.length()) {
            set.add(s);
            return;
        }
        
        for (int i = 0; i < str.length(); ++i) {
            if (!flags[i]) {
                flags[i] = true;
                getPermutation(s + str.charAt(i));
                flags[i] = false;
            }
        }
    }
    
    public String[] permutation(String s) {
        if (null == s || 0 == s.length()) {
            return new String[] {};
        }
        this.flags = new boolean[s.length()];
        this.str = s;
        
        getPermutation("");
        
        return set.toArray(new String[set.size()]);
    }
}

/**
 * 解法二：将 s 对应的字符数组进行排序，然后借助标记数组去重。与 LeetCode 的第 40 题一样。
 */
class _38Solution2 {
    
    private char[] candidates = null;
    private boolean[] flags = null;
    private List<String> resultList = new ArrayList<>(); 
    
    private void getPermutation(String str) {
        if (str.length() == candidates.length) {
            resultList.add(str);
            return;
        }
        
        for (int i = 0; i < candidates.length; ++i) {
            if (i > 0 && candidates[i - 1] == candidates[i] && !flags[i - 1]) {
                continue;
            }
            
            if (!flags[i]) {
                flags[i] = true;
                getPermutation(str + candidates[i]);
                flags[i] = false;
            }
        }
    }
    
    public String[] permutation(String s) {
        this.candidates = s.toCharArray();
        this.flags = new boolean[candidates.length];
        
        Arrays.sort(candidates);
        
        getPermutation("");
        
        return resultList.toArray(new String[resultList.size()]);
    }
    
}

