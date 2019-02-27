package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.algorithms.ArraySearch;
import edu.isu.cs.cs3308.algorithms.impl.BinarySearch;
import edu.isu.cs.cs3308.algorithms.impl.LinearSearch;
import edu.isu.cs.cs3308.algorithms.impl.RecursiveBinarySearch;
import edu.isu.cs.cs3308.algorithms.impl.RecursiveLinearSearch;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;

/**
 * Driver class for the experimental simulator.
 * @author Isaac Griffith
 */
public class Driver {

    public static void main(String args[]) {

        // do the simulation using generateRandomArray()
        Integer[] randomArray = generateRandomArray(2000);


        // Sets up a final time long to record final time for each of the searches
        long finalTime = 0;

        long[] finalBinary = new long[10];
        //Creates the binary Array
        for(int i = 0; i < 10; i++)
        {

            for(int j = 0; j < 2000; j++)
            {
                //Creates new random int
                Random rng = new Random();

                //Gets starting time
                long startTime = System.nanoTime();

                BinarySearch binTest = new BinarySearch();

                binTest.search(randomArray, rng.nextInt(2000));

                long endTime = System.nanoTime();

                finalTime += endTime - startTime;
            }

            finalTime = finalTime / 2000;

            finalBinary[i] = finalTime;
        }

        //Creates final long for linear
        long[] finalLinear = new long[10];

        for(int i = 0; i < 10; i++)
        {

            for(int j = 0; j < 2000; j++)
            {
                //Creates new random int
                Random rng = new Random();

                //Gets starting time
                long startTime = System.nanoTime();

                LinearSearch linSearch = new LinearSearch();

                linSearch.search(randomArray, rng.nextInt(2000));

                long endTime = System.nanoTime();

                finalTime += endTime - startTime;
            }

            finalTime = finalTime / 2000;

            finalLinear[i] = finalTime;
        }

        // Creates final recur long array
        long[] finalRecurBinary = new long[10];

        for(int i = 0; i < 10; i++)
        {

            for(int j = 0; j < 2000; j++)
            {
                //Creates new random int
                Random rng = new Random();

                //Gets starting time
                long startTime = System.nanoTime();

                RecursiveBinarySearch recurBin = new RecursiveBinarySearch();

                recurBin.search(randomArray,rng.nextInt(2000));

                long endTime = System.nanoTime();

                finalTime += (endTime - startTime);
            }

            // Divide by 2000 since we are iterating through 2000 times.
            finalTime = finalTime / 2000;

            finalRecurBinary[i] = finalTime;
        }

        // Creates linear long array
        long[] finalRecurLinear = new long[10];

        for(int i = 0; i < 10; i++)
        {

            for(int j = 0; j < 2000; j++)
            {
                //Creates new random int
                Random rng = new Random();

                //Gets starting time
                long startTime = System.nanoTime();

                RecursiveLinearSearch recurlin = new RecursiveLinearSearch();

                recurlin.search(randomArray, rng.nextInt(2000));

                long endTime = System.nanoTime();

                finalTime += endTime - startTime;
            }

            finalTime = finalTime / 2000;

            finalRecurLinear[i] = finalTime;
        }

        // report the results using report;
        report(finalLinear,finalRecurLinear,finalBinary,finalRecurBinary, 2000, 10);
    }

    /**
     * Generates a random ordered array of integers of the provided size
     *
     * @param size Size of the random array
     * @return An array of the provided size of random numbers in ascending
     * order.
     */
    public static Integer[] generateRandomArray(int size) {
        Random rand = new Random();

        Integer[] array = new Integer[size];

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(2000);
        }

        Arrays.sort(array);
        return array;
    }

    /**
     * Generates the output to both a Comma Separated Values file called
     * "results.csv" and to the screen.
     *
     * @param iterLinTimes Array of average values for the Iterative Linear
     * Search
     * @param recLinTimes Array of average values for the Recursive Linear
     * Search
     * @param iterBinTimes Array of average values for the Iterative Binary
     * Search
     * @param recBinTimes Array of average values for the Recursive Binary
     * Search
     * @param startIncrement Start increment for array sizes
     * @param increment Increment of array sizes.
     */
    private static void report(long[] iterLinTimes, long[] recLinTimes, long[] iterBinTimes, long[] recBinTimes, int startIncrement, int increment) {
        StringBuilder file = new StringBuilder();
        StringBuilder screen = new StringBuilder();

        screen.append(String.format("N    IterLin\tRecLin\tIterBin\tRecBin%s", System.lineSeparator()));
        file.append(String.format("N,IterLin,RecLin,IterBin,RecBin%s", System.lineSeparator()));

        for (int i = 0; i < iterLinTimes.length; i++) {
            screen.append(String.format("%d %d\t%d\t%d\t%d%s", startIncrement + (i * increment), iterLinTimes[i], recLinTimes[i], iterBinTimes[i], recBinTimes[i], System.lineSeparator()
            ));
            file.append(String.format("%d,%d,%d,%d,%d%s", startIncrement + (i * increment), iterLinTimes[i], recLinTimes[i], iterBinTimes[i], recBinTimes[i], System.lineSeparator()
            ));
        }

        System.out.println(screen.toString());

        Path p = Paths.get("results.csv");
        try {
            Files.deleteIfExists(p);
        } catch (IOException e) {

        }

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.WRITE))) {
            pw.println(file.toString());
        } catch (IOException e) {

        }
    }
}
