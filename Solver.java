/*******************************************************************************
* Compilation: javac Solver.java
* Execution:
* Dependencies: Board.java, algs4.jar, java.util, stdlib.jar
*
* This program creates an immutable data type that solves
* 8-puzzle problem using A* algorithm.
*******************************************************************************/
import java.util.Comparator;

public class Solver {
    private SearchNode goal;              
    
    private class SearchNode {             // A search node consists of the board, number of moves to reach
        private int moves;                 // this step and pointed to the previous search node
        private Board board;
        private SearchNode prev;

        public SearchNode(Board initial) {
            moves = 0;
            prev = null;
            board = initial;
        }
    }

    public Solver(Board initial) {           // find a solution to the initial board (using the A* algorithm)
        PriorityOrder order = new PriorityOrder();
        MinPQ<SearchNode> PQ = new MinPQ<SearchNode>(order);
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>(order);
        SearchNode Node = new SearchNode(initial);
        SearchNode twinNode = new SearchNode(initial);
        PQ.insert(Node);
        twinPQ.insert(twinNode);                // twin created to detect infeasible cases

        SearchNode min = PQ.delMin();
        SearchNode twinMin = twinPQ.delMin();

        while(!min.board.isGoal() && !twinMin.board.isGoal()) {

            for (Board b : min.board.neighbors()) {      
                if (min.prev == null || !b.equals(min.prev.board)) {   // check if move back this previous state
                    SearchNode n = new SearchNode(b);
                    n.moves = min.moves + 1;
                    n.prev = min;
                    PQ.insert(n);
                    }
            }
            
            for (Board b : twinMin.board.neighbors()) {
                if (twinMin.prev == null ||!b.equals(twinMin.prev.board)) {
                    SearchNode n = new SearchNode(b);
                    n.moves = twinMin.moves + 1;
                    n.prev = twinMin;
                    twinPQ.insert(n);
                    }
            }
             
             min = PQ.delMin();
             twinMin = twinPQ.delMin();
         }
         if (min.board.isGoal())  goal = min;
         else                     goal = null;                
    }

    private class PriorityOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            int pa = a.board.manhattan() + a.moves;
            int pb = b.board.manhattan() + b.moves;
            if (pa > pb)   return 1;
            if (pa < pb)   return -1;
            else              return 0;
        }
    }

    public boolean isSolvable() {            // is the initial board solvable?
        return goal != null;
    }

    public int moves() {                     // min number of moves to solve initial board; -1 if no solution
        if (!isSolvable())  return -1;
        else                   return goal.moves;
    }

    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if no solution
        if (!isSolvable())  return null;
        Stack<Board> s = new Stack<Board>();
        for (SearchNode n = goal; n != null; n = n.prev) 
            s.push(n.board);
        return s;
    }

    public static void main(String[] args) { // solve a slider puzzle
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
