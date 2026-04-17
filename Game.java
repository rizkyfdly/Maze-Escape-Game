import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private Maze maze;
    private ArrayList<Enemy> enemies;
    private boolean enemyAggro = true;

    public Game() {
        player = new Player(1, 1);
        maze = new Maze();

        enemies = new ArrayList<>();

        // 👾 10 MUSUH DISEBAR
        enemies.add(new Enemy(1, 13));
        enemies.add(new Enemy(2, 2));
        enemies.add(new Enemy(3, 10));
        enemies.add(new Enemy(4, 5));
        enemies.add(new Enemy(5, 12));
        enemies.add(new Enemy(6, 3));
        enemies.add(new Enemy(7, 9));
        enemies.add(new Enemy(8, 1));
        enemies.add(new Enemy(9, 13));
        enemies.add(new Enemy(10, 6));
    }

    public void start() {

        Scanner input = new Scanner(System.in);

        while (true) {

            System.out.println("\nHP Kamu: " + player.getHp());
            System.out.println("Enemy: " + enemies.size());

            maze.printMaze(player, enemies);

            System.out.print("Gerak (w/a/s/d): ");
            String move = input.nextLine().toLowerCase();

            int newRow = player.getRow();
            int newCol = player.getCol();

            switch (move) {
                case "w": newRow--; break;
                case "s": newRow++; break;
                case "a": newCol--; break;
                case "d": newCol++; break;
            }

            if (maze.isWalkable(newRow, newCol)) {
                player.setPosition(newRow, newCol);
            }

            // 💊 HEAL
            if (maze.getCell(newRow, newCol) == 'H') {
                player.heal(20);
                maze.clearCell(newRow, newCol);
            }

            // 🎉 WIN
            if (maze.getCell(newRow, newCol) == 'X') {
                System.out.println("🎉 MENANG!");
                break;
            }

            if (enemyAggro) {
                moveEnemies();
            }

            checkBattle(input);
        }
    }

    // 👾 AI FIX (CHASE + RANDOM SPREAD MINIMAL)
    private void moveEnemies() {

        for (Enemy e : enemies) {

            int er = e.getRow();
            int ec = e.getCol();

            int pr = player.getRow();
            int pc = player.getCol();

            int newRow = er;
            int newCol = ec;

            double rand = Math.random();

            // 70% chase
            if (rand < 0.7) {

                if (pr < er && maze.isWalkable(er - 1, ec)) {
                    newRow = er - 1;
                }
                else if (pr > er && maze.isWalkable(er + 1, ec)) {
                    newRow = er + 1;
                }
                else if (pc < ec && maze.isWalkable(er, ec - 1)) {
                    newCol = ec - 1;
                }
                else if (pc > ec && maze.isWalkable(er, ec + 1)) {
                    newCol = ec + 1;
                }
            }

            // 30% random move biar menyebar
            else {
                int dir = (int)(Math.random() * 4);

                if (dir == 0 && maze.isWalkable(er - 1, ec)) newRow = er - 1;
                if (dir == 1 && maze.isWalkable(er + 1, ec)) newRow = er + 1;
                if (dir == 2 && maze.isWalkable(er, ec - 1)) newCol = ec - 1;
                if (dir == 3 && maze.isWalkable(er, ec + 1)) newCol = ec + 1;
            }

            e.setPosition(newRow, newCol);
        }
    }

    // ⚔️ BATTLE SAFE + 💚 REWARD HP
    private void checkBattle(Scanner input) {

        for (int i = enemies.size() - 1; i >= 0; i--) {

            Enemy e = enemies.get(i);

            if (player.getRow() == e.getRow() &&
                player.getCol() == e.getCol()) {

                System.out.println("\n👾 BATTLE!");

                while (player.isAlive() && e.isAlive()) {

                    System.out.println("HP Kamu  : " + player.getHp());
                    System.out.println("HP Musuh : " + e.getHp());

                    System.out.println("1. Serang");
                    System.out.println("2. Lari");
                    System.out.print("Pilih: ");

                    String choice = input.nextLine();

                    if (choice.equals("1")) {

                        e.takeDamage(player.getAttack());

                        // 💀 MUSUH MATI
                        if (!e.isAlive()) {
                            System.out.println("Musuh mati!");
                            enemies.remove(i);

                            // 💚 BONUS HP
                            player.heal(15);
                            System.out.println("💚 Kamu dapat +15 HP!");

                            break;
                        }

                        player.takeDamage(e.getAttack());
                    }

                    else if (choice.equals("2")) {
                        System.out.println("Kamu lari!");
                        enemyAggro = false;
                        return;
                    }
                }
            }
        }
    }
}