package offer.day04;

/**
 * ClassName ReverseList<br>
 * Function <br>
 * 题目描述<br>
 * 输入一个链表，反转链表后，输出新链表的表头。<br>
 *
 * @author 辛江勇<br>
 * @version 1.0.0
 * @date 2018/10/20 20:45<br>
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = null;
        ListNode pre = null;
        while (head != null) {
            next = head.next;//暂存当前元素的下一个结点
            head.next = pre;//将当前元素得指向反向
            pre = head;//记录当前元素
            head = next;//移动元素
        }
        return pre;
    }
}
