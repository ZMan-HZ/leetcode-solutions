package com.zzm.solutions.leetcode.medium;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <b>单词拆分</b>
 * <p>题目：</p>
 * <blockquote>
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。<p>
 * 你可以假设字典中没有重复的单词。
 * </blockquote>
 * <p>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。<p>
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词<p>
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false<p>
 *
 * @author Zhenzhen
 * @version v1.0.0
 * @since 2021/7/25 星期日
 */
public class WordBreak {


    /**
     * 思路：动态规划
     * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。
     * 从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置 i−1 的最后一个单词，
     * 看它是否出现在字典中以及除去这部分的字符串是否合法即可。
     * 公式化来说，我们需要枚举 s[0..i−1] 中的分割点 j ，看 s[0..j−1] 组成的字符串 s1 （默认j=0 时 s1为空串）
     * 和s[j..i−1] 组成的字符串 s2 是否都合法，如果两个字符串均合法，那么按照定义 s1 和 s2 拼接成的字符串也同样合法。
     * 由于计算到dp[i] 时我们已经计算出了 dp[0..i−1] 的值，
     * 因此字符串 s1 是否合法可以直接由 dp[j] 得知，剩下的我们只需要看 s2是否合法即可，
     * 因此我们可以得出如下转移方程：
     * dp[i]= dp[j] && check(s[j..i−1])
     * 其中 check(s[j..i−1]) 表示子串 s[j..i−1] 是否出现在字典中。
     * 对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，
     * 同时也可以做一些简单的剪枝，枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，
     * 那么就结束枚举，但是需要注意的是下面的代码给出的是不带剪枝的写法。
     * 对于边界条件，我们定义 dp[0]=true 表示空串且合法。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
       /* int maxw = 0;
        int minw = len + 1;
        Set<String> set = new HashSet();
        for (String str : wordDict) {
            set.add(str);
            maxw = Math.max(maxw, str.length());
            minw = Math.min(minw, str.length());
        }*/
       //其实stream操作更慢
        Set<String> dict = new HashSet(wordDict);
        int max = dict.stream().map(String::length).max(Integer::compareTo).orElse(len);
        int min = dict.stream().map(String::length).min(Integer::compareTo).orElse(0);

        for (int index = 1; index < len + 1; index++) {
            //dp[i]只需要往前探索到词典里最长的单词即可
            for (int kndex = index; kndex >= 0 && kndex >= index - max; kndex--) {
                //如果取的字符串小于最短的单词，不需要判断set是否有
                if (index - kndex < min) {
                    continue;
                }
                if (dp[kndex] && dict.contains(s.substring(kndex, index))) {
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> dict = Lists.newArrayList("leet", "code");
        boolean wordBreak = wordBreak(s, dict);
        System.out.println("wordBreak = " + wordBreak);
    }
}
