package com.algoritms;

import java.util.Arrays;
public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void sortWithBinarySearch(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];

            int posToInsert = Math.abs(BinarySearch.iteratively(arr, value, 0, i) + 1);

            System.out.println(posToInsert);
            System.out.println(Arrays.binarySearch(arr, 0, i, value));

            System.arraycopy(arr, posToInsert,
                    arr, posToInsert + 1, i - posToInsert);

            arr[posToInsert] = value;

        }
    }
}
