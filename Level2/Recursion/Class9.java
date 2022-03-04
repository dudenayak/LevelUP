package Level2.Recursion;

import java.util.*;

public class Class9 {
    public static int kPartitions(String[] Character, int idx, int TotalTeam, ArrayList<ArrayList<String>> ans) {
        if (idx == Character.length) {
            for (ArrayList<String> a : ans)
                if (a.size() == 0)
                    return 0;

            for (ArrayList<String> a : ans)
                System.out.print(a + " ");

            System.out.println();
            return 1;
        }

        int count = 0;
        for (int i = 0; i < TotalTeam; i++) {
            ans.get(i).add(Character[idx]);
            count += kPartitions(Character, idx + 1, TotalTeam, ans);
            ans.get(i).remove(ans.get(i).size() - 1);

            if (ans.get(i).size() == 0)
                break;
        }

        return count;
    }

    public static void kPartitions() {
        int k = 3;
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        String[] Character = { "Thor", "IronMan", "Hulk", "SpiderMan", "CaptainAmerica" };
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList<>());

        System.out.println(kPartitions(Character, 0, k, ans));

    }

    static int[] map = new int[26];
    static boolean[] isUsed = new boolean[10];

    public static int StringToInteger(String str) {
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int mapedValue = map[ch - 'a'];
            res = res * 10 + mapedValue;
        }

        return res;
    }

    public static int crypto(String uniqueString, int idx, String str1, String str2, String str3) {
        if (idx == uniqueString.length()) {
            int n1 = StringToInteger(str1);
            int n2 = StringToInteger(str2);
            int n3 = StringToInteger(str3);

            if (n1 + n2 == n3) {
                for (int i = 0; i < 26; i++) {
                    if (map[i] != -1) {
                        System.out.print((char) (i + 'a') + "-" + map[i] + " ");
                    }
                }
                return 1;
            }

            return 0;
        }

        int count = 0;
        char ch = uniqueString.charAt(idx);
        for (int num = 0; num <= 9; num++) {
            if ((str1.charAt(0) == ch || str2.charAt(0) == ch || str3.charAt(0) == ch) && num == 0)
                continue;

            if (!isUsed[num]) {
                isUsed[num] = true;
                map[ch - 'a'] = num;

                count += crypto(uniqueString, idx + 1, str1, str2, str3);

                isUsed[num] = false;
                map[ch - 'a'] = -1;
            }
        }

        return count;

    }

    public static void crypto(String str1, String str2, String str3) {
        String str = str1 + str2 + str3;
        boolean[] freq = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            freq[str.charAt(i) - 'a'] = true;
        }

        str = "";
        for (int i = 0; i < 26; i++) {
            if (freq[i]) {
                str += (char) (i + 'a');
            }
        }

        Arrays.fill(map, -1);

        System.out.println(crypto(str, 0, str1, str2, str3));

    }
}
