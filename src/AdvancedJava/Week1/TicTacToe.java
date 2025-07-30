package AdvancedJava.Week1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    static String[] board = new String[9];
    static String turn;

    // checkWinner method will decide the winner
    public static String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };

            // for X winner
            if (line.equals("XXX")) {
                return "X";
            }

            // for O winner
            if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            // the below condition can also be written as: board[a].equals(String.valueOf(a + 1))
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
        return null;
    }

    // to print the board
    public static void displayBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;

        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        displayBoard();
        System.out.println("X will play first. Enter a slot number to place X in: ");

        while (winner == null) {
            int numInput;

            try {
                numInput = in.nextInt();

                // check range
                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter the slot number: ");
                    continue;
                }

                // check if slot is available
                if (board[numInput - 1].equals(String.valueOf(numInput))) {
                    board[numInput - 1] = turn;

                    // toggle turn
                    turn = turn.equals("X") ? "O" : "X";

                    displayBoard();
                    winner = checkWinner();
                } else {
                    System.out.println("Slot already taken; re-enter slot number: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number: ");
                in.nextLine(); // consume invalid input to prevent infinit loop
            }
        }

        // final result
        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }

        in.close();
    }
}
