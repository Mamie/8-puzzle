8-puzzle
============
This is the week 4 assignment of [Coursera Algorithm, part I] (https://www.coursera.org/course/algs4partI) class.

Summary
---------
This program implements [A* search algorithm] (http://en.m.wikipedia.org/wiki/A*_search_algorithm) to solve 8-puzzle problem (a type of slider puzzle). It uses the sum of moves to current step and Manhattan priority function as cost function. A priority queue of search node containing number of moves, current board and previous search node is created. For each move, the search node with minimum cost is dequeued and neighboring nodes of this search node are then inserted into the priority queue. The sequence of moves using fewest number of moves to solve the puzzle is obtained when the goal board is ultimately dequeued (total number of moves is always at least its priority).

Board
------
Board data type represents a priority queue of search node. Its API is as follows:

      public class Board {
          public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                                 // (where blocks[i][j] = block in row i, column j)
          public int dimension()                 // board dimension N
          public int hamming()                   // number of blocks out of place
          public int manhattan()                 // sum of Manhattan distances between blocks and goal
          public boolean isGoal()                // is this board the goal board?
          public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
          public boolean equals(Object y)        // does this board equal y?
          public Iterable<Board> neighbors()     // all neighboring boards
          public String toString()               // string representation of the board 
      }



Solver
------
Check whether the board is solvable by simultaneously solving a twin board derived from initial board by exchanging two adjacent blocks. Test client solver is in the main function. API is shown below:

      public class Solver {
          public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
          public boolean isSolvable()             // is the initial board solvable?
          public int moves()                      // min number of moves to solve initial board; -1 if no solution
          public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
          public static void main(String[] args)  // solve a slider puzzle 
      }



Details of the assignment requirement with regard to the running time and space limitation can be found [here]
(http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html). 

In addition, some enrichment hints can be found [here] (http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html). 
