package com.zzm.solutions.leetcode.easy;

import java.util.Objects;

/**
 * <b>买卖股票的最佳时机,获取最大利益</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。<p>
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。<p>
 * 提示：<p>
 * 1 <= prices.length <= 105<p>
 * 0 <= prices[i] <= 104<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/24 星期六
 */
public class StockBuySellBestProfit {

    /**
     * 思路：动态规划
     * 进阶：可以不开辟新空间，只维护一个变量即可,如下一个解法
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (Objects.isNull(prices) || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int[] dp = new int[length];
        //第一天的利润 0；
        dp[0] = 0;
        //最便宜的价格
        int cheapest = Integer.MAX_VALUE;
        for (int index = 1; index < length; index++) {
            //最便宜的价格
            cheapest = Math.min(cheapest, prices[index - 1]);
            //第 index 天买入时的利润最大值
            dp[index] = Math.max(dp[index - 1], prices[index] - cheapest);
        }

        return dp[length - 1];
    }

    /**
     * 在第一印象选了动态规划后的一种优化
     *
     * @param prices
     * @return
     */
    public static int max(int[] prices) {
        if (Objects.isNull(prices) || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        //最便宜的价格
        int cheapest = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int index = 0; index < length; index++) {
            //最便宜的价格
            cheapest = Math.min(cheapest, prices[index]);
            maxProfit = Math.max(prices[index] - cheapest, maxProfit);
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int profit = maxProfit(prices);
        System.out.println("profit = " + profit);

        int max = max(prices);
        System.out.println("max = " + max);

    }
}
