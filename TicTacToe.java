/*

Name: Nicholas Weisson
Date: 09/30/2022
Assignment 3: Tic Tac Toe
Due Date: 10/07/2022

*/

import java.util.*;
import java.lang.Integer;

public class TicTacToe {

    // member data
    static ArrayList<Integer> playerOnePositions = new ArrayList<Integer>();
    static ArrayList<Integer> playerTwoPositions = new ArrayList<Integer>();

    private static String [][] board = {{" ", "|", " ", "|", " "},
            {"-", "-", "-", "-", "-"},
            {" ", "|", " ", "|", " "},
            {"-", "-", "-", "-", "-"},
            {" ", "|", " ", "|", " "}};

    private static String[][] positions = {{"1", "|", "2", "|", "3"},
            {"-", "-", "-", "-", "-"},
            {"4", "|", "5", "|", "6"},
            {"-", "-", "-", "-", "-"},
            {"7", "|", "8", "|", "9"}};

    // print boards
    public static void printBoards(String[][] board, String[][] pos){
        System.out.println("\nGame board:");
        for(String[] row : board){
            for(String c : row){
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("\nPositions:");
        for(String[] row : pos){
            for(String c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    // place piece on board
    public static void placePiece(int pos, int user){

        String symbol = " ";

        // player 1 places X
        if (user == 1){
            symbol = "X";
            playerOnePositions.add(pos);
        }
        // player 2 places O
        else if (user == 2){
            symbol = "O";
            playerTwoPositions.add(pos);
        }

        // place piece on the position that is provided
        switch (pos) {
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
        }
    }

    // check winner
    public static int checkWinner(){

        // list for rows
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);

        // list for columns
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);

        // list for diagonals
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(7,5,3);

        List<List> conditions = new ArrayList<List>();

        conditions.add(topRow);
        conditions.add(midRow);
        conditions.add(botRow);
        conditions.add(leftCol);
        conditions.add(midCol);
        conditions.add(rightCol);
        conditions.add(cross1);
        conditions.add(cross2);

        // check if players have won or tied
        for (List l : conditions){
            if (playerOnePositions.containsAll(l)){
                return 1;
            }
            else if (playerTwoPositions.containsAll(l)){
                return 2;
            }
        }

        if (playerOnePositions.size() + playerTwoPositions.size() == 9){
            return 3;
        }

        return 0;
    }

    // human vs human
    public static void humanVsHuman(){
        int result = 0;
        while (true){
            Scanner scan = new Scanner(System.in);
            // get player 1 move
            System.out.print("\nPlayer 1, please enter a move(1-9): ");
            int pos = scan.nextInt();
            System.out.println();
            // if position invalid or taken, print error and retry
            while (playerOnePositions.contains(pos) || playerTwoPositions.contains(pos) || pos > 9){
                System.out.println("Invalid/Taken position. Please try again");
                System.out.print("\nPlayer 1, please enter a move(1-9): ");
                pos = scan.nextInt();
                System.out.println();
            }

            // place move on board
            placePiece(pos,1);
            result = checkWinner();
            printBoards(board, positions);

            // check for winner
            if (result == 1){
                System.out.println("\nPlayer 1 Wins!\n");
                break;
            }
            if (result == 2){
                System.out.println("\nPlayer 2 Wins!\n");
                break;
            }
            if (result == 3){
                System.out.println("\nTie!\n");
                break;
            }

            // get player 2 move
            System.out.print("\nPlayer 2, please enter a move(1-9): ");
            int pos2 = scan.nextInt();
            System.out.println();
            // if position invalid or taken, print error and retry
            while (playerTwoPositions.contains(pos2) || playerOnePositions.contains(pos2) || pos2 > 9){
                System.out.println("Invalid/Taken position. Please try again");
                System.out.print("\nPlayer 2, please enter a move(1-9): ");
                pos2 = scan.nextInt();
                System.out.println();
            }

            // place move on board
            placePiece(pos2,2);
            result = checkWinner();
            printBoards(board, positions);

            // check for winner
            if (result == 1){
                System.out.println("\nPlayer 1 Wins!\n");
                break;
            }
            if (result == 2){
                System.out.println("\nPlayer 2 Wins!\n");
                break;
            }
            if (result == 3){
                System.out.println("\nTie!\n");
                break;
            }

            if (result == 0){
                continue;
            }

        }
    }

    // computer vs computer
    public static void compVsComp(){

        // comp 1 & 2
        Random compOne = new Random();
        Random compTwo = new Random();
        int cpuPos1 = 0;
        int cpuPos2 = 0;
        int result = 0;

        // continue game until there is a win or tie
        while (true){

            cpuPos1 = compOne.nextInt(9) + 1;
            while (playerOnePositions.contains(cpuPos1) || playerTwoPositions.contains(cpuPos1)) {
                cpuPos1 = compOne.nextInt(9) + 1;
            }

            placePiece(cpuPos1, 1);
            result = checkWinner();
            printBoards(board, positions);

            if (result == 1){
                System.out.println("\nPlayer 1 Wins!\n");
                break;
            }
            if (result == 2){
                System.out.println("\nPlayer 2 Wins!\n");
                break;
            }
            if (result == 3){
                System.out.println("\nTie!\n");
                break;
            }

            cpuPos2 = compTwo.nextInt(9) + 1;
            while (playerTwoPositions.contains(cpuPos2) || playerOnePositions.contains(cpuPos2)) {
                cpuPos2 = compTwo.nextInt(9) + 1;
            }
            placePiece(cpuPos2, 2);
            result = checkWinner();
            printBoards(board, positions);

            if (result == 1){
                System.out.println("\nPlayer 1 Wins!\n");
                break;
            }
            if (result == 2){
                System.out.println("\nPlayer 2 Wins!\n");
                break;
            }
            if (result == 3){
                System.out.println("\nTie!\n");
                break;
            }

        }

    }

    // human vs computer
    public static void humanVsComp(int arg){

        Random cpu = new Random();
        int cpuPlayer = 0;
        int cpuPos = 0;
        int result = 0;

        // if -c 1, computer is player 1
        if (arg == 1){
            cpuPlayer = 1;
        }
        // if -c 2, computer is player 2
        else{
            cpuPlayer = 2;
        }

        // loop game until there is a win or tie
        while (true) {
            Scanner scan = new Scanner(System.in);
            cpuPos = cpu.nextInt(9) + 1;
            int pos = 0;

            // if CPU is player 1, place first
            if (cpuPlayer == 1) {
                // check if cpu position is taken already
                while (playerOnePositions.contains(cpuPos) || playerTwoPositions.contains(cpuPos)) {
                    cpuPos = cpu.nextInt(9) + 1;
                }

                placePiece(cpuPos, 1);
                result = checkWinner();
                printBoards(board, positions);

                if (result == 1) {
                    System.out.println("\nPlayer 1 Wins!\n");
                    break;
                }
                if (result == 2) {
                    System.out.println("\nPlayer 2 Wins!\n");
                    break;
                }
                if (result == 3) {
                    System.out.println("\nTie!\n");
                    break;
                }

                // get player 2 move
                System.out.print("\nPlayer 2, please enter a move(1-9): ");
                pos = scan.nextInt();
                System.out.println();
                // if position invalid or taken, print error and retry
                while (playerTwoPositions.contains(pos) || playerOnePositions.contains(pos) || pos > 9) {
                    System.out.println("Invalid/Taken position. Please try again");
                    System.out.print("\nPlayer 2, please enter a move(1-9): ");
                    pos = scan.nextInt();
                    System.out.println();
                }

                placePiece(pos, 2);
                result = checkWinner();
                printBoards(board, positions);

                if (result == 1) {
                    System.out.println("\nPlayer 1 Wins!\n");
                    break;
                }
                if (result == 2) {
                    System.out.println("\nPlayer 2 Wins!\n");
                    break;
                }
                if (result == 3) {
                    System.out.println("\nTie!\n");
                    break;
                }

            } else {
                // get player 1 move
                System.out.print("\nPlayer 1, please enter a move(1-9): ");
                pos = scan.nextInt();
                System.out.println();
                // if position invalid or taken, print error and retry
                while (playerOnePositions.contains(pos) || playerTwoPositions.contains(pos) || pos > 9) {
                    System.out.println("Invalid/Taken position. Please try again");
                    System.out.print("\nPlayer 1, please enter a move(1-9): ");
                    pos = scan.nextInt();
                    System.out.println();
                }

                placePiece(pos, 1);
                result = checkWinner();
                printBoards(board, positions);

                if (result == 1) {
                    System.out.println("\nPlayer 1 Wins!\n");
                    break;
                }
                if (result == 2) {
                    System.out.println("\nPlayer 2 Wins!\n");
                    break;
                }
                if (result == 3) {
                    System.out.println("\nTie!\n");
                    break;
                }

                // check if cpu position is taken already
                while (playerOnePositions.contains(cpuPos) || playerTwoPositions.contains(cpuPos)) {
                    cpuPos = cpu.nextInt(9) + 1;
                }

                placePiece(cpuPos, 2);
                result = checkWinner();
                printBoards(board, positions);

                if (result == 1) {
                    System.out.println("\nPlayer 1 Wins!\n");
                    break;
                }
                if (result == 2) {
                    System.out.println("\nPlayer 2 Wins!\n");
                    break;
                }
                if (result == 3) {
                    System.out.println("\nTie!\n");
                    break;
                }

            }

        }

    }

    public static void main(String[] args){

        // human vs human
        if (args.length == 0){
            printBoards(board, positions);
            humanVsHuman();
        }
        // comp vs comp
        else if (args.length == 1){
            printBoards(board, positions);
            compVsComp();
        }
        // human vs comp
        else if (args.length == 2){
            printBoards(board,positions);
            // store argument that specifies player 1 or 2
            int argument = Integer.parseInt(args[1]);
            humanVsComp(argument);
        }
        else{
            System.out.println("\nUsage: java TicTacToe [-c [1|2]]\n");
            java.lang.System.exit(0);
        }


    }






}







