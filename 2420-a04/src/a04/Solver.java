package a04;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Board current; //maybe to test the current board in the while loop
    private int moves;

    MinPQ<Board> open = new MinPQ<Board>(); //neighboring nodes
    List<Board> path = new ArrayList<Board>(); //past boards added to a path

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if(initial.equals(null)) throw new NullPointerException(
                "Board Cannot be NULL");

        if(!initial.isSolvable()) {
            throw new IllegalArgumentException("Unsolvable Board");
        }

        this.current = boardCopy(initial); //Making a copy bc good idea
        path.add(current); //Starting the path off with the first board
        this.moves = 0; //No moves made yet
        solution();

    }

    // min number of moves to solve initial board
    public int moves() {
        return this.moves;
    }

    //This should return the size of the closed list or the amount of past nodes in our path.
    public int traversalNumber() {
        return path.size();

    }

    // an int to determin the priority of a path
    public int score(Board neighbor) {
        return moves + neighbor.hamming();
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){

        while(!current.isGoal()) {

            Board lowestBoard = current; //Board to be the chosen one
            int lowestCost = 0; //cost of this chosen board
            for(Board neighbor : current.neighbors()) { //iterating through the returned queue from Board.neighbors
                if(score(neighbor) < lowestCost || lowestCost == 0) { //comparing costs or setting the initial cost
                    lowestBoard=neighbor; //reassigning
                    lowestCost = score(neighbor); //reassigning
                    moves++; //increasing moves
                    System.out.println(moves); //just so im not in an infinte loop
                }
            }
            current = lowestBoard;
            path.add(lowestBoard);

        }

    	/*
        Stack stack = new Stack();
        Queue q = new Queue();
        Board board1 = boardCopy(this.board);
        this.solution = board1.solution;
        q.enqueue(this.board);
        Stack neighbors = new Stack();
        neighbors.push(board1.neighbors());

        Board tempBoard = (Board) stack.pop();
        if(tempBoard.isGoal()) {
            q.enqueue(tempBoard);
            return q;
        }
		*/


//        for(int i = 0; i < stack.size(); i++) {
//            Board tempBoard = (Board) q.dequeue();
//            if(tempBoard.isGoal()) {
//                return q;
//
//            } else if() {
//                board1 = boardCopy(board1);
//
//            }
//        }
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
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
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