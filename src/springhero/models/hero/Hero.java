package springhero.models.hero;

import springhero.models.ObvserverPattern.Observable;
import springhero.models.ObvserverPattern.Observer;
import springhero.models.main.Cell;
import springhero.models.main.Constants;
import springhero.models.main.Map;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Hero implements Constants, Observable {

    private JLabel sprite;
    private Point position;
    private int health;
    private final ImageIcon upImg, downImg, leftImg, rightImg;
    private List<Observer> observerList;

    public Hero(Cell cell) {
        this.sprite = new JLabel();
        this.position = cell.getID();
        this.health = 15;
        this.upImg = new ImageIcon(HERO_SRC + "hero-w.png");
        this.downImg = new ImageIcon(HERO_SRC + "hero-s.png");
        this.leftImg = new ImageIcon(HERO_SRC + "hero-a.png");
        this.rightImg = new ImageIcon(HERO_SRC + "hero-d.png");
        this.setSprite(view.RIGHT);
    }

    public JLabel getSprite(){return sprite;}

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
        this.sprite.setLocation((cell.getPosition().x - (this.sprite.getIcon().getIconWidth() / 2)), (cell.getPosition().y - (this.sprite.getIcon().getIconHeight() / 2)));
        cell.setEntity(getClass().getSimpleName());
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

    public void move(view direction, Map map) {
        Cell currentCell = map.getCell(this.position);
        Point nextPosition = null;
        switch (direction) {
            case UP -> nextPosition = new Point(this.position.x - 1, this.position.y);
            case DOWN -> nextPosition = new Point(this.position.x + 1, this.position.y);
            case LEFT -> nextPosition = new Point(this.position.x, this.position.y - 1);
            case RIGHT -> nextPosition = new Point(this.position.x, this.position.y + 1);
        }
        if ((nextPosition != null) & (currentCell.getNeighbors().contains(map.getCell(nextPosition)))) {
            setPosition(map.getCell(nextPosition));
            setSprite(direction);
            currentCell.resetEntity();
            notifyObserver(map.getCell(nextPosition));
        }
    }

    public void rescue(Ally ally) {}

    public void attack(Enemy enemy) {}


    @Override
    public void attach(Observer o) {
        this.observerList.add(o);

    }

    @Override
    public void detach(Observer o) {
        this.observerList.remove(o);

    }

    @Override
    public void notifyObserver(Cell cell) {
        if(observerList!= null) {
            for (Observer observer : observerList) {
                observer.update(cell);
            }
        }

    }
}