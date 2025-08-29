package com.example.base.algorithm;

/**
 * 倒排数组
 */
public class demo1 {

    //2个数组实现数组倒排
    public static void reverse(int[] a, int n, int[] b) {
        for (int i = 0; i < n; i++){
            b[i] = a[n - i - 1];
        }
    }

    //在本数组上实现倒排
    public static void nxReverse(int[] a, int n){
        int m = n/2;
        for (int i = 0; i < m; i++){
            int temp = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = new int[a.length];

        reverse(a, a.length, b);
        for (int i = 0; i < b.length; i++){
            System.out.println(b[i] + " ");
        }

        nxReverse(a, a.length);
        for (int i = 0; i < a.length; i++){
            System.out.println(a[i] + " ");
        }
    }
}
