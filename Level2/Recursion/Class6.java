package Level2.Recursion;

public class Class6 {
    public static void leftShift() {
        int n = 5;
        for (int i = 1; i <= 4; i++) {
            System.out.println(n << i);
        }
    }

    public static void rightShift() {
        int n = 80;
        for (int i = 1; i <= 4; i++) {
            System.out.println(n >> i);
        }
    }
}
