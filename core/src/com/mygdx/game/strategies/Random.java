package com.mygdx.game.strategies;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.board.Board;
import com.mygdx.game.board.Cell;
import com.mygdx.game.board.CellPosition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 8.8.2016.
 */
public class Random implements Strategy {
    Cell.CellValue cV;
    public static ArrayList<CellPosition> forbiddenS = new ArrayList<CellPosition>();
    public static ArrayList<CellPosition> forbiddenO = new ArrayList<CellPosition>();
    public CellPosition CP=null;
    public CellPosition determineBestPosition(Board board) {
        List<CellPosition> EMPTYCells = new LinkedList<CellPosition>();
        for (int i = 0; i < board.getNumRow(); i++) {
            for (int m = 0; m < board.getNumColumn(); m++) {
                if (board.cellAtPosition(new CellPosition(i, m)).getValue() == Cell.CellValue.EMPTY) {
                    EMPTYCells.add(new CellPosition(i, m));
                }
            }
        }
        int randomIndex = (int) (Math.random() * ((EMPTYCells.size() - 1) + 0));
        CP = EMPTYCells.get(randomIndex);
        if(CP!=null)
        {
            boolean x = isForbidden(board, CP);
            System.out.println(" aaaaaaaaaaaaaaa "+x);
        }
        return CP;
    }

    @Override
    public Cell.CellValue determineValue(Board board) {
        cV = Cell.CellValue.EMPTY;
        int randomIndex = MathUtils.random(1, 50);

        if ((randomIndex % 2) != 0)
            cV = Cell.CellValue.O_cell;
        else
            cV = Cell.CellValue.S_cell;

        if(CP!=null)
        {
            boolean x = isForbidden(board, CP);
            System.out.println(" aaaaaaaaaaaaaaa "+x);
        }


        return cV;
    }

    public Cell.CellValue determineValue(Board board,Boolean rule) {
        cV = Cell.CellValue.EMPTY;
        int randomIndex = MathUtils.random(1, 50);

        if ((randomIndex % 2) != 0)
            cV = Cell.CellValue.O_cell;
        else
            cV = Cell.CellValue.S_cell;
        if(CP!=null)
        {
            boolean x = isForbidden(board, CP);
        }
        if(rule)
        {
            for (int n = 0; n < forbiddenO.size(); n++)
            {
                if (CP.getRow() == forbiddenO.get(n).getRow() && CP.getColumn() == forbiddenO.get(n).getColumn()) {
                    cV = Cell.CellValue.S_cell;
                }}
        }


        return cV;
    }

    public boolean isForbidden(Board board, CellPosition CPV) {
        boolean b = false;boolean f=false;boolean g=false;
        for (int x = 0; x < board.getNumRow(); x++) {
            for (int y = 0; y < board.getNumColumn(); y++) {
                if (board.cellAtPosition(new CellPosition(x, y)).getValue() == Cell.CellValue.S_cell) {
                    if (x - 2 >= 0) {
                        forbiddenS.add(new CellPosition(x - 2, y));
                    }
                    if ((x - 2) >= 0 && (y - 2) >= 0) {
                        forbiddenS.add(new CellPosition(x - 2, y - 2));
                    }
                    if ((x - 2) >= 0 && (y + 2) < board.getNumColumn()) {
                        forbiddenS.add(new CellPosition(x - 2, y + 2));
                    }
                    if ((x + 2) < board.getNumRow()) {
                        forbiddenS.add(new CellPosition(x + 2, y));
                    }
                    if ((x + 2) < board.getNumRow() && (y - 2) >= 0) {
                        forbiddenS.add(new CellPosition(x + 2, y - 2));
                    }
                    if ((x + 2) < board.getNumRow() && (y + 2) < board.getNumColumn()) {
                        forbiddenS.add(new CellPosition(x + 2, y + 2));
                    }
                    /////////////////////////////////////////
                    if (x - 1 >= 0) {
                        forbiddenO.add(new CellPosition(x - 1, y));
                    }
                    if ((x - 1) >= 0 && (y - 1) >= 0) {
                        forbiddenO.add(new CellPosition(x - 1, y - 1));
                    }
                    if ((x - 1) >= 0 && (y + 1) < board.getNumColumn()) {
                        forbiddenO.add(new CellPosition(x - 1, y + 1));
                    }
                    if ((x + 1) < board.getNumRow()) {
                        forbiddenO.add(new CellPosition(x + 1, y));
                    }
                    if ((x + 1) < board.getNumRow() && (y - 1) >= 0) {
                        forbiddenO.add(new CellPosition(x + 1, y - 1));
                    }
                    if ((x + 1) < board.getNumRow() && (y + 1) < board.getNumColumn()) {
                        forbiddenO.add(new CellPosition(x + 1, y + 1));
                    }
                }
                if (board.cellAtPosition(new CellPosition(x, y)).getValue() == Cell.CellValue.O_cell) {
                    if (x - 1 >= 0) {
                        forbiddenS.add(new CellPosition(x - 1, y));
                    }
                    if ((x - 1) >= 0 && (y - 1) >= 0) {

                        forbiddenS.add(new CellPosition(x - 1, y - 1));
                    }
                    if ((x - 1) >= 0 && (y + 1) < board.getNumColumn()) {
                        forbiddenS.add(new CellPosition(x - 1, y + 1));
                    }
                    if ((x + 1) < board.getNumRow()) {
                        forbiddenS.add(new CellPosition(x + 1, y));
                    }
                    if ((x + 1) < board.getNumRow() && (y - 1) >= 0) {
                        forbiddenS.add(new CellPosition(x + 1, y - 1));
                    }
                    if ((x + 1) < board.getNumRow() && (y + 1) < board.getNumColumn()) {
                        forbiddenS.add(new CellPosition(x + 1, y + 1));
                    }
                }
            }
        }

        for (int n = 0; n < forbiddenO.size(); n++)
        {
            if (CPV.getRow() == forbiddenO.get(n).getRow() && CPV.getColumn() == forbiddenO.get(n).getColumn()) {
                cV = Cell.CellValue.S_cell;
                f=true;
                b = true;
            }}

        for (int m = 0; m < forbiddenS.size(); m++)
            if (CPV.getRow() == forbiddenS.get(m).getRow() && CPV.getColumn() == forbiddenS.get(m).getColumn()) {
                b = true;
                g=true;
                cV = Cell.CellValue.O_cell;
            }


        return b;


        }
    }
