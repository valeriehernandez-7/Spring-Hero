package springhero.controllers.main;

import springhero.models.main.Constants.screens;
import springhero.models.main.SpringHero;
import springhero.views.main.SpringHeroView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Spring Hero Game controller class.
 * @author <a href="https://github.com/valeriehernandez-7">Valerie M. Hernández Fernández</a>
 * @author <a href="https://github.com/Mariana612">Mariana Navarro Jiménez</a>
 */
public class SpringHeroController implements KeyListener {

    private final SpringHero springHero;
    private final SpringHeroView springHeroView;

    public SpringHeroController(){
        this.springHero = new SpringHero();
        this.springHeroView = new SpringHeroView(this);
    }

    public screens getSpringHeroScreenview() {
        return springHero.getScreenview();
    }

    public void setSpringHeroScreenview(screens screenview) {
        this.springHero.setScreenview(screenview);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (getSpringHeroScreenview() == screens.GAME) {
            if (key == KeyEvent.VK_W) {
                System.out.println("UP"); // TODO: DELETE LINE
                // TODO: HERO UP()
            }
            if (key == KeyEvent.VK_S) {
                System.out.println("DOWN"); // TODO: DELETE LINE
                // TODO: HERO DOWN()
            }
            if (key == KeyEvent.VK_A) {
                System.out.println("LEFT"); // TODO: DELETE LINE
                // TODO: HERO LEFT()
            }
            if (key == KeyEvent.VK_D) {
                System.out.println("RIGHT"); // TODO: DELETE LINE
                // TODO: HERO RIGHT()
            }
            if (key == KeyEvent.VK_F) {
                System.out.println("ATTACK"); // TODO: DELETE LINE
                // TODO: HERO ATTACK()
            }
            // TODO: observer events
            // TODO: map.update()
        } else if (getSpringHeroScreenview() == screens.CONTROLS) {
            if (key == KeyEvent.VK_P) {
                setSpringHeroScreenview(screens.GAME);
                this.springHeroView.screenSetup(getSpringHeroScreenview());
                // TODO: gameInit()
            }
        }  else if (getSpringHeroScreenview() == screens.WELCOME) {
            if (key == KeyEvent.VK_X) {
                setSpringHeroScreenview(screens.CONTROLS);
                this.springHeroView.screenSetup(getSpringHeroScreenview());
            }
        }
    }
}