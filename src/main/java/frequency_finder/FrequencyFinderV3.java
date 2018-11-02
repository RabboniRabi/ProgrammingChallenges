import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Version 3.0 of {@link FrequencyFinder}.
 *
 * In this version of the solution, we are going to
 * utilise the fact that the numbers in the input
 * array are in the range: 0 <= number <= 1000
 *
 * In this case, the running time of the algorithm is linear
 */
public class FrequencyFinderV3 {

    /**
     * The main method of this class
     * @param args command line arguments - not used in the program.
     */
    public static void main(String[] args) throws IOException {

        /* Initialise the buffered reader */
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        /* Get the number of numbers that will be given */
        String numbersShownStr = reader.readLine();
        int numbersShown = Integer.parseInt(numbersShownStr);

        /* Get the numbers */
        String numbersStr = reader.readLine();
        String[] numbersStrSplit = numbersStr.split(" ");

        /* create an array of 1001 values, where the value at the index
         * is the number of times the index is seen in the input array
         */
        int frequencyIndex[] = new int[1001];


        /* Iterate through the numbers read in the input stream */
        for (int i = 0; i < numbersShown; i++) {

            // parse the numbers in the array
            int number = Integer.parseInt(numbersStrSplit[i]);

            // update the frequency index
            frequencyIndex[number]++;
        }

        /* Get the number of questions */
        String numberOfQuestionsStr = reader.readLine();
        int numberOfQuestions = Integer.parseInt(numberOfQuestionsStr);

        /* Scan for a question, find frequency and print */
        for (int i = 0; i < numberOfQuestions; i++) {

            String questionStr = reader.readLine();
            int question = Integer.parseInt(questionStr);

            int frequency = frequencyIndex[question];

            if (frequency != 0) {
                System.out.println(frequency);
            }
            else {
                System.out.println("NOT PRESENT");
            }
        }

    }

}
