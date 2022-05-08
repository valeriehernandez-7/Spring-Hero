package springhero.models.npc;

import springhero.models.main.Cell;

import javax.swing.ImageIcon;

public class Ally extends NPC {

    private boolean visible;
    private boolean rescued;

    public Ally(Cell cell) {
        this.rescued = false;
        this.upImg = new ImageIcon(ALLIES_SRC + "ally-w.png");
        this.downImg = new ImageIcon(ALLIES_SRC + "ally-s.png");
        this.leftImg = new ImageIcon(ALLIES_SRC + "ally-a.png");
        this.rightImg = new ImageIcon(ALLIES_SRC + "ally-d.png");
        this.setSprite(view.RIGHT);
        this.setPosition(cell);
        this.setVisible(false);
        //this.registerObserver();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
        this.sprite.setVisible(visible);
    }

    public boolean isRescued() {
        return rescued;
    }

    public void setRescued(boolean rescued) {
        this.rescued = rescued;
    }
}