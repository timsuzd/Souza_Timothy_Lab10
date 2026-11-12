import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];
    void main(){
        Scanner in = new Scanner(System.in);
        boolean valid = false;
        boolean done = false;
        int moveCount = 0;
        int gameCount = 0;
        int p1WinCount = 0;
        int p2WinCount = 0;
        int tieCount = 0;
        int row = 0;
        int col = 0;
        boolean keepPlaying = true;
        String input = "";
        String p1Marker = "";
        String p2Marker = "";
        String currentPlayer = "(X)";

        SafeInput.prettyHeader("Tic Tac Toe!");
        input = SafeInput.getRegExString(in,"Player 1, choose X or O", "^[XxOo]$");
        p1Marker = input.toUpperCase();
        if (p1Marker.equals("X")){
            p1Marker = "(X)";
            p2Marker = "(O)";
        }
        else if (p1Marker.equals("O")){
            p1Marker = "(O)";
            p2Marker = "(X)";
        }
        IO.println("Player 1 has chosen: " + p1Marker + " Player 2 is: " + p2Marker + " by default.\n");

        // Main game loop
        do{
            if (gameCount >= 1){
                p1Marker = switchPlayer(p1Marker);
                p2Marker = switchPlayer(p2Marker);
            }
            gameCount++;
            moveCount = 0;
            done = false;
            currentPlayer = "(X)";
            clearBoard(board);

            IO.println("Game number: " + gameCount);
            IO.println("Player 1 is: " + p1Marker + "  Player 2 is: " + p2Marker);
            IO.println("Player 1 Wins: " + p1WinCount);
            IO.println("Player 2 Wins: " + p2WinCount);
            IO.println("Tie Count: " + tieCount);
            display(board);

            // A turn is only completed when the chosen space is empty
            do{
                if (currentPlayer.equals(p1Marker)){
                    IO.println("Player 1's turn " + currentPlayer);
                }
                else {
                    IO.println("Player 2's turn " + currentPlayer);
                }

                do{
                    row = SafeInput.getRangedInt(in,"Enter row",1,3) - 1;
                    col = SafeInput.getRangedInt(in, "Enter column", 1, 3) - 1;
                    valid = isValidMove(row,col);
                    if (!valid){
                        IO.println("That space is already taken. Try again.");
                    }
                }while(!valid);

                board[row][col] = currentPlayer;
                moveCount++;
                display(board);
                IO.println("Move count: " + moveCount);

                if (moveCount >= 5 && isWin(currentPlayer)){
                    if (currentPlayer.equals(p1Marker)){
                        IO.println("Player 1 wins!");
                        p1WinCount++;
                    }
                    else {
                        IO.println("Player 2 wins!");
                        p2WinCount++;
                    }
                    done = true;
                }
                else if (moveCount == 9 && isTie()){
                    IO.println("It's a tie!");
                    tieCount++;
                    done = true;
                }
                else {
                    currentPlayer = switchPlayer(currentPlayer);
                }
            }while(!done);

            IO.println("___________________________________");
            IO.println("Player 1 Wins: " + p1WinCount);
            IO.println("Player 2 Wins: " + p2WinCount);
            IO.println("Tie Count: " + tieCount);
            keepPlaying = SafeInput.getYNConfirm(in,"Would you like to play again?");
            IO.println("___________________________________");
        }while(keepPlaying);

        IO.println("Thanks for playing!");
    }

    // many methods
    private static String switchPlayer(String player)
    {
        if (player.equals("(X)")) {
            return "(O)";
        }
        else {
            return "(X)";
        }
    }

    private static boolean isValidMove(int row, int col){
        if ((row < 0) || (row >= ROWS) || (col < 0) || (col >= COLS)){
            return false;
        }
        return board[row][col].equals("( )");
    }


    private static void clearBoard(String[][] board){
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                board[row][col] = "( )";
            }

        }
    }

    private static void display(String[][] board){
        System.out.print("\n");
        for (int row = 0; row < ROWS; row++){
            for (int col = 0; col < COLS; col++){
                System.out.print(board[row][col] + " ");
                if (row == 0 && col == 2 || row == 1 && col == 2){
                    System.out.print("\n");
                }
            }
        }
        System.out.print("\n");
    }

    private static boolean isWin(String player){
        return isRowWin(player) || isColWin(player) || isDiagWin(player);
    }
    private static boolean isColWin(String player){
        for (int col = 0; col < COLS; col++){
            if (board[0][col].equals(player) &&
                board[1][col].equals(player) &&
                board[2][col].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isRowWin(String player){
        for (int row = 0; row < ROWS; row++){
            if (board[row][0].equals(player) &&
                board[row][1].equals(player) &&
                board[row][2].equals(player)){
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagWin(String player){
        if (player.equals(board[0][0]) &&
            player.equals(board[1][1]) &&
            player.equals(board[2][2])) {
            return true;
        }
        else return player.equals(board[0][2]) &&
                    player.equals(board[1][1]) &&
                    player.equals(board[2][0]);
    }
    private static boolean isTie() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals("( )")) {
                    return false;
                }
            }
        }
        return !isWin("(X)") && !isWin("(O)");
    }

    // end of methods
}