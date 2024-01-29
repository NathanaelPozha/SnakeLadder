package SnakeLadder.src.com.gamelauncher.boardgame.player.dice;

public class Dice {
    
    private int diceResult;
    private final int DICE_NUMBER = 6;

    public void diceRoll(){
        diceResult = (int) (Math.random() * DICE_NUMBER) + 1;
    }

    public int getResult(){
        return diceResult;
    }

}
