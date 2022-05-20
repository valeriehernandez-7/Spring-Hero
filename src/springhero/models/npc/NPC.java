package springhero.models.npc;

import springhero.models.main.Cell;
import springhero.models.main.Constants;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class NPC implements Constants, PropertyChangeListener {

    protected JLabel sprite = new JLabel();
    protected Point position;
    protected Point target;
    protected ImageIcon upImg, downImg, leftImg, rightImg;
    protected view view = Constants.view.RIGHT;

    public JLabel getSprite() {
        return sprite;
    }

    protected void updateSprite(Cell cell) {
        ImageIcon icon = new ImageIcon();
        switch (getView(cell.getID())) {
            case UP -> icon = this.upImg;
            case DOWN -> icon = this.downImg;
            case LEFT -> icon = this.leftImg;
            case RIGHT -> icon = this.rightImg;
        }
        if (icon.getImage() != null) {
            this.sprite.setIcon(icon);
            this.sprite.setBounds(new Rectangle(this.sprite.getIcon().getIconWidth(), this.sprite.getIcon().getIconHeight()));
            this.setPosition(cell);
            updateStatus();
        }
    }

    protected view getView(Point newPosition) {
        view newView = this.view;
        if ((this.target.x != newPosition.x) || (this.target.y != newPosition.y)) {
            if (this.target.x < newPosition.x) {
                newView = Constants.view.UP;
            } else if (this.target.x > newPosition.x) {
                newView = Constants.view.DOWN;
            }
            if (this.target.y < newPosition.y) {
                newView = Constants.view.LEFT;
            } else if (this.target.y > newPosition.y) {
                newView = Constants.view.RIGHT;
            }
        }
        this.view = newView;
        return newView;
    }

    public Point getPosition() {
        return position;
    }

    protected void setPosition(Cell cell) {
        this.position = cell.getID();
        this.sprite.setLocation((cell.getPosition().x - (this.sprite.getIcon().getIconWidth() / 2)), (cell.getPosition().y - (this.sprite.getIcon().getIconHeight() / 2)));
        cell.setEntity(getClass().getSimpleName());
    }

    public Point getTarget() {
        return target;
    }

    public void setTarget(Point target) {
        this.target = target;
    }

    @Override
    public void propertyChange(PropertyChangeEvent changeEvent) {
        setTarget((Point) changeEvent.getNewValue());
        updateStatus();
    }

    protected abstract void updateStatus();
}