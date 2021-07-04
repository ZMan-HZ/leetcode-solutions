package com.zzm.solutions.leetcode.hard;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Stack;

/**
 * <b> 接雨水 </b><p>题目：</p>
 * <blockquote>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。<p>
 * 示例 2：<p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9<p>
 * 提示：<p>
 * n == height.length<p>
 * 0 <= n <= 3 * 10^4<p>
 * 0 <= height[i] <= 10^5<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/4 星期日
 */
public class TrappingRainWater {

    /**
     * 思路：
     * 双指针法。大体思想就是只有低洼的地方才可以存雨水。
     * 1。从左边遍历height，记录经过的height，当出现比左边记录的height小的height时，此时可形成低洼，能接雨水的就是以左边的height为基准
     * 若出现的height使得左边的高度大于右边出现的height时
     * 2。从右边遍历height，记录经过的height，当出现比右边记录height小于右边时，此时可形成低洼，就以右边height为基准计算
     * 3。当记录的左边的最大height小于当前height时，更新记录的左边最大height，否则，则加入计算，接雨水量 = 左边最大height - 当前height
     * 4。当记录的右边的最大height小于右边当前height时，更新记录的右边最大height，否则，加入计算 接雨水量 = 右边最大height - 当前height
     *
     * @param heights 高度数组
     * @return 最大值
     */
    public static int maxTrapWater(int[] heights) {
        int max = 0;
        if (ArrayUtils.isEmpty(heights)) {
            return max;
        }
        int length = heights.length;

        int left = 0;
        int leftMaxHeight = 0;

        int right = length - 1;
        int rightMaxHeight = 0;

        while (left < right) {
            int leftHeight = heights[left];
            int rightHeight = heights[right];
            if (leftHeight < rightHeight) {
                //以左边为基准
                if (leftMaxHeight < leftHeight) {
                    leftMaxHeight = leftHeight;
                } else {
                    //只计算纵向上的雨水，即出现时，当前低洼的雨水
                    max += leftMaxHeight - leftHeight;
                }
                left++;
            } else {
                //右边为基准
                if (rightMaxHeight < rightHeight) {
                    rightMaxHeight = rightHeight;
                } else {
                    max += rightMaxHeight - rightHeight;
                }
                right--;
            }
        }

        return max;
    }

    /**
     * 思路：
     * 单调递减栈，基本思想，就是记录低洼
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 height 中的元素递减。
     * 当出现height高度大于前一个时，即为低洼，则出栈坐标，用对应的height参与计算
     *
     * @param heights
     * @return
     */
    public static int maxWater(int[] heights) {
        int max = 0;
        if (ArrayUtils.isEmpty(heights)) {
            return max;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int height : heights) {
            //栈顶元素，用于获取栈顶的height
            //此时出现了低洼,当前元素的height，大于前一个元素的height(栈顶元素的对应高度)
            while (!stack.isEmpty() && height > heights[stack.peek()]) {
                //弹出栈顶，即当前height的前一个height的低洼地方
                int top = stack.pop();
                if (stack.isEmpty()) {
                    //栈已空，则说明前面没有大的height或者没有height，不需要计算
                    break;
                }
                //低洼的宽度, 当前元素的坐标减去前一个元素的坐标再减1
                //由于前面出了一个栈，现在的栈顶是前一个元素的前一个元素
                int width = index - stack.peek() - 1;
                //高度就是当前元素的高度，前一个元素的前一个元素差值中的较小的值
                int high = Math.min(heights[stack.peek()] - heights[top], height);
                //则面积就是雨水的量
                max += width * high;
            }
            stack.push(index++);
        }

        return max;
    }

    /**
     * 动态规划
     * <p>
     * 从左边开始遍历，保存最大的height值：当前height与 max left Height比较取大者
     * 从右边开始遍历，保存最大的height值：当前height与 max right height比较取大者（注意这个数组要从最后一个元素开始倒着放）
     * <p>
     * 雨水  则累加 = min（max left Height，max right Height）- 当前height
     *
     * @param heights
     * @return
     */
    public static int maxRainWater(int[] heights) {
        int max = 0;
        if (ArrayUtils.isEmpty(heights)) {
            return max;
        }
        int length = heights.length;
        int[] leftMaxHeight = new int[length];
        leftMaxHeight[0] = heights[0];

        int[] rightMaxHeight = new int[length];
        rightMaxHeight[length - 1] = heights[length - 1];

        for (int index = 1; index < length; index++) {
            leftMaxHeight[index] = Math.max(heights[index], leftMaxHeight[index - 1]);
        }
        for (int index = length - 2; index >= 0; index--) {
            rightMaxHeight[index] = Math.max(heights[index], rightMaxHeight[index + 1]);
        }
        for (int index = 0; index < length; index++) {
            max += Math.min(leftMaxHeight[index], rightMaxHeight[index]) - heights[index];
        }

        return max;
    }


    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int water = maxTrapWater(heights);
        System.out.println(water);

        water = maxWater(heights);
        System.out.println(water);

        water = maxRainWater(heights);
        System.out.println(water);


    }
}
