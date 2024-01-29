package SnakeLadder.src.com.gamelauncher.boardgame.player;

import SnakeLadder.src.com.gamelauncher.boardgame.player.dice.Dice;

public class Player {
 
    private int numberOfPlayer;
    private String playerName;
    private int playerTurn;
    private int dice_value;
    private int initialPosition;
    private int finalPosition;
    private Dice dice;

    public Player(int numberOfPlayer, String playerName, int playerTurn, int initialPosition){
        this.numberOfPlayer = numberOfPlayer;
        this.playerName = playerName;
        this.playerTurn = playerTurn;
        this.initialPosition = 0;
        dice = new Dice();
    }

    public void roll(){
        dice.diceRoll();
        dice_value = dice.getResult();
        finalPosition = initialPosition + dice_value;
        System.out.println(playerName + " rolled a " + dice_value + " and move from " + initialPosition + " to " + finalPosition);
        initialPosition = finalPosition;
    }

    public int getNumberOfPlayer(){
        return numberOfPlayer;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public void setPlayerPosition(int initialPosition){
        this.initialPosition = initialPosition;
    }

    public int getPlayerPosition(){
        return initialPosition;
    }

    public int getDice_Value(){
        return dice_value;
    }
}
