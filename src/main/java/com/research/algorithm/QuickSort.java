package com.research.algorithm;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        Integer[] array = new Integer[] { 3, 7, 2, 8, 9, 6, 4, 5, 7, 3, 2 };
        QSort(array, 0, array.length - 1);
        System.out.println(Arrays.asList(array));
//        System.out.println(2 >>> 1);
    }

    /*
     * 2 1 6 4 3 9 5 3
     */
    public static <T extends Comparable<? super T>> int divArray(T[] array, int start, int end) {
        T key = array[(start + end) >>> 1];
        array[(start + end) >>> 1] = array[start];
        array[start] = key;
        
        int i = start;
        int j = end;

        while (j > i) {
            while (key.compareTo(array[j]) <= 0 && j > i)
                j--;
            if (j > i) {
                array[i] = array[j];
                i++;
            }
            
            while (key.compareTo(array[i]) >= 0 && j > i)
                i++;
            if (j > i) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = key;
        return i;
    }
    
    public static <T extends Comparable<? super T>> void QSort(T[] array, int start, int end) {
        if (end > start) {
            int i = divArray(array,start,end);
            QSort(array,start,i-1);
            QSort(array,i+1,end);
        }
    }
}
