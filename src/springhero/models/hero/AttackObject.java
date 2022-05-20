package springhero.models.hero;

import springhero.models.main.Constants;
import springhero.models.npc.Enemy;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Point;
import java.awt.Rectangle;

public class AttackObject implements Constants {

    private final JLabel sprite;

    public AttackObject(Hero hero) {
        this.sprite = new JLabel(new ImageIcon(HERO_SRC + "hero-f.png"));
        setPosition(hero.getSprite().getLocation());
    }

    public JLabel getSprite() {
        return sprite;
    }

    private void setPosition(Point position) {
        this.sprite.setBounds(new Rectangle(this.sprite.getIcon().getIconWidth(), this.sprite.getIcon().getIconHeight()));
        this.sprite.setLocation((position.x - (this.sprite.getIcon().getIconWidth() / 2)), (position.y - (this.sprite.getIcon().getIconHeight() / 2)));
    }

    public void move(Enemy enemy) {
        Point spriteCenter = new Point(((enemy.getSprite().getSize().width / 2) + enemy.getSprite().getLocation().x), ((enemy.getSprite().getSize().height / 2) + enemy.getSprite().getLocation().y));
        setPosition(spriteCenter);
    }
}
