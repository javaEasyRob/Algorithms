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
}
