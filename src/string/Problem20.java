package string;


/**
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 * 
 * 题目描述：请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *        数值（按顺序）可以分成以下几个部分：
 *          （1）若干空格
 *          （2）一个 小数 或者 整数
 *          （3）（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 *          （4）若干空格
 *       小数（按顺序）可以分成以下几个部分：
 *          （1）（可选）一个符号字符（'+' 或 '-'）
 *          （2）下述格式之一：
 *              1）至少一位数字，后面跟着一个点 '.'
 *              2）至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 *              3）一个点 '.' ，后面跟着至少一位数字
 *       整数（按顺序）可以分成以下几个部分：
 *          （1）（可选）一个符号字符（'+' 或 '-'）
 *          （2）至少一位数字
 *       
 *       部分数值列举：["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 *       部分非数值列举：["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 20
 *  （2）s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 *  
 *  示例：
 *   示例 1
 *      输入：s = "0"
 *      输出：true
 *   
 *   示例 2
 *      输入：s = "e"
 *      输出：false
 *   
 *   示例 3
 *      输入：s = "."
 *      输出：false
 *   
 *   示例 4
 *      输入：s = "    .1  "
 *      输出：true
 * 
 */
public class Problem20 {

    public static void main(String[] args) {
//        String s = "0";
//        String s = "e";
//        String s = ".";
//        String s = ".1";
        
        String s = ".e2";
        
        _20Solution solution = new _20Solution();
        
        
        System.out.println(solution.isNumber(s));
    }
}

class _20Solution {
    
    private char[] charArr = null;
    private int index = 0;
    
    // 如果能够扫描出一部分数字，则返回 true；否则，返回 false
    private boolean scanNum() {
        if (index < charArr.length && (charArr[index] == '+' || charArr[index] == '-')) {
            ++index;
        }
        
        return scanUnsingedNum();
    }
    
    private boolean scanUnsingedNum() {
        int tmp = index;
        while (index < charArr.length && (charArr[index] >= '0' && charArr[index] <= '9')) {
            ++index;
        }
        return index > tmp;
    }
    
    public boolean isNumber(String s) {
        this.charArr = s.trim().toCharArray();
        
        if (charArr.length == 0) {
            return false;
        }
        
        // 合法数字格式：整数[.[无符号整数]][e[整数]]，所以依次扫描：整数、"."、无符号整数、"e"、整数
        // 扫描整数
        boolean flag = scanNum();
        
        // 扫描 .
        if (index < charArr.length && charArr[index] == '.') {
            ++index;
            // 扫描无符号整数（. 后面的整数不能有 +、-，所以使用 scanUnsingedNum）
            flag = scanUnsingedNum() || flag; // 因为 "." 之前、之后可以没有数字，所以用 || ，如：.1，1.
        }
        
        // 扫描 e
        if (index < charArr.length && (charArr[index] == 'E' || charArr[index] == 'e')) {
            ++index;
            // 扫描整数（e 后面的整数可以有 +、-，所以用 scanNum）
            flag = scanNum() && flag; // e 之前、之后必须要有数字，所以 flag 、scanNum 必须要全部为 true，故用 && 
        }
        
        return flag && (index == charArr.length); // 前面合法并且已经处理完毕，则说明 s 整体是合法的数字
    }
}
