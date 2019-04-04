package offer.march.day04;

import java.util.Arrays;

/**
 * ClassName ReOrderArray1<br>
 * Function <br>
 * 题目描述<br>
 * <p>输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保
 * 证奇数和奇数，偶数和偶数之间的相对位置不变。</p>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class ReOrderArray {
    public static int[] reOrderArray(int[] array) {
        int len = array.length;
        int start = 0, end = len - 1;
        int temp;
        while (start < end) {
            while (start < end && (array[start] & 1) == 0) {
                start++;
            }
            while (start < end && (array[end] & 1) == 1) {
                end--;
            }
            if (start < end) {
                temp = array[start];
                array[start] = array[end];
                array[end] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = reOrderArray(new int[]{1, 2, 3, 4, 5});
        System.out.println(Arrays.toString(array));
    }
}