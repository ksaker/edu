package ru.platonov.edu.algorithm;

/**
 * Найти подмассив с максимальной суммой в числовом множестве
 * Created by platonov on 27/03/17.
 */
public class MaxSubArrayProblem {

    public int maxSum(int[] mas) {

        int maxSum = 0;
        int partialSum = 0;
        int startIndex = 0;
        int endIndex = 0;
        int currentMasIndex = 0;

        for (int i = 0; i < mas.length; i++) {
            partialSum += mas[i];

            if (maxSum < partialSum) {
                maxSum = partialSum;
                endIndex = i;
                startIndex = currentMasIndex;
            }

            if (partialSum < 0) {
                partialSum = 0;
                currentMasIndex = i;
            }
        }

        System.out.format("indexStart: %d, indexEnd: %d, maxSum: %d", startIndex, endIndex, maxSum);
        return maxSum;
    }

    public static void main(String[] args) {
        int[] mas = {1, 2, 3, -1, 8, 9, -100, 2, 5};
        MaxSubArrayProblem maxSubArrayProblem = new MaxSubArrayProblem();

        maxSubArrayProblem.maxSum(mas);
    }

}

