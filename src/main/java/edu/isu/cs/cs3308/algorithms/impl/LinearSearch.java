package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class LinearSearch implements ArraySearch {

    @Override
    public <E extends Comparable> int search(E[] array, E item) {

        // Checks if the array is null before starting search
        if(array != null)
        {
            for(int i = 0; i < array.length - 1; i++)
            {
                if(array[i] == item)
                {
                    return i;
                }
            }
            return -1;
        }
        //If the array is null, just return 1
        else
        {
            return -1;
        }
    }
}
