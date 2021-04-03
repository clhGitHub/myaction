package algorithm;

import java.util.Scanner;

/**
 * Create by longhchen on  2021-03-04 22:25
 *
 * 例如: "","()","()()","((()))"都是合法的括号序列 对于一个合法的括号序列我们又有以下定义它的深度:
 *
 * 空串""的深度是0
 * 如果字符串"X"的深度是x,字符串"Y"的深度是y,那么字符串"XY"的深度为max(x,y)
 * 如果"X"的深度是x,那么字符串"(X)"的深度是x+1
 *
 * 思路:由于输入的是合法的括号序列,运用栈的思想，每次进入一个左括号,一个暂存量一定是+1的，每当进入一个右括号，就得和之前的左括号进行抵消，于是这个暂存量就-1了，最大深度通过max函数求得暂存量和当前深度的最大值即可。
 * 通俗的来说就是遍历字符串 记录最大’连续’左括号数量，遇到右括号要相应消去一个左括号数量 ，由于题目输入是合法的 所以最大’连续’左括号数量就是这个序列的深度。
 *
 */
public class BracketsDepth {
    public static void main(String[] args) {
        String str = new Scanner(System.in).nextLine();
        int cnt = 0 ;
        int max = 0 ;
        for (int i = 0; i < str.length() - 1; ++i) {
            if(str.charAt(i) == '('){
                cnt++;
            }else{
                cnt--;
            }
            max = Math.max(max,cnt);
        }
        System.out.println(max);

    }
}
