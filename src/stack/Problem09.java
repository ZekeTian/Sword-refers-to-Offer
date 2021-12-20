package stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 
 * 题目描述：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 *        (若队列中没有元素，deleteHead 操作返回 -1 )
 *        
 * 限制条件：
 *  （1）1 <= values <= 10000
 *  （2）最多会对 appendTail、deleteHead 进行 10000 次调用
 * 
 * 示例：
 *  输入：["CQueue","appendTail","deleteHead","deleteHead"]
 *      [[],[3],[],[]]
 *  输出：[null,null,3,-1]
 * 
 */
public class Problem09 {

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        
        queue.appendTail(3);

        // 输出：3，-1
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}

/**
 * 利用两个栈模拟，一个栈负责接收输入的数字，另一个栈负责输出
 */
class CQueue {
    
    private Stack<Integer> inputStack = null;
    private Stack<Integer> outputStack = null;
    
    public CQueue() {
        this.inputStack = new Stack<>();
        this.outputStack = new Stack<>();
    }
    
    public void appendTail(int value) {
        inputStack.push(value); // 添加数字时，inputStack 负责添加
    }
    
    public int deleteHead() {
        if (inputStack.isEmpty() && outputStack.isEmpty()) {
            return -1;
        }
        
        if (outputStack.isEmpty()) {
            move(); // 当输出栈为空时，则将输入栈的数字移动到输出栈
        }
        
        return outputStack.pop();
    }
    
    // 将输入栈的数字转移到输出栈
    private void move() {
        while (!inputStack.isEmpty()) {
            outputStack.push(inputStack.pop());
        }
    }
}
