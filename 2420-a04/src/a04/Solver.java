package a04;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private Board board;
    private static int[][] solution;
    private int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
         this.board = initial;

    }

    // min number of moves to solve initial board
    public int moves() {
        int ham = this.board.hamming();
        return this.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
        if(!this.board.isSolvable()) {
            throw new IllegalArgumentException();
        }

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

        return q;

    }

    private Board boardCopy(Board board) {
        Board board1 = board;

        return board;

    }

    // solve a slider puzzle (given below)
    public static void main(String[] args){
        int[][] board1 = { { 0, 1, 2 }, { 4, 3, 5 }, { 7, 6, 8 } };
        Board board = new Board(board1);

        for(int i = 0; i < board1.length; i++) {
            for(int j = 0; j < board1.length; j++) {
                System.out.print(board.solution[i][j] + ", ");
            }
            System.out.println();
        }

    }
}