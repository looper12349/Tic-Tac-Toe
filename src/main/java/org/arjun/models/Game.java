package org.arjun.models;

import org.arjun.exceptions.InvalidMoveException;
import org.arjun.strategies.WinningAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player winner;
    private int nextPlayerMoveIndex;
    private WinningAlgorithm winningAlgorithm;

    public Game(int dimension, List<Player> players) {
        this.board = new Board(dimension);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.winner = null;
        this.nextPlayerMoveIndex = 0;
        this.winningAlgorithm = new WinningAlgorithm();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public void printBoard(){
        this.board.printBoard();
    }

    private Boolean validateMove(Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()){
            return false;
        }

        return board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY);
    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);

        System.out.println("It is " + currentPlayer.getPlayerName() + "'s turn");

        // Move that currentPlayer wants to make
        Move move = currentPlayer.makeMove(board);
        // this will return any random cell object that may or maynot belong to board

        // Game will validate the move before executing
        if(!validateMove(move)){
            // throw an exception

            // there is an error in this line, bcoz we are directly extending the
            // Exception class, and this the exception is checked exception
            // and hence we have to add it to the method signature
            throw new InvalidMoveException("Invalid move made by " + currentPlayer.getPlayerName());
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        // this cell we are getting from the board, here the move will be applied
        cellToChange.setPlayer(currentPlayer);
        cellToChange.setCellState(CellState.FILLED);

        // we need this finalMove object bcoz in the earlier move object,
        // it was just used to encapsulate the row and col
        // it didnt belong to the board, after making a move we were supposed to
        // update it on the board

        Move finalMove = new Move(cellToChange, currentPlayer);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex+1)%players.size();

        // check if the current move is the winning move or not
        if(winningAlgorithm.checkWinner(board, move)){
            gameState = GameState.ENDED;
            winner = currentPlayer;
        }
    }
}
