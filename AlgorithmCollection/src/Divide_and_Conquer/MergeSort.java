package Divide_and_Conquer;
//Divide_and_Conquer.MergeSort 类用于实现归并排序算法

/**
 * Divide_and_Conquer.MergeSort 类实现了归并排序算法。
 * 归并排序（Merge Sort）的时间复杂度是O(nlogn)。
 * <p>
 * 归并排序的思路是将待排序的序列划分成若干个子序列，每个子序列都是有序的，然后再将这些子序列合并成一个有序的序列。
 * 具体来说，归并排序分为两个步骤：归并和排序。
 * 在排序的过程中，我们将待排序的序列递归地分成两个子序列，然后对这两个子序列进行归并排序。
 * 在归并的过程中，我们将两个有序子序列合并成一个有序序列。
 * <p>
 * 归并排序的时间复杂度分析：
 * <p>
 * 归并排序的时间复杂度可用递推式来表示：T(n) = 2T(n/2) + O(n)。
 * 归并排序的最好、最坏和平均时间复杂度都是O(nlogn)。
 * 归并排序的空间复杂度为O(n)。
 * 归并排序是稳定的排序算法。
 */
public class MergeSort {
    /**
     * sort 方法用于调用 mergeSort 进行归并排序
     *
     * @param arr 要进行排序的整数数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * mergeSort 方法用于实现归并排序算法，通过递归调用自身进行排序
     *
     * @param arr   要进行排序的整数数组
     * @param left  数组的起始下标
     * @param right 数组的终止下标
     */
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;//计算要排序数组的中间下标
            mergeSort(arr, left, mid);//排序中间下标左边的数
            mergeSort(arr, mid + 1, right);//排序中间下标右边的数
            merge(arr, left, mid, right);//合并两个有序子序列，产生一个有序序列
        }
    }

    /**
     * merge 方法用于合并两个有序数组
     *
     * @param arr   要进行合并的整数数组
     * @param left  数组的起始下标
     * @param mid   数组的中间下标
     * @param right 数组的终止下标
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;//计算左边数组长度
        int n2 = right - mid;//计算右边数组长度
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        /*for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }*/
        System.arraycopy(arr, left, leftArr, 0, n1);
        //leftArr数组中的元素就是arr数组中从下标left开始，长度为n1的这一段元素。

        /*for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }*/
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        //rightArr数组中的元素就是arr数组中从下标mid+1开始，长度为n2的这一段元素。
        int i = 0, j = 0;// 初始化左半部分数组的索引 i 和右半部分数组的索引 j
        int k = left;// 初始化归并后数组的索引 k，开始为 left
        while (i < n1 && j < n2) {// 当左半部分数组和右半部分数组均未遍历完时，循环
            if (leftArr[i] <= rightArr[j]) {// 若左半部分数组当前元素小于等于右半部分数组当前元素
                arr[k++] = leftArr[i++];// 将左半部分数组当前元素存入归并后数组，然后移动左半部分数组索引 i
            } else {// 若右半部分数组当前元素小于左半部分数组当前元素
                arr[k++] = rightArr[j++];// 将右半部分数组当前元素存入归并后数组，然后移动右半部分数组索引 j
            }
        }
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }// 当右半部分数组遍历完后，将左半部分数组剩余的元素全部存入归并后数组
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }// 当左半部分数组遍历完后，将右半部分数组剩余的元素全部存入归并后数组
    }
}
