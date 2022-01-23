import com.sun.tools.javac.Main;

import java.awt.*;
import java.io.Console;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static Board board = new Board();
    private static Player player;
    private static Player computer;
    private static String level;

    private static void start(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("pick a level, easy, medium or hard");
        level = scanner.nextLine().toLowerCase();
        while(!level.equals("easy") && !level.equals("hard") && !level.equals("medium")){
            System.out.println("please type easy, medium, or hard");
            level = scanner.nextLine().toLowerCase();
        }

        System.out.println("would you like to be X or O");
        String choice = scanner.next().toUpperCase();
        while(!choice.equals("X") && !choice.equals("O")){
            System.out.println("Please pick an X or an O");
            choice = scanner.next().toUpperCase();
        }

        player = new Player(choice);
        if(choice.equals("X")) {
             computer = new Player("O");
        }
        else
        {
            computer = new Player("X");
        }
    }

    private static void playerTurn(Player player){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please type what row you would like");
        try {
            int i = scanner.nextInt();

            while (i < 0 || i > 2) {
                System.out.println("please pick a row between 0 and 2");
                i = scanner.nextInt();
            }

            System.out.println("please type what column you would like");
            int j = scanner.nextInt();
            while (j < 0 || j > 2) {
                System.out.println("please pick a row between 0 and 2");
                j = scanner.nextInt();
            }

            if (!board.isValidSpot(i, j)) {
                System.out.println("please pick a valid spot");
                playerTurn(player);
            } else {
                board.setSpot(i, j, player);
            }
        }
        catch (Exception e){
            System.out.println("please type valid integers");
            playerTurn(player);
        }
    }

    private static void easyComputerTurn(){
        Random random = new Random();
        int i = random.nextInt(3);
        int j = random.nextInt(3);
        while(!board.isValidSpot(i , j)){
            i = random.nextInt(3);
            j = random.nextInt(3);
        }
        board.setSpot(i, j, computer);
    }

    private static  void mediumComputerTurn(){
        for(int i = 0; i < board.backingBoard.length; i ++){
            if(board.getSpot(i,0).equals(computer.getLetter()) && board.getSpot(i, 0).equals(board.getSpot(i, 1)) && board.isValidSpot(i, 2)){
                board.setSpot(i, 2, computer);
                return;
            }
            if(board.getSpot(i,0).equals(computer.getLetter()) && board.getSpot(i,0).equals(board.getSpot(i, 2)) && board.isValidSpot(i, 1)){
                board.setSpot(i,1, computer);
                return;
            }
            if(board.getSpot(i,1).equals(computer.getLetter()) && board.getSpot(i, 1).equals(board.getSpot(i,2)) && board.isValidSpot(i, 0)){
                board.setSpot(i, 0, computer);
                return;
            }
        }
        for(int j = 0; j < board.backingBoard[0].length; j++){
            if(board.getSpot(0, j).equals(computer.getLetter()) && board.getSpot(0, j).equals(board.getSpot(1, j)) && board.isValidSpot(2, j)){
                board.setSpot(2, j, computer);
                return;
            }
            if(board.getSpot(0,j).equals(computer.getLetter()) && board.getSpot(0,j).equals(board.getSpot(2, j)) && board.isValidSpot(1, j)){
                board.setSpot(1,j, computer);
                return;
            }
            if(board.getSpot(1,j).equals(computer.getLetter()) && board.getSpot(1, j).equals(board.getSpot(2,j)) && board.isValidSpot(0, j)){
                board.setSpot(0, j, computer);
                return;
            }
        }
        if(board.getSpot(0,0).equals(computer.getLetter()) && board.getSpot(0, 0).equals(board.getSpot(1,1)) && board.isValidSpot(2, 2)){
            board.setSpot(2, 2, computer);
            return;
        }
        if(board.getSpot(0,0).equals(computer.getLetter()) && board.getSpot(0, 0).equals(board.getSpot(2,2)) && board.isValidSpot(1, 1)){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1,1).equals(computer.getLetter()) && board.getSpot(1, 1).equals(board.getSpot(2,2)) && board.isValidSpot(0, 0)){
            board.setSpot(0, 0, computer);
            return;
        }

        if(board.getSpot(2,0).equals(computer.getLetter()) && board.getSpot(2, 0).equals(board.getSpot(1,1)) && board.isValidSpot(0, 2)){
            board.setSpot(0, 2, computer);
            return;
        }
        if(board.getSpot(2,0).equals(computer.getLetter()) && board.getSpot(2, 0).equals(board.getSpot(0,2)) && board.isValidSpot(1, 1)){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1,1).equals(computer.getLetter()) && board.getSpot(1, 1).equals(board.getSpot(0,2)) && board.isValidSpot(2, 0)){
            board.setSpot(2, 0, computer);
            return;
        }
        if(board.getSpot(1,1).equals(computer.getLetter()) && board.getSpot(0, 2).equals(board.getSpot(1,1)) && board.isValidSpot(2, 0) && !board.getSpot(0, 2).equals("-")){
            board.setSpot(2, 0, computer);
            return;
        }

        for(int i = 0; i < board.backingBoard.length; i ++){
            if(board.getSpot(i, 0).equals(board.getSpot(i, 1)) && board.isValidSpot(i, 2) && !board.getSpot(i, 0).equals("-")){
                board.setSpot(i, 2, computer);
                return;
            }
            if(board.getSpot(i,0).equals(board.getSpot(i, 2)) && board.isValidSpot(i, 1) && !board.getSpot(i, 0).equals("-")){
                board.setSpot(i,1, computer);
                return;
            }
            if(board.getSpot(i, 1).equals(board.getSpot(i,2))&& board.isValidSpot(i, 0) && !board.getSpot(i, 1).equals("-")){
                board.setSpot(i, 0, computer);
                return;
            }
        }
        for(int j = 0; j < board.backingBoard[0].length; j++){
            if(board.getSpot(0, j).equals(board.getSpot(1, j)) && board.isValidSpot(2, j) && !board.getSpot(0, j).equals("-")){
                board.setSpot(2, j, computer);
                return;
            }
            if(board.getSpot(0,j).equals(board.getSpot(2, j)) && board.isValidSpot(1, j) && !board.getSpot(0, j).equals("-")){
                board.setSpot(1,j, computer);
                return;
            }
            if(board.getSpot(1, j).equals(board.getSpot(2,j)) && board.isValidSpot(0, j) && !board.getSpot(1, j).equals("-")){
                board.setSpot(0, j, computer);
                return;
            }
        }
        if(board.getSpot(0, 0).equals(board.getSpot(1,1)) && board.isValidSpot(2, 2) && !board.getSpot(0, 0).equals("-")){
            board.setSpot(2, 2, computer);
            return;
        }
        if(board.getSpot(0, 0).equals(board.getSpot(2,2)) && board.isValidSpot(1, 1) && !board.getSpot(0, 0).equals("-")){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1, 1).equals(board.getSpot(2,2)) && board.isValidSpot(0, 0) && !board.getSpot(1, 1).equals("-")){
            board.setSpot(0, 0, computer);
            return;
        }

        if(board.getSpot(2, 0).equals(board.getSpot(1,1)) && board.isValidSpot(0, 2) && !board.getSpot(2, 0).equals("-")){
            board.setSpot(0, 2, computer);
            return;
        }
        if(board.getSpot(2, 0).equals(board.getSpot(0,2)) && board.isValidSpot(1, 1) && !board.getSpot(2, 0).equals("-")){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1, 1).equals(board.getSpot(2,2)) && board.isValidSpot(2, 0) && !board.getSpot(1, 1).equals("-")){
            board.setSpot(0, 0, computer);
            return;
        }
        if(board.getSpot(0, 2).equals(board.getSpot(1,1)) && board.isValidSpot(2, 0) && !board.getSpot(0, 2).equals("-")){
            board.setSpot(2, 0, computer);
            return;
        }
        easyComputerTurn();

    }

    private static void hardComputerTurn(){

        for(int i = 0; i < board.backingBoard.length; i ++){
            if(board.getSpot(i,0).equals(computer.getLetter()) && board.getSpot(i, 0).equals(board.getSpot(i, 1)) && board.isValidSpot(i, 2)){
                board.setSpot(i, 2, computer);
                return;
            }
            if(board.getSpot(i,0).equals(computer.getLetter()) && board.getSpot(i,0).equals(board.getSpot(i, 2)) && board.isValidSpot(i, 1)){
                board.setSpot(i,1, computer);
                return;
            }
            if(board.getSpot(i,1).equals(computer.getLetter()) && board.getSpot(i, 1).equals(board.getSpot(i,2)) && board.isValidSpot(i, 0)){
                board.setSpot(i, 0, computer);
                return;
            }
        }
        for(int j = 0; j < board.backingBoard[0].length; j++){
            if(board.getSpot(0, j).equals(computer.getLetter()) && board.getSpot(0, j).equals(board.getSpot(1, j)) && board.isValidSpot(2, j)){
                board.setSpot(2, j, computer);
                return;
            }
            if(board.getSpot(0,j).equals(computer.getLetter()) && board.getSpot(0,j).equals(board.getSpot(2, j)) && board.isValidSpot(1, j)){
                board.setSpot(1,j, computer);
                return;
            }
            if(board.getSpot(1,j).equals(computer.getLetter()) && board.getSpot(1, j).equals(board.getSpot(2,j)) && board.isValidSpot(0, j)){
                board.setSpot(0, j, computer);
                return;
            }
        }
        if(board.getSpot(0,0).equals(computer.getLetter()) && board.getSpot(0, 0).equals(board.getSpot(1,1)) && board.isValidSpot(2, 2)){
            board.setSpot(2, 2, computer);
            return;
        }
        if(board.getSpot(0,0).equals(computer.getLetter()) && board.getSpot(0, 0).equals(board.getSpot(2,2)) && board.isValidSpot(1, 1)){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1,1).equals(computer.getLetter()) && board.getSpot(1, 1).equals(board.getSpot(2,2)) && board.isValidSpot(0, 0)){
            board.setSpot(0, 0, computer);
            return;
        }

        if(board.getSpot(2,0).equals(computer.getLetter()) && board.getSpot(2, 0).equals(board.getSpot(1,1)) && board.isValidSpot(0, 2)){
            board.setSpot(0, 2, computer);
            return;
        }
        if(board.getSpot(2,0).equals(computer.getLetter()) && board.getSpot(2, 0).equals(board.getSpot(0,2)) && board.isValidSpot(1, 1)){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1,1).equals(computer.getLetter()) && board.getSpot(1, 1).equals(board.getSpot(0,2)) && board.isValidSpot(2, 0)){
            board.setSpot(2, 0, computer);
            return;
        }
        if(board.getSpot(1,1).equals(computer.getLetter()) && board.getSpot(0, 2).equals(board.getSpot(1,1)) && board.isValidSpot(2, 0) && !board.getSpot(0, 2).equals("-")){
            board.setSpot(2, 0, computer);
            return;
        }

        for(int i = 0; i < board.backingBoard.length; i ++){
            if(board.getSpot(i, 0).equals(board.getSpot(i, 1)) && board.isValidSpot(i, 2) && !board.getSpot(i, 0).equals("-")){
                board.setSpot(i, 2, computer);
                return;
            }
            if(board.getSpot(i,0).equals(board.getSpot(i, 2)) && board.isValidSpot(i, 1) && !board.getSpot(i, 0).equals("-")){
                board.setSpot(i,1, computer);
                return;
            }
            if(board.getSpot(i, 1).equals(board.getSpot(i,2))&& board.isValidSpot(i, 0) && !board.getSpot(i, 1).equals("-")){
                board.setSpot(i, 0, computer);
                return;
            }
        }
        for(int j = 0; j < board.backingBoard[0].length; j++){
            if(board.getSpot(0, j).equals(board.getSpot(1, j)) && board.isValidSpot(2, j) && !board.getSpot(0, j).equals("-")){
                board.setSpot(2, j, computer);
                return;
            }
            if(board.getSpot(0,j).equals(board.getSpot(2, j)) && board.isValidSpot(1, j) && !board.getSpot(0, j).equals("-")){
                board.setSpot(1,j, computer);
                return;
            }
            if(board.getSpot(1, j).equals(board.getSpot(2,j)) && board.isValidSpot(0, j) && !board.getSpot(1, j).equals("-")){
                board.setSpot(0, j, computer);
                return;
            }
        }
        if(board.getSpot(0, 0).equals(board.getSpot(1,1)) && board.isValidSpot(2, 2) && !board.getSpot(0, 0).equals("-")){
            board.setSpot(2, 2, computer);
            return;
        }
        if(board.getSpot(0, 0).equals(board.getSpot(2,2)) && board.isValidSpot(1, 1) && !board.getSpot(0, 0).equals("-")){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1, 1).equals(board.getSpot(2,2)) && board.isValidSpot(0, 0) && !board.getSpot(1, 1).equals("-")){
            board.setSpot(0, 0, computer);
            return;
        }

        if(board.getSpot(2, 0).equals(board.getSpot(1,1)) && board.isValidSpot(0, 2) && !board.getSpot(2, 0).equals("-")){
            board.setSpot(0, 2, computer);
            return;
        }
        if(board.getSpot(2, 0).equals(board.getSpot(0,2)) && board.isValidSpot(1, 1) && !board.getSpot(2, 0).equals("-")){
            board.setSpot(1, 1, computer);
            return;
        }
        if(board.getSpot(1, 1).equals(board.getSpot(2,2)) && board.isValidSpot(2, 0) && !board.getSpot(1, 1).equals("-")){
            board.setSpot(0, 0, computer);
            return;
        }
        if(board.getSpot(0, 2).equals(board.getSpot(1,1)) && board.isValidSpot(2, 0) && !board.getSpot(0, 2).equals("-")){
            board.setSpot(2, 0, computer);
            return;
        }

        if(board.isValidSpot(1,1)){
            board.setSpot(1, 1, computer);
            return;
        }
        easyComputerTurn();
    }


     private static boolean isWinner(){
        for(int i = 0; i < board.backingBoard.length; i ++){
            if(!board.getSpot(i,0).equals("-") && board.getSpot(i, 0).equals(board.getSpot(i, 1)) && board.getSpot(i, 0).equals(board.getSpot(i, 2))){
                return true;
            }
        }
        for(int j = 0; j < board.backingBoard.length; j++){
            if(!board.getSpot(0,j).equals("-") &&board.getSpot(0, j).equals(board.getSpot(1, j)) && board.getSpot(0, j).equals(board.getSpot(2, j))){
                return true;
            }
        }
        if(!board.getSpot(0,0).equals("-") &&board.getSpot(0, 0).equals(board.getSpot(1, 1)) && board.getSpot(2, 2).equals(board.getSpot(0, 0))){
            return true;
        }
        if(!board.getSpot(2,0).equals("-") &&board.getSpot(2, 0).equals(board.getSpot(1, 1)) && board.getSpot(2, 0).equals(board.getSpot(0, 2))){
            return true;
        }

        return false;
    }

     private  static boolean isDraw(){
        for(int i = 0; i < board.backingBoard.length; i++){
            for(int j = 0; j < board.backingBoard[i].length; j++){
                if(board.isValidSpot(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    private static void printWinner(){
        for(int i = 0; i < board.backingBoard.length; i ++){
            if(!board.getSpot(i,0).equals("-") && board.getSpot(i, 0).equals(board.getSpot(i, 1)) && board.getSpot(i, 0).equals(board.getSpot(i, 2))){
                System.out.println(board.getSpot(i, 0) + " wins!");
                return;
            }
        }
        for(int j = 0; j < board.backingBoard.length; j++){
            if(!board.getSpot(0,j).equals("-") &&board.getSpot(0, j).equals(board.getSpot(1, j)) && board.getSpot(0, j).equals(board.getSpot(2, j))){
                System.out.println(board.getSpot(0, j) + " wins!");
                return;
            }
        }
        if(!board.getSpot(0,0).equals("-") &&board.getSpot(0, 0).equals(board.getSpot(1, 1)) && board.getSpot(2, 2).equals(board.getSpot(0, 0))){
            System.out.println(board.getSpot(0, 0) + " wins!");
            return;
        }
        if(!board.getSpot(2,0).equals("-") && board.getSpot(2, 0).equals(board.getSpot(1, 1)) && board.getSpot(2, 0).equals(board.getSpot(0, 2))){
            System.out.println(board.getSpot(2, 0) + " wins!");
            return;
        }

        System.out.println("we have a draw");
    }

    public static void main(String[] args) {
        System.out.println("Hi! Welcome to Tic Tac Toe");
        System.out.println("please choose single player or two player");
        Scanner scanner = new Scanner(System.in);
        String numPlayers = scanner.nextLine().toLowerCase();
        while(!numPlayers.equals("single player") && !numPlayers.equals("two player")){
            System.out.println("please choose single player or two player");
            numPlayers = scanner.nextLine().toLowerCase();
        }
        if(numPlayers.equals("two player")){
            Player x = new Player("x");
            Player o = new Player("o");
            board.printBoard();

            while(!isWinner() && !isDraw()){
                System.out.println("player x please go");
                playerTurn(x);
                board.printBoard();
                if(!isWinner() && !isDraw()){
                    System.out.println("player o please go");
                    playerTurn(o);
                    board.printBoard();
                }
            }
            printWinner();
            System.out.println("\nwould you like to play again Y or N?");
            scanner = new Scanner(System.in);
            String choice = scanner.next().toUpperCase();
            while(!choice.equals("Y") && !choice.equals("N")){
                System.out.println("type Y to play again, N to quit");
                choice = scanner.next().toUpperCase();
            }
            if(choice.equals("Y")){
                board = new Board();
                main(new String[1]);
            }
            else {
                System.out.println("Thank you for playing");
                System.exit(0);
            }
        }
        else {

            start();
            board.printBoard();
            if (player.getLetter().equals("X")) {
                playerTurn(player);
                board.printBoard();
                if (level.equals("easy")) {
                    easyComputerTurn();
                } else if(level.equals("medium")) {
                    mediumComputerTurn();
                }
                else {
                    hardComputerTurn();
                }
                board.printBoard();
            } else {
                if (level.equals("easy")) {
                    easyComputerTurn();
                } else if(level.equals("medium")) {
                    mediumComputerTurn();
                }
                else {
                    hardComputerTurn();
                }
                board.printBoard();
            }
            while (!isWinner() && !isDraw()) {
                playerTurn(player);
                board.printBoard();
                if (!isWinner() && !isDraw()) {
                    if (level.equals("easy")) {
                        easyComputerTurn();
                    } else {
                        mediumComputerTurn();
                    }
                    board.printBoard();
                } else {
                    break;
                }
            }
            printWinner();
            System.out.println("\nwould you like to play again Y or N?");
            scanner = new Scanner(System.in);
            String choice = scanner.next().toUpperCase();
            while (!choice.equals("Y") && !choice.equals("N")) {
                System.out.println("type Y to play again, N to quit");
                choice = scanner.next().toUpperCase();
            }
            if (choice.equals("Y")) {
                board = new Board();
                main(new String[1]);
            } else {
                System.out.println("Thank you for playing");
                System.exit(0);
            }
        }
    }
}
