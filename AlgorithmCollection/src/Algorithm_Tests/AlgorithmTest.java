package Algorithm_Tests;

import Divide_and_Conquer.MergeSort;
import Divide_and_Conquer.QuickSort;

import java.util.Arrays;

public class AlgorithmTest {
    public static void main(String[] args) {
        int[] arr1 = {1, 5, 4, 7, 6, 99, 58, 68, 96, 156, 36, 25, 66, 75, 89, 100};
        QuickSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = {1, 5, 4, 7, 6, 99, 58, 68, 96, 156, 36, 25, 66, 75, 98, 100};
        QuickSort.sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}