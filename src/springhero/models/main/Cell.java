package springhero.models.main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Cell model class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 */
public class Cell {

    private final Point ID;
    private final Point position;
    private List<Cell> neighbors;
    private String entity;

    public Cell(Point ID, Point position) {
        this.ID = ID;
        this.position = position;
        this.neighbors = new ArrayList<>();
        this.entity = "Cell";
    }

    public Point getID() {
        return ID;
    }

    public Point getPosition() {
        return position;
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Cell> neighbors) {
        this.neighbors = neighbors;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void resetEntity() {
        this.entity = "Cell";
    }
}