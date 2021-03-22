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

        //Copying the passed array to the new array
        for(int i=0; i<size; i++) {
            for(int j=0; j< size; j++) {
                this.board[i][j]=a[i][j];
            }
        }
        solutionBoard();
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
        int inv_count = 0;
        for (int i = 0; i < size*size; i++) {
            for (int j = i + 1; j < size*size; j++) {

                // Value 0 is used for empty space
                //checks whether the number before is greater than the number after or an inversion
                if (board[j][i] > 0 &&
                        board[j][i] > board[i][j])
                    inv_count++;
                if (board[i][j] == 0)
                    zeroRow = i;
            }
        }

        if (inv_count % 2 == 1 && size % 2 == 1) return false; //Odd boards only need odd inversions
        if ((inv_count + zeroRow) % 2 == 0) return false; //Even boards need odd number of inversions + the row where 0 is

        return true;
    }

    // does this board equal y?
    public boolean equals(Object y)     {
        Board yBoard = (Board) y;
        if (y == null || getClass() != y.getClass()
                || size != yBoard.size || !Arrays.deepEquals(board, yBoard.board))
            return false;
        if (this.board == y)
            return true;
        return (board.equals(y));
    }

    // all neighboring boards
    public Iterable<Board> neighbors()  {
        Stack<Board> stack = new Stack<Board>();

        //check if i+-1 and j+-1 are available, if so then we can exch()
        int zColumn = 0; //Location of the 0 column
        int zRow = 0; // location of the 0 row

        // for loop to find where the zero is on the board
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(board[i][j] == 0) {
                    zColumn = i;
                    zRow = j;
                }
            }
        }

        int[][] mainBoard = this.board.clone();

        //There can be only 2-4 moves a board. These loops should determine if an exchange can be done
        if(zColumn + 1 != size ) stack.push(exch(createACopy(), zColumn, zRow, zColumn + 1, zRow)); // Exchanges to the right
        resetBoard(mainBoard);

        if(zColumn != 0) stack.push(exch(createACopy(), zColumn, zRow, zColumn - 1, zRow)); // Exchanges to the left
        resetBoard(mainBoard);

        if(zRow + 1 != size) stack.push(exch(createACopy(), zColumn, zRow, zColumn, zRow + 1)); // Exchanges up one
        resetBoard(mainBoard);

        if(zRow != 0) stack.push(exch(createACopy(), zColumn, zRow, zColumn, zRow - 1)); // Exchanges down one
        resetBoard(mainBoard);

        return stack;
    }

    private int[][] createACopy() {
        int[][] newBoard = this.board.clone();
        return newBoard;

    }

    private Board exch(int[][] a, int i, int j, int i2, int j2) {
        int temp1 = this.board[i][j];
        int temp2 = this.board[i2][j2];

        a[i2][j2] = temp1;
        a[i][j] = temp2;

        return new Board(a);
    }

    private Board resetBoard(int[][] a) {
        this.board = a.clone();

        return new Board(a);
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
        int[][] board1 = { { 0, 1, 2 }, { 4, 3, 5 }, { 7, 6, 8 } }; // 4
        int[][] board2 = {{2, 1}, {0,3}};

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