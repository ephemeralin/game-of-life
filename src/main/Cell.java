package main;

import java.util.ArrayList;

public class Cell {
    private final int x;
    private final int y;
    private boolean alive;
    private boolean willAlive;
    private final Universe universe;
    
    public Cell(int x, int y, boolean alive, Universe universe) {
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.universe = universe;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public void makeAlive(boolean alive) {
        this.alive = alive;
    }
    
    public boolean isWillAlive() {
        return this.willAlive;
    }
    
    public void makeWillAlive(boolean willAlive) {
        this.willAlive = willAlive;
    }
    
    public ArrayList<Cell> getAllNeighbors() {
        ArrayList<Cell> list = new ArrayList<>();
        Cell neighbor;
        int max_x = universe.getMax_X();
        int max_y = universe.getMax_Y();
        
        //left
        neighbor = universe.getCell(x-1, y);
        if (neighbor != null) {list.add(neighbor);}
        
        //left-up
        neighbor = universe.getCell(x-1, y+1);
        if (neighbor != null) {list.add(neighbor);}
        
        //up
        neighbor = universe.getCell(x, y+1);
        if (neighbor != null) {list.add(neighbor);}
        
        //right-up
        neighbor = universe.getCell(x+1, y+1);
        if (neighbor != null) {list.add(neighbor);}
        
        //right
        neighbor = universe.getCell(x+1, y);
        if (neighbor != null) {list.add(neighbor);}
        
        //right-down
        neighbor = universe.getCell(x+1, y-1);
        if (neighbor != null) {list.add(neighbor);}
     
        //down
        neighbor = universe.getCell(x, y-1);
        if (neighbor != null) {list.add(neighbor);}
        
        //left-down
        neighbor = universe.getCell(x-1, y-1);
        if (neighbor != null) {list.add(neighbor);}
        
        return list;
    }
    
    public ArrayList<Cell> getActiveNeighbors() {
        ArrayList<Cell> listActive = getAllNeighbors();
        Cell cell;
        for (int i = 0; i < listActive.size(); i++) {
            cell = listActive.get(i);
            if (!cell.isAlive()) {
                listActive.remove(i);
                i--;
            }
        }
        
        return listActive;
    }

    public int numberOfNeighbors() {
        return getActiveNeighbors().size();
    }
    
}
