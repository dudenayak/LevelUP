package Questions_Sheet;

import java.util.ArrayList;

public class SearchSort {

    // First and last occurrences of x
    class GFG {

        Long firstIndex(long arr[], int n, int x) {
            int low = 0, high = n - 1;
            long fi = -1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid] > x) {
                    high = mid - 1;
                } else if (arr[mid] < x) {
                    low = mid + 1;
                } else {
                    fi = mid;
                    high = mid - 1;
                }
            }
            return fi;
        }

        Long lastIndex(long arr[], int n, int x) {
            int low = 0, high = n - 1;
            long li = -1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr[mid] > x) {
                    low = mid + 1;
                } else if (arr[mid] < x) {
                    high = mid - 1;
                } else {
                    li = mid;
                    high = mid + 1;
                }
            }
            return li;
        }

        ArrayList<Long> find(long arr[], int n, int x) {
            ArrayList<Long> fili = new ArrayList<>();

            fili.add(firstIndex(arr, n, x));
            fili.add(lastIndex(arr, n, x));
            return fili;
        }
    }

    // 2nd Approach
    ArrayList<Long> find(long arr[], int n, int x) {
        long a1 = -1;
        long a2 = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                a1 = i;
                break;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == x) {
                a2 = i;
                break;
            }
        }
        ArrayList<Long> ans = new ArrayList<>();
        ans.add(a1);
        ans.add(a2);
        return ans;

    }

    // Value equal to index value
    ArrayList<Integer> valueEqualToIndex(int arr[], int n) {
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] == i + 1)
                ans.add(i + 1);
        }
        return ans;
    }

}
