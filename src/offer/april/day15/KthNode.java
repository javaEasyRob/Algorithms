package offer.april.day15;

import datastructure.wheel.TreeNode;

import java.util.Stack;

/**
 * ClassName KthNode<br>
 * Function <br>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2019/4/15 20:55
 */
public class KthNode {
    TreeNode kThNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while (!stack.isEmpty() || pRoot != null) {
            if (pRoot != null) {
                stack.push(pRoot);
                pRoot = pRoot.left;
            } else {
                count++;
                pRoot = stack.pop();
                if (count == k) {
                    return pRoot;
                }
                pRoot = pRoot.right;
            }
        }
        return null;
    }
}
