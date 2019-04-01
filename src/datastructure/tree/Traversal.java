package datastructure.tree;

import datastructure.wheel.SqQueue;
import datastructure.wheel.TreeNode;

import java.util.Stack;

/**
 * ClassName Traversal<br>
 * Function <br>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class Traversal {
    /**
     * 递归先序、中序、后序遍历
     *
     * @param root 待遍历树的根节点
     */
    public void preOderTraversal(TreeNode root) {
        if (root != null) {
            //访问语句的放置顺序不同，对应不同的遍历方式
            System.out.println(root.val);
            preOderTraversal(root.left);
            //此时访问为inOrderTraversal
            preOderTraversal(root.right);
            //此时访问为postOrderTraversal
        }
    }

    /**
     * 非递归前、中序遍历
     *
     * @param root 待遍历树的根节点
     */
    public void inOrderTraversal(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        while (root != null || !s.isEmpty()) {
            if (root != null) {
                //此时为先序遍历
                System.out.println(root.val);
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();
                //此时为中序遍历
                root = root.right;
            }
        }
    }

    /**
     * 使用队列实现的层次遍历
     *
     * @param root 待遍历树的根节点
     */
    public void levelTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        SqQueue<TreeNode> queue = new SqQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            root = queue.dequeue();
            System.out.println("<==\t" + root.val);
            if (root.left != null) {
                System.out.println("==>\t" + root.left.val);
                queue.enqueue(root.left);
            }
            if (root.right != null) {
                System.out.println("==>\t" + root.right.val);
                queue.enqueue(root.right);
            }
        }
    }

    /**
     * 测试遍历方法。
     *          1
     *         / \
     *        2   3
     *       /\  /\
     *      4 5 6 7
     */
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Traversal traversal = new Traversal();
        traversal.levelTraversal(node1);
        traversal.inOrderTraversal(node1);
    }
}
