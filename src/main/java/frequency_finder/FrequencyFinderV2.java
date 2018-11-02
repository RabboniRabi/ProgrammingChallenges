import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 * Program to find frequency of occurrences of a given set of numbers
 * in another array of numbers.
 *
 *
 * The earlier implementation was straight forward in searching for frequencies
 * of m numbers in an array of length n. When m or n is small, the running time
 * is along the order of the input. However, when m and n are large and of the same
 * order then the running time becomes quadratic.
 *
 * This program will use merge sort and binary search to bring down the order
 * to n log n.
 *
 * Turns out a solution for the problem can be done in linear time if the numbers
 * are within a pre-defined range. {@link FrequencyFinderV3}
 * </p>
 */
public class FrequencyFinderV2 {

    /**
     * The main method of this class
     * @param args command line arguments - not used in the program.
     */
    public static void main(String args[]) throws IOException {

        FrequencyFinderV2 v2 = new FrequencyFinderV2();

        /* Initialise the buffered reader */
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        /* Get the number of numbers that will be given */
        String numbersShownStr = reader.readLine();
        int numbersShown = Integer.parseInt(numbersShownStr);

        /* Get the numbers */
        String numbersStr = reader.readLine();
        String[] numbersStrSplit = numbersStr.split(" ");
        int[] numbers = new int[numbersShown];

        // convert the numbers given as strings to array of integers
        for (int i = 0; i < numbersStrSplit.length; i++) {
            int number = Integer.parseInt(numbersStrSplit[i]);
            numbers[i] = number;
        }

        /* sort the numbers */
        int[] sortedNumbers = v2.mergeSort(numbers);

        /* Get the number of questions */
        String numberOfQuestionsStr = reader.readLine();
        int numberOfQuestions = Integer.parseInt(numberOfQuestionsStr);

        /* create an array holding all the answers - to be printed out later */
        int[] answers = new int[numberOfQuestions];

        /* Scan for a question and find and store the frequency value */
        for (int i = 0; i < numberOfQuestions; i++) {

            String questionStr = reader.readLine();
            int question = Integer.parseInt(questionStr);

            /* frequency is the number of matches found in the search */
            int frequency = v2.binarySearch(
                    sortedNumbers, question, 0, sortedNumbers.length - 1).length;
            // store the answer in an array
            answers[i] = frequency;
        }

        /* Print out the answers - NOT PRESENT where frequency is 0 */

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == 0) {
                System.out.println("NOT PRESENT");
            }
            else {
                System.out.println(answers[i]);
            }
        }

    }

    /**
     * <p>
     * Helper method which implements the merge sort algorithm
     * </p>
     * @param numbers The array of numbers to be sorted
     * @return the sorted array of numbers
     */
    private int[] mergeSort(int[] numbers) {

        /* If the length of the array is 1, return it back */
        if (numbers.length == 1) {
            return numbers;
        }
        else { /* Divide and conquer */

            /* Split the given array along the middle into left and right halves */
            int splitLength = numbers.length / 2;
            int[] leftArray = new int[splitLength];
            int[] rightArray = new int[numbers.length - splitLength];

            /* Populate the left array */
            for (int i = 0; i < leftArray.length; i++) {
                leftArray[i] = numbers[i];
            }

            /* Populate the right array */
            for (int i = 0; i < rightArray.length; i++) {
                rightArray[i] = numbers[i + splitLength];;
            }

            int[] leftSorted = mergeSort(leftArray);
            int[] rightSorted = mergeSort(rightArray);

            /* merge the two smaller sorted arrays */
            return merge(leftSorted, rightSorted);

        }

    }

    /**
     * <p>
     * Helper method to merge two arrays, each of which
     * is sorted into a larger sorted array
     * </p>
     * @param leftArray the left sorted array
     * @param rightArray the right sorted array
     * @return the merged sorted array
     */
    private int[] merge(int[] leftArray, int[] rightArray) {

        /* Get the length of the array to be merged */
        int mergedLength = leftArray.length + rightArray.length;

        /* Declare a larger array to merge */
        int[] mergedArray = new int[mergedLength];

        /* The left array and right array cursors */
        int leftCursor = 0, rightCursor = 0;

        /* Iterate and pick the smallest of the two values from
        *  the two arrays as the merged array is filled up */
        for (int i = 0; i < mergedLength; i++) {

            if (leftCursor == leftArray.length) {

                mergedArray[i] = rightArray[rightCursor];
                rightCursor++;
            }
            else if (rightCursor == rightArray.length) {

                mergedArray[i] = leftArray[leftCursor];
                leftCursor++;
            }
            else {
                if (leftArray[leftCursor] <= rightArray[rightCursor]) {
                    mergedArray[i] = leftArray[leftCursor];
                    leftCursor++;
                }
                else {
                    mergedArray[i] = rightArray[rightCursor];
                    rightCursor++;
                }
            }

        }

        return mergedArray;
    }

    /**
     * <p>
     * Private method which implements the binary search algorithm
     * to search for occurrences of a number in an array,
     * This function returns an array of values which are indices in the
     * given array. These indices are occurrences of the number in the array.
     * </p>
     * @param array The array in which the number is to be searched for
     * @param number The number to be searched for
     * @param searchStart the index from which to search for the number in the array
     * @param searchEnd the index upto which to search for the number in the array
     * @return An array of indices - where each index is a position of occurrence of the number
     *             An empty array is returned if the number is not found
     */
    private int[] binarySearch(int[] array, int number, int searchStart, int searchEnd) {

        /* Check if the array length is 1 - end of recursive search */
        if (searchEnd - searchStart == 0) {
            if (array[searchStart] == number) {

                int[] matchingIndices = {searchStart};
                return matchingIndices;
            }
            else {
                return new int[0];
            }
        }

        /* Check if the array length is 2 - end of recursive search */
        if (searchEnd - searchStart == 1) {

            if (array[searchStart] == number  && array[searchEnd] == number) {
                int[] matchingIndices = {searchStart, searchEnd};
                return matchingIndices;
            }
            else if (array[searchStart] == number) {
                int[] matchingIndices = {searchStart};
                return matchingIndices;
            }
            else if (array[searchEnd] == number) {
                int[] matchingIndices = {searchEnd};
                return matchingIndices;
            }
            else {
                return new int[0];
            }
        }

        // Declare a first index and last index of occurrences
        // of the number in the array
        int firstIndex, lastIndex;

        /* calculate the middle index to look at */
        int middleIndex = (searchStart + searchEnd) / 2;

        if (array[middleIndex] == number) {

            /* Found the number! - look for occurrences to the left and right */
            firstIndex = lastIndex = middleIndex;

            /* look left */
            for (int i = middleIndex-1; i >= 0 ; i--) {

                if (array[i] == number) {
                    /* update the first index */
                    firstIndex--;
                }
                else {
                    break; // break out of the loop
                }

            }

            /* look right */
            for (int i = middleIndex+1; i < array.length; i++) {

                if (array[i] == number) {

                    /* update the right index */
                    lastIndex++;
                }
                else {
                    break;
                }

            }

            /* Return the indices in the array with the given number */
            int numberOfMatches = lastIndex - firstIndex + 1;
            int[] matchingIndices = new int[numberOfMatches];

            for (int i = 0; i < numberOfMatches; i++) {
                matchingIndices[i] = firstIndex + i;
            }

            return matchingIndices;

        }

        else if (number < array[middleIndex]) {

            /* look in the left section of the array */
            return binarySearch(array, number, searchStart, middleIndex);
        }
        else {

            /* Look in the right section of the array */
            return binarySearch(array, number, middleIndex, searchEnd);
        }
    }

}