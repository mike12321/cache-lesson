package com.algoritms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class MergeSortTest {

    @Test
    public void mergeSortTest() {
        int[] array = {3, 2, 17, 99, 1231, 18, -1, 13};
        String expected = "[-1, 2, 3, 13, 17, 18, 99, 1231]";

        MergeSort.sort(array);

        assertEquals(expected, Arrays.toString(array));
    }
}