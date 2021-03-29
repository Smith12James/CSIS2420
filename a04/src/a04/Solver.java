package a04;

import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import org.w3c.dom.ranges.RangeException;

public class Solver {
    private Board current; //maybe to test the current board in the while loop
    private int moves;

    MinPQ<Board> pq = new MinPQ<Board>(); //neighboring nodes
    List<Board> path = new ArrayList<Board>(); //past boards added to a path

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if(initial.equals(null)) { throw new NullPointerException("Board Cannot be NULL"); }
        if(!initial.isSolvable()) { throw new IllegalArgumentException("Unsolvable Board"); }

        this.current = boardCopy(initial); //Making a copy bc good idea
        path.add(current); //Starting the path off with the first board
        this.moves = 0; //No moves made yet
        solution();

    }

    // min number of moves to solve initial board
    public int moves() {
        if(this.moves == 0) { return 0; }
        return this.moves - 1;
    }

    //This should return the size of the closed list or the amount of past nodes in our path.
    private int traversalNumber() { return path.size(); }

    // an int to determine the priority of a path
    private int score(Board neighbor) { return moves + neighbor.hamming(); }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){

        while(!current.isGoal()) {

            Board lowestBoard = current; //Board to be the chosen one
            int lowestCost = 0; //cost of this chosen board

            for(Board neighbor : current.neighbors()) { //iterating through the returned queue from Board.neighbors
                if(score(neighbor) < lowestCost || lowestCost == 0) { //comparing costs or setting the initial cost
                    lowestBoard = neighbor; //reassigning
                    lowestCost = score(neighbor); //reassigning
                    int prevLowestCost = lowestCost;
                    moves++; //increasing moves
//                    System.out.println(neighbor.toString());
//                    System.out.println(moves); // check infinite loop
//                    System.out.println(current.hamming());
//                    System.out.println();
//                    System.out.println();
                    if(this.moves >= 500) { throw new BufferOverflowException(); }
                    if(current.hamming() == 0) { path.add(current); return path; };
                }
            }
            current = lowestBoard;
            path.add(lowestBoard);

        }
        return path;

    }

    //Makes a copy of the board bc good idea
    private Board boardCopy(Board board) {
        //the old might of just made a reference to the same board
        Board board1 = new Board(board.board);
        return board1;

    }

    // solve a slider puzzle (given below)
    public static void main(String[] args){

        In in = new In("src/a04/puzzle04.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}