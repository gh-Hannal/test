package com.example.apple.calculator;

import java.text.NumberFormat;
import java.util.Stack;

/**
 * Created by apple on 2018/4/16.
 */

public class Calculator {
    private Stack<Character> optr = new Stack<Character>();
    private Stack<Double> opnd = new Stack<Double>() ;

    public Calculator(){
        optr.clear();
        opnd.clear();
        optr.push('#');
    }
    public String run(String str){
        char  op, theta;
        Boolean limit_precision = false;
        double val, a, b, num;
        int len = str.length(),state = 0;
        op = '#';
        int i =  0;
        Success sc;
        while(str.charAt(i)!='#' || op !='#'){
            val = 0;
            if(isdigit(str.charAt(i))){//是否是数字
                if(str.charAt(i) != (char)960){
                    do{
                        val = val*10+str.charAt(i)-48;
                        i++;
                    }while(i < len-1 && isdigit(str.charAt(i)));

                }else{
                    val = Math.PI;
                    i++;
                    limit_precision = true; //限制精度
                }
                opnd.push(val);
            }else {//不是数字
                if (str.charAt(i) == '.' && isdigit(str.charAt(i + 1))) {//解决小数点
                    num = opnd.peek();//获取数字栈顶
                    i++;
                    String p ="0.";
                    while (isdigit(str.charAt(i))) {
                        p = p + Character.toString(str.charAt(i));
                        i++;
                    }
                    opnd.pop();
                    opnd.push(num +Double.parseDouble(p));
                }
                if (lp(op) < rp(str.charAt(i))) {//当前字符优先级 大于 前一个字符优先级
                    if(str.charAt(i) == 's'||str.charAt(i) == 't'||str.charAt(i) == 'c'){
                        optr.push(str.charAt(i));
                        i += 3;
                    }else {
                        optr.push(str.charAt(i));
                        i++;
                    }
                } else if (lp(op) == rp(str.charAt(i))) {//优先级相等
                    if (optr.peek() == '(')
                        optr.pop();
                    i++;

                } else {//当前字符优先级 小于 前一个字符优先级
                    theta = optr.peek();
                    optr.pop();
                    b = opnd.peek();
                    opnd.pop();
                    if(theta != 's' && theta != 't'  && theta != 'c' ){
                        a = opnd.peek();
                        opnd.pop();
                    } else{
                        a = 0;
                    }
                    sc = operate(theta,a,b);
                    state = sc.getState();
                    val = sc.getValue();
                    if(!limit_precision)//判断是否限制精度
                        limit_precision = sc.getLimit_precision();
                    switch (state){
                        case 0:
                            opnd.push(val);
                            break;
                        case 1:
                            return "除零错误";
                        case 2:
                            return "tan无定义";
                        default:
                            break;
                    }
                }
            }
            op = optr.peek();
            if(i >= len) break;
        }

        num = opnd.peek();

        NumberFormat nFormat = NumberFormat.getNumberInstance();
        nFormat.setMaximumFractionDigits(8);//限制精度，小数点后
        if(limit_precision){
            return nFormat.format(num);
        }else if(Math.abs(num - (int)num) == 0)//如果是整数
            return Integer.toString((int)num);
        else
            return Double.toString(num);
    }

    private int lp(char op){
        int result = 0;//优先级
        if (op == '+')    result = 3;
        else if (op == '-')    result = 3;
        else if (op == '*')  result = 5;
        else if (op == '÷')  result = 5;
        else if (op == '(') result = 1;
        else if (op == ')')  result = 7;
        else if (op == '#')  result = 0;
        else if (op == 's')  result = 5;
        else if (op == 't')  result = 5;
        else if (op == 'c')  result = 5;
        return result;
    }
    private int rp(char op){
        int result = 0;
        if (op == '+')    result = 2;
        else if (op == '-')   result = 2;
        else if (op == '*') result = 4;
        else if (op == '÷') result = 4;
        else if (op == '(') result = 7;
        else if (op == ')') result = 1;
        else if (op == '#') result = 0;
        else if (op == 's')  result = 6;
        else if (op == 't')  result = 6;
        else if (op == 'c')  result = 6;
        return result;
    }
    private Success operate(char ch, double num1,double num2){
        //实现两数退栈运算再进栈的操作
        Boolean limit_precision= false;
        double n1 = num1,n2 = num2 ,result = 0;
        if(num1 == Math.PI || num2 == Math.PI ) limit_precision = true;

        if(ch == '+'){
            result = n1 + n2;
        }else if(ch == '-'){
            result = n1 - n2;
        }else if(ch == '*'){
            result = n1 * n2;
        }else if(ch == '÷'){
            if(n2 == 0) return new Success(1, result,limit_precision);
            result = n1 / n2;
            limit_precision = true;
        }else if(ch == 's'){//sin
            limit_precision = true;
            result = Math.sin(n2);
        }else if(ch == 't'){//tan
            if(n2 % (Math.PI/2)== 0){
                return new Success(2,result,limit_precision);
            }
            limit_precision = true;
            result = Math.tan(n2);
        }else if(ch == 'c') {
            limit_precision = true;
            result = Math.cos(n2);//sin cos tan 退出一个数
        }
        return new Success(0,result,limit_precision);
    }
    private Boolean isdigit(char c){
        if(( c>='0' && c<='9')|| c == (char)960) {
            return true;
        }
        return false;
    }
}
