import java.util.*;
public class TicTacToe {
   enum gameState {
      NOT_ENDED, TIE, O_VICTORY, X_VICTORY
   }
   
   private char[][] grid = new char[3][3];
   private gameState state;
   
   public TicTacToe(){
      for (int i = 0; i < 3; ++i) {
         for (int j = 0; j < 3; ++j) {
            grid[i][j] = '*';
         }
      }
      state = gameState.NOT_ENDED;
   }
   public boolean ifValid(int x, int y) {
      if (x < 0 || x > 2)return false;
      if (y < 0 || y > 2)return false;
      if (grid[x][y] == '*')return true;
      else return false;
   }
   public void playRound(int x, int y, boolean ifX) {
      if (ifX == true) {
         grid[x][y] = 'X';
      }
      else {
         grid[x][y] = 'O';
      }
      updateState(x, y);
   }
   private void updateState(int x, int y) {
      boolean ifDiffers = false;
      for (int i = 0; i < 3; ++i) {
         if(grid[x][i] != grid[x][y]){
            ifDiffers = true;
         }
      }
      if (!ifDiffers) {
         if(grid[x][y] == 'X')state = gameState.X_VICTORY;
         else if(grid[x][y] == 'O')state = gameState.O_VICTORY;
         return;
      }
      
      ifDiffers = false;
      for (int i = 0; i < 3; ++i) {
         if(grid[i][y] != grid[x][y]){
            ifDiffers = true;
         }
      }
      if (!ifDiffers) {
         if(grid[x][y] == 'X')state = gameState.X_VICTORY;
         else if(grid[x][y] == 'O')state = gameState.O_VICTORY;
         return;
      }
      
      ifDiffers = false;
      if (x == y) {
         for (int i = 0; i < 3; ++i) {
            if(grid[i][i] != grid[x][y]){
               ifDiffers = true;
            }
         }
         if (!ifDiffers) {
            if(grid[x][y] == 'X')state = gameState.X_VICTORY;
            else if(grid[x][y] == 'O')state = gameState.O_VICTORY;
            return;
         }
      }
      else if (x == (2 - y)) {
         for (int i = 0; i < 3; ++i) {
            if(grid[i][2 - i] != grid[x][y]){
               ifDiffers = true;
            }
         }
         if (!ifDiffers) {
            if(grid[x][y] == 'X')state = gameState.X_VICTORY;
            else if(grid[x][y] == 'O')state = gameState.O_VICTORY;
            return;
         }
      }
      
      int asteriskCount = 0;
      for (int i = 0; i < 3; ++i) {
         for (int j = 0; j < 3; ++j) {
            if (grid[i][j] == '*') {
               asteriskCount++;
            }
         }
      }
      if (asteriskCount == 0) {
         state = gameState.TIE;
      }
   }
   public gameState getState() {
      return state;
   }
   public void printGrid() {
      System.out.println("  1 2 3 Y");
      for (int i = 0; i < 3; ++i) {
         System.out.print((i + 1) + " ");
         for (int j = 0; j < 3; ++j) {
            System.out.print(grid[i][j] + " ");
         }
         System.out.println();
      }
      System.out.println("X");
   }
   public static void main(String[] args) {
      int xCoordinate;
      int yCoordinate;
      int round = 1;
      boolean turnOfX;
   
      System.out.println("Rules For TIC-TAC-TOE:");
      System.out.println("The game is played on a grid that's 3 squares by 3 squares.");
      System.out.println("You are X, your friend is O. Players take turns putting their marks in empty squares(denoted by asterisks.");
      System.out.println("The first player to get 3 of his/her marks in a row (up, down, across, or diagonally) is the winner.");
      System.out.println("When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
      System.out.println("Enter X to start, or any other key to quit.");
      Scanner scnr = new Scanner(System.in);
      if (scnr.nextLine().equals("X")) {
         TicTacToe game = new TicTacToe();
         do {
            System.out.print("\nEnter -1 to quit, or enter X and Y coordinates");
            if (round % 2 == 1) {
               System.out.println(" for X");
               turnOfX = true;
            }
            else {
               System.out.println(" for O");
               turnOfX = false;
            }
            
            game.printGrid();
            
            xCoordinate = scnr.nextInt();
            if (xCoordinate == -1) {
               break;
            }
            else {
               yCoordinate = scnr.nextInt();
               if (game.ifValid(xCoordinate - 1, yCoordinate - 1)) {
                  game.playRound(xCoordinate - 1, yCoordinate - 1, turnOfX);
                  if (game.getState() == gameState.TIE) {
                     game.printGrid();
                     System.out.println("You have tied, thanks for playing");
                  }
                  else if (game.getState() == gameState.O_VICTORY) {
                     game.printGrid();
                     System.out.println("O has won, thanks for playing");
                  } 
                  else if (game.getState() == gameState.X_VICTORY) {
                     game.printGrid();
                     System.out.println("X has won, thanks for playing");
                  }
                  round++;
               }
               else {
                  System.out.println("Invalid input please try again");
               }
            }
         }while(game.getState() == gameState.NOT_ENDED);
      }
   }
}