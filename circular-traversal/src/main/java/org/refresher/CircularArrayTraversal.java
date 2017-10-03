package org.refresher;

import java.util.*;

public class CircularArrayTraversal implements CircularTraversal {


    public int removeNextAndGiveKnifeToNext(int[] elements) {
        int startPosition = 0;
        int survivor = findLastSurvivor(elements, startPosition);
        return survivor;
    }

    public int findLastSurvivor(int[] elements, int startPosition) {
        ArrayUtils.printArray(elements);

        if(elements.length == 1) {
            return elements[0];
        }

        List<Integer> newElements = new ArrayList<Integer>();
        int j = 0;
        int i = startPosition;
        for (i = startPosition; i < elements.length && elements[i] != 0; i = i + 2, j++) {
            newElements.add(elements[i]);
        }

        int length = newElements.size();
        final int[] remainingArray = new int[length];
        i = 0;

        Iterator iter = newElements.iterator();
        while(iter.hasNext()) {
            remainingArray[i] = (Integer)iter.next();
            i++;
        }

        return findLastSurvivor(remainingArray, length%2);
    }


}
