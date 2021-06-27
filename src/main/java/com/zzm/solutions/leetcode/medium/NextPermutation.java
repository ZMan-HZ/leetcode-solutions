package com.zzm.solutions.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

/**
 * <b>下一个排列</b>
 * <p>题目：</p>
 * <blockquote>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。<p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。<p>
 * 必须 原地 修改，只允许使用额外常数空间。<p>
 * 以数字序列 [1,2,3]为例，其排列按照字典序依次为：
 * [1,2,3]
 * [1,3,2]
 * [2,1,3]
 * [2,3,1]
 * [3,1,2]
 * [3,2,1]
 * </blockquote>
 * “下一个排列”的定义是：给定数字序列的字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。<p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]<p>
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * </p><p>
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * </p><p>
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * </p><p>
 * 提示：</p><p>
 * 1 <= nums.length <= 100<p>
 * 0 <= nums[i] <= 100
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/26 星期六
 */
public class NextPermutation {

    private NextPermutation() {
    }

    /**
     * 算法推导:<p>
     * 如何得到这样的排列顺序？可以这样来分析：<p>
     * 希望下一个数比当前数大，这样才满足“下一个排列”的定义。<p>
     * 因此只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。<p>
     * 比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。<p>
     * 我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。<p>
     * 为了满足这个要求，需要：<p>
     * 在尽可能靠右的低位进行交换，需要从后向前查找;<p>
     * 将一个 尽可能小的「大数」 与前面的「小数」交换。<p>
     * 比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换.<p>
     * 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。<p>
     * 以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；<p>
     * 然后需要将 5 之后的数重置为升序，得到 123546。<p>
     * 显然 123546 比 123564 更小，123546 就是 123465 的下一个排列<p>
     * 以上就是求“下一个排列”的分析过程。<p>
     * 算法过程:<p>
     * 标准的“下一个排列”算法可以描述为：<p>
     * <pre>
     *   step1: 从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。
     *          此时 [j,end) 必然是降序;
     *   step2: 在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。
     *          A[i]、A[k] 分别就是上文所说的「小数」、「大数」
     *   step3: 将 A[i] 与 A[k] 交换
     *   step4: 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序, 则找到了下一个排列。
     *   step5: 如果在步骤 1 找不到符合的相邻元素对，
     *          说明当前 [begin,end) 为一个降序顺序，则直接跳到 step4,逆置为升序
     * </pre>
     *
     * @param nums 数组
     */
    public static void permutation(int[] nums) {

        if (Objects.isNull(nums) || nums.length == 0) {
            return;
        }
        int end = nums.length - 1;

        //step1: 从后向前找 A[i] < A[j]的 i 和 j
        for (int start = end; start > 0; start--) {
            if (nums[start - 1] < nums[start]) {
                //step2: 找到 i 和 j，找 A[i] < A[k] 的k
                for (int index = end; index >= start; index--) {
                    if (nums[start - 1] < nums[index]) {
                        //step3: 找到 K，交换 A[i] 和 A[k]
                        int num = nums[start - 1];
                        nums[start - 1] = nums[index];
                        nums[index] = num;
                        break;
                    }
                }
                //step4: 升序排序 [j, end)
                Arrays.sort(nums, start, end + 1);
                return;
            }
        }
        //step5: 即已经是全降序排列，返回第一个排列就是全升序
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        permutation(nums);//1,3,2
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1, 2, 3, 4, 6, 5};
        permutation(nums2);//1,2,3,5,4,6
        System.out.println(Arrays.toString(nums2));
    }
}
