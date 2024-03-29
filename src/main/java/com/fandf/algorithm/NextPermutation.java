package com.fandf.algorithm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fandongfeng
 * @date 2023/3/13 06:57
 */
public class NextPermutation {


    /**
     * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
     * <p>
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     * <p>
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     * <p>
     * 必须 原地 修改，只允许使用额外常数空间。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     * <p>
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 示例 3：
     * <p>
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 100
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {

        int[] nums = new int[]{3, 1, 1};


        //暴力法，所有组合全部列出来,然后按升序排序，查找当前元素的下一个位置元素。
        Integer result1 = nextPermutation1(nums);
        String resultStr = result1.toString();
        int[] resultArray = new int[resultStr.length()];
        for (int i = 0; i < resultStr.length(); i++) {
            // 遍历str将每一位数字添加如intArray
            Character ch = resultStr.charAt(i);
            resultArray[i] = Integer.parseInt(ch.toString());
        }
        System.out.println(Arrays.toString(resultArray));

        //排序法
        int[] nums2 = new int[]{3, 1, 1};
        nextPermutation2(nums2);
        System.out.println(Arrays.toString(nums));

    }

    /**
     * 1. 先排除例外，如果数组是按照降序排列的，就没有升序的子序列了。 下一个排列就是将数组升序排列 比如{9,8,7}，下一个排列就是[7,8,9]
     * 2. 如果数组有一个升序的子序列，那么就一定可以找到它的下一个排列。 也就是说从右往左，找到第一对连续的数字 nums[i] 和 nums[i+1],满足nums[i+1] > nums[i]
     *  2.1比如 [1,5,7,4,2]   满足 nums[i+1] > nums[i] 则 i = 1, (num[2] = 7) > (num[1] = 5), 然后 从i+1到 数组结束，查找比5大，比7小的数字，
     *  2.2找到了6 , 那么就确定了前两个数字1,6 ， 后面的按正序排序就行了
     *  2.3如果没找到，则直接替换5和7的位置即可
     *
     * @param nums 数组
     * @return 下一个排列数
     */
    private static void nextPermutation2(int[] nums) {
        //从倒数第二个开始找
        int k = nums.length - 2;
        //2.找出第一对连续的数字
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }
        // 如果全部降序，以最小序列输出
        if (k < 0) {
            Arrays.sort(nums);
            return;
        }

        //2.1开始往后找
        int i = k + 2;
        while (i < nums.length && nums[i] > nums[k]) {
            i++;
        }
        // 交换nums[k]和找到的nums[i-1]
        int temp = nums[k];
        nums[k] = nums[i - 1];
        nums[i - 1] = temp;

        // k以后剩余的部分反转成升序
        int start = k + 1, end = nums.length - 1;
        while (start < end) {
            int reTemp = nums[start];
            nums[start] = nums[end];
            nums[end] = reTemp;
            start++;
            end--;
        }
    }



    /**
     * 下一个排列的int
     * 时间复杂度：O(n!)，可能的排列总计有 n! 个。
     *
     * @param nums 数组
     * @return int
     */
    private static Integer nextPermutation1(int[] nums) {
        //先将原数组转成数字
        int transfer = transfer(nums);
        System.out.println("当前数组转换成数字：" + transfer);
        //列出数组可以组合成数字的所有可能
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<Integer> track = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, track, used, res);
        System.out.println("所有的排列组合情况：" + res);
        //数组转数字并按升序排列
        List<Integer> all = res.stream().map(d -> transfer(d.stream().mapToInt(Integer::valueOf).toArray())).sorted().collect(Collectors.toList());
        System.out.println("排序后的数组：" + all);
        //判断原数字转成的数字的位置
        int index = all.indexOf(transfer);
        //最后一个位置要返回数组第一个元素
        if (index == all.size() - 1) {
            return all.get(0);
        }
        //返回原数组的下一个排列
        return all.get(index + 1);
    }

    /**
     * 查询数组所有排列组合情况
     *
     * @param res   结果
     * @param track 组合
     * @param used  是否使用
     * @param nums  数组
     */
    static void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 新添加的逻辑，固定相同的元素在排列中的相对位置
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            backtrack(nums, track, used, res);
            track.removeLast();
            used[i] = false;
        }
    }

    /**
     * 数组转数字
     *
     * @param arr 数组 example {1,3, 1}
     * @return 转换后的数字 131
     */
    public static Integer transfer(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            String s;
            int z = arr[i];
            s = Integer.toString(z);
            str = str.concat(s);
        }
        return Integer.valueOf(str);
    }

}
