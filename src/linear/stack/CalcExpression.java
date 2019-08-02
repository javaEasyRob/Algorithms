package linear.stack;

import javax.sound.midi.Soundbank;
import javax.xml.stream.FactoryConfigurationError;
import java.util.Comparator;
import java.util.Stack;

/**
 * ClassName CalcExpression<br>
 * Function <br>
 *
 * @author 辛江勇
 * @version 1.0.0
 * @date 2019/8/2 13:23
 */
public class CalcExpression {
    public static void main(String[] args) {
        CalcExpression calcExpression = new CalcExpression();
        String s = calcExpression.translate("1+2*2/(1+1)+2*1");
        System.out.println(s);
        System.out.println(calcExpression.calc(s));
    }

    public int calc(String source) {
        Stack<Integer> numbers = new Stack<>();
        char[] chars = source.toCharArray();
        int res = 0;
        for (char c : chars) {
            if (isNumber(c)) {
                numbers.push(Integer.valueOf("" + c));
            } else {
                if (numbers.size() < 2) {
                    throw new RuntimeException("Error expression");
                }
                res = Symbol.valueOf(c).calc(numbers.pop(), numbers.pop());
                numbers.push(res);
            }
        }
        if (numbers.size() > 1) {
            throw new RuntimeException("Error expression");
        }
        return res;
    }

    public String translate(String expression) {
        Stack<Symbol> symobls = new Stack<>();
        StringBuilder res = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char c : chars) {
            if (!isRight(c)) {
                throw new RuntimeException("UnSupport Symbol " + c);
            }
            if (isNumber(c)) {
                res.append(c);
                continue;
            }
            Symbol symbol = Symbol.valueOf(c);
            switch (symbol) {
                case LEFT:
                    symobls.push(symbol);
                    break;
                case RIGHT:
                    Symbol cur = null;
                    //一直出栈直到左括号为止
                    while (!symobls.empty() && (cur = symobls.pop()) != Symbol.LEFT) {
                        res.append(cur.getSymbol());
                    }
                    //如果没有元素出栈或者最后一个元素出栈也不是(
                    if (cur == null || cur.getSymbol() != '(') {
                        throw new RuntimeException("UnMatch Right");
                    }
                    break;
                default:
                    //一直出栈直到栈顶元素小于当前元素
                    while (!symobls.empty() && symobls.peek() != Symbol.LEFT
                            && symobls.peek().compareToOter(symbol) >= 0) {
                        res.append(symobls.pop().getSymbol());
                    }
                    symobls.push(symbol);
            }
        }
        //将剩余的符号出栈
        while (!symobls.empty()) {
            //如果栈底还有左括号说明没有遇到与之对应的右括号
            if (symobls.peek().getSymbol() == '(') {
                throw new RuntimeException("UnMatch Left");
            }
            res.append(symobls.pop().getSymbol());
        }
        return res.toString();
    }

    /**
     * 返回传入字符是数字吗
     * @param value 要测试的字符
     * @return 如果该字符为数字返回true，否则false
     */
    private boolean isNumber(char value) {
        return value <= '9' && value >= '0';
    }

    /**
     * 返回指定字符是否为合法输入
     * @param value 要检测的字符
     * @return 如果合法返回true，否则返回false
     */
    private boolean isRight(char value) {
        switch (value) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '(':
            case ')':
                return true;
        }
        return isNumber(value);

    }
}

/**
 * 符号类
 */
enum Symbol {

    /**
     * 加法
     */
    ADD(1, '+') {
        @Override
        public int calc(int a, int b) {
            return a + b;
        }
    },
    /**
     * 减法
     */
    SUBTRACTION(1, '-') {
        @Override
        public int calc(int a, int b) {
            return a - b;
        }
    },
    /**
     * 乘法
     */
    MULTIPLY(2, '*') {
        @Override
        public int calc(int a, int b) {
            return a * b;
        }
    },
    /**
     * 除法
     */
    DIVIDE(2, '/') {
        @Override
        public int calc(int a, int b) {
            return b / a;
        }
    },
    /**
     * 左括號
     */
    LEFT(3, '(') {
        @Override
        public int calc(int a, int b) {
            throw new RuntimeException("UnSupport operation");
        }
    },
    /**
     * 右括号
     */
    RIGHT(3, ')') {
        @Override
        public int calc(int a, int b) {
            throw new RuntimeException("UnSupport operation");
        }
    };

    private char symbol;

    private Integer level;


    Symbol(Integer level, char symbol) {
        this.symbol = symbol;
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    static Symbol valueOf(char symbol) {
        switch (symbol) {
            case '+':
                return ADD;
            case '-':
                return SUBTRACTION;
            case '*':
                return MULTIPLY;
            case '/':
                return DIVIDE;
            case '(':
                return LEFT;
            default:
                return RIGHT;
        }
    }


    public int compareToOter(Symbol symbol) {
        return this.level - symbol.getLevel();
    }

    public abstract int calc(int a, int b);
}
