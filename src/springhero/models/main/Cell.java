package springhero.models.main;

import java.awt.Point;

public class Cell {
    private final Point ID;
    private final Point position;
    private String entity;

    public Cell(Point ID, Point position) {
        this.ID = ID;
        this.position = position;
        this.entity  = "Cell";
    }

    public Point getID() {
        return ID;
    }

    public Point getPosition() {
        return position;
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