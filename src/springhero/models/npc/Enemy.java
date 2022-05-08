package springhero.models.npc;

import springhero.models.main.Cell;

import javax.swing.ImageIcon;
import java.awt.Point;

public class Enemy extends NPC {

    private boolean defeated;
    private Point target;
    private int power;

    public Enemy(Cell cell) {
        this.defeated = false;
        this.power = 1;
        this.upImg = new ImageIcon(ENEMIES_SRC + "enemy-w.png");
        this.downImg = new ImageIcon(ENEMIES_SRC + "enemy-s.png");
        this.leftImg = new ImageIcon(ENEMIES_SRC + "enemy-a.png");
        this.rightImg = new ImageIcon(ENEMIES_SRC + "enemy-d.png");
        this.setSprite(view.RIGHT);
        this.setPosition(cell);
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void move() {
        // TODO : check if next position is available
        if (position.x != target.x) {
            position.x += ((target.x - position.x) / Math.abs(target.x - position.x));
        }
        if (position.y != target.y) {
            position.y += ((target.y - position.y) / Math.abs(target.y - position.y));
        }
    }
}