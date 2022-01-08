package divideconquer;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 * 
 * 题目描述：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 
 * 限制条件：数组长度 <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入: [1,6,3,2,5]
 *      输出: false
 *  
 *  示例 2
 *      输入: [1,3,2,6,5]
 *      输出: true
 */
public class Problem33 {

    public static void main(String[] args) {
        // test case1, output: false
//        int[] postorder = { 1, 6, 3, 2, 5 };
        
        // test case2, output: true
//        int[] postorder = { 1, 3, 2, 6, 5 };
        
        // test case3, output: true
        int[] postorder = { 5, 4, 3, 2, 1 }; // 根顶点为 1，左子树为空
        
        _33Solution solution = new _33Solution();
        
        System.out.println(solution.verifyPostorder(postorder));
    }
}


class _33Solution {
    
    // 判断 postorder[left, index] 是否都小于 root，posterorder[index + 1, right - 1] 是否都大于 root
    // 如果两个都满足，则返回 true；否则，返回 false
    private boolean verify(int[] postorder, int left, int right, int index, int root) {
        // postorder[left, index] 表示左子树，posterorder[index + 1, right - 1] 表示右子树
        for (int i = left; i <= index; ++i) {
            if (postorder[i] > root) {
                return false;
            }
        }
        
        for (int i = index + 1; i < right; ++i) {
            if (postorder[i] < root) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean verifyPostorder(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right];
        
        // 寻找到正确的左右子树边界
        int index = 0; // postorder[left, index] 表示左子树，posterorder[index + 1, right - 1] 表示右子树
        for (index = left - 1; index < right; ++index) { // index 从 left - 1 处开始，是为了包含左子树为空的情况
            if (verify(postorder, left, right, index, root)) {
                break;
            }
        }
        
        if (index == right) {
            return false; // 未找到正确的左右子树边界，则直接返回 false
        }
        
        return verifyPostorder(postorder, left, index) && verifyPostorder(postorder, index + 1, right - 1);
    }
    
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }
}