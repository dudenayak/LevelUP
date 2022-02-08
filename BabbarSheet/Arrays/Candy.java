package BabbarSheet.Arrays;

import java.util.*;

public class Candy {
    public int candy(int[] ratings) {

        int[] ans = new int[ratings.length];
        Arrays.fill(ans, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1] && ans[i] <= ans[i - 1]) {
                ans[i] = ans[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && ans[i] <= ans[i + 1]) {
                ans[i] = ans[i + 1] + 1;
            }
        }

        int sm = 0;

        for (int a : ans) {
            sm += a;
        }

        return sm;
    }
}
