package springhero.models.main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Map implements Constants {

    private final Cell[][] grid;

    public Map() {
        grid = new Cell[MAP_GRID_ROWS][MAP_GRID_COLS];
        cellPositioning();
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

    public void update() {
        List<Cell> neighbors = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                // TO DO: calc & add cells to neighbors
                grid[row][col].setNeighbors(neighbors);
            }
        }
    }
}