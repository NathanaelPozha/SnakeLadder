package SnakeLadder.src.com.gamelauncher.boardgame;

import java.util.*;
import SnakeLadder.src.com.gamelauncher.boardgame.player.Player;

public class BoardGame {

private int numberOfCells = 100;
private int[] snakeLaddersArray = new int[101];
private int numberOfSnake;
private int numberOfLadder;
private int snakeCounter = 0;  
private int ladderCounter = 0;

Scanner scan = new Scanner(System.in);

    public void startSystem() {

        System.out.println("");
        System.out.println("Snake & Ladder Game by Nathanael Pozha");
        
        System.out.println("Please enter the number of players (p): ");
        int numberOfPlayer = scan.nextInt();
        while (numberOfPlayer < 2 || numberOfPlayer > 5){
            System.out.println("Error: Invalid number of player");
            System.out.println("Please enter the valid number of players (p) between 2 and 5: ");
            numberOfPlayer = scan.nextInt();
        }
        Player[] playersArray = new Player[numberOfPlayer];

        System.out.println("Please enter the name of each player");
        for (int p = 0; p < numberOfPlayer; p++){
            System.out.println("Name of Player " + (p+1) + " :");
            String playerName = scan.next();
            playersArray[p] = new Player(p + 1, playerName, p + 1, p + 0);
        }

        System.out.println("");
        System.out.println("Please enter the number of snakes (s): ");
        numberOfSnake = scan.nextInt();
        while (numberOfSnake < 1 || numberOfSnake > 50){
            System.out.println("Error: Invalid number of snake");
            System.out.println("Please enter a valid number of snakes (s) between 1 and 50: ");
            numberOfSnake = scan.nextInt();
        }

        System.out.println("");
        for (int s = 0; s < numberOfSnake; s++){
            System.out.println("Please enter the tail position of snake (" + (s+1) + "): ");
            int finish = scan.nextInt();
            while (finish < 1 || finish >= 90) {
                System.out.println("Error: Invalid tail position of snake(" + (s+1) + "):");
                System.out.println("Please enter a valid tail position of snake(" + (s+1) + "):");
                finish = scan.nextInt();
            }

            System.out.println("Please enter the head position of snake (" + (s+1) + "): ");
            int start = scan.nextInt();
            while (start <= 10 || start > 99) {
                System.out.println("Error: Invalid head position of snake(" + (s+1) + "):");
                System.out.println("Please enter a valid head position of snake(" + (s+1) + "):");
                start = scan.nextInt();
            }

            snakeLaddersArray[start] = finish; 
            System.out.println("");
        }

        System.out.println("");
        System.out.println("Please enter the number of ladder (l): ");
        numberOfLadder = scan.nextInt();
        while (numberOfLadder < 1 || numberOfLadder > 50){
            System.out.println("Error: Invalid number of ladder");
            System.out.println("Please enter a valid number of ladders (l) between 1 and 50: ");
            numberOfLadder = scan.nextInt();
        }

        System.out.println("");
        System.out.println("Please enter each location of the ladders");
        for (int l = 0; l < numberOfLadder; l++){
            System.out.println("Please enter the start position of ladder (" + (l+1) + "): ");
            int start = scan.nextInt();
            while (start <= 1 || start >= 90) {
                System.out.println("Error: Invalid start position of ladder(" + (l+1) + "):");
                System.out.println("Please enter a valid start position of ladder(" + (l+1) + "):");
                start = scan.nextInt();
            }

            System.out.println("Please enter the end position of ladder (" + (l+1) + "): ");
            int finish = scan.nextInt();
            while (finish <= 10 || finish >= 100) {
                System.out.println("Error: Invalid end position of ladder(" + (l+1) + "):");
                System.out.println("Please enter a valid end position of ladder(" + (l+1) + "):");
                finish = scan.nextInt();
            }

            snakeLaddersArray[start] = finish; 
            System.out.println("");
        }

        System.out.println("Base on your input, there will be " + numberOfPlayer + " number of Players, " + numberOfSnake + " number of Snakes, and " + numberOfLadder + " number of Ladders.");
        
        System.out.println("");
        System.out.println("Here are the player details");
        for (int p = 0; p < numberOfPlayer; p++){
            Player player = playersArray[p];
            System.out.println("Player " + player.getNumberOfPlayer() + ": Name - " + player.getPlayerName() + " , Turn number " + player.getPlayerTurn() + ", and default starting position " + player.getPlayerPosition());
        }
        
        System.out.println("");
        System.out.println("The location of each snakes will be read out below");

        for (int i = 0; i < numberOfCells; i++){
            if (snakeLaddersArray[i] != 0 && i > snakeLaddersArray[i]) {
                snakeCounter++;
                System.out.println("Snake " + snakeCounter + ": Head at position " + i + ", Tail at position " + snakeLaddersArray[i]);
            }
        }

        System.out.println("");
        System.out.println("The location of each ladders will be read out below");

        for (int i = 0; i < numberOfCells; i++){
            if (snakeLaddersArray[i] != 0 && i < snakeLaddersArray[i]) {
                ladderCounter++;
                System.out.println("Ladder " + ladderCounter + ": Start at position " + i + ", End at position " + snakeLaddersArray[i]);
            }
        }

        System.out.println("");

        while (true) {
            
            for (int p = 0; p < numberOfPlayer; p++){
            Player player = playersArray[p];
            player.roll();

            if (player.getPlayerPosition() > numberOfCells){
                player.setPlayerPosition(player.getPlayerPosition() - player.getDice_Value());
                System.out.println("It seems that " + player.getPlayerName() + " rolled to high! And will stay at " + player.getPlayerPosition());
            }

            if (snakeLaddersArray[player.getPlayerPosition()] != 0){
                
                if (player.getPlayerPosition() > snakeLaddersArray[player.getPlayerPosition()]){
                    System.out.println("It seems that " + player.getPlayerName() + " hit a snake!");
                    player.setPlayerPosition(snakeLaddersArray[player.getPlayerPosition()]);
                    System.out.println(player.getPlayerName() + " is now going down to " + player.getPlayerPosition());
                }

                if (player.getPlayerPosition() < snakeLaddersArray[player.getPlayerPosition()]){
                    System.out.println("It seems that " + player.getPlayerName() + " hit a ladder!");
                    player.setPlayerPosition(snakeLaddersArray[player.getPlayerPosition()]);
                    System.out.println(player.getPlayerName() + " is now going up to " + player.getPlayerPosition());
                }
            }

            System.out.println("");

            if (player.getPlayerPosition() == numberOfCells){
                System.out.println(player.getPlayerName() + " wins the game!");
                return;
            }
        }
    }
}
}
