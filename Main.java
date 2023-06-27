import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        // CODING_IN_PROGRESS - CODERS_AT_WORK!!!

        //Initializing Game
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        String gameState = "";
        
        String input = "         ";
        char[] inputArray = input.toCharArray();

        //Printing
        while(gameOver == false) {
            printGame(inputArray);
            gameState = gameCondition(inputArray, input);
            gameOver = gameOver(gameState);
            checkGameState(gameState);

            if (gameOver == false) {
                inputArray = X_Plays(inputArray, scanner);
                input = String.valueOf(inputArray);
                
                printGame(inputArray);
                gameState = gameCondition(inputArray, input);
                gameOver = gameOver(gameState);
                checkGameState(gameState);
            }

            if (gameOver == false) {
                inputArray = O_Plays(inputArray, scanner);
                input = String.valueOf(inputArray);
                
                printGame(inputArray);
                gameState = gameCondition(inputArray, input);
                gameOver = gameOver(gameState);
                checkGameState(gameState);
            }

            if(gameOver == true) {
                System.exit(0);
            }
    
        }
    }



    // MAIN ENDS -----------------------------------------------------------------------------------------//

    public static void printGame(char[] inputArray) {
        String input = String.valueOf(inputArray);
        
        System.out.println("---------");
        System.out.printf("| %s %s %s |\n", input.charAt(0), input.charAt(1), input.charAt(2));
        System.out.printf("| %s %s %s |\n", input.charAt(3), input.charAt(4), input.charAt(5));
        System.out.printf("| %s %s %s |\n", input.charAt(6), input.charAt(7), input.charAt(8));
        System.out.println("---------");
    }

    public static int locatingFinalCoord(int row, int column) {
        int gridSize = 3;
        int number = (row - 1) * gridSize + column - 1;
        return number;
    }

    public static char[] X_Plays(char[] inputArray, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        int newXCoord1;
        int newXCoord2;
        while(true) { //Getting X Input
            try {
                newXCoord1 = scanner.nextInt();
                newXCoord2 = scanner.nextInt();
                if (newXCoord1 <= 3 && newXCoord2 <= 3) {
                    int finalCoord = locatingFinalCoord(newXCoord1, newXCoord2);
                    if (inputArray[finalCoord] == 'X' || inputArray[finalCoord] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        inputArray[finalCoord] = 'X';
                        return inputArray;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static char[] O_Plays(char[] inputArray, Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        int newXCoord1;
        int newXCoord2;
        while(true) { //Getting X Input
            try {
                newXCoord1 = scanner.nextInt();
                newXCoord2 = scanner.nextInt();
                if (newXCoord1 <= 3 && newXCoord2 <= 3) {
                    int finalCoord = locatingFinalCoord(newXCoord1, newXCoord2);
                    if (inputArray[finalCoord] == 'X' || inputArray[finalCoord] == 'O') {
                        System.out.println("This cell is occupied! Choose another one!");
                    } else {
                        inputArray[finalCoord] = 'O';
                        return inputArray;
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static String gameCondition(char[] inputArray, String input) {
        int X_Played = 0;
        int O_Played = 0;
        int emptySlot = 0;
        String gameState = "";
        boolean X_Wins = false;
        boolean O_Wins = false;

        //Game Condition
            //Initializing Played Inputs
            for (char move: inputArray) {
                if (move == 'X') {
                    X_Played++;
                } else if (move == 'O') {
                    O_Played++;
                } else {
                    emptySlot++;
                }
            }

            //Analyzing Condition Of The Game
                //If the difference is 2 or more, the game state is impossible.
                if (X_Played > O_Played) {
                    if ( (X_Played - O_Played) > 1) {
                        gameState = "Impossible";
                    }
                } else if (O_Played > X_Played) {
                    if ( (O_Played - X_Played) > 1) {
                        gameState = "Impossible";
                    }
                }

                // X Wins
                    //Horizontal Check
                    for (int i = 0; i < 3; i++) {
                        if ( input.charAt(i*3) == 'X' && input.charAt(i*3+1) == 'X' && input.charAt(i*3+2) == 'X') {
                            gameState = "X Wins";
                            X_Wins = true;
                        }
                    }
                    //Vertical Check
                    for (int i = 0; i < 3; i++) {
                        if (input.charAt(i) == 'X' && input.charAt(i + 3) == 'X' && input.charAt(i + 6) == 'X') {
                            gameState = "X Wins";
                            X_Wins = true;
                        }
                    }
                    // Diagonal Check
                    if ((input.charAt(0) == 'X' && input.charAt(4) == 'X' && input.charAt(8) == 'X') ||
                        (input.charAt(2) == 'X' && input.charAt(4) == 'X' && input.charAt(6) == 'X')) {
                        gameState = "X Wins";
                        X_Wins = true;
                    }

                // O Wins
                    //Horizontal Check
                    for (int i = 0; i < 3; i++) {
                        if ( input.charAt(i*3) == 'O' && input.charAt(i*3+1) == 'O' && input.charAt(i*3+2) == 'O') {
                            gameState = "O Wins";
                            O_Wins = true;
                        }
                    }
                    //Vertical Check
                    for (int i = 0; i < 3; i++) {
                        if (input.charAt(i) == 'O' && input.charAt(i + 3) == 'O' && input.charAt(i + 6) == 'O') {
                            gameState = "O Wins";
                            O_Wins = true;
                        }
                    }
                    // Diagonal Check
                    if ((input.charAt(0) == 'O' && input.charAt(4) == 'O' && input.charAt(8) == 'O') ||
                        (input.charAt(2) == 'O' && input.charAt(4) == 'O' && input.charAt(6) == 'O')) {
                        gameState = "O Wins";
                        O_Wins = true;
                    }

                if(!gameState.equals("O Wins") && !gameState.equals("X Wins")) {
                    //3 X and O in a row simultaneously, the game state is impossible.
                    if (X_Wins == true && O_Wins == true) {
                        gameState = "Impossible";
                    } else if (X_Wins == false && O_Wins == false && emptySlot > 0 && !gameState.equals("Impossible")) {
                        gameState = "Not Finished";
                    } else if (X_Wins == false && O_Wins == false && emptySlot == 0) {
                        gameState = "Draw";
                    }
                }
                

        return gameState;
    }

    public static boolean gameOver(String gameState) {
        boolean gameOver = false;
        
        switch(gameState) {
            case "Impossible":
                gameOver = false;
                break;
                
            case "Not Finished":
                gameOver = false;
                break;
                
            case "Draw":
                gameOver = true;
                break;
                
            case "X Wins":
                gameOver = true;
                break;
                
            case "O Wins":
                gameOver = true;
                break;
        }

        return gameOver;
    }
    
    public static void checkGameState(String gameState) {
        switch(gameState) {
            case "Impossible":
                System.out.println("Impossible");
                break;
                
            case "Not Finished":
                System.out.println("Game not finished");
                break;
                
            case "Draw":
                System.out.println("Draw");
                break;
                
            case "X Wins":
                System.out.println("X wins");
                break;
                
            case "O Wins":
                System.out.println("O wins");
                break;
        }
    }

    // Class ENDS -----------------------------------------------------------------------------------------//

}


    