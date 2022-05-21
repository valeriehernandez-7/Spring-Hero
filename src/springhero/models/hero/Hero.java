package springhero.models.hero;

import springhero.models.main.Cell;
import springhero.models.main.Constants;
import springhero.models.main.Map;
import springhero.models.npc.Enemy;
import springhero.models.observer.Observable;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class Hero implements Constants, Observable {

    private final JLabel sprite = new JLabel();
    private Point position;
    private int health;
    private final ImageIcon upImg, downImg, leftImg, rightImg, upAttackImg, downAttackImg, leftAttackImg, rightAttackImg;
    private view view = Constants.view.RIGHT;
    private final PropertyChangeSupport observerManager = new PropertyChangeSupport(this);

    public Hero(Cell cell) {
        this.health = HERO_MAX_HEALTH;
        this.upImg = new ImageIcon(HERO_SRC + "hero-w.png");
        this.downImg = new ImageIcon(HERO_SRC + "hero-s.png");
        this.leftImg = new ImageIcon(HERO_SRC + "hero-a.png");
        this.rightImg = new ImageIcon(HERO_SRC + "hero-d.png");
        this.upAttackImg = new ImageIcon(HERO_SRC + "hero-wAttack.png");
        this.downAttackImg = new ImageIcon(HERO_SRC + "hero-sAttack.png");
        this.leftAttackImg = new ImageIcon(HERO_SRC + "hero-aAttack.png");
        this.rightAttackImg = new ImageIcon(HERO_SRC + "hero-dAttack.png");
        this.updateSprite(this.view, cell);
    }

    public JLabel getSprite() {
        return sprite;
    }

    public void updateSprite(view view, Cell cell) {
        ImageIcon icon = new ImageIcon();
        switch (view) {
            case UP -> icon = this.upImg;
            case DOWN -> icon = this.downImg;
            case LEFT -> icon = this.leftImg;
            case RIGHT -> icon = this.rightImg;
        }
        if (icon.getImage() != null) {
            this.view = view;
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

    public boolean attack(Map map, view direction, List<Enemy> enemyList) {
        ImageIcon icon = new ImageIcon();
        Point detectAttack = new Point(0, 0);
        switch (direction) {
            case UP -> {
                icon = this.upAttackImg;
                detectAttack = new Point(this.position.x - 1, this.position.y);
            }
            case DOWN -> {
                icon = this.downAttackImg;
                detectAttack = new Point(this.position.x + 1, this.position.y);
            }
            case LEFT -> {
                icon = this.leftAttackImg;
                detectAttack = new Point(this.position.x, this.position.y - 1);
            }
            case RIGHT -> {
                icon = this.rightAttackImg;
                detectAttack = new Point(this.position.x, this.position.y + 1);
            }
        }
        if (icon.getImage() != null) {
            this.sprite.setIcon(icon);
            this.sprite.setBounds(new Rectangle(this.sprite.getIcon().getIconWidth(), this.sprite.getIcon().getIconHeight()));
            setPosition(map.getCell(this.position));
        }
        for (Enemy enemy : enemyList) {
            if (enemy.getPosition().equals(detectAttack)) {
                enemy.setDefeated(true);
                return true;
            }
        }
        return false;
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

    public void attachObserver(PropertyChangeListener newObserver) {
        observerManager.addPropertyChangeListener(newObserver);
    }

    public void detachObserver(PropertyChangeListener observer) {
        observerManager.removePropertyChangeListener(observer);
    }
}