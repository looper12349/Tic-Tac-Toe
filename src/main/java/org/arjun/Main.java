package org.arjun;

import org.arjun.controllers.GameController;
import org.arjun.exceptions.InvalidMoveException;
import org.arjun.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidMoveException {

        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Please enter the board size: ");
        int dimensions = sc.nextInt();
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Ayaan",  new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot("Bot", new Symbol('O'), PlayerType.BOT, BotDifficultyLevel.EASY));

        Game game = gameController.startGame(dimensions, players);
        // gameController.printBoard(game);

        // we will keep on playing the game while its status is IN_PROGRESS
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            // what does it mean to keep continuing the game?

            // 1. Print the board
            gameController.prinBoard(game);

            // 2. Players' turn
            gameController.makeMove(game);
        }

        if(!game.getGameState().equals(GameState.ENDED)){
            game.setGameState(GameState.ENDED);
            System.out.println("Game DRAW");
        } else{
            gameController.prinBoard(game);
            System.out.println("Player " + gameController.getWinner(game).getPlayerName() + " is the WINNER");
        }
    }
}