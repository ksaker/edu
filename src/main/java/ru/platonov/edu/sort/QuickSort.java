package ru.platonov.edu.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by platonov on 14/02/17.
 */
public class QuickSort {
    final private int [] mas;

    public QuickSort(int [] mas) {
        this.mas = mas;
    }

    public void sort() {
        int leftIndex = 0;
        int rightIndex = mas.length - 1;

        sort(leftIndex, rightIndex);
    }

    private void sort(int startMarginIndex, int endMarginIndex) {
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

        if(leftIndex < endMarginIndex) {
            sort(leftIndex, endMarginIndex);
        }

        if(rightIndex > startMarginIndex) {
            sort(startMarginIndex, rightIndex);
        }

    }

    private void swap(int leftIndex, int rightIndex) {
        int tmp = mas[leftIndex];
        mas[leftIndex] = mas[rightIndex];
        mas[rightIndex] = tmp;
    }

    public void iterativeSort() {
        iterative(0, mas.length - 1);
    }

    private void iterative(int startMarginIndex, int leftMarginIndex) {
        Stack<Range> rangeStack = new Stack<>();
        rangeStack.push(new Range(startMarginIndex, leftMarginIndex));

        while(!rangeStack.isEmpty()) {
            Range range = rangeStack.pop();
            int leftIndex = range.getLeftMarginIndex();
            int rightIndex = range.getRightMarginIndex();

            if (leftIndex <= rightIndex) {
                continue;
            }

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

            if(leftIndex < range.getRightMarginIndex()) {
               rangeStack.push(new Range(leftIndex, range.getRightMarginIndex()));
            }

            if(rightIndex > range.getLeftMarginIndex()) {
               rangeStack.push(new Range(range.getLeftMarginIndex(), rightIndex));
            }
        }

    }

    public static void main(String[] args) {
        //int[] mas = {3, 9, 5, 7, 2, 10};

        int[] mas = {1, 3, 2, 9, 10, 3, 2, 1};

        new QuickSort(mas).sort();

        System.out.println(Arrays.toString(mas));

        int[] mas2 = {1, 3, 2, 9, 10, 3, 2, 1};

        new QuickSort(mas).iterativeSort();

        System.out.println(Arrays.toString(mas));
    }

    private static class Range {
        private final int leftMarginIndex;
        private final int rightMarginIndex;

        private Range(int leftMarginIndex, int rightMarginIndex) {
            this.leftMarginIndex = leftMarginIndex;
            this.rightMarginIndex = rightMarginIndex;
        }

        public int getLeftMarginIndex() {
            return leftMarginIndex;
        }

        public int getRightMarginIndex() {
            return rightMarginIndex;
        }
    }
}
