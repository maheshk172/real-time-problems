package org.refresher;

public class ArrayUtils {

    public static int getLengthOfArray(int[] array) {
        int length = 0;
        for(int i=0; i<array.length; i++) {
            if(array[i] != 0) {
                length++;
            }
        }
        return length;
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.print(" ]");
        System.out.println("");
    }

    public static int[] copyNonNullArray(int[] array) {
        int lengthOfNonNullArray = getLengthOfArray(array);
        if(array.length != lengthOfNonNullArray) {
            int[] resultArray = new int[lengthOfNonNullArray];

            for(int i =0; i < lengthOfNonNullArray; i++) {
                resultArray[i] = array[i];
            }
            return resultArray;

        } else {
            return array;
        }
    }



}
