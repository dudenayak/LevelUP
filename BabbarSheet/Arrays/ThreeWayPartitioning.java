package BabbarSheet.Arrays;

//1
public class ThreeWayPartitioning {
    public void threeWayPartition(int array[], int a, int b) {
        int n = array.length;

        int start = 0, end = n - 1;

        for (int i = 0; i <= end;) {

            if (array[i] < a) {

                int temp = array[start];
                array[start] = array[i];
                array[i] = temp;
                start++;
                i++;

            }

            else if (array[i] > b) {

                int temp = array[end];
                array[end] = array[i];
                array[i] = temp;
                end--;

            }

            else
                i++;
        }
    }

    // SORT 3 COLORS
    public void sortColors(int[] arr) {
        int i = 0;
        int j = 0;
        int k = arr.length - 1;

        while (j <= k) {

            if (arr[j] == 1) {
                j++;
            } else if (arr[j] == 0) {
                swap(arr, j, i);
                i++;
                j++;
            } else {
                swap(arr, j, k);
                k--;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
