package com.zzm.solutions.leetcode.medium;

import java.util.Objects;

/**
 * <b>动态规划：
 * 打家劫舍</b>最多的钱
 * <p>题目：</p><blockquote>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * </p>
 * <p>
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * </p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/6/19 星期六
 */
public class HouseRobber {

    /**
     * 所能获得的最多钱
     * <p>
     * 有两个选项：
     * </p>
     * <blockquote>
     * <p>
     * 1. 偷窃第 k 间房屋，那么就不能偷窃第 k-1 间房屋，偷窃总金额为前 k-2 间房屋的最高总金额与第 k 间房屋的金额之和。
     * </p>
     * <p>
     * 2. 不偷窃第k间房屋，偷窃总金额为前 k-1 间房屋的最高总金额。
     * </p>
     * </blockquote>
     * <p>
     * 临界条件：
     * </p>
     * <p>
     * 只有1间房屋时，最大即第1间房屋，
     * </p>
     * <p>
     * 只有2间房屋时，最大即为两间房屋中最大的数
     * </p>
     *
     * @param neighbors 每家所能获得的钱
     * @return 所能获取的最多钱
     */
    public static Integer getMaxMoneyFromNeighbors(int[] neighbors) {

        if (Objects.isNull(neighbors) || neighbors.length == 0) {
            return 0;
        }
        int length = neighbors.length;
        if (length == 1) {
            return neighbors[0];
        }

        int[] maxMoney = new int[length];
        maxMoney[0] = neighbors[0];
        maxMoney[1] = Math.max(neighbors[0], neighbors[1]);
        for (int index = 2; index < length; index++) {
            maxMoney[index] = Math.max(maxMoney[index - 2] + neighbors[index], maxMoney[index - 1]);
        }

        return maxMoney[length - 1];
    }

    public static void main(String[] args) {
        int[] moneys = {1, 5, 3, 2, 6};
        Integer money = getMaxMoneyFromNeighbors(moneys);
        String msg = String.format("Can steal money maximum is: %s", money);
        System.out.println(msg);

    }
}
