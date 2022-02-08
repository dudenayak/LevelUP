package BabbarSheet.Arrays;

import java.util.*;

public class KthSmallestElement {
    public static int kthSmallest(int[] arr, int l, int r, int k) {
        // Your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                pq.add(arr[i]);
            } else {
                if (arr[i] < pq.peek()) {
                    pq.remove();
                    pq.add(arr[i]);
                }
            }
        }
        int res = pq.peek();
        return res;
    }

}
