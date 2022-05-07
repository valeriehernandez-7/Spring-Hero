package springhero.views.main;

import springhero.models.main.Constants;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Spring Hero Game view class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHeroView extends JFrame implements ActionListener, Constants {

    private JLabel backgroundLbl;
    private final ImageIcon ICON_IMG = new ImageIcon(RESOURCES_SRC + "logo/icon.png");
    private final ImageIcon WELCOME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "welcome.png");
    private final ImageIcon CONTROLS_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "controls.png");
    private final ImageIcon GAME_BKG_IMG = new ImageIcon(BACKGROUNDS_SRC + "game.png");

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
    }

    private void getUiComponents() {
        backgroundSetup(screens.GAME);
        getContentPane().add(backgroundLbl);
    }

    private void backgroundSetup(screens screenName) {
        ImageIcon backgroundImg = new ImageIcon();
        switch (screenName) {
            case WELCOME -> backgroundImg = WELCOME_BKG_IMG;
            case CONTROLS -> backgroundImg = CONTROLS_BKG_IMG;
            case GAME -> backgroundImg = GAME_BKG_IMG;
        }
        if (backgroundImg.getImage() != null) {
            backgroundLbl = labelSetup(backgroundImg, 0, 0, true);
        }
    }

    private JLabel labelSetup(ImageIcon source, int posX, int posY, boolean visible) {
        JLabel label = new JLabel(source);
        label.setBounds(posX, posY, label.getIcon().getIconWidth(), label.getIcon().getIconHeight());
        label.setVisible(visible);
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
    }
}