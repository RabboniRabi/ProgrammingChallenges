import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <p>
 * Program to find frequency of occurrences of a given set of numbers
 * in another array of numbers.
 *
 * This version is a brute force solution for the problem:
 * www.hackerearth.com/practice/data-structures/arrays/1-d/practice-problems/algorithm/memorise-me/
 * </p>
 */
public class FrequencyFinder {

    public static void main (String[] args) throws IOException {

        FrequencyFinder frequencyFinder = new FrequencyFinder();

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

        // convert to the numbers as strings to array of integers
        for (int i = 0; i < numbersStrSplit.length; i++) {
            int number = Integer.parseInt(numbersStrSplit[i]);
            numbers[i] = number;
        }

        /* Get the number of questions */
        String numberOfQuestionsStr = reader.readLine();
        int numberOfQuestions = Integer.parseInt(numberOfQuestionsStr);

        int[] questions = new int[numberOfQuestions];

        // create an array holding all the answers - to be printed out later
        int[] answers = new int[numberOfQuestions];

        /* Scan for a question and find and store the frequency value */
        for (int i = 0; i < numberOfQuestions; i++) {
            String questionStr = reader.readLine();
            int question = Integer.parseInt(questionStr);
            questions[i] = question;
            //int frequency = frequencyFinder.findFrequencies(numbers, question);
            // store the answer in an array
            //answers[i] = frequency;
        }

        answers = frequencyFinder.findFrequencies(numbers, questions);

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
     * Helper method to calculate the frequencies of each number in the
     * given array against an array of numbers
     * </p>
     * @param array The given array of numbers
     * @param numbers The numbers, each of whose freqeuency of occurence in the
     *                given array is to be found
     * @return array of frequencies, with frequency at each index corresponding
     *          to the number in the same index of the numbers array
     */
    private int[] findFrequencies(int[] array, int[] numbers) {

        int[] frequencies = new int[numbers.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (array[i] == numbers[j]) {
                    frequencies[j]++;
                }
            }
        }

        return frequencies;

    }



}
