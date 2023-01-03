import java.util.Scanner;

public class TicTacToe {
  private char[][] board;     // A 2D array to represent the board
  private char currentPlayer; // A variable to store the current player
  private Scanner input;      // A Scanner to read input from the user
  public TicTacToe() {
    input = new Scanner(System.in);
    board = new char[3][3]; // 3x3 board
    currentPlayer = 'X'; // starting user is X
    for (int i = 0; i < 3; i++) { // Initialize the board to be empty
      for (int j = 0; j < 3; j++) {
        board[i][j] = ' ';
      }
    }
  }
  
  public void play() {
    // Keep playing until the game is over
    while (!isGameOver()) {
      printBoard();
      makeMove(); // Check if the current player has won
      if (checkForWin()) { // Announce the winner and end the game
        System.out.println("Player " + currentPlayer + " wins!");
        return;
      } // Switch players
      currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    } // If the game is over and no one has won, it's a draw
    System.out.println("It's a draw!");
  }
  
  private void makeMove() {
    System.out.print("Enter row (0, 1, 2) for player " + currentPlayer + ": ");
    int row = input.nextInt();
    System.out.print("Enter column (0, 1, 2) for player " + currentPlayer + ": ");
    int col = input.nextInt();
    if (row < 0 || row > 2 || col < 0 || col > 2) { // Check if the chosen spot is within the bounds of the board
      System.out.println("Invalid move. Try again.");
      makeMove();
    } else if (board[row][col] == ' ') { // Make the move
      board[row][col] = currentPlayer;
    } else { // The spot is not empty, so prompt the user to try again
      System.out.println("That spot is already taken. Try again.");
      makeMove();
    }
  }
  
  private boolean isGameOver() { // Check if someone has won
    if (checkForWin()) { 
      return true;
    }
    for (int i = 0; i < 3; i++) { // Check if the board is full
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') { // There's an empty spot, so the game is not over
          return false;
        }
      }
    } // The board is full and no one has won, so it's a draw
    return true;
  }
  
  private boolean checkForWin() {
    for (int i = 0; i < 3; i++) { // Check rows
      if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
        return true;
      }
    }

    for (int j = 0; j < 3; j++) { // Check columns
      if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
        return true;
      }
    }  // Check diagonals
    if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
      (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)
    ) {
      return true;
    }
    return false;    // If none of the above conditions are true, then no one has won
  }
  
  private void printBoard() {
    System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
    System.out.println("    --+-+--");
    System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
    System.out.println("    --+-+--");
    System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    System.out.println("     0 1 2 ");
  }
  
  public static void main(String[] args) throws ArrayIndexOutOfBoundsException {
    TicTacToe game = new TicTacToe();
    game.play();
  }
}