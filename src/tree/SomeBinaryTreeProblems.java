package tree;

import wheel.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName SomeBinaryTreeProblems<br>
 * Function <br>
 * 一些关于二叉树的常见操作<br>
 *
 * @author 辛江勇
 * @version 1.0.0
 */
public class SomeBinaryTreeProblems {
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
     * 给出二叉树的前序和中序遍历，构建二叉树。
     *
     * @param pre 前序遍历序列
     * @param in  中序遍历序列
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length != in.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(in.length);
        //快速得到元素的索引
        int i = 0;
        for (int number : in) {
            map.put(number, i++);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (preEnd < preStart) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        //根节点元素在中序序列中的位置
        int index = map.get(pre[preStart]);
        int leftLength = index - inStart;
        root.left = reConstructBinaryTree(pre, preStart + 1, preStart + leftLength, in, inStart, index - 1, map);
        root.right = reConstructBinaryTree(pre, preStart + leftLength + 1, preEnd, in, index + 1, inEnd, map);
        return root;
    }
}
