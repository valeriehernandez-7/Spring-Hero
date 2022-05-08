package springhero.models.hero;

import springhero.models.main.Cell;
import springhero.models.main.Constants;
import springhero.models.main.Map;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Hero implements Constants {

    private JLabel sprite;
    private Point position;
    private int health;
    private final ImageIcon upImg, downImg, leftImg, rightImg;

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
    public Point getPosition(){
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
            case ALLY -> this.health++;
            case ENEMY -> this.health--;
        }
    }
    public void moveHero(view view, Map map , Point newPosition){
        List<Cell> neighbors = map.getNeighbors(position);
        for(Cell neighbor : neighbors){
            if(neighbor.getID()==newPosition){
                setPosition(map.getCell(newPosition));
                setSprite(view);
                this.position = newPosition;
                //notify
                break;
            }
        }


    }


    public void rescue(Ally ally) {}

    public void attack(Enemy enemy) {}
}