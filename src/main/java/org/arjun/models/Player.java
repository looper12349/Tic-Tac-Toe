package org.arjun.models;

import java.util.Scanner;

public class Player {
    private String playerName;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);
    // the scanner is static bcoz only 1 copy of scanner is required, every player doesnt
    // need separate scanner

    public Player(String playerName, Symbol symbol, PlayerType playerType) {
        this.playerName = playerName;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board){
        // Ask the user where they want to place the symbol

        // to take the user input we need scanner object, if we create here
        // then the scanner object will be created multiple times while executing each move

        System.out.println("Please enter row no. where you want to make the move: ");
        int row = scanner.nextInt();

        System.out.println("Please enter col no. where you want to make the move: ");
        int col = scanner.nextInt();

        return new Move(new Cell(row, col), this);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
