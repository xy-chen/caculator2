package com.example.caculator;

/**
 * Created by f on 2017/11/7.
 */

public class ca {
    private static Stack<Double> Operands;
    // 操作符栈
    private static Stack<Character> Operators;
    // 操作符集合
    private static final Set<Character> C_OperatorSet = new HashSet<Character>() {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        {
            add('+');
            add('-');
            add('*');
            add('/');
            add('^');
            add('(');
            add(')');
        }
    };
    //获取操作符优先级
    private static int getOperatorPriority(char ch) {
        if (ch == '+' || ch == '-')
            return 0;
        else if (ch == '*' || ch == '/')
            return 1;
        else if (ch == '^')
            return 3;
        else
            return -1;
    }

    //中缀转后缀
    private static String infixToSuffix(String expression) {
        //操作符栈
        Operators = new Stack<>();
        Operators.clear();
        StringBuilder sBuilder = new StringBuilder();

        //遍历每一个字符
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            //遇到空格直接跳过
            if (ch == ' ')
                continue;
            //若字符为操作符
            if (C_OperatorSet.contains(ch)) {
                if (Operators.empty()) {
                    if (ch == ')') {
                        //栈为空的时候遇到右括号 不操作直接返回（error
                        return sBuilder.toString();
                    }
                    //空栈遇到非）的操作符直接压入栈中
                    Operators.push(ch);
                    //若不是空栈，遇到（直接压入栈中
                } else if (ch == '(') {
                    char j;
                    Operators.push(ch);
                    //如果检测到左括号 左括号右边又有负号                用于解决后缀表达式的负号与减号的区别
                    if ( (j = expression.charAt(i + 1)) == '-' )
                    {
                        StringBuilder change = new StringBuilder(expression);
                        change.replace(i+1,i+2,"0-");
                        expression = change.toString();
                    }
                    //如果字符是），输出所有）之前的字符，=如果输出了所有的字符依旧没有碰到（则表达式错误
                } else if (ch == ')') {
                    char top;
                    while ((top = Operators.peek()) != '(') {
                        if (Operators.empty()) {
                            //表达式中没有（
                            return sBuilder.toString();
                        }
                        //将栈中的符号输出（到sbuilder中
                        sBuilder.append(top);
                        Operators.pop();
                    }
                    //如果直接碰到（直接弹出就好
                    Operators.pop();
                    //其他的字符，与栈顶字符的优先级进行比较
                } else {
                    char top = Operators.peek();
                    //若该字符优先级较低则输出栈中的字符直到碰到相同优先级或者较低的字符，或全部输出
                    if (getOperatorPriority(ch) <= getOperatorPriority(top)) {
                        while (!Operators.empty()
                                && getOperatorPriority(ch) <= getOperatorPriority(top = Operators.peek())) {
                            sBuilder.append(top);
                            Operators.pop();
                        }
                    }
                    //若字符优先级较高则直接压入栈中，或者将栈中的字符输出至出现较低优先级，将ch输入栈中
                    Operators.push(ch);
                }
            }
            //若输入的字符是数字或者 . 直接输出
            else {
                sBuilder.append("[" + ch);
                //当 数字后有小数点且小数点不是最后一位 或者字符为数字



                //一旦有一个数字，后面的数字都加到【】中？？
                while (i + 1 < expression.length()
                        && (((ch = expression.charAt(i + 1)) == '.') || (ch >= '0' && ch <= '9'))) {
                    sBuilder.append(ch);
                    ++i;
                }
                //【】之间都是小数点和数字
                sBuilder.append(']');
            }


        }
        //以上遍历了所有的字符，要么输出了，要么存在操作符栈中

        //输出所有的数字后，将栈中的所有操作符都输出
        while (!Operators.empty()) {
            sBuilder.append(Operators.peek());
            Operators.pop();
        }
        return sBuilder.toString();  //中缀转后缀以字符串输出
    }

    //进行后缀表达式的运算
    public static double evalExp(String expression) {

        //操作数，遇到数字放入栈中
        Operands = new Stack<>();
        Operands.clear();
        double ret = 0;
        //转为后缀
        String suffix = infixToSuffix(expression);

        //遍历表达式，将数字先转入栈中
        for (int i = 0; i < suffix.length(); i++) {
            if (suffix.charAt(i) == '[') {
                i++;
                int beginIndex = i, endIndex = i;
                while (']' != suffix.charAt(i)) {
                    i++;
                    endIndex++;
                }
                //将【】内的数字都压入操作数栈


                Operands.push(Double.valueOf(suffix.substring(beginIndex, endIndex)));
            } else {
                double left , right, res = 0;

                switch (suffix.charAt(i)) {
                    case '+':
                        right = Operands.peek();
                        Operands.pop();
                        left = Operands.peek();
                        Operands.pop();
                        res = left + right;
                        break;

                    case '-':
                        //若操作数的栈只有一个数就只取一个
                        if (Operands.size() == 1) {
                            right = Operands.peek();
                            res = 0 - right;
                            Operands.pop();
                            res = 0 - right;
                        }
                        else {
                            right = Operands.peek();
                            Operands.pop();
                            left = Operands.peek();
                            Operands.pop();
                            res = left - right;
                        }
                        break;

                    case '*':
                        right = Operands.peek();
                        Operands.pop();
                        left = Operands.peek();
                        Operands.pop();
                        res = left * right;
                        break;
                    case '/':
                        right = Operands.peek();
                        Operands.pop();
                        left = Operands.peek();
                        Operands.pop();
                        res = left / right;
                        break;
                    case '^':
                        right = Operands.peek();
                        Operands.pop();
                        left = Operands.peek();
                        Operands.pop();
                        res = Math.pow(left, right);
                        break;
                }



                Operands.push(res);
            }
        }
        ret = Operands.peek();
        Operands.pop();
        return ret;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner input = new Scanner(System.in);
        System.out.println("请输入一个含+-*/^()的表达式，请确认输入合法");
        String expression = input.nextLine();
        System.out.println(expression + " =" + evalExp(expression));
        input.close();
    }
}