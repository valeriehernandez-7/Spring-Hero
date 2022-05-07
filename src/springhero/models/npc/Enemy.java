package springhero.models.npc;

import springhero.models.main.Cell;

import javax.swing.ImageIcon;
import java.awt.Point;

public class Enemy extends NPC {

    private boolean defeated;
    private Point target;
    private int power;

    public Enemy(Cell cell) {
        this.power = 1;
        this.defeated = false;
        this.sprite.setIcon(new ImageIcon(ENEMIES_SRC + "enemy-d.png"));
        this.setPosition(cell);
    }

    public void moveTo() {
        if (position.x != target.x) {
            position.x += ((target.x - position.x) / Math.abs(target.x - position.x));
        }
        if (position.y != target.y) {
            position.y += ((target.y - position.y) / Math.abs(target.y - position.y));
        }
    }
}