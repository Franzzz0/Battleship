package battleship;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private GamePlay player1;
    private GamePlay player2;
    private byte turn;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.player1 = new GamePlay();
        this.player2 = new GamePlay();
        this.turn = 1;
    }

    public void start() {

        addShips(player1);
        playerChange();

        addShips(player2);
        playerChange();

        this.startGame();

    }

    private void startGame() {
        while (true) {
            GamePlay player = (turn == 1) ? player1 : player2;
            GamePlay opponent = (turn != 1) ? player1 : player2;
            if (player.isGameOver()) {
                break;
            }
            opponent.getPlayerGrid().printGridFogged();
            System.out.println("---------------------");
            player.getPlayerGrid().printGrid();
            System.out.printf("\nPlayer %d, it's your turn:\n", turn);
            System.out.print("\n> ");
            String input = scanner.nextLine();

            char c = input.charAt(0);
            int n = Integer.parseInt(input.substring(1));

            if (c < 'A' || c > 'J' || n < 1 || n > 10) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
            String targetHit = opponent.shoot(c, n);

            if (opponent.isGameOver()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                break;
            }

            if (targetHit.equals("M")) {
                System.out.println("\nYou missed!");
            } else if (opponent.getShips().get(targetHit) == null) {
                System.out.println("\nYou sank a ship!");
            } else {
                System.out.println("\nYou hit a ship!");
            }
            playerChange();
        }

    }

    private void playerChange() {
        System.out.print("\nPress Enter and pass the move to another player");
        String input = scanner.nextLine();
        turn = (byte) ((turn == 1) ? 2 : 1);
        System.out.println("...");
    }

    private void addShips(GamePlay player) {
        System.out.printf("Player %d, place your ships on the game field\n\n", turn);
        player.getPlayerGrid().printGrid();
        for (ShipEnum ship : ShipEnum.values()) {
            this.addShip(player, ship.getName(), ship.getSize());
        }
    }

    private void addShip(GamePlay player, String name, int size) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n", name, size);

        while (true) {
            System.out.print("\n> ");
            String[] parts = scanner.nextLine().split(" ");
            char c1 = parts[0].charAt(0);
            int n1 = Integer.parseInt(parts[0].substring(1));
            char c2 = parts[1].charAt(0);
            int n2 = Integer.parseInt(parts[1].substring(1));

            int shipSize = player.getShipSize(c1, n1, c2, n2);

            if (shipSize == -1) {
                System.out.println("\nError! Wrong ship location! Try again:");
                continue;
            } else if (shipSize != size) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n", name);
                continue;
            }

            if (player.addShip(name, size, c1, n1, c2, n2)) {
                System.out.println();
                player.getPlayerGrid().printGrid();
                break;
            } else {
                System.out.println("\nError! You placed it too close to another one. Try again:");
            }
        }
    }
}
