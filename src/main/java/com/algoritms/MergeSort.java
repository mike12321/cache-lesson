package com.algoritms;

public class MergeSort {
    public static void sort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] leftArr = new int[arr.length / 2];
            int[] rightArr = new int[arr.length / 2];

            System.arraycopy(arr, 0, leftArr, 0, arr.length / 2);
            System.arraycopy(arr, mid, rightArr, 0, arr.length / 2);

            sort(leftArr);
            sort(rightArr);

            int i = 0;
            int j = 0;
            int k = 0;

            while (i < leftArr.length && j < rightArr.length) {
                if (leftArr[i] < rightArr[j]) {
                    arr[k] = leftArr[i];
                    i++;
                }
                else {
                    arr[k] = rightArr[j];
                    j++;
                }
                k++;
            }

            while (i < leftArr.length) {
                arr[k] = leftArr[i];
                i++;
                k++;
            }

            while (j < rightArr.length) {
                arr[k] = rightArr[j];
                j++;
                k++;
            }
        }
    }

}
