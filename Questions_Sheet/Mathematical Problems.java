public class MathematicalProblems {

    // LEETCODE 453. Minimum Moves to Equal Array Elements

    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
        }
        int ans = 0;
        for (int i : nums) {
            ans += i - min;
        }
        return ans;
    }

    // LEETCODE 67. Add Binary

    public String addBinary(String a, String b) {
        int c = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {
            int sum = c;
            if (i >= 0)
                sum += a.charAt(i) - '0';
            if (j >= 0)
                sum += b.charAt(j) - '0';
            sb.append(sum % 2);
            c = sum / 2;
            i--;
            j--;
        }
        if (c > 0) {
            sb.append(c);
        }
        return sb.reverse().toString();
    }

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE

    // LEETCODE
}
