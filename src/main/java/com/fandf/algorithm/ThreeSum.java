package com.fandf.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 三数之和
 *
 * @author fandongfeng
 */
public class ThreeSum {

    /**
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * <p>
     * 你返回所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1,1]
     * 输出：[]
     * 解释：唯一可能的三元组和不为 0 。
     * 示例 3：
     * <p>
     * 输入：nums = [0,0,0]
     * 输出：[[0,0,0]]
     * 解释：唯一可能的三元组和为 0 。
     * <p>
     * 提示：
     * <p>
     * 3 <= nums.length <= 3000
     * -105 <= nums[i] <= 105
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        //输出 [[-1,-1,2],[-1,0,1]]
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        //暴力法
        List<List<Integer>> result1 = threeSum1(nums);
        System.out.println(result1);


    }

    /**
     * 遍历三次
     *
     * @param nums 数组
     * @return 返回所有和为 0 且不重复的三元组。
     */
    private static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        //三重for循环
        //三个且不重复的元素，所以i的最大值为 length - 2
        for (int i = 0; i < length - 2; i++) {
            for (int j = i + 1; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    //判断三数相加之和等于0
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        //结果去重
        // result 结果 [[-1, 0, 1], [-1, 2, -1], [0, 1, -1]]
        //[-1, 0, 1] 和 [0, 1, -1] 是重复的，需要去重
        return distinctList(result);
    }

    /**
     * list 去重
     */
    private static List<List<Integer>> distinctList(List<List<Integer>> result) {
        List<List<Integer>> distinctList = new ArrayList<>();
        for (List<Integer> list : result) {
            //因为数组长度都为3，所以不用比较长度。只需排个序，然后使用contains方法判断即可
            //contains会遍历每个元素比较
            //list.sort(null) 如果指定的比较器为空，则此列表中的所有元素都必须实现 Comparable 接口，并且应使用元素的自然顺序。
            list.sort(null);
            System.out.println("list排序后为：" + list);
            if (!distinctList.contains(list)) {
                distinctList.add(list);
            }
        }
        return distinctList;
    }

}
