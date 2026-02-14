package core_java;

import java.util.Arrays;

public class SortArray {
    public static void main2(String[] args) {
        int[] arr ={0,1,0,1,0,1};
        int left =0;
        int right =0;
        for (int i : arr) {
            if (i == 0) {
                left++;
            }
            for (int j = 0; j < arr.length; j++) {
                if (j < left) {
                    arr[j] = 0;
                } else {
                    arr[j] = 1;
                }
            }
            for (int k : arr) {
                System.out.print(k + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 1, 0, 1, 1, 0, 0};

        int countZero = 0;
        int[] sortedArr = new int[arr.length];
        for (int i : arr) {
            if (i == 0) {
                countZero++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i < countZero) {
                sortedArr[i] = 0;
            } else {
                sortedArr[i] = 1;
            }
        }

        for (int i : sortedArr) {
            System.out.print(i + " ");
        }
    }
}

