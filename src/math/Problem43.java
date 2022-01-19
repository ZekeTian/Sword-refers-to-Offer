package math;


/**
 * https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
 * 
 * 题目描述：输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *        例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *        
 * 限制条件：1 <= n < 2^31
 * 
 * 示例：
 *  示例 1
 *      输入：n = 12
 *      输出：5
 * 
 *  示例 2
 *      输入：n = 13
 *      输出：6
 * 
 */
public class Problem43 {

    public static void main(String[] args) {
//        int n = 12;
        
//        int n = 13;
        
        int n = 1410065408;
        
        _43Solution solution = new _43Solution();
        
        System.out.println(solution.countDigitOne(n));
    }
}

class _43Solution {
    
    public int countDigitOne(int n) {
        int i = 1; // 位数（表示个位、十位、百位...）
        int cur = n % 10; // n 中第 i 位的数字
        int high = n / 10; // 高位
        int low = 0; // 低位
        int count = 0;
        
        // 分别统计个位、十位...最高位上的 1，每次循环统计一个位上的 1，第 1 次循环统计个位，第 2 次循环统计十位...
        while (high != 0 || cur != 0) {
            if (cur == 0) {
                // 高位可能出现的情况：0 ~ high-1，总共有 high 种。低位可能出现的情况：0~i-1，总共有 i 种
                // 所以组合起来，总共有：high * i 种
                count = count + high * i;
            } else if (cur == 1){
                // 高位可能出现的情况：0 ~ high，总共有 high + 1 种。
                // 高位是 0~high-1 时，低位可以是 0~i-1 的任意一种，但是当高位是 high 时，低位不能超过 low。
                // 所以有两种情况，高位是 0~high-1 时，低位可以是 0~i-1，总共有：high * i 种
                //            高位是 high 时，低位是 0~low，总共有 low + 1 种
                count = count + high * i + low + 1;
            } else { // cur > 1 时
                // 高位可能出现的情况：0~high，总共有 high + 1 种。因为此时 cur > 1 ，所以当高位是 high 时，低位依然可以取 0~i-1 
                // 即对于 0~high 种可能的高位，每种高位对应的低位都可以取 0~i-1，所以总共有：(high + 1) * i
                count = count + (high + 1) * i;
            }
            
            // 更新各个值
            i *= 10; // 前进一位。如：1234，当前位是十位，前进一位即是百位
            cur = high % 10; // 当前 high 位的最后一个数就是下一次循环的 cur。如：1234，当前位是十位，high 是 12，下一次的 cur 则是 2
            high = high / 10; // 当前 high 缩小 10 倍，即前进一位。如：1234，当前位是十位，high 是 12，下一次的 high 是 1
            low = n % i; // 模上当前位，即可得到低位，如：1234，当前位是十位，前进一位即是百位，1234 模上 100 得 34，即下一次的 low 位 
        }
        
        return count;
    }
}

