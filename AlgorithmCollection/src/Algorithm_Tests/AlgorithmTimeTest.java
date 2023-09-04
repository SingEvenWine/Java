package Algorithm_Tests;

import Divide_and_Conquer.MergeSort;
import Divide_and_Conquer.QuickSort;

import java.util.Random;

public class AlgorithmTimeTest {
    private final String[] algorithmNames;
    private final long[] elapsedTimeArray;
    private int[] arr;

    public AlgorithmTimeTest(String... algorithmNames) {
        this.algorithmNames = algorithmNames;
        this.elapsedTimeArray = new long[algorithmNames.length];
        this.arr = generateRandomIntArray(1000000);
    }

    public void setArr(int size) {
        this.arr = generateRandomIntArray(size);
    }

    public void run(Runnable... algorithms) {
        for (int i = 0; i < algorithms.length; i++) {
            int[] arrCopy = arr.clone();
            long startTime = System.currentTimeMillis();
            algorithms[i].run();
            long elapsedTime = System.currentTimeMillis() - startTime;
            elapsedTimeArray[i] = elapsedTime;
            this.arr=arrCopy.clone();
        }
    }

    public void printElapsedTime() {
        for (int i = 0; i < algorithmNames.length; i++) {
            System.out.printf("%s took %d seconds to execute.\n", algorithmNames[i], elapsedTimeArray[i]);
        }
    }

    private static int[] generateRandomIntArray(int size) {
        int[] arr = new int[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }
        return arr;
    }

    public static void main(String[] args) {
        AlgorithmTimeTest test = new AlgorithmTimeTest("MergeSort", "QuickSort");
        test.setArr(1000000);
        test.run(
                () -> {
                    MergeSort.sort(test.arr);
                },
                () -> {
                    QuickSort.sort(test.arr);
                }
        );
        test.printElapsedTime();
    }
}
