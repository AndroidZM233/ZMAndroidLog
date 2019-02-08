package com.zm.utilslib;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void quickSort() {
        int[] a = {7, 5, 3, 2, 9, 10, 8, 4, 6, 1};
//        ArrayUtils.quickSort(a, 0, a.length - 1);
        quickSortTest(a, 0, a.length - 1);
        for (int i : a) {
            System.out.print(i + " ");
        }

    }

    public void quickSortTest(int[] a, int left, int right) {
        if (left > right) {
            return;
        }
        int pivot = a[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (pivot <= a[j] && i < j) {
                j--;
            }
            while (pivot >= a[i] && i < j) {
                i++;
            }

            if (i < j) {
                int value = a[i];
                a[i] = a[j];
                a[j] = value;
            }
        }

        a[left] = a[i];
        a[i] = pivot;
        quickSortTest(a, left, i - 1);
        quickSortTest(a, i + 1, right);
    }
}