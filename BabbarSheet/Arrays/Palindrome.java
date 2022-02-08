package BabbarSheet.Arrays;

public class Palindrome {

    // 1
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l)))
                l++;
            else if (!Character.isLetterOrDigit(s.charAt(r)))
                r--;
            else if (Character.toLowerCase(s.charAt(l++)) != Character.toLowerCase(s.charAt(r--)))
                return false;
        }

        return true;
    }

    // 2
    public String firstPalindrome(String[] words) {
        for (int i = 0; i < words.length; i++) {
            StringBuilder reversedString = new StringBuilder(words[i]);

            if (reversedString.reverse().toString().equals(words[i])) {
                return words[i];
            }
        }
        return "";
    }

    // 3
    public boolean isPalindrome(int x) {
        if (x == 0)
            return true;

        if (x < 0 || x % 10 == 0)
            return false;

        int reverse = 0;

        while (x > reverse) {
            int lastDigit = x % 10;
            x = x / 10;

            reverse = (reverse * 10) + lastDigit;
        }

        if (x == reverse || reverse / 10 == x)
            return true;

        return false;
    }

}
