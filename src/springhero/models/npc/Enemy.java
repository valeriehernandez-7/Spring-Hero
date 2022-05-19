package springhero.models.npc;

import springhero.models.hero.Hero;
import springhero.models.main.Cell;
import springhero.models.main.Map;

import javax.swing.ImageIcon;
import java.awt.Point;

public class Enemy extends NPC {

    private boolean defeated;

    public Enemy(Cell cell, Hero hero) {
        this.defeated = false;
        this.upImg = new ImageIcon(ENEMIES_SRC + "enemy-w.png");
        this.downImg = new ImageIcon(ENEMIES_SRC + "enemy-s.png");
        this.leftImg = new ImageIcon(ENEMIES_SRC + "enemy-a.png");
        this.rightImg = new ImageIcon(ENEMIES_SRC + "enemy-d.png");
        this.setTarget(hero.getPosition());
        this.updateSprite(cell);
        hero.attachObserver(this);
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
        if (defeated) {
            getSprite().setVisible(false);
        }
    }

    public void move(Map map) {
        if (!this.available) {
            Cell currentCell = map.getCell(this.position);
            Point nextPosition = new Point();
            // TODO : check if next position is available
            if (this.position.x != this.target.x) {
                nextPosition.x = this.position.x + ((this.target.x - this.position.x) / Math.abs(this.target.x - this.position.x));
            }
            if (this.position.y != this.target.y) {
                nextPosition.y = this.position.y + ((this.target.y - this.position.y) / Math.abs(this.target.y - this.position.y));
            }
            if (map.isCell(nextPosition)) {
                if (!map.getCell(nextPosition).getEntity().equals("Enemy")) {
                    updateSprite(map.getCell(nextPosition));
                    currentCell.resetEntity();
                }
            }
        } else {
            setDefeated(true);
        }
    }
}