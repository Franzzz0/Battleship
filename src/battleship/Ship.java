package battleship;

import java.util.ArrayList;

public class Ship {
    private String name;
    private int size;
    private boolean isAlive;
    private ArrayList<String> cells;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.isAlive = true;
        this.cells = new ArrayList<>();
    }

    public void addCell(String cell) {
        this.cells.add(cell);
    }

    public String getName() {
        return name;
    }

    public void removeCell(String cell) {
        this.cells.remove(cell);
        if (cells.size() == 0) {
            isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public ArrayList<String> getCells() {
        return cells;
    }
}
