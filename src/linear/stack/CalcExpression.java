package linear.stack;

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

    /**
     * 将后缀表达式字符串求值
     *
     * @param source 后缀表达式
     * @return 后缀表达式的计算结果
     */
    public int calc(String source) {
        Stack<Integer> numbers = new Stack<>();
        char[] chars = source.toCharArray();
        int res = 0;
        for (char c : chars) {
            if (isNumber(c)) {
                numbers.push(Integer.valueOf("" + c));
            } else {
                //如果操作数不足
                if (numbers.size() < 2) {
                    throw new RuntimeException("Error expression");
                }
                //调用每个枚举类型重写过的calc(int a,int b)方法
                res = Symbol.valueOf(c).calc(numbers.pop(), numbers.pop());
                numbers.push(res);
            }
        }
        //除了结果还有剩余的数字
        if (numbers.size() > 1) {
            throw new RuntimeException("Error expression");
        }
        return res;
    }

    /**
     * 将中缀表达式翻译为后缀表达式
     *
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    public String translate(String expression) {
        Stack<Symbol> symobls = new Stack<>();
        StringBuilder res = new StringBuilder();
        char[] chars = expression.toCharArray();
        for (char c : chars) {
            //非法字符
            if (!isRight(c)) {
                throw new RuntimeException("UnSupport Symbol " + c);
            }
            //是数字直接压入栈内
            if (isNumber(c)) {
                res.append(c);
                continue;
            }
            //调用自己实现的ValueOf方法，将字符转换为枚举类型
            Symbol symbol = Symbol.valueOf(c);

            switch (symbol) {
                //左括号直接入栈
                case LEFT:
                    symobls.push(symbol);
                    break;
                case RIGHT:
                    Symbol cur = null;
                    //一直出栈至到出栈元素左括号为止
                    while (!symobls.empty() && (cur = symobls.pop()) != Symbol.LEFT) {
                        res.append(cur.getSymbol());
                    }
                    //如果没有元素出栈或者最后一个元素出栈也不是‘(’
                    if (cur == null || cur != Symbol.LEFT) {
                        throw new RuntimeException("UnMatch Right");
                    }
                    break;
                default:
                    //一直出栈直到栈顶元素小于当前元素或者为'('停止出栈
                    while (!symobls.empty() && symobls.peek() != Symbol.LEFT
                            && symobls.peek().compareToOter(symbol) >= 0) {
                        res.append(symobls.pop().getSymbol());
                    }
                    //将当前符号入栈
                    symobls.push(symbol);
            }
        }
        //将剩余的符号出栈
        while (!symobls.empty()) {
            //如果栈底还有左括号说明没有遇到与之对应的右括号
            if (symobls.peek() == Symbol.LEFT) {
                throw new RuntimeException("UnMatch Left");
            }
            res.append(symobls.pop().getSymbol());
        }
        return res.toString();
    }

    /**
     * 返回传入字符是数字吗
     *
     * @param value 要测试的字符
     * @return 如果该字符为数字返回true，否则false
     */
    private boolean isNumber(char value) {
        return value <= '9' && value >= '0';
    }

    /**
     * 返回指定字符是否为合法输入
     *
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
        //如果是数字返回ture，如果不是操作符也不是操作数返回false
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
        /**
         * 注意为b/a,比如1/2，转为后缀表达式12/，此时应该是
         * 栈顶的为除数。传入的a为栈顶
         */
        @Override
        public int calc(int a, int b) {
            return b / a;
        }
    },
    /**
     * 左括号
     */
    LEFT(3, '(') {
        @Override
        /**
         * 括号不参与计算
         */
        public int calc(int a, int b) {
            throw new RuntimeException("UnSupport operation");
        }
    },
    /**
     * 右括号
     */
    RIGHT(3, ')') {
        @Override
        /**
         * 括号不参与计算
         */
        public int calc(int a, int b) {
            throw new RuntimeException("UnSupport operation");
        }
    };

    /**
     * 枚举对应的符号
     */
    private char symbol;

    /**
     * 符号的优先级
     */
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

    /**
     * 将字符转换为枚举，注意枚举类型的构造方法是默认私有的，此时返回的对象都是同一个对象，也就是说
     * Symbol.valueOf('+') == Sysmbol.valueOf('+') 为true，他两为同一个对象
     */
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

    /**
     * 比较优先级
     */
    public int compareToOter(Symbol symbol) {
        return this.level - symbol.getLevel();
    }

    //每一个子类必须重写
    public abstract int calc(int a, int b);
}
