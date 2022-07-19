/**
 * @author Cameron Vandermeersch (Updated Version From Source Code)
 * @version 1.1
 * Date: July 12, 2022
 */
import java.util.*;

public class TicTacToeGame {
  public static Scanner scnr = new Scanner(System.in);

  public static boolean gameWonChecker(char[][] boardGame, int userTurn, char userSymbol, char compSymbol) {
    char symbolCheck = ' ';
    int rowCheck;
    int colCheck;
    boolean gameWon = false;
    if (userTurn == 0) {
      symbolCheck = userSymbol;
    } else {
      symbolCheck = compSymbol;
    }
    for (colCheck = 0; colCheck < boardGame[0].length && !gameWon; colCheck++) {
      for (rowCheck = 0; rowCheck < boardGame.length; rowCheck++) {
        if (boardGame[rowCheck][colCheck] != symbolCheck)
          break;
      }
      if (rowCheck == boardGame.length)
        gameWon = true;
    }

    for (rowCheck = 0; rowCheck < boardGame.length && !gameWon; rowCheck++) {
      for (colCheck = 0; colCheck < boardGame[0].length; colCheck++) {
        if (boardGame[rowCheck][colCheck] != symbolCheck)
          break;
      }
      if (colCheck == boardGame[0].length)
        gameWon = true;
    }

    if (!gameWon) {
      for (rowCheck = 0; rowCheck < boardGame.length; rowCheck++) {
        if (boardGame[rowCheck][boardGame.length - 1 - rowCheck] != symbolCheck)
          break;
      }
      if (rowCheck == boardGame.length)
        gameWon = true;
    }

    if (!gameWon) {
      for (rowCheck = 0; rowCheck < boardGame.length; rowCheck++) {
        if (boardGame[rowCheck][rowCheck] != symbolCheck)
          break;
      }
      if (rowCheck == boardGame.length)
        gameWon = true;
    }
    return gameWon;
  }

  public static void computerMove(char[][] gameBoard, char computerSymbol) {
    for (int compRow = 0; compRow < gameBoard.length; compRow++) {
      for (int compColumn = 0; compColumn < gameBoard[0].length; compColumn++) {
        if (gameBoard[compRow][compColumn] == ' ') {
          gameBoard[compRow][compColumn] = computerSymbol;
          return;
        }
      }
    }
  }

  public static void userPlaceSelection(char[][] gameBoard, char userSymbol) {
    System.out.println("\nPlease Enter The Row and Column Indices: ");
    System.out.print("Row: ");
    int rowIndex = scnr.nextInt();
    System.out.print("Column: ");
    int colIndex = scnr.nextInt();

    while (gameBoard[rowIndex][colIndex] != ' ') {
      System.out.print("\n!! The Spot is Already Filled.\nPlease Enter The Row and Column Indices: \n");
      System.out.print("Row: ");
      rowIndex = scnr.nextInt();
      System.out.print("Column: ");
      colIndex = scnr.nextInt();
    }
    gameBoard[rowIndex][colIndex] = userSymbol;
  }

  public static void displayBoard(char[][] gameBoard) {
    int numberOfColumns = gameBoard[0].length;
    int numberOfRows = gameBoard.length;
    System.out.println();
    System.out.print("     ");
    for (int outline = 0; outline < numberOfColumns; outline++) {
      System.out.print(outline + "   ");
    }
    System.out.println();
    System.out.println();

    for (int row = 0; row < numberOfRows; row++) {
      System.out.print(row + "  ");
      for (int column = 0; column < numberOfColumns; column++) {
        if (column != 0) {
          System.out.print("|");
        }
        System.out.print(" " + gameBoard[row][column] + " ");
      }
      System.out.println();
      if (row != (numberOfRows - 1)) {
        System.out.print("   ");
        for (int x = 0; x < numberOfColumns; x++) {
          if (x != 0)
            System.out.print("+");
          System.out.print("---");
        }
        System.out.println();
      }
    }
    System.out.println();
  }

  public static void resetBoard(char[][] gameBoard) {
    for (int rowReset = 0; rowReset < gameBoard.length; rowReset++)
      for (int columnReset = 0; columnReset < gameBoard[0].length; columnReset++)
        gameBoard[rowReset][columnReset] = ' ';
  }

  public static void main(String[] args) {
    final int SIZE = 3;
    int userTurn;
    int boardSizeLeft = SIZE * SIZE;
    char[][] boardGame = new char[SIZE][SIZE];
    int gameWinner = -1;
    boolean turnDone = false;
    System.out.println("=*=*= Welcome To The TIC-TAC-TOE Game!! =*=*=");
    resetBoard(boardGame);
    displayBoard(boardGame);
    System.out.print("Which Symbol Would You Like To Play, \"X\" or \"O\"? ");
    char userSymbol = scnr.next().toLowerCase().charAt(0);
    char compSymbol = (userSymbol == 'x') ? 'o' : 'x';
    System.out.println();
    userSymbol = Character.toUpperCase(userSymbol);
    compSymbol = Character.toUpperCase(compSymbol);
    System.out.println("You are playing with " + userSymbol + " and Computer is playing with " + compSymbol);
    System.out.println();
    System.out.print("Would You Like To Go First (y/n)? ");
    char userResponse = scnr.next().toLowerCase().charAt(0);

    if (userResponse == 'y') {
      userTurn = 0;
      userPlaceSelection(boardGame, userSymbol);
    } else {
      userTurn = 1;
      computerMove(boardGame, compSymbol);
    }
    displayBoard(boardGame);
    boardSizeLeft--;

    while (!turnDone && boardSizeLeft > 0) {
      turnDone = gameWonChecker(boardGame, userTurn, userSymbol, compSymbol);
      if (turnDone)
        gameWinner = userTurn;
      else {
        userTurn = (userTurn + 1) % 2;
        if (userTurn == 0) {
          userPlaceSelection(boardGame, userSymbol);
        } else {
          computerMove(boardGame, compSymbol);
        }
        displayBoard(boardGame);
        boardSizeLeft--;
      }
    }
    if (gameWinner == 0) {
      System.out.println("Congratulations, You Won! :)");
    } else if (gameWinner == 1) {
      System.out.println("Sorry, You Lost! :( ");
    } else {
      System.out.println("It's a Tie Game!");
    }
  }
}
