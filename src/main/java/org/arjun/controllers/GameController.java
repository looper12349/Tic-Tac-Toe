package org.arjun.controllers;

import org.arjun.exceptions.InvalidMoveException;
import org.arjun.models.Game;
import org.arjun.models.GameState;
import org.arjun.models.Move;
import org.arjun.models.Player;

import java.util.*;

public class GameController {
    public Game startGame(int dimension, List<Player> players) {
        // validate if two players have the same symbol or not
        // if two players have same symbol throw exception

        // TODO
        // throw custom exception: 'Same Symbol Exception'
        return new Game(dimension, players);
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void prinBoard(Game game) {
        game.printBoard();
    }
}
