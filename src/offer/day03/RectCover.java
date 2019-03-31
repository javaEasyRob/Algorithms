package offer.day03;

/**
 * ClassName RectCover<br>
 * Function <br>
 * 题目描述
 * <p>我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？</p>
 * 解题思路
 *
 * @author 辛江勇<br>
 * @version 1.0.0
 * @date 2018/10/20 20:45<br>
 */
public class RectCover {
    public int rectCover(int target) {
        if (target <= 2) {
            return target;
        } else {
            return rectCover(target - 1) + rectCover(target - 2);
        }
    }
}
