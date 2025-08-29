package com.example.base.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取数组中和为目标值的数据，生成新的数组
 */
public class demo5 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        //双指针，对于有序的数组
        System.out.println(Arrays.toString(twoSum(nums,9)));
        //如果是无序的，可以通过哈希
        System.out.println(Arrays.toString(threeMethod(nums,9)));
        //无序的，可以用普通的方法处理
        System.out.println(Arrays.toString(getSum(nums,9)));
    }

    public static int[] getSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int s = target - nums[i];
            Boolean b = getNum(nums, s);
            if (b) {
                return new int[]{nums[i], s};
            }
        }
        return new int[]{-1, -1};
    }

    private static Boolean getNum(int[] nums, int s) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == s) {
                return true;
            }
        }
        return false;
    }

    public static int[] threeMethod(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsValue(complement)) {
                return new int[]{complement, nums[i]};
            }
            map.put(i, nums[i]);
        }
        return new int[]{-1, -1}; // 如果没有找到，根据题目描述这种情况不会出现
    }

    private static int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[] {-1,-1};
    }
}
