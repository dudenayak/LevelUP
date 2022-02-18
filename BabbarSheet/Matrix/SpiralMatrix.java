package BabbarSheet.Matrix;

import java.util.*;

public class SpiralMatrix {

    // 1
    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] visited = new int[matrix.length][matrix[0].length];
        List<Integer> ans = new ArrayList<>();
        recursive(matrix, visited, 0, 0, ans);
        return ans;
    }

    private void recursive(int[][] matrix, int[][] visited, int x, int y, List<Integer> ans) {
        if (x < 0 || y < 0 || x > matrix.length - 1 || y > matrix[0].length - 1 || visited[x][y] == 1) {
            return;
        }

        visited[x][y] = 1;
        ans.add(matrix[x][y]);
        if (x == 0 || visited[x - 1][y] == 1) {
            recursive(matrix, visited, x, y + 1, ans);
        }
        recursive(matrix, visited, x + 1, y, ans);
        recursive(matrix, visited, x, y - 1, ans);
        recursive(matrix, visited, x - 1, y, ans);
    }

    //
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];

        int top = 0;
        int left = 0;
        int bottom = n - 1;
        int right = n - 1;
        int add = 1;
        while (left <= right && top <= bottom) {

            for (int i = left; i <= right; i++) {
                arr[top][i] = add++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                arr[i][right] = add++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                arr[bottom][i] = add++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                arr[i][left] = add++;
            }
            left++;

        }

        return arr;
    }

    // 3
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] matrix = new int[rows * cols][2];
        int i = 0;
        matrix[i++] = new int[] { rStart, cStart };
        int d = 0;
        int len = 0;
        int[] directions = new int[] { 0, 1, 0, -1, 0 }; //
        while (i < rows * cols) {
            if (d == 0 || d == 2) {
                len++;
            }
            for (int k = 0; k < len; k++) {
                rStart += directions[d];
                cStart += directions[d + 1];
                if (rStart < rows && rStart >= 0 && cStart < cols && cStart >= 0) {
                    matrix[i++] = new int[] { rStart, cStart };
                }
            }
            d = ++d % 4;

        }
        return matrix;
    }
}
