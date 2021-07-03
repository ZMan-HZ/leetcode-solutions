package com.zzm.solutions.leetcode.medium;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b> 组合总和 </b><p>题目：</p>
 * <blockquote>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。<p>
 * </blockquote><p>
 * 示例 1：<p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]<p>
 * 示例 2：<p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]<p>
 * 提示：<p>
 * 所有数字（包括 target）都是正整数。<p>
 * 解集不能包含重复的组合。<p>
 * 1 <= nums.length <= 5000<p>
 * -10^4 <= nums[i] <= 10^4<p>
 * nums 中的每个值都 独一无二<p>
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转<p>
 * -10^4 <= target <= 10^4<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/3 星期六
 */
public class WantedSumOfCombination {

    /**
     * 树形结构
     * 回溯递归：
     * 如target = 7的组合，相当于，7 减去元素中的值的组合。
     * 组合target=0时的路径
     * 组合成树，然后再剪枝
     * 输入: candidates = [2, 3, 6, 7]，target = 7。
     * 1。以 target = 7 为 根结点 ，创建一个分支的时 做减法 ；
     * 2。每一个箭头表示：从父亲结点的数值减去边上的数值，得到孩子结点的数值。边的值就是题目中给出的 candidate 数组的每个元素的值；
     * 3。减到 0 或者负数的时候停止，即：结点 0 和负数结点成为叶子结点；
     * 4。所有从根结点到结点 0 的路径（只能从上往下，没有回路）就是题目要找的一个结果。
     * 这棵树有 4 个叶子结点的值 0，对应的路径列表是 [[2, 2, 3], [2, 3, 2], [3, 2, 2], [7]]
     *
     * @param candidates 候选数组
     * @param target     目标值
     * @return 组合
     */
    public static List<List<Integer>> combinationSumOf(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();

        if (ArrayUtils.isEmpty(candidates)) {
            return results;
        }
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new LinkedList<>();
        dfs(candidates, 0, target, path, results);

        return results;
    }

    private static void dfs(int[] candidates, int begin, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            dfs(candidates, i, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    /**
     * 动态规划
     * 比较占用内存
     *
     * @param target     目标值
     * @param candidates 候选
     * @return 组合
     */
    public static List<List<Integer>> combinations(int target, int[] candidates) {
        List<List<Integer>> results = new ArrayList<>();
        if (ArrayUtils.isEmpty(candidates)) {
            return results;
        }
        Map<Integer, Set<List<Integer>>> map = new HashMap<>(0);
        //对candidates数组进行排序
        Arrays.sort(candidates);
        int len = candidates.length;
        for (int count = 1; count <= target; count++) {
            //初始化map
            map.put(count, new HashSet<>());
            //对candidates数组进行循环
            for (int index = 0; index < len && candidates[index] <= target; index++) {
                if (count == candidates[index]) {
                    //相等即为相减为0的情况，直接加入set集合即可
                    List<Integer> temp = new ArrayList<>();
                    temp.add(count);
                    map.get(count).add(temp);
                } else if (count > candidates[index]) {
                    //count-candidates[index]是map的key
                    int key = count - candidates[index];
                    //使用迭代器对对应key的set集合进行遍历
                    //如果candidates数组不包含这个key值，对应的set集合会为空，故这里不需要做单独判断
                    for (List<Integer> list : map.get(key)) {
                        List<Integer> tempList = new ArrayList<>(list);
                        tempList.add(candidates[index]);
                        //排序是为了通过set集合去重
                        Collections.sort(tempList);
                        map.get(count).add(tempList);
                    }
                }
            }
        }
        results.addAll(map.get(target));
        return results;
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combination = combinationSumOf(candidates, target);
        System.out.println(combination);
        List<List<Integer>> combinations = combinations(target, candidates);
        System.out.println(combinations);

    }
}
