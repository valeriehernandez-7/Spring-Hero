package springhero.models.hero;

import springhero.models.main.Cell;
import springhero.models.main.Constants;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.Rectangle;

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

    private void setSprite(view view) {
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

    public void setPosition(Cell cell) {
        this.position = cell.getID();
        this.sprite.setLocation((cell.getPosition().x - (this.sprite.getIcon().getIconWidth() / 2)), (cell.getPosition().y - (this.sprite.getIcon().getIconHeight() / 2)));
        cell.setEntity(getClass().getSimpleName());
    }

    public void moveHero() {}
}