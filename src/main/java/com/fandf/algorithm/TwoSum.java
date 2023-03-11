package com.fandf.algorithm;

import org.apache.commons.lang3.time.StopWatch;

import java.util.*;

/**
 * @author fandongfeng
 */
public class TwoSum {


    /**
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     * <p>
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     * <p>
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 26;
        //暴力法
        int[] result1 = twoSum1(nums, target);
        System.out.println(result1);

        //两遍map
        int[] result2 = twoSum2(nums, target);
        System.out.println(result2);

        //一遍map
        int[] result3 = twoSum3(nums, target);
        System.out.println(result3);
    }

    /**
     * 暴力破解
     *
     * @param nums   数组
     * @param target 两数之和目标值
     * @return 两数的数组下标
     */
    private static int[] twoSum1(int[] nums, int target) {
        //双层for循环遍历 时间复杂度 O(n的平方)
        for (int i = 0; i < nums.length; i++) {
            //数组中同一个元素在答案里不能重复出现
            for (int j = i + 1; j < nums.length; j++) {
                //两数之和为target
                if (nums[j] + nums[i] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * 遍历两次数组
     *
     * @param nums   数组
     * @param target 两数之和目标值
     * @return 两数的数组下标
     */
    private static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //key 为数组的值     value 存放数组下标
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            /**
             * 设 x = target - nums[i]  就等于  num[i] + x = target
             * 然后判断 x 这个值是否在 map中，即 map.containsKey(x)
             *
             * map.get(x) != i  防止同一下标计算两次，  如目标值是8，  nums[2] = 4 , 则会返回 4,4
             */
            int x = target - nums[i];
            if (map.containsKey(x) && map.get(x) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }

    /**
     * 遍历一次数组
     *
     * @param nums   数组
     * @param target 两数之和目标值
     * @return 两数的数组下标
     */
    private static int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            //如果map存在 x 则直接返回结果,否则将当前值和下标保存在 map中
            if (map.containsKey(x)) {
                return new int[]{map.get(x), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }


}
