package a04;

import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board{

    int size;
    int[][] board;
    int[][] solution;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] a) {
        this.size = a.length;
        this.board = new int[size][size];
        solutionBoard();
        //Copying the passed array to the new array
        for(int i=0; i<size; i++) {
            for(int j=0; j< size; j++) {
                this.board[i][j]=a[i][j];
            }
        }
    }

    /**
     * Creates a board to be used as the goal board
     */
    private void solutionBoard() {

        /*assigns count to every spot on the board for a solution board
         *
         * {1,2,3}
         * {4,5,6}
         * {7,8,0}
         */
        solution = new int[size][size];
        int count = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(count == size*size) solution[i][j] = 0;
                else solution[i][j] = count;
                count++;
            }
        }
    }

    // board size N
    public int size() {
        return size;
    }

    // number of blocks out of place
    public int hamming() {

        int outOfPlace = 0; //number of slots that are incorrect
        int number; //the number to be compared
        int row, col;

        //for loop to iterate through the array to find which slots are incorrect
        for (int i = 1; i < size*size; i++) {
            row = i / size;
            col = i % size;
            number = board[row][col];
            if(number != i + 1) {
                outOfPlace++;
            }
        }
        return outOfPlace;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()  {
        int distance = 0; // this will be the sums of all disances
        int row1, col1; //row and col of mixed board
        int row2 = 0, col2 = 0; //row and col of solution board;
        int number; //number that we are comparing

        /*
         * n | / | %
         * 0   0   0
         * 1   0   1
         * 2   0   2
         * 3   1   0
         * 4   1   1
         * 5   2   2
         * 6   2   0
         */

        for (int i = 0; i < size*size; i++) {
            row1 = i / size;
            col1 = i % size;
            number = board[row1][col1];
            if(number == 0) continue; // dont count the zero


            for (int j = 0; j < size*size; j++) {
                if(solution[j / size][j % size] == number) {
                    row2 = j/size;
                    col2 = j%size;
                }
            }
            distance += Math.abs((row2-row1)) + Math.abs((col2-col1));
        }
        return distance;
    }

    // is this board the goal board?
    public boolean isGoal()  {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] != solution[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // is this board solvable?
    // this is based of inversions that checks for the pairs of inversions
    //https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable
    public boolean isSolvable()
    {
        int zeroRow = 0;
        int previous = 0;
        int inv_count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if(board[i][j] == 0) zeroRow = j;
                previous = board[i][j];
                // Value 0 is used for empty space
                for (int k = i; k < size; k++) {
                    for (int m = j; m < size; m++) {
                        if (previous > board[k][m]) {
                            inv_count++;
                        }
                    }
                }
            }
        }

        if (size % 2 != 0 && inv_count % 2 == 0) return true; //Odd boards only need odd inversions
        if ((inv_count + zeroRow) % 2 == 0) return false; //Even boards need odd number of inversions + the row where 0 is
        else return false;
    }

    // does this board equal y?
    public boolean equals(Object y)     {
        Board yBoard = (Board) y;
        if (y == null || getClass() != y.getClass()
                || size != yBoard.size || !Arrays.deepEquals(board, yBoard.board))
            return false;
        if (this == y)
            return true;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors()  {
        Stack<Board> queue = new Stack<Board>();

        //check if i+-1 and j+-1 are available, if so then we can exch()
        int zColumn = 0; //Location of the 0 column
        int zRow = 0; // location of the 0 row
        boolean zeroLocation = false;

        //for loop to find where the zero is on the board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] == 0) {
                    zColumn = j;
                    zRow = i;
                    zeroLocation = true;
                    break;
                }
                if(zeroLocation) break;
            }
        }

        //There can be only 2-4 moves a board. These loops should determine if an exchange can be done
        Board boardCopy = new Board(board);
        boolean inBounds;

        inBounds = boardCopy.exch(zRow, zColumn, zRow - 1, zColumn);
        if(inBounds) queue.push(boardCopy);// Exchanges to the left

        boardCopy = new Board(board);
        inBounds = boardCopy.exch(zRow, zColumn, zRow + 1, zColumn);
        if(inBounds) queue.push(boardCopy); // Exchanges to the right

        boardCopy = new Board(board);
        inBounds = boardCopy.exch(zRow, zColumn, zRow, zColumn - 1); // Exchanges down one
        if(inBounds) queue.push(boardCopy);

        boardCopy = new Board(board);
        inBounds = boardCopy.exch(zRow, zColumn, zRow, zColumn + 1); // Exchanges up one
        if(inBounds) queue.push(boardCopy);

        return queue;
    }

    public boolean exch(int i, int j, int i2, int j2) {
        if (i2 < 0 || i2 >= size || j2 < 0 || j2 >= size) {
            return false;
        }

        int temp = board[i][j];
        board[i][j] = board[i2][j2];
        board[i2][j2] = 0;
        return true;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int[][] getGoal() {
        return solution;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        int[][] board1 = { { 0, 1, 2 }, { 4, 3, 5 }, { 7, 6, 8 } };
        int[][] board2 = {{2, 1}, {0,3}};
        int[][] board3 = {{3,7,11,0},{2,6,10,14},{1,5,13,15},{4,8,12,9}}; // {1 2 3 4}, {5 6 7 8}, {9 10 11 12}, {13 14 15 0}

        Board myBoard = new Board(board1);
        StdOut.println("----------");
        StdOut.println("Board: ");
        StdOut.println(myBoard);
        StdOut.println("Neighbor: ");
        StdOut.println(myBoard.neighbors());
//        StdOut.println("Solution: ");
//        StdOut.println(new Board(myBoard.getGoal()));
//        StdOut.println("Manhattan Value: " + myBoard.manhattan());
//        StdOut.println("Hamming Value: " + myBoard.hamming());
//        StdOut.println();

        Board myBoard3 = new Board(board3);
        StdOut.println("----------");
        StdOut.println("Board: ");
        StdOut.println(myBoard3);
        StdOut.println("Neighbor: ");
        StdOut.println(myBoard3.neighbors());
//        StdOut.println("Solution: ");
//        StdOut.println(new Board3(myBoard3.getGoal()));
//        StdOut.println("Manhattan Value: " + myBoard3.manhattan());
//        StdOut.println("Hamming Value: " + myBoard3.hamming());
//        StdOut.println();

        Board myBoard2 = new Board(board2);
        StdOut.println("----------");
        StdOut.println("Board: ");
        StdOut.println(myBoard2);
        StdOut.println("Neighbor: ");
        StdOut.println(myBoard2.neighbors());
//        StdOut.println("Solution: ");
//        StdOut.println(new Board(myBoard2.getGoal()));
//        StdOut.println("Manhattan Value: " + myBoard2.manhattan());
//        StdOut.println("Hamming Value: " + myBoard2.hamming());
//        StdOut.println();
    }

}