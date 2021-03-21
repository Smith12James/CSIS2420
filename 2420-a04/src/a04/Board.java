package a04;

import java.util.Arrays;

import edu.princeton.cs.algs4.MinPQ;

public class Board{

    int size;
    int[][] blocks;
    int[][] solution;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        size = blocks.length;
        this.blocks = blocks;

        //Copying the passed array to the new array
        for(int i=0; i<blocks.length; i++) {
            for(int j=0; j<blocks[i].length; j++) {
                this.blocks[i][j]=blocks[i][j];
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

        int count = 1;
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution[i].length; j++) {
                if(count == size*size) solution[i][j] = 0; // assign last position as 0
                else solution[i][j] = count; // set board position equal to index
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

        //for loop to iterate through the array to find which slots are incorrect
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if(solution[i][j] != blocks[i][j]) outOfPlace++;
            }
        }
        return outOfPlace;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan()  {
        return hamming() + size(); //THIS COULD BE WRONG AND PROBABLY IS WRONG :)
    }

    // is this board the goal board?
    public boolean isGoal()  {
        return (hamming()==0);
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
                if (blocks[j][i] > 0 &&
                        blocks[j][i] > blocks[i][j])
                    inv_count++;
                if (blocks[i][j] == 0)
                    zeroRow = i;
            }
        }

        if (inv_count % 2 == 1 && size % 2 == 1) return false; //Odd boards only need odd inversions
        if ((inv_count + zeroRow) % 2 == 0) return false; //Even boards need odd number of inversions + the row where 0 is

        return true;
    }

    // does this board equal y? lmao this is all wrong
    public boolean equals(Object y)     {
        Board yBoard = (Board) y;
        if (y == null || getClass() != y.getClass()
                || size != yBoard.size || !Arrays.deepEquals(blocks, yBoard.blocks))
            return false;
        if (this.blocks == y)
            return true;
        return (blocks.equals(y));
    }

    // all neighboring boards
    public Iterable<Board> neighbors()  {
        MinPQ<Board> queue = new MinPQ<Board>();

        //check if i+-1 and j+-1 are available, if so then we can exch()
        int zColumn = 0; //Location of the 0 column
        int zRow = 0; // location of the 0 row

        //for loop to find where the zero is on the board
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                if(blocks[i][j] == 0) {
                    zColumn = i;
                    zRow = j;
                }
            }
        }
        //There can be only 2-4 moves a board. These loops should determin if an exchange can be done
        //Also the if statements can be wrong in this i kinda fired from the hip

        if(zColumn+1 != 3 ) queue.insert(exch(blocks, zColumn, zRow, zColumn + 1, zRow)); // Exchanges to the right
        if(zColumn-1 != -1) queue.insert(exch(blocks, zColumn, zRow, zColumn - 1, zRow)); // Exchanges to the left
        if(zRow+1 != 3) queue.insert(exch(blocks, zColumn, zRow, zColumn, zRow + 1)); // Exchanges up one
        if(zRow-1 != -1) queue.insert(exch(blocks, zColumn, zRow, zColumn, zRow - 1)); // Exchanges down one

        //This could possibly be another solution

        //if(zColumn / size == ) I was going somewhere with this but kidna lost it
        //if(zRow % size == )

        return queue;
    }

    public Board exch(int[][] a, int r1, int c1, int r2, int c2) {
        int[][] temp = new int[size][size];

        for(int i=0; i<a.length; i++) {
            for(int j=0; j<a[i].length; j++) {
                temp[i][j]=a[i][j];
            }
        }

        temp[c1][r1] = a[c2][r2];


        return new Board(temp);
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(size + "\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        
    }
}