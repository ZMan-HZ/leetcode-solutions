package com.zzm.solutions.leetcode.medium;

import java.util.List;

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
     * 思路：
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {


        for (String word : wordDict) {
            //先找开头就可以组成的单词
            if (s.startsWith(word)) {
                //开头就包含的单词，移除掉后，应该还可以继续拆分

            }


        }

        return false;
    }

    public static void main(String[] args) {

    }
}
