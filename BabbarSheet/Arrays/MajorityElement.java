package BabbarSheet.Arrays;

import java.util.*;

public class MajorityElement {
    public List<Integer> majorityElement(int[] nums) {
        int element1 = -1, element2 = -1, count1 = 0, count2 = 0;
        List<Integer> res = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == element1) {
                count1++;
            } else if (nums[i] == element2) {
                count2++;
            } else if (count1 == 0) {
                element1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                element2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        int finalcount1 = 0, finalcount2 = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == element1) {
                finalcount1++;
            } else if (nums[j] == element2) {
                finalcount2++;
            }
        }
        if (finalcount1 > nums.length / 3) {
            res.add(element1);
        }
        if (finalcount2 > nums.length / 3) {
            res.add(element2);
        }
        return res;
    }
}
