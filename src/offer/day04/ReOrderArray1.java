package offer.day04;

/**
 * ClassName ReOrderArray1<br>
 * Function <br>
 * 要求偶数和奇数的相对位置没有发生改变
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class ReOrderArray1 {
    public void reOrderArray(int[] array) {
        int len = array.length;
        int t;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if ((array[j] & 1) == 0 && (array[j + 1] & 1) == 1) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
            }
        }
    }
}
