import java.util.*;

public class tictactoe {

    public static char player1 = 'X';
    public static char computer = 'O';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] arr = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        System.out.println("Let's start the toss...");
        System.out.print("Player, choose Head or Tails: ");
        String playerChoice = sc.next();

        while (!playerChoice.equalsIgnoreCase("Head") && !playerChoice.equalsIgnoreCase("Tails")) {
            System.out.print("Invalid choice. Please choose Head or Tails: ");
            playerChoice = sc.next();
        }

        String computerChoice = playerChoice.equalsIgnoreCase("Head") ? "Tails" : "Head";
        System.out.println("Computer gets: " + computerChoice);

        String result = toss();
        char currentPlayer;
        if (result.equalsIgnoreCase(playerChoice)) {
            System.out.println("Player won the toss! Choose X or O:");
            player1 = sc.next().toUpperCase().charAt(0);
            computer = (player1 == 'X') ? 'O' : 'X';
            currentPlayer = player1;
        } else {
            System.out.println("Computer won the toss! Computer chooses...");
            computer = (Math.random() < 0.5) ? 'X' : 'O';
            player1 = (computer == 'X') ? 'O' : 'X';
            currentPlayer = computer;
            System.out.println("Computer chose: " + computer);
        }

        System.out.println("Player is: " + player1 + ", Computer is: " + computer);
        boolean gameOn = true;

        while (gameOn) {
            drawGrid(arr);
            if (currentPlayer == player1) {
                System.out.println("Your turn!");
                System.out.println("Choose row (0-2):");
                int row = sc.nextInt();
                System.out.println("Choose column (0-2):");
                int col = sc.nextInt();

                if (validMove(arr, row, col)) {
                    arr[row][col] = player1;
                    if (checkWinner(arr, player1)) {
                        drawGrid(arr);
                        System.out.println("You win!");
                        gameOn = false;
                    } else if (isDraw(arr)) {
                        drawGrid(arr);
                        System.out.println("It's a draw!");
                        gameOn = false;
                    } else {
                        currentPlayer = computer;
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Computer's turn...");
                int[] move = computerMove(arr);
                arr[move[0]][move[1]] = computer;

                if (checkWinner(arr, computer)) {
                    drawGrid(arr);
                    System.out.println("Computer wins!");
                    gameOn = false;
                } else if (isDraw(arr)) {
                    drawGrid(arr);
                    System.out.println("It's a draw!");
                    gameOn = false;
                } else {
                    currentPlayer = player1;
                }
            }
        }
    }

    public static boolean validMove(char[][] arr, int row, int col) {
        return arr[row][col] == ' ';
    }

    public static String toss() {
        Random x = new Random();
        return x.nextBoolean() ? "Head" : "Tails";
    }

    public static void drawGrid(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print(" " + arr[i][j] + " ");
                if (j + 1 < arr.length) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i + 1 < arr.length) {
                System.out.println("-----------");
            }
        }
    }

    public static boolean checkWinner(char[][] arr, char currentPlayer) {
        for (int i = 0; i < 3; i++) {
            if (arr[i][0] == currentPlayer && arr[i][1] == currentPlayer && arr[i][2] == currentPlayer) {
                return true;
            }
            if (arr[0][i] == currentPlayer && arr[1][i] == currentPlayer && arr[2][i] == currentPlayer) {
                return true;
            }
        }
        if (arr[0][0] == currentPlayer && arr[1][1] == currentPlayer && arr[2][2] == currentPlayer) {
            return true;
        }
        if (arr[0][2] == currentPlayer && arr[1][1] == currentPlayer && arr[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public static boolean isDraw(char[][] arr) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] computerMove(char[][] arr) {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
        } while (!validMove(arr, row, col));
        return new int[]{row, col};
    }
}
