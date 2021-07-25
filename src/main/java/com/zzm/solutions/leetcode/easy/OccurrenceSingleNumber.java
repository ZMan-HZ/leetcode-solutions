package com.zzm.solutions.leetcode.easy;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <b> 只出现一次的数字 </b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: [2,2,1]
 * 输出: 1<p>
 * 示例 2：
 * 输入: [4,1,2,1,2]
 * 输出: 4<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/25 星期日
 */
public class OccurrenceSingleNumber {


    /**
     * 思路：通过坐标来判断（不满足线性复杂度）
     * 1。 只有一个元素是出现过一次
     * 2。其余元素均出现两次
     * 3。当一个元素的从前面数的坐标不等于从后面数的坐标时，则为出现两次的
     * 4。当元素从数和从后数都是一样的坐标时，则是那个唯一的出现一次的元素
     *
     * @param nums
     * @return
     */
    public static int single(int[] nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return Integer.MIN_VALUE;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        for (int num : nums) {
            int index = indexOf(nums, num);
            int lastIndex = lastIndexOf(nums, num);
            if (index == lastIndex) {
                return num;
            }
        }
        return Integer.MIN_VALUE;
    }

    private static int indexOf(int[] nums, int value) {
        if (Objects.isNull(nums)) {
            return -1;
        }
        for (int index = 0; index < nums.length; index++) {
            if (value == nums[index]) {
                return index;
            }
        }
        return -1;
    }

    private static int lastIndexOf(int[] nums, int value) {
        if (Objects.isNull(nums)) {
            return -1;
        }
        int length = nums.length;
        for (int index = length - 1; index >= 0; index--) {
            if (value == nums[index]) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 哈希表，复杂度不满足，但比上面两次过滤要好
     *
     * @param nums
     * @return
     */
    public static int singleNum(int[] nums) {
        Map<Integer, Integer> counterMap = new HashMap<>();
        for (int num : nums) {
            Integer count = counterMap.getOrDefault(num, 0);
            counterMap.put(num, count + 1);
        }
        for (Integer key : counterMap.keySet()) {
            int count = counterMap.get(key);
            if(count==1){
                return key;
            }
        }
        return Integer.MIN_VALUE;
    }

    /**
     * 使用位运算（满足题意）
     * 1。 任何数与自身作异或运算，结构都是0
     * 2。 任何数与0作异或运算，结果都是自身
     * 3。 异或运算满足交换律，即 a ^ b ^ a = a ^ a ^ b = b;
     *
     * @param nums
     * @return
     */
    public static int singleton(int[] nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return Integer.MIN_VALUE;
        }
        int single = 0;
        for (int num : nums) {
            //与 0 异或还是num，
            //下一个如果是相同的num，则是结果是0
            //下一个如果是不相同的num，则最终运算交换律时，只要出现相同的就会是 与 0 异或
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1};
        int[] nums2 = {4, 1, 2, 1, 2};
        int single = single(nums1);
        System.out.println("single = " + single);
        single = single(nums2);
        System.out.println("single = " + single);

        single = singleton(nums1);
        System.out.println("singleton = " + single);
        single = singleton(nums2);
        System.out.println("singleton = " + single);

        single = singleNum(nums1);
        System.out.println("singleNum = " + single);
        single = singleNum(nums2);
        System.out.println("singleNum = " + single);

    }
}


