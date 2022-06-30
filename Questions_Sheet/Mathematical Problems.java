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

    // LEETCODE 168. Excel Sheet Column Title

    public String convertToTitle(int columnNumber) {
        String s = "";
        while (columnNumber != 0) {
            columnNumber = columnNumber - 1;
            char c = (char) (columnNumber % 26 + 65);
            s = c + s;
            columnNumber /= 26;
        }
        return s;
    }

    // LEETCODE 202. Happy Number

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            n = getNext(n);
        }
        return n == 1;
    }

    // LEETCODE

    // LEETCODE

    // LEETCODE
}
