package offer.day05;

import java.util.Stack;

/**
 * ClassName StackForMin<br>
 * Function <br>
 * <p>
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数
 * （时间复杂度应为O（1））。
 * </p>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class StackForMin {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    //小的入s2栈
    public void push(int node) {
        s1.push(node);
        if (s2.isEmpty()) {
            s2.push(node);
        } else if (node <= s2.peek()) {
            s2.push(node);
        }
    }

    //出栈同步，将重复的剔除。
    public void pop() {
        if (s1.peek().equals(s2.peek())) {
            s2.pop();
        }
        s1.pop();
    }

    public int top() {
        return s2.peek();
    }

    public int min() {
        return s2.peek();
    }
}
