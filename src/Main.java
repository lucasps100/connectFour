import java.util.Scanner;
//TODO: Create intelligent player.
public class Main {
    public static void main(String[] args) {
        boolean isOn = true;
        do{
        Scanner console = new Scanner(System.in);
        String[][] board = new String[7][7];
        for (int i = 1; i < 7; i++) {
            board[i] = new String[]{"_", "_", "_", "_", "_", "_", "_"};
        }
        board[0] = new String[]{"1", "2", "3", "4", "5", "6", "7"};
        System.out.println("Welcome to Connect Four!");
        System.out.println("========================");
        System.out.println();
        System.out.print("Player X, enter your name:");
        String playerX = console.nextLine().strip();
        System.out.print("Player 0, enter your name:");
        String playerO = console.nextLine().strip();
        System.out.println(playerX + " goes first.");
        printBoard(board);
        boolean winner = false;
        String currentSymbol;
        int turnNumber = 0;
            while (!winner) {
                if (turnNumber % 2 == 0) {
                    currentSymbol = "X";
                    System.out.print(playerX + ", choose a column number [1-7]:");
                } else {
                    currentSymbol = "O";
                    System.out.print(playerO + ", choose a column number [1-7]:");
                }
                placePiece(board, console, currentSymbol);
                printBoard(board);
                //check for win
                winner = checkForWinner(board, currentSymbol);
                turnNumber++;

            }
            System.out.println();
            System.out.println(((turnNumber - 1) % 2 == 0 ? playerX : playerO) + " wins!");
            System.out.println();
            System.out.println("Would you like to play again? [y/n]");
            isOn = getYesOrNo(console).equals("y");

        }while(isOn);
    }

    public static void printBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void placePiece(String[][] board, Scanner console, String currentSymbol) {
        int colChoice;
        boolean waiting;
        do {
            do {
                colChoice = validateIntType(console);
                if(colChoice > 7 || colChoice < 1){
                    System.out.print("Column number is out of range [1-7]. Please try again:");
                }
            }while(colChoice > 7 || colChoice < 1);
            waiting = false;
            for (int i = 6; i > 0; i--) {
                if (board[i][colChoice - 1].equals("_")) {
                    board[i][colChoice - 1] = currentSymbol;
                    break;
                } else if (i == 1) {
                    System.out.print("No room in this column. Please choose another:");
                    waiting = true;
                }
            }
        } while (waiting);
    }

    public static String stripWhitespace(String phrase) {
        String output = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (!Character.isWhitespace(phrase.charAt(i))) output = output + phrase.charAt(i);

        }
        return output;
    }

    public static int validateIntType(Scanner console) {
        do {
            try {
                return Integer.parseInt(stripWhitespace(console.nextLine()));
            } catch (Exception e) {
                System.out.print("Invalid entry. Please try again:");
            }

        } while (true);
    }

    public static boolean checkForWinner(String[][] board, String currentSymbol) {
        return checkVertical(board, currentSymbol) || checkHorizontal(board, currentSymbol) || checkDiagonal(board, currentSymbol);
    }

    public static boolean checkVertical(String[][] board, String currentSymbol) {
        for (int i = 0; i < 7; i++) {
            for (int j = 1; j < 4; j++) {
                if (board[j][i].equals(currentSymbol) && board[j][i].equals(board[j + 1][i]) && board[j + 1][i].equals(board[j + 2][i]) && board[j + 2][i].equals(board[j + 3][i])) {
                    return true;
                }
                ;
            }
        }
        return false;
    }

    public static boolean checkHorizontal(String[][] board, String currentSymbol) {
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(currentSymbol) && board[i][j].equals(board[i][j + 1]) && board[i][j + 1].equals(board[i][j + 2]) && board[i][j + 2].equals(board[i][j + 3])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkDiagonal(String[][] board, String currentSymbol){
        for(int i = 1; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j].equals(currentSymbol) && board[i][j].equals(board[i+1][j+1]) && board[i+1][j+1].equals(board[i+2][j+2]) && board[i+2][j+2].equals(board[i+3][j+3])){
                    return true;
                }
                if(board[i][6-j].equals(currentSymbol) && board[i][6-j].equals(board[i+1][5-j]) && board[i+1][5-j].equals(board[i+2][4-j]) && board[i+2][5-j].equals(board[i+3][4-j])){
                    return true;
                }
            }
        }
        return false;
    }
    public static String getYesOrNo(Scanner console) {
        String response;
        do{
            response = stripWhitespace(console.nextLine()).toLowerCase();
            if(!response.equals("y") && !response.equals("n")){
                System.out.println("Invalid input. Please try again.");
            }
        }while(!response.equals("y") && !response.equals("n"));

        return response;
    }


}