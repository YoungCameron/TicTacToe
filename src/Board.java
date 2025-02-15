import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    private final String[] boardSlots;
    private String player = "p1";

    public Board() {
        this.boardSlots = new String[]{ "0", "1", "2" , "3", "4", "5" , "6" , "7", "8" };
    }

    private void printBoard() {
        System.out.printf("""
                %s | %s | %s
                %s | %s | %s
                %s | %s | %s
                """, boardSlots[0], boardSlots[1], boardSlots[2],
                boardSlots[3], boardSlots[4], boardSlots[5],
                boardSlots[6], boardSlots[7], boardSlots[8]);
    }

    public void startGame() {
        Scanner in = new Scanner(System.in);
        printBoard();
        do {
            System.out.print("Player " + checkPlayer() + "\nEnter Slot Choice: ");
            setSlot(in.nextInt());
            printBoard();
        } while (!gameWon() && !gameDraw());
    }

    private void setSlots(String pos, String setTo) {
        try {
            this.boardSlots[Integer.parseInt(pos)] = setTo;
        } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
            System.out.println("BAD INPUT");
        }
    }

    private void setSlot(int choice) {
            try {
                if (checkPlayer().equals("p1")) {
                    checkSlot(choice);
                    setSlots(choice + "", "X");
                    swapPlayer();
                } else if (checkPlayer().equals("p2")) {
                    checkSlot(choice);
                    setSlots(choice + "", "O");
                    swapPlayer();
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Illegal Move\n");
            }
    }

    private void checkSlot(int choice) {
        if (boardSlots[choice].equals("O") || boardSlots[choice].equals("X")) {
            throw new IllegalArgumentException();
        }
    }

    private boolean gameDraw() {
        for (String x : boardSlots) {
            if (!x.equals("X") && !x.equals("O")) {
                return false;
            }
        }
        System.out.println("Game Draw");
        return true;
    }

    private boolean gameWon() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (boardSlots[i * 3].equals(boardSlots[i * 3 + 1]) && boardSlots[i * 3 + 1].equals(boardSlots[i * 3 + 2])) {
                System.out.println(boardSlots[i * 3] + "'s won");
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (boardSlots[i].equals(boardSlots[i + 3]) && boardSlots[i + 3].equals(boardSlots[i + 6])) {
                System.out.println(boardSlots[i + 3] + "'s won");
                return true;
            }
        }

        // Check diagonals
        if (boardSlots[0].equals(boardSlots[4]) && boardSlots[4].equals(boardSlots[8])) {
            System.out.println(boardSlots[0] + "'s won");
            return true;
        }
        if (boardSlots[2].equals(boardSlots[4]) && boardSlots[4].equals(boardSlots[6])) {
            System.out.println(boardSlots[2] + "'s won");
            return true;
        }

        return false;
    }

    private String checkPlayer() {
        return this.player;
    }


    private void swapPlayer() {
        if (checkPlayer().equals("p1")) {
            this.player = "p2";
        } else {
            this.player = "p1";
        }
    }
}
