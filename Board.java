/****************************************************************************************
 * Compilation: javac Board.java
 * Execution:
 * Dependencies: java.util, java.lang stdlib.jar
 *
 * This program creates an immutable data type for solving 8-puzzle
 * problem.
****************************************************************************************/
import java.util.Arrays;

public class Board {

    private int N;                                  // dimension of board
    private int[] board;                         // board of interest

    public Board(int[][] blocks)  {         // construct a board from an N-by-N array of blocks
        N = blocks[0].length;
        board = new int[N * N];
        for (int i = 0; i < N; i++)           // blocks[i][j]: block in row i, column j
            for (int j = 0; j < N; j++)
                board[i * N + j] = blocks[i][j];
    }

    private Board(int[] board) {            // private constructor useful in twin()
        N = (int) Math.sqrt(board.length);
        this.board = new int[board.length];
        for (int i = 0; i < board.length; i++)
            this.board[i] = board[i];
    }

    public int dimension() {                 // board dimension N
        return N;
    }

    public int hamming() {                  // number of blocks out of place
        int count = 0;
        for (int i = 0; i < N * N; i++)      // compare board[1] through board[N^2-1] with goal
                if (board[i] != i + 1 && board[i] != 0)                  // count for blocks in wrong place
                    count++;
        return count;
    }

    public int manhattan() {               // sum of Manhattan distances between blocks and goal
        int sum = 0;
        for (int i = 0; i < N * N; i++)
            if (board[i] != i + 1 && board[i] != 0)
                sum += manhattan(board[i], i);
        return sum;
    }

    private int manhattan(int goal, int current) {  // return manhattan distance of a misplaced block
        int row, col;                                                // row and column distance from the goal
        row = Math.abs((goal - 1) / N - current / N);              // row difference
        col = Math.abs((goal - 1) % N - current % N);             // column difference
        return row + col;
    }

    public boolean isGoal() {              // is this board the goal board?
        for (int i = 0; i < N * N - 1; i++)
             if (board[i] != i + 1) 
                 return false;
        return true;
    }

    public Board twin() {                  // a board obtained by exchanging two adjacent blocks in the same row
        Board twin;
        if (N == 1)  return null;                        // check if twin board exists
        twin = new Board(board);

        if (board[0] != 0 && board[1] != 0)
            exch(twin, 0, 1);                // if the first two blocks in first row is not empty, exchange them.
        else
            exch(twin, N, N + 1);  // otherwise, exchange the first two blocks on second row.
        return twin;
    }

    private Board exch(Board a, int i, int j) { // exchange two elements in the array
        int temp = a.board[i];
        a.board[j] = a.board[i];
        a.board[i] = temp;
        return a;
    }

    public boolean equals(Object y) {      // does this board equal y?
        if (y == this)  return true;
        if (y == null)  return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return Arrays.equals(this.board, that.board);
    }

    public Iterable<Board> neighbors() {    // all neighboring boards
        int index = 0;                               // record the position of empty block
        boolean found = false;                       // if empty block is found
        Board neighbor;
        Queue<Board> q = new Queue<Board>();

        for (int i = 0; i < board.length; i++)    // search for empty block
            if (board[i] == 0) {
                index = i;
                found = true;
                break;
            }
        if (!found)  return null;

        if (index / N != 0) {                      // if not first row
            neighbor = new Board(board);
            exch(neighbor, index, index - N);  // exchange with upper block
            q.enqueue(neighbor);
        }

        if (index / N != (N - 1)) {               // if not last row
            neighbor = new Board(board);
            exch(neighbor, index, index + N);  // exchange with lower block
            q.enqueue(neighbor);
        }

        if ((index % N) != 0) {                        // if not leftmost column
            neighbor = new Board(board);
            exch(neighbor, index, index - 1);  // exchange with left block
            q.enqueue(neighbor);
        }

        if ((index % N) != N - 1) {                          // if not rightmost column
            neighbor = new Board(board);
            exch(neighbor, index, index + 1);  // exchange with left block
            q.enqueue(neighbor);
        }

        return q;
    }

    public String toString() {              // string representation of the board
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < board.length; i++) {
            s.append(String.format("%2d ", board[i]));
            if (i % N == 0)
                s.append("\n");
        }
        return s.toString();
    }
}


