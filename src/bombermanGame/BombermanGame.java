package bombermanGame;

import java.util.*;

enum CellType {
    EMPTY, WALL, DESTRUCTIBLE_WALL, BOMB, EXPLOSION, PLAYER, ENEMY
}

class Cell {
    private CellType type;

    public Cell(CellType type) {
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public char getSymbol() {
        switch (type) {
            case EMPTY: return '.';
            case WALL: return '#';
            case DESTRUCTIBLE_WALL: return '*';
            case BOMB: return 'B';
            case EXPLOSION: return 'X';
            case PLAYER: return 'P';
            case ENEMY: return 'E';
            default: return ' ';
        }
    }
}

class Bomb {
    private int timer;

    public Bomb(int timer) {
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void decreaseTimer() {
        if (timer > 0) timer--;
    }
}

class Player {
    private int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void move(int newX, int newY, Board board) {
        if (board.isValidCell(newX, newY) && board.getCellType(newX, newY) == CellType.EMPTY) {
            board.updateCell(x, y, CellType.EMPTY);
            x = newX;
            y = newY;
            board.updateCell(x, y, CellType.PLAYER);
        }
    }

    public void placeBomb(Board board) {
        board.placeBomb(x, y, 3);
    }
}

class Board {
    private int rows, cols;
    private Cell[][] grid;
    private Map<String, Bomb> bombMap;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        this.bombMap = new HashMap<>();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(CellType.EMPTY);
            }
        }

        grid[1][1] = new Cell(CellType.WALL);
        grid[2][2] = new Cell(CellType.DESTRUCTIBLE_WALL);
    }

    public boolean isValidCell(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public CellType getCellType(int x, int y) {
        return grid[x][y].getType();
    }

    public void updateCell(int x, int y, CellType type) {
        grid[x][y].setType(type);
    }

    public void placeBomb(int x, int y, int timer) {
        if (isValidCell(x, y) && grid[x][y].getType() == CellType.EMPTY) {
            grid[x][y].setType(CellType.BOMB);
            bombMap.put(x + "," + y, new Bomb(timer));
        }
    }

    public void update() {
        List<int[]> explodedCells = new ArrayList<>();

        for (Map.Entry<String, Bomb> entry : bombMap.entrySet()) {
            String[] parts = entry.getKey().split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            Bomb bomb = entry.getValue();
            bomb.decreaseTimer();

            if (bomb.getTimer() == 0) {
                explodedCells.add(new int[]{x, y});
            }
        }

        for (int[] pos : explodedCells) {
            triggerExplosion(pos[0], pos[1]);
        }
    }

    private void triggerExplosion(int x, int y) {
        int explosionRadius = 2;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        grid[x][y].setType(CellType.EXPLOSION);

        for (int[] dir : directions) {
            for (int i = 1; i <= explosionRadius; i++) {
                int newX = x + dir[0] * i;
                int newY = y + dir[1] * i;

                if (isValidCell(newX, newY)) {
                    if (grid[newX][newY].getType() == CellType.WALL) break;
                    if (grid[newX][newY].getType() == CellType.DESTRUCTIBLE_WALL) {
                        grid[newX][newY].setType(CellType.EXPLOSION);
                        break;
                    }
                    grid[newX][newY].setType(CellType.EXPLOSION);
                }
            }
        }

        bombMap.remove(x + "," + y);
    }

    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class BombermanGame {
    public static void main(String[] args) {
        Board board = new Board(7, 7);
        Player player = new Player(3, 3);
        board.updateCell(3, 3, CellType.PLAYER);

        board.printBoard();

        player.placeBomb(board);

        for (int t = 0; t < 4; t++) {
            System.out.println("Time: " + t);
            board.update();
            board.printBoard();
        }
    }
}
