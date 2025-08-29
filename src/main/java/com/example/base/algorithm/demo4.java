package com.example.base.algorithm;

/**
 * 二分法查找数组中目标数据
 */
public class demo4 {

    public static void main(String[] args) {
        int[] sortedArr = {1, 3, 5, 7, 9};
        int target = 7;
        int index = binarySearch(sortedArr, target);
        System.out.println(index);
    }

    private static int binarySearch(int[] arr, int target) {
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                max = mid - 1;
            }else if(arr[mid] < target){
                min = mid +1;
            }
        }
        return -1;
    }
}
