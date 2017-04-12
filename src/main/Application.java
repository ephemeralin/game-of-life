package main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        int numberOfColumns = 10;
        int numberOfRows = 10;
        Universe theUniverse = new Universe(numberOfColumns, numberOfRows);

        //theUniverse.initFirstStage(2);
        int[][] firstColony = theUniverse.createDefaultColonyArray();
        theUniverse.initFirstStage(firstColony);
        theUniverse.show();

        int i = 0;
        while (i < 15) {
            theUniverse.initNextStage();
            TimeUnit.SECONDS.sleep(1);
            theUniverse.show();
            i++;
        }

        //test-case active neighbors
        //Cell cell = theUniverse.getCell(1, 7);
        //ArrayList<Cell> list = cell.getActiveNeighbors();
    }

}
