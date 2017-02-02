package largest_filled_region;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rabboni on 01/02/17.
 */
public class MatrixNode {


    public MatrixNode(int rowPosition, int columnPosition, int value, boolean visited) {
        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;
        this.value = value;
        this.visited = visited;
        neighbours = new ArrayList<MatrixNode>();
    }

    public int rowPosition;

    public int columnPosition;

    public int value;

    public List<MatrixNode> neighbours;

    public boolean visited;

}
