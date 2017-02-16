package ru.platonov.edu.sort;

import java.util.Arrays;

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
                rightIndex --;
            }

            if (leftIndex <= rightIndex){
                swap(leftIndex, rightIndex);
                leftIndex ++;
                rightIndex --;
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

    public static void main(String[] args) {
        //int[] mas = {3, 9, 5, 7, 2, 10};

        int[] mas = {1, 3, 2, 9, 10, 3, 2, 1};

        new QuickSort(mas).sort();

        System.out.println(Arrays.toString(mas));
    }

}
