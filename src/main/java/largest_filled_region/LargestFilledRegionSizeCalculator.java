package largest_filled_region;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by rabboni on 17/01/17.
 */

/**
 * This is a small program that for  a given matrix(2d array)
 * calculates the largest region of 1's. The matrix elements'
 * value can be 0 or 1.
 *
 * Region size is the number of elements(with value 1) that are connected.
 * Two neighbouring elements of the matrix are connected and form a region
 * if they are both 1. Neighbours can be up or down, left or right or across.
 *
 * The largest filled region in the example below is 9.
 *
 * [1 0 1 1
 *  0 0 0 1
 *  1 1 0 1
 *  1 0 1 1]
 *
 */
class LargestFilledRegionSizeCalculator {

    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the size of the row of the matrix: ");
        String rowInputLine = br.readLine();
        int m = Integer.parseInt(rowInputLine);
        System.out.println("Enter the size of the column of the matrix: ");
        String columnInputLine = br.readLine();
        int n = Integer.parseInt(columnInputLine);

        int[][] valuesMatrix = new int[m][n];

        System.out.println("Sample values for a row: 0 1 1 0");

        try{
            for(int i = 0; i < m; i ++){

                System.out.println("Enter the values for row " + i + ":");
                String[] rowValues = br.readLine().split(" ");

                for(int j = 0; j < n; j++){
                    valuesMatrix[i][j] = Integer.parseInt(rowValues[j]);
                }
            }
            System.out.println(calculateLargestRegionSize(valuesMatrix, m, n));
        }catch (NumberFormatException nfe){
            System.out.println("Invalid input. Try again.");
        }

    }


    public static int calculateLargestRegionSize(int[][] valuesMatrix, int rowSize, int columnSize){

        boolean[][] checkedStatusArray = initialiseCellCheckedStatusArray(rowSize, columnSize);

        int trailValue = 0;

        //For each element in the matrix
        for(int i = 0; i < rowSize; i ++){
            for(int j = 0; j < columnSize; j++){
                //If no trail has been followed via here already
                if(!checkedStatusArray[i][j] && valuesMatrix[i][j] == 1){

                    MatrixNode matrixNode = new MatrixNode(i,j,1,false);
                    int currentTrailValue = followTrail(valuesMatrix, checkedStatusArray, matrixNode);
                    if (currentTrailValue > trailValue){
                        trailValue = currentTrailValue;
                    }
                }
            }
        }

        System.out.println("Size of largest filled region is: " + trailValue);

        return trailValue;
    }

    /**
     * Recursive method that for a given element in the matrix at [x][y],
     * follows the neighbours with value 1. Trail stops when there is no
     * neighbour with value 1.
     * Returns the length of the trail.
     * @param valuesMatrix
     * @param checkedStatusArray
     * @param matrixNode
     * @return
     */
    private static int followTrail(int[][]valuesMatrix, boolean[][] checkedStatusArray, MatrixNode matrixNode){

        int trailLength = 1;
        checkedStatusArray[matrixNode.rowPosition][matrixNode.columnPosition] = true;

        matrixNode.visited = true;
        matrixNode = addFilledNeighbours(valuesMatrix, matrixNode);

        //for each filled neighbour
        for (MatrixNode neighbour : matrixNode.neighbours) {
            if (!checkedStatusArray[neighbour.rowPosition][neighbour.columnPosition]){
                trailLength = trailLength + followTrail(valuesMatrix, checkedStatusArray, neighbour);
            }
        }
        return trailLength;
    }

    /**
     * Instantiates nodes whose values are 1 and adds it to the list of neighbours for the
     * given matrix node. Neighbours whose values are 0 are not added.
     * @param valuesMatrix
     * @param matrixNode
     * @return
     */
    private static MatrixNode addFilledNeighbours(int[][] valuesMatrix, MatrixNode matrixNode){

        //This method has a lot of ifs, but the run time is O(1).
        int rowPosition = matrixNode.rowPosition;
        int columnPosition = matrixNode.columnPosition;

        int rowSize = valuesMatrix.length;
        int columnSize = valuesMatrix[0].length;

        if((rowPosition-1) >= 0){
            if (valuesMatrix[rowPosition-1][columnPosition] == 1){
                MatrixNode neighbourNode = new MatrixNode(rowPosition-1, columnPosition, 1, false);
                matrixNode.neighbours.add(neighbourNode);
            }
            if ((columnPosition-1) >= 0){
                if (valuesMatrix[rowPosition-1][columnPosition-1] == 1){
                    MatrixNode neighbourNode = new MatrixNode(rowPosition-1, columnPosition-1, 1, false);
                    matrixNode.neighbours.add(neighbourNode);
                }
                if (valuesMatrix[rowPosition][columnPosition-1] == 1){
                    MatrixNode neighbourNode = new MatrixNode(rowPosition, columnPosition-1, 1, false);
                    matrixNode.neighbours.add(neighbourNode);
                }
            }
            if ((columnPosition+1) <= columnSize-1){
                if (valuesMatrix[rowPosition-1][columnPosition+1] == 1){
                    MatrixNode neighbourNode = new MatrixNode(rowPosition-1, columnPosition+1, 1, false);
                    matrixNode.neighbours.add(neighbourNode);
                }
            }
        }

        if ((rowPosition+1) <= rowSize-1){
            if (valuesMatrix[rowPosition+1][columnPosition] == 1){
                MatrixNode neighbourNode = new MatrixNode(rowPosition+1, columnPosition, 1, false);
                matrixNode.neighbours.add(neighbourNode);
            }
            if ((columnPosition+1) <= columnSize-1){
                if (valuesMatrix[rowPosition+1][columnPosition+1] == 1){
                    MatrixNode neighbourNode = new MatrixNode(rowPosition+1, columnPosition+1, 1, false);
                    matrixNode.neighbours.add(neighbourNode);
                }
                if (valuesMatrix[rowPosition][columnPosition+1] == 1){
                    MatrixNode neighbourNode = new MatrixNode(rowPosition, columnPosition+1, 1, false);
                    matrixNode.neighbours.add(neighbourNode);
                }
            }
            if ((columnPosition-1) >= 0){
                if (valuesMatrix[rowPosition+1][columnPosition-1] == 1){
                    MatrixNode neighbourNode = new MatrixNode(rowPosition+1, columnPosition-1, 1, false);
                    matrixNode.neighbours.add(neighbourNode);
                }
            }
        }

        //Eventhough this condition is checked above, it is not checked in all cases
        if ((columnPosition-1) >= 0){
            if (valuesMatrix[rowPosition][columnPosition-1] == 1){
                MatrixNode neighbourNode = new MatrixNode(rowPosition, columnPosition-1, 1, false);
                matrixNode.neighbours.add(neighbourNode);
            }
        }
        if ((columnPosition+1) <= columnSize-1){
            if (valuesMatrix[rowPosition][columnPosition+1] == 1){
                MatrixNode neighbourNode = new MatrixNode(rowPosition, columnPosition+1, 1, false);
                matrixNode.neighbours.add(neighbourNode);
            }
        }

        return matrixNode;
    }

    /* Helper method to initialise the status of all elements in the corresponding matrix to false */
    private static boolean[][] initialiseCellCheckedStatusArray(int rowSize, int columnSize){

        boolean[][] statusArray = new boolean[rowSize][columnSize];

        for(int i = 0; i < rowSize; i ++){
            for(int j = 0; j < columnSize; j++){
                statusArray[i][j] = false;
            }
        }
        return statusArray;
    }

}
