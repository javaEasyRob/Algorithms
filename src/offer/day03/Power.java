package offer.day03;

/**
 * ClassName Power<br>
 * Function <br>
 * <p>给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。</p>
 * 解题思路
 * 偶次方的话等于n/2 * n/2的次方，奇数等于(n-1)/2*(n-1)/2*1的次方
 * 用右移代替了除以二，用&1代替了%2.
 *
 * @author 辛江勇<br>
 * @version 1.0.0
 * @date 2018/10/20 20:45<br>
 */
public class Power {
    public double Power(double base, int exponent) {
        if (base != 0 && exponent == 0) {
            return 1;
        }
        if (base == 0 && exponent < 0) {
            throw new RuntimeException();
        }
        if (base == 0 || exponent == 1) {
            return base;
        }
        int n = exponent < 0 ? -exponent : exponent;
        Double result = Power(base, n >> 1);
        result *= result;
        if ((n & 1) > 0) {
            result *= base;
        }
        return result;
    }
}
