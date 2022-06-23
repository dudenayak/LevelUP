package Questions_Sheet;

public class TwoPointerApproach {

    // LEETCODE 424. Longest Repeating Character Replacement

    public int characterReplacement(String s, int k) {
        int max = 0;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!set.contains(c))
                continue;
            int last = -1;
            int replaced = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != c) {
                    replaced++;
                }
                while (replaced > k && last < i) {
                    last++;
                    if (s.charAt(last) != c) {
                        replaced--;
                    }
                }
                max = Math.max(max, i - last);
            }
        }

        return max;
    }

    // LEETCODE 1610. Maximum Number of Visible Points

    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int pointer = 0;
        List<Double> lis = new ArrayList<>();
        for (List<Integer> point : points) {
            if (point.get(0) - location.get(0) == 0 && point.get(1) - location.get(1) == 0) {
                pointer++;
                continue;
            }
            lis.add(Math.toDegrees(Math.atan2(point.get(0) - location.get(0), point.get(1) - location.get(1))));
        }

        double[] arr = new double[lis.size() * 2];
        int counter = 0, output = pointer;
        for (Double v : lis) {
            arr[counter++] = v;
            arr[counter++] = v + 360;
        }
        Arrays.sort(arr);
        for (int i = 0, j = i + 1; i < arr.length; i++) {
            while (arr[i] - arr[j] > angle)
                j++;
            output = Math.max(output, pointer + i - j + 1);
        }
        return output;
    }

}
