import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rabboni on 09/01/17.
 */
public class RepeatNumbersFinder {

    public static void main(String[] args){

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter list of integer numbers to be looked at to find repeating numbers. Sample: 2,3,4,5,3,5 ");
        try {
            String inputLine = bufferedReader.readLine();
            List<Integer> repeatingNumbersList = bubbleCheckRepeatElements(sortArray(parseInputString(inputLine)));
            System.out.println("Repeating numbers list: " + repeatingNumbersList);

        } catch (IOException e) {
            System.out.println("There was an error in your input. Try again.");
        }
    }

    /**
     * Uses Java's in-built quick sort implementation.
     * Run-time of this method on average will be O(n log n).
     * @param arrayToBeSorted
     * @return
     */
    private static int[] sortArray(int[] arrayToBeSorted){
        Arrays.sort(arrayToBeSorted);
        return arrayToBeSorted;
    }

    /**
     * Simple iterative check through array for  equality of
     * an element at an index with elements at subsequent indices.
     * Returns a list with repeating numbers.
     * Run-time of this method will be O(n).
     * @param sortedArray
     * @return
     */
    private static List<Integer> bubbleCheckRepeatElements(int[] sortedArray){

        List<Integer> repeatingNumbersList = new ArrayList<Integer>();
        boolean repeated = false;

        for (int index = 0; index < sortedArray.length - 1; index++) {

            while (index < sortedArray.length - 1 && sortedArray[index] == sortedArray[index + 1]){
                repeated = true;
                index++;
            }
            if (repeated){
                repeatingNumbersList.add(sortedArray[index]);
            }
            repeated = false;
        }
        return repeatingNumbersList;
    }

    /**
     * Helper method to parse a given input string into an array of integers.
     * @param inputString
     * @return
     */
    private static int[] parseInputString(String inputString){

        String[] splitStrings =  inputString.split(",");
        int[] numbersArray = new int[splitStrings.length];

        try{
            for (int index = 0; index < splitStrings.length; index++) {
                    numbersArray[index] = Integer.parseInt(splitStrings[index]);
            }
        }catch (NumberFormatException nfe){
            System.out.println("Invalid input. Try again.");
            System.exit(0);
        }
        return numbersArray;
    }
}
