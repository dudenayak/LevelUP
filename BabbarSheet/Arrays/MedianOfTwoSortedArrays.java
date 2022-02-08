package BabbarSheet.Arrays;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;
        int i = 0;
        int j = 0;
        int merged[] = new int[m + n];
        int x = 0;

        while (i < m && j < n) {
            if (arr1[i] < arr2[j]) {
                merged[x++] = arr1[i++];
            } else {
                merged[x++] = arr2[j++];
            }

        }
        while (i < m) {
            merged[x++] = arr1[i++];
        }
        while (j < n) {
            merged[x++] = arr2[j++];
        }

        int mid = (m + n) / 2;
        double median;

        if ((m + n) % 2 == 0) {

            int prev = mid - 1;
            median = Double.valueOf(merged[mid] + merged[prev]) / 2;
        }

        else {
            median = merged[mid];
        }
        return median;
    }

    // FIND MEDIAN
    public int find_median(int[] v) {

        int n = v.length;
        Arrays.sort(v);

        if (n % 2 != 0)
            return (int) v[n / 2];

        return (int) (v[(n - 1) / 2] + v[n / 2]) / 2;
    }
}
