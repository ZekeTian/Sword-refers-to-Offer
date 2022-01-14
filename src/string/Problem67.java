package string;

/**
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 * 
 * 题目描述：写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *        首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *        
 *        当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 *        假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *        
 *        该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *        注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 *        在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *        
 *        说明：
 *          假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。
 *          如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *  
 * 示例：
 *  示例 1
 *      输入: "42"
 *      输出: 42
 *      
 *  示例 2
 *      输入: "4193 with words"
 *      输出: 4193
 *      解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 *      
 *  示例 3
 *      输入: "words and 987"
 *      输出: 0
 *      解释: 第一个非空字符是 'w', 但它不是数字或正、负号。因此无法执行有效的转换。
 *  
 *  示例 4
 *      输入: "-91283472332"
 *      输出: -2147483648
 *      解释: 数字 "-91283472332" 超过 32 位有符号整数范围。因此返回 INT_MIN (−231) 。
 *  
 *  
 */
public class Problem67 {

    public static void main(String[] args) {
//        String str = "42";

//        String str = "4193 with words";
        
//        String str = "words and 987";
        
        String str = "-91283472332";
        
        _67Solution solution = new _67Solution();
        
        System.out.println(Integer.MAX_VALUE);
        
        System.out.println(solution.strToInt(str));
    }
}

/**
 * 思路：
 *  （1）删除字符串前后的空白符
 *  （2）确定符号以及遍历的起始位置
 *  （3）逐个字符遍历
 *      1）如果遇到非数字字符，则停止；否则继续
 *      2）判断是否越界，如果越界根据正负号返回；否则，拼接还原成数字
 * 
 * 越界判断处理技巧：
 *      正数的最大值是 2147483647 ，将边界值 boundry 设置成 214748364 = 2147483647 / 10
 *      越界的两种情况：
 *      1）当前结果 > boundry
 *        此时，再拼接一个数字，则必然大于最大值或小于最小值。如：当前数字 = 214748365，则拼接一个数字之后，最小为 2147483650。
 *        因为 2147483650 > 2147483647，所以如果是正数，则越界。同时，2147483650 > 2147483648 ，所以如果是负数，则越界。
 *      2）当前结果 = boundry，并且当前字符 > '7'
 *        如果当前字符 = '8'，则拼接后的数字是 2147483648，如果是正数，则越界，如果是负数，则没有越界。虽然是负数时没有越界，但是可以当作越界处理，
 *      因为最终返回的是 -2147483648，结果不会发生变化。
 *        如果当前数字 = 9，则拼接后的数字是 2147483649，必然越界。
 */
class _67Solution {
    
    public int strToInt(String str) {
        if (null == str || str.length() == 0) {
            return 0;
        }
        char[] charArr = str.trim().toCharArray();
        if (charArr.length == 0) {
            return 0;
        }
        
        int sign = (charArr[0] == '-' ? -1: +1);
        int startIdx = (charArr[0] == '-' || charArr[0] == '+') ? 1: 0;
        int boundry = Integer.MAX_VALUE / 10; // 越界的边界值
        int res = 0;
        
        for (int i = startIdx; i < charArr.length; ++i) {
            if (charArr[i] < '0' || charArr[i] > '9') { // 遇到非数字的字符，则终止循环
                break;
            }
            
            int num = charArr[i] - '0';
            if (res > boundry
                    || (res == boundry && num > 7)) { // 越界，则根据正负号返回最大值或最小值
                return (sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
            }
            
            res = res * 10 + num; // 将字符拼接还原成数字
        }
        
        return sign * res;
    }
}
