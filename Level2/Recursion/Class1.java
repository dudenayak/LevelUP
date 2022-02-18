package Level2.Recursion;

import java.util.*;

public class Class1 {

    public static int subseq(String str, int idx, String ans, ArrayList<String> res) {
        if (idx == str.length()) {
            res.add(ans);
            return 1;
        }
        int count = 0;
        count += subseq(str, idx + 1, ans + str.charAt(idx), res);
        count += subseq(str, idx + 1, ans, res);
        return count;
    }
}
