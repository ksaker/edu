package ru.platonov.edu.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by platonov on 14/02/17.
 */
public class QuickSort {
    private final int [] mas;

    public QuickSort(int [] mas) {
        this.mas = mas;
    }

    public void sort() {
        int leftIndex = 0;
        int rightIndex = mas.length - 1;

        sort(leftIndex, rightIndex);
    }

    private void sort(int startMarginIndex, int endMarginIndex) {
        if(startMarginIndex >= endMarginIndex) {
            return;
        }

        Range partition = partition(startMarginIndex, endMarginIndex);

        sort(partition.getLeftIndex(), endMarginIndex);
        sort(startMarginIndex, partition.getRightIndex());
    }

    private Range partition(int startMarginIndex, int endMarginIndex) {
        int leftIndex = startMarginIndex;
        int rightIndex = endMarginIndex;

        int pivot = mas[leftIndex + (rightIndex - leftIndex) / 2];

        while (leftIndex <= rightIndex) {
            while (mas[leftIndex] < pivot) {
                leftIndex++;
            }

            while (mas[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                swap(leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }

        return new Range(leftIndex, rightIndex);
    }

    private void swap(int leftIndex, int rightIndex) {
        int tmp = mas[leftIndex];
        mas[leftIndex] = mas[rightIndex];
        mas[rightIndex] = tmp;
    }

    private static class Range {
        private final int leftIndex;
        private final int rightIndex;

        private Range(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }

        public int getLeftIndex() {
            return leftIndex;
        }

        public int getRightIndex() {
            return rightIndex;
        }
    }

    public void iterativeSort() {
        iterative(0, mas.length - 1);
    }

    private void iterative(int startMarginIndex, int leftMarginIndex) {
        Stack<Range> rangeStack = new Stack<>();
        rangeStack.push(new Range(startMarginIndex, leftMarginIndex));

        while(!rangeStack.isEmpty()) {
            Range range = rangeStack.pop();
            int leftIndex = range.getLeftIndex();
            int rightIndex = range.getRightIndex();

            if (rightIndex <= leftIndex) {
                continue;
            }

            Range partition = partition(leftIndex, rightIndex);

            rangeStack.push(new Range(partition.getLeftIndex(), range.getRightIndex()));
            rangeStack.push(new Range(range.getLeftIndex(), partition.getRightIndex()));
        }

    }

    public void hollandSort() {
        hollandFlagSort(0, mas.length - 1);
    }

    //holland flag problem
    private void hollandFlagSort(int startMarginIndex, int endMarginIndex) {

        if(endMarginIndex <= startMarginIndex) {
            return;
        }

        int leftIndex = startMarginIndex;
        int leftEquals = startMarginIndex;
        int rightIndex = endMarginIndex;
        int rightEquals = endMarginIndex;

        int pivot = mas[leftIndex + (rightIndex - leftIndex) / 2];

        while (leftIndex <= rightIndex) {
            while (mas[leftIndex] < pivot) {
                leftIndex++;
            }

            while (mas[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex >= rightIndex) {
                break;
            }

            swap(leftIndex, rightIndex);

            if (mas[leftIndex] == pivot) {
                swap(leftIndex, leftEquals);
                leftEquals++;
            }

            if (mas[rightIndex] == pivot) {
                swap(rightIndex, rightEquals);
                rightEquals--;
            }

            leftIndex++;
            rightIndex--;
        }

        for(int i = leftEquals - 1; i >= startMarginIndex; i--, rightIndex --) {
            swap(i, rightIndex);
        }

        //good
        for(int j = rightEquals + 1; j <= endMarginIndex; j++, leftIndex++) {
            swap(j, leftIndex);
        }

        hollandFlagSort(leftIndex, endMarginIndex);
        hollandFlagSort(startMarginIndex, rightIndex);
    }

    public static void main(String[] args) {
//        int[] mas = {3, 9, 5, 7, 2, 10};

        int[] mas = {1, 3, 2, 9, 10, 3, 2, 1};

        new QuickSort(mas).sort();

        System.out.println(Arrays.toString(mas));

        int[] mas2 = {1, 3, 2, 9, 10, 3, 2, 1};

        new QuickSort(mas2).iterativeSort();

        System.out.println(Arrays.toString(mas2));

        int[] mas3 = {2, 1, 4, 2, 10, 3, 2, 9, 2, 1, 4, 9, 10, 3, 4, 10, 2};

        int[] mas4 = {1, 3, 2, 9, 10, 3, 2, 1};

        new QuickSort(mas3).hollandSort();

        System.out.println(Arrays.toString(mas3));

    }

}
