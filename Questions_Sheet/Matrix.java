package Questions_Sheet;

public class Matrix {

    // LEETCODE 54. Spiral Matrix

    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] visited = new int[matrix.length][matrix[0].length];
        List<Integer> ans = new ArrayList<>();
        recursive(matrix, visited, 0, 0, ans);
        return ans;
    }

    private void recursive(int[][] matrix, int[][] visited, int r, int c, List<Integer> ans) {
        if (r < 0 || c < 0 || r > matrix.length - 1 || c > matrix[0].length - 1 || visited[r][c] == 1)
            return;

        visited[r][c] = 1;
        ans.add(matrix[r][c]);
        if (r == 0 || visited[r - 1][c] == 1)
            recursive(matrix, visited, r, c + 1, ans);
        recursive(matrix, visited, r + 1, c, ans);
        recursive(matrix, visited, r, c - 1, ans);
        recursive(matrix, visited, r - 1, c, ans);

    }

    // LEETCODE 48. Rotate Image

    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    public void transpose(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    public void reflect(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }
    }
}
