package BabbarSheet.Arrays;

import java.util.*;

public class Subsets {

    // 1
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            go(list, nums, i, new ArrayList<>());
        }

        return list;
    }

    public void go(List<List<Integer>> list, int[] nums, int index, List<Integer> now) {
        if (index >= nums.length) {
            return;
        }

        now.add(nums[index]);
        list.add(now);

        for (int i = index; i < nums.length; i++) {
            go(list, nums, i + 1, new ArrayList<>(now));
        }
    }

    // 2
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        selected = new boolean[nums.length];

        List<List<Integer>> result = new ArrayList<>();

        for (int subsetLength = 0; subsetLength <= nums.length; ++subsetLength) {
            List<List<Integer>> subsetsOfLength = new ArrayList<>();
            getAllSubsetsOfLength(nums, 0, subsetLength, subsetsOfLength);
            result.addAll(subsetsOfLength);
        }

        return result;
    }

    private boolean[] selected;
    private List<Integer> curSubset = new ArrayList<>();

    private void getAllSubsetsOfLength(int[] nums, int startPos, int length, List<List<Integer>> subsetsOfLength) {
        if (length == 0) {
            subsetsOfLength.add(new ArrayList<Integer>(curSubset));
            return;
        }

        for (int i = startPos; i <= nums.length - length; ++i) {
            if (i > 0 && nums[i] == nums[i - 1] && !selected[i - 1]) {
                // Duplication found, skip
                continue;
            }
            selected[i] = true;
            curSubset.add(nums[i]);
            getAllSubsetsOfLength(nums, i + 1, length - 1, subsetsOfLength);
            selected[i] = false;
            curSubset.remove(curSubset.size() - 1);
        }
    }
}
