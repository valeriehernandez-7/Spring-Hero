package springhero.models.npc;

import springhero.models.hero.Hero;
import springhero.models.main.Cell;
import springhero.models.main.Map;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Enemy model class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
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

    private void attack(Hero hero) {
        setDefeated(true);
        hero.setHealth(NPCType.ENEMY);
    }

    private void step(Map map, Cell cell, Point position) {
        if (map.isCell(position)) {
            if (!map.getCell(position).getEntity().equals("Enemy")) {
                updateSprite(map.getCell(position));
                cell.resetEntity();
            }
        }
    }

    public void move(Map map, Hero hero) {
        if (!isDefeated()) {
            Cell currentCell = map.getCell(this.position);
            Point nextPosition = new Point();
            if ((this.position.x != this.target.x) && (this.position.y != this.target.y)) {
                int random_int = (int) Math.floor(Math.random() * (2 - 1 + 1) + 1);
                switch (random_int) {
                    case 1 -> {
                        nextPosition.x = this.position.x + ((this.target.x - this.position.x) / Math.abs(this.target.x - this.position.x));
                        step(map, currentCell, new Point(nextPosition.x, this.position.y));
                    }
                    case 2 -> {
                        nextPosition.y = this.position.y + ((this.target.y - this.position.y) / Math.abs(this.target.y - this.position.y));
                        step(map, currentCell, new Point(this.position.x, nextPosition.y));
                    }
                }
            } else if (this.position.x != this.target.x) {
                nextPosition.x = this.position.x + ((this.target.x - this.position.x) / Math.abs(this.target.x - this.position.x));
                step(map, currentCell, new Point(nextPosition.x, this.position.y));
            } else if (this.position.y != this.target.y) {
                nextPosition.y = this.position.y + ((this.target.y - this.position.y) / Math.abs(this.target.y - this.position.y));
                step(map, currentCell, new Point(this.position.x, nextPosition.y));
            }
            if ((this.position.x == this.target.x) && (this.position.y == this.target.y)) {
                attack(hero); // enemy attacks hero
            }
        }
    }

    @Override
    protected void updateStatus() {
        if (getPosition().x == getTarget().x && getPosition().y == getTarget().y) {
            setDefeated(true); // hero defeats enemy
        }
    }
}