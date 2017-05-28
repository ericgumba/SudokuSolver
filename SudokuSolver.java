package sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ericgumba on 5/23/17.
 */
public class SudokuSolver {

  static BufferedReader reader;

  private StringTokenizer token;


  List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

  private ArrayList<ArrayList<ArrayList<Integer>>> candidateSolutions;

  public int[][] Algorithm(BufferedReader sudokuReader) throws IOException{

    int board[][] = new int[9][9];
      try {
        String text = null;
        String token;
        int i = 0;
        int j = 0;
        while (( text = reader.readLine()) != null) {

          this.token = new StringTokenizer(text);
          while (this.token.hasMoreTokens()) {
            if (!(token = this.token.nextToken()).equals(" ")) {
              board[i][j] = Integer.parseInt(token);
              j++;
            }
          }
          i++;
          j = 0;
        }
      } catch (Exception e){
        System.out.println(" -_- ");
      }
      return board;
  }


  //input, number to be checked, row and column of the square
  // being examined
  public boolean potentialCandidate(int[][] board, int candidate, int row, int column){

    if ( row <= 2 && column <= 2 ){
      for (int i = 0; i <= 2; i++){
        for (int j = 0; j <= 2; j++){
          if ( board[i][j] == candidate )
            return false;
        }
      }
    } else if ( row <= 2 && column <= 5) {
      for (int i = 0; i <= 2; i++) {
        for (int j = 3; j <= 5; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }

    } else if ( row <= 2 && column <= 8) {
      for (int i = 0; i <= 2; i++) {
        for (int j = 6; j <= 8; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }

    } else if ( row <= 5 && column <= 2 ) {
      for (int i = 3; i <= 5; i++) {
        for (int j = 0; j <= 2; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }

    } else if ( row <= 5 && column <= 5 ) {
      for (int i = 3; i <= 5; i++) {
        for (int j = 3; j <= 5; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }
    } else if ( row <= 5 && column <= 8 ){
      for (int i = 3; i <= 5; i++) {
        for (int j = 6; j <= 8; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }
    }  else if ( row <= 8 && column <= 2 ) {
      for (int i = 6; i <= 8; i++) {
        for (int j = 0; j <= 2; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }
    } else if ( row <= 8 && column <= 5 ) {
      for (int i = 6; i <= 8; i++) {
        for (int j = 3; j <= 5; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }

    } else if ( row <= 8 && column <= 8 ){
      for (int i = 6; i <= 8; i++) {
        for (int j = 6; j <= 8; j++) {
          if (board[i][j] == candidate)
            return false;
        }
      }
    }

    for (int i = 0; i < 9; i++){
      if ( candidate == board[row][i] ) {
        return false;
      }
    }
    for (int i = 0; i < 9; i++) {
      if ( candidate == board[i][column] ) {
        return false;
      }
    }
    return true;


  }

  public int[][] solve(int[][] board){
    return solved(board, 0, 0);
  }

  public int[][] solved(int[][] board, int row, int column){

    // check to see if the square already has a number


    boolean noMore = false;

      while (board[row][column] != 0 && (column != 8 || row != 8)) {
        if (column < 8) {
          column++;
        } else if (row < 8) {
          row++;
          column = 0;
        }
      }

      if (column == 8 && row == 8 && board[row][column] != 0) {
        return printSolution(board);
      } else
      for (int i = 1; i < 10; i++) {
        if (potentialCandidate(board, i, row, column)) {

          board[row][column] = i;

          if (column != 8 && row != 8) {
            return printSolution(board);
          } else if (column < 8) {
            solved(board, row, column + 1);
          } else {
            solved(board, row + 1, 0);
          }
        }
        board[row][column] = 0;
      }
      return board;

  }

  public int[][] printSolution(int[][] board){

    for(int i = 0; i < board.length; i++){
      System.out.println("\n \n ROW " + (i+1));
      for (int j = 0; j < board.length; j++){
        System.out.print(board[i][j] + " ");
      }
    }

    System.out.println("\n\n ");

    return board;
  }

}
