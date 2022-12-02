package battleship;
import java.util.HashMap;

public class GamePlay {
    private Grid playerGrid;
    private HashMap<String, Ship> ships;
    private boolean gameOver;

    public GamePlay() {
        this.playerGrid = new Grid();
        this.ships = new HashMap<>();
        this.gameOver = false;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Grid getPlayerGrid() {
        return playerGrid;
    }

    public String shoot(char c, int n) {
        String cellHit = "" + c + n;
        for (Ship ship : ships.values()) {
            if (ship.getCells().contains(cellHit)) {
                this.playerGrid.putValue(c, n, 'X');
                ship.removeCell(cellHit);
                if (!ship.isAlive()) {
                    ships.remove(ship.getName());
                    gameOver = ships.size() == 0;
                }
                return ship.getName();
            }
        }
        if (this.playerGrid.getValue(c, n) == 'X') {
            return "X";
        }
        this.playerGrid.putValue(c, n, 'M');
        return "M";
    }


    public boolean addShip(String name, int size, char c1, int n1, char c2, int n2) {
        if (c2 < c1) {
            char oldC1 = c1;
            c1 = c2;
            c2 = oldC1;
        }
        if (n2 < n1) {
            int oldN1 = n1;
            n1 = n2;
            n2 = oldN1;
        }
        if (!this.playerGrid.fieldsAvailable(c1, n1, c2, n2)) {
            return false;
        } else {
            this.ships.put(name,new Ship(name, size));
            for (char c = c1; c <= c2; c++) {
                for (int i = n1; i <= n2; i++) {
                    this.playerGrid.putValue(c, i, 'O');
                    String cell = "" + c + i;
                    this.ships.get(name).addCell(cell);
                }
            }
            return true;
        }
    }

    public int getShipSize(char c1, int n1, char c2, int n2) {
        if (c1 == c2) {
            return Math.abs(n1 - n2) + 1;
        } else if (n1 == n2) {
            return Math.abs(c1 - c2) + 1;
        } else {
            return -1;
        }
    }

    public HashMap<String, Ship> getShips() {
        return ships;
    }
}
