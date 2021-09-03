package com.algoritms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    public void binarySearchTestIteratively() {
        int[] array = {1, 12, 54, 63, 90, 120, 132, 454, 546, 900, 1000, 1200, 1300, 5000, 6000, 8000, 9000};
        int searchValue = 63;

        assertEquals(3, BinarySearch.iteratively(array, searchValue, 0, array.length));
    }

    @Test
    public void binarySearchTestIteratively_notFound() {
        int[] array = {1, 12, 54, 63, 90};
        int searchValue = 13;

        assertEquals(-3, BinarySearch.iteratively(array, searchValue, 0, array.length));
    }

    @Test
    public void binarySearchTestRecursively() {
        int[] array = {1, 12, 54, 63, 90, 120, 132, 454, 546, 900, 1000, 1200, 1300, 5000, 6000, 8000, 9000};
        int searchValue = 63;

        assertEquals(3, BinarySearch.recursively(array, searchValue, 0, array.length - 1));
    }

    @Test
    public void binarySearchTestRecursively_notFound() {
        int[] array = {1, 12, 54, 63, 90};
        int searchValue = 13;

        assertEquals(-1, BinarySearch.recursively(array, searchValue, 0, array.length - 1));
    }

    @Test
    public void performanceComparison() {
        int[] array = {1, 3, 4, 11, 21, 22, 32, 33, 55, 63, 90, 120, 132, 454, 546, 900, 1000, 1200, 1300,
                1000, 3000, 4000, 5000, 6000, 8000, 9000, 11000, 21000, 22000, 32000, 33000, 55000, 63000, 90000, 120000, 132000, 454000, 546000, 900000, 1000000, 1200000, 1300000, 5000000, 6000000, 8000000, 9000000};

        long startTimeIteratively = System.currentTimeMillis();
        BinarySearch.iteratively(array, 90, 0, array.length);
        System.out.println(System.currentTimeMillis() - startTimeIteratively);

        long startTimeRecursively = System.currentTimeMillis();
        BinarySearch.recursively(array, 90, 0, array.length);
        System.out.println(System.currentTimeMillis() - startTimeRecursively);
    }
}