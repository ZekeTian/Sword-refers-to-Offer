package sort;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/solution/
 * 
 * 题目描述：如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 *        如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *        例如，
 *          [2,3,4] 的中位数是 3
 *          [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *        设计一个支持以下两种操作的数据结构：
 *          void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 *          double findMedian() - 返回目前所有元素的中位数。
 * 
 * 限制条件：最多会对 addNum、findMedian 进行 50000 次调用
 * 
 * 示例：
 *  示例 1
 *      输入： ["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
 *           [[],[1],[2],[],[3],[]]
 *      输出：[null,null,null,1.50000,null,2.00000]
 *      
 *  示例 2
 *      输入： ["MedianFinder","addNum","findMedian","addNum","findMedian"]
 *           [[],[2],[],[3],[]]
 *      输出：[null,null,2.00000,null,2.50000]
 *      
 */
public class Problem41 {

    public static void main(String[] args) {
//        MedianFinder1 medianFinder = new MedianFinder1();

        MedianFinder2 medianFinder = new MedianFinder2();
        
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        
        // 1.5
        System.out.println(medianFinder.findMedian());
        
        medianFinder.addNum(3);
        
        // 2.00
        System.out.println(medianFinder.findMedian());
    }
}

/**
 * 解法一：实现方式一（该解法会更详细地展示如何维护最大堆、最小堆的元素个数关系）
 * 
 * 使用最大堆和最小堆解决。
 * 中位数将数字分成两部分，左边是偏小部分，右边是偏大部分，为了方便找到中位数，使用最大堆存储偏小部分（方便找到最大值），使用最小堆存储偏大部分（方便找到最小值）。
 * 在添加元素时，确保： 0 <= 最大堆的元素个数 - 最小堆的元素个数 <= 1 
 * 如果最大堆的元素个数 - 最小堆的元素个数 = 0，则总个数是偶数，中位数 = (最大堆的堆顶元素 + 最小堆的堆顶元素) / 2.0 
 * 如果最大堆的元素个数 - 最小堆的元素个数 = 1，则总个数是偶数，中位数 = 最大堆的堆顶元素
 * 
 * 为了确保始终满足以上的关系，在添加元素 x 时，需要进行如下的处理：
 *  若 x <= 最大堆的堆顶元素（添加到最大堆）：
 *      当最大堆的堆顶元素个数 = 最小堆的堆顶元素，则直接将 x 加入最大堆
 *      当最大堆的堆顶元素个数 > 最小堆的堆顶元素，将最大堆的堆顶元素移到最小堆中，然后将 x 加入最大堆（这是为了确保最大堆和最小堆元素个数之间的关系）
 *  若 x >= 最小堆的堆顶元素（添加到最小堆）：
 *      当最大堆的堆顶元素个数 = 最小堆的堆顶元素，则将最小堆的堆顶元素移到最大堆中，然后将 x 加入最小堆
 *      当最大堆的堆顶元素个数 > 最小堆的堆顶元素，直接将 x 加入最小堆
 *  若 最大堆的堆顶元素 < x < 最小堆的堆顶元素，则从大小来看， x 可以添加到任意一个堆中，但是为了保证满足以上关系，需要根据两个堆的元素个数进行确定：
 *      当最大堆的堆顶元素个数 = 最小堆的堆顶元素，则将 x 放进最大堆中
 *      当最大堆的堆顶元素个数 > 最小堆的堆顶元素，则将 x 放进最小堆中
 *      
 * 将 x 放进一个堆中，需要考虑两个因素：
 *  （1）值大小关系（x 与最大堆、最小堆的堆顶）
 *  （2）最小堆、最大堆之间的元素个数关系
 */
class MedianFinder1 {
    
    PriorityQueue<Integer> minHeap = null;
    PriorityQueue<Integer> maxHeap = null;

    /** initialize your data structure here. */
    public MedianFinder1() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((x, y) -> (y - x));
    }
    
    public void addNum(int num) {
        if (maxHeap.isEmpty()) { // 添加首个元素
            maxHeap.add(num);
            return;
        }
        
        Integer maxPeek = maxHeap.peek();
        if (num <= maxPeek) { // 添加到最大堆中
            if (maxHeap.size() > minHeap.size()) { // 最大堆先腾出空位，然后再放
                minHeap.add(maxHeap.poll());
            }
            maxHeap.add(num);
            return;
        }
        
        // 从值的大小来看，num 可以放进最小堆中。同时，因为现在最大堆不为空、最小堆为空，从堆的元素个数来看，也可以放进最小堆中
        if (minHeap.isEmpty()) {
            minHeap.add(num);
            return;
        }
        
        Integer minPeek = minHeap.peek();
        if (num >= minPeek) { // 添加到最小堆中
            if (maxHeap.size() == minHeap.size()) { // 最小堆先腾出空位，然后再放
                maxHeap.add(minHeap.poll());
            }
            minHeap.add(num);
            return;
        }
        
        // maxPeek < num < minPeek 时，从值的大小来看，num 可以放进任意一个堆中，但还是需要根据堆的元素个数确定
        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(num); // 最大堆的元素偏多，不能再向最大堆中放，只能放最小堆中
        } else {
            maxHeap.add(num); // 当最大堆的元素等于最小堆时，可以放进最大堆中
        }
    }
    
    public double findMedian() {
        
        if (minHeap.size() == maxHeap.size()) { // 总元素个数为偶数，则是两个堆顶元素之和的一半
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        
        return maxHeap.peek(); // 总元素个数为奇数，则是最大堆中的堆顶元素（因为在添加时，是确保最大堆元素个数比最小堆多）
    }
}

/**
 * 解法二：实现方式二（该解法在维护最大堆、最小堆元素个数关系时会更简洁）
 * 
 * 在添加元素时，确保： 0 <= 最大堆的元素个数 - 最小堆的元素个数 <= 1
 * 
 * 当 maxHeap.size() == minHeap.size() 时， maxHeap 的元素个数应当增加。
 *   但是新数 num 不能直接添加到 maxHeap 中，因为 num 可能比较大，实际应当放进 minHeap 中。
 *   为了解决这个问题，可以将 num 先添加到 minHeap 中，然后再将 minHeap 中的堆顶元素移到 maxHeap 中，这样就可以使得 maxHeap 的元素个数增加。
 *   num 有如下两种可能，现在分析上述解法如何保证正确性：
 *     当 num 比较大，实际应当添加到 minHeap 时：
 *        把 num 添加到 minHeap 后，minHeap 堆顶元素不会变，之后将 minHeap 堆顶元素移到 maxHeap 中 （最终，num 在 minHeap 中）
 *        过程示意如下：
 *                                ...maxPeek | minPeek... （左边元素是最大堆，右边是最小堆，maxPeek 是最大堆的堆顶，minPeek 是最小堆的堆顶）
 *        添加 num 到最小堆中         ...maxPeek | minPeek..,num,..
 *        minHeap 转移到 maxHeap    ...maxPeek,minPeek | minPeek',..,num.. （转移后，minPeek 成为最大堆的堆顶元素，minPeek' 是最小堆的堆顶元素）
 *     当 num 比较小，实际应当添加到 maxHeap 时：
 *        把 num 添加到 minHeap 后，minHeap 堆顶元素变为 num，之后将 minHeap 堆顶元素（num）移到 maxHeap 中（最终，num 在 maxHeap 中）
 *        过程示意如下：
 *                                ...maxPeek | minPeek... （左边元素是最大堆，右边是最小堆，maxPeek 是最大堆的堆顶，minPeek 是最小堆的堆顶）
 *        添加 num 到最小堆中         ..maxPeek | num,minPeek... （num 比较小，成为最小堆中的堆顶元素）
 *        minHeap 转移到 maxHeap    ..,num,..maxPeek | minPeek.. （转移后，num 进入 maxHeap，当然 num 也可能成为最大堆的堆顶元素）
 *   综上，最终 num 所在的堆与实际应当添加的堆一致，所以最终可以保证结果正确。
 * 
 * 当 maxHeap.size() > minHeap.size() 时， minHeap 的元素个数应当增加。
 *  同理，这种情况下不能直接将 num 添加到 minHeap 中。因为如果  num 比较小，num 实际应当添加到 maxHeap 中。
 *  解决方法一样，先 num 添加到 maxHeap 中，然后再将 maxHeap 的堆顶元素移到 minHeap 中，具体分析过程同上。
 */
class MedianFinder2 {
    
    private PriorityQueue<Integer> minHeap = null;
    private PriorityQueue<Integer> maxHeap = null;

    /** initialize your data structure here. */
    public MedianFinder2() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((x, y) -> (y - x));
    }
    
    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) { // 此时，maxHeap 的元素个数应该增加，但是 num 不能直接加入到 maxHeap 。
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        } else { // 此时，minHeap 中的元素个数应当添加，但是 num 不能直接添加到 minHeap 中
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}
