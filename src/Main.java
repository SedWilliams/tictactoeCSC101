import java.util.Scanner;

public class Main {

    static String[][] board = new String[3][3];
    static int playerID = -1;

    //determines if the current board is winning
    public static boolean checkWinner(String[][] board) {
        //check rows
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("   ") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                displayBoard(board);
                System.out.println("Player " + board[i][0] + " wins!");
                return true;
            }
        }

        //check columns
        for (int j = 0; j < 3; j++) {
            if (!board[0][j].equals("   ") && board[0][j].equals(board[1][j]) && board[1][j].equals(board[2][j])) {
                displayBoard(board);
                System.out.println("Player " + board[0][j] + " wins!");
                return true;
            }
        }

        //check main diagonal
        if (!board[0][0].equals("   ") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            displayBoard(board);
            System.out.println("Player " + board[0][0] + " wins!");
            return true;
        }

        // Check other diagonal
        if (!board[0][2].equals("   ") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            displayBoard(board);
            System.out.println("Player " + board[0][2] + " wins!");
            return true;
        }

        //check for draw
        boolean boardFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("   ")) {
                    boardFull = false;
                    break;
                }
            }
        }
        if (boardFull) {
            System.out.println("The game is a draw.");
            displayBoard(board);
            System.out.println("   ");
        }

        return false;
    }

    //prints current board to the console
    public static boolean insertMove(String[][] board, String move, int playerID) {

        String[] formattedMove = move.split(" ");
        int num1 = Integer.parseInt(formattedMove[0]);
        int num2 = Integer.parseInt(formattedMove[1]);

            //check if player choice on board is empty
        if (board[num1][num2].contains("   ")) {

            //insert X or O depending on player, if/elseif !else for better personal comprehension
            if (playerID == -1) {
                board[num1][num2] = " O ";
            } else if (playerID == 1) {
                board[num1][num2] = " X ";
            }

            return true;
        } else {
            return false;
        }

    }

    //prints the current board to the console
    public static void displayBoard(String[][] board) {
        //board
        System.out.println("  +---+---+---+");
        System.out.println("  |" + board[0][0] + "|" + board[0][1] + "|" + board[0][2] + "|");
        System.out.println("  +---+---+---+");
        System.out.println("  |" + board[1][0] + "|" + board[1][1] + "|" + board[1][2] + "|");
        System.out.println("  +---+---+---+");
        System.out.println("  |" + board[2][0] + "|" + board[2][1] + "|" + board[2][2] + "|");
        System.out.println("  +---+---+---+");
    }


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String inputHolder = "";

        //init game board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "   ";
            }
        }

        //Intro
        System.out.println("TIC-TAC-TOE");
        System.out.println("----------------");
        System.out.println("              ");

        //game loop, runs as long as checkWinner returns false
        boolean userWon = false;
        while(!userWon) {

            //playerID starts at -1 so first loop changes it to 1
            //player 1 is X and player 2 is O
            playerID = playerID * -1;

            System.out.println("  CURRENT BOARD:");
            displayBoard(board);

            //input move and check if valid
            boolean validMove = false;
            while(!validMove) {
                System.out.println("          ");
                System.out.print("Enter your move (row col, 0-2, ex. 1 2 or 2 1): ");
                inputHolder = scan.nextLine().trim();


                String[] formattedMove = inputHolder.split(" ");

                int num1 = Integer.parseInt(formattedMove[0]);
                int num2 = Integer.parseInt(formattedMove[1]);

                if(!((num1 >= 0 && num1 <= 2) && (num2 >= 0 && num2 <= 2))) {
                    System.out.println("\nInvalid move, input another...");
                    continue;
                }

                if (insertMove(board, inputHolder, playerID)) {
                    validMove = true;
                    System.out.println("    ");
                    System.out.println("Inserting move...");
                    System.out.println("    ");
                } else {
                    System.out.println("Move is already taken! Try again.");
                }
            }

            //insert move into board, and reprompt if insertion fails

            //checkWinner
            if(checkWinner(board)) {
                //what happens when a user has won
                userWon = true;
            }
        }
    }
}
