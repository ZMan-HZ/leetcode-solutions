package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

/**
 * <b> 跳跃游戏 </b><p>题目：</p>
 * <blockquote>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。<p>
 * 示例 2：<p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。<p>
 * 提示：<p>
 * 1 <= nums.length <= 3 * 10^4<p>
 * 0 <= nums[i] <= 10^5<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/4 星期日
 */
public class JumpGames {

    /**
     * 贪婪算法
     * 挨个跳跳看
     * 对于数组中的任意一个位置 y，我们如何判断它是否可以到达？
     * 根据题目的描述，只要存在一个位置 x，它本身可以到达，并且它跳跃的最大长度为x+nums[x]，
     * 这个值大于等于 y，即x+nums[x]≥y，那么位置 y 也可以到达。
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (ArrayUtils.isEmpty(nums)) {
            return false;
        }
        //最远可以到达的位置
        int most = 0;
        int length = nums.length;
        for (int index = 0; index < length; index++) {
            //当前位置，在最远能跳到的位置前，其实如果当前大于能跳到的最远位置了也就失败了
            if (index <= most) {
                most = Math.max(most, index + nums[index]);
                if (most >= length - 1) {
                    return true;
                }
            }
        }

        return false;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        boolean canJump = canJump(nums);
        System.out.println(canJump);

        int[] nums2 = {3, 2, 1, 0, 4};
        canJump = canJump(nums2);
        System.out.println(canJump);
    }
}
