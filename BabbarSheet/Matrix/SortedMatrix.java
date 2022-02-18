package BabbarSheet.Matrix;

import java.util.*;

public class SortedMatrix {
    static void sortedMatrix(int Mat[][], int N) {
        // temporary Matrix of size n^2
        int temp[] = new int[N * N];
        int k = 0;

        // copy the elements of Matrix
        // one by one into temp[]
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                temp[k++] = Mat[i][j];

        // sort temp[]
        Arrays.sort(temp);

        // copy the elements of temp[]
        // one by one in Mat[][]
        k = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Mat[i][j] = temp[k++];
    }

    // function to print the given matrix
    static void printMat(int Mat[][], int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(Mat[i][j] + " ");
            System.out.println();
        }
    }
}