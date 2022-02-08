package Test;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String A = scn.next();
        String B = scn.next();

        System.out.println(minLetter(A, B));
    }

    public static int minLetter(String A, String B) {

        if (A.length() != B.length())
            return -1;

        int i, j, res = 0;
        int count[] = new int[256];

        for (i = 0; i < A.length(); i++) {
            count[A.charAt(i)]++;
            count[B.charAt(i)]--;
        }

        for (i = 0; i < 256; i++)
            if (count[i] != 0)
                return -1;

        i = A.length() - 1;
        j = B.length() - 1;

        while (i >= 0) {

            if (A.charAt(i) != B.charAt(j))
                res++;
            else
                j--;
            i--;
        }
        return res;
    }

}
