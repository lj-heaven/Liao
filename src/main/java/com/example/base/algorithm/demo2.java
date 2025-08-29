package com.example.base.algorithm;

/**
 * 递归
 */
public class demo2 {

    //汉诺塔问题
    public static void hanNuoTa(int n, String from, String to, String aux) {
        if (n == 1) {
            System.out.println("移动碟子1从" + from + "柱子移动到" + to + "柱子");
        } else {
            hanNuoTa(n - 1, from, aux, to);
            System.out.println("移动碟子" + n + "从" + from + "柱子移动到" + to + "柱子");
            hanNuoTa(n - 1, aux, to, from);
        }
    }

    //斐波那契数列
    public static long fib(int n) {
        if (n == 0 || n == 1) return n;
        else return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        hanNuoTa(4, "A", "C", "B");
        System.out.println(fib(3));;
    }
}
