package offer.april.day08;

import java.util.ArrayList;

/**
 * ClassName FindContinuousSequence<br>
 * Function <br>
 * 思路
 * <p>采用双指针，相当于有一个窗口，窗口的左右两边就是两个指针，我们根据窗口内值之和来确定窗口的位置和宽度。</p>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2019/4/8 18:00
 */
public class FindContinuousSequence {
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int low = 1, high = 2;
        int cur;
        while (low < high) {
            cur = (low + high) * (high - low + 1) >> 1;
            if (cur == sum) {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = low; i <= high; i++) {
                    list.add(i);
                }
                result.add(list);
                low++;
            } else if (cur < sum) {
                high++;
            } else {
                low++;
            }
        }
        return result;
    }
}
