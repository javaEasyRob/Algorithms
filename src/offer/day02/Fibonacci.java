package offer.day02;

/**
 * ClassName Fibonacci<br>
 * Function <br>
 * 斐波那契数列
 *
 * @author 辛江勇<br>
 * @version 1.0.0
 * @date 2018/10/20 20:45<br>
 */
public class Fibonacci {
    public int fibonacci(int n) {
        int a = 0, b = 1;
        while (n-- > 0) {
            b += a;
            a = b - a;
        }
        return a;
    }
}
