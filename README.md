8-puzzle
============

Summary
---------
This program implements A* search algorithm to solve the 8-puzzle problem.

Board
------
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
          public String toString()               // string representation of the board (in the output format specified below)
      }



Solver
------
      public class Solver {
          public Solver(Board initial)            // find a solution to the initial board (using the A* algorithm)
          public boolean isSolvable()             // is the initial board solvable?
          public int moves()                      // min number of moves to solve initial board; -1 if no solution
          public Iterable<Board> solution()       // sequence of boards in a shortest solution; null if no solution
          public static void main(String[] args)  // solve a slider puzzle (given below)
      }



assignment requirement
> http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html

enrichment hint
> http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html
