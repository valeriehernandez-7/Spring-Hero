package springhero.models.npc;

import springhero.models.main.Cell;
import springhero.models.main.Constants;

import javax.swing.JLabel;
import java.awt.Point;

public class NPC implements Constants {
    protected JLabel sprite;
    protected Point position;
    protected int points;

    public JLabel getSprite() {
        return sprite;
    }

    public void setSprite(JLabel sprite) {
        this.sprite = sprite;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Cell cell) {
        this.position = cell.getID();
        this.sprite.setLocation(cell.getPosition());
        cell.setEntity(getClass().getSimpleName());
    }

    public int getPoints() {
        return points;
    }
}