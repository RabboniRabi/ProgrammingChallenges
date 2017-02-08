/**
 * Created by rabboni on 03/02/17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Program that takes two arrays as input
 * and prints out if the second array
 * is a sub-array of the first.
 */
public class SubArrayChecker {

    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the array against which a sub-array will be checked:");
            System.out.println("Sample: 4,5,7,3,1,2");
            String firstArray = bufferedReader.readLine();
            String[] firstArrayValues = firstArray.split(",");
            System.out.println("Enter the second array:");
            System.out.println("This array will be checked if it is a sub-array of the previously entered array.");
            String secondArray = bufferedReader.readLine();
            String[] secondArrayValues = secondArray.split(",");


            int[] masterArray = convertToIntegerArray(firstArrayValues);
            int[] possibleSubArray = convertToIntegerArray(secondArrayValues);

            checkSubArray(masterArray, possibleSubArray);


        } catch (IOException e) {
            System.out.println("Invalid input.");
            System.exit(0);
        }
    }


    public static void checkSubArray(int[] masterArray, int[] possibleSubArray) throws Exception {

        if (masterArray.length < possibleSubArray.length){
            throw new Exception("Size of master array is less than the given array to check.");
        }

        Arrays.sort(masterArray);
        Arrays.sort(possibleSubArray);

        boolean notASubArray = false;

        for (int i = 0; i < possibleSubArray.length; i++){

            //check if ith element in sub-array exists in the master array.
            int positionResult = Arrays.binarySearch(masterArray, possibleSubArray[i]);

            if (positionResult < 0){
                notASubArray = true;
                break;
            }
        }

        if (notASubArray){
            System.out.println("The given second array is not a sub array of the first.");
        }else{
            System.out.println("The given second array is a sub array of the first.");
        }

    }

    /* Helper method to convert string array to integer array */
    private static int[] convertToIntegerArray(String[] stringArray){

        int[] integerArray = new int[stringArray.length];

        try{
            for (int i = 0; i < stringArray.length; i++) {
                integerArray[i] = Integer.parseInt(stringArray[i]);
            }
        }catch (NumberFormatException nfe){
            System.out.println("Invalid input.");
            System.exit(0);
        }

        return integerArray;
    }

}
