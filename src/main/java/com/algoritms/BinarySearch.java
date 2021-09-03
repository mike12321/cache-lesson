package com.algoritms;

public class BinarySearch {
    public static int iteratively(int[] array, int value, int from, int to) {
        int left = from;
        int right = to - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] > value) {
                right = mid - 1;
            }
            else if (array[mid] < value) {
                left = mid + 1;
            }
            else {
                return mid;
            }
        }

        // Return position where value would've been inserted
        return -(left + 1);
    }

    public static int recursively(int[] array, int value, int left, int right) {
        int result = -1;

        if (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] > value) {
                return recursively(array, value, left, mid - 1);
            }
            else if (array[mid] < value) {
                return recursively(array, value, mid + 1, right);
            }
            else if (array[mid] == value) {
                return mid;
            }
        }

        return result;
    }
}
