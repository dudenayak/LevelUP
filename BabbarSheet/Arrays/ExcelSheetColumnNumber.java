package BabbarSheet.Arrays;

public class ExcelSheetColumnNumber {

    // TITLE TO NUMBER
    public int titleToNumber(String columnTitle) {

        int result = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            result *= 26;
            result += columnTitle.toUpperCase().charAt(i) - 64;
        }

        return result;
    }

    // NUMBER TO TITLE
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
}
