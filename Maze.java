import java.util.ArrayList;

public class Maze {

    private char[][] grid = {
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
        {'#','.','.','.','#','.','.','.','.','.','#','.','.','.','#'},
        {'#','.','#','.','#','.','#','#','#','.','#','.','#','.','#'},
        {'#','.','#','.','.','.','#','.','.','.','#','.','.','.','#'},
        {'#','.','#','#','#','#','#','.','#','#','#','.','#','.','#'},
        {'#','.','.','.','.','.','#','.','.','.','#','.','.','.','#'},
        {'#','#','#','#','#','.','#','#','#','.','#','#','#','.','#'},
        {'#','.','.','.','#','.','.','.','#','.','.','.','#','.','#'},
        {'#','.','#','.','#','#','#','.','#','#','#','.','#','.','#'},
        {'#','.','#','.','.','.','#','.','.','.','#','.','.','.','#'},
        {'#','.','#','#','#','#','#','.','#','#','#','#','#','X','#'},
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
    };

    public void printMaze(Player player, ArrayList<Enemy> enemies) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                boolean printed = false;

                if (i == player.getRow() && j == player.getCol()) {
                    System.out.print("P ");
                    printed = true;
                }

                for (Enemy e : enemies) {
                    if (i == e.getRow() && j == e.getCol()) {
                        System.out.print("E ");
                        printed = true;
                        break;
                    }
                }

                if (!printed) {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean isWalkable(int row, int col) {
        return grid[row][col] != '#';
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public void clearCell(int row, int col) {
        grid[row][col] = '.';
    }
}