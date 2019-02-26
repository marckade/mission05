package edu.isu.cs.cs3308.algorithms.impl;

import edu.isu.cs.cs3308.algorithms.ArraySearch;

public class RecursiveLinearSearch implements ArraySearch {

    @Override
    public <E extends Comparable> int search(E[] array, E item) {

        //Checks if the array is null before continuing.
        if(array != null)
        {
            return recLinearSearch(array,item,0);
        }
        else
        {
            // Returns -1 if the array is null
            return -1;
        }
    }


    private <E extends Comparable> int recLinearSearch(E[] array, E item, int index)
    {
        if(index >= array.length)
        {
            return -1;
        }
        else if(array[index] == item)
        {
            return index;
        }
        return recLinearSearch(array,item,index +1);
    }
}
