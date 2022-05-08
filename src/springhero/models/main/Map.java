package springhero.models.main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map implements Constants {

    private final Cell[][] grid;

    public Map() {
        grid = new Cell[MAP_GRID_ROWS][MAP_GRID_COLS];
        cellPositioning();
        update();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private void cellPositioning() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Point position = new Point((MAP_XPOS + (CELL_WIDTH / 2)) + (col * CELL_WIDTH), (MAP_YPOS + (CELL_HEIGHT / 2)) + (row * CELL_HEIGHT));
                grid[row][col] = new Cell(new Point(row, col), position);
            }
        }
    }

    public Cell getCell(Point ID) {
        return grid[ID.x][ID.y];
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    private List<Point> getPositions(Point origin) {
        Point[] directions = new Point[]{
                new Point(origin.x - 1, origin.y), // UP
                new Point(origin.x + 1, origin.y), // DOWN
                new Point(origin.x, origin.y - 1), // LEFT
                new Point(origin.x, origin.y + 1), // RIGHT
        };
        List<Point> positions = new ArrayList<>();
        for (Point direction : directions) {
            if (0 <= direction.x & direction.x <= MAP_GRID_ROWS - 1) {
                if (0 <= direction.y & direction.y <= MAP_GRID_COLS - 1) {
                    positions.add(direction);
                }
            }
        }
        return positions;
    }

    private List<Cell> getNeighbors(Point origin) {
        List<Cell> neighbors = new ArrayList<>();
        List<Point> positions = getPositions(origin);
        if (!positions.isEmpty()) {
            for (Point position : positions) {
                neighbors.add(getCell(position));
            }
        }
        return neighbors;
    }

    public void update() {
        for (Cell[] container : grid) {
            for (Cell cell : container) {
                cell.setNeighbors(getNeighbors(cell.getID()));
            }
        }
    }
}