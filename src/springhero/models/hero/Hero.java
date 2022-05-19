package springhero.models.hero;

import springhero.models.main.Cell;
import springhero.models.main.Constants;
import springhero.models.main.Map;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;
import springhero.models.observer.Observable;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Hero implements Constants, Observable {

    private JLabel sprite = new JLabel();
    private Point position;
    private int health;
    private final ImageIcon upImg, downImg, leftImg, rightImg;
    private PropertyChangeSupport observerManager = new PropertyChangeSupport(this);

    public Hero(Cell cell) {
        this.health = HERO_MAX_HEALTH;
        this.upImg = new ImageIcon(HERO_SRC + "hero-w.png");
        this.downImg = new ImageIcon(HERO_SRC + "hero-s.png");
        this.leftImg = new ImageIcon(HERO_SRC + "hero-a.png");
        this.rightImg = new ImageIcon(HERO_SRC + "hero-d.png");
        this.updateSprite(view.RIGHT, cell);
    }

    public JLabel getSprite() {
        return sprite;
    }

    private void updateSprite(view view, Cell cell) {
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
            this.setPosition(cell);
        }
    }

    public Point getPosition() {
        return position;
    }


    private void setPosition(Cell cell) {
        PropertyChangeEvent positionChanged = new PropertyChangeEvent(this, "position", this.position, cell.getID());
        this.position = cell.getID();
        this.sprite.setLocation((cell.getPosition().x - (this.sprite.getIcon().getIconWidth() / 2)), (cell.getPosition().y - (this.sprite.getIcon().getIconHeight() / 2)));
        cell.setEntity(getClass().getSimpleName());
        observerManager.firePropertyChange(positionChanged);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHealth(NPCType npc) {
        switch (npc) {
            case ALLY -> {
                if ((this.health + 1) <= HERO_MAX_HEALTH) {
                    this.health++;
                }
            }
            case ENEMY -> {
                if ((this.health - 1) >= 0) {
                    this.health--;
                }
            }
        }
    }

    public boolean move(view direction, Map map) {
        Cell currentCell = map.getCell(this.position);
        Point nextPosition = null;
        switch (direction) {
            case UP -> nextPosition = new Point(this.position.x - 1, this.position.y);
            case DOWN -> nextPosition = new Point(this.position.x + 1, this.position.y);
            case LEFT -> nextPosition = new Point(this.position.x, this.position.y - 1);
            case RIGHT -> nextPosition = new Point(this.position.x, this.position.y + 1);
        }
        if ((nextPosition != null) && map.isCell(nextPosition)) {
            if (currentCell.getNeighbors().contains(map.getCell(nextPosition))) {
                updateSprite(direction, map.getCell(nextPosition));
                currentCell.resetEntity();
                return true;
            }
        }
        return false;
    }

    public void rescue(Ally ally) {}

    public void attack(Enemy enemy) {}

    public void attachObserver(PropertyChangeListener newObserver) {
        observerManager.addPropertyChangeListener(newObserver);
    }

    public void detachObserver(PropertyChangeListener observer) {
        observerManager.removePropertyChangeListener(observer);
    }
}