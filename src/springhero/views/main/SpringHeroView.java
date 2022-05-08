package springhero.views.main;

import springhero.models.main.Constants;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.KeyListener;

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
    private JLabel backgroundLbl = labelSetup(WELCOME_BKG_IMG, 0, 0, true);
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
        hud = jPanelSetup();
        // TO DO: add progress bars to hud
        getContentPane().add(hud);
        // characters panel
        characters = jPanelSetup();
        // TO DO: add Hero & NPCs Sprites
        getContentPane().add(characters);
        // background panel
        background = jPanelSetup();
        backgroundSetup(screens.WELCOME);
        background.add(backgroundLbl);
        getContentPane().add(background);
    }

    public void backgroundSetup(screens screenName) {
        ImageIcon backgroundImg = new ImageIcon();
        switch (screenName) {
            case WELCOME -> backgroundImg = WELCOME_BKG_IMG;
            case CONTROLS -> backgroundImg = CONTROLS_BKG_IMG;
            case GAME -> backgroundImg = GAME_BKG_IMG;
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

    private JProgressBar progressBarSetup (int min, int max, int posX, int posY, Color color) {
        JProgressBar progressBar = new JProgressBar(min, max);
        progressBar.setBounds(posX, posY, 137, 33);;
        progressBar.setForeground(color);
        progressBar.setBorderPainted(false);
        return progressBar;
    }

    private JPanel jPanelSetup() {
        JPanel jPanel = new JPanel(null);
        jPanel.setOpaque(false);
        jPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        return jPanel;
    }
}