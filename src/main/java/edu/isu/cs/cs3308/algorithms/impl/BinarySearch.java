package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class BinarySearch implements ArraySearch {



    @Override
    public <E extends Comparable> int search(E[] array, E item) {

        if(item == null)
        {
            return -1;
        }
        else if(array == null)
        {
            return -1;
        }
        int low = 0;
        int high = array.length - 1;

        while(low <= high)
        {
            int index = (low + high) / 2;

            if(item == array[index])
            {
                return index;
            }
            else if(item.hashCode() < array[index].hashCode())
            {
                high = index -1;
            }
            else
            {
                low = index + 1;
            }
        }
        return -1;
    }
}
