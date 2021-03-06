package springhero.models.npc;

import springhero.models.hero.Hero;
import springhero.models.main.Cell;
import springhero.models.main.Map;

/**
 * Ally model class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 */
import javax.swing.ImageIcon;

public class Ally extends NPC {

    private boolean rescued;

    public Ally(Cell cell, Hero hero) {
        this.rescued = false;
        this.upImg = new ImageIcon(ALLIES_SRC + "ally-w.png");
        this.downImg = new ImageIcon(ALLIES_SRC + "ally-s.png");
        this.leftImg = new ImageIcon(ALLIES_SRC + "ally-a.png");
        this.rightImg = new ImageIcon(ALLIES_SRC + "ally-d.png");
        this.setTarget(hero.getPosition());
        this.updateSprite(cell);
        this.setVisible(false);
        hero.attachObserver(this);
    }

    private void setVisible(boolean visible) {
        getSprite().setVisible(visible);
    }

    public boolean isRescued() {
        return rescued;
    }

    private void setRescued(boolean rescued) {
        this.rescued = rescued;
        if (rescued) {
            setVisible(false);
        }
    }

    public void checkStatus(Map map, Hero hero) {
        if (isRescued()) {
            map.getCell(this.position).resetEntity();
            hero.setHealth(NPCType.ALLY);
        } else {
            this.updateSprite(map.getCell(this.position));
        }
    }

    @Override
    protected void updateStatus() {
        if (getPosition().x == getTarget().x && getPosition().y == getTarget().y) {
            setRescued(true);
        } else {
            setVisible((Math.abs(getPosition().x - getTarget().x) < ALLY_RANGE) && (Math.abs(getPosition().y - getTarget().y) < ALLY_RANGE));
        }
    }
}