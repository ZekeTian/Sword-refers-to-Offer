package bit;

/**
 * https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 * 
 * 题目描述：写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 * 
 * 限制条件：
 *  （1）a, b 均可能是负数或 0
 *  （2）结果不会溢出 32 位整数
 *  
 *  示例：
 *      输入: a = 1, b = 1
 *      输出: 2
 *      
 */
public class Problem65 {

    public static void main(String[] args) {
        int a = 1, b = 1;

//        int a = -1, b = 4;
        
//        _65Solution1 solution = new _65Solution1();

        _65Solution2 solution = new _65Solution2();
        
        System.out.println(solution.add(a, b));
    }
}

/**
 * 解法一：递归实现
 *      a + b = 进位 + 本位
 *      进位： (a & b) << 1
 *      本位： a ^ b
 *      为了让 进位 + 本位，所以再调一次 add 方法，求得 进位 + 本位
 *      
 *      当进位是 0 时，结束递归。因为当进位是 0 时，之后的每次相加操作的结果都是一样的，不会发生变化，已经得出最终结果。具体分析过程如下：
 *          当 a = 0 时， add(a, b) = add(0, b) = b
 *          下一次操作时，进位 a = 0，b 的值不变，则 add(a, b) = add(0, b) = b
 *          再下一次操作时，进位 a = 0，b 的值不变，则 add(a, b) = add(0, b) = b，与之前的结果是一样的，之后的结果也不会再发生变化，所以此时可以结束递归。
 */
class _65Solution1 {
    
    // a 表示 进位，b 表示 本位
    public int add(int a, int b) {
        if (0 == a) {
            return b;
        }
        
        return add((a & b) << 1, a ^ b);
    }
}

/**
 * 解法二：循环实现
 */
class _65Solution2 {
    
    public int add(int a, int b) {
        
        while (a != 0) { // 进位不为 0 时，继续
            int carry = (a & b) << 1; // 进位
            int tmp = a ^ b; // 本位
            
            // 更新 a、b 的值，再进行一次循环，从而将进位和本位相加
            a = carry;
            b = tmp;
        }
        
        return b;
    }
}
