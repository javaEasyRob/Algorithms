package datastructure.tree;

import datastructure.wheel.TreeNode;

/**
 * ClassName BSTProblems<br>
 * Function <br>
 * <p>
 * 关于二叉树的一些操作。（二叉搜索树：非空左子树的所有键值小于根结点，飞控右子树的所有键值大于根节点，
 * 左右子树都是二叉搜索树）
 * </p>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class BSTProblems {
    /**
     * 返回二叉搜索树的最小节点
     *
     * @param root 二叉树的根结点
     * @return 返回最小元素所在的结点
     */
    public TreeNode getMin(TreeNode root) {
        TreeNode pre = root;
        while (root != null) {
            pre = root;
            root = root.left;
        }
        return pre;
    }
}
