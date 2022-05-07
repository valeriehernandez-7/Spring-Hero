package springhero.models.npc;

import springhero.models.main.Cell;
import springhero.models.main.Constants;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Rectangle;

public class NPC implements Constants {

    protected JLabel sprite = new JLabel();
    protected Point position;
    protected ImageIcon upImg, downImg, leftImg, rightImg;

    public JLabel getSprite() {
        return sprite;
    }

    public void setSprite(view view) {
        ImageIcon icon = new ImageIcon();
        switch (view) {
            case UP -> icon = this.upImg;
            case DOWN -> icon = this.downImg;
            case LEFT -> icon = this.leftImg;
            case RIGHT -> icon = this.rightImg;
        }
        if (icon.getImage() != null) {
            this.sprite.setIcon(icon);
            this.sprite.setBounds(new Rectangle(this.sprite.getIcon().getIconWidth(), this.sprite.getIcon().getIconHeight()));
        }
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Cell cell) {
        this.position = cell.getID();
        this.sprite.setLocation((cell.getPosition().x - (sprite.getIcon().getIconWidth() / 2)), (cell.getPosition().y - (sprite.getIcon().getIconHeight() / 2)));
        cell.setEntity(getClass().getSimpleName());
    }
}