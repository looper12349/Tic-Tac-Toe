package org.arjun.strategies;

import org.arjun.models.Board;
import org.arjun.models.Move;

import java.util.HashMap;

public class WinningAlgorithm {
    // strategy design pattern should be used when there are multiple ways to implement
    // something

    /*
    * eg there are multiple ways to sort something
    * so you can create a sort interface and implement its multiple ways
    * quick sort,
    * merge sort,
    * bubble sort, etc
    * */

    // row number => HashMap for that row
    HashMap<Integer, HashMap<Character, Integer>> rowMaps = new HashMap<>();
    HashMap<Integer, HashMap<Character, Integer>> colMaps = new HashMap<>();
    HashMap<Character, Integer> leftDiagonalMap = new HashMap<>();
    HashMap<Character, Integer> rightDiagonalMap = new HashMap<>();

    // ideally the winning algorithm for rows / cols / diagonals should be in separate
    // class according to strategy design pattern

    public boolean checkWinner(Board board, Move move){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character ch = move.getPlayer().getSymbol().getChar();

        // implementation for checking winner in rows
        if(!rowMaps.containsKey(row)){
            rowMaps.put(row, new HashMap<>());
        }
        HashMap<Character, Integer> curRowMap = rowMaps.get(row);
        if(curRowMap.containsKey(ch)){
            curRowMap.put(ch, curRowMap.get(ch) + 1);
        } else{
            curRowMap.put(ch, 1);
        }

        if(curRowMap.get(ch) == board.getSize()){
            return true;
        }

        // implementation for checking winner in cols
        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }
        HashMap<Character, Integer> curColMap = colMaps.get(col);
        if(curColMap.containsKey(ch)){
            curColMap.put(ch, curColMap.get(ch) + 1);
        } else{
            curColMap.put(ch, 1);
        }

        if(curColMap.get(ch) == board.getSize()){
            return true;
        }

        // implementation for checking winner in left diagonal
        if(row == col){
            if(leftDiagonalMap.containsKey(ch)){
                leftDiagonalMap.put(ch, leftDiagonalMap.get(ch) + 1);
            } else{
                leftDiagonalMap.put(ch, 1);
            }

            if(leftDiagonalMap.get(ch) == board.getSize()){
                return true;
            }
        }

        // implementation for checking winner in right diagonal
        if(row + col == board.getSize()-1){
            if(rightDiagonalMap.containsKey(ch)){
                rightDiagonalMap.put(ch, rightDiagonalMap.get(ch) + 1);
            } else{
                rightDiagonalMap.put(ch, 1);
            }

            if(rightDiagonalMap.get(ch) == board.getSize()){
                return true;
            }
        }

        return false;
    }
}
