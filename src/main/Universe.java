package main;

import java.util.Random;

public class Universe {

    private final int MAX_X;
    private final int MAX_Y;

    Cell[][] theField;

    public Universe(int numberOfColumns, int numberOfRows) {
        MAX_X = numberOfColumns - 1;
        MAX_Y = numberOfRows - 1;
        theField = new Cell[numberOfColumns][numberOfRows];
        for (int x = 0; x < numberOfColumns; x++) {
            for (int y = 0; y < numberOfRows; y++) {
                Cell newCell = new Cell(x, y, false, this);
                theField[x][y] = newCell;
            }
        }
    }

    public int getMax_X() {
        return MAX_X;
    }

    public int getMax_Y() {
        return MAX_Y;
    }

    public Cell getCell(int x, int y) {
        if (x > MAX_X | y > MAX_Y | x < 0 | y < 0) {
            return null;
        }
        return theField[x][y];
    }
    
    public int[][] createDefaultColonyArray() {
        return new int[][] {    {0,0,1,0,0,0,0,0,0,0},
                                {1,0,1,0,0,0,0,0,0,0},
                                {0,1,1,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0,0,0}};
    }
    
    public void initFirstState(int countOfCells) {
        int x;
        int y;

        Random randomiser = new java.util.Random();

        for (int i = 0; i < countOfCells; i++) {

            do {
                x = randomiser.nextInt(MAX_X + 1);
                y = randomiser.nextInt(MAX_Y + 1);
            } while (theField[x][y] != null);

            theField[x][y].makeAlive(true);
        }
    }

    public void initFirstStage(int[][] firstColony) {
        int new_y;
        Cell cell;
        for (int x = 0; x < firstColony.length; x++) {
            for (int y = 0; y < firstColony[x].length; y++) {
                if (firstColony[y][x] == 1) {
                    new_y = mirror(y, firstColony[y].length);
                    cell = getCell(x, new_y);
                    if (cell != null) {
                        cell.makeAlive(true);
                    }
                }
            }
        }
    }

    public void show() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        boolean nextLine;
        for (int i = MAX_Y; i >= 0; i--) {
            nextLine = true;
            for (int j = 0; j <= MAX_X; j++) {
                Cell place = theField[j][i];
                if (place != null && place.isAlive()) {
                    if (nextLine) {
                        nextLine = false;
                        System.out.println();
                        System.out.print("+ ");
                    } else {
                        System.out.print("+ ");
                    }
                } else {
                    if (nextLine) {
                        System.out.println();
                        System.out.print("- ");
                        nextLine = false;
                    } else {
                        System.out.print("- ");
                    }
                }
            }
        }
        System.out.println();
    }

    private int mirror(int y, int length) {
        return length - y - 1;
    }

    public void initNextStage() {
        Cell currentCell;
        for (int x = 0; x < theField.length; x++) {
            for (int y = 0; y < theField[x].length; y++) {
                currentCell = getCell(x, y);
                
                if (currentCell == null) {continue;}
                
                if (!currentCell.isAlive()) {
                    //empty place
                    if (currentCell.numberOfNeighbors() == 3) {
                        //need to appear new Cell?
                        currentCell.makeWillAlive(true);
                    }
                    
                } else {
                    //live place
                    if (currentCell.numberOfNeighbors() == 2 || currentCell.numberOfNeighbors() == 3) {
                        currentCell.makeWillAlive(true);
                    } else {
                        currentCell.makeWillAlive(false);
                    }
                }
            }
        }
        //future gonna be present
        for (int x = 0; x < theField.length; x++) {
            for (int y = 0; y < theField[x].length; y++) {
                currentCell = getCell(x, y);
                currentCell.makeAlive(currentCell.isWillAlive());
            }
        }
    }

}
