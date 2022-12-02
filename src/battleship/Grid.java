package battleship;

import java.util.HashMap;

public class Grid {
    private HashMap<Character, HashMap<Integer, Character>> grid;

    public Grid() {
        this.grid = new HashMap<>();
        for (char i = 'A'; i < 'K'; i++) {
            grid.put(i, new HashMap<>());
            for (int j = 1; j < 11; j++) {
                grid.get(i).put(j, '~');
            }
        }
    }

    public HashMap<Character, HashMap<Integer, Character>> getGrid() {
        return grid;
    }

    public void putValue(char letter, int number, char value) {
        grid.get(letter).put(number, value);
    }

    public char getValue(char letter, int number) {
        return grid.get(letter).get(number);
    }

    public void printGrid() {
        System.out.println(this.toString());
    }

    public void printGridFogged() {
        System.out.println(this.toString().replace('O', '~'));
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("  1 2 3 4 5 6 7 8 9 10");
        for (char letter : grid.keySet()) {
            string.append("\n").append(letter);
            for (int i : grid.get(letter).keySet()) {
                string.append(" ").append(grid.get(letter).get(i));
            }
        }
        return string.toString();
    }

    public boolean fieldsAvailable(char c1, int n1, char c2, int n2) {
        if (c1 == c2) {
            if ((n1 != 1 && grid.get(c1).get(n1 - 1).equals('O'))
                    || (n2 != 10 && grid.get(c1).get(n2 + 1).equals('O'))) {
                return false;
            }
            for (int i = n1; i <= n2; i++) {
                if ((c1 != 'A' && grid.get((char) (c1 - 1)).get(i).equals('O'))
                        || (c1 != 'J' && grid.get((char) (c1 + 1)).get(i).equals('O'))
                        || grid.get(c1).get(i).equals('O')) {
                    return false;
                }
            }
            return true;
        } else if (n1 == n2) {
            if ((c1 != 'A' && grid.get((char) (c1 - 1)).get(n1).equals('O'))
                    || (c2 != 'J' && grid.get((char) (c2 + 1)).get(n1).equals('O'))) {
                return false;
            }
            for (char c = c1; c <= c2; c++) {
                if ((n1 != 1 && grid.get(c).get(n1 - 1).equals('O'))
                        || (n1 != 10 && grid.get(c).get(n1 + 1).equals('O'))
                        || grid.get(c).get(n1).equals('O')) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
