package com.zzm.solutions.leetcode.medium;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * <b> 最长连续序列 </b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。<p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9<p>
 * 提示：<p>
 * 树中节点数目在范围 [0, 100] 内<p>
 * -100 <= Node.val <= 100<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/25 星期日
 */
public class LongestConsecutiveSequence {


    /**
     * 时间复杂度O(n)，就是要一次遍历
     * 1。用哈希表存储每个端点值对应连续区间的长度
     * 2。若数已在哈希表中：跳过不做处理
     * 3。若是新数加入：
     * 3.1 取出其左右相邻数已有的连续区间长度 left 和 right
     * 3.2 计算当前数的区间长度为：cur_length = left + right + 1
     * 3.3 根据 cur_length 更新最大长度 max_length 的值
     * 3.4 更新区间两端点的长度值
     *
     * @param nums
     * @return
     */
    public int longestSequence(int[] nums) {
        int maxLength = 0;
        if (Objects.isNull(nums)) {
            return maxLength;
        }
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int num : nums) {
            //存在时不处理，num 第一次出现
            if (!map.containsKey(num)) {
                //前面一个数的连续个数
                int left = map.getOrDefault(num - 1, 0);
                //后面一个数的连续个数
                int right = map.getOrDefault(num + 1, 0);
                //当前数的连续个数
                int current = left + right + 1;
                maxLength = Math.max(maxLength, current);
                //对于key=num-1来说，它所对应的value只可能是区间[num - value , num-1]的长度！
                //即num-1只能是区间的右端点值！因为其他情况都会与num第一次出现冲突！
                //同理：对于key=num+1来说，它所对应的value只可能是区间[num+1， num+value]的长度！
                //即num+1只能是区间的左端点值！
                //接下来，就好理解了，因为num的出现这两个区间被打通了，
                //出现了一个更长的连续区间[num-hash[num-1], num+hash[num+1]]~长度为 cur_length = 1 + left + right
                //这一行如果省略，会有问题，即如果前面一个和后面一个的连续都不为0时，这个当前值的就不会更新
                map.put(num, current);
                //如果前面的个数是0，下面的put就是num
                map.put(num - left, current);
                //如果后面的个数是0，下面的put就是num
                map.put(num + right, current);
            }
        }


        return maxLength;
    }


    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        int sequence = new LongestConsecutiveSequence().longestSequence(nums);
        System.out.println("sequence = " + sequence);

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int longestSequence = new LongestConsecutiveSequence().longestSequence(nums2);
        System.out.println("longestSequence = " + longestSequence);

        int[] cases = {4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3};
        int i = new LongestConsecutiveSequence().longestSequence(cases);
        System.out.println("i = " + i);

    }
}
