import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class ContiguousAndNonContiguousArrayMaxSumCalculator {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int numberOfTestCases = Integer.parseInt(line);

        for(int i = 0; i <  numberOfTestCases; i++){

            String arraySizeInput = br.readLine();

            //Not used as size of array can got from the array values input
            int arraySize = Integer.parseInt(arraySizeInput);

            String[] stringArrayValues  = br.readLine().split(" ");

            int[] arrayValues = populateArrayValues(stringArrayValues);

            System.out.println("Contiguous max sum: " + calculateContiguousMaxSum(arrayValues));
            System.out.println("Non-Contiguous max sum: " + calculateNonContiguousMaxSum(arrayValues));
        }
    }


    public static int calculateNonContiguousMaxSum(int[] arrayValues){

        Arrays.sort(arrayValues);

        int sum = 0;
        boolean startCalculation  = false;
        int startIndex = 0;

        for(int i = 0; i < arrayValues.length; i++){

            if(arrayValues[i] > 0){
                startCalculation = true;
                startIndex = i;
                break;
            }
        }
        if(startCalculation){
            for(int i = startIndex; i < arrayValues.length; i++){
                sum = sum + arrayValues[i];
            }
        }

        return sum;
    }

    public static int calculateContiguousMaxSum(int[] arrayValues){

        int sum = 0;
        int currentSum = 0;

        for (int index = 0; index < arrayValues.length; index++) {

            if (arrayValues[index] >= 0 ){
                currentSum = currentSum + arrayValues[index];
            }else{
                int traversalSum = traverseAndCalculateSumFromNegativeValueIndex(arrayValues, index);
                if(traversalSum > 0){
                    currentSum = currentSum + traversalSum;
                }
                if (currentSum > sum){
                    sum = currentSum;
                }
                currentSum = 0;
            }
        }
        if (currentSum > sum){
            return currentSum;
        }else {
            return sum;
        }

    }


    /* Helper method to populate integer values passed in input to an array */
    private static int[] populateArrayValues(String[] stringArrayValues){

        int[] arrayValues  = new int[stringArrayValues.length];

        for(int i = 0; i < stringArrayValues.length; i++){
            arrayValues[i] = Integer.parseInt(stringArrayValues[i]);
        }
        return arrayValues;
    }

    /* Helper method to calculate the sum of the sub array from the index with negative value until the end of the array */
    private static int traverseAndCalculateSumFromNegativeValueIndex(int[] arrayValue, int index){

        int sum = 0;

        for (int i = index; i < arrayValue.length; i++){
            sum = sum + arrayValue[i];
        }
        return sum;
    }
}
