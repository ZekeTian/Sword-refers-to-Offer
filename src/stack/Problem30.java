package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 * 题目描述：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * 
 * 限制条件：各函数的调用总次数不超过 20000 次
 * 
 * 示例：
 *  MinStack minStack = new MinStack();
 *  minStack.push(-2);
 *  minStack.push(0);
 *  minStack.push(-3);
 *  minStack.min();   --> 返回 -3.
 *  minStack.pop();
 *  minStack.top();      --> 返回 0.
 *  minStack.min();   --> 返回 -2.
 * 
 */
public class Problem30 {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        
        // 输出： -3， 0， -2
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.min());
    }
}

/**
 * 利用两个栈实现，其中一个栈存储数据，另一个栈存储 “曾经最小” 的值。
 */
class MinStack {

    private Stack<Integer> dataStack = null; // 存储数据
    private Stack<Integer> minStack = null; // 存储 “曾经最小” 的值，栈顶是最新的最小值，栈底是最旧的最小值
    
    public MinStack() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }
    
    public void push(int x) {
        dataStack.push(x);
        
        // 判断 x 是否可以作为最小值，如果可以则用 minStack 记录下来
        int curMin = Integer.MAX_VALUE;
        if (!minStack.isEmpty()) {
            curMin = minStack.peek(); // minStack 不为空，则取出栈顶的元素，即当前最小值
        }
        if (x <= curMin) {
            minStack.push(x); // 当前插入的值可以成为最小值
        }
    }
    
    public void pop() {
        // 如果弹出的是最小值，则两个栈都应当删除
        int curMin = minStack.peek();
        int peek = dataStack.peek();
        
        dataStack.pop();
        if (curMin == peek) {
            minStack.pop(); // 弹出的元素是最小值，则 minStack 也需要弹出
        }
    }
    
    public int top() {
        return dataStack.peek();
    }
    
    public int min() {
        return minStack.peek();
    }
}
