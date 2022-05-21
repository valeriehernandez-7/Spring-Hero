package springhero.views.main;

import springhero.controllers.main.SpringHeroController;
import springhero.models.hero.Hero;
import springhero.models.main.Constants;
import springhero.models.npc.Ally;
import springhero.models.npc.Enemy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Spring Hero Game view class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHeroView extends JFrame implements Constants {

    private final ImageIcon ICON_IMG = new ImageIcon(RESOURCES_SRC + "logo/icon.png");
    private final ImageIcon WELCOME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "welcome.png");
    private final ImageIcon CONTROLS_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "controls.png");
    private final ImageIcon GAME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "game.png");
    private final JLabel backgroundLbl = labelSetup(WELCOME_BKG_IMG, 0, 0, true);
    private JProgressBar heroHealthBar, alliesRescuedBar, enemiesDestroyedBar;
    private JPanel hud, characters, background;


    public SpringHeroView(KeyListener keyListener) {
        setIconImage(ICON_IMG.getImage());
        setTitle("Spring Hero");
        setSize(WINDOW_WIDTH + 15, WINDOW_HEIGHT + 35);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getUiComponents();
        setVisible(true);
        addKeyListener(keyListener);
    }

    private void getUiComponents() {
        // hud panel
        hud = jPanelSetup(false);
        heroHealthBar = progressBarSetup(0, HERO_MAX_HEALTH, 90, 40, HERO_HEALTH_BAR_COLOR);
        hud.add(heroHealthBar);
        alliesRescuedBar = progressBarSetup(0, ALLIES_MAX_AMOUNT, 331, 40, ALLIES_RESCUED_BAR_COLOR);
        hud.add(alliesRescuedBar);
        enemiesDestroyedBar = progressBarSetup(0, ENEMIES_MAX_AMOUNT, 572, 40, ENEMIES_DESTROYED_BAR_COLOR);
        hud.add(enemiesDestroyedBar);
        getContentPane().add(hud);
        // characters panel
        characters = jPanelSetup(false);
        getContentPane().add(characters);
        // background panel
        background = jPanelSetup(true);
        screenSetup(screens.WELCOME);
        background.add(backgroundLbl);
        getContentPane().add(background);
    }

    public void screenSetup(screens screenName) {
        ImageIcon backgroundImg = new ImageIcon();
        switch (screenName) {
            case WELCOME -> {
                backgroundImg = WELCOME_BKG_IMG;
                hud.setVisible(false);
                characters.setVisible(false);
            }
            case CONTROLS -> backgroundImg = CONTROLS_BKG_IMG;
            case GAME -> {
                backgroundImg = GAME_BKG_IMG;
                hud.setVisible(true);
                characters.setVisible(true);
            }
        }
        if (backgroundImg.getImage() != null) {
            backgroundLbl.setIcon(backgroundImg);
        }
    }

    private JLabel labelSetup(ImageIcon source, int posX, int posY, boolean visible) {
        JLabel label = new JLabel(source);
        label.setBounds(posX, posY, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setVisible(visible);
        return label;
    }

    private JProgressBar progressBarSetup(int min, int max, int posX, int posY, Color color) {
        JProgressBar progressBar = new JProgressBar(min, max);
        progressBar.setBounds(posX, posY, 135, 32);
        progressBar.setBackground(NO_COLOR);
        progressBar.setForeground(color);
        progressBar.setBorder(null);
        progressBar.setBorderPainted(false);
        progressBar.setValue(progressBar.getMaximum());
        return progressBar;
    }

    private JPanel jPanelSetup(boolean visible) {
        JPanel jPanel = new JPanel(null);
        jPanel.setOpaque(false);
        jPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        jPanel.setVisible(visible);
        return jPanel;
    }

    private void gameOver(int level) {
        int input = JOptionPane.showConfirmDialog(this,
                "LEVEL: " + level + "\nDo you want to play another round of Spring Hero?",
                "Spring Hero", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, ICON_IMG);
        dispose();
        if (input == JOptionPane.YES_OPTION) {
            new SpringHeroController();
        }
    }

    private void updateHero(Hero hero) {
        characters.add(hero.getSprite());
        heroHealthBar.setValue(hero.getHealth());
    }
//
//    private void updateHeroAttack(Hero hero) {
//        if (hero.getRock() != null) {
//            characters.add(hero.getRock().getSprite());
//        }
//    }

    private void updateAllies(List<Ally> allies) {
        for (Ally ally : allies) {
            characters.add(ally.getSprite());
        }
        alliesRescuedBar.setValue(allies.size());
    }

    private void updateEnemies(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            characters.add(enemy.getSprite());
        }
        enemiesDestroyedBar.setValue(enemies.size());
    }

    private void updateCharacters(Hero hero, List<Ally> allies, List<Enemy> enemies) {
        characters.removeAll();
        updateHero(hero);
        updateEnemies(enemies);
        //updateHeroAttack(hero);
        updateAllies(allies);
        characters.repaint();
    }

    public void update(boolean gameOver, int level, Hero hero, List<Ally> allies, List<Enemy> enemies) {
        if (!gameOver) {
            updateCharacters(hero, allies, enemies);
            hud.repaint();
        } else {
            characters.remove(hero.getSprite());
            gameOver(level);
        }
    }
}