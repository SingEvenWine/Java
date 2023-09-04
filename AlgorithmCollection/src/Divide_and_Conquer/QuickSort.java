package Divide_and_Conquer;
//Divide_and_Conquer.QuickSort 类用于实现快速排序算法

/**
 * 快速排序的平均时间复杂度为 O(nlogn)，最坏情况下时间复杂度为 O(n^2)，最好情况下时间复杂度为 O(nlogn)。
 * 其中，最坏情况发生在待排序数组已经有序的情况下，而最好情况则是待排序数组随机分布的情况。
 */

public class QuickSort {
    /**
     * sort 方法用于对传入的整数数组进行排序，调用了 quickSort 方法
     *
     * @param arr 要进行排序的整数数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }


    /**
     * quickSort 方法用于实现快速排序算法，通过递归调用自身进行排序
     *
     * @param arr   要进行排序的整数数组
     * @param left  数组的起始下标
     * @param right 数组的终止下标
     */

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {// 如果数组不止一个元素
            int pivotIndex = partition(arr, left, right);// 划分数组
            quickSort(arr, left, pivotIndex - 1);//对左侧子数组进行快速排序
            quickSort(arr, pivotIndex + 1, right);// 对右侧子数组进行快速排序
        }
    }

    /**
     * partition 方法用于实现快速排序算法的一次分区操作
     *
     * @param arr   要进行分区操作的整数数组
     * @param left  分区操作的起始下标
     * @param right 分区操作的终止下标
     * @return 返回分区的位置下标
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];// 将右侧元素作为基准值
        int i = left - 1;// i 指向左侧区间的最后一个元素
        for (int j = left; j < right; j++) {// 遍历左右两侧区间，将小于基准值的元素交换到左侧区间
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);// 将基准值交换到中间位置
        return i + 1;
    }

    /**
     * swap 方法用于交换数组中两个元素的位置
     *
     * @param arr 要进行交换操作的整数数组
     * @param i   要进行交换的第一个元素的下标
     * @param j   要进行交换的第二个元素的下标
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
