package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <b> 合并区间 </b><p>题目：</p>
 * <blockquote>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].<p>
 * 示例 2：<p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。<p>
 * 提示：<p>
 * 1 <= intervals.length <= 10^4<p>
 * intervals[i].length == 2<p>
 * 0 <= starti <= endi <= 10^4<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/4 星期日
 */
public class MergeIntervals {

    /**
     * 思路：
     * 先排序，然后使用队列
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (ArrayUtils.isEmpty(intervals)) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Deque<int[]> queue = new LinkedList<>();
        //第一个区间入队
        queue.add(intervals[0]);
        for (int index = 1; index < intervals.length; index++) {
            int left = intervals[index][0];
            int right = intervals[index][1];
            //说明前面区间的右边界包含当前区间的左边界，可以合并
            if (queue.peekLast()[1] >= left) {
                //取交叉区间的较大的右边界
                right = Math.max(right, queue.peekLast()[1]);
                int[] last = queue.getLast();
                last[1] = right;
                //用的新的区级替换
                queue.removeLast();
                queue.addLast(last);
            } else {
                queue.addLast(intervals[index]);
            }
        }

        return queue.toArray(new int[queue.size()][]);
    }

    /**
     * 这个是先写的，使用了两个集合
     *
     * @param intervals
     * @return
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if (ArrayUtils.isEmpty(intervals)) {
            return intervals;
        }
        List<int[]> results = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Deque<Integer> queue = new LinkedList<>();
        //第一个区间入队
        queue.add(intervals[0][0]);
        queue.add(intervals[0][1]);
        for (int index = 1; index < intervals.length; index++) {
            int left = intervals[index][0];
            int right = intervals[index][1];
            //说明前面区间的右边界包含当前区间的左边界，可以合并
            if (queue.peekLast() >= left) {
                right = Math.max(right, queue.peekLast());
                queue.removeLast();
                queue.add(right);
            } else {
                queue.addLast(left);
                queue.addLast(right);
            }
        }
        //queue中两个一组，就是一个区间
        while (!queue.isEmpty()) {
            int[] ans = new int[2];
            ans[0] = queue.pop();
            ans[1] = queue.pop();
            results.add(ans);
        }
        return results.toArray(new int[results.size()][]);
    }


    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {8, 10}, {15, 18}, {2, 6}};
        int[][] merge = merge(intervals);
        System.out.println(ArrayUtils.toString(merge));

        merge = mergeIntervals(intervals);
        System.out.println(ArrayUtils.toString(merge));

    }
}
