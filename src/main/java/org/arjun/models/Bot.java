package org.arjun.models;

import java.util.List;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String playerName, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel) {
        super(playerName, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }
    // initially this class was throwing error bcoz we had defined custom constructor of the
    // parent.
    // To handle this you have to create a custom constructor of child class as well


    // here comes the concept of strategy design pattern,
    // we create an interface and then create design patterns for easyPlayingStrategy,
    // mediumPlayingStrategy, hardPlayingStrategy

    // but we dont know the design patterns hence we are hard coding it

    @Override
    public Move makeMove(Board board) {
        // Find the first empty cell and make the move there

        // now when we call the makeMove method, which method triggers?
        // bot one or the player one?
        // it depends on which object is present in the currentPlayer reference
        // this is called as runtime polymorphism
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(cell, this);
                }
            }
        }
        return null;
    }
}
