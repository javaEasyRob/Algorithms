package offer.day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ClassName PrintMinNumber<br>
 * Function <br>
 * <p>输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入
 * 数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。</p>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2018/10/20 20:45
 */
public class PrintMinNumber {
    public String PrintMinNumber(int[] numbers) {
        StringBuilder str = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        for (int number : numbers) {
            list.add(number);
        }

        list.sort((o1, o2) -> {
            String str1 = o1 + "" + o2;
            String str2 = o2 + "" + o1;
            return str1.compareTo(str2);
        });
        for (int i : list) {
            str.append(i);
        }
        return list.toString();
    }
}
