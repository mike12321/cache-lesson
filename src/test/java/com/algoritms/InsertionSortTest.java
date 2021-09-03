package com.algoritms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertionSortTest {

    @Test
    public void insertionSortTest() {
        int[] array = {3, 2, 17, 99, 1231, 18, -1, 13};
        String expected = "[-1, 2, 3, 13, 17, 18, 99, 1231]";

        InsertionSort.sort(array);

        assertEquals(expected, Arrays.toString(array));
    }

    @Test
    public void performanceTest() {
        long startTime = System.currentTimeMillis();
        int[] array = {3, 2, 17, 99, 1231, 18, -1, 13, 2141, 124324, -1, 214, 4533, 7, 5, 99};

        InsertionSort.sort(array);
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void insertionSortWithBinarySearchTest() {
        int[] array = {3, 2, 17, 99, 1231, 18, -1, 13};
        String expected = "[-1, 2, 3, 13, 17, 18, 99, 1231]";

        InsertionSort.sortWithBinarySearch(array);

        assertEquals(expected, Arrays.toString(array));
    }
}