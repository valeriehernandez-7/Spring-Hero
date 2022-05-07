package springhero.views.main;

import springhero.models.main.Constants;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Spring Hero Game view class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHeroView extends JFrame implements KeyListener, Constants {

    private final ImageIcon ICON_IMG = new ImageIcon(RESOURCES_SRC + "logo/icon.png");
    private final ImageIcon WELCOME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "welcome.png");
    private final ImageIcon CONTROLS_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "controls.png");
    private final ImageIcon GAME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "game.png");
    private screens screenview = screens.WELCOME;
    private JLabel backgroundLbl = labelSetup(WELCOME_BKG_IMG, 0, 0, true);
    private JPanel hud, characters, background;

    public SpringHeroView() {
        setIconImage(ICON_IMG.getImage());
        setTitle("Spring Hero");
        setSize(WINDOW_WIDTH + 15, WINDOW_HEIGHT + 35);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getUiComponents();
        setVisible(true);
        addKeyListener(this);
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

    private void backgroundSetup(screens screenName) {
        screenview = screenName;
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

    private JPanel jPanelSetup() {
        JPanel jPanel = new JPanel(null);
        jPanel.setOpaque(false);
        jPanel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        return jPanel;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (screenview == screens.GAME) {
            if (key == KeyEvent.VK_W) {
                System.out.println("UP"); // TO DO: DELETE LINE
                // TO DO: HERO UP()
            }
            if (key == KeyEvent.VK_S) {
                System.out.println("DOWN"); // TO DO: DELETE LINE
                // TO DO: HERO DOWN()
            }
            if (key == KeyEvent.VK_A) {
                System.out.println("LEFT"); // TO DO: DELETE LINE
                // TO DO: HERO LEFT()
            }
            if (key == KeyEvent.VK_D) {
                System.out.println("RIGHT"); // TO DO: DELETE LINE
                // TO DO: HERO RIGHT()
            }
            if (key == KeyEvent.VK_F) {
                System.out.println("ATTACK"); // TO DO: DELETE LINE
                // TO DO: HERO ATTACK()
            }
            // TO DO: observer events
            // TO DO: map.update()
        } else if (screenview == screens.CONTROLS) {
            if (key == KeyEvent.VK_P) {
                backgroundSetup(screens.GAME);
                // TO DO: gameInit()
            }
        }  else if (screenview == screens.WELCOME) {
            if (key == KeyEvent.VK_X) {
                backgroundSetup(screens.CONTROLS);
            }
        }
    }
}