package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 * 
 * 题目描述：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 *        例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 * 
 * 限制条件：
 *  （1）0 <= pushed.length == popped.length <= 1000
 *  （2）0 <= pushed[i], popped[i] < 1000
 *  （3）pushed 是 popped 的排列
 *  
 * 示例：
 *  示例 1
 *      输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 *      输出：true
 *      解释：我们可以按以下顺序执行：
 *          push(1), push(2), push(3), push(4), pop() -> 4,
 *          push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *  
 *  示例 2
 *      输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 *      输出：false
 *      解释：1 不能在 2 之前弹出。
 *      
 */
public class Problem31 {

    public static void main(String[] args) {
        // test case2, output: true
        int[] pushed = { 1, 2, 3, 4, 5 };
        int[] popped = { 4, 5, 3, 2, 1 };
        
        
        // test case2, output: false
//        int[] pushed = { 1, 2, 3, 4, 5 };
//        int[] popped = { 4, 3, 5, 1, 2 };
        
        _31Solution solution = new _31Solution();
        
        System.out.println(solution.validateStackSequences(pushed, popped));
    }
}

/**
 * 思路：利用一个栈模拟入栈、出栈的过程。
 *  若 pushed 序列未处理完，则执行如下操作，直到 pushed 序列处理完毕。
 *      当 popped 序列中当前元素 = 栈顶元素，此时栈可以执行出栈操作（出栈只能在此时执行，其它时候执行则不能产生符合的出栈序列），popped 序列的下标增加
 *      当 popped 序列中当前元素 != 栈顶元素，此时栈执行入栈操作，pushed 序列的下标增加
 *  当栈中还有元素，则入栈序列和出栈序列未能成功匹配，即入栈、出栈序列不合法。
 *  
 */
class _31Solution {
    
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int popIdx = 0;
        
        for (int i = 0; i < pushed.length; ++i) {
            stack.push(pushed[i]); // 栈为空 或 栈顶与 popped 序列当前元素不相等，则栈只能执行入栈操作

            while (!stack.isEmpty() && stack.peek() == popped[popIdx]) {
                stack.pop(); // 栈顶元素与 popped 序列当前元素一样，则栈必须执行出栈操作，否则无法匹配
                ++popIdx;
            }
        }
        
        return stack.isEmpty(); // 如果栈中还有元素，则说明 pushed、popped 序列不能完全匹配，即非法
    }
    
}


