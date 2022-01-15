package queue;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 * 
 * 题目描述：请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 *        若队列为空，pop_front 和 max_value 需要返回 -1
 *        
 * 限制条件：
 *  （1）1 <= push_back,pop_front,max_value的总操作数 <= 10000
 *  （2）1 <= value <= 10^5
 * 
 * 示例：
 *  示例 1
 *      输入: ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 *           [[],[1],[2],[],[],[]]
 *      输出: [null,null,null,2,1,2]
 *  
 *  示例 2
 *      输入： ["MaxQueue","pop_front","max_value"]
 *           [[],[],[]]
 *      输出: [null,-1,-1]
 *      
 */
public class Problem59II {

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        
        System.out.println(maxQueue.max_value()); // 2
        
        maxQueue.pop_front();
        
        System.out.println(maxQueue.max_value()); // 2
    }
}

class MaxQueue {
    
    private LinkedList<Integer> dataQueue = null; // 该队列存放原始元素
    private LinkedList<Integer> maxQueue = null; // 该队列存放可以作为最大值的元素，其元素顺序保持非严格递减
    
    public MaxQueue() {
        this.dataQueue = new LinkedList<>();
        this.maxQueue = new LinkedList<>();
    }
    
    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }
    
    public void push_back(int value) {
        dataQueue.addLast(value);
        
        // 从 maxQueue 的队尾处将队列中小于 value 的元素全部移除，然后再添加 value，从而保证 maxQueue 的非严格递减
        while (!maxQueue.isEmpty() && value > maxQueue.peekLast()) {
            maxQueue.pollLast(); // 一旦 value 添加到队列中，则 maxQueue 里面比 value 小的元素都没有资格再待在 maxQueue 中，所以应当将其移除
        }
        
        maxQueue.addLast(value);
    }
    
    public int pop_front() {
        if (dataQueue.isEmpty()) {
            return -1;
        }
        int res = dataQueue.pollFirst();
        
        if (res == maxQueue.peekFirst()) {
            maxQueue.pollFirst(); // 如果出队的元素是最大值，则 maxQueue 中的最大值也一起出队
        }
        
        return res;
    }
}
