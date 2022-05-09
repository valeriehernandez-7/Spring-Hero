package springhero.models.npc;

import springhero.models.hero.Hero;
import springhero.models.main.Cell;

import javax.swing.ImageIcon;

public class Enemy extends NPC {

    private boolean defeated;

    public Enemy(Cell cell, Hero hero) {
        this.defeated = false;
        this.upImg = new ImageIcon(ENEMIES_SRC + "enemy-w.png");
        this.downImg = new ImageIcon(ENEMIES_SRC + "enemy-s.png");
        this.leftImg = new ImageIcon(ENEMIES_SRC + "enemy-a.png");
        this.rightImg = new ImageIcon(ENEMIES_SRC + "enemy-d.png");
        this.updateSprite(view.RIGHT, cell);
        this.setTarget(hero.getPosition());
        hero.attachObserver(this);
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public void move() {
        // TODO : check if next position is available
        if (this.position.x != this.target.x) {
            this.position.x += ((this.target.x - this.position.x) / Math.abs(this.target.x - this.position.x));
        }
        if (this.position.y != this.target.y) {
            this.position.y += ((this.target.y - this.position.y) / Math.abs(this.target.y - this.position.y));
        }
    }
}