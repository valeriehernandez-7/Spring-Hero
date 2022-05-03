package springhero.models.main;

import java.awt.Point;

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
        Point position = new Point();
        for (int row = 0; row < grid.length; row++) {
//            System.out.println("\n");
            for (int col = 0; col < grid[row].length; col++) {
                position.x = (MAP_XPOS + (CELL_WIDTH / 2)) + (col * CELL_WIDTH);
                position.y = (MAP_YPOS + (CELL_HEIGHT / 2)) + (row * CELL_HEIGHT);
                grid[row][col] = new Cell(new Point(row, col), position);
//                System.out.print("[\t] ID [" + grid[row][col].getID().x + "][" + grid[row][col].getID().y + "] POS (" + grid[row][col].getPosition().x + "," + grid[row][col].getPosition().y + ")\t\t");
            }
        }
    }
}