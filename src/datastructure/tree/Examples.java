package datastructure.tree;

import datastructure.wheel.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName Examples<br>
 * Function <br>
 * 一些关于树的常见操作<br>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class Examples {
    /**
     * 递归形式返回指定结点的深度
     *
     * @param root 树的根结点
     * @return 返回待求结点的深度
     */
    public int getHeight(TreeNode root) {
        if (root != null) {
            int HL = getHeight(root.left);
            int HR = getHeight(root.right);
            return Math.max(HL, HR) + 1;
        }
        return 0;
    }

    /**
     * 非递归形式返回树的深度，采用的是树的层次遍历方式。
     *
     * @param root 树的根结点
     * @return 树的深度
     */
    public int getHeight1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int front = -1;
        int rear = -1;
        int last = 0;
        int level = 0;
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(++rear, root);
        while (front < rear) {
            root = nodes.get(++front);
            if (root.left != null) {
                nodes.add(++rear, root.left);
            }
            if (root.right != null) {
                nodes.add(++rear, root.right);
            }
            if (front == last) {
                level++;
                last = rear;
            }
        }
        return level;
    }

    /**
     * 查看两颗树是否同构。（同构：两颗树可以通过交换左右子树，最后两棵树相同。）
     *
     * @param root1 树1的根节点
     * @param root2 树2的根节点
     * @return 返回两颗树是否同构
     */
    public boolean isomorphic(TreeNode root1, TreeNode root2) {
        ///both empty
        if (root1 == null && root2 == null) {
            return true;
        }
        //one of them is empty
        if (root1 == null || root2 == null) {
            return false;
        }
        //roots are different
        if (root1.val != root2.val) {
            return false;
        }
        //both have no left subtree
        if (root1.left == null && root2.left == null) {
            return isomorphic(root1.right, root2.right);
        }
        //no need to swap the left and the right
        if (root1.left != null && root2.left != null && root1.left.val == root2.left.val) {
            return isomorphic(root1.left, root2.left) && isomorphic(root1.right, root2.right);
        }
        //need to swap the left and the right
        return isomorphic(root1.left, root2.right) && isomorphic(root1.right, root2.left);
    }

    /**
     * <p>
     * 返回二叉搜索树的最小节点（二叉搜索树：非空左子树的所有键值小于根结点，飞控右子树的所有键值大于根节点，
     * 左右子树都是二叉搜索树）
     * </p>
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
