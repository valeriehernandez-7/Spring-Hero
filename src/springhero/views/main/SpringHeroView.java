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
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
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
    private final ImageIcon LEVEL_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "level.png");
    private final ImageIcon GAME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "game.png");
    private final JLabel backgroundLbl = labelSetup(WELCOME_BKG_IMG, 0, 0, true);
    private final JLabel levelBkgLbl = labelSetup(LEVEL_BKG_IMG, 0, 0, true);
    private JProgressBar heroHealthBar, alliesRescuedBar, enemiesDestroyedBar;
    private JPanel hud, info, characters, background;
    private JLabel levelLbl;
    private Font fontSource, font;
    private int level = 0;

    public SpringHeroView(KeyListener keyListener) {
        setIconImage(ICON_IMG.getImage());
        setTitle(GAME_NAME);
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
        // fonts
        setFontSource(100);
        // info panel
        info = jPanelSetup(false);
        // level label
        levelLbl = textLabelSetup(372, 225, 115, 85, LEVEL_TXT_COLOR, String.valueOf(level), true);
        info.add(levelLbl);
        info.add(levelBkgLbl);
        getContentPane().add(info);
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

    private JLabel labelSetup(ImageIcon source, int posX, int posY, boolean visible) {
        JLabel label = new JLabel(source);
        label.setBounds(posX, posY, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setVisible(visible);
        return label;
    }

    private JLabel textLabelSetup(int posX, int posY, int width, int height, Color color, String text, boolean visible) {
        JLabel textLabel = new JLabel();
        textLabel.setBounds(posX, posY, width, height);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalAlignment(JLabel.CENTER);
        textLabel.setFont(font);
        textLabel.setForeground(color);
        textLabel.setText(text);
        textLabel.setVisible(visible);
        return textLabel;
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

    private void setFontSource(int size) {
        try {
            fontSource = Font.createFont(Font.TRUETYPE_FONT, new File(FONTS_SRC + "SunFlower.ttf"));
            font = fontSource.deriveFont(Font.PLAIN, size);
        } catch (IOException | FontFormatException exception) {
            exception.printStackTrace();
        }
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

    private void gameOver() {
        int input = JOptionPane.showConfirmDialog(this,
                "GAME OVER\nDo you want to play another round of " + GAME_NAME + "?",
                GAME_NAME, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, ICON_IMG);
        dispose();
        if (input == JOptionPane.YES_OPTION) {
            new SpringHeroController();
        }
    }

    private void updateLevel(int level) {
        this.level = level;
        info.setVisible(true);
        levelLbl.setText(String.valueOf(this.level));
        new Timer(1500, e -> {
            info.setVisible(false);
            ((Timer) e.getSource()).stop();
        }).start();
    }

    private void updateHero(Hero hero) {
        characters.add(hero.getSprite());
        heroHealthBar.setValue(hero.getHealth());
    }

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
        updateAllies(allies);
        characters.repaint();
    }

    public void update(boolean gameOver, int level, Hero hero, List<Ally> allies, List<Enemy> enemies) {
        if (!gameOver) {
            updateCharacters(hero, allies, enemies);
            hud.repaint();
            if (level != this.level) {
                updateLevel(level);
            }
        } else {
            characters.remove(hero.getSprite());
            gameOver();
        }
    }
}