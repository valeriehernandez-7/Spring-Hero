package springhero.models.npc;

import springhero.models.main.Cell;

import javax.swing.ImageIcon;

public class Ally extends NPC {

    private boolean visible;
    private boolean rescued;

    public Ally(Cell cell) {
        this.points = 100;
        this.rescued = false;
        this.sprite.setIcon(new ImageIcon(ALLIES_SRC + "ally-" + 0 + ".png"));
        this.setVisible(false);
        this.setPosition(cell);
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