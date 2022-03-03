package com.jam.leetcode.editor.cn;

//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 6958 👎 0

import java.util.Objects;

class LongestSubstringWithoutRepeatingCharacters{
	public static void main(String[] args) {
		Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
		System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
	}
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < chars.length-1; i++) {
			builder.append(chars[i]==chars[i+1] ? "" : chars[i]);

		}
		if(builder.length()%2==0){
			StringBuilder b = new StringBuilder();
			b.append(builder.toString().substring(0, builder.length() / 2).equals(builder.toString().substring(builder.length() / 2, builder.length())) ?
					builder.toString().substring(0,builder.length()/2) : builder);
			builder=b;
		}
		if (builder.toString().isEmpty()){
			builder.append(chars[0]);
		}
		System.out.println(builder.toString());
		System.out.println(builder.toString().substring(0, builder.length() / 2));
		return builder.length();
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}